package cc.colorcat.kingfisher.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cc.colorcat.kingfisher.core.DownPack;
import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.core.SimpleCallback;
import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import cc.colorcat.netbird.DownloadListener;
import cc.colorcat.netbird.GenericPlatform;
import cc.colorcat.netbird.Level;
import cc.colorcat.netbird.Logger;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Platform;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String TAG = "NetBird";
    private static final Gson GSON;
    private static final Logger LOGGER;
    private static final TestApi SERVICE;

    static {
        GSON = new GsonBuilder().create();
        Platform platform = new GenericPlatform();
        LOGGER = platform.logger();
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
                .addParserFactory(new GsonParserFactory<>(GSON))
                .initialize();
        SERVICE = new TestApiService();
    }

    @Test
    public void testGitHub() throws IOException {
        List<Repo> repos = SERVICE.listRepos("ccolorcat").execute();
        System.out.println(repos);
    }

    @Test
    public void testGithubAsync() {
        SERVICE.listRepos("ccolorcat").enqueue(new SimpleCallback<List<Repo>>() {
            @Override
            public void onStart() {
                super.onStart();
                log("onStart", Level.DEBUG);
            }

            @Override
            public void onSuccess(List<Repo> result) {
                super.onSuccess(result);
                log("onSuccess, result:\n" + result, Level.DEBUG);
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                log("onFailure, code=" + code + ", msg=" + msg, Level.ERROR);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                log("onFinish", Level.DEBUG);
                threadNotify();
            }
        });
        threadWait();
    }

    @Test
    public void testMooc() throws IOException {
        Parser<List<Course>> parser = new ResultParser<List<Course>>(GSON) {};
        List<Course> result = SERVICE.listCourses(4, 30).parser(parser).execute();
        System.out.println(result);
    }

    @Test
    public void testMoocAsync() {
        Parser<List<Course>> parser = new ResultParser<List<Course>>(GSON) {};
        SERVICE.listCourses(4, 30).parser(parser).enqueue(new SimpleCallback<List<Course>>() {
            @Override
            public void onStart() {
                super.onStart();
                log("onStart", Level.DEBUG);
            }

            @Override
            public void onSuccess(List<Course> result) {
                super.onSuccess(result);
                log("onSuccess, result:\n" + result, Level.DEBUG);
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                log("onFailure, code=" + code + ", msg=" + msg, Level.ERROR);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                log("onFinish", Level.DEBUG);
                threadNotify();
            }
        });
        threadWait();
    }

    @Test
    public void testDownload() throws IOException {
        TestApi api = new TestApiService();
        File savePath = new File(System.getProperty("user.home"), "weChat.apk");
        DownPack pack = DownPack.create(savePath, new DownloadListener() {
            @Override
            public void onChanged(long finished, long total, int percent) {
                System.out.printf("%d, %d, %d\n", finished, total, percent);
            }
        });
        System.out.println(api.downloadWeChat(pack).execute());
    }

    private static void log(String msg, Level level) {
        LOGGER.log(TAG, msg, level);
    }

    private static void threadWait() {
        synchronized (TAG) {
            try {
                TAG.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void threadNotify() {
        synchronized (TAG) {
            try {
                TAG.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}