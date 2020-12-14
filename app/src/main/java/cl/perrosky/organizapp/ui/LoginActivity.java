package cl.perrosky.organizapp.ui;

import android.app.Activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.StringRes;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cl.perrosky.organizapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


/*
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });
 */

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                consultarRegistro();
            }
        });
    }


    private void consultarRegistro(){
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

                                Log.i("RETORNO", "ID:" + id);
                                Log.i("RETORNO", "CORREO:" + correo);
                                Log.i("RETORNO", "NOMBRE:" + nombre);
                                Log.i("RETORNO", "APELLIDO:" + apellido);
                                Log.i("RETORNO", "PERFIL:" + perfil);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley", "Error: " + error.getMessage());
                        error.printStackTrace();
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
                    params.put("usuario", "hector@organizapp.cl");
                    params.put("password", "P4$$w0rd2");
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

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}