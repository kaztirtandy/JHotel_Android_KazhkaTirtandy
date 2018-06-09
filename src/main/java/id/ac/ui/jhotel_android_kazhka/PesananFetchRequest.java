package id.ac.ui.jhotel_android_kazhka;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class PesananFetchRequest extends StringRequest {
    private static final String Fetch_URL = "http://10.0.3.2:8080/pesanancustomer/";

    public PesananFetchRequest(int id_cust, Response.Listener<String> listener) {
        super(Method.GET, Fetch_URL+id_cust, listener, null);
    }
}
