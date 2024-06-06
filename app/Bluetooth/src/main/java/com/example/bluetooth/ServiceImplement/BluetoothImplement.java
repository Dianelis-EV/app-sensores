package com.example.bluetooth.ServiceImplement;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.pm.PackageManager;
import android.graphics.LinearGradient;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.bluetooth.Service.IBluetooth;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BluetoothImplement implements IBluetooth {
    private static final int REQUEST_BLUETOOTH_CONNECT_Code = 1;
    private BluetoothServerSocket conect;
    private BluetoothSocket socket;
    private BluetoothLeScanner busqueda;
    private static final UUID uuid = UUID.fromString("0000111e-0000-1000-8000-00805f9b34fb");

    @Override
    public void coneexion() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter.isEnabled()){
            @SuppressLint("MissingPermission") Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        }
   }

    @Override
    public boolean habilitado() {
        return false;
    }

    @Override
    public void startScanning( String[] uuids) {


    }

    @Override
    public void stopScanning() {

    }

    @Override
    public List<BluetoothDevice> getFoundDeviceList() {
        return null;
    }
}
