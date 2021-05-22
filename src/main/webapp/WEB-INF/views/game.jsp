<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head>
	<title>BlackJack | Gowoo</title>
	<link href="../blackjack/resources/css/styles.css" rel="Stylesheet" type="text/css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="../blackjack/resources/js/start.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/hit.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/stand.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/double.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/split.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/changes-bet.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/split-left-hit.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/split-right-hit.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/split-left-stand.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/split-right-stand.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
	<script src="../blackjack/resources/js/toggle-button-visibility.js?v=<%=System.currentTimeMillis() %>" charset="utf-8"></script>
</head>

<body>
	<div class="bodywrapper">
	
		<!-- 잔액/배팅금액 -->
		<div class="wrap2">
			<div id="nickname" class="msg5"></div>
			<div id="credits" class="msg5" ></div>		
			<div id="bet" class="msg6"></div>
		</div>

		<!-- 배팅 금액 -->
		<div id ="betInput" class="betInput">
			<input id="betInput" name="setBetSize" value="10000" onchange="playerChangesBet(this.value);"/>
			<button >배팅하기</button>
		</div>

		<!-- button group -->
		<p class="paragraph1">
			<button id="startgamebutton" type="button" class="btn1"onclick="startGame();">게임 Start</button>
			<button id="hitplayerbutton" type="button" class="btn2"onclick="hitPlayer();">Hit</button>
			<button id="playerstandsbutton" type="button" class="btn2" onclick="playerStands();" >Stand</button>
			<button id="playerdoublesbutton" type="button" class="btn2"onclick="playerDoubles();">Double</button>

			<button id="playersplitsbutton" type="button" class="btn2"onclick="playerSplits();">Split</button>
		</p>

		<!--딜러 카드-->
		<h2>
			<p>딜러</p>
			<div id="dealercards" class="card"></div>
			<div id="dealermessage" class="msg2"></div>
		</h2>
		
		<!--플레이어 카드 -->
		<h2>
			<p>플레이어</p>
			<div id="playercards" class="card"></div>
			<div id="playermessage" class="msg2"></div>
		</h2>
		
		<!-- 승패 결과 -->
		<h2>
			<div id="gamemessages" class="msg2"></div>
		</h2>

		<!-- split 했을때 -->
		<div class="wrap1">
			<div id="splitcardsleft" class="left"></div>
			<div id="splitcardsright" class="right"></div>
		</div>
		<div class="wrap1">
			<div id="splitleftplayermessage" class="msg3"></div>
			<div id="splitrightplayermessage" class="msg4"></div>
		</div>
		<div class="wrap1">
			<div id="splitleftgamemessages" class="msg3"></div>
			<div id="splitrightgamemessages" class="msg4"></div>
		</div>
		<div class="wrap1">
			<button id="lefthitplayerbutton" type="button" class="btn3"onclick="splitLeftHitPlayer();" >Hit</button>
			<button id="leftplayerstandsbutton" type="button" class="btn3"onclick="splitLeftPlayerStands();" >Stand</button>
			<button id="rightplayerstandsbutton" type="button" class="btn4"	onclick="splitRightPlayerStands();" >Stand</button>
			<button id="righthitplayerbutton" type="button" class="btn4"onclick="splitRightHitPlayer();">Hit</button>
		</div>
		
		
		<div class="push"></div>
	</div>
	
	<script>
		document.getElementById('nickname').innerHTML = `${round.player.name}`;
		document.getElementById('credits').innerHTML = "총 잔액: " + ${round.player.money};
		document.getElementById('bet').innerHTML = "배팅 금액: " + ${round.playerBet};
	</script>
	
</body>

</html>
