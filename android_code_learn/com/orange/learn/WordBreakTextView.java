package com.orange.learn;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

public class WordBreakTextView extends TextView
{
	private boolean mChecked = false;
	//raw text of the TextView, update it after setString
	private String mRawText = null;
	
	public WordBreakTextView(Context context) 
	{
		super(context);
	}
	
	@Override 
	protected void onDraw(Canvas canvas) 
	{ 
		if(!mChecked)
		{
			if(null == mRawText)
			{
				mRawText = getText().toString();
			}
			if(null != mRawText)
			{
				String text = mRawText;
				//do measure
				float clientWidth = getMeasuredWidth();
				int curStart = 0;
				float leftWidth = getPaint().measureText(text, curStart, text.length());
				do
				{	
					for(int i = 0; curStart + i < text.length(); )
					{
						float widthCur = getPaint().measureText(text, curStart, curStart + i);
						float widthNext = getPaint().measureText(text, curStart, curStart + i + 1);
						if(widthCur <= clientWidth && widthNext > clientWidth)
						{
							String tmp = text.substring(0, curStart + i) + "\r" + text.substring(curStart + i, text.length());
							text = tmp;
							curStart += (1 + i);
							leftWidth = getPaint().measureText(text, curStart, text.length());
							break;
						}
						else
						{
							i ++;
						}
					}
					
				}while(leftWidth > clientWidth);
				
				setText(text);
			}
			mChecked = true;
		}
		
		super.onDraw(canvas);
	}
	

}