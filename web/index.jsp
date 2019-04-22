<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
Created by IntelliJ IDEA.
User: macuser
Date: 2019-04-14
Time: 11:48
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

</head>
<body>


<%
    Properties properties=new Properties();



    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    try (FileInputStream inputStream=new FileInputStream("/Users/macuser/Desktop/projects/webPlusDatabase/src/main/resources/connect.properties")) {
        properties.load(( inputStream ));


    } catch (IOException e) {
        e.printStackTrace();
    }

    try (Connection connection=DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"))) {


        Statement statement=connection.createStatement();
        System.out.println("connection is successfull");
        ResultSet resultSet=statement.executeQuery("select * from vacancy_table");
%>
<h2><font><strong>Retrieve data from database in jsp</strong></font></h2>
<table llpadding="5" cellspacing="5" border="1">
    <tr>
    <tr bgcolor="#DEB887">
        <th>ID</th>
        <th>URL</th>
        <th>TITLE</th>
        <th>CITY</th>
        <th>COMPANY_NAME</th>
        <th>SALARY</th>
    </tr>
    <%
        while (resultSet.next()) {

    %>


    <td>

        <%=resultSet.getString("id") %>
    </td>
    <%--writer.print("<a href=" + s + ">");--%>
    <%--//                writer.print(s);--%>
    <%--//                writer.println("</a>");--%>
    <%String s=resultSet.getString("url"); %>

    <td><a href=<%="\""+resultSet.getString("url") + "\"" %> >      <%=s %>
    </a>
    </td>
    <td><%=resultSet.getString("title") %>
    </td>
    <td><%=resultSet.getString("city") %>
    </td>
    <td><%=resultSet.getString("company_name") %>
    </td>
    <td><%=resultSet.getString("salary") %>
    </td>

    </tr>


    <%
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();


        }

    %>


</table>

</body>
</html>
