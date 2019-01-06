package by.ez.smm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ez.smm.dao.orm.UserDao;
import by.ez.smm.dao.orm.entity.User;
import by.ez.smm.dao.orm.filter.UserFilter;
import by.ez.smm.service.UserService;

@Service
public class UserServiceImpl extends AbstractService<User, Integer, UserFilter> implements UserService
{
	@Autowired
	UserDao userDal;

	@Override
	public User getById(Integer id)
	{
		return userDal.getById(id);
	}

	public void save(User entity)
	{

	}

	@Override
	public List<User> find(UserFilter userFilter)
	{
		return userDal.find(userFilter);
	}
}
