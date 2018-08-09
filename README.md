# LocationUpdate Library
This library contains activity everything ready for getting location updates. Only one has to extend LocationManagerActivity class which is defined in this project. One should override onLocationUpdated method after extending that class and get location updates.

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
    compile 'com.github.bikcrum:LocationUpdate:v1.0.1'
}
```

### To use it in your code

#### Very important step
Your Activity should extend **LocationUpdateActivity** as following;

```
public class MainActivity extends LocationUpdateActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //set location update speed
        setLocationUpdateIntervalInMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS);
        setLocationFastestUpdateIntervalInMilliseconds(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        ...
    }
    
    //Override this for location updates
    @Override
    protected void onLocationUpdated(Location mCurrentLocation, String mLastUpdateTime) {
        long lat = mCurrentLocation.getLatitude();
        long lng = mCurrentLocation.getLongitude();
        //do whatever you want to do with latitude and longitude
    }
    
    ...
}
```
