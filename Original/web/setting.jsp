<%@page import="Jums.JumsHelper"%>
<%@page import="Jums.UserSetBeans"%>
<% 
UserSetBeans usb = new UserSetBeans();
usb=(UserSetBeans)session.getAttribute("usb");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <meta http-equiv="refresh" content="6000; url=Setting">
        <title>駅名設定画面</title>
    </head>
    <body>
        <form action="SettingSet" method="POST">
<%=JumsHelper.getInstance().View()%>
            出発駅名:
            <input type="text" name="start" value="<%if (usb != null) {out.print(usb.getStart());}%>">
            <br><br>
            経由駅名:
            <input type="text" name="relay" value="<%if (usb != null) {out.print(usb.getRelay());}%>">
            <br><br>
            到着駅名:
            <input type="text" name="end" value="<%if (usb != null) {out.print(usb.getEnd());}%>">
            <br><br>
            <input type="submit" name="btnSubmit" value="設定する">
        </form>
        <br>
    </body>
</html>
