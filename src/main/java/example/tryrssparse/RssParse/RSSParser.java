package example.tryrssparse.RssParse;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import example.tryrssparse.DataObject.New;
import example.tryrssparse.Model.DatabaseHelper;
import example.tryrssparse.MyAdapter;

/**
 * Created by redhwan on 2/9/2017.
 */

public class RSSParser extends AsyncTask<Void,Void,Boolean> {
    Context c;
    InputStream is;
    RecyclerView rv;
    ProgressDialog pd;

    DatabaseHelper dbNews;
    String title,details,date,link;


     public static  ArrayList<New> newsList=new ArrayList<>();
    public RSSParser(Context c, InputStream is, RecyclerView rv) {
        this.c = c;
        this.is = is;
        this.rv = rv;
        dbNews = new DatabaseHelper(c);

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parse data");
        pd.setMessage("Parsing data...Please wait");
        pd.show();
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRSS();
    }
    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        pd.dismiss();
        if(isParsed)
        {
                //Bind
                rv.setAdapter(new MyAdapter(c, newsList));

        }else {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean parseRSS()
    {
        try
        {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();


            parser.setInput(is, null);

            int event=parser.getEventType();

            String value=null;
            newsList.clear();
            New article=new New();

            do {
                String name=parser.getName();
                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if(name.equals("item"))
                        {
                                article=new New();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        value=parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("title"))
                        {
                            article.setNtitle(value);
                            System.out.println("title : "+article.getNtitle());
                            this.title = value;
                        }
                        else if(name.equals("description"))
                        {
                            article.setNdetails(value);
                            System.out.println("description : "+article.getNdetails());
                            this.details = value;
                        }
                        else if(name.equals("pubDate"))
                        {
                            article.setPubDate(value);
                            System.out.println("pubDate : "+article.getPubDate());
                            this.date = value;
                        }
                        else if(name.equals("image"))
                        {
                            article.setNiconUrl(value);
                            System.out.println("image : "+article.getNdetails());
                        }
                        else if(name.equals("link"))
                        {
                            article.setnUrl(value);
                            System.out.println("link : "+article.getnUrl());
                            this.link = value;
                        }

                        if(name.equals("item"))
                        {
                            newsList.add(article);

                            Runnable run = new Runnable() {
                                @Override
                                public void run() {

                                    int intNewId = dbNews.fnTotalRow() + 1;
                                    String query = "insert into "+DatabaseHelper.tblname+" values('"+intNewId+"','"+title+"','"+details+"','"+date + "','"+link + "');";
                                    dbNews.fnExecuteSql(query,c);

                                }
                            };

                            Thread thrSave = new Thread(run);
                            thrSave.start();


                        }
                        break;
                }
                event=parser.next();
            }while (event != XmlPullParser.END_DOCUMENT);
            is.close();
            return true;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}