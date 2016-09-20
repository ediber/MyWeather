package edi.com.myideodigital2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Edi on 7/7/2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    public interface AdapterListener {
        public void onItemClicked(int position);
    }

    private AdapterListener listener;

    //    private List<String> locations = new ArrayList<String>(Arrays.asList("London", "Tel Aviv", "lat:35 lon:130"));
    private List<String> locations;
    private Context context;


    public MyRecyclerAdapter(Context context, List<String> locations, AdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.locations = locations;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);


       /* WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.MATCH_PARENT));
*/

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {

        String location = locations.get(i);

        if (i % 2 == 0) { // even
            customViewHolder.parent.setBackgroundColor(Color.BLUE);
        } else {
            customViewHolder.parent.setBackgroundColor(Color.WHITE);
        }

        customViewHolder.textView.setText(Html.fromHtml(location));
        customViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return locations.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        protected View parent;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.textView);
            this.parent = view.findViewById(R.id.parent);

        }
    }
}
