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
	getMyCourses();
	getMyCollege();
	
	$("#insertCourseForm").submit(function(event){
		
		event.preventDefault();
		var formData = $(this).serialize();
		$.ajax({
			url:'insertCourse',
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
	})
	
	function getMyCourses(){
		$.ajax({
    		url:'getMyCourse',
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
    				$("#tableBody").append("<tr> <td>"+(i+1)+"</td> <td>"+response[i].courseName+"</td> <td>"+response[i].courseFaculty+"</td> <td>"+response[i].courseSemester+"</td> <td>"+response[i].courseCollege+"</td><td>"+status+"</td> <td><a href='CourseDetails.jsp?courseId="+response[i].courseId+"'>Details</a></td> </tr>");
    				
    				

    			}
    			
    		},
    		error:function(xhr,status,error){
    			console.log(error);
    		}
    	});
	}
	
	function getMyCollege(){
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
						
						$("#collegeDropBox").append("<option value='"+response[i].CollegeId+"'>"+response[i].CollegeName+"</option>");
						collegeArray.push(response[i].CollegeName);
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


</script>