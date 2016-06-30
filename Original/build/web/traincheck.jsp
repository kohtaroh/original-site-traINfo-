<%@page import="Jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%=JumsHelper.getInstance().ViewTrainCheck(request)%>
    </body>
</html>
