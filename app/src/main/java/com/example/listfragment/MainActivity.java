package com.example.listfragment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements AlarmFragment.OnListFragmentInteractionListener {

    AlarmFragment alarmFragment;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmFragment = new AlarmFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_rv,alarmFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //alarmFragment.getMyAlarmRecyclerViewAdapter().notifyDataSetChanged();
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addClicked(View view) {
        intent = new Intent(getApplicationContext(),DescriptionActivity.class);
        intent.putExtra("Position",AlarmProvider.ITEMS.size());
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void titleClicked(View view) {
        AlarmProvider.AlarmItem mItem = AlarmProvider.getLatestActiveAlarm();
        if(mItem!=null){
            Toast.makeText(getApplicationContext(),mItem.toString(),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"No Alarms",Toast.LENGTH_SHORT).show();
        }
    }
}
