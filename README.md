# simple_chromesafari

A simple in app browser controller for Flutter mobile application.

## What can I do?
You can control in app browser simply.
Support a browser **open** and **close**.

## When do I use it?
- when you want to open browser on your flutter app.
- when you using **oauth** or **deep link** in your flutter app.
- when you want to use closeable browser.

## SimpleChromesafari
SimpleChromesafari class providing **open** function and **close** function.

```.dart

    /* simple_chromesafari.dart */

    static Future<void> open(String url) async { // 'url' is require.

        ...
        await _channel.invokeMethod('openBrowser', {'url': url});
    }

  
    static Future<void> close() async {
        await _channel.invokeMethod('closeBrowser');
    }
```
- open(String url) : open browser with target url. **url** is require. if url is `null` or ``(empty string) then throw `ArgumentError`.
- close( ) : close browser. can be used even when the browser is not in operation.

```.dart

    /* your source file... */
    ...
    
    String url = 'https://flutter.io';
    await SimpleChromesafari.open(url);
    
    ...
    
    await SimpleChromesafari.close();
    
    ...
```

### Android
Using chrome custom tabs.

### iOS
target : **>= iOS 9.0**

Using SFSafariViewController.

