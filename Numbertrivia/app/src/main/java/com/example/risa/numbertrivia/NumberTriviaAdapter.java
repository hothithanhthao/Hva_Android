package com.example.risa.numbertrivia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.TextView;


public class NumberTriviaAdapter extends RecyclerView.Adapter<NumberTriviaAdapter.ViewHolder> {

    private List<NumberTriviaItem> values;

    public NumberTriviaAdapter(List<NumberTriviaItem> triviaDataset) {
        values = triviaDataset;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.number_text, parent, false);
        } else {
            view = inflater.inflate(R.layout.number_text_opposite, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final int number = values.get(position).getNumber();
        viewHolder.number.setText(String.valueOf(number));
        final String text = values.get(position).getText();
        viewHolder.description.setText(text);

    }

    @Override
    public int getItemViewType(int position) {
        return position %2*2;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView number;
        public TextView description;
        public View view;

        public ViewHolder(View itemView){
            super(itemView);
            view = itemView;
            number = (TextView) view.findViewById(R.id.number1);
            description = (TextView) view.findViewById(R.id.text1);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
