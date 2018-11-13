<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <%--<c:url value="/css/main.css" var="jstlCss" />--%>
    <%--<link href="${jstlCss}" rel="stylesheet" />--%>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Resizr</a>
        </div>
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>Welcome to Resizr!</h1>
        <h2><a href="/upload">Upload Image</a></h2>
        <h2><a href="/history">Upload History</a></h2>
    </div>

</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
