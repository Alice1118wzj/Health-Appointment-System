import java.util.List;

/**
 * Part2: General Practitioner (GP) mandatory type
 * Can prescribe general medicine
 */
public class GeneralPractitioner extends HealthProfessional {
    private final String practiceClinic;

    public GeneralPractitioner(int professionalId, String fullName, List<String> availableDays, String practiceClinic) {
        super(professionalId, fullName, availableDays);
        this.practiceClinic = practiceClinic;
    }

    @Override
    public String getSpecialDetails() {
        return String.format("TYPE:General Practitioner, Clinic=%s", practiceClinic);
    }

    // getter for special field
    public String getPracticeClinic() {
        return practiceClinic;
    }
}
