<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
	<title>Black Jack 시작하기</title>
	<link href="../blackjack/resources/css/styles.css" rel="Stylesheet" type="text/css" />
</head>

<body>
	<div class="bodywrapper">
		<form name=form1 action="http://localhost:8888/blackjack/game" method="post">
            <h2>로그인</h4>
			<div class="loginId">
				<h4>닉네임</h4>
                <input type="text" name="nickname" value="nickname" id="nickname" placeholder="nickname">
            </div>
            <div class="submit">
                <input type="submit" value="submit">
            </div>
		</form>
	</div>
</body>

</html>
