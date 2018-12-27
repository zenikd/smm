package by.ez.smm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ez.smm.dal.orm.UserDal;
import by.ez.smm.dal.orm.entity.User;
import by.ez.smm.service.UserService;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService<User>
{
	@Autowired
	UserDal userDal;

	public void save(User entity)
	{

	}

	public User findOne()
	{

		return userDal.findOne();
	}
}
