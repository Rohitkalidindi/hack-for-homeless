package com.example.mysqltest;

public class MyRecord {

	private int id;
	private String ServiceCode,ProviderName,ProviderDiscription,ProviderIlligibility,ProviderHours;
	private String AddressLine1, AddressLineCity, AddressLinePostalCode;
	double distance;
	
	public MyRecord(int id,
			String ServiceCode,
			String ProviderName,
			String ProviderDiscription,
			String ProviderIlligibility,
			String ProviderHours,
			String AddressLine1,
			String AddressLineCity,
			String AddressLinePostalCode,
			double distance){
		this.id=id;
		this.ServiceCode = ServiceCode;
		this.ProviderName=ProviderName;
		this.ProviderDiscription=ProviderDiscription;
		this.ProviderIlligibility=ProviderIlligibility;
		this.ProviderHours=ProviderHours;
		this.AddressLine1=AddressLine1;
		this.AddressLineCity=AddressLineCity;
		this.AddressLinePostalCode=AddressLinePostalCode;
		this.distance=distance;
	}
	
	
	
	
	
	public String getAddressLine1() {
		return AddressLine1+","+AddressLineCity+","+AddressLinePostalCode;
	}





	public Double getDistance() {
		return (Double)distance;
	}





	public String getProviderName() {
		return ProviderName;
	}
	public String getProviderDiscription() {
		return ProviderDiscription;
	}
	public String getProviderHours() {
		return ProviderHours;
	}

	public String toString() {
		
			return  "  ProviderName:" + " " + this.ProviderName
					+"\n  ProviderDiscription:" + " " + this.ProviderDiscription
					+"\n  ProviderIlligibility:" + " " + this.ProviderIlligibility
					+"\n  ProviderHours:" + " " + this.ProviderHours
					+"\n  AddressLine:" + " " + this.AddressLine1 
					+ this.AddressLineCity+ this.AddressLinePostalCode;	
		}
	
}
