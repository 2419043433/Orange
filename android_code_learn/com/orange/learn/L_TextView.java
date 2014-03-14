package com.orange.learn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.QuoteSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class L_TextView extends LinearLayout
{
	//////////////////////////////////Ellipse
	//End
	TextView mTvEllipseEnd = null;
	//Start
	TextView mTvEllipseStart = null;
	//Middle
	TextView mTvEllipseMiddle = null;
	//Marquee
	TextView mTvEllipseMarquee = null;
	private void initEllipse(Context context)
	{
		final int WIDTH = 50;
		mTvEllipseEnd = new TextView(context);
		mTvEllipseEnd.setText("ellipse endendendendendendendendendend");
		mTvEllipseEnd.setEllipsize(TextUtils.TruncateAt.END);
		//mTvEllipseEnd.setWidth(30);
		mTvEllipseEnd.setMaxWidth(WIDTH);
		mTvEllipseEnd.setTextColor(Color.YELLOW);
		mTvEllipseEnd.setMaxEms(4);
		ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(WIDTH, 50);
		mTvEllipseEnd.setLayoutParams(lp);
		mTvEllipseEnd.setSingleLine();
		
		mTvEllipseStart = new TextView(context);
		mTvEllipseStart.setText("ellipse startstartstartstartstartstartstart");
		mTvEllipseStart.setEllipsize(TextUtils.TruncateAt.START);
		mTvEllipseStart.setWidth(30);
		mTvEllipseStart.setLayoutParams(lp);
		mTvEllipseStart.setSingleLine();
		
		mTvEllipseMiddle = new TextView(context);
		mTvEllipseMiddle.setText("ellipse middlemiddlemiddlemiddlemiddlemiddle");
		mTvEllipseMiddle.setEllipsize(TextUtils.TruncateAt.MIDDLE);
		mTvEllipseMiddle.setWidth(30);
		mTvEllipseMiddle.setLayoutParams(lp);
		mTvEllipseMiddle.setSingleLine();
		
		mTvEllipseMarquee = new TextView(context);
		mTvEllipseMarquee.setText("ellipse marqueemarqueemarqueemarqueemarqueemarquee");
		mTvEllipseMarquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		mTvEllipseMarquee.setMarqueeRepeatLimit(-1);
		mTvEllipseMarquee.setLayoutParams(lp);
		mTvEllipseMarquee.setSingleLine();
		mTvEllipseMarquee.setHorizontallyScrolling(true);
		mTvEllipseMarquee.setSelected(true);
	}
	//////////////////////////////////BufferType
	//Editable
	TextView mTvBufferTypeEditable = null;
	//Normal
	TextView mTvBufferTypeNormal = null;
	//Spannable
	TextView mTvBufferTypeSpannable = null;
	private void initBufferType(Context context)
	{
		mTvBufferTypeEditable = new TextView(context);
		mTvBufferTypeEditable.setText("buffer type editable");
		mTvBufferTypeNormal = new TextView(context);
		mTvBufferTypeNormal.setText("buffer type normal ");
		mTvBufferTypeSpannable = new TextView(context);
		mTvBufferTypeSpannable.setText("buffer type spannable ");
	}
	//////////////////////////////////HighLight
    TextView mSpanWithString = null;
    TextView mSpanWithStringBuilder = null;
	private void initHighLight(Context context)
	{
		mSpanWithString = new TextView(context);
		String text = "My email : \"wangli3@ucweb.com\", My phonenum: 18924106216, UCWEB: uc.cn";
		int start = text.indexOf("email");
		int end = start + "email".length();
		Spannable spannable = new SpannableString(text);
		spannable.setSpan(new AbsoluteSizeSpan(25), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		spannable.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		spannable.setSpan(new BackgroundColorSpan(Color.GREEN), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		mSpanWithString.setText(spannable);
		
		mSpanWithStringBuilder = new TextView(context);
		SpannableStringBuilder builder = new SpannableStringBuilder();
		String email = "my email: \"wangli3@ucweb.com\" ";
		start = email.indexOf("my");
		end = start + "my".length();
		builder.append(email);
		builder.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		String url = "my company: \"uc.cn\"";
		start = email.length() + url.indexOf("my");
		end = start + "my".length();
		builder.append(url);
		builder.setSpan(new URLSpan(url), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		builder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, 0);//Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
		builder.setSpan(new ScaleXSpan(0.618f), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
		builder.setSpan(new StrikethroughSpan(), start, end, 0);//Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
		builder.setSpan(new QuoteSpan(), start, end, 0); //Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
		String regex = "find by regex : ab ac uvw abc bc abcd xyz mn abc ";
		builder.append(regex);
		start = email.length() + url.length();
		end = start + regex.length();
		Pattern regexPattern = Pattern.compile("ab");
		Matcher matcher = regexPattern.matcher(builder.toString());
		while(matcher.find())
		{
			builder.setSpan(new StrikethroughSpan(), matcher.start(), matcher.end(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
		}
		mSpanWithStringBuilder.setAutoLinkMask(Linkify.ALL);
		mSpanWithStringBuilder.setText(builder);
	}

	
	private void initContainers(Context context)
	{
		setLayoutParams(
				new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, 
						LayoutParams.MATCH_PARENT));
		setOrientation(LinearLayout.VERTICAL);
		//add ellipse
		addView(mTvEllipseEnd);
		addView(mTvEllipseStart);
		addView(mTvEllipseMiddle);
		addView(mTvEllipseMarquee);
		addView(mTvBufferTypeEditable);
		addView(mTvBufferTypeNormal);
		addView(mTvBufferTypeSpannable);
		addView(mSpanWithString);
		addView(mSpanWithStringBuilder);	
	}

	
	public L_TextView(Context context) 
	{
		super(context);
		
		initComponents(context);
	}
	
	private void initComponents(Context context)
	{
		initEllipse(context);
		initBufferType(context);
		initHighLight(context);
		initContainers(context);
	}

}