package id.ac.ui.jhotel_android_kazhkatirtandy;

import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.Response;

public class MenuRequest extends StringRequest {
    private static final String Vacant_URL = "http://10.0.2.2/vacantrooms";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, Vacant_URL, listener, null);
        /*params = new HashMap<>();
        params.get(vacantroom);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }*/
    }
}
