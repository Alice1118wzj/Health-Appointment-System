import java.util.List;

/**
 * Part2 Nurse: can take blood samples
 */
public class Nurse extends HealthProfessional {
    private final String wardAssignment;

    public Nurse(int professionalId, String fullName, List<String> availableDays, String wardAssignment) {
        super(professionalId, fullName, availableDays);
        this.wardAssignment = wardAssignment;
    }

    @Override
    public String getSpecialDetails() {
        return String.format("TYPE:Nurse, Ward=%s", wardAssignment);
    }

    public String getWardAssignment() {
        return wardAssignment;
    }
}
