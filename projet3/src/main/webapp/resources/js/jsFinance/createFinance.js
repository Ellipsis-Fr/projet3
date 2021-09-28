/**
 * 
 */
 $(function () {
	 "use strict";
	 	
		 // Appelle API avec ajax
	    $.ajax({
	        type: 'GET',
	        dataType: "json",
	        contentType: "application/json",
	        url: "getAllFinance", //TODO: ici
	        success: function (result) {
	           console.log('Success : ', result);
	        //   showFinanceInDoughnut(result);
	        }, 
	        error: function (error) {
			 console.log('Error : ', error);
			}
	    });
		
	
	//function showFinanceInDoughnut(result) {
	  var maxAmountToFinance = 50000;
	 document.getElementById('montantTotale').innerHTML = "50 000 €";	
	// document.getElementById('montantActuel').innerHTML = calculMontantActuelle(result) + ' €';
	 var ctx = document.getElementById("total-sale").getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'doughnut',
                
                data: {
                    labels: ["Montant totale", " Montant actuelle"],
                    datasets: [{
                        backgroundColor: [
	                         "rgba(89, 105, 255, 0.5)",
	                         "rgba(255, 64, 123,0.5)"
                        ],
                        data: [maxAmountToFinance/*, calculMontantActuelle(result)*/]
                    }]
                },
                options: {
                    legend: {
                        display: false

                    }
                }

            });
   //   }
   
    // Calcul le montant totale actuele
  	function calculMontantActuelle(resultTmp) {
		var totaleAmount = 0.0;
		if(resultTmp) {
			$.each(resultTmp, function (i, obj) {
				totaleAmount+=obj.amount;
			});
		}
		return totaleAmount;
	}
	
});
