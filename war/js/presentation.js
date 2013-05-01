$(document).ready(function() {

	$("body").css("display", "none");

    $("body").fadeIn(600);

	$(document).keydown(function(e){
		if (e.keyCode == 37) { 
		   $('a#prev').click();
		   return false;
		} else if (e.keyCode == 39) {
			$('a#next').click();
			return false;
		}
	});
	
	$('a#prev').click(function(event){
		event.preventDefault();

		$("body").fadeOut(600, function(){
			window.location = $('a#prev').attr('href');
		});		
	});

	$("a#next").click(function(event){
		event.preventDefault();

		$("body").fadeOut(600, function(){
			window.location = $('a#next').attr('href');
		});		
	});
});