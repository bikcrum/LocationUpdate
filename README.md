# LocationUpdate Library
This library contains activity everything ready for getting location updates.

# How to use

### Add root build.gradle (Project)
```
allprojects {
    repositories {
        ...
        // add this line
        maven { url "https://jitpack.io" }
    }
}
```

### Add build.gradle (Module)
```
dependencies {
    ...
    // Also add this line
    implementation 'com.github.bikcrum:LocationUpdate:v2.0.0'
}
```

### To use it in your code

#### Very important step
Your Activity should implement **LocationUpdate.OnLocationUpdatedListener** otherwise you app with crash.

```
public class MainActivity implements LocationUpdate.OnLocationUpdatedListener  {

    // Create LocationUpdate object
    private LocationUpdate locationUpdate;
    
    // The desired interval for location updates. Inexact. Updates may be more or less frequent.
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    
    // The fastest rate for active location updates. Exact. Updates will never be more frequent than this value.
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //create instance
        locationUpdate = LocationUpdate.getInstance(this);
        
        //don't forget to call this
        locationUpdate.onCreate(savedInstanceState);
        
        //this is optional
        locationUpdate.setLocationUpdateIntervalInMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationUpdate.setLocationFastestUpdateIntervalInMilliseconds(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //don't forget to call this
        locationUpdate.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //don't forget to call this
        locationUpdate.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        //don't forget to call this
        locationUpdate.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLocationUpdated(Location mCurrentLocation, String mLastUpdateTime) {
       Log.v(TAG, "onLocationUpdated is called");
       long lat = mCurrentLocation.getLatitude();
       long lng = mCurrentLocation.getLongitude();
       //do whatever you want to do with latitude and longitude
    }
    
    ...
}
```
