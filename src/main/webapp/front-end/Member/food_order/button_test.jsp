<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <FORM METHOD="post" ACTION="food_order.do" >
       <input type="hidden" name="action" value="Member_order_button">
       <input type="submit" value="送出">
    </FORM>
<%--     <a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=food_order_button">W3School</a> --%>
</body>
</html>