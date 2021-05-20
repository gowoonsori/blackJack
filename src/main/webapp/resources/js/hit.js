function hitPlayer() {
	document.getElementById('playerstandsbutton').disabled = true;
	document.getElementById('playerdoublesbutton').disabled = true;
	document.getElementById('hitplayerbutton').disabled = true;
	document.getElementById('playersplitsbutton').disabled = true;

	$.getJSON(
			"hit.do",{},
			function(data) {
				var lastCardInArray, cardImage;
				lastCardInArray = data.playerCards.length - 1;
				cardImage = "resources/images/"+ data.playerCards[lastCardInArray].rank + data.playerCards[lastCardInArray].suit + ".png";
				$('#playercards').append($('<img src= ' + cardImage + '>').fadeIn(2000));

				if (data.bustPlayer === true) {
					document.getElementById('gamemessages').style.visibility = 'visible';
					document.getElementById('gamemessages').innerHTML = data.gameMessage;
					
					toggleButtonVisibility();
				} else {
					document.getElementById('playerstandsbutton').disabled = false;
					document.getElementById('hitplayerbutton').disabled = false;
				}
				
				document.getElementById('playermessage').innerHTML="Total: " + data.playerHandValue;
				document.getElementById('dealermessage').innerHTML="Total: " + data.dealerHandValue;
		
				document.getElementById('credits').innerHTML = "총 잔액: " + data.player.money;
				document.getElementById('bet').innerHTML = "배팅 금액: "+ data.playerBet;	
			});	
}