package com.orange.learn.mvc;

import java.util.ArrayList;

public class ArrayStringDataSet
{
	private ArrayList<String> mData = new ArrayList<String>();
	
	public ArrayStringDataSet()
	{
	}
	
	public void set(ArrayList<String> data)
	{
		mData = data;
	}
	
	public int getCount()
	{
		return mData.size();
	}
	
	public String get(int position)
	{
		return mData.get(position);
	}
	
	public void set(int position, String value)
	{
		mData.set(position, value);
	}
}