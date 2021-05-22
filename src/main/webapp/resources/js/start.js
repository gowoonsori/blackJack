function startGame() {
	var splitCardsLeft, splitCardsRight, playerCards, playerMessage;
	
	document.getElementById('startgamebutton').disabled = true;
	document.getElementById('betInput').disabled = true;
	document.getElementById('dealercards').innerHTML = '';
	document.getElementById('playercards').innerHTML = '';
	document.getElementById('gamemessages').innerHTML = '';
	document.getElementById('splitleftplayermessage').innerHTML = '';
	document.getElementById('splitrightplayermessage').innerHTML = '';
	document.getElementById('splitleftgamemessages').innerHTML = '';
	document.getElementById('splitrightgamemessages').innerHTML = '';
	document.getElementById('splitcardsleft').innerHTML = '';
	document.getElementById('splitcardsright').innerHTML = '';
	
	splitCardsLeft = document.getElementById('splitcardsleft');
	splitCardsLeft.style.display = 'none';
	
	splitCardsRight = document.getElementById('splitcardsright');
	splitCardsRight.style.display = 'none';

	playerCards = document.getElementById('playercards');
	playerCards.style.display = 'block';
	
	playerMessage = document.getElementById('playermessage');
	playerMessage.style.display = 'block';
	
	$.ajax({
		url: "start.do",
		data: {},
		type:"GET",
		dataType: "json"
	}).done(function(data) {
		var index, backOfCard, cardImage, startGameButton, hitPlayerButton, playerStandsButton, playerSplitsButton, dealerMessage, playerDoublesButton, betInput;
			
		document.getElementById('credits').innerHTML = "총 잔액: "	+ data.player.money;
		document.getElementById('bet').innerHTML = "배팅 금액: "+ data.playerBet;

		cardImage = "resources/images/" + data.dealerCards[0].rank+ data.dealerCards[0].suit + ".png";
		$('#dealercards').append($('<img src= ' + cardImage + '>').fadeIn(2000));

		backOfCard = "resources/images/BACKOFCARD.png";
		$('#dealercards').append($('<img src= ' + backOfCard + '>').fadeIn(2000));
				
		for (index = 0; index < data.playerCards.length; index += 1) {
			cardImage = "resources/images/"+ data.playerCards[index].rank+ data.playerCards[index].suit + ".png";
			$('#playercards').append($('<img src= ' + cardImage + '>').fadeIn(2000));
		}
				
		document.getElementById('playermessage').innerHTML = "Total: "+ data.playerHandValue;
		document.getElementById('dealermessage').innerHTML = "Total: "+ data.dealerHandValue;
		dealerMessage = document.getElementById('dealermessage');
		dealerMessage.style.visibility = 'visible';
		playerMessage = document.getElementById('playermessage');
		playerMessage.style.visibility = 'visible';
				
		startGameButton = document.getElementById('startgamebutton');
		startGameButton.style.display = 'none';		
		betInput = document.getElementById('betInput');
		betInput.style.visibility="hidden";
		hitPlayerButton = document.getElementById('hitplayerbutton');
		hitPlayerButton.style.display = 'inline';
		hitPlayerButton.disabled = false;
		playerStandsButton = document.getElementById('playerstandsbutton');
		playerStandsButton.style.display = 'inline';
		playerStandsButton.disabled = false;
		playerDoublesButton = document.getElementById('playerdoublesbutton');
		playerDoublesButton.style.display = 'inline';
		playerDoublesButton.disabled = false;
				
		if (data.playerCanSplit) {
			playerSplitsButton = document.getElementById('playersplitsbutton');
			playerSplitsButton.style.display = 'inline';
			playerSplitsButton.disabled = false;
		}
	}).fail(function(data,status,err){
			alert(data.responseText);
			if(data.status == 400){
				document.getElementById('playerdoublesbutton').disabled = true;
			}else if(data.status == 412){
				window.location.replace("/blackjack/");
			}
	});
	document.getElementById('startgamebutton').disabled = false;
	document.getElementById('betInput').disabled = false;
}