$(document).ready(function(){
    		$("#loginform").submit(function(event){
				$('#loginbtn').hide();
    			event.preventDefault();
    			emptyvalue=false;
    			var formData = $(this).serialize();
    			if($("#username").val().length==0||$("#password").val().length==0){
    				emptyvalue=true;
    				$("#loginbtn").show();
    			}
    			if(!emptyvalue){
    				$.ajax({
        				url:'loginuser',
        				method:'post',
        				data:formData,
        				success:function(response){
        					console.log(response);
        					if(response.message==="incorrect"){
        						Swal.fire({
    								  icon: 'error',
    								  title: 'Oops...',
    								  text: 'Incorrect username or password',
    								  footer: ''
    								})
    							$("#loginbtn").show();
        					}
        					if(response.message==="correct"){
        						window.location.href="Home.jsp";
        					}
        					if(response.message==="admincorrect"){
								console.log("hit");
								window.location.href="AdminPage.jsp";
							}
        				},
        				error:function(){
        					console.log("error occured");
        					$("#loginbtn").show();
        					
        				}
        			});
    			}
    			
    		})
})