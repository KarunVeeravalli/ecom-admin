package com.clayfin.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
public class AppProperties {
	
	private String unsecureWhiteListServiceName;
	private String secureWhiteListServiceName;
	public String getUnsecureWhiteListServiceName() {
		return unsecureWhiteListServiceName;
	}
	public void setUnsecureWhiteListServiceName(String unsecureWhiteListServiceName) {
		this.unsecureWhiteListServiceName = unsecureWhiteListServiceName;
	}
	public String getSecureWhiteListServiceName() {
		return secureWhiteListServiceName;
	}
	public void setSecureWhiteListServiceName(String secureWhiteListServiceName) {
		this.secureWhiteListServiceName = secureWhiteListServiceName;
	}
	
	

}
