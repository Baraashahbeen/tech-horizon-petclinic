package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * HTTP visit form without persistent entity identifiers or associations.
 */
public class VisitForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now().plusDays(1);

    @NotBlank
    @Size(max = 255)
    private String description;

    public Visit toNewVisit() {
        Visit visit = new Visit();
        visit.setDate(date);
        visit.setDescription(description);
        return visit;
    }

    public boolean isNew() { return true; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate value) { date = value; }
    public String getDescription() { return description; }
    public void setDescription(String value) { description = value; }
}
