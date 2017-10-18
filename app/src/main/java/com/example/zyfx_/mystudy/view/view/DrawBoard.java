package com.example.zyfx_.mystudy.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Author zhangxin
 * @date 2017/10/17 15:19
 * @description 自定义手写签名
 **/
public class DrawBoard extends View {

	private static final float STROKE_WIDTH = 5f; // 笔画的宽度

	/*
	 * 笔画的一半宽度，用于在获取签名（手势）的矩形区域的时候将所有的笔画都框进去。
	 * 因为左上角和右下角的点是在笔画的那个点的中心的，所以左上角(x,y)->(x-w/2, y-w/2), 右下角(x,y)->(x+w/2,
	 * y+w/2)
	 */
	private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;

	private Paint paint = new Paint();
	private Path path = new Path();

	private float lastTouchX;
	private float lastTouchY;
	private final RectF dirtyRect = new RectF(); // 用于框出签名（手势）的区域

	public DrawBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint.setAntiAlias(true); // 反锯齿
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND); // 连接处圆润
		paint.setStrokeWidth(STROKE_WIDTH);
	}

	public void clear() {
		path.reset();
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float eventX = event.getX();
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(eventX, eventY);
			lastTouchX = eventX;
			lastTouchY = eventY;
			return true;

		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
			resetDirtyRect(eventX, eventY); // 和最后一个坐标值比较更新图形矩形左上角和右下角
			int historySize = event.getHistorySize();
			for (int i = 0; i < historySize; i++) {
				float historicalX = event.getHistoricalX(i);
				float historicalY = event.getHistoricalY(i);
				expandDirtyRect(historicalX, historicalY); // 和历史坐标值比较更新图形矩形左上角和右下角
				path.lineTo(historicalX, historicalY);
			}
			path.lineTo(eventX, eventY);
			break;

		default:
			return false;
		}

		invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
				(int) (dirtyRect.top - HALF_STROKE_WIDTH),
				(int) (dirtyRect.right + HALF_STROKE_WIDTH),
				(int) (dirtyRect.bottom + HALF_STROKE_WIDTH)); // 刷新整个图型区域

		lastTouchX = eventX;
		lastTouchY = eventY;

		return true;
	}

	private void expandDirtyRect(float historicalX, float historicalY) {
		if (historicalX < dirtyRect.left) {
			dirtyRect.left = historicalX;
		} else if (historicalX > dirtyRect.right) {
			dirtyRect.right = historicalX;
		}

		if (historicalY < dirtyRect.top) {
			dirtyRect.top = historicalY;
		} else if (historicalY > dirtyRect.bottom) {
			dirtyRect.bottom = historicalY;
		}
	}

	private void resetDirtyRect(float eventX, float eventY) {
		dirtyRect.left = Math.min(lastTouchX, eventX);
		dirtyRect.right = Math.max(lastTouchX, eventX);
		dirtyRect.top = Math.min(lastTouchY, eventY);
		dirtyRect.bottom = Math.max(lastTouchY, eventY);
	}
}
