import java.util.List;

/**
 * Part2: Specialist e.g. cardiologist / paediatrician
 */
public class Specialist extends HealthProfessional {
    private final String specialtyField;

    public Specialist(int professionalId, String fullName, List<String> availableDays, String specialtyField) {
        super(professionalId, fullName, availableDays);
        this.specialtyField = specialtyField;
    }

    @Override
    public String getSpecialDetails() {
        return String.format("TYPE:Specialist, Specialty=%s", specialtyField);
    }

    public String getSpecialtyField() {
        return specialtyField;
    }
}
