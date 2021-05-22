function splitRightHitPlayer() {
	document.getElementById('rightplayerstandsbutton').disabled = true;
	document.getElementById('righthitplayerbutton').disabled = true;

	$.ajax({
		url: "splitRightHit.do",
		data: {},
		type:"GET",
		dataType: "json"
	}).done(function(data) {
		var lastCardInArray, cardImage, rightHitPlayerButton, rightPlayerStandsButton, startGameButton, betInput;

		lastCardInArray = data.splitHand.splitRightCards.length - 1;
		cardImage = "resources/images/"+ data.splitHand.splitRightCards[lastCardInArray].rank+ data.splitHand.splitRightCards[lastCardInArray].suit	+ ".png";
		$('#splitcardsright').append($('<img src= ' + cardImage + '>').fadeIn(2000));

		if (data.splitHand.splitRightBust=== true) {
			document.getElementById('splitrightgamemessages').style.visibility = 'visible';
			document.getElementById('splitrightgamemessages').innerHTML = data.splitHand.splitRightGameMessage;
	
			rightHitPlayerButton = document.getElementById('righthitplayerbutton');
			rightHitPlayerButton.style.display = 'none';

			rightPlayerStandsButton = document.getElementById('rightplayerstandsbutton');
			rightPlayerStandsButton.style.display = 'none';
										
			startGameButton = document.getElementById('startgamebutton');
			startGameButton.style.display = 'inline';

			betInput = document.getElementById('betInput');
			betInput.style.display = 'block';
					
			document.getElementById('gamemessages').style.visibility = 'visible';
			document.getElementById('gamemessages').innerHTML = data.gameMessage;
					
			document.getElementById('credits').innerHTML = "총 잔액: "	+ data.player.money;
					
			if (data.splitHand.splitLeftBust === false){
				splitRightPlayerStands();
			}	
		}
		document.getElementById('splitrightplayermessage').style.visibility = 'visible';
		document.getElementById('splitrightplayermessage').innerHTML="Total: " + data.splitHand.splitRightHandValue;

		document.getElementById('rightplayerstandsbutton').disabled = false;
		document.getElementById('righthitplayerbutton').disabled = false;
	}).fail(function(data,status,err){
			alert(data.responseText);
			if(data.status == 400){
				document.getElementById('playerdoublesbutton').disabled = true;
			}else if(data.status == 412){
				window.location.replace("/blackjack/");
			}
	});
}