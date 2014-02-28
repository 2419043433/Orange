package com.orange.interfaces;

import com.orange.common.CommandId;
import com.orange.common.CommonParam;

public interface ICommandProcessor
{
	boolean processCommand(CommandId id, CommonParam param);
}