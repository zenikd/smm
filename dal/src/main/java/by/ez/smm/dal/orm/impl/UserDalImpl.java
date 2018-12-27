package by.ez.smm.dal.orm.impl;

import org.springframework.stereotype.Repository;

import by.ez.smm.dal.orm.AbstractDal;
import by.ez.smm.dal.orm.UserDal;
import by.ez.smm.dal.orm.entity.User;
@Repository
public class UserDalImpl extends AbstractDalImpl<User> implements UserDal
{
	@Override
	public void save(User entity)
	{

	}

	@Override
	public User findOne()
	{
		User user = new User();
		user.setId(123432);
		return user;
	}
}
