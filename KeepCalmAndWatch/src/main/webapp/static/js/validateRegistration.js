function validateForm() {
	
		var username = document.forms["regform"]["username"].value;
	    var email = document.forms["regform"]["email"].value;
	    var password = document.forms["regform"]["password"].value;
	    var passwordconfirmation = document.forms["regform"]["passwordconfirmation"].value;
	    var channelName = document.forms["regform"]["channelName"].value;
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    
	    if(username == "" || email == "" || password == "" || passwordconfirmation == "" || channelName == ""){
	    	alert("Моля попълнете всички полета!");
	    	return false;
	    } else if ((password.length) < 6) {
			alert("Паролата трябва да е поне 6 символа!");
			return false;	
		} else if (!(password).match(passwordconfirmation)) {
			alert("Паролите не съвпадат!");
			return false;
		} else if(!re.test(email)){
			alert("Невалиден email!");
			return false;
		}
}