package org.springframework.samples.petclinic.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Only the editable fields of an owner are exposed to HTTP request binding.
 * Persistent entity relationships and identifiers cannot be bound.
 */
public class OwnerForm {

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 80)
    private String city;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "{telephone.invalid}")
    private String telephone;

    private boolean existing;

    public static OwnerForm fromOwner(Owner owner) {
        OwnerForm form = new OwnerForm();
        form.firstName = owner.getFirstName();
        form.lastName = owner.getLastName();
        form.address = owner.getAddress();
        form.city = owner.getCity();
        form.telephone = owner.getTelephone();
        form.existing = !owner.isNew();
        return form;
    }

    public void applyTo(Owner owner) {
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
    }

    public boolean isNew() { return !existing; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String value) { firstName = value; }
    public String getLastName() { return lastName; }
    public void setLastName(String value) { lastName = value; }
    public String getAddress() { return address; }
    public void setAddress(String value) { address = value; }
    public String getCity() { return city; }
    public void setCity(String value) { city = value; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String value) { telephone = value; }
}
