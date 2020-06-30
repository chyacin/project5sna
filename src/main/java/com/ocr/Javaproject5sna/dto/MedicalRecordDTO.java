package com.ocr.Javaproject5sna.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class MedicalRecordDTO {

	public MedicalRecordDTO() {

	}

	private boolean created;
	private boolean errors;

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public boolean getErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

}
