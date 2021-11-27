<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>


<html>
<head>
<link rel="stylesheet" href="css/main.css">
<script>

	function viewData(data){
		$("#rankViewTable > tr").remove();
		console.log(data);
		data.forEach(function (val,idx){
			var tr = document.createElement("tr");
			for(var key in val){
				var td = document.createElement("td");
				$(td).text(val[key]);
				$(tr).append(td);	
			}
			console.log(tr);
			$("#rankViewTable").append(tr);
		});
	}

	$(function(){
		$("#rankList").change(function(){
			$.ajax({
				url : "/rankAjax.do",
				data : {rankName : $("#rankList").val()},
				success : function(data) {
					viewData(data);
				}
			});
		});
	});

</script>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>

<div id="contents">
<div class="container center" style="margin-right: 5px; margin-left: 5px">
  <div class="row">
    <div>
      <table class="table">
		<tbody >
			<tr >
				<th colspan="2" style="color: blue; 
				font-style:italic; font-size: 24px; text-align: center; border: none">랭크</th>
			</tr>
			<tr>
				<td colspan="1" style="text-align: center; color: yellow; border: none;
				padding-left: 100px ">목록</td>		
				<td colspan="1" style="border: none; padding-left: 40px">
					<select id="rankList" class="selectpicker col-xs-12 col-xs-6 m-8" data-style="btn-info">
						  	  <option value="not">없음</option>
						  	  <option value="many">많이쓴사람</option>
						  	  <option value="like">좋아요</option>
						      <option value="saw">조회수</option>
					</select>
				</td>	 
			 </tr>       
		</tbody>
	</table>
	
	<table class="table">
	  <thead>
	    <tr class="fontTr">
	      <th scope="col">Ranking</th>
	      <th scope="col">아이디</th>
	      <th scope="col">글수 </th>
	      <th scope="col">총 조회수</th>
	      <th scope="col">총 좋아요</th>
	    </tr>
	  </thead>
	  <tbody id="rankViewTable">
	   
	  </tbody>
	</table>
	
    </div>
    
    <div class="col">
      2 of 2
    </div>
  </div>
</div>

	
</div>	
</body>
</html>