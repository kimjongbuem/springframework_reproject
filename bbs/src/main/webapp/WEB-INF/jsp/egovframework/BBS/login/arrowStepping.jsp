<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	console.log(<%=request.getAttribute("pageNumber") %>);
	console.log(<%=request.getAttribute("isRight") %>);
	location.href = "/bbs.do?pageNumber="+<%=request.getAttribute("pageNumber") %>
			+"&isRight=" + <%=request.getAttribute("isRight").toString()%>;
</script>