package vinamra.example.com.brainyfools.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import vinamra.example.com.brainyfools.R;
import vinamra.example.com.brainyfools.Session.SessionManager;

import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_DESGN;
import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_Email;
import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_IMG;
import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_JOIN;
import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_NAME;
import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_PHONE;
import static vinamra.example.com.brainyfools.Session.SessionManager.KEY_UID;

public class ProfileActivity extends AppCompatActivity
{
    private TextView name,id,phone,model,email,joining,desgn;
    ImageView imgView;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session=new SessionManager(getApplicationContext());
        name=findViewById(R.id.tv_name);
        id=findViewById(R.id.emp_id);
        phone=findViewById(R.id.emp_phone);
        model=findViewById(R.id.model);
        email=findViewById(R.id.emp_email);
        desgn=findViewById(R.id.desgn);
        joining=findViewById(R.id.joining);
        imgView=findViewById(R.id.profilePic);

        HashMap<String,String> user=session.getDetails();
        Picasso.with(getApplicationContext()).load(user.get(KEY_IMG)).into(imgView);
        name.setText(user.get(KEY_NAME));
        id.setText(user.get(KEY_UID));
        email.setText(user.get(KEY_Email));
        desgn.setText(user.get(KEY_DESGN));
        joining.setText(user.get(KEY_JOIN));
        phone.setText(user.get(KEY_PHONE));
        name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                session.logoutUser();
                finish();
            }
        });
    }
}
