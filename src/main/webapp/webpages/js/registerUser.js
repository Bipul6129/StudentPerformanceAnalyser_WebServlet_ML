$(document).ready(function(){
    		$("#spinicon").hide();
    		$("#registerForm").submit(function(event){
    			$("#spinicon").show();
    			event.preventDefault();
    			error = false;
    			var errormsg = "";
    			if($("#username").val().length<4){
    				errormsg="Username and password length must be greater than 6";
    				error=true;
    			}
    			if($("#password").val().length<6){
    				errormsg="Username and password length must be greater than 6";
    				error=true;
    			}
    			if(!error){
    				$("#formregisterbtn").hide();
    				var formData = $(this).serialize();
        			$.ajax({
        				url:"registeruser",
    					type:"POST",
    					data:formData,
    					success:function(response){
    						console.log(response.message);
    						console.log(response);
    						if(response.message=="registered"){
    							$("#formregisterbtn").show();
    							$("#spinicon").hide();
    							Swal.fire(
    									  'Registered Successfully',
    									  'You have registered sucessfully',
    									  'success'
    									)
    						}else if(response.message=="notregistered"){
    							$("#formregisterbtn").show();
    							$("#spinicon").hide();
    							Swal.fire({
    								  icon: 'error',
    								  title: 'Oops...',
    								  text: 'Register failed',
    								  footer: ''
    								})
    						}
    					},
    					error:function(xhr,status,error){
							$("#formregisterbtn").show();
    						$("#spinicon").hide();
    						Swal.fire({
    								  icon: 'error',
    								  title: 'Oops...',
    								  text: 'Connection failed',
    								  footer: ''
    								})
    					}
        			});
    			}else{
    				$("#spinicon").hide();
    				Swal.fire({
						  icon: 'error',
						  title: 'Oops...',
						  text: errormsg,
						  footer: ''
						});
    			}
    			
    		})
    	})