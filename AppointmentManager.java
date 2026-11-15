import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Part5 AppointmentManager manages collection of appointments
 * Provides add, filter, sort, cancel operations; prevents double‑booking
 * Clinic time slots 08:00‑16:00 every 30 minutes
 */
public class AppointmentManager {
    // fixed clinic daily schedule slots
    public static final List<String> CLINIC_TIME_SLOTS = List.of(
            "08:00","08:30","09:00","09:30",
            "10:00","10:30","11:00","11:30",
            "12:00","12:30","13:00","13:30",
            "14:00","14:30","15:00","15:30",
            "16:00"
    );

    private final List<Appointment> appointmentList;

    public AppointmentManager() {
        appointmentList = new ArrayList<>();
    }

    /**
     * Add new appointment, reject double booking for same professional + timeslot
     * @param newAppointment appointment to add
     * @return status message for success / failure
     */
    public String addAppointment(Appointment newAppointment) {
        if(newAppointment == null){
            return "ERROR: Appointment object cannot be null.";
        }
        HealthProfessional hp = newAppointment.getHealthProfessional();
        String slot = newAppointment.getTimeSlot();

        // check if time slot inside clinic valid schedule
        if(!CLINIC_TIME_SLOTS.contains(slot)){
            return "ERROR: Time slot "+slot+" is outside clinic working schedule.";
        }

        // check double‑booking: same professional id + same time slot
        for(Appointment app : appointmentList){
            if(app.getHealthProfessional().getProfessionalId() == hp.getProfessionalId()
                    && app.getTimeSlot().equals(slot)){
                return String.format("BOOKING FAILED: Professional ID %d already booked at %s",
                        hp.getProfessionalId(), slot);
            }
        }
        appointmentList.add(newAppointment);
        return "SUCCESS: Appointment added for "+hp.getFullName()+" at "+slot;
    }

    /**
     * Filter appointments by health professional ID
     * @param targetId professional id
     * @return filtered list
     */
    public List<Appointment> filterByProfessionalId(int targetId){
        return appointmentList.stream()
                .filter(a -> a.getHealthProfessional().getProfessionalId() == targetId)
                .collect(Collectors.toList());
    }

    /**
     * Filter appointments by patient mobile phone number
     * @param mobile patient mobile
     * @return filtered list
     */
    public List<Appointment> filterByPatientMobile(String mobile){
        return appointmentList.stream()
                .filter(a -> a.getPatient().getMobileNumber().equals(mobile))
                .collect(Collectors.toList());
    }

    /**
     * Sort all stored appointments by time slot (earliest first)
     */
    public void sortAllAppointments(){
        Collections.sort(appointmentList);
    }

    /**
     * Sort a filtered sub‑list
     * @param list list to sort
     */
    public void sortFilteredList(List<Appointment> list){
        Collections.sort(list);
    }

    /**
     * Cancel appointment: find by professionalId + timeSlot
     * @param hpId health professional id
     * @param timeSlot target time
     * @return result message
     */
    public String cancelAppointmentByIdAndTime(int hpId, String timeSlot){
        Appointment found = null;
        for(Appointment a : appointmentList){
            if(a.getHealthProfessional().getProfessionalId() == hpId
                    && a.getTimeSlot().equals(timeSlot)){
                found = a;
                break;
            }
        }
        if(found == null){
            return String.format("CANCEL FAILED: No appointment found for ID %d at %s", hpId, timeSlot);
        }else{
            appointmentList.remove(found);
            return String.format("SUCCESS: Cancelled appointment for ID %d at %s", hpId, timeSlot);
        }
    }

    /**
     * Cancel all appointments matching patient mobile number
     * @param patientMobile mobile
     * @return result message
     */
    public String cancelAppointmentByMobile(String patientMobile){
        List<Appointment> matches = filterByPatientMobile(patientMobile);
        if(matches.isEmpty()){
            return "CANCEL FAILED: No appointments found for mobile "+patientMobile;
        }
        appointmentList.removeAll(matches);
        return "SUCCESS: Removed "+matches.size()+" appointment(s) for mobile "+patientMobile;
    }

    /**
     * Get copy of all appointments
     * @return new list
     */
    public List<Appointment> getAllAppointments(){
        return new ArrayList<>(appointmentList);
    }

    /**
     * Helper print list of appointments neatly
     * @param list appointment list
     */
    public void printAppointmentList(List<Appointment> list){
        if(list.isEmpty()){
            System.out.println(" -- No appointments in this list --\n");
            return;
        }
        for(Appointment app : list){
            System.out.println(app);
        }
    }
}
