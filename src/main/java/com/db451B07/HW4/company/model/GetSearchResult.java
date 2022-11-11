package com.db451B07.HW4.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetSearchResult {

    private String name;
    private String ssn;
    private String bDate;
    private String address;
    private String sex;
    private String salary;
    private String supervisor;
    private String department;

    @Override
    public String toString() {
        return "(" +
                name + ", " +
                ssn + ", " +
                bDate + ", " +
                address + ", " +
                sex + ", " +
                salary + ", " +
                supervisor + ", " +
                department + ", " +
                ")";
    }
}
