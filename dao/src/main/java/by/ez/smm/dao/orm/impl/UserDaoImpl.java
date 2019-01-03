package by.ez.smm.dao.orm.impl;

import org.springframework.stereotype.Repository;

import by.ez.smm.dao.orm.UserDao;
import by.ez.smm.dao.orm.entity.Users;
import by.ez.smm.dao.orm.filter.UserFilter;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Users, UserFilter, Integer> implements UserDao
{
	protected UserDaoImpl() {
		super(Users.class);
	}

	@Override
	public void save(Users entity)
	{

	}

	public Users find(UserFilter filter)
	{
		Users user = new Users();
		user.setId(123432);
		return user;
	}
}
