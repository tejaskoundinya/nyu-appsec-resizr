<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
</head>

<body>

    <c:forEach items="${images}" var="image">
        <a href="${image.uploadUrl}">Original Image</a>
        <a href="${image.resizedUrl}">Resized Image</a>
        <br>
    </c:forEach>

</body>