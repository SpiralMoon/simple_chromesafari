import 'dart:async';

import 'package:flutter/services.dart';

// Browser controller. support only 'open' and 'close'.
class SimpleChromesafari {
  static const MethodChannel _channel =
      const MethodChannel('simple_chromesafari');

  /*
   * Open a browser.
   *
   * Android : show chrome custom tabs.
   * iOS : show SFSafariViewController.
   */
  static Future<void> open(String url) async {
    await _channel.invokeMethod('openBrowser', {'url': url});
  }

  /*
   * Close a browser.
   */
  static Future<void> close() async {
    await _channel.invokeMethod('closeBrowser');
  }
}
