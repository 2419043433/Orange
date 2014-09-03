package com.orange.common;

import java.util.HashMap;

public class CommonParam
{
	private HashMap<Integer, Object> mParams = new HashMap<Integer, Object>();
	
	public void Set(Integer key, Object value)
	{
		mParams.put(key, value);
	}
	
	public Object get(Integer key)
	{
		return mParams.get(key);
	}
	
}