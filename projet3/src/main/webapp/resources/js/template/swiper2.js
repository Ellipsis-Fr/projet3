let interval;

function restartCarrousel(){
	stopCarrousel();
	interval = setInterval(function(){ 
	    document.getElementById("idswiper").click();
	}, 5000);
}

function stopCarrousel() {
	if (interval) {
		clearInterval(interval);
	}
}

restartCarrousel();
const elements = document.getElementsByClassName('swiper-button');
for(let element of elements) {
	element.addEventListener('click', function() {
		restartCarrousel();
	});
}

var causesSlider = new Swiper('.causes-slider', {
		loop: true,
        slidesPerView: 3,
        spaceBetween: 30,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
        },
        breakpoints: {
            1200: {
                slidesPerView: 2,
                spaceBetween: 30,
            },
            768: {
                slidesPerView: 1,
                spaceBetween: 0,
            }
        }
    } );   