package cl.perrosky.organizapp.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class EscanearActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final String RETORNO = "codigo_de_barras";
    private static final int CODIGO_PERMISOS_CAMARA = 1;

    public static final int CODIGO_INTENT = 3;

    private boolean permisoCamaraConcedido = false;
    private boolean permisoSolicitadoDesdeBoton = false;

    private ZXingScannerView escanerZXing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_escanear);
        escanerZXing = new ZXingScannerView(this);

        verificarYPedirPermisosDeCamara();

        // Hacer que el contenido de la actividad sea el escaner
        setContentView(escanerZXing);
    }

    @Override
    public void onResume() {
        super.onResume();
        // El "manejador" del resultado es esta misma clase, por eso implementamos ZXingScannerView.ResultHandler
        escanerZXing.setResultHandler(this);
        escanerZXing.startCamera(); // Comenzar la cámara en onResume
    }

    @Override
    public void onPause() {
        super.onPause();
        escanerZXing.stopCamera(); // Pausar en onPause
    }

    @Override
    public void handleResult(Result resultado) {

        // Si quieres que se siga escaneando después de haber leído el código, descomenta lo siguiente:
        // Si la descomentas no recomiendo que llames a finish
//        escanerZXing.resumeCameraPreview(this);
        // Obener código/texto leído
        String codigo = resultado.getText();
        // Preparar un Intent para regresar datos a la actividad que nos llamó
        Intent intentRegreso = new Intent();
        intentRegreso.putExtra(RETORNO, codigo);
        setResult(Activity.RESULT_OK, intentRegreso);

        // Cerrar la actividad. Ahora mira onActivityResult de MainActivity
        finish();
    }

    private void verificarYPedirPermisosDeCamara() {

        int estadoDePermiso = ContextCompat.checkSelfPermission(EscanearActivity.this, Manifest.permission.CAMERA);

        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            permisoCamaraConcedido = true;
        } else {
            // Si no, pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(EscanearActivity.this, new String[]{Manifest.permission.CAMERA}, CODIGO_PERMISOS_CAMARA);
        }
    }

    private void permisoDeCamaraDenegado() {
        // Esto se llama cuando el usuario hace click en "Denegar" o
        // cuando lo denegó anteriormente
        Toast.makeText(EscanearActivity.this, "No puedes escanear si no das permiso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Escanear directamten solo si fue pedido desde el botón
                    if (permisoSolicitadoDesdeBoton) {
                        // escanear();
                    }
                    permisoCamaraConcedido = true;
                } else {
                    permisoDeCamaraDenegado();
                }
                break;
        }
    }
}