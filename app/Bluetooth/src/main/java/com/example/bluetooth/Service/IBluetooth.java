package com.example.bluetooth.Service;

import android.bluetooth.BluetoothDevice;

import java.util.List;

public interface IBluetooth {
    void conexion();
    boolean habilitado();
    void startScanning( String[] uuids);
    void stopScanning();
    List<BluetoothDevice> getFoundDeviceList();

}
