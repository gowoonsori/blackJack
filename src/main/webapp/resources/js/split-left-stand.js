function splitLeftPlayerStands() {
	document.getElementById('leftplayerstandsbutton').disabled = true;
	document.getElementById('lefthitplayerbutton').disabled = true;

	$.getJSON(
			"splitLeftstand.do",{},
			function(data) {
				var leftHitPlayerButton, leftPlayerStandsButton, rightHitPlayerButton, rightPlayerStandsButton;

				leftHitPlayerButton = document.getElementById('lefthitplayerbutton');
				leftHitPlayerButton.style.display = 'none';

				leftPlayerStandsButton = document.getElementById('leftplayerstandsbutton');
				leftPlayerStandsButton.style.display = 'none';
							
				rightHitPlayerButton = document.getElementById('righthitplayerbutton');
				rightHitPlayerButton.style.display = 'inline';

				rightPlayerStandsButton = document.getElementById('rightplayerstandsbutton');
				rightPlayerStandsButton.style.display = 'inline';
				
				document.getElementById('splitleftplayermessage').style.visibility = 'visible';
				document.getElementById('splitleftplayermessage').innerHTML="Total: " + data.splitHand.splitLeftHandValue;
				
				document.getElementById('leftplayerstandsbutton').disabled = false;
				document.getElementById('lefthitplayerbutton').disabled = false;
			});
			
}