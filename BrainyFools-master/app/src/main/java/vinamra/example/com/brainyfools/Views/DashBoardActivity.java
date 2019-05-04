package vinamra.example.com.brainyfools.Views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import vinamra.example.com.brainyfools.Connection.RequestHandler;
import vinamra.example.com.brainyfools.Models.Employee;
import vinamra.example.com.brainyfools.R;
import vinamra.example.com.brainyfools.Services.LocationMonitoringService;
import vinamra.example.com.brainyfools.Session.SessionManager;

public class DashBoardActivity extends AppCompatActivity
{

    CardView cardView_Profile, cardView_CurrentStatus, cardView_Notification, cardView_PermissionforLeaves, cardView_Update_project_status, cardView_Perks;
    CardView getCardView_Profile_head, getCardView_CurrentStatus_head, cardView_Messages_head, cardView_PermissionforLeaves_head,
            cardView_Project_status_head, cardView_calender_head;
    String userName="";
    String com = "Head";
    SessionManager session;
    boolean checked=true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        session=new SessionManager(getApplicationContext());
        userName=session.getDetails().get(session.KEY_DESGN);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String latitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LATITUDE);
                String longitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LONGITUDE);
//                moving.setLatitude(Double.parseDouble(latitude));
//                moving.setLongitude(Double.parseDouble(longitude));
                Date date=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
                if((sdf.format(date).compareTo("09:00")>0)&&(sdf.format(date).compareTo("18:00")<0))
                {
                    Log.e("Latitude",latitude);
                    Log.e("longitude",longitude);
                    Toast.makeText(getApplicationContext(),"Office Time",Toast.LENGTH_SHORT).show();
                    if(checked==true)
                    {
                        new UploadAttendance().execute();
                        checked=false;
                    }
                }
                else
                {
                    checked=true;
                    Log.e("Time out","time out");
                }
//                Toast.makeText(context, ""+moving.distanceTo(pin), Toast.LENGTH_SHORT).show();
//                if (moving.distanceTo(pin) < 100)
//                {
//                    checkin.setEnabled(true);
//                }
//                runService();
            }
        }, new IntentFilter(LocationMonitoringService.ACTION_LOCATION_BROADCAST));
        if(userName.equals(com))
        {
            //HEAD
            setContentView(R.layout.activity_dash_board_head);
            getCardView_Profile_head = findViewById(R.id.cardViewProfileHead);
            getCardView_CurrentStatus_head = findViewById(R.id.cardViewCurrentStatusHead);
            cardView_Messages_head = findViewById(R.id.cardViewMessageHead);
            cardView_PermissionforLeaves_head = findViewById(R.id.cardViewPermissionHead);
            cardView_Project_status_head = findViewById(R.id.cardViewProjectStatusHead);
            cardView_calender_head = findViewById(R.id.cardViewCalendarHead);
            getCardView_Profile_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_profile = new Intent(DashBoardActivity.this,ProfileActivity.class);
                    startActivity(intent_profile);
                }
            });
            getCardView_CurrentStatus_head.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(getApplicationContext(),ListEmployees.class));
                }
            });
            cardView_Messages_head.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(getApplicationContext(),SendMessage.class));
                }
            });
            cardView_PermissionforLeaves_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            cardView_Project_status_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            cardView_calender_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),StatusActivity.class));
                }
            });
        }
        else
        {
            setContentView(R.layout.activity_dash_board);
            cardView_Profile = findViewById(R.id.cardViewProfile);
            cardView_CurrentStatus = findViewById(R.id.cardViewCurrentStatus);
            cardView_Notification = findViewById(R.id.cardViewNotification);
            cardView_PermissionforLeaves = findViewById(R.id.cardViewPermission);
            cardView_Update_project_status = findViewById(R.id.cardViewUpdateProjectStatus);
            cardView_Perks = findViewById(R.id.cardViewPerks);
            cardView_Profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_profile = new Intent(DashBoardActivity.this,ProfileActivity.class);
                    startActivity(intent_profile);
                }
            });
            cardView_CurrentStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(getApplicationContext(),StatusActivity.class));
                }
            });
            cardView_Notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            cardView_PermissionforLeaves.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(getApplicationContext(),RequestLeavesActivity.class));
                }
            });
            cardView_Update_project_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            cardView_Perks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
//            Toast.makeText(DashBoardActivity.this,"EMPLOYEE",Toast.LENGTH_SHORT).show();
        }
    }

    private class UploadAttendance extends AsyncTask<Void,Void,String>
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
            hm.put("eid",session.getKeyUid());
            return requestHandler.sendPostRequest("http://192.168.43.41:8081/innotech/apis/user/incall.php",hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Log.d("result date",s);
        }
    }
}

