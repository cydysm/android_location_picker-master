package com.zeonic.icity.location_picker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.mob.tools.utils.SharePrefrenceHelper;
import com.mukesh.tinydb.TinyDB;


import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    TextView tvLongitude;
    TextView tvLatitude;
    TextView tvRemark;
    //TextView tvAddress;

    List<Location> locationList = new ArrayList<>();
    MyAdapter mMyAdapter;

    public static final String Lat = "Lat";
    public static final String Lng = "Lng";
    public static final String Rmk = "Remark";

    TinyDB tinyDB = new TinyDB(appContext);






    SharedPreferences sharedpreferences;


    private ListView location_listView;
    public LocationClient mLocationClient = null;
    private BDLocation mBdLocation;
    public BDLocationListener myListener = new MyLocationListener(){
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Log.e("tag", "onReceiveLocation");
            super.onReceiveLocation(bdLocation);
            mBdLocation = bdLocation;
            tvLongitude.setText(String.valueOf(mBdLocation.getLongitude()));
            tvLatitude.setText(String.valueOf(mBdLocation.getLatitude()));
            //tvAddress.setText(String.valueOf(mBdLocation.getAddrStr()));
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SDKInitializer.initialize(getApplicationContext());
        tvLongitude = (TextView) findViewById(R.id.TV_longitude);
        tvLatitude = (TextView) findViewById(R.id.TV_latitude);
        tvRemark = (TextView) findViewById(R.id.remark);

        location_listView = (ListView) findViewById(R.id.location_listView);


        mMyAdapter = new MyAdapter(locationList, this);
        //tvAddress = (TextView)findViewById(R.id.Text_address);
        initLocation();
        mLocationClient.start();
        location_listView.setAdapter(mMyAdapter);
        tinyDB.putListString("");
        tinyDB.getString()


    }




    /*private void saveMyLocation(List<Location> list){

        sharedpreferences = getSharedPreferences("Mylocation", MODE_WORLD_READABLE);

        *//*String lat = tvLatitude.getText().toString();
        String lng = tvLongitude.getText().toString();
        String rmk = tvRemark.getText().toString();*//*

        SharedPreferences.Editor editor = sharedpreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor.commit();*/


/*        editor.putString(Lat,lat);
        editor.putString(Lng,lng);
        editor.putString(Rmk,rmk);
        editor.commit();
    }*/

   // private List<Location> getMyLocation(){
//        sharedpreferences = getSharedPreferences("Mylocation",MODE_WORLD_READABLE);
//        list.clear();
//        int size = sharedpreferences.getInt("Mylocation",0);
//        for(int i =0;i < size;i++){
//            list.add(sharedpreferences.getString(String.valueOf(i),null));
//        }
        /*String lat = sharedpreferences.getString(Lat,null);
        String lng = sharedpreferences.getString(Lng,null);
        String rmk = sharedpreferences.getString(Rmk,null);*/

    //}

    //getLocation by button
    /*   public void getLocation(View view) {
        //Log.e("tag", "getLocation");
        if (mBdLocation != null) {
            tvLongitude.setText(String.valueOf(mBdLocation.getLongitude()));
            tvLatitude.setText(String.valueOf(mBdLocation.getLatitude()));
          //tvAddress.setText(String.valueOf(mBdLocation.getAddrStr()));
        }
    }*/

    //SharedPreferences




    //upload location
    public void uploadLocation(View view){
        Location location = new Location();
        location.setLatitude(Double.valueOf(tvLatitude.getText().toString()));
        location.setLongitude(Double.valueOf(tvLongitude.getText().toString()));
        location.setRemark(tvRemark.getText().toString());

        locationList.add(location);
        mMyAdapter.notifyDataSetChanged();
        tvRemark.setText("");
    }

    private void initLocation(){
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度 (Hight_Accuracy)/低功耗 (Battery_Saving)/仅设备 (Device_Sensors)

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系 国测局火星坐标 (gcj02)/百度墨卡托 (bd09)/百度经纬度 (bd09ll)

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        //option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        //option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        //option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }



/*    public void onCreate(Bundle savedInstanceState) {

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

/*        {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                tv.setText(String.valueOf(bdLocation.getLatitude()));
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
            */

        };
        //注册监听函数

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // TODO: 5/4/17



        //LocationClient mLocationClient = new LocationClient(getApplicationContext());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });




    //public  TextView tv;


//    TextView tv = (TextView) findViewById(R.id.TV_latitude);




/*    public void showLocation(View sL){
        TextView tvGet = (TextView)findViewById(R.id.btn_get);
        String tvGetString = tvGet.getText().toString();
    }

    public void uploadLocation(View uL){

    }


   // @Override
/*    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // TODO: 5/4/17

        tv = (TextView) findViewById(R.id.TV_latitude);
        LocationClient mLocationClient = new LocationClient(getApplicationContext());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDLocationListener()
        {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                tv.setText(String.valueOf(bdLocation.getLatitude()));
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        });
        //注册监听函数
    }
*/


/*    public void getLocation (View v){
        mLocationClient.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/