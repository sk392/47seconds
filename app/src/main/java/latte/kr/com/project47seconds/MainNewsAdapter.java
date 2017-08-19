package latte.kr.com.project47seconds;

/**
 * Created by SK392 on 2017-07-30.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by sk392 on 2016-09-20.
 */
public class MainNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<MainNewsItem> resultItemList;
    int item_layout;
    View.OnClickListener mOnClickListener;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResultViewholder viewholder = (ResultViewholder) holder;
        MainNewsItem item = resultItemList.get(position);

        viewholder.tvsubtitle.setText(item.getSubtitle());
        viewholder.tvdate.setText(item.getDate());
        viewholder.tvtitle.setText(item.getTitle());
        Glide.with(context).load(item.imageurl)
                .centerCrop()
                .fitCenter().into(viewholder.ivMain);
    }


    @Override
    public ResultViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_news,null);
        view.setOnClickListener(mOnClickListener);
        ResultViewholder resultViewholder = new ResultViewholder(view);
        return resultViewholder;
    }

    @Override
    public int getItemCount() {
        return this.resultItemList.size();
    }
    public MainNewsAdapter(Context context, List<MainNewsItem> resultItemList, int item_layout, View.OnClickListener onClickListener){
        this.context = context;
        this.resultItemList = resultItemList;
        this.item_layout = item_layout;
        this.mOnClickListener =onClickListener;
    }
    public final static class ResultViewholder extends RecyclerView.ViewHolder{
        ImageView ivMain;
        TextView tvdate,tvtitle,tvsubtitle;

        public ResultViewholder(View itemView){
            super(itemView);
            ivMain =(ImageView)itemView.findViewById(R.id.iv_main_news_item);
            tvdate = (TextView)itemView.findViewById(R.id.tv_main_news_item_date);
            tvtitle = (TextView)itemView.findViewById(R.id.tv_main_news_item_title);
            tvsubtitle = (TextView)itemView.findViewById(R.id.tv_main_news_item_subtitle);
        }
    }
    public void animate(){
        notifyItemRangeChanged(0,getItemCount());
    }

}
