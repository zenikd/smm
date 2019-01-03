package by.ez.smm.dao.orm.entity;

import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(updatable = false)
	private Date created;

	@Column
	private Date updated;

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(final Date created)
	{
		this.created = created;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(final Date updated)
	{
		this.updated = updated;
	}
}
