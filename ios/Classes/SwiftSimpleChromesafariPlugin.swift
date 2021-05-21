import Flutter
import UIKit
import SafariServices

public class SwiftSimpleChromesafariPlugin: NSObject, FlutterPlugin {
    
    public static func register(with registrar: FlutterPluginRegistrar) {
        
        let channel = FlutterMethodChannel(name: "simple_chromesafari", binaryMessenger: registrar.messenger())
        let instance = SwiftSimpleChromesafariPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        
        // open safari browser.
        if (call.method == "openBrowser") {
            let arguments = call.arguments as? NSDictionary
            let url: String = arguments!["url"] as! String
            let nsUrl = URL(string:url);
            
            let uiController = SwiftSimpleChromesafariPlugin.topViewController()!;
            let sfViewController = SFSafariViewController(url:nsUrl!);
            
            uiController.present(sfViewController, animated: true, completion: nil);
            
            result(nil);
        }
        // close safari browser.
        else if (call.method == "closeBrowser") {
            
            let window = UIApplication.shared.keyWindow;
            let controller = window?.rootViewController;
            
            controller?.dismiss(animated: true, completion: nil);
            
            result(nil);
        }
    }
    
    // get root view controller.
    class func topViewController() -> UIViewController? {
        
        if let keyWindow = UIApplication.shared.keyWindow {
            if var viewController = keyWindow.rootViewController {
                while viewController.presentedViewController != nil {
                    viewController = viewController.presentedViewController!
                }
                
                return viewController
            }
        }
        
        return nil
    }
}
