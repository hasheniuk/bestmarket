<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
  <nav class="navbar" id="navbar-ghostwhite" role="navigation">

    <!-- Brand -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/index">
        <span class="glyphicon glyphicon-home small"></span>
        <span>Gadgets</span></a></div>

    <div class="nav-pad">
      <ul class="nav navbar-nav navbar-left">

        <!-- Cart -->
        <li><a href="/cart">
          <span class="glyphicon glyphicon-shopping-cart"></span>
          <span>Cart</span></a></li>

        <!-- About -->
        <li><a href="/about">
          <span class="glyphicon glyphicon-info-sign"></span>
          <span>About</span></a></li>

      </ul>
      <security:authorize access="hasRole('ANONYMOUS')">
        <ul class="nav navbar-nav navbar-right">

          <!-- Registration -->
          <li><a href="/registration">
            <span class="glyphicon glyphicon-pencil"></span>
            <span>Registration</span></a></li>

          <!-- Login -->
          <li><a href="/login">
            <span class="glyphicon glyphicon-log-in"></span>
            <span>Login</span></a></li></ul>

      </security:authorize>

      <security:authorize access="hasAnyRole('ADMIN','USER')">
        <ul class="nav navbar-nav navbar-right">

          <!-- Profile -->
          <li><a href="/profile">
            <span class="glyphicon glyphicon-user"></span>
            <span>Profile</span></a></li>

          <!-- Logout -->
          <li><a href="/logout">
            <span class="glyphicon glyphicon-log-out"></span>
            <span>Logout</span></a></li></ul>

      </security:authorize>

    </div>

  </nav>
</div>

