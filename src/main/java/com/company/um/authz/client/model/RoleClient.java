package com.company.um.authz.client.model;

import java.util.Collection;


public class RoleClient {

	private String roleId;

	private String name;   // mandatory

	private String displayName;

	private String desc;// optional

	private String projectId;

	private Collection<PermissionClient> permission;

	private String parentRoleId;

	/**
	 * 
	 */
	public RoleClient() {
		super();
	}

	/**
	 * @param roleId
	 * @param name
	 * @param displayName
	 * @param desc
	 * @param projectId
	 * @param permission
	 * @param parentRoleId
	 */
	public RoleClient(String roleId, String name, String displayName, String desc, String projectId,
			Collection<PermissionClient> permission, String parentRoleId) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.displayName = displayName;
		this.desc = desc;
		this.projectId = projectId;
		this.permission = permission;
		this.parentRoleId = parentRoleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Collection<PermissionClient> getPermission() {
		return permission;
	}

	public void setPermission(Collection<PermissionClient> permission) {
		this.permission = permission;
	}

	public String getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(String parentRoleId) {
		this.parentRoleId = parentRoleId;
	}
	
	

}
