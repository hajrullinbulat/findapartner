$(function() {

	//SVG Fallback
	if(!Modernizr.svg) {
		$("img[src*='svg']").attr("src", function() {
			return $(this).attr("src").replace(".svg", ".png");
		});
	};

	//E-mail Ajax Send
	//Documentation & Example: https://github.com/agragregra/uniMail
	$("form").submit(function() { //Change
		var th = $(this);
		$.ajax({
			type: "POST",
			url: "mail.php", //Change
			data: th.serialize()
		}).done(function() {
			alert("Thank you!");
			setTimeout(function() {
				// Done Functions
				th.trigger("reset");
			}, 1000);
		});
		return false;
	});

});
$(document).ready(function() {
	$('.popup-with-move-anim').magnificPopup({
		type: 'inline',

		fixedContentPos: false,
		fixedBgPos: true,

		overflowY: 'auto',

		closeBtnInside: true,
		preloader: false,
		
		midClick: true,
		removalDelay: 300,
		mainClass: 'my-mfp-slide-bottom'
	});
});

var $head = $( '#ha-header' );
$( '.ha-waypoint' ).each( function(i) {
    var $el = $( this ),
        animClassDown = $el.data( 'animateDown' ),
        animClassUp = $el.data( 'animateUp' );

    $el.waypoint( function( direction ) {
        if( direction === 'down' && animClassDown ) {
            $head.attr('class', 'ha-header ' + animClassDown);
        }
        else if( direction === 'up' && animClassUp ){
            $head.attr('class', 'ha-header ' + animClassUp);
        }
    }, { offset: '100%' } );
} );

var $head = $( '#ha-header' );
$( '.ha-waypoint' ).each( function(i) {
    var $el = $( this ),
        animClassDown = $el.data( 'animateDown' ),
        animClassUp = $el.data( 'animateUp' );
 
    $el.waypoint( function( direction ) {
        if( direction === 'down' && animClassDown ) {
            $head.attr('class', 'ha-header ' + animClassDown);
        }
        else if( direction === 'up' && animClassUp ){
            $head.attr('class', 'ha-header ' + animClassUp);
        }
    }, { offset: '100%' } );
} );

$('#ex-basic').picker();