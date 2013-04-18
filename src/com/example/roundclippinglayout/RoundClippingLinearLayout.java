package com.example.roundclippinglayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RoundClippingLinearLayout extends LinearLayout {

	private Paint drawPaint;
	private Paint roundPaint;

	private int mCornerRadius = 100;

	private RectF bounds;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public RoundClippingLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onInit();
	}

	public RoundClippingLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		onInit();
	}

	public RoundClippingLinearLayout(Context context) {
		super(context);
		onInit();
	}

	protected void onInit() {
		drawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		drawPaint.setColor(0xffffffff);
		drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

		roundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		roundPaint.setColor(0xffffffff);

		setWillNotDraw(false);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (w != oldw && h != oldh) {
			bounds = new RectF(0, 0, w, h);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), drawPaint, Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
				| Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
		canvas.drawRoundRect(bounds, mCornerRadius, mCornerRadius, roundPaint);
		canvas.restoreToCount(sc);
	}

}
