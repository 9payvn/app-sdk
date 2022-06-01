#9Pay SDK (version 1.0.0)

9Pay SDK is a set of libraries for apps to interact with 9Pay Platform. 9Pay SDK includes the following main functions:

- Registration, login through 9Pay wallet account

- Deposit and withdrawal function from 9Pay Platform wallet. (coming soon)

- Integration of services of 9Pay.

## Install for Android

Download 9pay sdk at
- In Android Studio, open File/new/new module, choose import .JAR/.AAR Package, fill file name is path of `npay_sdk.aar` was attachment.


- Config at App build.gradle:
```java
    dependencies {
        ...
        implementation project(':npay_sdk')
       ...
    }
```


### Initializing the library

Before using the 9PaySDK, you need to import the 9PaySDK Component and pass the ref to initialize and use.

```java
import vn.ewallet.npay_android_sdk.NPayLibrary;
import vn.ewallet.npay_android_sdk.model.SdkConfig;

private void initSDK() {
        NPayLibrary.getInstance().init(this, new SdkConfig.Builder()
        .appVersionCode("app version_code")
        .appVersionName("app version_name")
        .merchantCode("merchant_code")
        .uid("user phone or email")
        .flavor("ENV")
        .build()
        );
    }
```


#### Constant

| Property | Type | Description |
| ------------------- | ------ | ---------------------- |
| `ENV.STAGING` | `enum` | Staging environment. |
| `ENV.BETA` | `enum` | Beta environment. |
| `ENV.PRODUCTION` | `enum` | Production environment. |

### Functions of 9Pay SDK
When call some functions, 9pay will check login status of user:

- Use login for the first time or accessToken expires, 9Pay sdk will direct to login screen.
  
After login successfully, then call other functions of the SDK
#### Open wallet

```java
NPayLibrary.getInstance().open9pay();
```
#### Payment

```java
NPayLibrary.getInstance().payment("order_id", new PaymentCallBack() {

@Override
public void onSuccess(String transactionID) {
            //handle payment success
        }

@Override
public void onError(int errorCode, String message) {
            //handle error
        }
   }
);
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

## Install for IOS (Comming soon)


