package com.example.listfragment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.listfragment.AlarmFragment.OnListFragmentInteractionListener;
import com.example.listfragment.AlarmProvider.AlarmItem;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AlarmItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAlarmRecyclerViewAdapter extends RecyclerView.Adapter<MyAlarmRecyclerViewAdapter.ViewHolder> {

    private final List<AlarmItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    MyAlarmRecyclerViewAdapter(List<AlarmItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_alarm, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.textviewTitle.setText(holder.mItem.getAlarmTitle());
        holder.textviewTime.setText(holder.mItem.getAlarmTime().format(DateTimeFormatter.ofPattern("hh:mm a")));
        holder.isOn.setChecked(holder.mItem.isOn());

        if(holder.mItem.isOnSun()) holder.onSun.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onSun.setTextColor(Color.WHITE);

        if(holder.mItem.isOnMon()) holder.onMon.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onMon.setTextColor(Color.WHITE);

        if(holder.mItem.isOnTue()) holder.onTue.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onTue.setTextColor(Color.WHITE);

        if(holder.mItem.isOnWed()) holder.onWed.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onWed.setTextColor(Color.WHITE);

        if(holder.mItem.isOnThu()) holder.onThu.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onThu.setTextColor(Color.WHITE);

        if(holder.mItem.isOnFri()) holder.onFri.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onFri.setTextColor(Color.WHITE);

        if(holder.mItem.isOnSat()) holder.onSat.setTextColor(ContextCompat.getColor(holder.mView.getContext(),R.color.colorPrimary));
        else holder.onSat.setTextColor(Color.WHITE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(position);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    mListener.onLongPress(position);
                }
                return true;
            }
        });
        holder.isOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (null != mListener) {
                    mListener.onSwitchPress(position,holder.isOn.isChecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView textviewTitle,textviewTime,onSun,onMon,onTue,onWed,onThu,onFri,onSat;
        final Switch isOn;
        AlarmItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            textviewTitle = view.findViewById(R.id.tv_listTitle);
            textviewTime = view.findViewById(R.id.tv_listTime);
            isOn = view.findViewById(R.id.switch_on_off);
            onSun = view.findViewById(R.id.tv_Su);
            onMon = view.findViewById(R.id.tv_M);
            onTue = view.findViewById(R.id.tv_Tu);
            onWed = view.findViewById(R.id.tv_W);
            onThu  = view.findViewById(R.id.tv_Th);
            onFri = view.findViewById(R.id.tv_F);
            onSat = view.findViewById(R.id.tv_St);
        }

        /*@Override
        public String toString() {
            return super.toString() + " '" + textviewTime.getText() + "'";
        }*/
    }
}
