package cc.colorcat.kingfisher.sample;

import java.io.IOException;
import java.lang.reflect.Type;

import cc.colorcat.netbird.JsonParser;
import cc.colorcat.netbird.NetworkData;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Response;
import cc.colorcat.netbird.StateIOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class ResultParser<T> extends JsonParser<T> {
    private final Gson gson;

    public ResultParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public NetworkData<? extends T> parse(Response response) throws IOException {
        try {
            // fastjson
            //                String content = response.responseBody().string();
            //                ParameterizedType pt = new ParameterizedTypeImpl(new Type[]{typeOfT}, null, Result.class);
            //                Result<T> result = JSON.parseObject(content, pt);

            // jackson
            //                Reader reader = response.responseBody().reader();
            //                TypeFactory factory = TypeFactory.defaultInstance();
            //                JavaType innerType = factory.constructType(typeOfT);
            //                JavaType outerType = factory.constructParametricType(Result.class, innerType);
            //                Result<T> result = MAPPER.readValue(reader, outerType);

            // gson
            Type type = TypeToken.getParameterized(Result.class, generateType()).getType();
            Result<T> result = gson.fromJson(response.responseBody().reader(), type);
            if (result != null) {
                T data = result.getData();
                if (result.getStatus() == Result.STATUS_OK && data != null) {
                    return NetworkData.newSuccess(data);
                } else {
                    return NetworkData.newFailure(result.getStatus(), result.getMsg());
                }
            }
            return NetworkData.newFailure(response.responseCode(), response.responseMsg());
        } catch (Exception e) {
            throw new StateIOException(response.responseCode(), response.responseMsg(), e);
        }
    }
}
