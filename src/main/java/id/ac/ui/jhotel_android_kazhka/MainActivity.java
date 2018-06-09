package id.ac.ui.jhotel_android_kazhka;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();
    private int currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshList();
        final ExpandableListView expandable = findViewById(R.id.expandable);
        final MenuListAdapter listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
        expandable.setAdapter(listAdapter);
        currentUserId = getIntent().getIntExtra("ID customer",0);
        expandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Room selected = childMapping.get(listHotel.get(groupPosition)).get(childPosition);
                Hotel hotel = listHotel.get(groupPosition);
                String nomor_kamar = selected.getRoomNumber();
                Intent childIntent = new Intent(MainActivity.this,BuatPesananActivity.class);
                childIntent.putExtra("ID customer", currentUserId);
                childIntent.putExtra("room number", nomor_kamar);
                childIntent.putExtra("ID hotel",hotel.getId());
                startActivity(childIntent);
                return true;
            }
        });

        Button pesanantmbl = (Button) findViewById(R.id.pesanan);
        pesanantmbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selesaiintent = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                selesaiintent.putExtra("ID customer", currentUserId);
                startActivity(selesaiintent);
            }
        });



    }

    public void refreshList() {

        Response.Listener<String> responseListener  = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        JSONObject e = jsonResponse.getJSONObject(0).getJSONObject("hotel");
                        JSONObject lokasi = e.getJSONObject("lokasi");

                        listHotel.add(new Hotel(e.getInt("id"), e.getString("nama"), new Lokasi(lokasi.getInt("x_coord"), lokasi.getInt("y_coord"), lokasi.getString("deskripsiLokasi")), e.getInt("bintang")));

                        for (int i = 0; i <= 3; i++) {
                            JSONObject room = jsonResponse.getJSONObject(i);
                            listRoom.add(new Room(room.getString("tipeKamar"), room.getString("roomNumber"), room.getDouble("dailyTariff"), room.getString("statusKamar")));
                        }
                        childMapping.put(listHotel.get(0), listRoom);
                    }
                }
                catch (JSONException je) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Failed to load.")
                            .create()
                            .show();

                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);

    }
}
