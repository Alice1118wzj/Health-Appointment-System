import java.util.List;

/**
 * AssignmentOne main class, entry point
 * Demonstrates Part3 (health professionals) and Part6 full appointment simulation
 * GenAI Declaration: I acknowledge that I have not knowingly used GenAI to complete this assessment.
 */
public class AssignmentOne {
    public static void main(String[] args) {
        // ========== PART 3: Working with health professional objects ==========
        System.out.println("========== PART3: DISPLAY HEALTH PROFESSIONALS ==========\n");
        List<String> monWedFri = List.of("Monday","Wednesday","Friday");
        List<String> tueThu = List.of("Tuesday","Thursday");
        List<String> monTueWed = List.of("Monday","Tuesday","Wednesday");

        // create multiple instances of four different types
        HealthProfessional gp1 = new GeneralPractitioner(101,"Dr Alice Smith", monWedFri,"Central Clinic");
        HealthProfessional gp2 = new GeneralPractitioner(102,"Dr Bob Lee", tueThu,"Suburb Clinic");
        HealthProfessional gp3 = new GeneralPractitioner(103,"Dr Chloe Wang", monTueWed,"City Clinic");

        HealthProfessional cardiologist = new Specialist(201,"Dr David Brown", monWedFri,"Cardiology");
        HealthProfessional wardNurse = new Nurse(301,"Nurse Emma Hall", tueThu,"Emergency Ward");
        HealthProfessional diet = new Dietitian(401,"Ms Fiona Green", monWedFri,"Weight‑management");

        // store in one list (polymorphism: base class reference holds all subtypes)
        List<HealthProfessional> professionalList = List.of(gp1,gp2,gp3,cardiologist,wardNurse,diet);
        for(HealthProfessional hp : professionalList){
            System.out.println(hp);
            System.out.println("------------------------------------------------");
        }

        // ========== PART 6: Working with appointments end‑to‑end demo ==========
        System.out.println("\n\n========== PART6: APPOINTMENT SYSTEM SIMULATION ==========\n");

        AppointmentManager manager = new AppointmentManager();

        // create patients
        Patient p1 = new Patient("Tom Johnson","0400111222");
        Patient p2 = new Patient("Amy Clark","0400333444");
        Patient p3 = new Patient("Jack Wilson","0400555666");

        // create valid appointments
        Appointment app1 = new Appointment(p1, gp1, "08:30");
        Appointment app2 = new Appointment(p2, cardiologist, "09:00");
        Appointment app3 = new Appointment(p3, wardNurse, "10:30");

        // test add appointments
        System.out.println(manager.addAppointment(app1));
        System.out.println(manager.addAppointment(app2));
        System.out.println(manager.addAppointment(app3));

        // ERROR CASE 1: double‑book same professional same timeslot
        Appointment duplicateBooking = new Appointment(new Patient("Fake","0411000000"),gp1,"08:30");
        System.out.println(manager.addAppointment(duplicateBooking));

        System.out.println("\n--- ALL APPOINTMENTS UNSORTED ---");
        manager.printAppointmentList(manager.getAllAppointments());

        // sort all appointments
        manager.sortAllAppointments();
        System.out.println("\n--- ALL APPOINTMENTS AFTER TIME SORT ---");
        manager.printAppointmentList(manager.getAllAppointments());

        // filter by professional id 101(gp1)
        System.out.println("\n--- FILTER: Professional ID=101 ---");
        var filteredHp = manager.filterByProfessionalId(101);
        manager.printAppointmentList(filteredHp);

        // filter by patient mobile 0400333444 (Amy Clark)
        System.out.println("\n--- FILTER: Patient Mobile=0400333444 ---");
        var filteredPatient = manager.filterByPatientMobile("0400333444");
        manager.printAppointmentList(filteredPatient);

        // cancel valid appointment by hpId + time
        System.out.println("\n"+manager.cancelAppointmentByIdAndTime(301,"10:30"));

        // ERROR CASE2: cancel non‑existing appointment
        System.out.println(manager.cancelAppointmentByIdAndTime(999,"14:00"));

        System.out.println("\n--- APPOINTMENTS AFTER CANCELLATION ---");
        manager.printAppointmentList(manager.getAllAppointments());

        // ERROR CASE3: cancel mobile that has zero bookings
        System.out.println("\n"+manager.cancelAppointmentByMobile("9999999999"));

        System.out.println("\n========== SIMULATION COMPLETE ==========");
    }
}
