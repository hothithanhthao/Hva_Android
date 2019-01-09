package com.example.risa.gamebacklog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.util.List;

public class LoggerAdapter extends RecyclerView.Adapter<LoggerAdapter.ViewHolder>{
    private List<Logger> mLoggers;
    final private LoggerClickListener mLoggerClickListener;
    private Context mContext;

    public interface LoggerClickListener{
        void reminderOnClick (int i);
    }


    public LoggerAdapter(List<Logger> mLoggers, LoggerClickListener mLoggerClickListener) {
        this.mLoggers = mLoggers;
        this.mLoggerClickListener = mLoggerClickListener;
        //this.mContext = mContext;
    }

    @NonNull
    @Override
    public LoggerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_games, null);
// Return a new holder instance
        LoggerAdapter.ViewHolder viewHolder = new LoggerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoggerAdapter.ViewHolder holder, int position) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        Logger logger =  mLoggers.get(position);
        holder.mTitletext.setText(logger.mgameText);
        holder.mPlatformtext.setText(logger.mplatText);
        holder.mGameStatus.setText(logger.mstatusText);
        holder.mdate.setText(strDate);
    }

    @Override
    public int getItemCount() {
        return mLoggers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitletext;
        public TextView mPlatformtext;
        public TextView mGameStatus;
        public TextView mdate;

        public ViewHolder(View itemView) {

            super(itemView);
            mTitletext =  itemView.findViewById(R.id.titileName);
            mPlatformtext = itemView.findViewById(R.id.platfromName);
            mGameStatus = itemView.findViewById(R.id.statusName);
            mdate = itemView.findViewById(R.id.Date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mLoggerClickListener.reminderOnClick(clickedPosition);
        }

    }

    public void swapList (List<Logger> newList) {


        mLoggers = newList;

        if (newList != null) {

            // Force the RecyclerView to refresh

            this.notifyDataSetChanged();

        }

    }
}
