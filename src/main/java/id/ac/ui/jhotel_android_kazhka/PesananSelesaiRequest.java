package id.ac.ui.jhotel_android_kazhka;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String Selesai_URL = "http://10.0.3.2:8080/finishpesanan";
    private Map<String, String> params;

    public PesananSelesaiRequest(int id_pesanan, Response.Listener<String> listener) {
        super(Method.POST, Selesai_URL, listener, null);
        params = new HashMap<>();
        params.put("id_pesanan", String.valueOf(id_pesanan));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
