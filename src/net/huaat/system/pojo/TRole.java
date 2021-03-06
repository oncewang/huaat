package net.huaat.system.pojo;

// Generated 2012-10-8 14:17:55 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRole generated by hbm2java
 */
@Entity
@Table(name = "t_role")
public class TRole implements java.io.Serializable {

	private String id;
	private String name;
	private String des;
	private String homePageUrl;

	public TRole() {
	}

	public TRole(String id,String name, String des,String homePageUrl) {
		this.id=id;
		this.name = name;
		this.des = des;
		this.homePageUrl=homePageUrl;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "des", length = 200)
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	@Column(name = "home_page_url", length = 200)
	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	
	

}
