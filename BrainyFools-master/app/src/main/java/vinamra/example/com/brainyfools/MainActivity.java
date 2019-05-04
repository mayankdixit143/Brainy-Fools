package vinamra.example.com.brainyfools;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import vinamra.example.com.brainyfools.Connection.RequestHandler;
import vinamra.example.com.brainyfools.Services.LocationMonitoringService;
import vinamra.example.com.brainyfools.Session.SessionManager;
import vinamra.example.com.brainyfools.Views.DashBoardActivity;
import vinamra.example.com.brainyfools.Views.SelectionActivity;

public class MainActivity extends AppCompatActivity
{

    RelativeLayout rellay1;
    Button btnLogin;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private static final String TAG = MainActivity.class.getSimpleName();
    String mPermission= Manifest.permission.ACCESS_FINE_LOCATION;
    EditText etUserid, etPassWord;
    String empid="",pass="";
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!checkPermissions())
        {
            requestPermissions();
        }
        else
        {
            startService(new Intent(MainActivity.this,LocationMonitoringService.class));
        }
        session=new SessionManager(getApplicationContext());
        btnLogin = findViewById(R.id.buttonLogin);
        etUserid = findViewById(R.id.userid);
        etPassWord = findViewById(R.id.passWord);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
        if(session.isLoggedIn()&&("Head").equals(session.getDetails().get(session.KEY_DESGN)))
        {
            startActivity(new Intent(getApplicationContext(),SelectionActivity.class));
            finish();
        }
        else if(session.isLoggedIn())
        {
            startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                empid = etUserid.getText().toString().trim();
                pass=etPassWord.getText().toString().trim();
                new Login().execute();
            }
        });
    }
    private boolean checkPermissions()
    {
        int permissionState1 = ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        int permissionState2 = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        return permissionState1 == PackageManager.PERMISSION_GRANTED && permissionState2 == PackageManager.PERMISSION_GRANTED;

    }

    /**
     * Start permissions requests.
     */
    private void requestPermissions()
    {

        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

        boolean shouldProvideRationale2 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);


        // Provide an additional rationale to the img_user. This would happen if the img_user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale || shouldProvideRationale2)
        {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
        }
        else
        {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the img_user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
    private class Login extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("eid",empid);
            hm.put("password",pass);
            Log.d("hello",hm+"");
            return requestHandler.sendPostRequest("http://192.168.43.41:8081/innotech/apis/user/login.php",hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            try
            {
                JSONObject jsonObject=new JSONObject(s);
                JSONObject info=jsonObject.getJSONObject("info");
                if(info.getString("status").equals("success"))
                {
                    JSONObject records=jsonObject.getJSONObject("records");
                    String name=records.getString("name");
                    String email=records.getString("email");
                    String phone=records.getString("phone");
                    String desgn=records.getString("desgn");
                    String joining=records.getString("joining");
                    String emp_id=records.getString("emp_id");
                    String img=records.getString("image_url");
                    String deviceid=records.getString("deviceid");
                    session.createLoginSession(emp_id,phone);
                    session.saveDetails(name,email,desgn,deviceid,img,joining);
                    if(desgn.equals("Head"))
                    {
                        Intent intent=new Intent(getApplicationContext(),SelectionActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    Intent intent=new Intent(getApplicationContext(),DashBoardActivity.class);
                    intent.putExtras(intent);
                    startActivity(intent);
                    finish();
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }


        }
    }
}
