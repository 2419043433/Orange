package com.orange.controller;

import java.util.ArrayList;

import com.orange.common.CommandId;
import com.orange.common.CommonParam;
import com.orange.common.MessageId;
import com.orange.interfaces.ICommandProcessor;
import com.orange.interfaces.IMessageFilter;
import com.orange.interfaces.IMessageHandler;
import com.orange.interfaces.IState;
import com.orange.os.android.IActivityDelegate;
/**
 * 
 * @author wangli
 * 
 * Controller is the maim bus of the whole system, it receives message from UI, native(c, c++..) or network,
 * and controll the process sequence.
 *
 */
public class Controller implements ICommandProcessor, IMessageHandler, IActivityDelegate
{
	private ArrayList<IMessageFilter> mMessageFilters = new ArrayList<IMessageFilter>();
	private IState mCurrentState = null;
	
	public Controller()
	{
		
	}
//From IMessageHandler	
	/**
	 * 
	 * @param id 
	 * @param param
	 * @return true if message if handled , else return false
	 */
	@Override
	public boolean handleMessage(MessageId id, CommonParam param) 
	{
		//1. handle message before state, by filters(one by one)
		//2. state handle message, by current state
		//3. handle message after state
		return false;
	}
 
	//From ICommandProcessor
	@Override
	public boolean processCommand(CommandId id, CommonParam param) 
	{
		//1. process command myself 
		//2. process command by sub procesoors
		return false;
	}
	//From IActivityDelegate
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() 
	{
		// TODO Auto-generated method stub
		
	}
}