<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:url value="/j_spring_security_check" var="loginUrl" />

<div class="center-block row">

  <!-- Login error block -->
  <c:if test="${not empty error}">
    <div><p class="text-center error">${error}</p></div>
  </c:if>
  <!-- End of Login error block -->

  <!-- Login form -->
  <form name="loginForm" action="${loginUrl}" method="POST" class="form-signin"   data-toggle="validator" role="form">
    <fieldset>
      <div class="form-group required">
        <input type="text" id="email" name="email" class="form-control input-md" placeholder="E-email" required="required"
               pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b" data-error="Incorrect email !" />
        <div class="help-block with-errors"></div></div>
      <div class="form-group required">
        <input type="password" id="password" name="password" class="form-control input-md" placeholder="Password" required="required"
               pattern="\b[A-Za-z0-9._%+-]{6,30}\b" data-error="Incorrect password !" />
        <div class="help-block with-errors"></div></div>
      <div class="form-group text-right">
        <button type="submit" class="btn btn-sm" id="registration">
          <span class="glyphicon glyphicon-ok"></span>
          <span>Register</span></button>
        <button type="submit" class="btn btn-sm">
          <span class="glyphicon glyphicon-ok"></span>
          <span>Login</span></button></div>
    </fieldset>
  </form>
  <!-- End of Login form -->

</div>