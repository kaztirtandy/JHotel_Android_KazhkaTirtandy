package id.ac.ui.jhotel_android_kazhka;

import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.Response;

public class MenuRequest extends StringRequest {
    private static final String Menu_URL = "http://10.0.3.2:8080/vacantrooms";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, Menu_URL, listener, null);
        /*params = new HashMap<>();
        params.get(vacantroom);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }*/
    }
}
