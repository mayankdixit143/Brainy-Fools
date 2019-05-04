package vinamra.example.com.brainyfools.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.HashMap;

import vinamra.example.com.brainyfools.MainActivity;

public class SessionManager
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    private static final String IS_LOGIN="isLoggedIn";
    private static final String PREF_NAME="Innotech_Session";
    public static final String KEY_UID="uid";
    public static final String KEY_NAME="username";
    public static final String KEY_PHONE="phone";
    public static final String KEY_Email="email";
    public static final String KEY_DESGN="designation";
    public static final String KEY_JOIN="joining date";
    public static final String KEY_MODEL="phone model";
    public static final String KEY_IMG="image url";

    int PRIVATE_MODE=0;
    public SessionManager(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }
    public void createLoginSession(String eid, String mobileno)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_UID,eid);
        editor.putString(KEY_PHONE,mobileno);
        editor.commit();
    }
    public boolean isLoggedIn()
    {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
    public void checkLogin()
    {
        if(!this.isLoggedIn())
        {
            Intent i=new Intent(context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
    public void logoutUser()
    {
        editor.clear();
        editor.commit();
        Intent i=new Intent(context,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public void saveDetails(String name, String email, String desgn, String model, String img_url, String join)
    {
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_Email,email);
        editor.putString(KEY_DESGN,desgn);
        editor.putString(KEY_MODEL,model);
        editor.putString(KEY_IMG,img_url);
        editor.putString(KEY_JOIN,join);
        editor.commit();
    }

    public HashMap<String,String> getDetails()
    {
        HashMap<String,String> user=new HashMap<>();
        user.put(KEY_NAME,sharedPreferences.getString(KEY_NAME,null));
        user.put(KEY_Email,sharedPreferences.getString(KEY_Email,null));
        user.put(KEY_PHONE,sharedPreferences.getString(KEY_PHONE,null));
        user.put(KEY_UID,sharedPreferences.getString(KEY_UID,null));
        user.put(KEY_JOIN,sharedPreferences.getString(KEY_JOIN,null));
        user.put(KEY_DESGN,sharedPreferences.getString(KEY_DESGN,null));
        user.put(KEY_MODEL,sharedPreferences.getString(KEY_MODEL,null));
        user.put(KEY_IMG,sharedPreferences.getString(KEY_IMG,null));
        return user;
    }

    public String getKeyUid()
    {
        return sharedPreferences.getString(KEY_UID,null);
    }
}
