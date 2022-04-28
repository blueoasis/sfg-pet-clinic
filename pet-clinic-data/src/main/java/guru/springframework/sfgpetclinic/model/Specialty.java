package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

public class Specialty extends BaseEntity implements Serializable {

    private String description;

    public Specialty() {
    }

    public Specialty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Specialty{ " +
                super.toString() + " " +
                "description='" + description + '\'' +
                '}';
    }
}
