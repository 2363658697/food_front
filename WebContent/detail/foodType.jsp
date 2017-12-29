<%@ page language="java" 
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<jsp:useBean id="typeName" class="model.MyFoodType"></jsp:useBean>
<c:forEach var="map" items="${pageScope.typeName.allType}">			
	<li>
		<a href="${pageContext.request.contextPath }/showFood?typeId=${pageScope.map.TYPEID}">${pageScope.map.TYPENAME}</a>
	</li>
</c:forEach>			