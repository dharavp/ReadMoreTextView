package com.readmore.android.demo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.readmore.ReadMoreTextView;
import com.readmore.android.demo.databinding.ItemTextListBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/9/18.
 */

public class TextListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private ArrayList<String> textList;
  private SparseBooleanArray stateReadMoreText;

  public TextListAdapter() {
    textList = new ArrayList<>();
  }

  void addData(List<String> data) {
    textList.addAll(data);
    stateReadMoreText = new SparseBooleanArray(textList.size());
    for (int i = 0; i < textList.size(); i++) {
      stateReadMoreText.append(i, true);//default true
    }
    notifyDataSetChanged();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    ItemTextListBinding mBinding;
    Context context;

    public MyViewHolder(View itemView) {
      super(itemView);
      mBinding = DataBindingUtil.bind(itemView);
      context = itemView.getContext();
      mBinding.txtView.setTrimMode(ReadMoreTextView.TRIM_MODE_LINES);
      mBinding.txtView.setColorClickableText(
          ContextCompat.getColor(context, R.color.loadMoreText));

    }

    public void bind(String data, int position) {
      if (data != null) {
        boolean stateText = stateReadMoreText.get(position);
        Log.e("textListAdapter", "bind: " + position + " " + stateText);
        //it is remain the state of textview wheather it is expanded or collapsed
        mBinding.txtView.setReadMoreState(stateText);
        mBinding.txtView.setText(data);
        mBinding.txtView.setTrimLines(3);
        mBinding.txtView.addClickListener(new ReadMoreTextView.ExpandCollapseListener() {
          @Override
          public void onTextClick(boolean readMore) {
            //changed the state of textview when we click on this
            stateReadMoreText.append(getAdapterPosition(), !readMore);
            Log.e("textListAdapter", "bind: " + getAdapterPosition() + " " + !readMore);
          }
        });
      }
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_list, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MyViewHolder) {
      ((MyViewHolder) holder).bind(textList.get(position), position);
    }
  }

  @Override
  public int getItemCount() {
    return textList.size();
  }
}
