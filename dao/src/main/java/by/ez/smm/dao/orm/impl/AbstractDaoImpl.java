package by.ez.smm.dao.orm.impl;

import java.sql.ResultSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Value;

import by.ez.smm.dao.orm.AbstractDao;
import by.ez.smm.dao.orm.filter.AbstractFilter;

public abstract class AbstractDaoImpl<ENTITY, FILTER, ID> implements AbstractDao<ENTITY, FILTER, ID>
{

	@PersistenceContext
	private EntityManager entityManager;

	private final Class<? extends ENTITY> entityClass;

	protected AbstractDaoImpl(final Class<? extends ENTITY> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public ENTITY getById(final ID id) {
		return entityManager.find(getEntityClass(), id);
	}

	public Class<? extends ENTITY> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected void setPaging(final AbstractFilter filter, final TypedQuery<?> q) {
		if (filter.getOffset() != null) {
			q.setFirstResult(filter.getOffset());
		}

		if (filter.getLimit() != null) {
			q.setMaxResults(filter.getLimit());
		}
	}
}
