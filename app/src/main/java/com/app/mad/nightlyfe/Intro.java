package com.app.mad.nightlyfe;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        VenueDB db = new VenueDB(this);

        db.insertVenue("Sticky Fingers", "Rock", "17.00-03.00", 60, "Grönsakstorget", "medium crowded");

        Cursor rs = db.getData(1);
        rs.moveToFirst();
        String venue = rs.getString(rs.getColumnIndex(VenueDB.VENUES_COLUMN_VENUE_NAME));

       TextView venueName = (TextView) findViewById(R.id.venueName);
        venueName.setText(venue);

    }

}
