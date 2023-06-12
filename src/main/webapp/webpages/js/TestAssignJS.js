	$(document).ready(function(){
			var passMarks;
			let studentMarks=[];
			var url_string = window.location.href;
			var url = new URL(url_string);
			var URLtestId = url.searchParams.get("testId");
			var URLcourseId = url.searchParams.get("courseId");
			
			const Testdata={
					testId:URLtestId
			}
			
			const Coursedata={
				courseId:URLcourseId
			}
			getMarks(Testdata);
			getTests(Coursedata);
			
			
			$('#tableBody').on('click','.successButton',function(){
				var marksEnter = $(this).data('values').split(',');
				console.log(marksEnter[0]);
				
				Swal.fire({
					title:'Enter marks',
					html:'<input id="swalMarks" placeholder="Enter marks"/>',
					showCancelButton:true,
					confirmButtonText:'SAVE',
					preConfirm:function(){
						var swalmarks = $('#swalMarks').val();
						if(!swalmarks){
							Swal.showValidationMessage('Please fill in all fields');
						}
						return {swalmarks:swalmarks}
					}
				}).then(function(result){
					if(result.isConfirmed){
						console.log(result.value.swalmarks);
						const data={
								studentId:marksEnter[0],
								testId:marksEnter[1],
								marksObtained:result.value.swalmarks
						}
						const jsonData= JSON.stringify(data);
						$.ajax({
							url:'myMarks',
							method:'post',
							data:jsonData,
							success:function(response){
								if(response.message=="inserted"){
									Swal.fire(
											  'Success!',
											  'Student marks assigned successfully!',
											  'success'
									)
									$('#testFull').empty();
									$('#testPass').empty();
									$('#testName').empty();
									$('#tableBody').empty();
									getMarks(Testdata);
									getTests(Coursedata);
									
								}else if(response.message=="notinserted"){
									swal ( "Oops" ,  "Assign failed!" ,  "error" )
								}
							},
							error:function(){
								swal ( "Oops" ,  "Something went wrong!" ,  "error" )
							}
						})
					}
				})
				
			})
			
			
			$("#tableBody").on('click','.dangerButton',function(){
				var Editmarks = $(this).data('values').split(',');
				console.log(Editmarks);
				
				Swal.fire({
					title:'Edit marks',
					html:'<input id="swalMarks" placeholder="Enter marks"/>',
					showCancelButton:true,
					confirmButtonText:'SAVE',
					preConfirm:function(){
						var swalmarks = $('#swalMarks').val();
						if(!swalmarks){
							Swal.showValidationMessage('Please fill in all fields');
						}
						return {swalmarks:swalmarks}
					}
				}).then(function(result){
					if(result.isConfirmed){
						console.log(result.value.swalmarks);
						const data={
								studentId:Editmarks[1],
								testId:Editmarks[2],
								marksObtained:result.value.swalmarks
						}
						const jsonData= JSON.stringify(data);
						$.ajax({
							url:'myMarks',
							method:'put',
							data:jsonData,
							success:function(response){
								if(response.message=="updated"){
									Swal.fire(
											  'Success!',
											  'Student marks assigned successfully!',
											  'success'
									)
									$('#testFull').empty();
									$('#testPass').empty();
									$('#testName').empty();
									$('#tableBody').empty();
									getMarks(Testdata);
									getTests(Coursedata);
									
								}else if(response.message=="notupdated"){
									swal ( "Oops" ,  "Assign failed!" ,  "error" )
								}
							},
							error:function(){
								swal ( "Oops" ,  "Something went wrong!" ,  "error" )
							}
						})
					}
				})
				
			})
			
			
			
			function getMarks(data){
				$.ajax({
					url:'myMarks',
					method:'get',
					data:data,
					success:function(response){
						
						for(i=0;i<response.length;i++){	
							let newPair = {key:response[i].studentId,value:response[i].marksObtained};
							studentMarks.push(newPair);
						}
					},
					error:function(){
						
					}
				})
			}
			
			function getStudents(data){
				let marks="";
				let result="";
				$.ajax({
					url:'myStudent',
					method:'get',
					data:data,
					success:function(response){
						console.log(response);
						var formMarks=0;
						for(i=0;i<response.length;i++){	
							var sno = '<tr><td>'+(i+1)+'</td>';
							var name=sno+'<td>'+response[i].studentName+'</td>';
							
							marks=name+"<td><button class='successButton' data-values='"+response[i].studentId+","+URLtestId+"'>Assign</button></td>";
	
							for(j=0;j<studentMarks.length;j++){
								if(studentMarks[j].key==response[i].studentId){
									console.log('Marks of student '+response[i].studentId+" is "+studentMarks[j].value);
									marks = name+'<td>'+studentMarks[j].value+'</td>';
									formMarks=studentMarks[j].value;
									
									if(studentMarks[j].value<passMarks){
										result=marks+'<td>Fail</td>';
										marks=result+'<td><button class="dangerButton" data-values="'+formMarks+','+response[i].studentId+','+URLtestId+'" >Edit</button></td>';
									}else if(studentMarks[j].value>=passMarks){
										result=marks+'<td>Pass</td>';
										marks=result+'<td><button class="dangerButton" data-values="'+formMarks+','+response[i].studentId+','+URLtestId+'" >Edit</button></td>';
									}
									
								}
							}
							
							
							
							
							var end = marks+'</tr>';
							$("#tableBody").append(end);
							
						}
						
						
					},
					error:function(){
						
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
								if(response[i].testId==URLtestId){
									$("#testName").append(response[i].testName);
									$("#testFull").append("FullMarks:"+response[i].fullMarks);
									$("#testPass").append("PassMarks:"+response[i].passMarks);
									passMarks = response[i].passMarks;
								}
							}
						}
						getStudents(Coursedata);
						
					},
					error:function(response){
						
					}
				})
			}
			
		})