package mishmarot.guy.com.testtime;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import mishmarot.guy.com.timelineunit.TimeLineLayout;
import mishmarot.guy.com.timelineunit.TimelineView;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    TimeLineLayout timeLayout_1;
    TimeLineLayout timeLayout_2;
    TimeLineLayout timeLayout_3;
    TimeLineLayout timeLayout_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeLayout_1 = (TimeLineLayout)findViewById(R.id.timeLayout_1);
        timeLayout_2 = (TimeLineLayout)findViewById(R.id.timeLayout_2);
        timeLayout_3 = (TimeLineLayout)findViewById(R.id.timeLayout_3);
        timeLayout_4 = (TimeLineLayout)findViewById(R.id.timeLayout_4);

        timeLayout_1.setTimeLines(this, TimelineView.LineOrientation.HORIZONTAL, 5, R.color.colorPrimary, android.R.color.darker_gray);
        timeLayout_2.setTimeLines(this, TimelineView.LineOrientation.VERTICAL, 12, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 3, 20, 10);
        timeLayout_3.setTimeLines(this, TimelineView.LineOrientation.VERTICAL, 5, R.color.colorPrimary, android.R.color.darker_gray);
        timeLayout_4.setTimeLines(this, TimelineView.LineOrientation.VERTICAL, 6, Color.argb(123, 250, 0, 0), Color.argb(123, 0, 255, 0), Color.argb(123, 200, 200, 0), 4, 25, 0);

        timeLayout_1.setUnitCompleted(0);
        timeLayout_1.setUnitActive(1);
        timeLayout_1.setUnitCompleted(2);
        timeLayout_1.setUnitInactive(3);

        timeLayout_2.setUnitCompleted(0);
        timeLayout_2.setUnitCompleted(1);
        timeLayout_2.setUnitCompleted(2);
        timeLayout_2.setUnitCompleted(3);
        timeLayout_2.setUnitCompleted(4);
        timeLayout_2.setUnitCompleted(5);
        timeLayout_2.setUnitCompleted(6);
        timeLayout_2.setUnitInactive(7);

        timeLayout_3.setUnitCompleted(0);
        timeLayout_3.setUnitCompleted(1);
        timeLayout_3.setUnitCompleted(2);
        timeLayout_3.setUnitInactive(3);

//        timeLayout_4.setUnitCompleted(0);
//        timeLayout_4.setUnitCompleted(1);
//        timeLayout_4.setUnitCompleted(2);
//        timeLayout_4.setUnitCompleted(3);
//        timeLayout_4.setUnitCompleted(4);
//        timeLayout_4.setUnitCompleted(5);
//        timeLayout_4.setUnitCompleted(6);
//        timeLayout_4.setUnitInactive(7);

        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeLayout_4.setUnitCompleted(index);
                timeLayout_4.setUnitInactive(++index);
            }
        });

        ((Button) findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pttt", "Color=" + timeLayout_4.timelineViews.get(0).mainColor);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //timeLayout_4.invalidate();
    }
}
