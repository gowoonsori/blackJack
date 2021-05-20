function playerStands() {
	document.getElementById('playerstandsbutton').disabled = true;
	document.getElementById('hitplayerbutton').disabled = true;
	document.getElementById('playerdoublesbutton').disabled = true;

	$.getJSON(
			"stand.do",{},
			function(data) {
				var index, cardImage;

				if (data.dealerCards.length > 1) {
					document.getElementById('dealercards').innerHTML = '';
					for (index = 0; index < data.dealerCards.length; index += 1 ) {
						if (index === 0) {
							cardImage = "resources/images/"+ data.dealerCards[index].rank+ data.dealerCards[index].suit	+ ".png";
							$('#dealercards').append($('<img src= ' + cardImage + '>'));
						} else {
							cardImage = "resources/images/"	+ data.dealerCards[index].rank+ data.dealerCards[index].suit+ ".png";
							$('#dealercards').append($('<img src= ' + cardImage + '>').fadeIn(3000));
						}
					}
				}
				document.getElementById('playermessage').innerHTML="Total: " + data.playerHandValue;
				document.getElementById('dealermessage').innerHTML="Total: " + data.dealerHandValue;

				document.getElementById('gamemessages').style.visibility = 'visible';
				document.getElementById('gamemessages').innerHTML = data.gameMessage;
				
				toggleButtonVisibility();
				document.getElementById('credits').innerHTML = "총 잔액: " + data.player.money;
				document.getElementById('bet').innerHTML = "배팅 금액: "+ data.playerBet;
ss
				document.getElementById('playerstandsbutton').disabled = false;
				document.getElementById('hitplayerbutton').disabled = false;
				document.getElementById('playerdoublesbutton').disabled = false;
			});
}