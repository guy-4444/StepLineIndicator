package mishmarot.guy.com.timelineunit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

//TimeLineLayout
public class TimeLineLayout extends LinearLayout {

    Context context;
    public ArrayList<TimelineView> timelineViews;
//    int mainColor = Color.argb(255, 0, 0, 255);
    //int mainColor = Color.argb(255, 189, 45, 0);
    //int secondColor = Color.argb(255, 25, 25, 25);
    //int lineColor = Color.argb(255, 25, 25, 100);

    int mainColor = Color.argb(255, 0, 0, 0);
    int secondColor = Color.argb(255, 0, 0, 0);
    int lineColor = Color.argb(255, 0, 0, 0);

    int markerRadius = 20;
    int lineSize = 3;
    int linePadding = 0;
    TimelineView.LineOrientation lineOrientation = TimelineView.LineOrientation.VERTICAL;

    public TimeLineLayout(Context context) {
        this(context, null, 0);
        Log.d("ptttL", "TimeLineLayout");

    }

    public TimeLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d("ptttL", "TimeLineLayout");
    }

    public TimeLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("ptttL", "TimeLineLayout");
        init(context);
    }

    private void init(Context context) {
        Log.d("ptttL", "init");
        this.setOrientation(LinearLayout.HORIZONTAL);
        //lineColor = Color.argb(255, 25, 25, 25);
        //mainColor = Color.argb(255, 0, 0, 255);
        //secondColor = context.getResources().getColor(android.R.color.darker_gray);
    }

    public void setTimeLines(Context context, TimelineView.LineOrientation _lineOrientation, int _numOfItems, int _mainColor, int _secondColor, int _lineColor, int _lineSize, int _markerRadius, int _linePadding){
        this.lineColor = _lineColor;
        this.lineSize = _lineSize;
        this.markerRadius = _markerRadius;
        this.linePadding = _linePadding;
        setTimeLines(context, _lineOrientation, _numOfItems, _mainColor, _secondColor);
    }

    public void setTimeLines(Context context, TimelineView.LineOrientation _lineOrientation, int _numOfItems, int _mainColor, int _secondColor){
        this.mainColor = _mainColor;
        this.secondColor = _secondColor;
        setTimeLines(context, _lineOrientation, _numOfItems);
    }

    private void setTimeLines(Context context, TimelineView.LineOrientation _lineOrientation, int numOfItems) {
        timelineViews = new ArrayList<>();
        TimelineView timelineView;

        this.lineOrientation = _lineOrientation;
        if (_lineOrientation == TimelineView.LineOrientation.HORIZONTAL)
            this.setOrientation(LinearLayout.HORIZONTAL);
        else
            this.setOrientation(LinearLayout.VERTICAL);

        if (numOfItems <=0) {
            return;
        }
        else if (numOfItems == 1) {
            timelineView = getTimeLineUnit(context, TimelineView.LineType.ONLY_ONE, TimelineView.OrderStatus.ACTIVE);
            timelineViews.add(timelineView);
        }
        else if (numOfItems > 1) {
            timelineView = getTimeLineUnit(context, TimelineView.LineType.BEGIN, TimelineView.OrderStatus.ACTIVE);
            timelineViews.add(timelineView);

            for (int i = 1; i < numOfItems-1; i++) {
                timelineView = getTimeLineUnit(context, TimelineView.LineType.NORMAL, TimelineView.OrderStatus.ACTIVE);
                timelineViews.add(timelineView);
            }

            timelineView = getTimeLineUnit(context, TimelineView.LineType.END, TimelineView.OrderStatus.ACTIVE);
            timelineViews.add(timelineView);
        }

        attachListToView();
    }

    private void attachListToView() {
        Log.d("ptttL", "attachListToView");
        for (int i = 0; i < timelineViews.size(); i++) {
            this.addView(timelineViews.get(i));
        }
    }

    public void setUnitActive(int activeIndex) {
        timelineViews.get(activeIndex).setOrderStatus(getContext(), TimelineView.OrderStatus.ACTIVE);
    }

    public void setUnitInactive(int inactiveIndex) {
        timelineViews.get(inactiveIndex).setOrderStatus(getContext(), TimelineView.OrderStatus.INACTIVE);
    }

    public void setUnitCompleted(int completedIndex) {
        timelineViews.get(completedIndex).setOrderStatus(getContext(), TimelineView.OrderStatus.COMPLETED);
    }

    public TimelineView getTimeLineUnit(Context context, int lineType, TimelineView.OrderStatus orderStatus) {
        // markerSize - default=20
        LinearLayout.LayoutParams linearParams;
        if (lineOrientation == TimelineView.LineOrientation.HORIZONTAL)
            linearParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        else
            linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

        float density = context.getResources().getDisplayMetrics().density;

        TimelineView timelineView = new TimelineView(context, null);
        timelineView.setMarkerSize(markerRadius);
        timelineView.setMainColor(mainColor);
        timelineView.setSecondColor(secondColor);
        timelineView.setLineColor(lineColor);
        timelineView.setOrderStatus(context, orderStatus);
        timelineView.initLine(lineType);
        timelineView.setLayoutParams(linearParams);
        timelineView.setLineOrientation(lineOrientation);
        timelineView.setLineSize((int) (lineSize * density));
        timelineView.setMarkerSize((int) (markerRadius * density));
        timelineView.setLinePadding(linePadding);

        return timelineView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("ptttL", "onDraw");
        invalidate();
    }
}