package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zainabiftikharmalhi on 2017-05-16.
 */

public class PetAdapter extends ArrayAdapter<Pet> {
    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";


    public PetAdapter(Context context, ArrayList<Pet> pets) {
        super(context, 0, pets);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_row, parent, false);
        }

        Pet currentpet = getItem(position);

        //Setting the Pet Name
        TextView Name_TextView = (TextView) listItemView.findViewById(R.id.Name);
        Name_TextView.setText(currentpet.getName());




        return listItemView;
    }

}
