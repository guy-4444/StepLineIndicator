package mishmarot.guy.com.testtime;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mishmarot.guy.com.timelineunit.StepLineLayout;
import mishmarot.guy.com.timelineunit.StepLineView;

public class MainActivity extends AppCompatActivity {

    int index = -1;
    StepLineLayout stepLayout_1;
    StepLineLayout stepLayout_2;
    StepLineLayout stepLayout_3;
    StepLineLayout stepLayout_4;
    StepLineLayout stepLayout_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepLayout_1 = (StepLineLayout) findViewById(R.id.stepLayout_1);
        stepLayout_2 = (StepLineLayout) findViewById(R.id.stepLayout_2);
        stepLayout_3 = (StepLineLayout) findViewById(R.id.stepLayout_3);
        stepLayout_4 = (StepLineLayout) findViewById(R.id.stepLayout_4);
        stepLayout_5 = (StepLineLayout) findViewById(R.id.stepLayout_5);

        stepLayout_1.setStepLines(this, StepLineView.LineOrientation.HORIZONTAL, 7, R.color.colorPrimary, android.R.color.darker_gray, R.color.colorPrimary);
        stepLayout_2.setStepLines(this, StepLineView.LineOrientation.VERTICAL, 12, Color.argb(123, 0, 0, 255), Color.argb(123, 0, 255, 0), Color.argb(123, 0, 255, 0), 3, 20, 10);
        stepLayout_3.setStepLines(this, StepLineView.LineOrientation.VERTICAL, 5, R.color.orange, R.color.yellow, R.color.orange);
        stepLayout_4.setStepLines(this, StepLineView.LineOrientation.VERTICAL, 6, Color.argb(200, 255, 0, 0), Color.argb(100, 255, 0, 0), Color.argb(123, 200, 200, 0), 4, 25, 0);
        stepLayout_5.setStepLines(this, StepLineView.LineOrientation.VERTICAL, 40, R.color.colorPrimary, android.R.color.darker_gray, Color.argb(123, 200, 200, 0), 4, 25, 0);



        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepLayout_1.setUnitCompleted(index);
                stepLayout_2.setUnitCompleted(index);
                stepLayout_3.setUnitCompleted(index);
                stepLayout_4.setUnitCompleted(index);
                stepLayout_5.setUnitCompleted(index);
                index++;
                stepLayout_1.setUnitInactive(index);
                stepLayout_2.setUnitInactive(index);
                stepLayout_3.setUnitInactive(index);
                stepLayout_4.setUnitInactive(index);
                stepLayout_5.setUnitInactive(index);

            }
        });

    }
}
