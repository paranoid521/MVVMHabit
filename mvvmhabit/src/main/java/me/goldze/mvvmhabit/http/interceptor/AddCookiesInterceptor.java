package me.goldze.mvvmhabit.http.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.util.HashSet;

import me.goldze.mvvmhabit.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) SPUtils.getInstance().getStringSet("cookie", null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
