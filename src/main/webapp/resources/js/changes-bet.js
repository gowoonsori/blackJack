function playerChangesBet(value) {
	document.getElementById('startgamebutton').disabled = true;
	document.getElementById('betInput').disabled = true;
	
	$.ajax({
		url:"change.do",
		data:{money: value},
		type:"POST",
		dataType: "json"
	}).done(function(data) {
		document.getElementById('bet').innerHTML = "배팅 금액: "+ data.playerBet;
	}).fail(function(data,status,err){
		alert(data.responseText);
		document.getElementById('startgamebutton').disabled = true;
		document.getElementById('betInput').value=10000;
	});
	
	document.getElementById('startgamebutton').disabled = false;
	document.getElementById('betInput').disabled = false;
}