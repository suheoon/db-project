<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="home.jsp" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>


<% ArrayList<String> nameList;%>
<% nameList = (ArrayList<String>)request.getAttribute("name");%>
<%
    if (!nameList.isEmpty()) {
        for (int i = 0; i < nameList.size(); i++) { %>
<p><%= nameList.get(i) %>
</p>
<% }
}%>
