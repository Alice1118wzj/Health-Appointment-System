/**
 * Part 4 Appointment class
 * Links patient, health professional and time slot
 * Implements Comparable for time‑based sorting
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional healthProfessional;
    // time slot format "HH:mm", clinic slots 08:00‑16:00 step 30 min
    private String timeSlot;

    /**
     * Create appointment, all parameters must be non‑null
     * @param patient patient info
     * @param hp any subclass of HealthProfessional
     * @param timeSlot time "HH:mm"
     */
    public Appointment(Patient patient, HealthProfessional hp, String timeSlot) {
        // guard: prevent incomplete invalid appointment object
        if(patient == null || hp == null || timeSlot == null || timeSlot.isBlank()){
            throw new IllegalArgumentException("Cannot create appointment with null/blank details");
        }
        this.patient = patient;
        this.healthProfessional = hp;
        this.timeSlot = timeSlot;
    }

    // getters
    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getHealthProfessional() {
        return healthProfessional;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Compare appointments by time slot for sorting Part4 requirement
     * @param other another appointment
     * @return comparison integer
     */
    @Override
    public int compareTo(Appointment other) {
        return this.timeSlot.compareTo(other.timeSlot);
    }

    @Override
    public String toString() {
        return String.format(
                "==== Appointment ====\nTime: %s\n%s\nAssigned Professional: %s\n=====================\n",
                timeSlot,
                patient,
                healthProfessional
        );
    }
}
