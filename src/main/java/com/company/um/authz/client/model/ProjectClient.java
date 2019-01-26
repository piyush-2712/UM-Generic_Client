package com.company.um.authz.client.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


public class ProjectClient {

	private String projectId;

	private String projectName;

	private String displayName;

	private List<String> departments;

	private List<String> modules;

	private HashMap<Object, Object> label; 

	
	private List<HierarchyInfo> hierarchyData;

	private List<RoleClient> roles;

	private List<PermissionClient> permissions;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private long    creationDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private long    lastUpdated;

	/**
	 * 
	 */
	public ProjectClient() {
		super();
	}

	/**
	 * @param projectId
	 * @param projectName
	 * @param displayName
	 * @param departments
	 * @param modules
	 * @param label
	 * @param hierarchyData
	 * @param roles
	 * @param permissions
	 * @param creationDate
	 * @param lastUpdated
	 */
	public ProjectClient(String projectId, String projectName, String displayName, List<String> departments,
			List<String> modules, HashMap<Object, Object> label, List<HierarchyInfo> hierarchyData,
			List<RoleClient> roles, List<PermissionClient> permissions, long creationDate, long lastUpdated) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.displayName = displayName;
		this.departments = departments;
		this.modules = modules;
		this.label = label;
		this.hierarchyData = hierarchyData;
		this.roles = roles;
		this.permissions = permissions;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules = modules;
	}

	public HashMap<Object, Object> getLabel() {
		return label;
	}

	public void setLabel(HashMap<Object, Object> label) {
		this.label = label;
	}

	public List<HierarchyInfo> getHierarchyData() {
		return hierarchyData;
	}

	public void setHierarchyData(List<HierarchyInfo> hierarchyData) {
		this.hierarchyData = hierarchyData;
	}

	public List<RoleClient> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleClient> roles) {
		this.roles = roles;
	}

	public List<PermissionClient> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionClient> permissions) {
		this.permissions = permissions;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
}
