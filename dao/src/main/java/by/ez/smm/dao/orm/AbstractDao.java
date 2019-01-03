package by.ez.smm.dao.orm;

public interface AbstractDao<ENTITY, FILTER, ID>
{
	ENTITY getById(final ID id);
	void save(ENTITY entity);
	ENTITY find(FILTER filter);
}
