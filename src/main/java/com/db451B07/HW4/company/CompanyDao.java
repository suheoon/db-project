package com.db451B07.HW4.company;

import com.db451B07.HW4.company.model.GetSearchResult;
import com.db451B07.HW4.config.Password;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class CompanyDao {

    private static String url = "jdbc:mysql://localhost:3306/COMPANY";
    private static String user = "root";
    private static String password = new Password().getPassword();

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public ArrayList<GetSearchResult> searchEmployee(String query, ArrayList<Boolean> option) {
        ResultSet rs;
        Connection conn = null;
        ArrayList<GetSearchResult> getSearchResults = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("정상적으로 연결되었습니다.");

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                GetSearchResult getSearchResult = new GetSearchResult();
                if (option.get(0)) {
                    getSearchResult.setName(checkNull(rs.getString("e_fname")) + " " + checkNull(rs.getString("e_minit")) + " " + checkNull(rs.getString("e_lname")));
                }
                if (option.get(1)) {
                    getSearchResult.setSsn(checkNull(rs.getString("e_ssn")));
                }
                if (option.get(2)) {
                    getSearchResult.setBDate(checkNull(rs.getString("e_bdate")));
                }
                if (option.get(3)) {
                    getSearchResult.setAddress(checkNull(rs.getString("e_address")));
                }
                if (option.get(4)) {
                    getSearchResult.setSex(checkNull(rs.getString("e_sex")));
                }
                if (option.get(5)) {
                    getSearchResult.setSalary(checkNull(rs.getString("e_salary")));
                }
                if (option.get(6)) {
                    getSearchResult.setSupervisor(checkNull(rs.getString("s_fname")) + " " + checkNull(rs.getString("s_lname")));
                }
                if (option.get(7)) {
                    getSearchResult.setDepartment(checkNull(rs.getString("dname")));
                }
                getSearchResults.add(getSearchResult);
            }

        } catch (SQLException e) {
            System.err.println("연결할 수 없습니다.");
            e.printStackTrace();
        }

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {

        }

        return getSearchResults;
    }

    public void deleteEmployee(ArrayList<String> queries) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            System.out.println("정상적으로 연결되었습니다.");

            for (String query : queries) {
                pstmt = conn.prepareStatement(query);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("연결할 수 없습니다.");
            e.printStackTrace();
        }

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {

        }

    }

    public void updateEmployee(String query) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            System.out.println("정상적으로 연결되었습니다.");

            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("연결할 수 없습니다.");
            e.printStackTrace();
        }

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {

        }
    }

    public void insertEmployee(String query) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            System.out.println("정상적으로 연결되었습니다.");

            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("연결할 수 없습니다.");
            e.printStackTrace();
        }

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {

        }
    }

    public String checkNull(String str) {
        if (str == null) return " ";
        return str;
    }
}
