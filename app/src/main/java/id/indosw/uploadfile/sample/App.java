package id.indosw.uploadfile.sample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import id.indosw.uploadfile.service.UploadServiceConfig;
import id.indosw.uploadfile.service.data.RetryPolicyConfig;
import id.indosw.uploadfile.service.observer.request.GlobalRequestObserver;

/**
 * @author gotev (Aleksandar Gotev)
 */
public class App extends Application {

    public static String CHANNEL = "UploadServiceDemoChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            enableStrictMode();
        }

        createNotificationChannel();

        // Set your application namespace to avoid conflicts with other apps
        // using this library
        UploadServiceConfig.initialize(this, App.CHANNEL, BuildConfig.DEBUG);

        // Set up the Http Stack to use. If you omit this or comment it, HurlStack will be
        // used by default
        //UploadServiceConfig.setHttpStack(new OkHttpStack(getOkHttpClient()));

        // setup backoff multiplier
        UploadServiceConfig.setRetryPolicy(new RetryPolicyConfig(1, 10, 2, 3));

        // you can add also your own custom placeholders to be used in notification titles and
        // messages
        UploadServiceConfig.setPlaceholdersProcessor(new CustomPlaceholdersProcessor());

        // Uncomment to experiment Single Notification Handler
        // UploadServiceConfig.setNotificationHandlerFactory(ExampleSingleNotificationHandler::new);

        new GlobalRequestObserver(this, new GlobalRequestObserverDelegate());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL, "Upload Service Demo", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //private OkHttpClient getOkHttpClient() {
        //return new OkHttpClient.Builder()
                //.followRedirects(true)
                //.followSslRedirects(true)
                //.retryOnConnectionFailure(true)
                //.connectTimeout(15, TimeUnit.SECONDS)
                //.writeTimeout(30, TimeUnit.SECONDS)
                //.readTimeout(30, TimeUnit.SECONDS)
                //.addInterceptor(chain -> {
                    //Request request = chain.request().newBuilder()
                            //.header("User-Agent", UploadServiceConfig.defaultUserAgent)
                            //.build();
                    //return chain.proceed(request);
                //})
                // you can add your own request interceptors to add authorization headers.
                // do not modify the body or the http method here, as they are set and managed
                // internally by Upload Service, and tinkering with them will result in strange,
                // erroneous and unpredicted behaviors
                //.addNetworkInterceptor(chain -> {
                    //Request.Builder request = chain.request().newBuilder()
                            //.addHeader("myheader", "myvalue")
                            //.addHeader("mysecondheader", "mysecondvalue");

                    //return chain.proceed(request.build());
                //})

                // if you use HttpLoggingInterceptor, be sure to put it always as the last interceptor
                // in the chain and to not use BODY level logging, otherwise you will get all your
                // file contents in the log. Logging body is suitable only for small requests.
                //.addInterceptor(new HttpLoggingInterceptor(message -> Log.d("OkHttp", message)).setLevel(HttpLoggingInterceptor.Level.HEADERS))

                //.cache(null)
                //.build();
    //}

    private void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDialog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
                .penaltyLog()
                .build());
    }
}
