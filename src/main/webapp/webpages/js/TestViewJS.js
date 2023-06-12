		document.querySelector("#addTestBtn").addEventListener("click",function(){
		    
		    document.querySelector(".center").style.top="50%"
		    document.querySelector("#blanker").style.opacity="40%"
		});
		document.querySelector(".close-btn").addEventListener("click",function(){
		    
		    document.querySelector(".center").style.top="-140%"
		    document.querySelector("#blanker").style.opacity="100%"
		});
		
		function popDown(){
			document.querySelector(".center").style.top="-140%"
			document.querySelector("#blanker").style.opacity="100%"
		}
		
		$(document).ready(function(){
			
			
			
			var courseArray=[];
			var courseIdArray=[];
			$.ajax({
				url:'myCourse',
				method:'get',
				success:function(response){
					
					var options = "";
					for(i=0;i<response.length;i++){
						
						options=options+"<option value='"+response[i].courseId+"'>"+response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")</option>";
						courseArray.push(response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")");
						courseIdArray.push(response[i].courseId);
					}
					$("#selectCourse").html(options);
					$('#insertTestCourse').html(options);
					
					var dropdown = $('#selectCourse').children('option');
					if(dropdown.length == 0){
						
					}else{
						var courseId2 = $('#selectCourse').val();
						const data2 = {
								courseId:courseId2
						}
						$(".courseHead").empty();
						$(".courseHead").html($('#selectCourse option:selected').text());
						getTests(data2);
					}
					
					
				},
				error:function(){
					
				}
			})
			
			$("#getTestBtn").on('click',function(){
				var dropdown = $('#selectCourse').children('option');
				var course_id=$('#selectCourse').val();
				if(dropdown.length == 0){
						Swal.fire('Try adding course')
					}else{
						var courseId2 = $('#selectCourse').val();
						const data2 = {
								courseId:courseId2
						}
						$(".courseHead").empty();
						$(".courseHead").html($('#selectCourse option:selected').text());
						getTests(data2);
					}
			})
			
			$("#insertTestForm").on('submit',function(event){
				event.preventDefault();
				var selectedCourse = $('#insertTestCourse').val();
				if($('#testName').val()==""||$('#fullMarks').val()==""||$('#passMarks')==""){
					Swal.fire({
						  icon: 'error',
						  title: 'Oops...',
						  text: 'Fill every field',
						  footer: ''
						})
				}else{
					
					var formData = $(this).serialize();
					console.log(formData);
					$.ajax({
						url:'myTest',
						method:'post',
						data:formData,
						success:function(response){
							if(response.message=="inserted"){
								swal("Success!", "You inserted the test!", "success");
								popDown();
								var data={
										courseId:selectedCourse
								}
								getTests(data);
								
							}else if(response.message=="notinserted"){
								swal ( "Oops" ,  "Something went wrong!" ,  "error" )
							}
						},
						error:function(){
							swal ( "Oops" ,  "Something went wrong!" ,  "error" )
						}
					})
				}
			})
			
			$(document).on('click','.dangerButton',function(){
				var testId = $(this).val();
				var courseId = $('#selectCourse').val();
				
				const data = {
						test_id:testId,
						course_id:courseId
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
						deleteTest(jsonData,courseId);
					  }
					})
				
			})
			
			$(document).on('click','.editBtn',function(){
				var testEdit = $(this).data('values').split(',');
				console.log(testEdit);
				
				Swal.fire({
					title:'Edit Test',
					html:'<input id="swalName" value="'+testEdit[0]+'" placeholder="Enter Test Name"/><br>'+
						 '<input id="swalfull" value="'+testEdit[1]+'" placeholder="Enter Full Marks"/><br>'+
						 '<input id="swalpass" value="'+testEdit[2]+'" placeholder="Enter Pass Marks"/><br>'+
						 '<input type="hidden" value="'+testEdit[3]+'" id="swalTestId" />'+
						 '<input type="hidden" value="'+testEdit[4]+'" id="swalCourseId"/>',
						 
					showCancelButton:true,
					confirmButtonText:'SAVE',
					preConfirm:function(){
						var testName = $("#swalName").val();
						var fullMarks = $("#swalfull").val();
						var passMarks = $("#swalpass").val();
						var testId = $("#swalTestId").val();
						var courseId = $("#swalCourseId").val();
						if(!testName){
							Swal.showValidationMessage('Please fill in all fields');
						}
						return {testName:testName,fullMarks:fullMarks,passMarks:passMarks,testId:testId,courseId:courseId}
					}
					
				}).then(function(result){
					if(result.isConfirmed){
						selectedCourse=result.value.courseId;
						const data={
								testName:result.value.testName,
								fullMarks:result.value.fullMarks,
								passMarks:result.value.passMarks,
								testId:result.value.testId,
								courseId:result.value.courseId,
						}
						const jsonData=JSON.stringify(data);
						$.ajax({
							url:'myTest',
							method:'put',
							data:jsonData,
							success:function(response){
								if(response.message=="updated"){
									Swal.fire(
											  'Success!',
											  'Student updated successfully!',
											  'success'
											)
									var data={
										courseId:selectedCourse
									}
								   	getTests(data);	
											
								}else if(response.message=="notupdated"){
									swal ( "Oops" ,  "Update failed!" ,  "error" )
								}
							},
							error:function(){
								swal ( "Oops" ,  "Something went wrong!" ,  "error" )
							}
						})
					}
				})
			})
			
			$(document).on('click','.testViewBtn',function(){
				var testId = $(this).val();
				var courseId = $('#selectCourse').val();
				var courseName = $('#selectCourse')
				window.location.href='TestAssign.jsp?testId='+testId+'&courseId='+courseId;
				
			})
			
			
			
			function deleteTest(jsonData,selectedCourse){
				$.ajax({
					url:'myTest',
					method:'delete',
					data:jsonData,
					success:function(response){
						if(response.message=="deleted"){
							Swal.fire(
									  'Success!',
									  'You removed the Test!',
									  'success'
									)
							var data={
								courseId:selectedCourse
							}
						   	getTests(data);		
						}else if(response.message=="notdeleted"){
							swal ( "Oops" ,  "Delete failed!" ,  "error" );
						}
					}
				})
			}
			
			
			function getTests(data){
				$.ajax({
					url:'myTest',
					method:'get',
					data:data,
					success:function(response){
						
						$(".carpet").empty();
						if(response.length==0){
							
						}else{
							console.log(response);
							for(i=0;i<response.length;i++){
								var card="<div class='card'>";
								var testName=card+"<h3>TestName:"+" "+response[i].testName+"</h3>";
								var fullMarks=testName+"<p>FullMarks:"+response[i].fullMarks+"</p>";
								var passMarks = fullMarks+"<p>PssMarks: "+response[i].passMarks+"</p>";
								var view = passMarks+"<button class='testCardBtn testViewBtn' value='"+response[i].testId+"'>View</button>";
								var edit = view+"<button class='successButton testCardBtn editBtn' data-values='"+response[i].testName+","+response[i].fullMarks+","+response[i].passMarks+","+response[i].testId+","+response[i].courseId+"'>Edit</button>";
								var remove = edit+"<button value='"+response[i].testId+"' class='dangerButton testCardBtn'>Remove</button>";
								$(".carpet").append(remove);
							}
						}
						
					},
					error:function(response){
						
					}
				})
			}
			
		})