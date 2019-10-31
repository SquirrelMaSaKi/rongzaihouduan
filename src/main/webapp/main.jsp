<%--
  Created by IntelliJ IDEA.
  User: 海马哥
  Date: 2019/10/31
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>定时任务列表</title>
</head>
<body>
    <h2>定时任务列表</h2>

    <table border="1px" cellspacing="0">
        <tr>
            <th>jobName</td>
            <th>jobGroup</td>
            <th>jobClassName</td>
            <th>triggerName</td>
            <th>triggerGroup</td>
            <th>cronExpression</td>
        </tr>
        <c:forEach var="j" items="${jobList}">
            <tr>
                <td>${j.jobName}</td>
                <td>${j.jobGroup}</td>
                <td>${j.jobClassName}</td>
                <td>${j.triggerName}</td>
                <td>${j.triggerGroup}</td>
                <td>${j.cronExpression}</td
            </tr>
        </c:forEach>
    </table>
</body>
</html>
