package cl.perrosky.organizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;

import cl.perrosky.organizapp.R;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private Handler mHandlerBarra;

    private ProgressBar barra;
    private int progreso;

    Runnable myTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    };

    Runnable manejadorBarra = new Runnable() {
        @Override
        public void run() {
            Log.i("INFORMACION", "VALOR DE LA BARRA["+String.valueOf(progreso)+"]");
            progreso+=8;
            if(progreso<100){
                barra.setProgress(progreso);
                mHandlerBarra.postDelayed(manejadorBarra,  150);
            } else {
                progreso=100;
                barra.setProgress(progreso);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_splash);

        barra = (ProgressBar) findViewById(R.id.progressBar);

        progreso = 0;
        barra.setProgress(progreso);


        mHandler = new Handler(Looper.getMainLooper());
        mHandlerBarra = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.postDelayed(myTask,  2000);
        mHandlerBarra.postDelayed(manejadorBarra,  150);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(myTask);
        mHandler.removeCallbacks(manejadorBarra);
    }
}