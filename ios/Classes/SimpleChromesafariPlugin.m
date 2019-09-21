#import "SimpleChromesafariPlugin.h"
#import <simple_chromesafari/simple_chromesafari-Swift.h>

@implementation SimpleChromesafariPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSimpleChromesafariPlugin registerWithRegistrar:registrar];
}
@end
