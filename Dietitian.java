import java.util.List;

/**
 * Part2 Dietitian fourth distinct health professional type
 */
public class Dietitian extends HealthProfessional {
    private final String focusArea;

    public Dietitian(int professionalId, String fullName, List<String> availableDays, String focusArea) {
        super(professionalId, fullName, availableDays);
        this.focusArea = focusArea;
    }

    @Override
    public String getSpecialDetails() {
        return String.format("TYPE:Dietitian, FocusArea=%s", focusArea);
    }

    public String getFocusArea() {
        return focusArea;
    }
}
