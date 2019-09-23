import 'package:flutter/material.dart';
import 'dart:async';

import 'package:simple_chromesafari/simple_chromesafari.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  final String url = 'https://github.com';

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('Browser open & close example'),
          ),
          body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                RaisedButton(
                  onPressed: () async {
                    SimpleChromesafari.open(url);
                  },
                  child: Text('Open browser'),
                ),
                RaisedButton(
                  onPressed: () async {
                    SimpleChromesafari.open(url);
                    await Future.delayed(Duration(seconds: 7));
                    SimpleChromesafari.close();
                  },
                  child: Text('Open browser and close after 7 seconds'),
                ),
              ],
            ),
          )),
    );
  }
}
