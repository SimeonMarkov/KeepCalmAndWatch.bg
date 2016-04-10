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

function validateLogin(){
	var username = document.forms["loginForm"]["username"].value;
    var password = document.forms["loginForm"]["password"].value;
    
    if(username == "" || password == ""){
    	alert("Моля попълнете всички полета!");
    	return false;
    } 
}

function validateLogin2(){
	var username = document.forms["loginForm2"]["username"].value;
    var password = document.forms["loginForm2"]["password"].value;
    
    if(username == "" || password == ""){
    	alert("Моля попълнете всички полета!");
    	return false;
    } 
}

function validateUpdate(){
	var channelName = document.forms["updateForm"]["channelName"].value;
    var password = document.forms["updateForm"]["password"].value;
    var email = document.forms["updateForm"]["email"].value;
    var newPassword = document.forms["updateForm"]["newPassword"].value;
    var newPasswordConf = document.forms["updateForm"]["newPasswordConf"].value;
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    if(channelName == "" || password == "" || email == ""){
    	alert("Моля попълнете всички полета!");
    	return false;
    } 
    if(newPassword != "" || newPasswordConf != ""){
    	if(newPassword != newPasswordConf){
    		alert("Новите пароли не съвпадат!");
        	return false;
    	}
    }
    if(!re.test(email)){
    	alert("Въведете валиден имейл!");
    	return false;
    } 
}