package by.ez.smm.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.ez.smm.dao.orm.UserDao;
import by.ez.smm.dao.orm.entity.User;
import by.ez.smm.dao.orm.entity.User_;
import by.ez.smm.dao.orm.filter.UserFilter;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, UserFilter, Integer> implements UserDao
{
	protected UserDaoImpl() {
		super(User.class);
	}

	@Override
	public void save(User entity)
	{

	}

	@Override
	public List<User> find(final UserFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<User> cq = cb.createQuery(User.class);
		final Root<User> from = cq.from(User.class);
		cq.select(from);


		applyFilter(filter, cb, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<User> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private void applyFilter(final UserFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
	                         final Root<User> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String name = filter.getName();
			ands.add(cb.equal(from.get(User_.name), name));

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}


	private Path<?> getSortPath(final Root<User> from, final String sortColumn) {
		switch (sortColumn) {
			case "created":
				return from.get(User_.created);
			case "updated":
				return from.get(User_.updated);
			case "id":
				return from.get(User_.id);
			case "sold":
				return from.get(User_.name);
			case "sold_date":
				return from.get(User_.pass);
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
