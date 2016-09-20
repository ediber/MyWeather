package edi.com.myideodigital2.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edi.com.myideodigital2.MyApplication;
import edi.com.myideodigital2.MyRecyclerAdapter;
import edi.com.myideodigital2.R;


public class LocationsFragment extends Fragment {


    private View fab;

    public interface LocationsListener {
        public void urlSelected(int position);
    }

    private LocationsListener listener;
    private MyApplication application;
    private View customLocation;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyRecyclerAdapter adapter;

    private View customLayout;
    private EditText lat;
    private EditText lon;
    private View ok;

    public LocationsFragment() {
        // Required empty public constructor
    }

    public static LocationsFragment newInstance() {
        LocationsFragment fragment = new LocationsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locations, container, false);

        application = (MyApplication) (getActivity().getApplication());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        customLocation = view.findViewById(R.id.customLocation);
        customLayout = view.findViewById(R.id.customLayout);
        lat = (EditText) view.findViewById(R.id.lat);
        lon = (EditText) view.findViewById(R.id.lon);
        ok = view.findViewById(R.id.ok);
        fab = view.findViewById(R.id.fab);


        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyRecyclerAdapter(getActivity(), application.getListOfLocationNames(), new MyRecyclerAdapter.AdapterListener() {
            @Override
            public void onItemClicked(int position) {
                listener.urlSelected(position);
            }
        });
        mRecyclerView.setAdapter(adapter);

        customLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateCustomViews();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.addParams(lat.getText().toString(), lon.getText().toString());
                listener.urlSelected(application.getParamsList().size() - 1); // chhose last added location
            }
        });


        return view;
    }

    private void animateCustomViews() {
        customLayout.setVisibility(View.VISIBLE);

        ObjectAnimator latX = ObjectAnimator.ofFloat(lat, "translationX", getResources().getDimension(R.dimen.animate_horizontal), 0);
        ObjectAnimator latY = ObjectAnimator.ofFloat(lat, "translationY", 500, 0);

        ObjectAnimator lonY = ObjectAnimator.ofFloat(lon, "translationY", 500, 0);

        ObjectAnimator okX = ObjectAnimator.ofFloat(ok, "translationX", -1 * getResources().getDimension(R.dimen.animate_horizontal), 0);
        ObjectAnimator okY = ObjectAnimator.ofFloat(ok, "translationY", 500, 0);

        AnimatorSet set = new AnimatorSet();
        set.play(latX).with(latY).with(lonY).with(okX).with(okY);
        set.setDuration(500);
        set.start();
    }

    public void setListener(LocationsListener listener) {
        this.listener = listener;
    }
}
