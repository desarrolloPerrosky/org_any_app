package cl.perrosky.organizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.impl.LoginDataSource;
import cl.perrosky.organizapp.model.Categoria;
import cl.perrosky.organizapp.model.Usuario;

public class SplashActivity extends AppCompatActivity {

    private LoginDataSource loginDataSource;
    private Usuario usuario;
    private Handler mHandlerBarra;

    private ProgressBar barra;
    private int progreso;

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

                if(usuario==null){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    String welcome = getString(R.string.welcome) + usuario.getNombre() + " " + usuario.getApellido();
                    Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("LOGIN", usuario);
                    startActivity(intent);
                }
                finish();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_splash);

        barra = (ProgressBar) findViewById(R.id.progressBar);

        loginDataSource = new LoginDataSource(SplashActivity.this);

        usuario = loginDataSource.loginActive();
        progreso = 0;
        barra.setProgress(progreso);

        mHandlerBarra = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandlerBarra.postDelayed(manejadorBarra,  150);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandlerBarra.removeCallbacks(manejadorBarra);
    }
}