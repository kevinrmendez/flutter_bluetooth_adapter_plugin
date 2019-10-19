package kevinrmendez.flutter_bluetooth_adapter

import android.bluetooth.BluetoothAdapter
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


class FlutterBluetoothAdapterPlugin(val registrar: Registrar) : MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "flutter_bluetooth")
      channel.setMethodCallHandler(FlutterBluetoothAdapterPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    val pairedDevices = mBluetoothAdapter.getBondedDevices()
    var bluetoothStatus: Boolean
    var address: String
    var deviceName: String?
    when (call.method) {
      "getBluetoothList" -> {
        val s = ArrayList<String>()
        for (bt in pairedDevices)
          s.add(bt.getName())
        result.success(s)
      }
      "enableBluetooth" -> {
        bluetoothStatus = mBluetoothAdapter.enable()
        result.success(bluetoothStatus)
      }
      "disableBluetooth" -> {
        bluetoothStatus = mBluetoothAdapter.disable()
        result.success(bluetoothStatus)
      }
      "getAddress" -> {
        address = mBluetoothAdapter.getAddress()
        result.success(address)
      }
      "getName" -> {
        deviceName = mBluetoothAdapter.getName()
        result.success(deviceName)
      }
      "setName" -> {
        var isNameSet: Boolean
        deviceName = call.argument("name")
        isNameSet = mBluetoothAdapter.setName(deviceName)
        result.success(isNameSet)
      }
      "isEnabled" -> {
        val isEnabled = mBluetoothAdapter.isEnabled()
        result.success(isEnabled)
      }
    }
  }
}
