import java.util.ArrayList;
import java.util.List;

/**
 * Part1: Abstract base class for all health professionals
 * Cannot instantiate directly, must be extended by concrete types
 */
public abstract class HealthProfessional {
    private final int professionalId;
    private final String fullName;
    // available working days e.g. "Monday","Wednesday"
    private final List<String> availableDays;

    /**
     * Full parameter constructor
     * @param professionalId numeric only ID
     * @param fullName name of professional
     * @param availableDays list of working weekdays
     */
    public HealthProfessional(int professionalId, String fullName, List<String> availableDays) {
        this.professionalId = professionalId;
        this.fullName = fullName;
        // defensive copy to prevent external modification
        this.availableDays = new ArrayList<>(availableDays);
    }

    // Getters only (encapsulation, no setters, object immutable after creation)
    public int getProfessionalId() {
        return professionalId;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getAvailableDays() {
        return new ArrayList<>(availableDays);
    }

    /**
     * Abstract method: each subclass adds its own specific details
     * Used for toString output
     * @return string of type‑specific info
     */
    public abstract String getSpecialDetails();

    @Override
    public String toString() {
        return String.format(
                "Health Professional [ID:%d, Name:%s, AvailableDays:%s | %s]",
                professionalId,
                fullName,
                availableDays,
                getSpecialDetails()
        );
    }

    /**
     * Equals: compare by professional ID for booking checks (Part5 requirement)
     * @param o object
     * @return true if same ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthProfessional that = (HealthProfessional) o;
        return professionalId == that.professionalId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(professionalId);
    }
}
