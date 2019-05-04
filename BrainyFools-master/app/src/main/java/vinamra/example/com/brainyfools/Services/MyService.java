package vinamra.example.com.brainyfools.Services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vinamra.example.com.brainyfools.R;
import vinamra.example.com.brainyfools.Views.SelectionActivity;

import static android.support.constraint.Constraints.TAG;

public class MyService extends Service
{
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"service started",Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cam");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.child("Ping1").getValue(String.class);
                String value2 = dataSnapshot.child("Ping2").getValue(String.class);
                Log.e("Value from ping 1",value);
                Log.e("Value from ping 2",value2);
                if(value.equals("1"))
                    ping(value);
                else
                    ping(value2);
            }//  @Override
            public void onCancelled(DatabaseError error) {
                //    Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return START_STICKY;
    }
    public void onDestroy(){

        super.onDestroy();
        Toast.makeText(this,"service stopped",Toast.LENGTH_LONG).show();
    }
    public void ping(String x){
        if(Integer.parseInt(x)==1){
            Intent intent = new Intent(this, SelectionActivity.class);

            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
            taskStackBuilder.addParentStack(SelectionActivity.class);
            taskStackBuilder.addNextIntent(intent);

            PendingIntent pendingIntent = taskStackBuilder.
                    getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("New face Detected by service")
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);
        }


    }
}
