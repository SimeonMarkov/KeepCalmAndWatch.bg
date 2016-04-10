$(document).ready(function() {

	// this will be fired once the page is fully loaded

	$('#comment-post-btn').click(function() {
		comment_post_btn_click();
	});
});

function comment_post_btn_click() {

	// Text within textarea which the person has entered
	var _channelName = $("#channelName").val();
	var _text = $("#comment-post-text").val();
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	var _videoId = $.urlParam('v');
	//var _comment = $('#comment-post-text').val();
	//var _channelName = $('#channelName').val();
	
	

	if (_channelName != "") {
		if (_text.length > 0) {
			$('.comment-insert-container').css('border', '1px solid #e1e1e1');

			$.ajax({
				channelName : _channelName,
				comment : _text,
				videoId: _videoId,
			}

			).error(function() {
				console.log("Error: ");
				console.log("ResponseText: " + _text + ";" + _channelName + " " + _videoId);
				console.log("video is:" + $.urlParam('v'));
			}).success(function() {
				comment_insert(_text, _channelName);
				comment_submit(_text,_channelName,_videoId);
				console.log("Success!");
				console.log("ResponseText: " + _text + ";" + _channelName + ";" + _videoId);
			});
			// proceed with out ajax callback
		} else {
			// the textarea is empty,lets but a red border on it
			$('.comment-insert-container').css('border', '1px solid #ff0000');
			alert('Sorry,you cannot submit an empty comment');
			console.log("The text area was empty");
		}
	} else {
	}
	// remove the text from the textarea, ready for another comment
	// possibly
	$('#comment-post-text').val("");
}

function comment_insert(_comment, _channel) {
	var t = '';
	t += '<div class="user-img">';
	t += '<img src="images/photo.png" class="user-img-pic" />';
	t += '</div>';
	t += '<div class="comment-body">';
	t += '<h3 class="username-field">';
	t += _channel;
	t += '</h3>';
	t += '<div class="comment-text">';
	t += _comment;
	t += '</div>';
	t += '</div>';
	t += '</div>';

	$('#newest').prepend(t);
}

function comment_submit(text,channelName,videoId){
	// Text within textarea which the person has entered
	var _channelName = $("#channelName").val();
	var _text = $("#comment-post-text").val();
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	var _videoId = $.urlParam('v');
	//var _comment = $('#comment-post-text').val();
	//var _channelName = $('#channelName').val();
	dataString={"text":text,"channelName":channelName,"videoId":videoId};
	$.ajax({
		url: "submitComment",
		type:"POST",
		data: dataString,
		contentType:"application/json",
		dataType:"json"
		
	}

	).error(function(data) {
		console.log("Error from submit: ");
		console.log("ResponseText: " + data.text);
		console.log("video is:" + $.urlParam('v'));
	}).success(function(data) {
		console.log("Success with submit!");
		console.log("ResponseText: " + data.text + ";" + data.channelName + ";" + data.videoId);
	});
	
}
