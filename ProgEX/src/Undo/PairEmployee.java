package Undo;

import model.Employee;

public class PairEmployee {
    private int position;
    private String attribute;
    private Employee employee;

    public PairEmployee(){}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PairEmployee(int position, String attribute, Employee employee){
        this.position = position;
        this.attribute = attribute;
        this.employee = employee;
    }
}
