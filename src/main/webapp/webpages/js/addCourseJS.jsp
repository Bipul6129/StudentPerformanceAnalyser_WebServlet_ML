<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sps_website.model.*"%>
<% UserModel currentUser = (UserModel) session.getAttribute("user"); %>
<script>
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
	var userId = <%= currentUser.getUserid() %>;
	var collegeArray=[];
	var collegeIdArray=[];
	getMyCourses();
	getMyCollege("#collegeDropBox");
	
	$("#insertCourseForm").submit(function(event){
		
		event.preventDefault();
		if($("#courseName").val()==""||$("#courseFaculty").val()==""||$("#courseSemester").val()==""||$("#collegeDropBox").val()==null){
			Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Insertion failed',
				  footer: ''
				})
		}else{
			console.log('entered');
			var formData = $(this).serialize();
			$.ajax({
				url:'myCourse',
				type:'post',
				data:formData,
				success:function(response){
					console.log(response);
					$("#tableBody").empty();
					getMyCourses();
					if(response.message=="inserted"){
						popdown();
						Swal.fire(
								  'Course Added Successfully',
								  'You have sucessfully added new course',
								  'success'
								)
					}else if(response.message=="notinserted"){
						popdown();
						Swal.fire({
							  icon: 'error',
							  title: 'Oops...',
							  text: 'Insertion failed',
							  footer: ''
							})
					}
				},
				error:function(xhr,status,error){
					Swal.fire({
						  icon: 'error',
						  title: 'Oops...',
						  text: 'Connection Error?',
						  footer: ''
						})
				}
			})
		}
		
	})
	
	function getMyCourses(){
		$.ajax({
    		url:'myCourse',
    		type:'get',
    		dataType:'json',
    		headers:{'userid':userId},
    		success:function(response){
    			console.log(response);
    			
    			if(response.length==0){
    				
    			}
    			
    			for(i=0;i<response.length;i++){
    				var status="";
    				if(response[i].courseStatus==1){
    					status="Ongoing";
    				}else{
    					status="Completed";
    				}
    				
    				$("#tableBody").append("<tr> <td>"+(i+1)+"</td> <td class='formcourseName'>"+response[i].courseName+"</td> <td>"+response[i].courseFaculty.toUpperCase()+"</td> <td>"+response[i].courseSemester+"</td> <td>"+response[i].courseCollege+"</td><td>"+status+"</td> <td><a href='CourseDetails.jsp?courseId="+response[i].courseId+"'>Details</a></td><td><button class='successButton' data-values='"+response[i].courseId+","+response[i].courseName+","+response[i].courseFaculty+","+response[i].courseSemester+","+response[i].courseCollege+","+response[i].courseStatus+"'>Edit</button></td><td><button class='dangerButton' value='"+response[i].courseId+"'>Remove</button></td> </tr>");
    				
    				

    			}
    			
    		},
    		error:function(xhr,status,error){
    			console.log(error);
    		}
    	});
	}
	
	$('#tableBody').on('click','.dangerButton',function(){
		var courseId = $(this).val();
		Swal.fire({
			  title: 'Are you sure?',
			  text: "This will delete all the existing student on this course!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, remove it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				const data={
						courseId:courseId,
				}
				const jsonData=JSON.stringify(data);
				$.ajax({
					url:'myCourse',
					type:'delete',
					data:jsonData,
					success:function(response){
						console.log(response)
						if(response.message=="deleted"){
							Swal.fire(
							     'Removed!',
								 'Your course has been removed.',
								 'success'
							)
							$("#tableBody").empty();
							getMyCourses();
							getMyCollege("#collegeDropBox");
						}else if(response.message=="notdeleted"){
							Swal.fire(
									  'Have you removed the existing Tests?',
									  'Delete failed',
									  'question'
									)
						}
						
					},
					error:function(){
						console.log('error');
					}
					
				})  
				  
			    
			  }
			})
	});
	
	$('#tableBody').on('click','.successButton',function(){
		var courseEdit = $(this).data('values').split(',');
		var selectLine;
		if(courseEdit[5]==1){
			selectLine='Select Status<br><select id="editStatus" name="status"><option selected value=1>Ongoing</option><option value=0>Completed</option></select>';
		}else{
			selectLine='Select Status<br><select id="editStatus" name="status"><option value=1>Ongoing</option><option selected value=0>Completed</option></select>';
		}
		
		var selectCollege="Select College<br><select id='editCollege' name='courseCollege'>";		
		for(j=0;j<collegeArray.length;j++){
			if(courseEdit[4]==collegeArray[j]){
				selectCollege=selectCollege+"<option selected value="+collegeIdArray[j]+">"+collegeArray[j]+"</option>";
			}else{
				selectCollege=selectCollege+"<option value="+collegeIdArray[j]+">"+collegeArray[j]+"</option>";
			}
			
		}
		selectCollege=selectCollege+"</select>";
		
		
		
		Swal.fire({
		    title: 'Edit Your Course',
		    html: '<form id="swalForm">'+
		    	  '<input id="swalCourseId" type="hidden" value="'+courseEdit[0]+'"/>'+
		    	  '<input type="text" name="courseName" placeholder="CourseName" value="'+courseEdit[1]+'" id="editCourseName" class="swal2-input">' +
		          '<input type="text" name="faculty" placeholder="Faculty" id="editFaculty" value="'+courseEdit[2]+'" class="swal2-input">'+
		          '<input type="text" name="semester" placeholder="Semester" id="editSemester" value="'+courseEdit[3]+'" class="swal2-input"><br>'+
		          selectCollege+'<br>'+
		          selectLine+'</form>',
		    showCancelButton: true,
		    confirmButtonText: 'SAVE',
		    preConfirm: function () {
		      var courseId = document.getElementById('swalCourseId').value;
		      var courseName = document.getElementById('editCourseName').value;
		      var faculty = document.getElementById('editFaculty').value;
		      var semester = document.getElementById('editSemester').value;
		      var courseCollege = document.getElementById('editCollege').value;
		      var courseStatus = document.getElementById('editStatus').value;
		      if (!courseName || !faculty || !editSemester) {
		        Swal.showValidationMessage('Please fill in all fields');
		      }
		      return {courseId:courseId, courseName: courseName, faculty:faculty,semester:semester,courseCollege:courseCollege,courseStatus:courseStatus};
		    }
		  }).then(function (result) {
		    if (result.isConfirmed) {
			 console.log(result.value.courseStatus+" is status");
			 const data={
					 courseId:result.value.courseId,
					 courseName:result.value.courseName,
					 faculty:result.value.faculty,
					 semester:result.value.semester,
					 college:result.value.courseCollege,
					 status:result.value.courseStatus
			 }
			 const jsonData = JSON.stringify(data);
		      $.ajax({
		    	  url:'myCourse',
		    	  method:'put',
				  data:jsonData,
		    	  success:function(response){
		    		  
		    		  Swal.fire(
							  'Success!',
							  'You edited the course',
							  'success'
							)
		    		  $("#tableBody").empty();
		    		  getMyCourses();
		    		  getMyCollege("#collegeDropBox");
		    	  },
		    	  error:function(){
		    		  
		    	  }
		      });
		      // You can perform further actions with the form data, such as sending it to a server or processing it.
		    }
		  });
	})
	
	
	function getMyCollege(selectId){
		
		
		$.ajax({
			url:'myCollege',
			method:'get',
			dataType:'json',
			headers:{'userId':userId},
			success:function(response){
				console.log(response);
				for(i=0;i<response.length;i++){
					console.log(response[i].CollegeName);
					if(itemExists(response[i].CollegeName)){
						console.log("college exists");
					}else{
						
						$(selectId).append("<option value='"+response[i].CollegeId+"'>"+response[i].CollegeName+"</option>");
						collegeArray.push(response[i].CollegeName);
						collegeIdArray.push(response[i].CollegeId);
					}
				}
			
			},
			error:function(xhr,status,error){
				console.log(error);
			}
		})
	}
	
	
	
	
	function itemExists(item){
		return collegeArray.indexOf(item) !== -1;
	}
})

	function showForm() {
	  
	}


</script>