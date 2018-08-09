package com.bikcrum.locationupdatesdemo;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bikcrum.locationupdate.LocationUpdateActivity;


public class MainActivity extends LocationUpdateActivity {

    protected static final String TAG = "MainActivity";

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // UI Widgets.
    protected Button mStartUpdatesButton;
    protected Button mStopUpdatesButton;
    protected TextView mLastUpdateTimeTextView;
    protected TextView mLatitudeTextView;
    protected TextView mLongitudeTextView;

    // Labels.
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected String mLastUpdateTimeLabel;

    /**
     * Time when the location was updated represented as a String.
     */
    protected String mLastUpdateTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLocationUpdateIntervalInMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS);
        setLocationFastestUpdateIntervalInMilliseconds(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        // Locate the UI widgets.
        mStartUpdatesButton = findViewById(R.id.start_updates_button);
        mStopUpdatesButton = findViewById(R.id.stop_updates_button);
        mLatitudeTextView = findViewById(R.id.latitude_text);
        mLongitudeTextView = findViewById(R.id.longitude_text);
        mLastUpdateTimeTextView = findViewById(R.id.last_update_time_text);

        // Set labels.
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mLastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);

        mLastUpdateTime = "";

        mStartUpdatesButton.setOnClickListener(view -> {
            startLocationUpdates();
            mStartUpdatesButton.setEnabled(false);
            mStopUpdatesButton.setEnabled(true);
        });

        mStopUpdatesButton.setOnClickListener(view -> {
            stopLocationUpdates();
            mStartUpdatesButton.setEnabled(true);
            mStopUpdatesButton.setEnabled(false);
        });
    }

    @Override
    protected void onLocationUpdated(Location mCurrentLocation, String mLastUpdateTime) {
        Log.v(TAG, "onLocationUpdated is called");
        updateLocationUI(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), mLastUpdateTime);
    }

    /**
     * Sets the value of the UI fields for the location latitude, longitude and last update time.
     */
    private void updateLocationUI(double latitude, double longitude, String time) {
        Log.v(TAG, "latitude = " + latitude + " , longitude = " + longitude + " , time = " + time);
        mLatitudeTextView.setText(getString(R.string.latitude_label, latitude));
        mLongitudeTextView.setText(getString(R.string.longitude_label, longitude));
        mLastUpdateTimeTextView.setText(getString(R.string.last_update_time_label, time));
    }
}
