package vinamra.example.com.brainyfools.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import vinamra.example.com.brainyfools.R;

public class SelectionActivity extends AppCompatActivity
{

    CardView surveillance,dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        surveillance=findViewById(R.id.cardViewServeillance);
        dashboard=findViewById(R.id.cardViewDashboard);
        surveillance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),SurveillanceActivity.class));
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
            }
        });
    }
}
