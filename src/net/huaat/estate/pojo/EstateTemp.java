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
import net.huaat.common.util.DateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-15 上午11:12:55
 * @version V1.0  
 */
@Entity
@Table(name = "estate_temp")
public class EstateTemp  implements java.io.Serializable{
	private String id;
	private String province;
	private String city;
	private String area;
	private String address;
	private Integer buildingNo;
	private Integer allFloor;
	private Integer roomFloor;
	private String    buildDate;
	private Integer housingProperty;
	private double roomArea;
	private String  batchId;
	private String  userId;
	private double  longitude;
	private double  latitude;
	private Integer pinggu;
	private String estateCard;
	private Date   dtaDate;
	
	
	public EstateTemp() {
	}
	
	public EstateTemp(String id,String province, String city,String area,String address,Integer buildingNo,Integer allFloor,Integer roomFloor,
			String    buildDate,Integer housingProperty,double roomArea,String  batchId,String  userId,double  longitude,double  latitude,Integer pinggu,String estateCard,Date   dtaDate) {
		this.id=id;
		this.province=province;
		this.city=city;
		this.area=area;
		this.address=address;
		this.buildingNo=buildingNo;
		this.allFloor=allFloor;
		this.roomFloor=roomFloor;
		this.buildDate=buildDate;
		this.housingProperty=housingProperty;
		this.roomArea=roomArea;
		this.batchId=batchId;
		this.userId=userId;
		this.longitude=longitude;
		this.latitude=latitude;
		this.pinggu=pinggu;
		this.estateCard=estateCard;
		this.dtaDate=dtaDate;
	
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "user_id", length = 200)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	@Column(name = "address", length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "building_no", length = 200)
	public Integer getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(Integer buildingNo) {
		this.buildingNo = buildingNo;
	}
	@Column(name = "all_floor", length = 200)
	public Integer getAllFloor() {
		return allFloor;
	}
	public void setAllFloor(Integer allFloor) {
		this.allFloor = allFloor;
	}
	@Column(name = "room_floor", length = 200)
	public Integer getRoomFloor() {
		return roomFloor;
	}
	public void setRoomFloor(Integer roomFloor) {
		this.roomFloor = roomFloor;
	}
	@Column(name = "build_date", length = 200)
	public String getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	@Column(name = "housing_property", length = 200)
	public Integer getHousingProperty() {
		return housingProperty;
	}
	public void setHousingProperty(Integer housingProperty) {
		this.housingProperty = housingProperty;
	}
	@Column(name = "room_area", length = 200)
	public double getRoomArea() {
		return roomArea;
	}
	public void setRoomArea(double roomArea) {
		this.roomArea = roomArea;
	}
	@Column(name = "batch_id", length = 200)
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	@Column(name = "pinggu")
	public Integer getPinggu() {
		return pinggu;
	}

	public void setPinggu(Integer pinggu) {
		this.pinggu = pinggu;
	}
	@Column(name = "estate_card")
	public String getEstateCard() {
		return estateCard;
	}

	public void setEstateCard(String estateCard) {
		this.estateCard = estateCard;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	@Column(name = "dta_date")
	public Date getDtaDate() {
		return dtaDate;
	}

	public void setDtaDate(Date dtaDate) {
		this.dtaDate = dtaDate;
	}
	
	
	
}
