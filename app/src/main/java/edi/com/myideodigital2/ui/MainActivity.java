package edi.com.myideodigital2.ui;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.Map;

import edi.com.myideodigital2.MyApplication;
import edi.com.myideodigital2.NetworkManager;
import edi.com.myideodigital2.R;


public class MainActivity extends AppCompatActivity {

//    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=ddb3d423762b362b995b1e509dbc774f";
    private FrameLayout frame;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private LocationsFragment locatinsFragment;
    private MyApplication application;
    private ExtendedDataFragment extendedDataFragment;
    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = (FrameLayout) findViewById(R.id.frame);

        application = (MyApplication) (getApplication());

        sendAllRequest(); // to cash them later

        locatinsFragment = LocationsFragment.newInstance();
        locatinsFragment.setListener(new LocationsFragment.LocationsListener() {
            @Override
            public void urlSelected(int position) {
                startExtendedDataFragment(position);
                frameFadeIn();

            }
        });

        fragmentManager = getSupportFragmentManager();
        startLocationsFragment();


    }

    private void frameFadeIn() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(frame, "alpha", 0, 1).setDuration(1000);
        alpha.start();
    }

    private void startLocationsFragment() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, locatinsFragment);
        transaction.commit();
    }



    private void startExtendedDataFragment(int position) {

        extendedDataFragment = ExtendedDataFragment.newInstance(position);
        extendedDataFragment.setListener(new ExtendedDataFragment.ExtendedListener() {
            @Override
            public void back() {
                frameFadeIn();
                startLocationsFragment();
            }
        });
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, extendedDataFragment);
        transaction.commit();
    }

    private void sendAllRequest() {
        networkManager = application.getNetworkManager();
        for (Map<String, String> params : application.getParamsList()) {
            networkManager.send(params, null); // no callback needed
        }
    }


}








