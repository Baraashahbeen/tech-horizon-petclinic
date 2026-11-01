package org.springframework.samples.petclinic.owner;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validate editable form data independently of persistence entities.
 */
public class PetFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PetForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object value, Errors errors) {
		PetForm form = (PetForm) value;
		if (!StringUtils.hasText(form.getName())) {
			errors.rejectValue("name", "required", "required");
		}
		else if (form.getName().length() > 30) {
			errors.rejectValue("name", "size", "Name must be no more than 30 characters");
		}
		if (form.isNew() && form.getType() == null) {
			errors.rejectValue("type", "required", "required");
		}
		if (form.getBirthDate() == null) {
			errors.rejectValue("birthDate", "required", "required");
		}
	}

}
