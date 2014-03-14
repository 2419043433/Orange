package com.orange.model.db;

public interface ICondition
{
	ICondition and(ICondition condition);
	ICondition or(ICondition condition);
}