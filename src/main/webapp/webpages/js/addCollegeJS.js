function popdown(){
			document.querySelector(".center").style.top="-140%"
			document.querySelector("#blanker").style.opacity="100%"
		}
		document.querySelector("#addcourse").addEventListener("click",function(){
		    
		    document.querySelector(".center").style.top="50%"
		    document.querySelector("#blanker").style.opacity="40%"
		});
		document.querySelector(".close-btn").addEventListener("click",function(){
		    
		    document.querySelector(".center").style.top="-140%"
		    document.querySelector("#blanker").style.opacity="100%"
		});
		
		$(document).ready(function(){
			loadColleges();
			function loadColleges(){
				$.ajax({
					url:'myCollege',
					method:'get',
					dataType:'json',
					success:function(response){
						console.log(response)
						j=0;
						for(i=0;i<response.length;i++){
							j++;
							console.log(response[i].CollegeName);
							$("#tableBody").append("<tr><td>"+j+"</td><td>"+response[i].CollegeName.toUpperCase()+"</td><td>"+response[i].location.charAt(0).toUpperCase()+response[i].location.slice(1)+"</td><td><button class='viewCourse' value='"+response[i].CollegeId+"'>View</button></td><td><button class='dangerButton removeCollegeBtn' value="+response[i].CollegeId+">Remove</button></td></tr>")
						}
					},
					error:function(){
						
					}
				});
			}
			
			$("#tableBody").on("click",".viewCourse",(function(){
				
				courseId = $(this).val();
				console.log(courseId);
				var lists="";
				$.ajax({
					url:'myCourse',
					method:'get',
					dataType:'json',
					success:function(response){
						console.log(response);
						for(i=0;i<response.length;i++){
							if(courseId==response[i].courseCollegeId){
								console.log(response[i].courseName);
								lists=lists+"<li>"+response[i].courseName+"</li>"
							}
						}
						Swal.fire('<ol>'+lists+'</ol')
					},
					error:function(){
						
					}
				})
				
			}));
			
			$("#insertCollegeForm").submit(function(eent){
				event.preventDefault();
				if($("#collegeName").val()==""||$("#collegeLocation").val()==""){
					Swal.fire({
						  icon: 'error',
						  title: 'Oops...',
						  text: 'Insertion failed',
						  footer: ''
						})
				}else{
					var formData = $(this).serialize();
					$.ajax({
						url:'myCollege',
						type:'post',
						data:formData,
						success:function(response){
							console.log(response);
							if(response.message=="inserted"){
								Swal.fire(
										  'College Added Successfully',
										  'You have sucessfully added new college',
										  'success'
										)
								
								$("#tableBody").empty();
								popdown();
								loadColleges();
							}else if(response.message =="notinserted"){
								Swal.fire({
									  icon: 'error',
									  title: 'Oops...',
									  text: 'Insertion failed',
									  footer: ''
									})
							}
						},
						error:function(xhr,status,error){
							
						}
					})
				}
			})
			
			$("#tableBody").on('click','.removeCollegeBtn',function(){
				var collegeId = $(this).val();
				console.log(collegeId);
				const data = {
					collegeId : collegeId
				}
				const jsonData = JSON.stringify(data);
				Swal.fire({
				  title: 'Are you sure?',
				  text: "You won't be able to revert this!",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then((result) => {
				  if (result.isConfirmed) {
				    $.ajax({
						url:'myCollege',
						method:'delete',
						data:jsonData,
						success:function(response){
							if(response.message=="courseExists"){
								Swal.fire({
								  icon: 'error',
								  title: 'Oops...',
								  text: 'This college has course that exists!',
								  footer: '<a href="">Remove the courses of this college first?</a>'
								})
							}else if(response.message=="deleted"){
								Swal.fire(
								  'Removed Successfully!',
								  'You removed the college successfully!',
								  'success'
								)
								$("#tableBody").empty();	
								loadColleges();
								
							}else if(response.message=="notdeleted"){
								Swal.fire(
								  'Delete failed',
								  'Deletion of the college failed',
								  'question'
								)
							}
						},
						error:function(){
							
						}
						
					});
				    
				  }
				})
				
			})
			
			
			
		})