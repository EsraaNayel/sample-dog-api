package com.esraanayel.sampledogapi.mainlist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.esraanayel.sampledogapi.R;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Esraa on 6/6/2018.
 */

public class CategoryListingRecyclerViewAdapter extends RecyclerView.Adapter<CategoryListingRecyclerViewAdapter.ViewHolder> implements Filterable {
    List<String> mDataset;
    View.OnClickListener mOnClickListener;


    List<CategoryModel> list;
    private List<CategoryModel> contactListFiltered;
    private CategoryListingRecyclerViewAdapter listener;


    public CategoryListingRecyclerViewAdapter(List<String> items, View.OnClickListener onClickListener) {
        this.mDataset = items;
        this.mOnClickListener = onClickListener;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_row_item, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mDataset.get(position);

//        ImageLoader.loadImage(item,holder.categoryImageView);
        holder.categoryTitleTextView.setText(item);
//        Glide.with(imageView.getContext()).asDrawable().load(imageUrl).into(imageView);

//        holder.categoryTitleTextView.setText(item.getCategoryTitle());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void clearDataset() {
        if (mDataset != null) {
            mDataset.clear();
        }
        notifyDataSetChanged();
    }

    public void setDataset(List<String> dataset) {
        this.mDataset = dataset;
    }

    public void addAll(List<String> items) {
        if (mDataset == null) {
            mDataset = items;

        } else {
            mDataset.addAll(items);
        }
        notifyItemRangeInserted(mDataset.size() - items.size(), items.size());
    }

    public String getItemByPosition(int position) {
        return mDataset.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_image_view)
        ImageView categoryImageView;
        @BindView(R.id.category_text_view)
        TextView categoryTitleTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = list;
                } else {
                    List<CategoryModel> filteredList = new ArrayList<>();
                    ArrayList arrayList = new ArrayList<String>();
                    for (int i = 0; i < mDataset.size(); i++) {
                        String rowItem = mDataset.get(i);
                        if (rowItem.toLowerCase().contains(charString.toLowerCase())) {
                            arrayList.add(rowItem);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<CategoryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        }

                ;
    }
}
