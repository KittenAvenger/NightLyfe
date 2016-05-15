package com.app.mad.nightlyfe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dennis on 2016-05-14.
 */
public class MyArrayAdapter extends ArrayAdapter<Venues> {
    private final Context context;
    private final ArrayList<Venues> venues;

    public MyArrayAdapter(Context context, ArrayList<Venues> venues) {
        super(context, R.layout.venues, venues);
        this.context = context;
        this.venues = venues;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View messageView;

        // Get a reference to the LayoutInflater. This helps construct the
        // view from the layout file
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // The messageView is constructed by inflating the message.xml layout file
        messageView = inflater.inflate(R.layout.venues, parent, false);

        // Get the reference to the two TextViews in the message layout and set them
        // to the time and message string respectively
        TextView timeView = (TextView) messageView.findViewById(R.id.venueName);
        timeView.setText(venues.get(position).getVenueName());

     /*   TextView addressField = (TextView) messageView.findViewById(R.id.addressField);
          addressField.setText(venues.get(position).getAddress());
     */

        /*TextView entranceField = (TextView) messageView.findViewById(R.id.entranceField);
        entranceField.setText(venues.get(position).getEntrance_fee());*/


        return messageView;
    }
}
