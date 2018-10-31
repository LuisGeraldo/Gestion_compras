/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
          $('.only-letter').on('keypress', function (e) {
           tecla = (document.all) ? e.keyCode : e.which;
                if (tecla === 8) {
                    return true;
                }
                
                patron = /[ A-Za-z\s]/;
                tecla_final = String.fromCharCode(tecla);
                return patron.test(tecla_final);
            });
            
          $('.only-number').on('input', function () {
                  this.value = this.value.replace(/[^0-9]/g, '');
            });  
});
