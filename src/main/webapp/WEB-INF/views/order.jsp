<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-12">
  <table class="table table-bordered text-center">

   <!-- Order page header -->
   <tr>
     <td class="text-center" colspan="4">
       <span><b>Your order processed successfully. Thank you for your choice. We will contact you soon.</b></span></td></tr>

   <!-- Ordered products -->
   <c:forEach items="${boughtProducts}" var="productOutcome">
   <tr class="product">
     <td>
       <div><img src="${productOutcome.product.picture}" alt="${productOutcome.product.name}" height="40px"></div></td>
     <td>
       <p class="text-left"><b>${productOutcome.product.name}</b></p>
       <p class="text-left">${productOutcome.product.description}</p></td>
     <td>
       <span>${productOutcome.count}</span></td>
     <td>
       <span><b>${productOutcome.product.price}</b></span><span class="glyphicon glyphicon-usd"></span></td></tr>
   </c:forEach>

   <!-- Total price -->
   <tr>
     <td class="text-right" colspan="4">
       <span><b>Total: </b></span>
       <span><b id="total">${total}</b></span>
       <span class="glyphicon glyphicon-usd"></span></td></tr>

  </table>
</div>