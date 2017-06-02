package example.tryrssparse.RssParse;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import example.tryrssparse.R;

/**
 * Created by redhwan on 2/10/2017.
 */

public class ImageDownloader {

    public static void downloadImage (Context context, String imageUrl, ImageView imageView)
    {
        if(imageUrl != null && imageUrl.length()>0)
        {
            Picasso.with(context).load(imageUrl).placeholder(R.drawable.icon_luncher).into(imageView);
        }
        else
        {
                Picasso.with(context).load(R.drawable.logo).into(imageView);
        }
    }

}
