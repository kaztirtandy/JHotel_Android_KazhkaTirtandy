package id.ac.ui.jhotel_android_kazhkatirtandy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailinput = (EditText) findViewById(R.id.email);
        final EditText passinput = (EditText) findViewById(R.id.password);
        final Button loginbutton = (Button) findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailinput.getText().toString();
                String password = passinput.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse!=null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Success")
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login Failed")
                                    .create()
                                    .show();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }



            });


        final TextView registertext = (TextView) findViewById(R.id.regeistertext);
        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regisInt);
            }
        });
        }

        }

