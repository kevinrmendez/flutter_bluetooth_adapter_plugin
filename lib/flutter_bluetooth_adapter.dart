import 'dart:async';

import 'package:flutter/services.dart';

class FlutterBluetoothAdapter {
  static const MethodChannel _channel =
  const MethodChannel('flutter_bluetooth');

  static Future<List> getBluetoothList() async {
    return await _channel.invokeMethod('getBluetoothList');
  }

  static Future<bool> enableBluetooth() async {
    return await _channel.invokeMethod('enableBluetooth');
  }

  static Future<bool> disableBluetooth() async {
    return await _channel.invokeMethod('disableBluetooth');
  }

  static Future<String> getAddress() async {
    return await _channel.invokeMethod('getAddress');
  }

  static Future<String> getName() async {
    return await _channel.invokeMethod('getName');
  }

  static Future<String> setName(String name) async {
    return await _channel.invokeMethod('setName', {"name": name});
  }

  static Future<bool> isEnabled() async {
    return await _channel.invokeMethod('isEnabled');
  }
}