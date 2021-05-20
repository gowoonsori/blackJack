function toggleButtonVisibility() {
	var startGameButton, betDropDown, hitPlayerButton, playerStandsButton, playerDoublesButton, playerSplitsButton;

	startGameButton = document.getElementById('startgamebutton');
	startGameButton.style.display = 'inline';

	betInput = document.getElementById('betInput');
	betInput.style.display = 'block';
	betInput.style.visibility = "visible";

	hitPlayerButton = document.getElementById('hitplayerbutton');
	hitPlayerButton.style.display = 'none';

	playerStandsButton = document.getElementById('playerstandsbutton');
	playerStandsButton.style.display = 'none';

	playerDoublesButton = document.getElementById('playerdoublesbutton');
	playerDoublesButton.style.display = 'none';

	playerSplitsButton = document.getElementById('playersplitsbutton');
	playerSplitsButton.style.display = 'none';

}