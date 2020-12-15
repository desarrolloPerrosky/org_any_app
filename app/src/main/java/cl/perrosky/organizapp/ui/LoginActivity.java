package cl.perrosky.organizapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cl.perrosky.organizapp.R;
import cl.perrosky.organizapp.bbdd.impl.LoginDataSource;
import cl.perrosky.organizapp.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        // FIXME
        usernameEditText.setText("hector@organizapp.cl");
        passwordEditText.setText("P4$$w0rd");
        // FIXME

        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloqueoLogin();
                consultarRegistro();
            }
        });
    }

    @Override
    public void onBackPressed()  {
        finish();
    }

    private void consultarRegistro() {
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        String apiLink = "http://www.perrosky.cl/restapi/v1/organizapp/ingresar";

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST, apiLink,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s;
                        s = response.toString().trim();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(s);
                        } catch (Throwable t) {}

                        try {
                            String mensaje = obj.getString("message");
                            boolean error = obj.getBoolean("error");
                            Log.i("RETORNO", "MENSAJE:" + mensaje);

                            if(!error) {
                                String id = (obj.getJSONObject("data")).getString("id");
                                String correo = (obj.getJSONObject("data")).getString("correo");
                                String nombre = (obj.getJSONObject("data")).getString("nombre");
                                String apellido = (obj.getJSONObject("data")).getString("apellido");
                                String perfil = (obj.getJSONObject("data")).getString("perfil");

                                Usuario usuario = new Usuario();

                                usuario.setId(Integer.valueOf(id));
                                usuario.setCorreo(correo);
                                usuario.setNombre(nombre);
                                usuario.setApellido(apellido);
                                usuario.setPerfil(perfil);

                                Log.i("RETORNO", "ID:" + id);
                                Log.i("RETORNO", "CORREO:" + correo);
                                Log.i("RETORNO", "NOMBRE:" + nombre);
                                Log.i("RETORNO", "APELLIDO:" + apellido);
                                Log.i("RETORNO", "PERFIL:" + perfil);

                                (new LoginDataSource(LoginActivity.this)).iniciarSesion(usuario);

                                String welcome = getString(R.string.welcome) + usuario.getNombre() + " " + usuario.getApellido();
                                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("LOGIN", usuario);
                                startActivity(intent);

                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        enableLogin();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String retorno = "Error indeterminado";
                        switch (error.networkResponse.statusCode){
                            case 401:
                                retorno = "Error en las credenciales";
                                break;
                        }
                        Log.d("Volley", "Error: " + error.getMessage());
                        error.printStackTrace();
                        Log.d("Volley 234", "Error: " + error.networkResponse.statusCode);

                        Toast.makeText(getApplicationContext(), retorno, Toast.LENGTH_SHORT).show();
                        enableLogin();
                    }

            }) {
                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=UTF-8";
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String parsed;
                    try {
                        parsed = new String(response.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        parsed = new String(response.data);
                    }
                    return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usuario", usernameEditText.getText().toString());
                    params.put("password", passwordEditText.getText().toString());
                    return params;
                }
            };

            requestQueue.add(jsonObjRequest);
    }

    private void updateUiWithUser() {
        String welcome = getString(R.string.welcome); // + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void bloqueoLogin(){
        usernameEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        loginButton.setEnabled(false);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void enableLogin(){
        usernameEditText.setEnabled(true);
        passwordEditText.setEnabled(true);
        loginButton.setEnabled(true);
        loadingProgressBar.setVisibility(View.GONE);
    }
}