function playerChangesBet(value) {
	document.getElementById('startgamebutton').disabled = true;
	document.getElementById('betInput').disabled = true;
	
	$.getJSON(
			"change.do",{money: value},
			function(data) {
				document.getElementById('bet').innerHTML = "배팅 금액: "+ data.playerBet;
			});
	
	document.getElementById('startgamebutton').disabled = false;
	document.getElementById('betInput').disabled = false;
}