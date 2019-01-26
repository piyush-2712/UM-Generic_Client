package com.company.um.authz.client.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatedUser implements UserDetails
{
	 private String id;
	 
	    private String userDisplayName;
	    @JsonIgnore
	    private String password;

	    @JsonIgnore
	    private List<GrantedAuthority> roles;
	    
	    private List<RoleClient> roleValues;
	    
	    private String mobile;
	    
	    private String email;
	    
	    private String userId;
	    
		private List<HierarchyInfo> hierarchyData;
		
		

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

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUserId() {
			return userId;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public List<RoleClient> getRoleValues() {
			return roleValues;
		}

		public void setRoleValues(List<RoleClient> roleValues) {
			this.roleValues = roleValues;
		}

		public List<GrantedAuthority> getRoles() {
			return roles;
		}

		public AuthenticatedUser() {
	    }

	    public AuthenticatedUser(String userId, String password, List<GrantedAuthority> roles) {
	        this.userId = userId;
	        this.password = password;
	        this.roles = roles;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    public void setRoles(List<GrantedAuthority> roles) {
	        this.roles = roles;
	    }

	    @Override
	    @JsonIgnore
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return roles;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public String getUsername() {
	        return userId;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

//	    @Override
//	    public String toString() {
//	        return "AuthenticatedUser{" +
//	            "userId='" + userId + '\'' +
//	            ", roles=" + roles +
//	             ", roles=" + roleValues +
//	              
//	            '}';
//	    }
	    
	    

		public String getUserDisplayName() {
			return userDisplayName;
		}

		@Override
		public String toString() {
			return "AuthenticatedUser [id=" + id + ", userDisplayName=" + userDisplayName + ", password=" + password
					+ ", roles=" + roles + ", roleValues=" + roleValues + ", mobile=" + mobile + ", email=" + email
					+ ", userId=" + userId + "]";
		}

		public void setUserDisplayName(String userDisplayName) {
			this.userDisplayName = userDisplayName;
		}

		
}
