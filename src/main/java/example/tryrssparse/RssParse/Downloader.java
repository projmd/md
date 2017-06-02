package example.tryrssparse.RssParse;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by redhwan on 2/9/2017.
 */

public class Downloader extends AsyncTask<Void,Void,Object> {
    Context c;
    String urlAddress;
    RecyclerView rv;

    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, RecyclerView rv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Fetch data");
        pd.setMessage("Fetching data...Please wait");
        pd.show();
    }
    @Override
    protected Object doInBackground(Void... params) {
        return downloadData();
    }
    @Override
    protected void onPostExecute(Object data) {
        super.onPostExecute(data);
        pd.dismiss();
        try {
            if (data.toString().startsWith("Error")) {
                Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show();
            } else {
                //PARSE
                new RSSParser(c, (InputStream) data, rv).execute();
            }
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
    private Object downloadData()
    {
        Object connection=Connector.connect(urlAddress);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }
        try
        {
            HttpURLConnection con= (HttpURLConnection) connection;
            int responseCode=con.getResponseCode();
            if(responseCode==con.HTTP_OK)
            {

                InputStream inputStream = new BufferedInputStream(con.getInputStream());
//                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//
//                InputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes());
                //inputStream.close();
                //return ErrorTracker.IO_EROR+con.getResponseMessage();
                return inputStream;
            }
            else
            {
                return ErrorTracker.RESPONSE_EROR + con.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.IO_EROR;
        }
    }
}