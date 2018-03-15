package com.example.sambal.app2fetchdevicedatainjson_sambal;

import android.net.NetworkInfo;
import android.os.AsyncTask;

/**
 * Created by Sambal on 2/16/2018.
 */

public class Model {

    private String kernal, locale_country, locale_language, os_incremantal, hardware, device, private_ip, type,
            timezone, timezone_id, serial, android_id, cpu_arch, cpu_abi, display,
            User_Agent, os_Version, build, product, brand, mac, fingerprint, imei, manufacturer, name,
            mainboard, country, local_timezone, cpu, carrier, os_codename, model,
            local_tz_name;

    private NetworkInfo network_Info;
    private Double  screen_size;

    private long ram_Available, ram_Shown, internal_memory_available, internal_memory_total;

    private int cpu_core, mnc, sdk, mcc, resolution_width_pixels, resolution_height_pixels, dpi_height, dpi_width;

    private String adid;


    public Model(String kernal, String locale_country, String locale_language, String os_incremantal, String hardware, String device, String private_ip, String type, String timezone, String timezone_id, String serial, String android_id, String cpu_arch, String cpu_abi, String display, String user_Agent, String os_Version, String build, String product, String brand, Double screen_size, String mac, String fingerprint, String imei, String manufacturer, String name, String mainboard, String country, String local_timezone, String cpu, String carrier, String os_codename, String model, String local_tz_name, NetworkInfo network_Info, long ram_Available, long ram_Shown, long internal_memory_available, long internal_memory_total, int cpu_core, int mnc, int sdk, int mcc, int resolution_width_pixels, int resolution_height_pixels, int dpi_height, int dpi_width, String adid) {
        this.kernal = kernal;
        this.locale_country = locale_country;
        this.locale_language = locale_language;
        this.os_incremantal = os_incremantal;
        this.hardware = hardware;
        this.device = device;
        this.private_ip = private_ip;
        this.type = type;
        this.timezone = timezone;
        this.timezone_id = timezone_id;
        this.serial = serial;
        this.android_id = android_id;
        this.cpu_arch = cpu_arch;
        this.cpu_abi = cpu_abi;
        this.display = display;
        this.User_Agent = user_Agent;
        this.os_Version = os_Version;
        this.build = build;
        this.product = product;
        this.brand = brand;
        this.screen_size = screen_size;
        this.mac = mac;
        this.fingerprint = fingerprint;
        this.imei = imei;
        this.manufacturer = manufacturer;
        this.name = name;
        this.mainboard = mainboard;
        this.country = country;
        this.local_timezone = local_timezone;
        this.cpu = cpu;
        this.carrier = carrier;
        this.os_codename = os_codename;
        this.model = model;
        this.local_tz_name = local_tz_name;
        this.network_Info = network_Info;
        this.ram_Available = ram_Available;
        this.ram_Shown = ram_Shown;
        this.internal_memory_available = internal_memory_available;
        this.internal_memory_total = internal_memory_total;
        this.cpu_core = cpu_core;
        this.mnc = mnc;
        this.sdk = sdk;
        this.mcc = mcc;
        this.resolution_width_pixels = resolution_width_pixels;
        this.resolution_height_pixels = resolution_height_pixels;
        this.dpi_height = dpi_height;
        this.dpi_width = dpi_width;
        this.adid = adid;
    }

    public void setKernal(String kernal) {
        this.kernal = kernal;
    }

    public void setLocale_country(String locale_country) {
        this.locale_country = locale_country;
    }

    public void setLocale_language(String locale_language) {
        this.locale_language = locale_language;
    }

    public void setOs_incremantal(String os_incremantal) {
        this.os_incremantal = os_incremantal;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setPrivate_ip(String private_ip) {
        this.private_ip = private_ip;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezone_id(String timezone_id) {
        this.timezone_id = timezone_id;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public void setCpu_arch(String cpu_arch) {
        this.cpu_arch = cpu_arch;
    }

    public void setCpu_abi(String cpu_abi) {
        this.cpu_abi = cpu_abi;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setUser_Agent(String user_Agent) {
        User_Agent = user_Agent;
    }

    public void setOs_Version(String os_Version) {
        this.os_Version = os_Version;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setScreen_size(Double screen_size) {
        this.screen_size = screen_size;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocal_timezone(String local_timezone) {
        this.local_timezone = local_timezone;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setOs_codename(String os_codename) {
        this.os_codename = os_codename;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLocal_tz_name(String local_tz_name) {
        this.local_tz_name = local_tz_name;
    }

    public void setNetwork_Info(NetworkInfo network_Info) {
        this.network_Info = network_Info;
    }

    public void setRam_Available(long ram_Available) {
        this.ram_Available = ram_Available;
    }

    public void setRam_Shown(long ram_Shown) {
        this.ram_Shown = ram_Shown;
    }

    public void setInternal_memory_available(long internal_memory_available) {
        this.internal_memory_available = internal_memory_available;
    }

    public void setInternal_memory_total(long internal_memory_total) {
        this.internal_memory_total = internal_memory_total;
    }

    public void setCpu_core(int cpu_core) {
        this.cpu_core = cpu_core;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public void setSdk(int sdk) {
        this.sdk = sdk;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public void setResolution_width_pixels(int resolution_width_pixels) {
        this.resolution_width_pixels = resolution_width_pixels;
    }

    public void setResolution_height_pixels(int resolution_height_pixels) {
        this.resolution_height_pixels = resolution_height_pixels;
    }

    public void setDpi_height(int dpi_height) {
        this.dpi_height = dpi_height;
    }

    public void setDpi_width(int dpi_width) {
        this.dpi_width = dpi_width;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }
}
