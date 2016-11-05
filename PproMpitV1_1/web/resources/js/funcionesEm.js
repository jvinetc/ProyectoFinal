$(document).ready(function(){
   $('#rutProveedorNuevo').Rut({
  on_error: function(){
      alert('Rut incorrecto');
        $('#rutProveedorNuevo').val("");},
  format_on: 'keyup'
}); 
});


