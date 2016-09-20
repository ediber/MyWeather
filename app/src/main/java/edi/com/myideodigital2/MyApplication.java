package edi.com.myideodigital2;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edi on 7/12/2016.
 */
public class MyApplication extends Application {
    private NetworkManager networkManager;
    //    private ArrayList<String> paramsList;
    ArrayList<Map<String, String>> paramsList;

    @Override
    public void onCreate() {
        super.onCreate();

        networkManager = new NetworkManager(getApplicationContext());
        initializeParamsList();
    }


    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public ArrayList<Map<String, String>> getParamsList() {
        return paramsList;
    }

    public ArrayList<String> getListOfLocationNames() {
        ArrayList<String> keys = new ArrayList<>();


        for (Map<String, String> param : paramsList) {
            String value = param.entrySet().iterator().next().getValue(); // getting first value
            if(! isDouble(value)){ // if value is a name, not a coordinate
                keys.add(value);
            }
        }

        return keys;
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void initializeParamsList() {
        paramsList = new ArrayList<>();
        Map<String, String> map;

        map = new HashMap<String, String>();
        map.put("q", "London");
        paramsList.add(map);

        map = new HashMap<String, String>();
        map.put("q", "TelAviv");
        paramsList.add(map);

        map = new HashMap<String, String>();
        map.put("q", "Amsterdam");
        paramsList.add(map);
    }

    public void addParams(String lat, String lon) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("lat", lat);
        map.put("lon", lon);
        paramsList.add(map);
    }
}
