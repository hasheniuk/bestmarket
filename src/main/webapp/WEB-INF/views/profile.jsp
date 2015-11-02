<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Profile -->
<div class="center-block row">
  <sf:form action="/profile" method="POST" modelAttribute="user" class="form-horizontal" data-toggle="validator" role="form">
    <fieldset>
      <legend>Change your data</legend>

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
        <sf:errors path="lastName" cssClass="error" /></div>

      <!-- E-email (disabled) -->
      <div class="form-group">
        <sf:input path="email" type="text" class="form-control input-md" placeholder="E-email" required="required" disabled="true" />
        <div class="help-block with-errors"></div>
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

      <!-- Submit button -->
      <div class="form-group text-right">
        <sf:button type="submit" class="btn btn-sm">
          <span class="glyphicon glyphicon-ok"></span>
          <span> Submit</span></sf:button></div>

    </fieldset>
  </sf:form>
</div>
<!-- End of Profile -->

<!-- Order history -->
<table class="text-center table table-bordered text-center table-fixed" id="history">
  <caption id="caption"><b><i>Order History</i></b></caption>
  <tr>
    <th class="text-center">Date</th>
    <th class="text-center">Total Price</th>
    <th class="text-center">Status</th></tr>

  <c:forEach items="${orders}" var="order">
  <tr>
    <td>${order.orderDateTime}</td>
    <td class="text-right">${order.totalPrice}</td>
    <td>Processed successfully</td></tr>
  </c:forEach>

</table>
<!-- End of Order history -->