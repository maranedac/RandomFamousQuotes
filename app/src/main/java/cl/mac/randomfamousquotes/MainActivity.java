package cl.mac.randomfamousquotes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import cl.mac.randomfamousquotes.models.Quote;
import cl.mac.randomfamousquotes.network.GetQuotes;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressDialog progressDialog;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.rootRl);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);

        new BackgroundQuotes().execute();

        textView = (TextView) findViewById(R.id.quoteTv);
        author = (TextView) findViewById(R.id.authorTv);
        ImageButton button = (ImageButton) findViewById(R.id.quoteBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundQuotes().execute();
            }
        });


    }

    private class BackgroundQuotes extends GetQuotes{
        @Override
        protected void onPreExecute() {
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Quote quote) {
            if (quote != null ){
                textView.setText(quote.getQuote());
                author.setText(quote.getAuthor());

            }
            progressDialog.dismiss();
        }

    }




}
