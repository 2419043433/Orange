package com.orange.model.db;

public interface ISorter
{
	ISorter orderBy(IAttribute attribute, boolean isIncrease);
}