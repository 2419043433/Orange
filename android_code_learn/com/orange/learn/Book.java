package com.example.bt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;

class Book extends View
{
	//memory buffer
	private Bitmap mBackground = null;
	private Canvas mMemCanvas = null;
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
	//right angle ratio, also ce = ef*(1-ratio), ap = ag * ratio
	private float mRightAngleRatio = 0.5f;

	public Book(Context context) 
	{
		super(context);

		mCurPage = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mCurPage);
		mMemCanvas.drawColor(Color.GREEN);
		mCurPageBack = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mCurPageBack);
		mMemCanvas.drawColor(Color.YELLOW);
		mNextPage = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mNextPage);
		mMemCanvas.drawColor(Color.BLUE);	
		
		mBackground = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mMemCanvas = new Canvas(mBackground);
		
		mPointF.x = 480;
		mPointF.y = 800;
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

//		mPathCurBack.reset();
//		mPathCurBack.moveTo(mPointJ.x, mPointJ.y);
//		mPathCurBack.quadTo(mPointH.x, mPointH.y, mPointK.x, mPointK.y);
//		mPathCurBack.lineTo(mPointA.x, mPointA.y);
//		mPathCurBack.lineTo(mPointB.x, mPointB.y);
//		mPathCurBack.quadTo(mPointE.x, mPointE.y, mPointC.x, mPointC.y);
//		mPathCurBack.lineTo(mPointD.x, mPointD.y);
//		mPathCurBack.lineTo(mPointI.x, mPointI.y);
//		mPathCurBack.close();
		mPathNext.reset();
		mPathNext.moveTo(mPointJ.x, mPointJ.y);
		mPathNext.lineTo(mPointI.x, mPointI.y);
		mPathNext.lineTo(mPointD.x, mPointD.y);
		mPathNext.lineTo(mPointC.x, mPointC.y);
		mPathNext.lineTo(mPointF.x, mPointF.y);
		mPathNext.close();
	}
	
	private void drawArea(Canvas canvas)
	{
		calculateAllPoints();
		makePath();
		//draw current page
		canvas.save();
		canvas.clipPath(mPathNotCurrent, Region.Op.XOR);
		canvas.drawBitmap(mCurPage, 0, 0, null);
		canvas.restore();
		canvas.save();
		canvas.clipPath(mPathNotCurrent);
		canvas.clipPath(mPathNext, Region.Op.INTERSECT);
		canvas.drawBitmap(mNextPage, 0, 0, null);
		canvas.restore();
		
		canvas.save();
		canvas.clipPath(mPathNotCurrent);
		canvas.clipPath(mPathNext, Region.Op.DIFFERENCE);
		canvas.drawBitmap(mCurPageBack, 0, 0, null);
		canvas.restore();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		mMemCanvas.drawColor(Color.BLACK);
		drawArea(mMemCanvas);
		canvas.drawBitmap(mBackground, 0, 0, null);
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
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			this.postInvalidate();
		}
		return true;
	}
}