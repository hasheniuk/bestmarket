<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-12">
  <table class="table table-bordered text-center">

    <c:forEach items="${products}" var="product">
      <tr class="product" id="${product.key.name}">
        <td>
          <div><img src="${product.key.picture}" alt="${product.key.name}" height="40px"></div></td>
        <td>
          <p class="text-left"><b>${product.key.name}</b></p>
          <p class="text-left">${product.key.description}</p></td>
        <td>
          <input class="text-right number-xs" type="number" step="1" min="1" max="${product.key.count}" value="${product.value}" /></td>
        <td>
          <span><b>${product.key.price}</b></span><span class="glyphicon glyphicon-usd"></span></td>
        <td>
          <button class="btn btn-xs del-prod" type="submit"><span class="glyphicon glyphicon-remove"></span></button></td></tr>
    </c:forEach>

      <tr>
        <td class="text-right" colspan="5">
          <span><b>Total: </b></span>
          <span><b id="total">${total}</b></span>
          <span class="glyphicon glyphicon-usd"></span>
          <button id="order" class="btn" type="submit" ${deactivate}>Order</button></td></tr>
  </table>
</div>