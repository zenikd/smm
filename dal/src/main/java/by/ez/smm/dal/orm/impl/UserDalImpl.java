package by.ez.smm.dal.orm.impl;
import org.springframework.stereotype.Repository;

import by.ez.smm.dal.orm.AbstractDal;
import by.ez.smm.dal.orm.UserDal;
import by.ez.smm.dal.orm.entity.User;
import by.ez.smm.dal.orm.filter.AbstractFilter;
import by.ez.smm.dal.orm.filter.UserFilter;

@Repository
public class UserDalImpl extends AbstractDalImpl<User, UserFilter> implements UserDal
{
	@Override
	public void save(User entity)
	{

	}

	public User find(UserFilter filter)
	{
		User user = new User();
		user.setId(123432);
		return user;
	}
}
