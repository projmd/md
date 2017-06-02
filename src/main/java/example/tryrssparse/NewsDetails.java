package example.tryrssparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import example.tryrssparse.RssParse.ImageDownloader;

public class NewsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);


        TextView dTitle = (TextView)findViewById(R.id.Dtitle);
        TextView dDetails = (TextView)findViewById(R.id.Ddetails);
        TextView dDate = (TextView)findViewById(R.id.Ddate);
        ImageView dIcon= (ImageView)findViewById(R.id.Dicon);
        dIcon.setScaleType(ImageView.ScaleType.FIT_XY);
        Button Dlink = (Button)findViewById(R.id.DLinkButton);

        dTitle.setText(getIntent().getExtras().getString("Title"));
        dDetails.setText(getIntent().getExtras().getString("Details"));
        dDate.setText(getIntent().getExtras().getString("Date"));
        ImageDownloader.downloadImage(getBaseContext(),getIntent().getExtras().getString("ImageUrl"),dIcon);
        final String link = getIntent().getExtras().getString("Url");

        Dlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), WebLink.class);
                intent.putExtra("link",link);
                view.getContext().startActivity(intent);
//                Toast.makeText(getBaseContext(),"Clicked",Toast.LENGTH_LONG).show();
            }
        });

    }
}
