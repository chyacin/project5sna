package com.ocr.Javaproject5sna.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class ResponseDTO {

	public ResponseDTO() {

	}

	private boolean success;
	private  boolean errors;

	public boolean isSuccessful() {
		return success;
	}

	public void setSuccessful(boolean success) {
		this.success = success;
	}

	public boolean getErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

}
