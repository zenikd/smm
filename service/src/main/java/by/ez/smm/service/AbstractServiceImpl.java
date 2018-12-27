package by.ez.smm.service;

public interface AbstractServiceImpl<T>
{
	public void save(T entity);
	public T findOne();
}
