# 9Pay SDK (version 1.0.0)

9Pay SDK is a set of libraries for apps to interact with 9Pay Platform. 9Pay SDK includes the following main functions:

- Registration, login through 9Pay wallet account

- Deposit and withdrawal function from 9Pay Platform wallet. (coming soon)

- Integration of services of 9Pay.

## Install for Android

Download 9pay sdk at library folder in repo

- Config at Project build.gradle:

```groovy
    buildscript {
        repositories {
            google()
            mavenCentral()
        }
        dependencies {
            classpath "com.android.tools.build:gradle:7.0.2"
            classpath 'com.google.gms:google-services:4.3.5'
        }
    }

    allprojects {
        repositories {
            google()
            mavenCentral()
        }
    }
```

- Config at App build.gradle:
```groovy

    apply plugin: 'com.google.gms.google-services'

    repositories {
        mavenCentral()
        maven {
            url "$project.rootDir/app/libs/repo"
        }
        maven {
            url "https://storage.googleapis.com/download.flutter.io"
        }
    }
    ...
    android {
        ...
        buildTypes {
            ...
            profile {
                initWith debug
            }
        } 
    }
    
    dependencies {
        ...
        implementation files('libs/npay_sdk-release.aar')
        debugImplementation 'com.example.npay_flutter_module:flutter_debug:1.0'
        profileImplementation 'com.example.npay_flutter_module:flutter_profile:1.0'
        releaseImplementation 'com.example.npay_flutter_module:flutter_release:1.0'
        implementation platform('com.google.firebase:firebase-bom:26.5.0')
        implementation 'com.google.firebase:firebase-core'
       ...
    }

    rootProject.ext {
        set('FlutterFire', [
            FirebaseSDKVersion: '25.12.0'
        ])
    }
```

- Config at setting.gradle:
```groovy
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
        repositories {
            google()
            mavenCentral()
        }
    }
```

- Config at Manifest:
1. Add NpayActivity: 
```xml
     <activity
        android:name=".NPayActivity"
        android:resizeableActivity="false"
        android:launchMode="singleTop"
        android:theme="@android:style/Theme.Black.NoTitleBar"
        android:configChanges="orientation|keyboardHidden|keyboard|screenSize|smallestScreenSize|locale|layoutDirection|fontScale|screenLayout|density|uiMode"
        android:hardwareAccelerated="true"
        android:screenOrientation="portrait"
        android:windowSoftInputMode="adjustResize">
    </activity>
```
2. Add queries: 

```xml
     <queries>
        <package android:name="vn.ninepay.ewallet" />
        <package android:name="vn.ninepay.ewallet.beta" />
        <package android:name="vn.ninepay.ewallet.stg" />
    </queries>
```

3. Add application: 
```xml
    <application
        android:name=".NPayApplication"
        android:allowBackup="true">
    </application>
```
- Add NPayActivity & NPayApplication in demo sdk to your project

### Initializing the library

Before using the 9PaySDK, you need to import the 9PaySDK Component and pass the ref to initialize and use.

```java
    private void initSDK() {
        NPayLibrary.getInstance().init(this, new SdkConfig.Builder()
            .merchantCode(merchantCode)
            .uid(uid)
            .env(NPayLibrary.STAGING)
            .build()
        );
    }
```


#### Constant

| Property | Type | Description |
| ------------------- | ------ | ---------------------- |
| `NPayLibrary.STAGING` | `enum` | Staging environment. |
| `NPayLibrary.BETA` | `enum` | Beta environment. |
| `NPayLibrary.PRODUCTION` | `enum` | Production environment. |

### Functions of 9Pay SDK
When call some functions, 9pay will check login status of user:

- User login for the first time or accessToken expires, 9Pay sdk will direct to login screen.
  
After login successfully, then call other functions of the SDK
#### Open wallet

```java
    Intent intent = new Intent(MainActivity.this, NPayActivity.class);
    intent.putExtra("data", NPayLibrary.getInstance().walletData());
    startActivity(intent);
```
#### Payment

```java
    Intent intent = new Intent(MainActivity.this, NPayActivity.class);
    intent.putExtra("data", NPayLibrary.getInstance().paymentData(edtOrderId.getText().toString()));
    startActivity(intent);
```

| **Parameters** | **Required** | **Explanation** |
| :------------------------------------------------- | :---------- | :------------------------------------------------- |
| orderId | Yes | Partner transaction code, which needs to be unique on each transaction. |
| onSuccess | Yes | Used to catch callback when making a successful transaction from 9Pay SDK |
| onError | Yes | Used to catch callback when an error occurs during calling 9Pay SDK |



#### getAccountInfo (Coming soon)

App can use this property after SDK initialization to know the link status to 9Pay wallet.



#### getWalletInfo - Get wallet information (Coming soon)

### List of payment methods
| **payCode** | **Payment Method** |
| :-----------| :------------|
| 9Pay | 9Pay wallet payment |
| ATM | ATM card payment |
| MANUAL_BANK | Bank transfer payment |
| CREDIT | Credit card payments |


