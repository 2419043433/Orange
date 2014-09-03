package com.orange.controller;

import com.orange.common.CommonParam;
import com.orange.common.MessageId;
import com.orange.interfaces.ICommandProcessor;
import com.orange.interfaces.IMessageHandler;

public class MessageHandler implements IMessageHandler
{
	private final ICommandProcessor mCommandProcessor;
	
	public MessageHandler(ICommandProcessor commandProcessor)
	{
		mCommandProcessor = commandProcessor;
	}
	
	@Override
	public boolean handleMessage(MessageId id, CommonParam param)
	{
		switch(id)
		{
			default:
				break;
		}
		return false;
	}
}