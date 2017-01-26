package entity.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import entity.enums.BuildYear;
import entity.enums.ConstructionType;
import entity.enums.FlatType;
import entity.enums.Furnished;
import entity.enums.Heating;

@Entity
public class FlatOffer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
		
	@Column(name="FILE_SOURCE")
	private String fileName;
	
	@Column(name="FURNISHED") 
	private String furnished;
	
	@Column(name="BUILD_YEAR") 
	private String buildYear;
	
	@Column(name="CONSTRUCTION_TYPE") 
	private String consturctionType;
	
	@Column(name="FLAT_TYPE") 
	private String flatType;
	
	@Column(name="HEATING") 
	private String heating;
	
	@Embedded
	private Floors floor;
	
	@Column(name="ADDITIONAL_INFORMATION", length=1000000)
	private String additionInformation;
	
	@Column(name="STEMMED_ADDITIONAL_INFORMATION", length=1000000)
	private String stemmedAdditionInformation;
	
	@Column
	private String size;
	
	@Column
	private boolean isForRent; 
	
	@Column
	private String neighbourhood;
	
	@Column
	private String city;
	
	@Column
	private String money;
	
	@Column
	private String currency;
	
	@Column
	private String extrasConteiner;
	
	
	public String toFile() {
		return fileName + "\n" + flatType + " " + size + " \n" + furnished + " \n" + floor + " \n" + heating + "\n" + buildYear + "\n" +
				consturctionType + " \n \n \n \n " + additionInformation + " \n" + stemmedAdditionInformation + "\n" + neighbourhood + " " + city + " " + money;
				
	}
	@Override
	public String toString() {
		return "FlatOffer [id=" + id + ", fileName=" + fileName + ", furnished=" + furnished + ", buildYear="
				+ buildYear + ", consturctionType=" + consturctionType + ", flatType=" + flatType + ", heating="
				+ heating + ", floor=" + floor + ", additionInformation=" + additionInformation
				+ ", stemmedAdditionInformation=" + stemmedAdditionInformation + ", size=" + size + ", isForRent="
				+ isForRent + ", neighbour=" + neighbourhood + ", city=" + city + ", money=" + money + ", currency="
				+ currency + ", extrasConteiner=" + extrasConteiner + "]";
	}
	
	public String getExtrasConteiner() {
		return extrasConteiner;
	}
	public void setExtrasConteiner(String extrasConteiner) {
		this.extrasConteiner = extrasConteiner;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public boolean isForRent() {
		return isForRent;
	}
	public void setForRent(boolean isForRent) {
		this.isForRent = isForRent;
	}
	public String getStemmedAdditionInformation() {
		return stemmedAdditionInformation;
	}
	public void setStemmedAdditionInformation(String stemmedAdditionInformation) {
		this.stemmedAdditionInformation = stemmedAdditionInformation;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFurnished() {
		return furnished;
	}
	public void setFurnished(String furnished) {
		this.furnished = furnished;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getConsturctionType() {
		return consturctionType;
	}
	public void setConsturctionType(String consturctionType) {
		this.consturctionType = consturctionType;
	}
	public String getFlatType() {
		return flatType;
	}
	public void setFlatType(String flatType) {
		this.flatType = flatType;
	}
	public String getHeating() {
		return heating;
	}
	public void setHeating(String heating) {
		this.heating = heating;
	}
	public Floors getFloor() {
		return floor;
	}
	public void setFloor(Floors floor) {
		this.floor = floor;
	}
	public String getAdditionInformation() {
		return additionInformation;
	}
	public void setAdditionInformation(String additionInformation) {
		this.additionInformation = additionInformation;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
