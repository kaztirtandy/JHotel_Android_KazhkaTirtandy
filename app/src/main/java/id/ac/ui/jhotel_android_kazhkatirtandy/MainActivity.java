package id.ac.ui.jhotel_android_kazhkatirtandy;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView expandable = findViewById(R.id.expandable);

        refreshList();

        MenuListAdapter listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
        expandable.setAdapter(listAdapter);

    }

    public void refreshList() {

        Response.Listener<String> responseListener  = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    JSONObject e = jsonResponse.getJSONObject(0).getJSONObject("hotel");
                    JSONObject lokasi = e.getJSONObject("lokasi");

                    listHotel.add(new Hotel(e.getString("nama"), new Lokasi(lokasi.getInt("x_coord"), lokasi.getInt("y_coord"), lokasi.getString("deskripsiLokasi")), e.getInt("4")));

                    for(int i = 0;i<=3;i++) {
                        JSONObject room = jsonResponse.getJSONObject(i);
                        listRoom.add(new Room(room.getString("tipeKamar"), room.getString("roomNumber"), room.getDouble("dailyTariff"), room.getString("statusKamar")));
                    }

                    childMapping.put(listHotel.get(0), listRoom);
                }catch (JSONException je) {

                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);

    }
}
