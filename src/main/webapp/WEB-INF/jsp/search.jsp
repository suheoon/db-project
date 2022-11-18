<%@ page import="com.db451B07.HW4.company.model.GetSearchResult" %>
<%@ include file="home.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<%!
    ArrayList<Boolean> option;
    ArrayList<GetSearchResult> searches;
    ArrayList<String> tableHeaders;
%>
<%
    option = (ArrayList<Boolean>) request.getAttribute("option");
    searches = (ArrayList<GetSearchResult>) request.getAttribute("searches");
    tableHeaders = (ArrayList<String>) request.getAttribute("tableHeaders");
%>

<script>
    function update(form) {
        form.action = "/update";
        form.submit();
        return true;
    }

    function departmentSalaryUpdate(form) {
        form.action = "/update/department/salary";
        form.submit();
        return true;
    }
</script>
<form method="post" action="/delete">
    <table border="1" bordercolor="blue">
        <th>선택</th>
        <%
            for (int i = 0; i < tableHeaders.size(); i++) {
        %>
        <th><%=tableHeaders.get(i)%>
        </th>
        <%
            }
        %>
        <%
            for (int i = 0; i < searches.size(); i++) {
        %>
        <tr>
            <td><label><input type="checkbox" name="ssn" value="<%=searches.get(i).getSsn()%>"></label></td>
            <%
                if (option.get(0)) {
            %>
            <td><%=searches.get(i).getName()%>
            </td>
            <%
                }
                if (option.get(1)) {
            %>
            <td><%=searches.get(i).getSsn()%>
            </td>
            <%
                }
                if (option.get(2)) {
            %>
            <td><%=searches.get(i).getBDate()%>
            </td>
            <%
                }
                if (option.get(3)) {
            %>
            <td><%=searches.get(i).getAddress()%>
            </td>
            <%
                }
                if (option.get(4)) {
            %>
            <td><%=searches.get(i).getSex()%>
            </td>
            <%
                }
                if (option.get(5)) {
            %>
            <td><%=searches.get(i).getSalary()%>
            </td>
            <%
                }
                if (option.get(6)) {
            %>
            <td><%=searches.get(i).getSupervisor()%>
            </td>
            <%
                }
                if (option.get(7)) {
            %>
            <td><%=searches.get(i).getDepartment()%>
            </td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>
    </table>
    <p>
        수정 :
        <select name="column">
            <option value="Address">Address</option>
            <option value="Sex">Sex</option>
            <option value="Salary">Salary</option>
        </select>
        <input type="text" name="content">
        <input type="button" value="UPDATE" onclick='return update(this.form);'>
    </p>
    <p>
        <input type="submit" name="delete" value="선택한 데이터 삭제">
    </p>
    <p>
    <h2>부서별로 월급 일괄 수정하기</h2>
    </p>
    <p>
        부서명 : <input type="text" name="department">
        월급 : <input type="text" name="salary">
        <input type="button" value="월급 수정하기" onclick='return departmentSalaryUpdate(this.form);'>
    </p>
</form>
