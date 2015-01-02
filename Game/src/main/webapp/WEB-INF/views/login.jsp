<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Hello</title>
</head>
<body>
    <form action="/j_spring_security_check" method="POST">
        <c:choose>
            <c:when test="${not empty error}">
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </c:when>
        </c:choose>
        <div>
            <input type="text" name="j_username" placeholder="Username">
            <input type="password" name="j_password" placeholder="Password">
            <button type="submit">Login</button>
        </div>
        <label>
            <input type="checkbox" name="_spring_security_remember_me" value="true">
            Remember me
        </label>
        <a href="#">Forgot Your Password</a>
    </form>
</body>
</html>
