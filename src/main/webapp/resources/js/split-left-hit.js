function splitLeftHitPlayer() {
	document.getElementById('leftplayerstandsbutton').disabled = true;
	document.getElementById('lefthitplayerbutton').disabled = true;

	$.getJSON(
			"splitLeftHit.do",{},
			function(data) {
				var lastCardInArray, cardImage, leftHitPlayerButton, leftPlayerStandsButton, rightHitPlayerButton, rightPlayerStandsButton;

				lastCardInArray = data.splitHand.splitLeftCards.length - 1;
				cardImage = "resources/images/"	+ data.splitHand.splitLeftCards[lastCardInArray].rank+ data.splitHand.splitLeftCards[lastCardInArray].suit
						+ ".png";
				$('#splitcardsleft').append($('<img src= ' + cardImage + '>').fadeIn(2000));

				if (data.splitHand.splitLeftBust === true) {
					document.getElementById('splitleftgamemessages').style.visibility = 'visible';
					document.getElementById('splitleftgamemessages').innerHTML = data.splitHand.splitLeftGameMessage;
					
					leftHitPlayerButton = document.getElementById('lefthitplayerbutton');
					leftHitPlayerButton.style.display = 'none';

					leftPlayerStandsButton = document.getElementById('leftplayerstandsbutton');
					leftPlayerStandsButton.style.display = 'none';					
					
					rightHitPlayerButton = document	.getElementById('righthitplayerbutton');
					rightHitPlayerButton.style.display = 'inline';

					rightPlayerStandsButton = document.getElementById('rightplayerstandsbutton');
					rightPlayerStandsButton.style.display = 'inline';
					
					document.getElementById('credits').innerHTML = "총 잔액: "+ data.player.money;
				}
				
				document.getElementById('splitleftplayermessage').style.visibility = 'visible';
				document.getElementById('splitleftplayermessage').innerHTML="Total: " + data.splitHand.splitLeftHandValue;
			
				document.getElementById('leftplayerstandsbutton').disabled = false;
				document.getElementById('lefthitplayerbutton').disabled = false;

			});	
}