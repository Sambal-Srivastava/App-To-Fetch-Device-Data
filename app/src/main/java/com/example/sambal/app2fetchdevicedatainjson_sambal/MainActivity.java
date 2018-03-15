package com.example.sambal.app2fetchdevicedatainjson_sambal;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.opengl.GLES11;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity  {

    TextView textView, forAsync , textViewProcessor;

    String advertId = null;
    String carrierName;

    private final int PhoneState_Request_Code = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView2);
        forAsync = findViewById(R.id.forAsync);
        textViewProcessor = findViewById(R.id.textViewProcessor);


        findViewById(R.id.buttonClick).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                ActivityManager actManager = (ActivityManager) MainActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
                ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
                actManager.getMemoryInfo(memInfo);
                long totalMemory = memInfo.totalMem;
                long availableMemory = memInfo.availMem;


                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                String macAddress = wm.getConnectionInfo().getMacAddress();

                if (macAddress == null) {
                    macAddress = "Device don't have mac address or wi-fi is disabled";
                }

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo network = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


                TimeZone tz = TimeZone.getDefault();

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);

                int widthPixels = metrics.widthPixels;
                int heightPixels = metrics.heightPixels;

                float scaleFactor = metrics.density;

                float widthDp = widthPixels / scaleFactor;
                float heightDp = heightPixels / scaleFactor;

                float widthDpi = metrics.xdpi;
                float heightDpi = metrics.ydpi;

                float widthInches = widthPixels / widthDpi;
                float heightInches = heightPixels / heightDpi;

                //a² + b² = c²

//The size of the diagonal in inches is equal to the square root of the height in inches squared plus the width in inches squared.
                double diagonalInches = Math.sqrt(
                        (widthInches * widthInches)
                                + (heightInches * heightInches));


                AsyncTask<Void, Void, String> task = null;
                try {
                    task = new AsyncTask<Void, Void, String>() {


                        @Override
                        protected String doInBackground(Void... params) {
                            AdvertisingIdClient.Info idInfo = null;
                            try {
                                idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                            } catch (GooglePlayServicesNotAvailableException e) {
                                e.printStackTrace();
                            } catch (GooglePlayServicesRepairableException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                advertId = idInfo.getId();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return advertId;
                        }

                        @Override
                        protected void onPostExecute(String advertId) {
                        Toast.makeText(getApplicationContext(), advertId, Toast.LENGTH_SHORT).show();
                        forAsync.setText("Ad_id : " + advertId);

                        }
//
                    };
                    task.execute();
                } catch (Exception e) {

                }


/** Retrieve the Android Advertising Id
 *
 * The device must be KitKat (4.4)+
 * This method must be invoked from a background thread.
 *
 * */


                StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long bytesAvailable;
                if (android.os.Build.VERSION.SDK_INT >=
                        android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
                } else {
                    bytesAvailable = (long) stat.getBlockSizeLong() * (long) stat.getAvailableBlocksLong();
                }
                long gigAvailable = bytesAvailable / (1024 * 1024 * 1024);
                Log.e("", "Available GB : " + gigAvailable);

                long bytesTotal;
                if (android.os.Build.VERSION.SDK_INT >=
                        android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    bytesTotal = stat.getBlockSizeLong() * stat.getBlockCountLong();
                } else {
                    bytesTotal = (long) stat.getBlockSizeLong() * (long) stat.getBlockCountLong();
                }
                long gigTotal = bytesTotal / (1024 * 1024 * 1024);
                Log.e("", "Total GB : " + gigTotal);


                String Imei = null;
                try {


                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    Imei = telephonyManager.getDeviceId();

                    SubscriptionManager subscriptionManager = (SubscriptionManager) getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);

                    List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();

                    if (subscriptionInfoList != null && subscriptionInfoList.size() > 0) {
                        for (SubscriptionInfo info : subscriptionInfoList) {
                            carrierName = info.getCarrierName().toString();
                            String mobileNo = info.getNumber();
                            String countyIso = info.getCountryIso();
                            int dataRoaming = info.getDataRoaming();

                        }

                    }
                } catch (Exception e) {

                }

                String renderer = GLES11.glGetString(GLES11.GL_RENDERER);


                String Kernal = System.getProperty("os.version");
                String locale_Country = getResources().getConfiguration().locale.getCountry();
                String locale_Language = getResources().getConfiguration().locale.getLanguage();
                long Ram_Available = availableMemory;
                long Ram_Total = totalMemory;
                String Os_increment = Build.VERSION.INCREMENTAL;
                String hardware = Build.HARDWARE;
                String device = Build.DEVICE;
                int cpu_core = Runtime.getRuntime().availableProcessors();
                String private_ip = ip;
                String type = Build.TYPE;
                String time_zone = tz.getDisplayName(false, TimeZone.SHORT);
                String time_Zone_id = tz.getID();
                String serial = Build.SERIAL;
                int mnc = getResources().getConfiguration().mnc;
                String android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                String cpu_arch = System.getProperty("os.arch");
                String cpu_abi = Build.CPU_ABI;
                NetworkInfo network_info = network;

                String Display = Build.DISPLAY;
                String UserAgent = System.getProperty("http.agent");
                String os_vrsion = Build.VERSION.RELEASE;
                String build = Build.BOARD;
                int Sdk = Build.VERSION.SDK_INT;
                String product = Build.PRODUCT;
                String brand = Build.BRAND;
                Double screen_size = diagonalInches;
                int mcc = getResources().getConfiguration().mcc;
                String mac = getMacAddr();
                String adid = task.toString();
                String fingerprint = Build.FINGERPRINT;
                String imei = Imei;
                String maufacturer = Build.MANUFACTURER;
                String device_name = Build.DEVICE;
                String mainboard = Build.BOARD;
                String country = getResources().getConfiguration().locale.getISO3Country();
                String local_tz = tz.getDisplayName(false, TimeZone.SHORT);
                String cpu = Build.CPU_ABI;
                String carrier = carrierName;
                String os_codename = Build.BOOTLOADER;
                long internal_memory_available = gigAvailable;
                long internal_memory_total = gigTotal;
                String model = Build.MODEL;
                String local_tz_name = tz.getID();
                int resolution_height = heightPixels;
                int resolution_width = widthPixels;
                int height_dpi = (int) heightDpi;
                int width_dpi = (int) widthDpi;


                Model obj = new Model(Kernal, locale_Country, locale_Language, Os_increment, hardware, device,
                        private_ip, type, time_zone, time_Zone_id, serial, android_id, cpu_arch, cpu_abi,
                        Display, UserAgent, os_vrsion, build, product, brand, screen_size, mac,
                        fingerprint, imei, maufacturer, device_name, mainboard, country, local_tz,
                        cpu, carrier, os_codename, model, local_tz_name, network_info, Ram_Available,
                        Ram_Total, internal_memory_available, internal_memory_total, cpu_core, mnc, Sdk, mcc,
                        resolution_width, resolution_height, height_dpi, width_dpi, adid);

                obj.setKernal(Kernal);
                obj.setLocale_country(locale_Country);
                obj.setLocale_language(locale_Language);
                obj.setOs_incremantal(Os_increment);
                obj.setHardware(hardware);
                obj.setDevice(device);
                obj.setPrivate_ip(private_ip);
                obj.setType(type);
                obj.setTimezone(time_zone);
                obj.setTimezone_id(time_Zone_id);
                obj.setSerial(serial);
                obj.setAndroid_id(android_id);
                obj.setCpu_arch(cpu_arch);
                obj.setCpu_abi(cpu_abi);
                obj.setDisplay(Display);
                obj.setUser_Agent(UserAgent);
                obj.setOs_Version(os_vrsion);
                obj.setBuild(build);
                obj.setProduct(product);
                obj.setBrand(brand);
                obj.setScreen_size(screen_size);
                obj.setMac(mac);
                obj.setFingerprint(fingerprint);
                obj.setImei(imei);
                obj.setManufacturer(maufacturer);
                obj.setDevice(device_name);
                obj.setMainboard(mainboard);
                obj.setCountry(country);
                obj.setLocal_timezone(local_tz);
                obj.setCpu(cpu);
                obj.setCarrier(carrier);
                obj.setOs_codename(os_codename);
                obj.setModel(model);
                obj.setLocal_tz_name(local_tz_name);
                obj.setNetwork_Info(network_info);
                obj.setRam_Available(Ram_Available);
                obj.setRam_Shown(Ram_Total);
                obj.setInternal_memory_available(internal_memory_available);
                obj.setInternal_memory_total(internal_memory_total);
                obj.setCpu_core(cpu_core);
                obj.setMnc(mnc);
                obj.setSdk(Sdk);
                obj.setMcc(mcc);
                obj.setResolution_width_pixels(resolution_width);
                obj.setResolution_height_pixels(resolution_height);
                obj.setDpi_height(height_dpi);
                obj.setDpi_width(width_dpi);
                obj.setAdid(adid);


                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String Json = gson.toJson(obj);

                textView.setText(Json);

                String processor = ReadCPUinfo().toString();
                textViewProcessor.setText(processor);
//


            }
        });
    }




    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }





    private String ReadCPUinfo () {
        {
            ProcessBuilder cmd;
            String result = "";


            try {
                String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
                cmd = new ProcessBuilder(args);

                Process process = cmd.start();
                InputStream in = process.getInputStream();
                byte[] re = new byte[1024];
                while (in.read(re) != -1) {
                    System.out.println(new String(re));
                    result = result + new String(re);
                }
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return result;
        }
    }





    private void askPermission (String permission , int requestCode)
    {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
        {
            //We don't have permission
            ActivityCompat.requestPermissions(this, new String[] {permission}, requestCode);
        }
        else {
            //We have permission
            Toast.makeText(getApplicationContext(), "Permission is Already Granted", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        askPermission(Manifest.permission.READ_PHONE_STATE, PhoneState_Request_Code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED )
        {
            Toast.makeText(getApplicationContext(), "Read Phone State Permission Granted" , Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Read Phone State Permission Denied" , Toast.LENGTH_SHORT).show();

    }
}







