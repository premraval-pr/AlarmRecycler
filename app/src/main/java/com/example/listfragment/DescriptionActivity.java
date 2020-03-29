package com.example.listfragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class DescriptionActivity extends AppCompatActivity {

    TextView sun, mon, tue, wed, thur, fri, sat;
    EditText title;
    Switch sound, vibration, snooze;
    TimePicker timePicker;
    int position;
    List<AlarmProvider.AlarmItem> mData;
    AlarmProvider.AlarmItem mItem;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        position = Objects.requireNonNull(getIntent().getIntExtra("Position", 0));
        title = findViewById(R.id.edittext_title);
        sun = findViewById(R.id.tv_edit_Su);
        mon = findViewById(R.id.tv_edit_M);
        tue = findViewById(R.id.tv_edit_Tu);
        wed = findViewById(R.id.tv_edit_W);
        thur = findViewById(R.id.tv_edit_Th);
        fri = findViewById(R.id.tv_edit_F);
        sat = findViewById(R.id.tv_edit_St);
        timePicker = findViewById(R.id.time_picker);
        sound = findViewById(R.id.switch_alarm_sound);
        vibration = findViewById(R.id.switch_alarm_vibration);
        snooze = findViewById(R.id.switch_alarm_snooze);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mData = AlarmProvider.ITEMS;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (position < AlarmProvider.ITEMS.size()) {
                mItem = mData.get(position);
                title.setText(mItem.getAlarmTitle());
                timePicker.setHour(mItem.getAlarmTime().getHour());
                timePicker.setMinute(mItem.getAlarmTime().getMinute());
                if (mItem.isOnSun())
                    sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                if (mItem.isOnMon())
                    mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                if (mItem.isOnTue())
                    tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                if (mItem.isOnWed())
                    wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                if (mItem.isOnThu())
                    thur.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                if (mItem.isOnFri())
                    fri.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                if (mItem.isOnSat())
                    sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                sound.setChecked(mItem.isSoundON());
                vibration.setChecked(mItem.isVibrationON());
                snooze.setChecked(mItem.isSnoozeON());
            }
        }
    }

    public void cancelClicked(View view) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void saveClicked(View view) {
        AlarmProvider.AlarmItem tempItem = mItem;

        if (tempItem != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mItem = new AlarmProvider.AlarmItem(title.getText().toString(), LocalTime.of(timePicker.getHour(), timePicker.getMinute()), tempItem.isOn(),
                        sun.getCurrentTextColor() != Color.WHITE, mon.getCurrentTextColor() != Color.WHITE, tue.getCurrentTextColor() != Color.WHITE,
                        wed.getCurrentTextColor() != Color.WHITE, thur.getCurrentTextColor() != Color.WHITE, fri.getCurrentTextColor() != Color.WHITE,
                        sat.getCurrentTextColor() != Color.WHITE,
                        sound.isChecked(), vibration.isChecked(), snooze.isChecked());
                AlarmProvider.ITEMS.set(position, mItem);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AlarmProvider.addItem(new AlarmProvider.AlarmItem(title.getText().toString(), LocalTime.of(timePicker.getHour(), timePicker.getMinute()), true,
                        sun.getCurrentTextColor() != Color.WHITE, mon.getCurrentTextColor() != Color.WHITE, tue.getCurrentTextColor() != Color.WHITE,
                        wed.getCurrentTextColor() != Color.WHITE, thur.getCurrentTextColor() != Color.WHITE, fri.getCurrentTextColor() != Color.WHITE,
                        sat.getCurrentTextColor() != Color.WHITE,
                        sound.isChecked(), vibration.isChecked(), snooze.isChecked()));
            }
        }
        finish();
    }

    public void dayClicked(View view) {
        view = (TextView) view;
        if (((TextView) view).getCurrentTextColor() == Color.WHITE)
            ((TextView) view).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        else ((TextView) view).setTextColor(Color.WHITE);
    }
}
