paypal.Buttons({
    style : {
        color: 'blue',
        shape: 'pill'
    },
    createOrder: function (data, actions) {
        return actions.order.create({
            purchase_units : [{
                amount: {
                    value: document.getElementById("amount").value
                }
            }]
        });
    },
    onApprove: function (data, actions) {
        return actions.order.capture().then(function (details) {
            console.log(details)
            window.location.replace("/projet3/paypal/success.jsp")
        })
    },
    onCancel: function (data) {
        window.location.replace("/projet3/paypal/Oncancel.jsp")
    }
}).render('#paypal-payment-button');

