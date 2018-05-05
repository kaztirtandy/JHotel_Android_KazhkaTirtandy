package id.ac.ui.jhotel_android_kazhkatirtandy;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nameregis = (EditText) findViewById(R.id.nama);
        final EditText emailregis = (EditText) findViewById(R.id.emailreg);
        final EditText passregis = (EditText) findViewById(R.id.passwordreg);
        final Button regisbutton = (Button) findViewById(R.id.buttonreg);

        regisbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailregis.getText().toString();
                String nama = nameregis.getText().toString();
                String pass = passregis.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse!=null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Success")
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Registration Failed")
                                    .create()
                                    .show();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(nama,email,pass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }


        });






    }
}
