package by.ez.smm.dao.orm;

import by.ez.smm.dao.orm.entity.User;
import by.ez.smm.dao.orm.filter.UserFilter;

public interface UserDao extends AbstractDao<User, UserFilter, Integer>
{
}
