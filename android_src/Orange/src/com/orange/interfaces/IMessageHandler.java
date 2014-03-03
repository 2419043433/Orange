package com.orange.interfaces;

import com.orange.common.CommonParam;
import com.orange.common.MessageId;

public interface IMessageHandler
{
	boolean handleMessage(MessageId id, CommonParam param);
}