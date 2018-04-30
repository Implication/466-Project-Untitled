package com.example.dusk.loginpage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jared Fipps on 2/25/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CardsJava> mList;
    private OnItemClickListener mListener;
    private DbHelper db;
    private String username;

    public Adapter(ArrayList<CardsJava> cards) {
        mList = cards;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        ViewHolder vh = new ViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CardsJava currentCard = mList.get(position);
        db = new DbHelper(holder.mContext);
        holder.mTextOne.setText(currentCard.getTitleText());
        holder.mTextTwo.setText((currentCard.getMHour()));
        holder.mTextThree.setText((currentCard.getMMinute()));
        holder.deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String t = currentCard.getTitleText();
                final String h = currentCard.getMHour();
                final String m = currentCard.getMMinute();
                StaticUsername staticUsername = new StaticUsername();
                String un = StaticUsername.username;
                db.deleteTask(un, t, h, m);
                Intent intent = new Intent(holder.mContext, MainPageActivity.class);
                holder.mContext.startActivity(intent);
            }
        });
        holder.modifyEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticUsername staticUsername = new StaticUsername();
                String un = StaticUsername.username;
                Intent intent = new Intent(holder.mContext, ModifyEvent.class);
                intent.putExtra("username", un);
                intent.putExtra("title", currentCard.getTitleText());
                intent.putExtra("hour", currentCard.getMHour());
                intent.putExtra("minute", currentCard.getMMinute());
                holder.mContext.startActivity(intent);
            }
        });
    }
    
    @Override
    public int getItemCount() {

        return mList.size();
    }




    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextOne;
        public TextView mTextTwo;
        public TextView mTextThree;
        private Button deleteEvent;
        private Button modifyEvent;
        private Context mContext;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mContext = itemView.getContext();
            mTextOne = itemView.findViewById(R.id.titleText);
            mTextTwo = itemView.findViewById(R.id.hourText);
            mTextThree = itemView.findViewById(R.id.minuteText);
            deleteEvent = itemView.findViewById(R.id.deleteEventButton);
            modifyEvent = itemView.findViewById(R.id.modifyEventButton);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
