package vinamra.example.com.brainyfools.Views;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vinamra.example.com.brainyfools.Adapter.MessageAdapter;
import vinamra.example.com.brainyfools.Connection.RequestHandler;
import vinamra.example.com.brainyfools.Models.Employee;
import vinamra.example.com.brainyfools.R;

public class SendMessage extends AppCompatActivity
{
    private RecyclerView listEmployees;
    private List<Employee> employeeList=new ArrayList<>();
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        listEmployees=findViewById(R.id.allempmsg);
        messageAdapter=new MessageAdapter(employeeList,SendMessage.this);
        new FetchEmp().execute();
        listEmployees.setLayoutManager(new LinearLayoutManager(this));
        listEmployees.setItemAnimator(new DefaultItemAnimator());
        listEmployees.setAdapter(messageAdapter);
    }
    private class FetchEmp extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String,Object> hm=new HashMap<>();
            return requestHandler.sendPostRequest("http://192.168.43.41:8081/innotech/apis/user/fetchall.php",hm);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Log.d("onPostMessage",s);
            Employee employee=null;
            try
            {
                JSONObject jsonObject=new JSONObject(s);
                JSONObject info=jsonObject.getJSONObject("info");
                if(info.getString("status").equals("success"))
                {
                    JSONArray records=jsonObject.getJSONArray("records");
                    for(int i=0;i<records.length();i++)
                    {
                        JSONObject ith=records.getJSONObject(i);
                        if(ith.getString("desgn").equals("Head"))
                        {
                        }
                        else
                        {
                            employee=new Employee(ith.getString("name"),ith.getString("empid"));
                            employeeList.add(employee);
                        }
                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            messageAdapter.notifyDataSetChanged();
        }
    }
}
