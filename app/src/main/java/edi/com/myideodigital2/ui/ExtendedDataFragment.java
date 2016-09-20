package edi.com.myideodigital2.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edi.com.myideodigital2.Data;
import edi.com.myideodigital2.MyApplication;
import edi.com.myideodigital2.NetworkManager;
import edi.com.myideodigital2.R;


public class ExtendedDataFragment extends Fragment {

    public interface ExtendedListener {
        public void back();
    }

    private static final String POSITION = "position";

    private ExtendedListener listener;

    private int position;
    private MyApplication application;
    private NetworkManager manager;

    private TextView name;
    private TextView temp;
    private TextView pressure;
    private TextView humidity;
    private TextView tempMin;
    private TextView tempMax;

    private View fab;


    public ExtendedDataFragment() {
        // Required empty public constructor
    }


    public static ExtendedDataFragment newInstance(int position) {
        ExtendedDataFragment fragment = new ExtendedDataFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extended_data, container, false);

        name = (TextView)view.findViewById(R.id.name);
        temp = (TextView)view.findViewById(R.id.temp);
        pressure = (TextView)view.findViewById(R.id.pressure);
        humidity = (TextView)view.findViewById(R.id.humidity);
        tempMin = (TextView)view.findViewById(R.id.temp_min);
        tempMax = (TextView)view.findViewById(R.id.temp_max);

        fab = view.findViewById(R.id.fab);

        application = (MyApplication)(getActivity().getApplication());
        manager = application.getNetworkManager();

        manager.send(application.getParamsList().get(position), new NetworkManager.NetworkListener() {
            @Override
            public void onResponse(Data data) {
                name.setText(data.getName());
                Data.MainEntity main = data.getMain();
                temp.setText(main.getTemp() + "");
                pressure.setText(main.getPressure() + "");
                humidity.setText(main.getHumidity() + "");
                tempMin.setText(main.getTemp_min() + "");
                tempMax.setText(main.getTemp_max() + "");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.back();
            }
        });

        return view;
    }

    public void setListener(ExtendedListener listener) {
        this.listener = listener;
    }
}
