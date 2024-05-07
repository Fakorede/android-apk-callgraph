# android-apk-callgraph

## Requirements

- JDk 11 or above
- Ensure path to `ANDROID_HOME` is set

## Setup

```
$ git clone https://github.com/Fakorede/android-apk-callgraph.git
$ cd android-apk-callgraph
$ ./gradlew build
```

Finally, install dependencies:
```
$ ./gradlew build
```

## Run

```
$ java -cp /path/to/android-apk-callgraph/app/build/libs/android-apk-callgraph-all.jar com.thefabdev.androidcallgraph.Main -p $ANDROID_HOME/platforms -a /path/to/apk
```
