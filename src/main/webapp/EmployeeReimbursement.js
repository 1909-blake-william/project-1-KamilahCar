/**
 * 
 */
function addEvent(el, type, handler) {

    el.addEventListener(type, handler);

}

function login(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const credential = {
        username,
        password
    };

    fetch('http://localhost:8080/Project1/login', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        credentials: 'include', // put credentials: 'include' on every request to use session info
        body: JSON.stringify(credential)
    })
    .then(resp => {
        if(resp.status === 201) {
            // redirect
            console.log('navigate to users')
            window.location = '/ActualHomePage.html';
        } else {
            document.getElementById('error-message').innerText = 'Failed to login';
        }
    })

/*Do filtering on server side*/
//Here is where you can define your submit button for login page
//Approve or deny function button for pending requests and back button
}