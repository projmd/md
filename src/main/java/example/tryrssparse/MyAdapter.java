package example.tryrssparse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import example.tryrssparse.DataObject.New;

/**
 * Created by redhwan on 2/9/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
     Context c;
     ArrayList<New> newsList;
       New news;


    public MyAdapter(Context c, ArrayList<New> newsList) {
        this.c = c;
        this.newsList = newsList;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        news= newsList.get(position);
        holder.mTitleView.setText(news.getNtitle());
        if(news.getNdetails().length()<1)
        {
            holder.mDetailsView.setText("NO Details for this News");
        }
        else if(news.getNdetails().length()>100)
        {
            String detail = news.getNdetails().substring(0,100)+"...";
            holder.mDetailsView.setText(detail);
        }
        else
        {
            holder.mDetailsView.setText(news.getNdetails());
        }

        //ImageDownloader.downloadImage(c,news.getNiconUrl(),holder.mIconView);

        //share button implementation
        holder.mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, "Share Button", Toast.LENGTH_LONG).show();
            }
            });
                //Implement click listener
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

//                       Toast.makeText(c,newsList.get(position).getNtitle(),Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(c, NewsDetails.class);
                        intent.putExtra("Title", newsList.get(position).getNtitle()); //you can name the keys whatever you like
                        intent.putExtra("Details", newsList.get(position).getNdetails()); //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
                        intent.putExtra("Url", newsList.get(position).getnUrl());
                        intent.putExtra("ImageUrl", newsList.get(position).getNiconUrl());
                        intent.putExtra("Date", newsList.get(position).getPubDate());
                        view.getContext().startActivity(intent);
                    }
                });


//        ImageDownloader.downloadImage(c,"https://amazingcarousel.com/wp-content/uploads/amazingcarousel" +
//                "/3/images/lightbox/golden-wheat-field-lightbox.jpg",holder.mIconView);
        //ImageDownloader.downloadImage(c,"http://www.cyberangel.it/blog/wp-content/uploads/2010/03/link.jpg",holder.mIconView);
       // ImageDownloader.downloadImage(c,"http://rassd.com/upload/mixmedia-02101122Wh9J7.jpg",holder.mIconView);
        //holder.mIconView.setImageURI(news.getNiconUrl());
    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public Context getC() {
        return c;
    }
}