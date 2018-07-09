package org.greenleaf.easyandroid.core.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import org.greenleaf.easyandroid.core.R;

/**
 * 严格平分的网格布局
 * author: wangyonghua
 * version: V1.0
 * date: 2018/6/13
 * time: 16:10
 */

public class PureGridLayout extends ViewGroup {

    private static final int DEFAULT_COUNT = 5;
    private static final int DEFAULT_GAP = 0;

    private int mColumnCount = DEFAULT_COUNT;
    private int mRowCount = DEFAULT_COUNT;
    private int mGapSize = DEFAULT_GAP;
    private boolean mGapIncludeOutline = false;

    public enum AdjustStrategy {
        TO_MOST, TO_LEAST, LAST_ADJUST
    }

    private AdjustStrategy mAdjustStrategy = AdjustStrategy.LAST_ADJUST;

    public PureGridLayout(Context context) {
        this(context, null);
    }

    public PureGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PureGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PureGridLayout);
        try {
            mRowCount = a.getInt(R.styleable.PureGridLayout_row_count, DEFAULT_COUNT);
            mColumnCount = a.getInt(R.styleable.PureGridLayout_column_count, DEFAULT_COUNT);
            mGapSize = a.getDimensionPixelSize(R.styleable.PureGridLayout_gap, DEFAULT_COUNT);
            mGapIncludeOutline = a.getBoolean(R.styleable.PureGridLayout_gap_include_outline, false);
            int strategy = a.getInt(R.styleable.PureGridLayout_adjust_strategy, AdjustStrategy.LAST_ADJUST.ordinal());
            if (strategy == AdjustStrategy.LAST_ADJUST.ordinal()) {
                mAdjustStrategy = AdjustStrategy.LAST_ADJUST;
            } else if (strategy == AdjustStrategy.TO_MOST.ordinal()) {
                mAdjustStrategy = AdjustStrategy.TO_MOST;
            } else if (strategy == AdjustStrategy.TO_LEAST.ordinal()){
                mAdjustStrategy = AdjustStrategy.TO_LEAST;
            } else {
                mAdjustStrategy = AdjustStrategy.LAST_ADJUST;
            }
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int totalHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        int totalWidthGapSize = mGapSize * (mGapIncludeOutline ? (mColumnCount + 1) : (mColumnCount - 1));
        int totalHeightGapSize = mGapSize * (mGapIncludeOutline ? (mRowCount + 1) : (mRowCount - 1));

        int cellHeight = floorDiv(totalHeight - totalHeightGapSize, mRowCount);
        int cellWidth = floorDiv(totalWidth - totalWidthGapSize, mColumnCount);

        final int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {

            if (i >= mRowCount * mColumnCount) {
                return;
            }
            View c = getChildAt(i);

            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(cellWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(cellHeight, MeasureSpec.EXACTLY);

            if ((totalHeight - totalHeightGapSize) % mRowCount != 0) {
                switch (mAdjustStrategy) {
                    case TO_LEAST:
                        break;
                    case TO_MOST:
                        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(cellHeight + 1, MeasureSpec.EXACTLY);
                        break;
                    case LAST_ADJUST:
                        if (i / mColumnCount == (mRowCount - 1)) {
                            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(totalHeight - totalHeightGapSize
                                    - (mRowCount - 1) * cellHeight, MeasureSpec.EXACTLY);
                        }
                        break;
                }
            }

            if ((totalWidth - totalWidthGapSize) % mColumnCount != 0) {
                switch (mAdjustStrategy) {
                    case TO_LEAST:
                        break;
                    case TO_MOST:
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(cellWidth + 1, MeasureSpec.EXACTLY);
                        break;
                    case LAST_ADJUST:
                        if ((i + 1) % mColumnCount == 0) {
                            childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(totalWidth - totalWidthGapSize
                                    - (mColumnCount - 1) * cellWidth, MeasureSpec.EXACTLY);
                        }
                        break;
                }
            }

            c.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        final int xOffset = getPaddingLeft() + (mGapIncludeOutline ? mGapSize : 0);
        final int yOffset = getPaddingTop() + (mGapIncludeOutline ? mGapSize : 0);

        int cl = xOffset;
        int ct = yOffset;
        for (int i = 0; i < childCount; i++) {
            View c = getChildAt(i);

            if (i >= mRowCount * mColumnCount) {
                return;
            }

            if (c.getVisibility() == GONE) {
                continue;
            }

            if (i % mColumnCount == 0) {
                cl = xOffset;
            }

            c.layout(cl, ct, cl + c.getMeasuredWidth(), ct + c.getMeasuredHeight());

            cl += c.getMeasuredWidth() + mGapSize;

            if ((i + 1) % mColumnCount == 0) {
                ct += c.getMeasuredHeight() + mGapSize;
            }
        }
    }

    public AdjustStrategy getAdjustStrategy() {
        return mAdjustStrategy;
    }

    public void setAdjustStrategy(AdjustStrategy mAdjustStrategy) {
        this.mAdjustStrategy = mAdjustStrategy;
        requestLayout();
    }

    public static int floorDiv(int x, int y) {
        int r = x / y;
        // if the signs are different and modulo not zero, round down
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }
}
