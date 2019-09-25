#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'simple_chromesafari'
  s.version          = '0.0.1'
  s.summary          = 'A simple browser controller for Flutter.'
  s.description      = <<-DESC
A simple browser controller for Flutter.
                       DESC
  s.homepage         = 'https://spiralmoon.tistory.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Your Company' => 'mjh1660@gmail.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'

  s.ios.deployment_target = '9.0'
end

