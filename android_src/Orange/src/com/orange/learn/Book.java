package com.orange.learn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;

class Book extends View
{
	//memory buffer
	private Bitmap mBackground = null;
	private Canvas mMemCanvas = null;
	private Canvas mCurBackCanvas = null;
	private Matrix mMatrix = new Matrix();
	//pages to draw
	private Bitmap mCurPage = null;
	private Bitmap mCurPageBack = null;
	private Bitmap mNextPage = null;
	//touch point
	private PointF mPointA = new PointF();//Touch point
	private PointF mPointB = new PointF();
	private PointF mPointC = new PointF();
	private PointF mPointD = new PointF();
	private PointF mPointE = new PointF();
	private PointF mPointF = new PointF();
	private PointF mPointG = new PointF();
	private PointF mPointH = new PointF();
	private PointF mPointI = new PointF();
	private PointF mPointJ = new PointF();
	private PointF mPointK = new PointF();
	
	private PointF mPointM = new PointF();
	private PointF mPointN = new PointF();
	
	private PointF mPointBCMid = new PointF();
	private PointF mPointJKMid = new PointF();
	
	private PointF mPointRotateCenter = new PointF();
	private float mRotateDegree = 0.0f;
	//right angle ratio, also ce = ef*(1-ratio), ap = ag * ratio
	private float mRightAngleRatio = 0.5f;
	
	
	
	//
	private String mStrCur = new String();
	private String mStrCurBack = new String();
	private String mStrNext = new String();
	
	private TextPaint mPaint = new TextPaint();
	private TextPaint mPointPaint = new TextPaint();
	private TextPaint mPointPaintX = new TextPaint();

	public Book(Context context) 
	{
		super(context);
		
		for(int i = 0; i < 800; i ++)
		{
			mStrCur += "A";
			mStrCurBack += "B";
			mStrNext += "C";
		}
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(28);
		
		mPointPaint.setColor(Color.BLACK);
		mPointPaint.setTextSize(35);
		mPointPaint.setFakeBoldText(true);
		
		mPointPaintX.setColor(Color.RED);
		mPointPaintX.setTextSize(28);
		
		mCurPage = Bitmap.createBitmap(600, 1000, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mCurPage);
		mMemCanvas.drawColor(Color.GREEN);
		StaticLayout layout = new StaticLayout(mStrCur,mPaint,480,Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
		layout.draw(mMemCanvas);

		mCurPageBack = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mCurPageBack);
		mMemCanvas.drawColor(Color.RED);
		layout = new StaticLayout(mStrCurBack,mPaint,480,Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
		layout.draw(mMemCanvas);
		mCurBackCanvas = new Canvas(mCurPageBack);
		
		mNextPage = Bitmap.createBitmap(600, 1000, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mNextPage);
		mMemCanvas.drawColor(Color.BLUE);	
		layout = new StaticLayout(mStrNext,mPaint,480,Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
		layout.draw(mMemCanvas);
		
		mBackground = Bitmap.createBitmap(600,1000, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mBackground);
		
		mPointA.x = 480;
		mPointA.y = 800;
		mPointF.x = 480;
		mPointF.y = 800;
		setWillNotDraw(false);
	}
	
	private void JudgePointF()
	{
		if(mPointA.x < 480 / 2 && mPointA.y < 800 / 2)
		{
			mPointF.x = 0;
			mPointF.y = 0;
		}
		else if(mPointA.x >= 480 / 2 && mPointA.y < 800 / 2)
		{
			mPointF.x = 480;
			mPointF.y = 0;
		}
		else if(mPointA.x < 480 / 2 && mPointA.y >= 800 / 2)
		{
			mPointF.x = 0;
			mPointF.y = 800;
		}
		else if(mPointA.x >= 480 / 2 && mPointA.y >= 800 / 2)
		{
			mPointF.x = 480;
			mPointF.y = 800;
		}
	}
	
	private PointF getMidPoint(PointF start, PointF end)
	{
		return new PointF((start.x + end.x)/2, (start.y + end.y)/2);
	}
	
	private void calculateAllPoints()
	{
		//A : the touch point
		//F : the right bottom corner
		//G
		mPointG.x = (mPointA.x + mPointF.x) / 2;
		mPointG.y = (mPointA.y + mPointF.y) / 2;
		//M
		mPointM.x = mPointG.x;
		mPointM.y = mPointF.y;
		//E
		float gm = mPointM.y - mPointG.y;
		float mf = mPointF.x - mPointM.x;
		float em = (gm * gm) / mf;
		mPointE.x = mPointM.x - em;
		mPointE.y = mPointF.y;
		//N
		mPointN.x = mPointF.x;
		mPointN.y = mPointG.y;
		//H
		float gn = mPointN.x - mPointG.x;
		float nf = mPointF.y - mPointN.y;
		float hn = (gn * gn) / nf;
		mPointH.x = mPointF.x;
		mPointH.y = mPointN.y - hn;
		
		//ag,bk cross p
		//assume ap = 0.5 * ag, then ap = gp = 0.5 * ag
		//then gp = (1- ratio(is 0.5)) *fp;
		//and ce = (1 - ratio) * fc;
		
		//C
		float ef = mPointF.x - mPointE.x;
		float ce = ef*(1 - mRightAngleRatio);
		mPointC.x = mPointE.x - ce;
		mPointC.y = mPointF.y;
		//J
		float hf = mPointF.y - mPointH.y;
		float jh = hf*(1 - mRightAngleRatio);
		mPointJ.x = mPointF.x;
		mPointJ.y = mPointH.y - jh;
		//B
		mPointB.x = mPointE.x + (mPointA.x - mPointE.x)*(1 - mRightAngleRatio);
		mPointB.y = mPointA.y + (mPointE.y - mPointA.y)*mRightAngleRatio;
		//K
		mPointK.x = mPointA.x + (mPointH.x - mPointA.x)*mRightAngleRatio;
		mPointK.y = mPointA.y + (mPointH.y - mPointA.y)*mRightAngleRatio;
		//D
		mPointBCMid.x = (mPointB.x + mPointC.x) / 2;
		mPointBCMid.y = (mPointB.y + mPointC.y) / 2;
		
		mPointD.x = (mPointBCMid.x + mPointE.x) / 2;
		mPointD.y = (mPointBCMid.y + mPointE.y) / 2;
		//I
		mPointJKMid.x = (mPointJ.x + mPointK.x) / 2;
		mPointJKMid.y = (mPointJ.y + mPointK.y) / 2;
		
		mPointI.x = (mPointJKMid.x + mPointH.x) / 2;
		mPointI.y = (mPointJKMid.y + mPointH.y) / 2;
		
		//mPointRotateCenter
		//mRotateDegree
		double aef = Math.atan2(800.0f - mPointA.y, mPointA.x - mPointE.x);
		mRotateDegree = 180 - (float)Math.toDegrees(aef);
		mPointRotateCenter.x = (float)(480 * Math.cos(Math.PI-aef)) + mPointA.x;
		mPointRotateCenter.y = (float)(480 * Math.sin(aef)) + mPointA.y;
		
	}
	
	private Path mPathNotCurrent = new Path();
	private Path mPathNext = new Path();
	private void makePath()
	{
		mPathNotCurrent.reset();
		mPathNotCurrent.moveTo(mPointJ.x, mPointJ.y);
		mPathNotCurrent.quadTo(mPointH.x, mPointH.y, mPointK.x, mPointK.y);
		mPathNotCurrent.lineTo(mPointA.x, mPointA.y);
		mPathNotCurrent.lineTo(mPointB.x, mPointB.y);
		mPathNotCurrent.quadTo(mPointE.x, mPointE.y, mPointC.x, mPointC.y);
		mPathNotCurrent.lineTo(mPointF.x, mPointF.y);
		mPathNotCurrent.close();

//		mPathNext.reset();
//		mPathNext.moveTo(mPointJ.x, mPointJ.y);
//		mPathNext.lineTo(mPointI.x, mPointI.y);
//		mPathNext.lineTo(mPointD.x, mPointD.y);
//		mPathNext.lineTo(mPointC.x, mPointC.y);
//		mPathNext.lineTo(mPointF.x, mPointF.y);
//		mPathNext.close();
		
		mPathNext.reset();
		mPathNext.moveTo(mPointJ.x, mPointJ.y);
		mPathNext.quadTo(mPointI.x, mPointI.y, mPointI.x, mPointI.y);
		mPathNext.lineTo(mPointD.x, mPointD.y);
		mPathNext.quadTo(mPointC.x, mPointC.y, mPointC.x, mPointC.y);
		mPathNext.lineTo(mPointF.x, mPointF.y);
		mPathNext.close();
	}
	
	private void drawPoint(Canvas canvas, String text, PointF point)
	{
		canvas.drawCircle(point.x, point.y, 5, mPointPaint);
		canvas.drawText(" " + text,point.x, point.y, mPointPaint);
	}
	
	private void drawPointx(Canvas canvas, String text, PointF point)
	{
		canvas.drawCircle(point.x, point.y, 5, mPointPaintX);
		canvas.drawText(text,point.x, point.y, mPointPaintX);
	}
	
	private void drawPoints(Canvas canvas)
	{
		drawPoint(canvas, "A", mPointA);
		drawPoint(canvas, "B", mPointB);
		drawPoint(canvas, "C", mPointC);
		drawPoint(canvas, "D", mPointD);
		drawPoint(canvas, "E", mPointE);
		drawPoint(canvas, "F", mPointF);
		drawPoint(canvas, "G", mPointG);
		drawPoint(canvas, "H", mPointH);
		drawPoint(canvas, "I", mPointI);
		drawPoint(canvas, "J", mPointJ);
		drawPoint(canvas, "K", mPointK);
		drawPoint(canvas, "M", mPointM);
		drawPoint(canvas, "N", mPointN);
			
		//drawPoint(canvas, "BCMid", mPointBCMid);
		//drawPoint(canvas, "JKMid", mPointJKMid);
	//	drawPoint(canvas, "X", mPointRotateCenter);


	}
	private int WIDTH = 1;
	private int HEIGHT = 1;
	
	void setPoint(float[] dest, int x, int y, float value)
	{
		dest[x * WIDTH + y] = value;
	}
	
	private void drawArea(Canvas canvas)
	{
		calculateAllPoints();
		makePath();
		//draw current page
		canvas.save();
		canvas.clipPath(mPathNotCurrent, Region.Op.XOR);
		canvas.drawBitmap(mCurPage, 0, 0, null);

//		float[] verts = new float[2 * 2 * 2];
//		setPoint(verts, 0, 0, 0);
//		setPoint(verts, 0, 1, 0);
//		setPoint(verts, 1, 0, 1);
//		setPoint(verts, 0, 1, 1);
//		
//		
//		canvas.drawBitmapMesh(mCurPage, 1, 1, verts, 0, null, 0, null);
		canvas.restore();
		//draw next page
		canvas.save();
		canvas.clipPath(mPathNotCurrent);
		canvas.clipPath(mPathNext, Region.Op.INTERSECT);
		canvas.drawBitmap(mNextPage, 0, 0, null);
		canvas.restore();
		
		//draw cur page back
		canvas.save();
		canvas.clipPath(mPathNotCurrent);
		canvas.clipPath(mPathNext, Region.Op.DIFFERENCE);
		mMatrix.reset();
		mMatrix.postTranslate(mPointRotateCenter.x - mPointF.x, mPointRotateCenter.y - mPointF.y);
		mMatrix.postRotate(mRotateDegree, mPointRotateCenter.x, mPointRotateCenter.y);
		canvas.drawBitmap(mCurPageBack, mMatrix, null);
		canvas.restore();
		
		drawPoints(canvas);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		mMemCanvas.drawColor(Color.BLACK);
		drawArea(mMemCanvas);
		canvas.drawBitmap(mBackground, 0, 0, null);
		drawPointx(canvas, "X", mPointRotateCenter);
		//canvas.drawText("hjfgfhgffhfhgfhjgfhjgfhgf\r\n\r\nhgfhgjhgjfhgfhgfhgfhgfhgfhgfhgfhg", 0, 100, mPaint);
//		canvas.drawColor(Color.BLACK);
//		mMemCanvas.drawColor(Color.RED);
//		mPath.reset();
//		mPath.moveTo(0, 0);
//		mPath.lineTo(100, 100);
//		mPath.quadTo(100, 200, 20, 180);
//		mPath.close();
//		canvas.clipPath(mPath,Region.Op.XOR);
//		canvas.drawBitmap(mBackground, 0, 0, null);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			mPointA.x = event.getX();
			mPointA.y = event.getY();
			this.postInvalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mPointA.x = event.getX();
			mPointA.y = event.getY();
			this.postInvalidate();
			JudgePointF();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			this.postInvalidate();
		}
		return true;
	}
}