package by.ez.smm.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.ez.smm.dao.orm.UserDao;
import by.ez.smm.dao.orm.entity.Users;
import by.ez.smm.dao.orm.entity.Users_;
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

	@Override
	public List<Users> find(final UserFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Users> cq = cb.createQuery(Users.class);
		final Root<Users> from = cq.from(Users.class);
		cq.select(from);


		applyFilter(filter, cb, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<Users> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private void applyFilter(final UserFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
	                         final Root<Users> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String name = filter.getName();
			ands.add(cb.equal(from.get(Users_.name), name));

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}


	private Path<?> getSortPath(final Root<Users> from, final String sortColumn) {
		switch (sortColumn) {
			case "created":
				return from.get(Users_.created);
			case "updated":
				return from.get(Users_.updated);
			case "id":
				return from.get(Users_.id);
			case "sold":
				return from.get(Users_.name);
			case "sold_date":
				return from.get(Users_.pass);
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
