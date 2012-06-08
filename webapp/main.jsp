<%@ taglib uri="/WEB-INF/tags.tld" prefix="univ" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="screen.css" media="all">
    <link type="text/css" rel="stylesheet" href="screen.css" media="screen">
	<link type="text/css" rel="stylesheet" href="handheld.css" media="handheld">
	<script type="text/javascript">
	    function openDiv(name) {
		   var element = document.getElementById(name);
		   if (element.style.display == "block") {
		       element.style.display == "none";
		   } else {
		       element.style.display = "block";
		   }
		}
	</script>
	<style type="text/css">
	   .hide {
	       display: none;
	   }
	</style>
</head>
<body>

<div id="main-page">

	<univ:factionInfo />
	
	<univ:messages />
	
	<univ:webActions />

</div>
</body>
</html>