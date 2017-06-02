package example.tryrssparse;

/**
 * Created by redhwan on 2/20/2017.
 */


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.tryrssparse.DataObject.New;
import example.tryrssparse.Model.DatabaseHelper;
import example.tryrssparse.RssParse.Connector;
import example.tryrssparse.RssParse.Downloader;

public class SquadFragment extends android.support.v4.app.Fragment {

    View view;
    DatabaseHelper databaseHelper;
    String temp;

    public static ArrayList<New> newsList=new ArrayList<>();

     final static String urlAddress="http://rss.nytimes.com/services/xml/rss/nyt/World.xml";
    // final static String urlAddress = "http://www.rassd.com/rss.xml";
//    final static String urlAddress = "http://www.albayanat.blogspot.com/atom.xml";
//    final static String urlAddress = "http://partner.dw.com/rdf/rss-ar-all";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.content_main,null);
        databaseHelper = new DatabaseHelper(view.getContext());
        final RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));



             Object connection= Connector.connect(urlAddress);
            if(connection.toString().startsWith("Error"))
            {
                new Downloader(this.getContext(), urlAddress, rv).execute();
            }
            else {

                String strSql = "Select * from "+ DatabaseHelper.tblname;
                Cursor curr = databaseHelper.getReadableDatabase().rawQuery(strSql,null);

                while (curr.moveToNext()) {
                    New article=new New();
                    article.setNtitle(curr.getString(curr.getColumnIndex(DatabaseHelper.colTitle)));
                    article.setNdetails(curr.getString(curr.getColumnIndex(DatabaseHelper.colDetails)));
                    article.setPubDate(curr.getString(curr.getColumnIndex(DatabaseHelper.colDate)));
                    article.setnUrl(curr.getString(curr.getColumnIndex(DatabaseHelper.colink)));

                    newsList.add(article);
                }
                rv.setAdapter(new MyAdapter(view.getContext(), newsList));


            }



        return view;


    }
}