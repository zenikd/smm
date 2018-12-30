package by.ez.smm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ez.smm.dao.orm.UserDal;
import by.ez.smm.dao.orm.entity.User;
import by.ez.smm.dao.orm.filter.UserFilter;
import by.ez.smm.service.UserService;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService<User>
{
	@Autowired
	UserDal userDal;

	public void save(User entity)
	{

	}

	public User find()
	{

		return userDal.find(new UserFilter());
	}
}
