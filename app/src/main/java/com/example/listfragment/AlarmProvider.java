package com.example.listfragment;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmProvider {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<AlarmItem> ITEMS = new ArrayList<AlarmItem>();

    static {
        /*addItem(new AlarmItem("College",LocalTime.of(3, 0),true,
                false,true,false,
                false,true,false,
                false,false,false,false));
        addItem(new AlarmItem("Gym",LocalTime.of(10, 0),false,
                false,false,false,
                false,true,true,
                true,false,false,false));
        addItem(new AlarmItem("",LocalTime.of(22, 15),true,
                false,false,false,
                false,false,false,
                false,false,false,false));*/
    }

    static void populate(){
        addItem(new AlarmItem("College",LocalTime.of(3, 0),true,
                false,true,false,
                false,true,false,
                false,false,false,false));
        addItem(new AlarmItem("Gym",LocalTime.of(10, 0),false,
                false,false,false,
                false,true,true,
                true,false,false,false));
        addItem(new AlarmItem("",LocalTime.of(22, 15),true,
                false,false,false,
                false,false,false,
                false,false,false,false));
    }

    static void addItem(AlarmItem item) {
        ITEMS.add(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    static AlarmItem getLatestActiveAlarm() {
        List<AlarmItem> allActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<ITEMS.size();i++){
                if(ITEMS.get(i).isOn()) allActiveAlarms.add(ITEMS.get(i));
            }
        }
       /* List<AlarmItem> allSundayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnSun()) allSundayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allSundayActiveAlarms.sort(new TimeSorter());
        List<AlarmItem> allMondayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnMon()) allMondayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allMondayActiveAlarms.sort(new TimeSorter());
        List<AlarmItem> allTuesdayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnTue()) allTuesdayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allTuesdayActiveAlarms.sort(new TimeSorter());
        List<AlarmItem> allWednesdayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnWed()) allWednesdayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allWednesdayActiveAlarms.sort(new TimeSorter());
        List<AlarmItem> allThursdayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnThu()) allThursdayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allThursdayActiveAlarms.sort(new TimeSorter());
        List<AlarmItem> allFridayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnFri()) allFridayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allFridayActiveAlarms.sort(new TimeSorter());
        List<AlarmItem> allSaturdayActiveAlarms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for(int i=0;i<allActiveAlarms.size();i++){
                if(allActiveAlarms.get(i).isOnSat()) allSaturdayActiveAlarms.add(allActiveAlarms.get(i));
            }
        }
        allSaturdayActiveAlarms.sort(new TimeSorter());
        int day = Calendar.DAY_OF_WEEK;
        day = 1;
        Log.d("Day",String.valueOf(day));
        List<AlarmItem> mainSort = new ArrayList<>();
        switch (day){
            case Calendar.SUNDAY: if(allSundayActiveAlarms.size()>0) mainSort.add(allSundayActiveAlarms.get(0));
            case Calendar.MONDAY: if(allMondayActiveAlarms.size()>0) mainSort.add(allMondayActiveAlarms.get(0));
            case Calendar.TUESDAY: if(allTuesdayActiveAlarms.size()>0) mainSort.add(allTuesdayActiveAlarms.get(0));
            case Calendar.WEDNESDAY: if(allWednesdayActiveAlarms.size()>0) mainSort.add(allWednesdayActiveAlarms.get(0));
            case Calendar.THURSDAY: if(allThursdayActiveAlarms.size()>0) mainSort.add(allThursdayActiveAlarms.get(0));
            case Calendar.FRIDAY: if(allFridayActiveAlarms.size()>0) mainSort.add(allFridayActiveAlarms.get(0));
            case Calendar.SATURDAY: if(allSaturdayActiveAlarms.size()>0) mainSort.add(allSaturdayActiveAlarms.get(0));
        }
        if(mainSort.size()>0) return mainSort.get(mainSort.size()-1);*/
        List<AlarmItem> currentDay = new ArrayList<>();
        int day = Calendar.getInstance().DAY_OF_WEEK;
        AlarmItem nextItem = null;

        while(true) {
            switch (day) {
                case Calendar.SUNDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnSun()) currentDay.add(nItem);
                    }
                    break;
                case Calendar.MONDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnMon()) currentDay.add(nItem);
                    }
                    break;
                case Calendar.TUESDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnTue()) currentDay.add(nItem);
                    }
                    break;
                case Calendar.WEDNESDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnWed()) currentDay.add(nItem);
                    }
                    break;
                case Calendar.THURSDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnThu()) currentDay.add(nItem);
                    }
                    break;
                case Calendar.FRIDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnFri()) currentDay.add(nItem);
                    }
                    break;
                case Calendar.SATURDAY:
                    for (AlarmItem nItem : allActiveAlarms) {
                        if (nItem.isOnSat()) currentDay.add(nItem);
                    }
                    break;
            }
            if(!currentDay.isEmpty()){
                for(AlarmItem mItem : currentDay){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if(day==Calendar.DAY_OF_WEEK) {
                            if ((mItem.getAlarmTime().compareTo(LocalTime.now()) > 0)) {
                                if (nextItem == null) nextItem = mItem;
                                else {
                                    if ((nextItem.getAlarmTime().compareTo(mItem.getAlarmTime()) > 0)) {
                                        nextItem = mItem;
                                    }
                                }
                            }
                        }
                        else{
                            if (nextItem == null) nextItem = mItem;
                            else {
                                if ((nextItem.getAlarmTime().compareTo(mItem.getAlarmTime()) > 0)) {
                                    nextItem = mItem;
                                }
                            }
                        }
                    }
                }
            }
            if(nextItem!=null){
                return nextItem;
            }
            else {
                if(day==7) day=1;
                else day++;
            }
        }

    }





    /**
     * A dummy item representing a piece of content.
     */
    public static class AlarmItem{
        private String alarmTitle;
        private LocalTime alarmTime;
        private boolean isOn,onSun,onMon,onTue,onWed,onThu,onFri,onSat,soundON,vibrationON,snoozeON;

        public AlarmItem() {
        }

        public String getAlarmTitle() {
            return alarmTitle;
        }

        public void setAlarmTitle(String alarmTitle) {
            this.alarmTitle = alarmTitle;
        }

        public LocalTime getAlarmTime() {
            return alarmTime;
        }

        public void setAlarmTime(LocalTime alarmTime) {
            this.alarmTime = alarmTime;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }

        public boolean isOnSun() {
            return onSun;
        }

        public void setOnSun(boolean onSun) {
            this.onSun = onSun;
        }

        public boolean isOnMon() {
            return onMon;
        }

        public void setOnMon(boolean onMon) {
        }

        public boolean isOnTue() {
            return onTue;
        }

        public void setOnTue(boolean onTue) {
            this.onTue = onTue;
        }

        public boolean isOnWed() {
            return onWed;
        }

        public void setOnWed(boolean onWed) {
            this.onWed = onWed;
        }

        public boolean isOnThu() {
            return onThu;
        }

        public void setOnThu(boolean onThu) {
            this.onThu = onThu;
        }

        public boolean isOnFri() {
            return onFri;
        }

        public void setOnFri(boolean onFri) {
            this.onFri = onFri;
        }

        public boolean isOnSat() {
            return onSat;
        }

        public void setOnSat(boolean onSat) {
            this.onSat = onSat;
        }
        public boolean isSoundON() {
            return soundON;
        }

        public void setSoundON(boolean soundON) {
            this.soundON = soundON;
        }

        public boolean isVibrationON() {
            return vibrationON;
        }

        public void setVibrationON(boolean vibrationON) {
            this.vibrationON = vibrationON;
        }

        public boolean isSnoozeON() {
            return snoozeON;
        }

        public void setSnoozeON(boolean snoozeON) {
            this.snoozeON = snoozeON;
        }

        public AlarmItem(String alarmTitle, LocalTime alarmTime, boolean isOn,
                         boolean onSun, boolean onMon, boolean onTue,
                         boolean onWed, boolean onThu, boolean onFri,
                         boolean onSat,
                         boolean onSound, boolean onVibration, boolean onSnooze) {
            this.alarmTitle = alarmTitle;
            this.alarmTime = alarmTime;
            this.isOn = isOn;
            this.onSun = onSun;
            this.onMon = onMon;
            this.onTue = onTue;
            this.onWed = onWed;
            this.onThu = onThu;
            this.onFri = onFri;
            this.onSat = onSat;
            this.soundON = onSound;
            this.vibrationON = onVibration;
            this.snoozeON = onSnooze;
        }

        @Override
        public String toString() {
            return "AlarmItem{" +
                    "alarmTitle='" + alarmTitle + '\'' +
                    ", alarmTime='" + alarmTime + '\'' +
                    ", isOn=" + isOn +
                    '}';
        }

    }
}
