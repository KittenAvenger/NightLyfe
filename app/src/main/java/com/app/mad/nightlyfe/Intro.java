package com.app.mad.nightlyfe;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Intro extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> listItems=new ArrayList<String>();
    Button searchButton;
    EditText searchField;
    ListView venueList;

    MyArrayAdapter mAdapter = null;
    ArrayList<Venues> venues = null;
    VenueDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

        searchField = (EditText) findViewById(R.id.searchField);

        venueList = (ListView) findViewById(R.id.venueList);

        venues = new ArrayList<Venues>();


        mAdapter = new MyArrayAdapter(this, venues);

        // Set the ListView's adapter to be the adapter that we just constructed.
        venueList.setAdapter((ListAdapter) mAdapter);

        db = new VenueDB(this);


        db.dropTable();

     //   VenueDB db2 = new VenueDB(this);

        db.createTable();

        db.insertVenue("Sticky Fingers", "Rock", "21.00-04.00", 60, "Kaserntorget 7", "medium crowded", "https://open.spotify.com/user/spotify/playlist/0lbtgFu3JNKX77J5YOpW7n");
        db.insertVenue("Pustervik", "Indie","17.00-03.00", 120, "Järntorgsgatan 12", "highly crowded", "https://open.spotify.com/user/spotify/playlist/4dJHrPYVdKgaCE3Lxrv1MZ" );
        db.insertVenue("Sticky Fingers", "Indie", "21.00-04.00", 60, "Kaserntorget 7", "medium crowded", "https://open.spotify.com/user/spotify/playlist/0lbtgFu3JNKX77J5YOpW7n");
        db.insertVenue("Nefertiti", "Jazz", "17.00-01.00", 100, "Hvitfeldtsplatsen 6", "lightly crowded", "https://open.spotify.com/user/guardianmusic/playlist/35i05dxUnfnU0ulnimZh3V");




      //  TextView venueName = (TextView) findViewById(R.id.venueName);
      //  venueName.setText(venue);
      /*  int resID = getResources().getIdentifier(venue, "drawable", getPackageName());
        ImageView venueImage = (ImageView) findViewById(R.id.venueImage);
        venueImage.setImageResource(resID);
       */
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.searchButton:
                mAdapter.clear();
                mAdapter.notifyDataSetChanged();
                String messString = searchField.getText().toString();

                // If the message is not empty string
                if (!messString.equals("")) {

                    Cursor rs = getVenues(messString);

                    if(rs.getCount() <= 0){
                        venues.add(new Venues("Sorry no matches found", null, null));
                    }

                    else {
                        while (rs.isAfterLast() == false) {
                            venues.add(createVenue(rs));
                            rs.moveToNext();
                        }
                    }
                ArrayList<Venues> vens = closeVenues();
                    int size = closeVenues().size();

                    for(int i = 0; i < size; i++){
                        Log.d("Intro", vens.get(i).getAddress() + " " + vens.get(i).getGenre() + " " + vens.get(i).getVenueName());
                    }


                    // Notify the adapter that the data has changed due to the addition
                    // of a new message object. This triggers an update of the ListView
                    mAdapter.notifyDataSetChanged();

                    searchField.setText("");

                 //   venueList.setAdapter((ListAdapter) mAdapter);

                }

                else{
                    venues.add(new Venues("Please type in a genre", null, null));
                    mAdapter.notifyDataSetChanged();
                    searchField.setText("");
                }


                break;
            default:
                break;
        }
    }

    public Cursor  getVenues(String genre){
        Cursor rs = db.getData(genre.substring(0, 1).toUpperCase() + genre.substring(1).toLowerCase());
        if(rs.getCount() > 0){
            rs.moveToFirst();
            Log.d("Intro", rs.toString());
            return rs;
        }
            Log.d("Intro", "Couldn't find genre");
            return rs;
    }

    public Venues createVenue(Cursor rs){
        String venue = rs.getString(rs.getColumnIndex(VenueDB.VENUES_COLUMN_VENUE_NAME));
        String genre = rs.getString(rs.getColumnIndex(VenueDB.VENUES_COLUMN_GENRE));
        String address = rs.getString(rs.getColumnIndex(VenueDB.VENUES_COLUMN_ADDRESS));

        return new Venues(venue, genre, address);
    }

    public ArrayList<Venues> closeVenues(){
        ArrayList<Venues> vens = new ArrayList<Venues>();
        Cursor rs = db.getCloseLocations();
        rs.moveToFirst();
        while (rs.isAfterLast() == false) {
            vens.add(createVenue(rs));
            rs.moveToNext();
        }

        return vens;
    }

}
