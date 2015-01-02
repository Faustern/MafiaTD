<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html ng-app="mafia">
<html>
<head>
    <title>Mafia</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
</head>
<body>
    <div class="container" ng-view id="main-page-wrapper"></div>
    <script src="<c:url value="/resources/angularjs/angular.js"/>"></script>
    <script src="<c:url value="/resources/angularjs/ngmodules/angular-route.js"/>"></script>
    <script src="<c:url value="/resources/angularjs/controllers/gameController.js"/>"></script>
    <script src="<c:url value="/resources/angularjs/app.js"/>"></script>
</body>
</html>
