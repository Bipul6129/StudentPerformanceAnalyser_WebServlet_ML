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
        					if(response.message=="incorrect"){
        						Swal.fire({
    								  icon: 'error',
    								  title: 'Oops...',
    								  text: 'Incorrect username or password',
    								  footer: ''
    								})
    							$("#loginbtn").show();
        					}else if(response.message=="correct"){
        						window.location.href="http://localhost:8080/sps_website/webpages/Home.jsp";
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