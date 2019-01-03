package by.ez.smm.service;

public interface AbstractServiceImpl<ENTITY, ID>
{
	public void save(ENTITY entity);
	public ENTITY find();
	public ENTITY getById(ID id);
}
