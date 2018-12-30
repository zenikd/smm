package by.ez.smm.dao.orm;

public interface AbstractDal<ENTITY, FILTER>
{
	void save(ENTITY entity);
	ENTITY find(FILTER filter);
}
