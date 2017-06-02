package example.tryrssparse;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by redhwan on 2/9/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView mTitleView,mDetailsView;
    ImageView mIconView;
    Button mShareButton;

    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);
        mTitleView= (TextView) itemView.findViewById(R.id.title);
        mDetailsView= (TextView) itemView.findViewById(R.id.details);
        mIconView= (ImageView) itemView.findViewById(R.id.icon);
        mIconView.setScaleType(ImageView.ScaleType.FIT_XY);
        mShareButton = (Button) itemView.findViewById(R.id.shareButton);
                itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        System.out.println(view);
        if (getLayoutPosition() >= 0){
            this.itemClickListener.onItemClick(view,getLayoutPosition());

        }
        else
        {
            System.out.println("View is Empty "+getLayoutPosition());
        }

    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}