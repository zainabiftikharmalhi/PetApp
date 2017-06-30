package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.id.list;

/**
 * Created by zainabiftikharmalhi on 2017-06-18.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    private static final String LOCATION_SEPARATOR = " of ";

    private List<String> mList;

    public CustomAdapter(Context context, int layoutResourceId, List<String> list) {
        super(context, layoutResourceId, list);
        mList = list;

    }

    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public String getItem(int pos) {
        return mList.get(pos);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.simpletextview, parent, false);
        }

        String currentitem = getItem(position);

        //Setting the Pet Name
        TextView Name_TextView = (TextView) listItemView.findViewById(R.id.Simple);
        Name_TextView.setText(currentitem);

        return listItemView;
    }


}
