/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setAutocomplete()
{
if (navigator.userAgent.toLowerCase().indexOf('chrome') >= 0 && navigator.userAgent.toLowerCase().indexOf('mozilla')) 
{
setTimeout(function () {
        document.getElementById('formulario').setAttribute('autocomplete', 'off'); 
}, 100);
}
}


