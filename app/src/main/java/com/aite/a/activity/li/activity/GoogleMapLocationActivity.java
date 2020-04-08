package com.aite.a.activity.li.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.util.LogUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.jiananshop.a.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: liziyang
 * @datetime: 2020-02-25
 * @desc: 地图选点
 */
public class GoogleMapLocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnMapLoadedCallback, GoogleMap.OnMapClickListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraMoveCanceledListener, GoogleMap.OnCameraIdleListener {
    private GoogleMap google_location_map;
    private PlacesClient placesClient;
    private LatLng mlatLng;
    private ImageView iv_back;
    private Button sumbit_btn;
    private TextView location_tv_name;
    private Context context;
    private Marker mMarker;
    private boolean isInputKeySearch;
    private boolean isItemClickAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_location_map_activity_layout);
        initEtras();
        applyLocationpermission();
        initViews();


    }

    private void initDatas() {
        Places.initialize(getApplicationContext(), getResources().getString(R.string.google_maps_key));
        // Create a new Places client instance
        placesClient = Places.createClient(this);
    }

    private void initEtras() {
        context = this;
        iv_back = findViewById(R.id.iv_back);
        sumbit_btn = findViewById(R.id.sumbit_btn);
        location_tv_name = findViewById(R.id.location_tv_name);
        iv_back.setOnClickListener(v -> onBackPressed());
        sumbit_btn.setOnClickListener(v -> {
            Intent intent = getIntent();
            if (mlatLng != null) {
                intent.putExtra("LocationName", location_tv_name.getText().toString());
                intent.putExtra("Latitude", String.valueOf(mlatLng.latitude));
                intent.putExtra("Longitude", String.valueOf(mlatLng.longitude));
                // 设置返回码和返回携带的数据
                setResult(Activity.RESULT_OK, intent);
                onBackPressed();
            }

        });

    }

    /**
     * 搜索
     */
    private void setupAutocompleteSupportFragment() {
        final AutocompleteSupportFragment autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.autocomplete_support_fragment);
        //设置搜索的接受的参数 必须写
        List<Place.Field> fields = new ArrayList<>();
        fields.add(Place.Field.ADDRESS);
        fields.add(Place.Field.NAME);
        fields.add(Place.Field.LAT_LNG);
        fields.add(Place.Field.WEBSITE_URI);
        autocompleteSupportFragment.setPlaceFields(fields);
        autocompleteSupportFragment.setCountry("KH");//柬埔寨 的地理代码  CN 是中国大陆
        //设置搜索的回调
        autocompleteSupportFragment.setOnPlaceSelectedListener(getPlaceSelectionListener());
    }

    private PlaceSelectionListener getPlaceSelectionListener() {
        return new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                LogUtils.d(place.getAddress());
                if (place.getLatLng() != null) {
                    moveAmap(place.getLatLng(), geoAddress(place.getLatLng()));
                    isInputKeySearch = true;

                }

            }

            @Override
            public void onError(@NonNull Status status) {
                LogUtils.e(status.getStatusMessage());
            }
        };
    }

    /**
     * 获取系统定位
     * 附近的地点
     */
    @SuppressLint({"DefaultLocale", "MissingPermission"})
    private void initLocation() {
        setupAutocompleteSupportFragment();
        initDatas();
        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Collections.singletonList(Place.Field.ADDRESS);
        // Use the builder to create a FindCurrentPlaceRequest.
        FindCurrentPlaceRequest request =
                FindCurrentPlaceRequest.newInstance(placeFields);
        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
        placeResponse.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FindCurrentPlaceResponse response = task.getResult();
                for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                    LogUtils.i(String.format("Place '%s' has likelihood: %f",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));
                }
            } else {
                Exception exception = task.getException();
                if (exception instanceof ApiException) {
                    ApiException apiException = (ApiException) exception;
                    LogUtils.e("Place not found: " + apiException.getStatusCode());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    protected void applyLocationpermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.setLogging(true);
            rxPermissions
                    .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(permission -> { // will emit 2 Permission objects
                        if (permission.granted) {
                            // `permission.name` is granted !
                            LogUtils.d("ACCESS_COARSE_LOCATION权限同意");
                            initLocation();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("ACCESS_COARSE_LOCATION权限被拒绝");
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("ACCESS_COARSE_LOCATION权限被拒绝");

                        }
                    });
        }
    }

    private void initViews() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_location_map);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
        else Toast.makeText(this, "定位错误", Toast.LENGTH_SHORT).show();
    }

    private void moveAmap(LatLng latLng, String title) {
        if (mMarker != null)
            mMarker.remove();
        if (latLng == null) return;
        if (title == null)
            mMarker = google_location_map.addMarker(new MarkerOptions().
                    position(latLng).
                    title("Marker in Sydney").
                    icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
        else mMarker = google_location_map.addMarker(new MarkerOptions().
                position(latLng).
                title(title).
                icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
        google_location_map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
        mlatLng = latLng;
        location_tv_name.setText(title);
//        google_location_map.moveCamera(CameraUpdateFactory.newLatLng(mlatLng));
//        google_location_map.animateCamera(CameraUpdateFactory.newLatLngZoom(mlatLng, 12.0f));
    }

    @SuppressLint({"DefaultLocale", "MissingPermission"})
    @Override
    public void onMapReady(GoogleMap googleMap) {
        google_location_map = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng sydney = new LatLng(-34, 151);
//        google_location_map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        google_location_map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        google_location_map.setMyLocationEnabled(true);
        google_location_map.setOnMyLocationButtonClickListener(this);
        google_location_map.setOnMyLocationClickListener(this);
        google_location_map.setOnCameraMoveListener(this);
        google_location_map.setOnCameraMoveCanceledListener(this);
        google_location_map.setOnMapLoadedCallback(this);
        google_location_map.setOnMapClickListener(this);
        google_location_map.setOnCameraMoveStartedListener(this);
        google_location_map.setOnCameraIdleListener(this);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert lm != null;
        Location myLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = lm.getBestProvider(criteria, true);
            myLocation = lm.getLastKnownLocation(provider);
        }
        if (myLocation != null) {
            LogUtils.d(myLocation.getLatitude() + "********" + myLocation.getLongitude());
            moveAmap(
                    mlatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude()),
                    geoAddress(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())));
        }

    }

    @Override
    public boolean onMyLocationButtonClick() {
        isItemClickAction = true;
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        LogUtils.e(location.getLatitude() + location.getLongitude());
        moveAmap(new LatLng(location.getLatitude(), location.getLongitude()), geoAddress(new LatLng(location.getLatitude(), location.getLongitude())));
    }


    @Override
    public void onCameraMove() {

    }


    //设置Marker在屏幕上,不跟随地图移动
    @Override
    public void onMapLoaded() {
        LatLng latLng = google_location_map.getCameraPosition().target;
        if (latLng != null) {
            Point screenPosition = google_location_map.getProjection().toScreenLocation(mlatLng = latLng);
            if (mMarker != null) mMarker.remove();
            mMarker = google_location_map.addMarker(new MarkerOptions()
                    .anchor(0.5f, 0.5f)
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
            mMarker.setPosition(google_location_map.getProjection().fromScreenLocation(screenPosition));
            mMarker.setZIndex(1);
        }


    }

    @Override
    public void onMapClick(LatLng latLng) {
        moveAmap(latLng, geoAddress(latLng));


    }

    //移动开始
    @Override
    public void onCameraMoveStarted(int i) {

    }

    @Override
    public void onCameraMoveCanceled() {

    }

    private void startJumpAnimation() {
        LatLng latLng = google_location_map.getCameraPosition().target;
        moveAmap(latLng, geoAddress(latLng));

    }

    public String geoAddress(LatLng latLng) {
        try {
            Geocoder geocoder = new Geocoder(context);
//            Geocoder geocoder = new Geocoder(context, Locale.CHINA);
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 10);
            if (addresses != null&&!addresses.isEmpty()) {
                Address address = addresses.get(0);
                //1市 2区 3详细地址
                String location = address.getLocality() + address.getSubLocality() + address.getThoroughfare();
                LogUtils.d(location);
                return location;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";


    }

    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //移动结束
    @Override
    public void onCameraIdle() {
        if (!isItemClickAction && !isInputKeySearch) {
            startJumpAnimation();
        }
        isInputKeySearch = false;
        isItemClickAction = false;
    }
}
