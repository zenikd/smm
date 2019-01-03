package by.ez.smm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ez.smm.dao.orm.UserDao;
import by.ez.smm.dao.orm.entity.Users;
import by.ez.smm.dao.orm.filter.UserFilter;
import by.ez.smm.service.UserService;

@Service
public class UserServiceImpl extends AbstractService<Users, Integer> implements UserService
{
	@Autowired
	UserDao userDal;

	@Override
	public Users getById(Integer id)
	{
		return userDal.getById(id);
	}

	public void save(Users entity)
	{

	}

	public Users find()
	{

		return userDal.find(new UserFilter());
	}
}
