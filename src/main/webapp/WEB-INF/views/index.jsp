<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Sidebar-left -->
<div class="col-sm-2 sidebar">
  <ul class="nav nav-sidebar">

      <c:forEach items="${categories}" var="category">
        <li class="radio-active">
          <a href="<c:out value="/category/${fn:toLowerCase(category.name)}/page/1" />">
            <c:out value="${category.name}" /></a></li>
      </c:forEach></ul>

</div>
<!-- End of Sidebar-left -->

<!-- Products -->
<div class="col-sm-10">
  <table class="table table-bordered text-center">

  <!-- Product top -->
    <!-- Product name, picture, description -->
    <tr>
      <c:forEach items="${products}" var="product" begin="0" end="3">
        <td class="container">
          <div>
            <p><b><c:out value="${product.name}" /></b></p>
            <img src="${product.picture}" alt="${product.name}">
            <p class="text-left"><c:out value="${product.description}" /></p></div></td>
      </c:forEach></tr>
    <!-- End Product name, picture, description -->

    <!-- Product price, submit button -->
    <tr>
      <c:forEach items="${products}" var="product" begin="0" end="3">
        <td class="text-right">
          <span><b>${product.price} $</b></span>

          <security:authorize access="hasRole('ANONYMOUS')">
            <button type="submit" class="btn btn-sm to-login">
              <span class="glyphicon glyphicon-shopping-cart"></span>
              <span><b>To Cart</b></span></button>
          </security:authorize>

          <security:authorize access="hasAnyRole('ADMIN','USER')">
            <button type="submit" class="btn btn-sm to-cart" id="${product.name}">
              <span class="glyphicon glyphicon-shopping-cart"></span>
              <span><b>To Cart</b></span></button>
          </security:authorize></td>
      </c:forEach></tr>
      <!-- End of Product price, submit button -->
  <!-- End of Product top -->

  <!-- Product bottom -->
    <!-- Product name, picture, description -->
    <tr>
      <c:forEach items="${products}" var="product" begin="4" end="7">
        <td class="container">
          <div>
            <p><b><c:out value="${product.name}" /></b></p>
            <img src="${product.picture}" alt="${product.name}">
            <p class="text-left"><c:out value="${product.description}" /></p></div></td>
      </c:forEach></tr>
    <!-- End Product name, picture, description -->

    <!-- Product price, submit button -->
    <tr>
      <c:forEach items="${products}" var="product" begin="4" end="7">
        <td class="text-right">
          <span><b>${product.price} $</b></span>

          <security:authorize access="hasRole('ANONYMOUS')">
            <button type="submit" class="btn btn-sm to-login">
              <span class="glyphicon glyphicon-shopping-cart"></span>
              <span><b>To Cart</b></span></button>
          </security:authorize>

          <security:authorize access="hasAnyRole('ADMIN','USER')">
            <button type="submit" class="btn btn-sm to-cart" id="${product.name}">
              <span class="glyphicon glyphicon-shopping-cart"></span>
              <span><b>To Cart</b></span></button>
          </security:authorize></td>
      </c:forEach></tr>
      <!-- End of Product price, submit button -->
    <!-- End of Product bottom -->

    <!-- Navigation product buttons -->
    <tr>
      <td class="text-right" colspan="4">
        <button class="btn btn-xs btn-prev" ${prevDisabled}>Prev</button>
        <button class="btn btn-xs btn-next" ${nextDisabled}>Next</button></td></tr>
    <!-- End of Navigation product buttons -->

  </table>
</div>
<!-- End of Products -->