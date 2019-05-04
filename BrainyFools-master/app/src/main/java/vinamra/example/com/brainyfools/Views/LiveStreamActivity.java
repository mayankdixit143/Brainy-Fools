package vinamra.example.com.brainyfools.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import vinamra.example.com.brainyfools.R;

public class LiveStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);
    }
    public void playimg(View v)
    {
        WebView wb = findViewById(R.id.wb);
        String piAddr = "http://192.168.78.1:5000";
        wb.loadUrl(piAddr);
    }
}
