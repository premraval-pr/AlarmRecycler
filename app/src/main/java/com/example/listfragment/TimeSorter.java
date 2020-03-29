package com.example.listfragment;

import java.util.Comparator;

public class TimeSorter implements Comparator<AlarmProvider.AlarmItem> {
    @Override
    public int compare(AlarmProvider.AlarmItem o1, AlarmProvider.AlarmItem o2) {
        return o1.getAlarmTime().compareTo(o2.getAlarmTime());
    }
}
