package cl.mac.randomfamousquotes.network;

import java.util.Map;

import cl.mac.randomfamousquotes.models.Quote;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Michael on 14-08-2017.
 */

public interface Quotes {

    @GET("?cat=famous")
    Call<Quote> getQuotes();
}
