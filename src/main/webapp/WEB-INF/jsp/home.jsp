<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>You've reached home</title>
</head>
<body>
	<h3>You've reached home</h3>
	<div id="result">
	</div>
	<button id="requestAjax">Request AJAX</button>
	<script>
		var handleClick = function() {
			console.log("handleClick");
			var xhr;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xhr = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xhr.open("GET", "${pageContext.request.contextPath}", true);
			xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
			xhr.setRequestHeader("Accepts", "application/xml");
			xhr.onreadystatechange=function() {
				var result= document.getElementById("result");
				if( xhr.readyState == 4) {
					if( xhr.status == 200 ) {
						var pre= document.createElement("pre");
						var responseText= document.createTextNode(xhr.responseText);
						pre.appendChild(responseText);
						result.appendChild(pre);
					}
					else {
						result.innerHTML="Failed";
					}
				}
			};
			xhr.send();
		};
		document.getElementById("requestAjax").onclick=handleClick;
	</script>
</body>
</html>
