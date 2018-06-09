package id.ac.ui.jhotel_android_kazhka;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SelesaiPesananActivity extends AppCompatActivity {
    private int id_customer;
    private int id_pesan_fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);
        id_customer = getIntent().getIntExtra("ID Customer", 1);
        TextView idpesanstatic = (TextView) findViewById(R.id.staticIdPesanan);
        TextView idpesan = (TextView) findViewById(R.id.id_pesanan);
        TextView biayastatic = (TextView) findViewById(R.id.staticBiaya);
        TextView biaya = (TextView) findViewById(R.id.biaya);
        TextView jumlahharistatic = (TextView) findViewById(R.id.staticJumlahHari);
        TextView jumlahhari = (TextView) findViewById(R.id.jumlah_hari);
        TextView tanggalpesanstatic = (TextView) findViewById(R.id.staticTanggalPesan);
        TextView tanggalpesan = (TextView) findViewById(R.id.tanggal_pesan);
        Button bataltmbl = (Button) findViewById(R.id.batal);
        Button selesaitmbl = (Button) findViewById(R.id.selesai);

        fetchPesanan();

        bataltmbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Cancel success.")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Failed to cancel.")
                                    .create()
                                    .show();
                        }
                    }
                };
                PesananBatalRequest batalRequest = new PesananBatalRequest(id_pesan_fetch, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);
            }
        });
        selesaitmbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Finish success.")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Failed to finish.")
                                    .create()
                                    .show();
                        }
                    }
                };
                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(id_pesan_fetch, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);
            }
        });

    }

    public void fetchPesanan() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse != null) {
                        id_pesan_fetch = jsonResponse.getInt("id");
                        double biaya_fetch = jsonResponse.getDouble("biaya");
                        int hari_fetch = jsonResponse.getInt("jumlahHari");
                        String tanggal_fetch = jsonResponse.getString("tanggalPesan");

                        TextView idpesan = (TextView) findViewById(R.id.id_pesanan);
                        TextView biaya = (TextView) findViewById(R.id.biaya);
                        TextView jumlahhari = (TextView) findViewById(R.id.jumlah_hari);
                        TextView tanggalpesan = (TextView) findViewById(R.id.tanggal_pesan);

                        idpesan.setText(Integer.toString(id_pesan_fetch));
                        biaya.setText(Double.toString(biaya_fetch));
                        jumlahhari.setText(Integer.toString(hari_fetch));
                        tanggalpesan.setText(tanggal_fetch);

                    } else if (jsonResponse == null) {
                        Intent backIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                        startActivity(backIntent);
                    }
                } catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                    builder.setMessage("Failed to load.")
                            .create()
                            .show();
                }
            }
        };
        PesananFetchRequest fetchRequest = new PesananFetchRequest(id_customer, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchRequest);
    }
}

