package com.company.um.authz.client.model;

import java.io.Serializable;
import java.util.List;

import com.company.um.authz.client.model.HierarchyInfo;
import com.company.um.authz.client.model.RoleClient;


//import javax.validation.constraints.NotEmpty;

//@Document(collection =Constants.USER_COLLECTION_NAME)
//@TypeAlias(Constants.USER_COLLECTION_NAME)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserClient //extends AbstractDataModel implements Serializable


{


	
	private String id;

	private String userId; // userId is system generated unique userid

	
	private String firstName;   // mandatory
	private String middleName;  // optional
	private String lastName;    // mandatory
	private String displayName;
	
	private String email;      //mandatory
	
	//  @Indexed
	private String mobileNumber;  //mandatory
	//private Group department;     //mandatory
	private boolean enabled;
	private List<String> circles;      //mandatory
	private List<String> hubs;            // optional
	private List<String> cities;          // optional
	// Irrelevant fields: these fields can be control by roles
	private boolean projectAdmin;
	private boolean groupAdmin;   // department Admin
	private boolean systemAdmin;
	private boolean approver;
	private String reportingManagerEmployeeId;
	private String reportingManagerMailId;
	private String reportingManagerName; 
	private String reportingManagerContactNumber;
 
	private String projectId;


	private List<HierarchyInfo> hierarchyData;







	///

	public List<HierarchyInfo> getHierarchyData() {
		return hierarchyData;
	}




	public void setHierarchyData(List<HierarchyInfo> hierarchyData) {
		this.hierarchyData = hierarchyData;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getProjectId() {
		return projectId;
	}




	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	//    @Indexed
	//    @ValidEmployeeId
	private String employeeId;  //optional

//	@DBRef
//	//@NotEmpty
//	private List<Role> roles;  //mandatory


//	@DBRef
//	@CascadeSave
	private List<RoleClient> roleObject;  //mandatory

//	private LoginType loginType;   //mandatory



	//    public String getId() {
	//		return id;
	//	}
	//
	//
	//
	//
	//	public void setId(String id) {
	//		this.id = id;
	//	}




	public String getModule() {
		return module;
	}




	public List<RoleClient> getRoleObject() {
		return roleObject;
	}




	public void setRoleObject(List<RoleClient> roleObject) {
		this.roleObject = roleObject;
	}




	public void setModule(String module) {
		this.module = module;
	}

	private String module;
	//@DBRef
	//    @NotEmpty
//	private List<Project> projects; //mandatory

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isEnabled() {
		return enabled;
	}

//	public List<Project> getProjects() {
//		return projects;
//	}

//	public List<Role> getRoles() {
//		return roles;
//	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

//	public void setProjects(List<Project> projects) {
//		this.projects = projects;
//	}

//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

//	public Group getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Group department) {
//		this.department = department;
//	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getCircles() {
		return circles;
	}

	public void setCircles(List<String> circles) {
		this.circles = circles;
	}

	public List<String> getHubs() {
		return hubs;
	}

	public void setHubs(List<String> hubs) {
		this.hubs = hubs;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public boolean isProjectAdmin() {
		return projectAdmin;
	}

	public boolean isGroupAdmin() {
		return groupAdmin;
	}

	public boolean isSystemAdmin() {
		return systemAdmin;
	}

	public void setProjectAdmin(boolean projectAdmin) {
		projectAdmin = projectAdmin;
	}

	public void setGroupAdmin(boolean groupAdmin) {
		groupAdmin = groupAdmin;
	}

	public void setSystemAdmin(boolean systemAdmin) {
		systemAdmin = systemAdmin;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	



	public boolean isApprover() {
		return approver;
	}

	public void setApprover(boolean approver) {
		this.approver = approver;
	}

	public String getDisplayName() {
		return String.format("%s %s",firstName,lastName);
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getReportingManagerName() {
		return reportingManagerName;
	}

	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}

	public String getReportingManagerContactNumber() {
		return reportingManagerContactNumber;
	}

	public void setReportingManagerContactNumber(String reportingManagerContactNumber) {
		this.reportingManagerContactNumber = reportingManagerContactNumber;
	}

	public String getReportingManagerEmployeeId() {
		return reportingManagerEmployeeId;
	}

	public void setReportingManagerEmployeeId(String reportingManagerEmployeeId) {
		this.reportingManagerEmployeeId = reportingManagerEmployeeId;
	}

	public String getReportingManagerMailId() {
		return reportingManagerMailId;
	}

	public void setReportingManagerMailId(String reportingManagerMailId) {
		this.reportingManagerMailId = reportingManagerMailId;
	}

	//    @Override
	//    public String toString() {
	//        final StringBuffer sb = new StringBuffer("User{");
	//        sb.append("userId='").append(userId).append('\'');
	//        sb.append(", firstName='").append(firstName).append('\'');
	//        sb.append(", middleName='").append(middleName).append('\'');
	//        sb.append(", lastName='").append(lastName).append('\'');
	//        sb.append(", email='").append(email).append('\'');
	//        sb.append(", mobileNumber='").append(mobileNumber).append('\'');
	//        sb.append(", department=").append(department);
	//        sb.append(", enabled=").append(enabled);
	//        sb.append(", circles=").append(circles);
	//        sb.append(", hubs=").append(hubs);
	//        sb.append(", cities=").append(cities);
	//        sb.append(", isProjectAdmin=").append(projectAdmin);
	//        sb.append(", isGroupAdmin=").append(groupAdmin);
	//        sb.append(", isSystemAdmin=").append(systemAdmin);
	//        sb.append(", approver=").append(approver);
	//        sb.append(", employeeId='").append(employeeId).append('\'');
	//        sb.append(", roles=").append(roles);
	//        sb.append(", loginType=").append(loginType);
	//        sb.append(", module='").append(module).append('\'');
	//        sb.append(", projects=").append(projects);
	//        sb.append('}');
	//        return sb.toString();
	//    }
}

