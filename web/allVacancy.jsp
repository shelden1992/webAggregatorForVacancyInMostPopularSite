<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.aggregator.vo.Vacancy" %>
<%@ page import="java.util.List" %>
<%@ page import="net.servletDatabase.controller.ServletDateBase" %>
Created by IntelliJ IDEA.
User: macuser
Date: 2019-04-14
Time: 11:48
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style type="text/css">
        body {
            background-color: darkgray;
        }
    </style>

</head>
<body>


<h2><font><strong>Retrieve data from database in jsp</strong></font></h2>
<table llpadding="5" cellspacing="5" border="1">
    <tr>
    <tr bgcolor="#DEB887">
        <%--<th>ID</th>--%>
        <th>URL</th>
        <th>TITLE</th>
        <th>CITY</th>
        <th>COMPANY_NAME</th>
        <th>SALARY</th>
    </tr>

    <%
        List<Vacancy> vacancy=ServletDateBase.getChooseVacancy();
        for (Vacancy vac : vacancy) {
    %>

    <%String s=vac.getUrl(); %>

    <td><a href=<%="\"" + vac.getUrl() + "\"" %>><%=s %>
    </a>
    </td>
    <td><%=vac.getTitle() %>
    </td>
    <td><%=vac.getCity() %>
    </td>
    <td><%=vac.getCompanyName() %>
    </td>
    <td><%=vac.getSalary() %>
    </td>

    </tr>
    <%}%>


</table>

</body>
</html>
