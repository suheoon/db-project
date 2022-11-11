package com.db451B07.HW4.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Employee {

    private String fName;
    private String mInit;
    private String lName;
    private String ssn;
    private String bDate;
    private String address;
    private String sex;
    private String salary;
    private String superSsn;
    private String dno;

    @Override
    public String toString() {
        return "(" +
                fName + ", " +
                mInit + ", " +
                lName + ", " +
                ssn + ", " +
                bDate + ", " +
                address + ", " +
                sex + ", " +
                salary + ", " +
                superSsn + ", " +
                dno + ")"
                ;
    }
}
