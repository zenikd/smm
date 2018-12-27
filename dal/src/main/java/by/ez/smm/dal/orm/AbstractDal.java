package by.ez.smm.dal.orm;

public interface AbstractDal<T>
{
	void save(T entity);
	T findOne();
}
