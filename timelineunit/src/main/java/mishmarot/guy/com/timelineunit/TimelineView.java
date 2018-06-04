package mishmarot.guy.com.timelineunit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimelineView extends View {

    private Drawable mMarker;
    private Drawable mStartLine;
    private Drawable mEndLine;
    private int mMarkerSize;
    private int mLineSize;
    private int mLineOrientation;
    private int mLinePadding;
    private boolean mMarkerInCenter;
    private OrderStatus mOrderStatus = OrderStatus.ACTIVE;

    private int mainColor = android.R.color.holo_blue_dark;
    private int secondColor = android.R.color.darker_gray;
    private int lineColor = android.R.color.darker_gray;

    private Rect mBounds;
    private Context mContext;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.timeline_style);
        mMarker = typedArray.getDrawable(R.styleable.timeline_style_marker);
        mStartLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        mEndLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        mMarkerSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_markerSize, dpToPx(20, mContext));
        mLineSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_lineSize, dpToPx(2, mContext));
        mLineOrientation = typedArray.getInt(R.styleable.timeline_style_lineOrientation, 1);
        mLinePadding = typedArray.getDimensionPixelSize(R.styleable.timeline_style_linePadding, 0);
        mMarkerInCenter = typedArray.getBoolean(R.styleable.timeline_style_markerInCenter, true);
        typedArray.recycle();

        if(mMarker == null) {
            mMarker = mContext.getResources().getDrawable(R.drawable.marker);
        }

        if(mStartLine == null && mEndLine == null) {
            //mStartLine = new ColorDrawable(mContext.getResources().getColor(android.R.color.darker_gray));
            //mEndLine = new ColorDrawable(mContext.getResources().getColor(android.R.color.darker_gray));

            mStartLine = new ColorDrawable(mContext.getResources().getColor(lineColor));
            mEndLine = new ColorDrawable(mContext.getResources().getColor(lineColor));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Width measurements of the width and height and the inside view of child controls
        int w = mMarkerSize + getPaddingLeft() + getPaddingRight();
        int h = mMarkerSize + getPaddingTop() + getPaddingBottom();

        // Width and height to determine the final view through a systematic approach to decision-making
        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
        initDrawable();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // When the view is displayed when the callback
        // Positioning Drawable coordinates, then draw
        initDrawable();
    }

    private void initDrawable() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        int width = getWidth();// Width of current custom view
        int height = getHeight();

        int cWidth = width - pLeft - pRight;// Circle width
        int cHeight = height - pTop - pBottom;

        int markSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));

        if(mMarkerInCenter) { //Marker in center is true

            if(mMarker != null) {
                mMarker.setBounds((width/2) - (markSize/2),(height/2) - (markSize/2), (width/2) + (markSize/2),(height/2) + (markSize/2));
                mBounds = mMarker.getBounds();
            }

        } else { //Marker in center is false

            if(mMarker != null) {
                mMarker.setBounds(pLeft,pTop,pLeft + markSize,pTop + markSize);
                mBounds = mMarker.getBounds();
            }
        }

        int centerX = mBounds.centerX();
        int lineLeft = centerX - (mLineSize >> 1);

        if(mLineOrientation==0) {

            //Horizontal Line
            if(mStartLine != null) {
                mStartLine.setBounds(0, pTop + (mBounds.height()/2) + (height/2) - (markSize/2), mBounds.left - mLinePadding, (mBounds.height()/2) + pTop + mLineSize + (height/2) - (markSize/2));
            }

            if(mEndLine != null) {
                mEndLine.setBounds(mBounds.right + mLinePadding, pTop + (mBounds.height()/2) + (height/2) - (markSize/2), width, (mBounds.height()/2) + pTop + mLineSize + (height/2) - (markSize/2));
            }
        } else {

            //Vertical Line
            if(mStartLine != null) {
                mStartLine.setBounds(lineLeft, 0, mLineSize + lineLeft, mBounds.top - mLinePadding);
            }

            if(mEndLine != null) {
                mEndLine.setBounds(lineLeft, mBounds.bottom + mLinePadding, mLineSize + lineLeft, height);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mMarker != null) {
            mMarker.draw(canvas);
        }

        if(mStartLine != null) {
            mStartLine.draw(canvas);
        }

        if(mEndLine != null) {
            mEndLine.draw(canvas);
        }
    }

    /**
     * Sets marker.
     *
     * @param marker will set marker drawable to timeline
     */
    public void setMarker(Drawable marker) {
        mMarker = marker;
        initDrawable();
    }

    /**
     * Sets marker.
     *
     * @param marker will set marker drawable to timeline
     * @param color  with a color
     */
    public void setMarker(Drawable marker, int color) {
        mMarker = marker;
        mMarker.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        initDrawable();
    }

    /**
     * Sets marker color.
     *
     * @param color the color
     */
    public void setMarkerColor(int color) {
        mMarker.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        initDrawable();
    }

    /**
     * Sets start line.
     *
     * @param color    the color
     * @param viewType the view type
     */
    public void setStartLine(int color, int viewType) {
        mStartLine = new ColorDrawable(color);
        initLine(viewType);
    }

    /**
     * Sets end line.
     *
     * @param color    the color
     * @param viewType the view type
     */
    public void setEndLine(int color, int viewType) {
        mEndLine = new ColorDrawable(color);
        initLine(viewType);
    }

    /**
     * Sets marker size.
     *
     * @param markerSize the marker size
     */
    public void setMarkerSize(int markerSize) {
        mMarkerSize = markerSize;
        initDrawable();
    }

    /**
     * Sets line size.
     *
     * @param lineSize the line size
     */
    public void setLineSize(int lineSize) {
        mLineSize = lineSize;
        initDrawable();
    }

    /**
     * Sets line padding
     * @param padding the line padding
     */
    public void setLinePadding(int padding) {
        mLinePadding = padding;
        initDrawable();
    }

    private void setStartLine(Drawable startLine) {
        mStartLine = startLine;
        initDrawable();
    }

    private void setEndLine(Drawable endLine) {
        mEndLine = endLine;
        initDrawable();
    }

    /**
     * Init line.
     *
     * @param viewType the view type
     */
    public void initLine(int viewType) {
        if(viewType == LineType.BEGIN) {
            setStartLine(null);
        } else if(viewType == LineType.END) {
            setEndLine(null);
        } else if(viewType == LineType.ONLY_ONE) {
            setStartLine(null);
            setEndLine(null);
        }
        initDrawable();
    }

    /**
     * Gets timeline view type.
     *
     * @param position   the position
     * @param total_size the total size
     * @return the time line view type
     */
    public static int getTimeLineViewType(int position, int total_size) {
        if(total_size == 1) {
            return LineType.ONLY_ONE;
        } else if(position == 0) {
            return LineType.BEGIN;
        } else if(position == total_size - 1) {
            return LineType.END;
        } else {
            return LineType.NORMAL;
        }
    }

    public static int dpToPx(float dp, Context context) {
        return dpToPx(dp, context.getResources());
    }

    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public static Drawable getDrawable(Context context, int drawableResId) {
        return VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
    }

    public static Drawable getDrawable(Context context, int drawableResId, int colorFilter) {
        Drawable drawable = getDrawable(context, drawableResId);
        try {
            drawable.setColorFilter(ContextCompat.getColor(context, colorFilter), PorterDuff.Mode.SRC_IN);
        }
        catch (Exception ex) {
            drawable.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN);
        }
        return drawable;
    }

    public enum LineOrientation {
        VERTICAL,
        HORIZONTAL;
    }

    public class LineType {
        public static final int NORMAL = 0;
        public static final int BEGIN = 1;
        public static final int END = 2;
        public static final int ONLY_ONE = 3;
    }

    public enum OrderStatus {
        COMPLETED,
        ACTIVE,
        INACTIVE;
    }

    public void setMainColor(int _mainColor) {
        this.mainColor = _mainColor;
    }

    public void setSecondColor(int _secondColor) {
        this.secondColor = _secondColor;
    }

    public void setLineColor(int _lineColor) {
        this.lineColor = _lineColor;
        mStartLine = new ColorDrawable(mContext.getResources().getColor(lineColor));
        mEndLine = new ColorDrawable(mContext.getResources().getColor(lineColor));
    }

    public void setLineOrientation(LineOrientation _mLineOrientation) {
        if (_mLineOrientation == LineOrientation.HORIZONTAL)
            this.mLineOrientation = 0;
        else
            this.mLineOrientation = 1;
    }

    public void setOrderStatus(Context context, OrderStatus orderStatus) {
        this.mOrderStatus = orderStatus;
        if(orderStatus == TimelineView.OrderStatus.INACTIVE) {
            this.setMarker(TimelineView.getDrawable(context, R.drawable.ic_marker_inactive, mainColor));
        } else if(orderStatus == TimelineView.OrderStatus.ACTIVE) {
            this.setMarker(TimelineView.getDrawable(context, R.drawable.ic_marker_active, secondColor));
        } else {
            this.setMarker(TimelineView.getDrawable(context, R.drawable.ic_marker_completed, mainColor));
        }

        initDrawable();
    }
}
