package cl.mac.randomfamousquotes.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cl.mac.randomfamousquotes.models.Quote;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michael on 13-08-2017.
 */

public class QuotesInterceptor {

    public static final String BASE_URL = "https://andruxnet-random-famous-quotes.p.mashape.com/";

    public Quotes get() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                        .header("X-Mashape-Key", "i23BJZxTKmmshx9xa0CqJv8ZUhGHp1AVt4CjsnUEpwVI4DRoFh")
                        .header("Accept", "application/json")
                        .build();

                Response response = chain.proceed(request);

                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Quotes request = interceptor.create(Quotes.class);

        return request;
    }

}
