package cl.perrosky.organizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    Runnable myTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    };

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.postDelayed(myTask, 10); // 2000
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(myTask);
    }
}