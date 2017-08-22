package cl.mac.randomfamousquotes.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.sql.Wrapper;

import cl.mac.randomfamousquotes.models.Quote;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Michael on 14-08-2017.
 */

public class GetQuotes extends AsyncTask<String, Void, Quote> {


    @Override
    protected Quote doInBackground(String... params) {

        Quotes request = new QuotesInterceptor().get();

        Call<Quote> call = request.getQuotes();

        try {
            Response<Quote> response = call.execute();
            if (200 == response.code() && response.isSuccessful()){
                Log.d("IS_WORKING", response.body().getQuote());
                return response.body(); //devolvemos el cuerpo del json.

            }else{
                return null;
            }
        } catch (IOException e) {
            /*Something went wrong*/
            return null;
        }
    }
}
