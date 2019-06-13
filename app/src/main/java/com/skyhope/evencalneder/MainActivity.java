package com.skyhope.evencalneder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener;
import com.skyhope.eventcalenderlibrary.listener.OnMonthChangedListener;
import com.skyhope.eventcalenderlibrary.model.DayContainerModel;
import com.skyhope.eventcalenderlibrary.model.Event;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnMonthChangedListener {
    private static final String TAG = "CalenderTest";
    private CalenderEvent calenderEvent;
    private MyView myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calenderEvent = findViewById(R.id.calender_event);
        myView = findViewById(R.id.myView);

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Event event = new Event(calendar.getTimeInMillis(), "Test");
        calenderEvent.addEvent(event);

        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {
                Log.d(TAG, dayContainerModel.getDate());
            }
        });

        drawDays();

        calenderEvent.setMonthChangedListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        drawDays();
    }

    private void drawDays() {
        TextView[] days = calenderEvent.getDays();
        Log.d(TAG, days.length + "");

        int[] xPos = new int[days.length];
        int[] yPos = new int[days.length];
        int[] radius = new int[days.length];
        boolean[] isVisible = new boolean[days.length];

        for (int i = 0; i < days.length; i++) {
            int actionBarHeight = 0;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            }

            if (days[i].getTag(R.id.x) != null)
                xPos[i] = (int) days[i].getTag(R.id.x);
            if (days[i].getTag(R.id.y) != null)
                yPos[i] = (int) days[i].getTag(R.id.y);

//            radius[i] = 90;
            radius[i] = getRandomRadius(days[i].getText().toString().trim());
            isVisible[i] = !TextUtils.isEmpty(days[i].getText().toString());

            int width = days[i].getWidth();
            int height = days[i].getHeight();
            xPos[i] = xPos[i] + width / 2;
            yPos[i] = yPos[i] + actionBarHeight / 2 - height / 3 - height;

        }

        myView.drawShape(xPos, yPos, radius, isVisible);

    }

    @Override
    public void onMonthChanged() {
        onWindowFocusChanged(true);
    }

    private int getRandomRadius(String dayText) {

        if (TextUtils.isEmpty(dayText))
            return 0;
        int day = Integer.parseInt(dayText);
        if (day % 2 == 0)
            return 80;
        else if (day % 3 == 0)
            return 90;
        else if (day % 4 == 0)
            return 100;
        else if (day % 5 == 0)
            return 95;
        else if (day % 6 == 0)
            return 85;
        else if (day % 7 == 0)
            return 110;
        else return 80;

    }

}
