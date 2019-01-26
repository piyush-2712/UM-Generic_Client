package com.company.um.authz.client.model;

import java.util.List;


//
//@Document
public class HierarchyInfo {
	
	private String hierarchyId;

	private String level;  //display name for which level /type
	
	private String name;

	private String displayName;
	
	private String type;
	
	private List<HierarchyInfo> hierarchyInfo;



	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HierarchyInfo> getHierarchyInfo() {
		return hierarchyInfo;
	}

	public void setHierarchyInfo(List<HierarchyInfo> hierarchyInfo) {
		this.hierarchyInfo = hierarchyInfo;
	}
	
	
	
	
	

}
