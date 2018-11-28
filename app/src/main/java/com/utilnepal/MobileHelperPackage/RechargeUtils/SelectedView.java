package com.utilnepal.MobileHelperPackage.RechargeUtils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SelectedView extends View {

    private Canvas canvas;
    private Paint paint;
    private Context context;
    public static Rect focusRect;

    public SelectedView(Context context) {
        super(context);
        init();
        this.context = context;
    }

    public SelectedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        this.context = context;

    }

    public SelectedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        this.context = context;
    }

    private void init() {
        canvas = new Canvas();
        paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);


        // for width
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = RechargeActivity.oldSurfaceView.getHeight();

        canvas.drawRect(40,height/2-40,(int)getScreenWidth()-40,height/2,paint);
        focusRect = new Rect(40,height/2-40,(int)getScreenWidth()-40,height/2);
    }

    public  int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public  int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
