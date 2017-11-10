package com.bliss.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Machine")
public class Machine {	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
			
	@Column(name="name")
	private String name;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="ram")
	private String ram;
	
	@Column(name="room")
	private int room;
	
		
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
}