package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Request form: never expose persistent Pet entities to MVC data binding.
 */
public class PetForm {

    private Integer id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private PetType type;

    public static PetForm fromPet(Pet pet) {
        PetForm form = new PetForm();
        form.id = pet.getId();
        form.name = pet.getName();
        form.birthDate = pet.getBirthDate();
        form.type = pet.getType();
        return form;
    }

    public Pet toNewPet() {
        Pet pet = new Pet();
        applyTo(pet);
        return pet;
    }

    public void applyTo(Pet pet) {
        pet.setName(name);
        pet.setBirthDate(birthDate);
        pet.setType(type);
    }

    public boolean isNew() { return id == null; }
    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String value) { name = value; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate value) { birthDate = value; }
    public PetType getType() { return type; }
    public void setType(PetType value) { type = value; }
}
