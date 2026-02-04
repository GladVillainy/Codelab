package codelab_1;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
    String name;
    LocalDate birthDate;
    String department;
    double salary;

    public Employee(String name, LocalDate birthDate, String department, double salary) {
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return
                "\nEmployee {" +
                        "\n  Name       : " + name +
                        "\n  Birth date : " + birthDate +
                        "\n  Department : " + department +
                        "\n  Salary     : " + salary +
                        "\n}";
    }
}
