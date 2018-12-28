package by.ez.smm.dal.orm;

import by.ez.smm.dal.orm.filter.AbstractFilter;

public interface AbstractDal<ENTITY, FILTER>
{
	void save(ENTITY entity);
	ENTITY find(FILTER filter);
}
