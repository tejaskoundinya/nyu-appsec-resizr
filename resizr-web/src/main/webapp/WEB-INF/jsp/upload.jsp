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
        <h1>Upload Image</h1>
        <h2>Enter the width of the image to be resized to</h2>
        <form action="/image/upload" method="post", enctype="multipart/form-data">
            <input type="number" name="width" placeholder="Width (in px)"/><br/><br/>
            <input type="file" name="image" placeholder="Image"><br/><br/>
            <input type="submit" value="Upload and Resize"/>
        </form>
    </div>

</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>