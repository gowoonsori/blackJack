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
        <h2 class="title">BlackJack Login</h4>
		<form name=form1 class="loginForm" action="http://localhost:8888/blackjack/game" method="post">
			<div class="inputBox">
				<h4 class="inputLabel">닉네임</h4>
                <input type="text" name="nickname" class="nicknameInput" value="" id="nickname" placeholder="nickname">
            </div>
            <div>
                <button class="submitButton" type="submit" value="submit">시작하기</button>
            </div>
		</form>
	</div>
</body>

</html>
