package com.orange.learn.mvc;

import java.util.ArrayList;

import com.orange.common.CommandId;
import com.orange.common.CommonParam;
import com.orange.interfaces.ICommandProcessor;

public class Controller implements ICommandProcessor
{
	private ArrayStringDataSet mArrayStringDataSet = new ArrayStringDataSet();
	
	public Controller()
	{
		
	}
	
	public void init()
	{
		ArrayList<String> arrString = new ArrayList<String>();
		for(int i = 0; i < 1000; ++i)
		{
			arrString.add("String" + i);
		}
		mArrayStringDataSet.set(arrString);
	}
	@Override
	public boolean processCommand(CommandId id, CommonParam param) {
		switch(id)
		{
			default:
				break;
		}
		return false;
	}
}