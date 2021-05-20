function splitRightPlayerStands() {
	document.getElementById('rightplayerstandsbutton').disabled = true;
	document.getElementById('righthitplayerbutton').disabled = true;

	$.getJSON(
			"splitRightstand.do",{},
			function(data) {
				var index, cardImage, rightHitPlayerButton, rightPlayerStandsButton, startGameButton, betInput;
				document.getElementById('dealercards').innerHTML = '';
				for (index = 0; index < data.dealerCards.length; index += 1) {
					if (index === 0) {
						cardImage = "resources/images/"+ data.dealerCards[index].rank+ data.dealerCards[index].suit+ ".png";
						$('#dealercards').append(	$('<img src= ' + cardImage + '>'));
					} else {
						cardImage = "resources/images/"+ data.dealerCards[index].rank+ data.dealerCards[index].suit+ ".png";
						$('#dealercards').append($('<img src= ' + cardImage + '>').fadeIn(3000));
					}
				}
				document.getElementById('dealermessage').innerHTML="Total: " + data.dealerHandValue;

				document.getElementById('gamemessages').style.visibility = 'visible';
				document.getElementById('gamemessages').innerHTML = data.gameMessage;
				
				document.getElementById('splitleftgamemessages').style.visibility = 'visible';
				document.getElementById('splitleftgamemessages').innerHTML = data.splitHand.splitLeftGameMessage;				
				document.getElementById('splitrightgamemessages').style.visibility = 'visible';
				document.getElementById('splitrightgamemessages').innerHTML = data.splitHand.splitRightGameMessage;
				
				document.getElementById('splitrightplayermessage').style.visibility = 'visible';
				document.getElementById('splitrightplayermessage').innerHTML="Total: " + data.splitHand.splitRightHandValue;
				
				document.getElementById('credits').innerHTML = "총 잔액: "+ data.player.money;
				
				startGameButton = document.getElementById('startgamebutton');
				startGameButton.style.display = 'inline';

				betInput = document.getElementById('betInput');
				betInput.style.display = 'block';

				rightHitPlayerButton = document.getElementById('righthitplayerbutton');
				rightHitPlayerButton.style.display = 'none';

				rightPlayerStandsButton = document.getElementById('rightplayerstandsbutton');
				rightPlayerStandsButton.style.display = 'none';

				document.getElementById('rightplayerstandsbutton').disabled = false;
				document.getElementById('righthitplayerbutton').disabled = false;
			});		
}