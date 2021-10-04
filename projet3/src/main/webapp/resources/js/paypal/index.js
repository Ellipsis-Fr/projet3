paypal.Buttons({
    style : {
        color: 'blue',
        shape: 'pill'
    },
    createOrder: function (data, actions) {
        return actions.order.create({
            purchase_units : [{
                amount: {
                    value: '10.0' //ici gère le montant du don  voir pour récupéré le montant mis dans la case montant
                }
            }]
        });
    },
    onApprove: function (data, actions) {
        return actions.order.capture().then(function (details) {
            console.log(details)
            window.location.replace("/webapp/paypal/Oncancel.jsp")
        })
    },
    onCancel: function (data) {
        window.location.replace("webapp/paypal/success.jsp")
    }
}).render('#paypal-payment-button');