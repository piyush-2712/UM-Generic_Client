package com.company.um.authz.client.model;

public class PermissionClient {
	
	private String permissionId;

	private String name;

	private String desc;

	PermissionType permissionType;

	/**
	 * 
	 */
	public PermissionClient() {
		super();
	}

	/**
	 * @param permissionId
	 * @param name
	 * @param desc
	 * @param permissionType
	 */
	public PermissionClient(String permissionId, String name, String desc, PermissionType permissionType) {
		super();
		this.permissionId = permissionId;
		this.name = name;
		this.desc = desc;
		this.permissionType = permissionType;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PermissionType getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}
	
	
	
	
}
