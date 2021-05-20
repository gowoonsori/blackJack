function playerSplits() {
	var hitPlayerButton, playerStandsButton, playerDoublesButton, playerSplitsButton;

	hitPlayerButton = document.getElementById('hitplayerbutton');
	hitPlayerButton.style.display = 'none';

	playerStandsButton = document.getElementById('playerstandsbutton');
	playerStandsButton.style.display = 'none';

	playerDoublesButton = document.getElementById('playerdoublesbutton');
	playerDoublesButton.style.display = 'none';

	playerSplitsButton = document.getElementById('playersplitsbutton');
	playerSplitsButton.style.display = 'none';

	$.getJSON(
			"split.do",{},
			function(data) {
				var playerCards, playerMessage, cardImage, leftHitPlayerButton, leftPlayerStandsButton, splitCardsLeft, splitCardsRight;

				playerCards = document.getElementById('playercards');
				playerCards.style.display = 'none';

				playerMessage = document.getElementById('playermessage');
				playerMessage.style.display = 'none';
				
				document.getElementById('gamemessages').style.visibility = 'visible';
				document.getElementById('gamemessages').innerHTML = data.gameMessage;
				
				splitCardsLeft = document.getElementById('splitcardsleft');
				splitCardsLeft.style.display = 'inline';
				
				splitCardsRight = document.getElementById('splitcardsright');
				splitCardsRight.style.display = 'inline';

				cardImage = "resources/images/"+ data.splitHand.splitLeftCards[0].rank	+ data.splitHand.splitLeftCards[0].suit + ".png";
				$('#splitcardsleft').append($('<img src= ' + cardImage + '>').fadeIn(2000));
											
				cardImage = "resources/images/"	+ data.splitHand.splitRightCards[0].rank	+ data.splitHand.splitRightCards[0].suit + ".png";
				$('#splitcardsright').append($('<img src= ' + cardImage + '>').fadeIn(	2000));

				leftHitPlayerButton = document.getElementById('lefthitplayerbutton');
				leftHitPlayerButton.style.display = 'inline';
				leftHitPlayerButton.disabled = false;

				leftPlayerStandsButton = document.getElementById('leftplayerstandsbutton');
				leftPlayerStandsButton.style.display = 'inline';
				leftPlayerStandsButton.disabled = false;
				
				document.getElementById('credits').innerHTML = "총 잔액: "+ data.player.money;
				document.getElementById('bet').innerHTML = "배팅 금액: "	+ data.playerBet;
			});
}