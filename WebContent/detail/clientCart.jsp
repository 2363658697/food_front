﻿<%@ page language="java" 
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<html xmlns="http://www.w3.org/1999/xhtml">


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/detail/style/css/index.css" />
	<script type="text/javascript">
		function removeSorder(fId) {
			window.location= "showCart?method=remove&fId="+fId;
		}
		/** // 删除菜品项
		
		// 修改菜品项数量
		function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
		*/
		// 下单
		function genernateOrder() {
			window.location.href = "clientOrderList.html";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 	<c:set var="price"  value="0"></c:set>
					<c:forEach var="order" items="${sessionScope }">
						<c:if test="${fn: startsWith(pageScope.order.key,'cart_') }">
					<tr height="60">
				 		<td align="center" width="20%">${pageScope.order.value.foodName }</td>
				 		<td align="center" width="20%">￥${pageScope.order.value.price }</td>
				 		<td align="center" width="20%">
				 			<input type="text" value="${pageScope.order.value.count }" size="3" lang="3" onblur="alterSorder(this)"/>
				 		</td>
				 		<td align="center" width="20%">${pageScope.order.value.price * pageScope.order.value.count}</td>
				 		<td align="center" width="20%">
				 			<input type="button" value="删除" class="btn_next" lang="3" onclick="window.location='${pageContext.request.contextPath }/showCart?method=remove&fId=${pageScope.order.key }'" />
				 		</td>
				 		<c:set var="price" value="${pageScope.price+pageScope.order.value.price * pageScope.order.value.count }"></c:set>
				 	</tr>
						 </c:if>
					</c:forEach>
					
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp;${pageScope.price }</span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							
								
								
									<input type="button" value="下单" class="btn_next" onclick="window.location='${pageContext.request.contextPath }/showCart?method=order'" />
								
							
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="clientOrderList.html">
							<img src="style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
				
					<jsp:include page="foodType.jsp"></jsp:include>		
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath }/showFood" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<input type="hidden" value="selectFood" name="method">
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="#">
									<img src="${pageContext.request.contextPath }/detail/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
