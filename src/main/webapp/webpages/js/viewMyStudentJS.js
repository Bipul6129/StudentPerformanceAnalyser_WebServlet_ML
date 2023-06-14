	$(document).ready(function(){
			var courseArray=[];
			var courseIdArray=[];
			$.ajax({
				url:'myCourse',
				method:'get',
				success:function(response){
					console.log(response);
					var options = "";
					for(i=0;i<response.length;i++){
						console.log(response[i].courseName);
						options=options+"<option value='"+response[i].courseId+"'>"+response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")</option>";
						courseArray.push(response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")");
						courseIdArray.push(response[i].courseId);
					}
					$("#selectCourse").html(options);
					
				},
				error:function(){
					
				}
			})
			

			
			$("#getStudentBtn").on('click',function(){
				$("#tableBody").empty();
				$("#courseHead").empty();
				
				var course_id = $("#selectCourse").val();
				
				if(course_id==null){
					Swal.fire(
					  'Select a course',
					  'Tried adding course?',
					  'question'
					)
				}else{
					const data = {
						courseId:course_id,
					}
					getStudents(data);
				}
				
				
				
			});
			
			
			$("#tableBody").on('click','.dangerButton',function(){
				var studentId = $(this).val();
				
				const data = {
					studentId:studentId,
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
						deleteStudent(jsonData);
					  }
					})
				
				
			})
			
			
			function deleteStudent(jsonData){
				$.ajax({
					url:'myStudent',
					method:'delete',
					data:jsonData,
					success:function(response){
						
						if(response.message=="deleted"){
							Swal.fire(
									  'Success!',
									  'You removed the student!',
									  'success'
									)
							$("#tableBody").empty();
							$("#courseHead").empty();
							var course_id = $("#selectCourse").val();
							const data = {
									courseId:course_id,
							}
							getStudents(data);
							
						}else if(response.message=="notdeleted"){
							swal ( "Oops" ,  "Delete failed!" ,  "error" );
						}
					},
					error:function(){
						
					}
				})
			}
			
			
			function getStudents(data){
				$.ajax({
					url:'myStudent',
					method:'get',
					data:data,
					success:function(response){
						
						$("#courseHead").append($("#selectCourse option:selected").text());
						for(i=0;i<response.length;i++){
							$("#tableBody").append("<tr><td>"+(i+1)+"</td><td>"+response[i].studentName+"</td><td>"+response[i].age+"</td><td>"+response[i].gender+"</td><td><a href='StudentProfile.jsp?course_id="+response[i].courseId+"&student_id="+response[i].studentId+"&course="+$("#selectCourse option:selected").text()+"'>Profile</a></td><td><button class='successButton' data-values='"+response[i].studentName+","+response[i].courseId+","+response[i].age+","+response[i].ethnicity+","+response[i].gender+","+response[i].studentStatus+","+response[i].studentId+"'>Edit</button></td><td><button class='dangerButton' value='"+response[i].studentId+"'>Remove</button></td></tr>");	
							
						}
						
					},
					error:function(){
						
					}
				})
			}
			
			
			$("#tableBody").on('click','.successButton',function(){
				var studentEdit = $(this).data('values').split(',');
				
				
				var selectCourse = "<select id='swalCourse'>"
				
				for(i=0;i<courseIdArray.length;i++){
					if(courseIdArray[i]==studentEdit[1]){
						selectCourse=selectCourse+"<option selected value='"+courseIdArray[i]+"'>"+courseArray[i]+"</option>";
					}else{
						selectCourse=selectCourse+"<option value='"+courseIdArray[i]+"'>"+courseArray[i]+"</option>";
					}
				}

				selectCourse = selectCourse+"</select>";
				
				selectEthnicity=""
				if(studentEdit[3]=="terai"){
					selectEthnicity="<select id='swalEthni'><option value='hilly'>Hilly</option><option value='himalayan'>Himalayan</option><option value='terai' selected>Terai</option></select>";
				}else if(studentEdit[3]=="hilly"){
					selectEthnicity="<select id='swalEthni'><option value='hilly' selected>Hilly</option><option value='himalayan'>Himalayan</option><option value='terai'>Terai</option></select>";
				}else if(studentEdit[3]=="himalayan"){
					selectEthnicity="<select id='swalEthni'><option value='hilly'>Hilly</option><option value='himalayan' selected>Himalayan</option><option value='terai'>Terai</option></select>";
				}
				
				
				selectGender="";
				if(studentEdit[4]=="male"){
					selectGender="<select id='swalGender'><option value='male' selected>Male</option><option value='female'>Female</option></select>";
				}else if(studentEdit[4]=="female"){
					selectGender="<select id='swalGender'><option value='male'>Male</option><option value='female' selected>Female</option></select>";
				}
				
				selectStatus="";
				if(studentEdit[5]=="rich"){
					selectStatus="<select id='swalStatus'><option value='rich' selected>Rich</option><option value='middle'>Middle</option><option value='poor'>Poor</option></select>";
				}else if(studentEdit[5]=="middle"){
					selectStatus="<select id='swalStatus'><option value='rich'>Rich</option><option value='middle' selected>Middle</option><option value='poor'>Poor</option></select>";
				}else if(studentEdit[5]=="poor"){
					selectStatus="<select id='swalStatus'><option value='rich'>Rich</option><option value='middle'>Middle</option><option value='poor' selected>Poor</option></select>";
				}
				
				
				
				Swal.fire({
					title:'Edit Student',
					html:'<input type="hidden" value="'+studentEdit[6]+'" id="swalId">'+
						  '<input id="swalName" value="'+studentEdit[0]+'" placeholder="StudentName" /><br>'+
						  selectCourse+'<br>'+
						  '<input id="swalAge" value="'+studentEdit[2]+'" placeholder="StudentAge"/><br>'+
						  selectEthnicity+
						  selectGender+
						  selectStatus,
					showCancelButton:true,
					confirmButtonText:'SAVE',
					preConfirm:function(){
						var studentId = $("#swalId").val();
						var studentName = $('#swalName').val();
						var course = $('#swalCourse').val();
						var age = $('#swalAge').val();
						var ethni = $('#swalEthni').val();
						var gender = $('#swalGender').val();
						var status = $('#swalStatus').val();
						
						if(!studentName){
							Swal.showValidationMessage('Please fill in all fields');
						}
						return {studentId:studentId,studentName:studentName,course:course,age:age,ethni:ethni,gender:gender,status:status}
					}
				}).then(function(result){
					if(result.isConfirmed){
						
						const data={
								studentId:result.value.studentId,
								studentName:result.value.studentName,
								course:result.value.course,
								age:result.value.age,
								ethni:result.value.ethni,
								gender:result.value.gender,
								status:result.value.status,
						}
						const jsonData = JSON.stringify(data);
						$.ajax({
							url:'myStudent',
							method:'put',
							data:jsonData,
							success:function(response){
								
								if(response.message=="updated"){
									Swal.fire(
											  'Success!',
											  'Student updated successfully!',
											  'success'
											)
									$("#tableBody").empty();
									$("#courseHead").empty();
									var course_id = $("#selectCourse").val();
									const data = {
											courseId:course_id,
									}
									getStudents(data);
								}else if(response.message=="notupdated"){
									
								}
							},
							error:function(){
								
							}
						})
					}
				})
			})
			
		})