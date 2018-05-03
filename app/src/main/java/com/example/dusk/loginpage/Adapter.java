package com.example.dusk.loginpage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        if (currentCard.getMHour().equals(""))
        {
            holder.mTextTwo.setText((currentCard.getMHour()));
        }
        else {
            holder.mTextTwo.setText("Time: " + (currentCard.getMHour()));
        }
        holder.mTextThree.setText((currentCard.getMMinute()));
        holder.mColon.setText((currentCard.getMColon()));
        if (currentCard.getMMonth().equals("")) {
            holder.mMonth.setText(currentCard.getMMonth());
        }
        else {
            holder.mMonth.setText("Date: " + currentCard.getMMonth());
        }
        holder.mDay.setText(currentCard.getMDay());
        holder.mSlash.setText(currentCard.getMSlash());
        holder.optionsToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.modifyEvent.getVisibility() == View.INVISIBLE)
                {
                    holder.deleteEvent.setVisibility(View.VISIBLE);
                    holder.modifyEvent.setVisibility(View.VISIBLE);
                }
                else {
                    holder.deleteEvent.setVisibility(View.INVISIBLE);
                    holder.modifyEvent.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                intent.putExtra("month", currentCard.getMMonth());
                intent.putExtra("day", currentCard.getMDay());
                holder.mContext.startActivity(intent);
            }
        });
        holder.mTextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticUsername staticUsername = new StaticUsername();
                String un = StaticUsername.username;
                Intent intent = new Intent(holder.mContext, EventSummaryActivity.class);
                intent.putExtra("un", un);
                intent.putExtra("Title", currentCard.getTitleText());
                intent.putExtra("Hour", currentCard.getMHour());
                intent.putExtra("Min", currentCard.getMMinute());
                intent.putExtra("Month", currentCard.getMMonth());
                intent.putExtra("Day", currentCard.getMDay());
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
        public TextView mColon;
        public TextView mMonth;
        public TextView mDay;
        public TextView mSlash;
        public Button deleteEvent;
        public Button modifyEvent;
        public ImageButton optionsToggle;
        private Context mContext;

        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);
            mContext = itemView.getContext();
            mTextOne = itemView.findViewById(R.id.titleText);
            mTextTwo = itemView.findViewById(R.id.hourText);
            mTextThree = itemView.findViewById(R.id.minuteText);
            mColon = itemView.findViewById(R.id.colon);
            mMonth = itemView.findViewById(R.id.monthText);
            mDay = itemView.findViewById(R.id.dayText);
            mSlash = itemView.findViewById(R.id.slash);
            deleteEvent = itemView.findViewById(R.id.deleteEventButton);
            modifyEvent = itemView.findViewById(R.id.modifyEventButton);
            optionsToggle = itemView.findViewById(R.id.optionsButton);


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
