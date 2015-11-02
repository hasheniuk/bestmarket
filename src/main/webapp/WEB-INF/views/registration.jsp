<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- Registration form -->
<div class="center-block row">
  <sf:form action="/registration" method="POST" modelAttribute="user" class="form-horizontal" data-toggle="validator" role="form">
    <fieldset>

      <!-- First name -->
      <div class="form-group">
        <sf:input path="firstName" type="text" class="form-control input-md" placeholder="First Name" required="required"
                  pattern="\b[A-Za-z0-9]{2,20}\b" data-error="Incorrect first name!" />
        <div class="help-block with-errors"></div>
        <sf:errors path="firstName" cssClass="error" /></div>

      <!-- Last name -->
      <div class="form-group">
        <sf:input path="lastName" type="text" class="form-control input-md" placeholder="Last Name" required="required"
                  pattern="\b[A-Za-z0-9]{2,20}\b" data-error="Incorrect last name!" />
        <div class="help-block with-errors"></div>
        <sf:errors path="LastName" cssClass="error" /></div>

      <!-- E-email -->
      <div class="form-group">
        <sf:input path="email" type="text" class="form-control input-md" placeholder="E-email" required="required"
                  pattern="\b(?!.{31})([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})\b" data-error="Incorrect e-email!" />
        <div class="help-block with-errors"><span class="error">${registered}</span></div>
        <sf:errors path="email" cssClass="error" /></div>

      <!-- Phone number -->
      <div class="form-group">
        <sf:input path="phone" type="text" class="form-control input-md" placeholder="Phone number" required="required"
                  pattern="[\+]?[0-9]{4,19}\b" data-error="Incorrect phone number!" />
        <div class="help-block with-errors"></div>
        <sf:errors path="phone" cssClass="error" /></div>

      <!-- Password -->
      <div class="form-group">
        <sf:input path="password" id="password" type="password" class="form-control input-md" placeholder="Password" required="required"
                  pattern="\b[A-Za-z0-9._%+-]{6,30}\b" data-error="Incorrect password!" />
        <div class="help-block with-errors"></div>
        <sf:errors path="password" cssClass="error" /></div>

      <!-- Password confirm -->
      <div class="form-group">
        <sf:input path="" type="password" class="form-control input-md" placeholder="Confirm password" required="required"
                  pattern="\b[A-Za-z0-9._%+-]{6,30}\b" data-match="#password" data-error="Incorrect password confirm!" />
      <div class="help-block with-errors"></div>
      <sf:errors path="" cssClass="error" /></div>

      <!-- Register button -->
      <div class="form-group text-right">
        <sf:button type="submit" class="btn btn-sm">
          <span class="glyphicon glyphicon-ok"></span>
          <span> Register</span></sf:button></div>

    </fieldset>
  </sf:form>
  <!-- End of registration form -->
</div>
