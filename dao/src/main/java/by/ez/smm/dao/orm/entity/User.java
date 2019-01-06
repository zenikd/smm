package by.ez.smm.dao.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class User extends BaseEntity
{
	@Column
	private String name;

	@Column
	private String pass;

	public String getPass(){return pass;}

	public void setPass(String pass){this.pass = pass;}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
