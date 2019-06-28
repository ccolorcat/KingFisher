package cc.colorcat.kingfisher.sample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.core.SimpleCallback;
import cc.colorcat.netbird.Level;
import cc.colorcat.netbird.Logger;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.android.AndroidPlatform;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "KingFisher";
    private static final Logger LOGGER = AndroidPlatform.get().logger();
    private static final TestApi API = new TestApiService();

    static {
        NetBird netBird = new NetBird.Builder("https://api.github.com/")
            .addTailInterceptor(new JsonLoggingTailInterceptor())
            .enableGzip(true)
            .build();
        new KingFisher.Builder()
            .defaultClient(netBird)
            .addParserFactory(new GsonParserFactory<>(new Gson()))
            .initialize();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cc.colorcat.kingfisher.sample", appContext.getPackageName());
    }

    public void testListRepos() throws Exception {
        API.listRepos("ccolorcat")
            .enqueue(new SimpleCallback<List<Repo>>() {
                @Override
                public void onStart() {
                    super.onStart();
                    LOGGER.log(TAG, "onStart", Level.INFO);
                }

                @Override
                public void onSuccess(List<Repo> result) {
                    super.onSuccess(result);
                }

                @Override
                public void onFailure(int code, String msg) {
                    super.onFailure(code, msg);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    LOGGER.log(TAG, "onFinish", Level.INFO);
                }
            });
    }

    @Test
    public void testListCourses() throws Exception {
        API.listCourses(4, 30)
            .parser(new ResultParser<List<Course>>(new Gson()) {})
            .enqueue(new SimpleCallback<List<Course>>() {

                @Override
                public void onStart() {
                    super.onStart();
                    LOGGER.log(TAG, "onStart", Level.INFO);
                }

                @Override
                public void onSuccess(List<Course> result) {
                    super.onSuccess(result);
                    LOGGER.log(TAG, "onSuccess", Level.INFO);
                }

                @Override
                public void onFailure(int code, String msg) {
                    super.onFailure(code, msg);
                    LOGGER.log(TAG, "onFailure, code = " + code + ", msg = " + msg, Level.INFO);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    LOGGER.log(TAG, "onFinish", Level.INFO);
                }
            });
    }
}
