package firstapp.raherasesh.com.tr.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import firstapp.raherasesh.com.tr.R;


/**
 * Created by Sh-Java on 12/10/2016.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {

    TextView textNewsTitle;
    TextView textNewsSummary;
    ImageView imgNews;
    View itemView;

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public NewsViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.textNewsTitle = (TextView) itemView.findViewById(R.id.textTitle);
        this.textNewsSummary = (TextView) itemView.findViewById(R.id.textNewsSummary);
        this.imgNews = (ImageView) itemView.findViewById(R.id.imgNews);



    }
}
