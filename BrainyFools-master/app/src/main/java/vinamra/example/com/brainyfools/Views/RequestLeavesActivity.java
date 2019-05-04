package vinamra.example.com.brainyfools.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import vinamra.example.com.brainyfools.R;

public class RequestLeavesActivity extends AppCompatActivity
{

    EditText  abc;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_leaves);
    }
}
