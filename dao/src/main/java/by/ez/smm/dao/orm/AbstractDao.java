package by.ez.smm.dao.orm;

import java.util.List;

public interface AbstractDao<ENTITY, FILTER, ID>
{
	ENTITY getById(final ID id);
	void save(ENTITY entity);
	List<ENTITY> find(FILTER filter);
}
