package mishmarot.guy.com.timelineunit;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class StepLineLayout extends LinearLayout {

    public ArrayList<StepLineView> stepLineViews;

    int mainColor   = Color.argb(255, 0, 0, 200);
    int secondColor = Color.argb(255, 0, 0, 200);
    int lineColor   = Color.argb(255, 0, 0, 200);

    int markerRadius = 20;
    int lineSize = 3;
    int linePadding = 0;
    StepLineView.LineOrientation lineOrientation = StepLineView.LineOrientation.VERTICAL;

    public StepLineLayout(Context context) {
        this(context, null, 0);
    }

    public StepLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setStepLines(Context context, StepLineView.LineOrientation _lineOrientation, int _numOfItems, int _mainColor, int _secondColor, int _lineColor, int _lineSize, int _markerRadius, int _linePadding){
        this.lineSize = _lineSize;
        this.markerRadius = _markerRadius;
        this.linePadding = _linePadding;
        setStepLines(context, _lineOrientation, _numOfItems, _mainColor, _secondColor, _lineColor);
    }

    public void setStepLines(Context context, StepLineView.LineOrientation _lineOrientation, int _numOfItems, int _mainColor, int _secondColor, int _lineColor){

        // If the user send resource int instead of Color int..
        try {
            _lineColor = ResourcesCompat.getColor(getResources(), _lineColor, null);
        } catch (Exception ignored) { }

        try {
            _mainColor = ResourcesCompat.getColor(getResources(), _mainColor, null);
        } catch (Exception ignored) { }

        try {
            _secondColor = ResourcesCompat.getColor(getResources(), _secondColor, null);
        } catch (Exception ignored) { }


        this.mainColor = _mainColor;
        this.lineColor = _lineColor;
        this.secondColor = _secondColor;
        setStepLines(context, _lineOrientation, _numOfItems);
    }

    private void setStepLines(Context context, StepLineView.LineOrientation _lineOrientation, int numOfItems) {
        stepLineViews = new ArrayList<>();

        this.lineOrientation = _lineOrientation;
        if (_lineOrientation == StepLineView.LineOrientation.HORIZONTAL)
            this.setOrientation(LinearLayout.HORIZONTAL);
        else
            this.setOrientation(LinearLayout.VERTICAL);

        if (numOfItems <=0) {
            return;
        }
        else if (numOfItems == 1) {
            StepLineView stepLineView4 = getStepLineUnit(context, StepLineView.LineType.ONLY_ONE, StepLineView.OrderStatus.ACTIVE);
            stepLineViews.add(stepLineView4);
        }
        else {
            // numOfItems > 1
            StepLineView stepLineView0 = getStepLineUnit(context, StepLineView.LineType.BEGIN, StepLineView.OrderStatus.ACTIVE);
            stepLineViews.add(stepLineView0);

            for (int i = 1; i < numOfItems-1; i++) {
                StepLineView stepLineView1 = getStepLineUnit(context, StepLineView.LineType.NORMAL, StepLineView.OrderStatus.ACTIVE);
                stepLineViews.add(stepLineView1);
            }

            StepLineView stepLineView2 = getStepLineUnit(context, StepLineView.LineType.END, StepLineView.OrderStatus.ACTIVE);
            stepLineViews.add(stepLineView2);
        }

        attachListToView();
    }

    private void attachListToView() {
        for (int i = 0; i < stepLineViews.size(); i++) {
            this.addView(stepLineViews.get(i));
        }
    }

    public void setUnitActive(int activeIndex) {
        if (activeIndex >= 0 &&  activeIndex < stepLineViews.size())
            stepLineViews.get(activeIndex).setOrderStatus(StepLineView.OrderStatus.ACTIVE);
    }

    public void setUnitInactive(int inactiveIndex) {
        if (inactiveIndex >= 0 &&  inactiveIndex < stepLineViews.size())
            stepLineViews.get(inactiveIndex).setOrderStatus(StepLineView.OrderStatus.INACTIVE);
    }

    public void setUnitCompleted(int completedIndex) {
        if (completedIndex >= 0 &&  completedIndex < stepLineViews.size())
            stepLineViews.get(completedIndex).setOrderStatus(StepLineView.OrderStatus.COMPLETED);
    }

    public StepLineView getStepLineUnit(Context context, StepLineView.LineType lineType, StepLineView.OrderStatus orderStatus) {
        // markerSize - default=20
        LinearLayout.LayoutParams linearParams;
        if (lineOrientation == StepLineView.LineOrientation.HORIZONTAL)
            linearParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        else
            linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);

        float density = context.getResources().getDisplayMetrics().density;

        StepLineView stepLineView = new StepLineView(context, null);
        stepLineView.setMarkerSize(markerRadius);
        stepLineView.setMainColor(mainColor);
        stepLineView.setSecondColor(secondColor);
        stepLineView.setLineColor(lineColor);
        stepLineView.initLine(lineType);
        stepLineView.setLayoutParams(linearParams);
        stepLineView.setLineOrientation(lineOrientation);
        stepLineView.setLineSize((int) (lineSize * density));
        stepLineView.setMarkerSize((int) (markerRadius * density));
        stepLineView.setLinePadding(linePadding);
        stepLineView.setDrawables();
        stepLineView.setOrderStatus(orderStatus);

        return stepLineView;
    }
}