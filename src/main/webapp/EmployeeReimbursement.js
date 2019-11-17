/**
 * 
 */
function addEvent(el, type, handler) {

    if (el.attachEvent) el.attachEvent('on'+type, handler); else el.addEventListener(type, handler);

}
//Here is where you can define your submit button for login page
//Approve or deny function button for pending requests and back button
let buttonClicks = document.querySelectorAll('button');
addEvent(el, 'click', function(){ document.write('Clicked!'); })