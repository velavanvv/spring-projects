
const signupForm = document.getElementById("registerform");

signupForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const usernameInput = document.getElementById("customerName");
    const emailInput = document.getElementById("customerEmail");
    const passwordInput = document.getElementById("customerPassword");
    const phoneInput = document.getElementById("customerNumber");
  


    const username = usernameInput.value;
    const email = emailInput.value;
    const password = passwordInput.value;
    const phone = phoneInput.value;
   
    const data = { customerName: username, customerPassword: password, customerEmail: email, customerNumber: phone};
    console.log(data)
    if (email && password && username != "") {
        fetch("/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })
            .then(res =>{ return res.json()})
            .then(data => {
                console.log(data)
                if (data.success) {
                alert(data.message);
                    


                    setTimeout(() => {
                        window.location.href = "/log"
                    }, 1000)
                } else {
                     alert(data.message);

               
                }

            });

    } else {
    
            alert("please fill all details");
        
        
    }

});