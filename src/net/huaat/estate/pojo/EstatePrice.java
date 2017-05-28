/**
 * 
 */
package net.huaat.estate.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.huaat.common.util.CustomDateSerializer;
import net.huaat.common.util.DateTimeSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-25 上午9:42:59
 * @version V1.0  
 */
@Entity
@Table(name = "estate_price")
public class EstatePrice   implements java.io.Serializable{
	private String id;
	private String province;
	private String city;
	private String area;
	private String community;
	private String address;
	private Integer price;
	private String estateCard;
	private String estateType;
	private String  estateTime;
	private double  longitude;
	private double  latitude;
	private Date    dtaDate;
	
	
	public EstatePrice() {
	}
	
	public EstatePrice(String id,String province, String city,String area,String  community,String address,Integer price,String estateCard,
			String estateType,String  estateTime,double  longitude,double  latitude) {
		this.id=id;
		this.province=province;
		this.city=city;
		this.area=area;
		this.address=address;
		this.community=community;
		this.price=price;
		this.estateCard=estateCard;
		this.estateType=estateType;
		this.estateTime=estateTime;
		this.longitude=longitude;
		this.latitude=latitude;
		this.dtaDate=dtaDate;
		
	
	}
	@Id
	@Column(name = "hy_id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "province", length = 200)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name = "city", length = 200)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "area", length = 200)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name = "address1", length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "longitude")
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude")
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "estate_card")
	public String getEstateCard() {
		return estateCard;
	}

	public void setEstateCard(String estateCard) {
		this.estateCard = estateCard;
	}
	@Column(name = "community")
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
	@Column(name = "price")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	@Column(name = "estate_type")
	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}
	@Column(name = "estate_time")
	public String getEstateTime() {
		return estateTime;
	}

	public void setEstateTime(String estateTime) {
		this.estateTime = estateTime;
	}
	
	@JsonSerialize(using=DateTimeSerializer.class)
	@Column(name = "dta_date")
	public Date getDtaDate() {
		return dtaDate;
	}

	public void setDtaDate(Date dtaDate) {
		this.dtaDate = dtaDate;
	}
	
	
	
	

}
