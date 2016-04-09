$(document).ready(function() {

	// this will be fired once the page is fully loaded

	$('#comment-post-btn').click(function() {
		comment_post_btn_click();
	});
});

function comment_post_btn_click() {

	// Text within textarea which the person has entered
	var _comment = $('#comment-post-text').val();
	var _channelName = $('#channelName').val();

	if (_channelName != "") {
		if (_comment.length > 0) {
			$('.comment-insert-container').css('border', '1px solid #e1e1e1');

			$.ajax({
				task : "comment_insert",
				channelName : _channelName,
				comment : _comment
			}

			).error(function() {
				console.log("Error: ");
			}).success(function() {
				comment_insert(_comment, _channelName);
				console.log("ResponseText: " + _comment + ";" + _channelName);
			});
			// proceed with out ajax callback
		} else {
			// the textarea is empty,lets but a red border on it
			$('.comment-insert-container').css('border', '1px solid #ff0000');
			console.log("The text area was empty");
		}
	} else {
		alert("Please log in in order to comment!");
	}
	// remove the text from the textarea, ready for another comment
	// possibly
	$('#comment-post-text').val("");
}

function comment_insert(_comment, _channel) {
	var t = '';
	t += '<li class="comment-holder" id="_1">';
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
	t += '</li>';

	$('.comments-holder-ul').prepend(t);
}