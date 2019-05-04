package vinamra.example.com.brainyfools.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import vinamra.example.com.brainyfools.R;

public class SurveillanceActivity extends AppCompatActivity
{

    CardView access,intrusion;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveillance);
        access=findViewById(R.id.cardViewAccess);
        intrusion=findViewById(R.id.cardViewIntrusion);
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),AccessControlActivity.class));
            }
        });
        intrusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),IntrusionActivity.class));
            }
        });
    }
}
