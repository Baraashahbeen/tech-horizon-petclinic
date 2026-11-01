package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.bind.WebDataBinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Regression tests to prevent request parameters from changing persistent identifiers or
 * navigating into unrelated database associations.
 */
class FormBindingSecurityTests {

	@Test
	void ownerBindingRejectsIdentifierAndNestedPetProperties() {
		Owner owner = new Owner();
		WebDataBinder binder = new WebDataBinder(owner, "owner");
		new OwnerController(mock(OwnerRepository.class)).setAllowedFields(binder);
		MutablePropertyValues values = new MutablePropertyValues();
		values.add("firstName", "Jane");
		values.add("id", "999");
		values.add("pets[0].name", "Injected");
		binder.bind(values);

		assertEquals("Jane", owner.getFirstName());
		assertNull(owner.getId());
		assertEquals(0, owner.getPets().size());
		assertEquals(2, binder.getBindingResult().getSuppressedFields().length);
	}

	@Test
	void petBindingRejectsIdentifierAndNestedAssociations() {
		Pet pet = new Pet();
		WebDataBinder binder = new WebDataBinder(pet, "pet");
		new PetController(mock(OwnerRepository.class), mock(PetTypeRepository.class)).initPetBinder(binder);
		MutablePropertyValues values = new MutablePropertyValues();
		values.add("name", "Max");
		values.add("id", "999");
		values.add("visits[0].description", "Injected");
		binder.bind(values);

		assertEquals("Max", pet.getName());
		assertNull(pet.getId());
		assertEquals(0, pet.getVisits().size());
		assertEquals(2, binder.getBindingResult().getSuppressedFields().length);
	}

	@Test
	void visitBindingRejectsIdentifier() {
		Visit visit = new Visit();
		WebDataBinder binder = new WebDataBinder(visit, "visit");
		new VisitController(mock(OwnerRepository.class)).setAllowedFields(binder);
		MutablePropertyValues values = new MutablePropertyValues();
		values.add("description", "Checkup");
		values.add("id", "999");
		binder.bind(values);

		assertEquals("Checkup", visit.getDescription());
		assertNull(visit.getId());
		assertEquals(1, binder.getBindingResult().getSuppressedFields().length);
	}

}
