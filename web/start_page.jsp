<%--
  Created by IntelliJ IDEA.
  User: macuser
  Date: 2019-04-23
  Time: 12:21
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

    <title></title>
</head>
<body>


<h1 style="background-color:DodgerBlue;"> Hello you can choose vacancy and city </h1>

<form action="/database" method="get">
    Vacancy:<br>
    <input type="text" name="vacancy"><br>
    City:<br>
    <input type="text" name="city"><br>
    Salary minimal:<br>
    <input type="text" name="salary_min"><br>
    Salary maximal:<br>
    <input type="text" name="salary_max"><br>

    Choose web-site
    <br>


        <select name="web_site">
            <option disabled="disabled">Choose web-site</option>
            <option value="allWebSite">All web-site</option>
            <option value="hh.ua">hh.ua</option>
            <option value="work.ua">Work.ua</option>
            <option value="rabota.ua">Rabota.ua</option>
            <option value="dou.ua">dou.ua</option>
        </select>


    <br>


    <br>
    <input type="submit" value="Submit"><br>
</form>


</body>
</html>
