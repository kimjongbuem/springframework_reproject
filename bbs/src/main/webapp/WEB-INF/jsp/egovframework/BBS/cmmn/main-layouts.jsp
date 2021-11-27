<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles" %>

<!DOCTYPE html>
<html>
	<head>
		<tiles:insertAttribute name="head"/>
	</head>
	
  	<body>
 		<div id="wrap">
	  		<tiles:insertAttribute name="header" ignore="true"/>
  			<section id="container" class="main">
				<tiles:insertAttribute name="content" ignore="true"/>
 			</section>
  		</div>
  	</body>
</html>