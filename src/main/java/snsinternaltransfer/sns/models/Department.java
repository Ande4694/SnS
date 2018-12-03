package snsinternaltransfer.sns.models;


import org.springframework.stereotype.Component;

@Component
public class Department {

    private int id;
    private String department;

    public Department(String department) {
        this.department = department;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}