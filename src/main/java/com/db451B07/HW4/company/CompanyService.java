package com.db451B07.HW4.company;

import com.db451B07.HW4.company.model.*;
import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
public class CompanyService {

    private static CompanyDao companyDao = new CompanyDao();
    private ArrayList<Boolean> option;
    private ArrayList<String> header;

    public ArrayList<GetSearchResult> searchEmployee(Map<String, String> parmas) {
        String query = makeSearchQuery(parmas);
        return companyDao.searchEmployee(query, option);
    }

    public ArrayList<String> searchFamily(String ssn) {
        String query = "SELECT * FROM DEPENDENT WHERE Essn = " + "'" + ssn + "'";
        System.out.println(query);
        return companyDao.searchFamily(query);
    }

    public void deleteEmployee(DeleteEmployeeReq deleteEmployeeReq) {
        ArrayList<String> queries = new ArrayList<>();
        String query = "DELETE FROM EMPLOYEE WHERE ssn=";
        for (String ssn : deleteEmployeeReq.getSsn()) {
            queries.add(query + ssn);
        }
        companyDao.deleteEmployee(queries);
    }

    public void updateEmployee(UpdateEmployeeReq updateEmployeeReq) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
        String currentTimeString = df.format(currentTime);

        String query = "UPDATE EMPLOYEE SET ";
        query += (updateEmployeeReq.getColumn() + " = " + "'" + updateEmployeeReq.getContent() + "', "
               + "modified" + " = " + "'" + currentTimeString + "'" + " WHERE Ssn = " + updateEmployeeReq.getSsn());

        companyDao.updateEmployee(query);
    }

    public void updateDepartmentSalary(UpdateDepartmentSalaryReq updateDepartmentSalaryReq) {
        String query = "UPDATE (EMPLOYEE e JOIN DEPARTMENT d ON e.Dno = d.Dnumber) SET e.Salary = " + updateDepartmentSalaryReq.getSalary() +
                " WHERE d.Dname = " + "'" + updateDepartmentSalaryReq.getDepartment() + "'";
        System.out.println(query);
        companyDao.updateDepartmentSalary(query);
    }

    public void insertEmployee(Employee employee) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
        String currentTimeString = df.format(currentTime);

        String query = "INSERT INTO EMPLOYEE VALUES " + "(" +
                "'" + employee.getFName() + "', " +
                "'" + employee.getMInit() + "', " +
                "'" + employee.getLName() + "', " +
                "'" + employee.getSsn() + "', " +
                "'" + employee.getBDate() + "', " +
                "'" + employee.getAddress() + "', " +
                "'" + employee.getSex() + "', " +
                "'" + employee.getSalary() + "', " +
                "'" + employee.getSuperSsn() + "', " +
                "'" + employee.getDno() + "', " +
                "'" + currentTimeString + "', " +
                "'" + currentTimeString + "'"
                + ")";

        companyDao.insertEmployee(query);
    }

    public String makeSearchQuery(Map<String, String> searchParams) {
        String mainCategory = "";
        String subCategory = "";
        String inputText = "";

        option = new ArrayList(Collections.nCopies(8, false));
        header = new ArrayList<>();

        String query = "";
        for (String key : searchParams.keySet()) {
            if (key.equals("name")) {
                query += "e.fname as e_fname, e.lname as e_lname, e.minit as e_minit, ";
                option.set(0, true);
                header.add("NAME");
                continue;
            }
            if (key.equals("ssn")) {
                query += "e.ssn as e_ssn, ";
                option.set(1, true);
                header.add("SSN");
                continue;
            }
            if (key.equals("bdate")) {
                query += "e.bdate as e_bdate, ";
                option.set(2, true);
                header.add("BDATE");
                continue;
            }
            if (key.equals("address")) {
                query += "e.address as e_address, ";
                option.set(3, true);
                header.add("ADDRESS");
                continue;
            }
            if (key.equals("sex")) {
                query += "e.sex as e_sex, ";
                option.set(4, true);
                header.add("SEX");
                continue;
            }
            if (key.equals("salary")) {
                query += "e.salary as e_salary, ";
                option.set(5, true);
                header.add("SALARY");
                continue;
            }
            if (key.equals("supervisor")) {
                query += "s.fname as s_fname, s.lname as s_lname, ";
                option.set(6, true);
                header.add("SUPERVISOR");
                continue;
            }
            if (key.equals("department")) {
                query += "d.dname, ";
                option.set(7, true);
                header.add("DEPARTMENT");
                continue;
            }
            if (key.equals("mainCategory")) {
                mainCategory = searchParams.get(key);
                continue;
            }
            if (key.equals("subCategory")) {
                subCategory = searchParams.get(key);
                continue;
            }
            if (key.equals("inputText")) {
                inputText = searchParams.get(key);
                continue;
            }
        }

        if (!query.isEmpty()) {
            query = query.substring(0, query.length() - 2);
        }

        String where = "";
        if (!mainCategory.equals("none")) {
            where = " WHERE ";
            if (mainCategory.equals("department")) {
                where += "d.dname=" + "'" + subCategory + "'";
            }
            if (mainCategory.equals("sex")) {
                where += "e.sex=" + "'" + subCategory + "'";
            }
            if (mainCategory.equals("salary")) {
                where += "e.salary >" + inputText;
            }
            if (mainCategory.equals("bdate")) {
                if (Integer.parseInt(subCategory) < 10) {
                    where += "e.bdate LIKE " + "'_____0" + subCategory + "%'";
                }
                if (Integer.parseInt(subCategory) >= 10) {
                    where += "e.bdate LIKE " + "'_____" + subCategory + "%'";
                }
            }
            if (mainCategory.equals("supervisor")) {
                where += "s.ssn=" + "'" + inputText + "'";
            }
        }
        query = "SELECT " + query + " FROM (EMPLOYEE e LEFT OUTER JOIN DEPARTMENT d ON e.Dno=d.Dnumber) LEFT OUTER JOIN EMPLOYEE s on e.super_ssn=s.ssn" + where;

        System.out.println(query);

        return query;
    }

}
