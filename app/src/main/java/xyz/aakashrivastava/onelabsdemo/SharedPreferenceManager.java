package xyz.aakashrivastava.onelabsdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("ONEL", Context.MODE_PRIVATE);
    }

    public void writeGridSpanSize(int status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("GRISPA", status);
        editor.commit();
    }

    public int readGridSpanSize() {
        int status = 2;
        status = sharedPreferences.getInt("GRISPA", 2);
        return status;
    }
}
