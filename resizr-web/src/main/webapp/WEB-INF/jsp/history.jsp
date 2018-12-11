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
            <a class="navbar-brand" href="/">Resizr</a>
        </div>
        <form:form class="navbar-form navbar-right" action="/logout" method="get">
            <button type="submit" class="btn btn-default">Logout</button>
        </form:form>
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>Upload Image</h1>
        <h2>Enter the width of the image to be resized to</h2>
        <table style="width:100%">
            <tr>
                <th>Original Image</th>
                <th>Resized Image</th>
                <th>Uploaded Date</th>
                <th>Resized Width</th>
            </tr>
            <c:forEach items="${images}" var="image">
                <tr>
                    <td>
                        <a href="${image.uploadUrl}">Original Image</a>
                    </td>
                    <td>
                        <a href="${image.resizedUrl}">Resized Image</a>
                    </td>
                    <td>
                        ${image.uploadTime}
                    </td>
                    <td>
                        ${image.width}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>