package cc.colorcat.kingfisher.sample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

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
    static {
        NetBird netBird = new NetBird.Builder("https://api.github.com/")
                .addTailInterceptor(new JsonLoggingTailInterceptor())
                .enableGzip(true)
                .build();
        new KingFisher.Builder()
                .client(netBird)
                .addParserFactory(new ResultParserFactory<>(new Gson()))
                .initialize();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cc.colorcat.kingfisher.sample", appContext.getPackageName());
    }

    @Test
    public void testKingFisher() throws Exception {
        TestApi api = new TestApiService();
        api.listCourses(4, 30).enqueue(new SimpleCallback<List<Course>>() {
            private static final String TAG = "KingFisher";
            private final Logger logger = AndroidPlatform.get().logger();

            @Override
            public void onStart() {
                super.onStart();
                logger.log(TAG, "onStart", Level.INFO);
            }

            @Override
            public void onSuccess(List<Course> result) {
                super.onSuccess(result);
                logger.log(TAG, "onSuccess", Level.INFO);
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                logger.log(TAG, "onFailure, code = " + code + ", msg = " + msg, Level.INFO);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                logger.log(TAG, "onFinish", Level.INFO);
            }
        });
    }
}
