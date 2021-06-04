import 'dart:async';

import 'package:flutter/services.dart';

export 'package:simple_chromesafari/simple_chromesafari.dart';

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
  static Future<void> open(String? url) async {

    if (url == null) {
      throw ArgumentError('url is require.');
    }

    if (url.isEmpty) {
      throw ArgumentError('url must be not empty.');
    }

    await _channel.invokeMethod('openBrowser', {'url': url});
  }

  /*
   * Close a browser.
   */
  static Future<void> close() async {
    await _channel.invokeMethod('closeBrowser');
  }
}
