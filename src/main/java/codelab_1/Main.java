package codelab_1;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {
    public static void main(String[] args) {
        Main run = new Main();

       //run.calculateAgeEmployee();

        // run.threeOldesteEmployees();

       //run.saleryThreshold(40000);

       // run.birthdaysInThisMonth();

      //  run.employeesByBirthMonth();

        // run.birthdaySpecificMonth("April");

        run.sortEmploees("name");


    }

    List<Employee> employees = List.of(
            new Employee("Joe", LocalDate.of(2000, 1,9), "D12", 30000),
            new Employee("Bob", LocalDate.of(2001, 2,10), "D12", 41500.1),
            new Employee("Hannah", LocalDate.of(2003, 3,7), "D12", 45000),
            new Employee("Sara", LocalDate.of(1999, 4,12), "A11", 46000),
            new Employee("Lone", LocalDate.of(1998, 5,9), "A11", 46000),
            new Employee("Lars", LocalDate.of(1997, 6,10), "A11", 50000)
    );

    //Find the three oldest employees.
    public void threeOldesteEmployees (){

        //Finder alderen på employees
        Function<Employee, Integer> getAge =
                emp -> Period.between(emp.getBirthDate(), LocalDate.now()).getYears();

        Map<Employee, Integer> findOldeste =
                employees.stream()

                        //Finder ældste
                        .sorted(Comparator.comparing((Employee::getBirthDate)))

                        //Limited antal til 3, pga opgave krav
                        .limit(3)

                        //Apply'ere vores getAge key, for at få age og ikke kun dato
                        .map(e -> Map.entry(e, getAge.apply(e)))

                        //Opretter et map, og tildeler value og key
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ));
        System.out.println(findOldeste);
    }


    // Calculate the age of each employee based on their birthdate
    public void calculateAgeEmployee (){
        Map<String, Integer> ageOfEachEmployee = employees.stream()
                .collect(Collectors.toMap(Employee::getName, //tildeler navne og adder til maps
                        employee -> Period.between(employee.getBirthDate(), LocalDate.now()).getYears() //Udregner alder
                ));
        System.out.println(ageOfEachEmployee);
    }


    // Calculate the average age of all employees.
    public void calculateAverageAge(){
        double averageAge = employees.stream()
                .collect(Collectors.averagingInt(
                        e-> Period.between(e.getBirthDate(), LocalDate.now()).getYears()
                ));
        System.out.println(averageAge);
    }


    //Group employees by department and calculate the average salary for each department.
    public void averageSalaryByDepartment(){
        Map<String, Double> byDepartmenetAndAverage = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.averagingDouble(Employee::getSalary),
                                Math::floor)

                ));
        System.out.println(byDepartmenetAndAverage);
    }


    //Filter and display employees whose salary is above a certain threshold
    public void saleryThreshold(double threshold){
        List<Employee> thresholds = employees.stream()
                .filter(employee -> employee.getSalary() > threshold)
                .toList();
        System.out.println(thresholds);
    }


    //Count the number of employees in each department.
    public void countEmployeesInDepartment(){
        Map<String, Long> department = employees.stream()
                .collect(Collectors
                        .groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(department);
    }


    //Find the employee with the highest salary.
    public void highestSalary(){
        Employee sortedBySalary = employees.stream()
                .max(Comparator.comparing(Employee::getSalary)).get();
        System.out.println(sortedBySalary);
    }


    //List all employees who has a birthday in the current month.
    public void birthdaysInThisMonth(){
        //Finder hvilken måned det er
        Month currentMonth = LocalDate.now().getMonth();

        // Opretter en liste med employees
        List<Employee> birthday = employees.stream()

                //Filtre hvem der opfylder krav, ved at equals vores variable
                .filter(e -> e.getBirthDate().getMonth().equals(currentMonth))
                .toList();
        System.out.println(birthday);
    }


    //Group employees by birth month and display the count of employees in each group.
    public void employeesByBirthMonth(){
        //Laver et map, og forventer måned og en long værdi (antal personer med fødes i den måned)
        Map<Month, Long> sorteByBirthMonth = employees.stream()

                //Vi bruger groupby til at grouperer employees, iforhold til deres føds
                .collect(Collectors.groupingBy(employee -> employee.getBirthDate().getMonth()
                        //Her counter vi hvor mange der har føds den måned, returner long
                        , Collectors.counting()));

        System.out.println(sorteByBirthMonth);
    }


    //Filter and display employees who have birthdays in a specific month.
    public void birthdaySpecificMonth(String month){
        //Month er en enum, og ved at bruge valueof laver vi den om til en string
        //Det gør at vi kan bruge equals
        Month selectedMonth = Month.valueOf(month.toUpperCase());


        List<Employee> specificMonth = employees.stream()
                //Bruger string value af enums til at sammenligne employee føds
                .filter(employee -> employee.getBirthDate().getMonth().equals(selectedMonth))
                .toList();
        System.out.println(specificMonth);

    }


   // Create a method to sort employees based on different criteria, such as age, salary, or name.
    public void sortEmploees(String sortType) {
        switch (sortType.toUpperCase()) {
            case "AGE":
                sortByAge();
                break;
            case "SALARY":
                sortBySalary();
                break;
            case "NAME":
                sortByName();
                break;
            default:
                System.out.println("Invaild type");

        }
    }

        public void sortByAge(){
            List<Employee> sortingAge = employees.stream()
                    .sorted((p1, p2) -> p1.birthDate.compareTo(p2.birthDate))
                    .toList();
            System.out.println(sortingAge);
        }

          public void sortBySalary (){
              List<Employee> sortingAge = employees.stream()
                      .sorted((p1, p2) -> (int) (p1.salary-p2.salary))
                      .toList();
              System.out.println(sortingAge);
        }

          public void sortByName (){
              List<Employee> sortByName = employees.stream()
                      .sorted((p1, p2)
                                      -> p1.getName().compareTo(p2.getName())
                      )
                      .toList();
              System.out.println(sortByName);
        }

    }
    /*
        Function<Employee, Integer> getAge =
                emp -> Period.between(emp.getBirthDate(), LocalDate.now()).getYears();

        Map<Employee, Integer> mapAge =
                employees.stream()
                        .map(e -> Map.entry(e, getAge.apply(e)))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ));
     */





