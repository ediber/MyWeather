package edi.com.myideodigital2;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by Edi on 7/12/2016.
 */
public class NetworkManager {

    private static final String PATH = "http://api.openweathermap.org/data/2.5/weather?APPID=ddb3d423762b362b995b1e509dbc774f";

    public interface NetworkListener {
        public void onResponse(Data data);
    }

    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
    }

    public void send(Map<String, String> params, final NetworkListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = generateUrlFromParams(params);

/*        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (listener != null) {
                            Gson gson = new Gson();
                            Data data = gson.fromJson(response, Data.class);
                            listener.onResponse(data);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });*/

        MyGsonRequest gsonRequest = new MyGsonRequest(Request.Method.GET, url, Data.class, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                if (listener != null) {
                    Data data = (Data) response;
                    listener.onResponse(data);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


// Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }

    private String generateUrlFromParams(Map<String, String> params) {
        String url = PATH;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            url = url + "&" + key + "=" + value;
        }
        return url;
    }


}
