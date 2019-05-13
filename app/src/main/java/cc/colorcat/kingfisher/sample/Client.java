package cc.colorcat.kingfisher.sample;

import android.app.Application;

import com.google.gson.GsonBuilder;

import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Platform;
import cc.colorcat.netbird.android.AndroidPlatform;

/**
 * Author: cxx
 * Date: 2019-05-10
 */
public class Client extends Application {
    static {
        Platform platform = new AndroidPlatform();
        NetBird defaultClient = new NetBird.Builder("http://www.imooc.com/")
                .platform(platform)
                .addTailInterceptor(new JsonLoggingTailInterceptor(true))
                .enableGzip(true)
                .build();
        NetBird gitHubClient = new NetBird.Builder("https://api.github.com/")
                .platform(platform)
                .addTailInterceptor(new JsonLoggingTailInterceptor())
                .enableGzip(true)
                .build();

        new KingFisher.Builder()
                .client(defaultClient)
                .registerClient("github", gitHubClient)
                .addParserFactory(new GsonParserFactory<>(new GsonBuilder().create()))
                .initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
