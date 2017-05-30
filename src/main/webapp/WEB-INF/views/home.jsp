<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <form:form method="post" modelAttribute="simpleMailMessage" action="send">
            <table>
                <tr><td><form:input path="to" placeholder="To"/></td></tr>
                <tr><td><form:input path="subject" placeholder="Subject"/></td></tr>
                <tr><td><form:textarea path="text" placeholder="Message Body"/></td></tr>
                <tr><td><input type="submit" value="Send"/></td></tr>
            </table>
        </form:form>
    </body>
</html>