<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
  <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/resources/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="/resources/js/site.js"></script>
  <script type="text/javascript" src="/resources/js/validator.js"></script>
  <title><tiles:insertAttribute name="title"/></title>
</head>

<body>
 <tiles:insertAttribute name="nav" />
 <div class="container">
   <tiles:insertAttribute name="body" />
   <tiles:insertAttribute name="footer" /></div>
</body>