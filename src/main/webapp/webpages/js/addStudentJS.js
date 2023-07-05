	$(document).ready(function(){
			$.ajax({
				url:'myCourse',
				method:'get',
				success:function(response){
					console.log(response);
					var options="";
					for(i=0;i<response.length;i++){
						options = options+"<option value='"+response[i].courseId+"'>"+response[i].courseName+"_"+response[i].courseCollege+"_"+response[i].courseSemester+"</option>";
					}
					$('#courseSelect').html(options);
					
				},
				error:function(){
					
				}
			})
			
			$("#studentAddForm").on('submit',function(event){
				event.preventDefault();
				var formData = $(this).serialize();
				if($("#studentName").val()==""||$("#studentAge").val()==""||$("#studentEmail").val()==""){
					Swal.fire({
						  icon: 'error',
						  title: 'Oops...',
						  text: 'Fill every entry',
						  footer: '<a href="">Tried adding course?</a>'
						})
				}else{
					$.ajax({
						url:'myStudent',
						type:'post',
						data:formData,
						success:function(response){
							console.log(response);
							if(response.message=="inserted"){
								Swal.fire(
										  'Success!',
										  'Student added!',
										  'success'
										)
							}else if(respones.message=="notinserted"){
								Swal.fire({
									  icon: 'error',
									  title: 'Oops...',
									  text: 'Something went wrong!',
									  footer: '<a href="">Try contacting support?</a>'
									})
							}
						},
						error:function(){
							Swal.fire({
								  icon: 'error',
								  title: 'Oops...',
								  text: 'Something went wrong!',
								  footer: ''
								})
						}
					})
				}
				console.log(formData);
			})
		});