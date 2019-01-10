package com.example.risa.bucketlist;

import android.arch.paging.PagedListAdapter;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class BucketItemAdapter extends PagedListAdapter<BucketItem, BucketItemAdapter.BucketItemViewHolder> {

    private ClickListener clickListener;

    BucketItemAdapter( ClickListener clickListener ) {
        super(DIFF_CALLBACK);

        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void OnLongClick (BucketItem bucketItem);
        void OnClick (BucketItem bucketItem);
    }

    @NonNull
    @Override
    public BucketItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bucketlist, parent, false);

        return new BucketItemViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull BucketItemViewHolder holder, int position) {
        BucketItem bucketItem = getItem( position );

        if( bucketItem != null ) {
            holder.bindTo( bucketItem );
        }
    }

    private static final DiffUtil.ItemCallback<BucketItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<BucketItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull BucketItem oldBucketItem, @NonNull BucketItem newBucketItem ) {
            return oldBucketItem.getId() == newBucketItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull BucketItem oldBucketItem, @NonNull BucketItem newBucketItem) {
            return oldBucketItem.equals( newBucketItem );
        }
    };

    public class BucketItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private BucketItem bucketItem;

        BucketItemViewHolder( View itemView ) {
            super(itemView);

            itemView.setOnLongClickListener( this );
            itemView.setOnClickListener( this );
        }

        void bindTo( final BucketItem bucketItem ) {
            this.bucketItem = bucketItem;

            TextView viewName = itemView.findViewById( R.id.text_name );
            TextView viewDescription = itemView.findViewById( R.id.text_description );
            CheckBox checkboxCompleted = itemView.findViewById( R.id.checkbox_completed );

            viewName.setText( bucketItem.getName() );
            viewDescription.setText( bucketItem.getDescription() );
            checkboxCompleted.setChecked( bucketItem.getCompleted() == 1 );

            if( bucketItem.getCompleted() == 1 ) {
                viewName.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG );
                viewDescription.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG );
            } else {
                viewName.setPaintFlags( 0 );
                viewDescription.setPaintFlags( 0 );
            }
        }

        @Override
        public void onClick(View view) {
            clickListener.OnClick( bucketItem );
        }

        public BucketItem getBucketItem() {
            return bucketItem;
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.OnLongClick( bucketItem );
            return true;
        }
    }
}

