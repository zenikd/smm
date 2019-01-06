package by.ez.smm.service;

import java.util.List;

public interface AbstractServiceImpl<ENTITY, ID, FILTER>
{
	public void save(ENTITY entity);
	public List<ENTITY> find(FILTER filter);
	public ENTITY getById(ID id);
}
