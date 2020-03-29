package com.example.listfragment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends BaseActivity implements AlarmFragment.OnListFragmentInteractionListener {

    AlarmFragment alarmFragment;
    Intent intent;
    TextView alarmTitle,alarmDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTitle = findViewById(R.id.tv_alarmTitle);
        alarmDesc = findViewById(R.id.tv_alarmDesc);
        alarmFragment = new AlarmFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_rv,alarmFragment)
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        alarmFragment.getMyAlarmRecyclerViewAdapter().notifyDataSetChanged();
        AlarmProvider.AlarmItem mItem = AlarmProvider.getLatestActiveAlarm();
        if(mItem!=null){
            LocalDate date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = LocalDate.now();
            }
            String formattedDesc = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(AlarmProvider.COUNT==1){
                    alarmTitle.setText("Next Alarm today");
                    formattedDesc = date.format(DateTimeFormatter.ofPattern("MMM d, E, ")) + mItem.getAlarmTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
                    alarmDesc.setText(formattedDesc);
                }
                else{
                    alarmTitle.setText("Next Alarm in " + String.valueOf(AlarmProvider.COUNT-1) + " days");
                    formattedDesc = date.plusDays(AlarmProvider.COUNT-1).format(DateTimeFormatter.ofPattern("MMM d, E, ")) + mItem.getAlarmTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
                    alarmDesc.setText(formattedDesc);
                }
            }
        }
        else{
            alarmTitle.setText("No Alarms Coming");
            alarmDesc.setText("No Alarms Coming");
        }
    }

    @Override
    public void onListFragmentInteraction(int position) {
        intent = new Intent(getApplicationContext(),DescriptionActivity.class);
        intent.putExtra("Position",position);
        startActivity(intent);
    }

    @Override
    public void onLongPress(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm ?")
                .setMessage("Do you really want to delete this alarm?")
                .setIcon(R.drawable.delete_icon)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlarmProvider.ITEMS.remove(position);
                        onResume();
                    }
                })
                .setNegativeButton("No",null).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSwitchPress(int position,boolean isOn) {
        AlarmProvider.ITEMS.get(position).setOn(isOn);
        onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addClicked(View view) {
        intent = new Intent(getApplicationContext(),DescriptionActivity.class);
        intent.putExtra("Position",AlarmProvider.ITEMS.size());
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void titleClicked(View view) {

    }
}
