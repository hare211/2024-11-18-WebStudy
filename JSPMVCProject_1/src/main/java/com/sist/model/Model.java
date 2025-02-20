package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public interface Model {
	
	public String handlerRequest(HttpServletRequest request);
}
