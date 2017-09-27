package com.yw.bean;

public class FamilyBean {
	
	private String FamilyName;
	private int MaxVersion;
	private String Compression;
	private String InMemory;
	private int TimeToLive;
	public String getFamilyName() {
		return FamilyName;
	}
	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}
	public int getMaxVersion() {
		return MaxVersion;
	}
	public void setMaxVersion(int maxVersion) {
		MaxVersion = maxVersion;
	}
	public String getCompression() {
		return Compression;
	}
	public void setCompression(String compression) {
		Compression = compression;
	}
	public String getInMemory() {
		return InMemory;
	}
	public void setInMemory(String inMemory) {
		InMemory = inMemory;
	}
	public int getTimeToLive() {
		return TimeToLive;
	}
	public void setTimeToLive(int timeToLive) {
		TimeToLive = timeToLive;
	}
}
