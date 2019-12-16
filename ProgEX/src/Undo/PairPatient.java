package Undo;

import model.Patients;

public class PairPatient {
    private int position;
    private String attribute;
    private Patients patients;

    public PairPatient(){}

    public PairPatient(int position, Patients patients, String attribute) {
        this.position = position;
        this.patients = patients;
        this.attribute=attribute;
    }

    public int getPosition() {
        return position;
    }

    public String getAttribute(){
        return attribute;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }
}
