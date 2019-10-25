package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域对象，区域中包含有机位信息
 * @author sir
 *
 */
public class AreaEntity {

	private String name;
	
	private List<String> seats;
	
	public AreaEntity(String name){
		this.name = name;
		this.seats = new ArrayList<String>(10);
	}
	
	public AreaEntity(String name, List<String> seats){
		this.name = name;
		this.seats = seats;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSeats(List<String> seats) {
		this.seats = seats;
	}
	public List<String> getSeats(){
		return seats;
	}
	public void addSeat(String seatNumber) {
		boolean exist = false;
		for(String name: seats) {
			if(seatNumber.equals(name)) {
				exist = true;
				break;
			}
		}
		if(!exist) {
			this.seats.add(seatNumber);
		}
	}
}
