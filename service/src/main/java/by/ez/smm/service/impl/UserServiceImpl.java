package by.ez.smm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ez.smm.dao.orm.UserDao;
import by.ez.smm.dao.orm.entity.Users;
import by.ez.smm.dao.orm.filter.UserFilter;
import by.ez.smm.service.UserService;

@Service
public class UserServiceImpl extends AbstractService<Users, Integer, UserFilter> implements UserService
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

	@Override
	public List<Users> find(UserFilter userFilter)
	{
		return userDal.find(userFilter);
	}
}
