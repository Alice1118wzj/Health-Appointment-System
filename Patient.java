/**
 * Part 4 Patient model storing patient basic info
 */
public class Patient {
    private final String patientName;
    private final String mobileNumber;

    public Patient(String patientName, String mobileNumber) {
        this.patientName = patientName;
        this.mobileNumber = mobileNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return String.format("Patient{Name='%s', Mobile='%s'}", patientName, mobileNumber);
    }
}
