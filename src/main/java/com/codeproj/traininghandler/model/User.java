package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ThDateUtils;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Address address;
	private String displayName;
	private String password;
	private UserType userType;
	private String name;
	private String userName;
	private Date dob;
	private String email;
	private String mobileNo;
	private Boolean isEnabled;
	private Set<UserLevel> userLevels = new HashSet<>(0);
	private Set<Appointment> appointmentsForPatientId = new HashSet<>(0);
	private Set<Appointment> appointmentsForHealerId = new HashSet<>(0);
	private Set<SentTrainingEmail> sentTrainingEmails = new HashSet<>(0);
	private Set<Log> logs = new HashSet<>(0);
	private String locale;
	private Date created;

	public User() {
		//empty constructor
	}

	public User(Long userId) {
		this.userId = userId;
	}

	public User(Long userId, Address address, 
			String name, String email, 
			Boolean isEnabled, String locale, UserType userType, Date created) {
		this.userId = userId;
		this.address = address;
		this.name = name;
		this.email = email;
		this.isEnabled = isEnabled;
		this.setLocale(locale);
		this.created = created;
		this.userType = userType;
	}

	public User(Long userId, Address address, String displayName,
			String password, String name, String userName, Date dob, String email,
			String mobileNo, Boolean isEnabled, 
			Set<UserLevel> userLevels,
			Set<Appointment> appointmentsForPatientId,
			Set<Appointment> appointmentsForHealerId,
			Set<SentTrainingEmail> sentTrainingEmails, Set<Log> logs,
			String locale, Date created,
			UserType userType) {
		this.userId = userId;
		this.address = address;
		this.displayName = displayName;
		this.password = password;
		this.name = name;
		this.userName = userName;
		this.dob = dob;
		this.email = email;
		this.mobileNo = mobileNo;
		this.isEnabled = isEnabled;
		this.userLevels = userLevels;
		this.appointmentsForPatientId = appointmentsForPatientId;
		this.appointmentsForHealerId = appointmentsForHealerId;
		this.sentTrainingEmails = sentTrainingEmails;
		this.logs = logs;
		this.setLocale(locale);
		this.userType = userType;
		this.created = created;
	}
	
	public User(String name, String displayName,
			Date dob, String mobileNo, String email, Long addressId) {
		this.displayName = displayName;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.mobileNo = mobileNo;
		this.userType = new UserType(Constants.USER_TYPE_DATA_TRAINEE_ID, Constants.USER_TYPE_DATA_TRAINEE);
		this.address = new Address(addressId);
		this.isEnabled = new Boolean(true);
		this.created = new Date();
		this.locale = Constants.GLOBAL_DEFAULT_LOCALE;
	}
	
	public User(Long userId, String name, String email, String mobileNo) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Boolean isIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<UserLevel> getUserLevels() {
		return this.userLevels;
	}

	public void setUserLevels(Set<UserLevel> userLevels) {
		this.userLevels = userLevels;
	}

	public Set<Appointment> getAppointmentsForPatientId() {
		return this.appointmentsForPatientId;
	}

	public void setAppointmentsForPatientId(
			Set<Appointment> appointmentsForPatientId) {
		this.appointmentsForPatientId = appointmentsForPatientId;
	}

	public Set<Appointment> getAppointmentsForHealerId() {
		return this.appointmentsForHealerId;
	}

	public void setAppointmentsForHealerId(
			Set<Appointment> appointmentsForHealerId) {
		this.appointmentsForHealerId = appointmentsForHealerId;
	}

	public Set<SentTrainingEmail> getSentTrainingEmails() {
		return this.sentTrainingEmails;
	}

	public void setSentTrainingEmails(Set<SentTrainingEmail> sentTrainingEmails) {
		this.sentTrainingEmails = sentTrainingEmails;
	}

	public Set<Log> getLogs() {
		return this.logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime
				* result
				+ ((appointmentsForHealerId == null) ? 0
						: appointmentsForHealerId.hashCode());
		result = prime
				* result
				+ ((appointmentsForPatientId == null) ? 0
						: appointmentsForPatientId.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((logs == null) ? 0 : logs.hashCode());
		result = prime * result
				+ ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (userId != null && other.userId != null && userId == other.userId) {
			return true;
		}
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (appointmentsForHealerId == null) {
			if (other.appointmentsForHealerId != null) {
				return false;
			}
		} else if (!appointmentsForHealerId
				.equals(other.appointmentsForHealerId)) {
			return false;
		}
		if (appointmentsForPatientId == null) {
			if (other.appointmentsForPatientId != null) {
				return false;
			}
		} else if (!appointmentsForPatientId
				.equals(other.appointmentsForPatientId)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!ThDateUtils.isDateEqualsWithoutMs(created, other.created)) {
			return false;
		}
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (dob == null) {
			if (other.dob != null) {
				return false;
			}
		} else if (!dob.equals(other.dob)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (isEnabled == null) {
			if (other.isEnabled != null) {
				return false;
			}
		} else if (!isEnabled.equals(other.isEnabled)) {
			return false;
		}
		if (locale == null) {
			if (other.locale != null) {
				return false;
			}
		} else if (!locale.equals(other.locale)) {
			return false;
		}
		if (logs == null) {
			if (other.logs != null) {
				return false;
			}
		} else if (!logs.equals(other.logs)) {
			return false;
		}
		if (mobileNo == null) {
			if (other.mobileNo != null) {
				return false;
			}
		} else if (!mobileNo.equals(other.mobileNo)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (sentTrainingEmails == null) {
			if (other.sentTrainingEmails != null) {
				return false;
			}
		} else if (!sentTrainingEmails.equals(other.sentTrainingEmails)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userLevels == null) {
			if (other.userLevels != null) {
				return false;
			}
		} else if (!userLevels.equals(other.userLevels)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (userType == null) {
			if (other.userType != null) {
				return false;
			}
		} else if (!userType.equals(other.userType)) {
			return false;
		}
		return true;
	}
}
