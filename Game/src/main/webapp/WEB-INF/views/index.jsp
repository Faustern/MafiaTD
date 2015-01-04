<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html ng-app="mafia">
<html>
<head>
    <title>Mafia</title>
    <link rel="stylesheet" href="<c:url value="/resources/libs/css/jquery-ui-1.10.3.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/libs/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
</head>
<body>
    <div class="container" ng-view id="main-page-wrapper"></div>
    <script src="<c:url value="/resources/libs/js/jquery-1.10.2.js"/>"></script>
    <script src="<c:url value="/resources/libs/js/jquery-ui-1.10.3.js"/>"></script>
    <script src="<c:url value="/resources/libs/js/ui-bootstrap-tpls-0.12.0.js"/>"></script>
    <script src="<c:url value="/resources/libs/js/angular.js"/>"></script>
    <script src="<c:url value="/resources/libs/js/angular-route.js"/>"></script>
    <script src="<c:url value="/resources/js/directives/customDirectives.js"/>"></script>
    <script src="<c:url value="/resources/js/services/customServices.js"/>"></script>
    <script src="<c:url value="/resources/js/controllers/gameController.js"/>"></script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
