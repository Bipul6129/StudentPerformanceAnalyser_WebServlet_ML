<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sps_website.model.UserModel" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>

<body>
	<div id="blanker">
		<%@ include file="reusable_comp/CheckLogin.jsp" %>
		<%@ include file = "reusable_comp/NavBar.jsp" %>
		<div class="mycourse">
	        <h2>Colleges</h2>
	        <table>
	            <thead>
	                <tr>
	                    <th>Sno.</th>
	                    <th>CollegeName</th>
	                    <th>Location</th>
	                    <th>Course</th>
	                    <th></th>
	                </tr>
	            </thead>
	            <tbody id="tableBody">

	            </tbody>
	        </table>
	        <button id="addcourse">Add College</button>
	        
	    </div>
	    </div>
	    <div class="center">
	        <div class="popup">
	            <div class="close-btn">&times;</div>
	            <div class="form">
	                <h2>Add New College</h2>
	                <form id="insertCourseForm">
		                <div class="form-element">
		                    <label for="coursename">CollegeName</label>
		                    <input type="text" name="courseName" placeholder="Enter new College Name"/>
		                </div>
		                <div class="form-element">
		                    <label for="faculty">Location</label>
		                    <input type="text" name="courseFaculty" placeholder="Enter College Location"/>
		                </div>
		                
		                <button type="submit">Save</button>
	                </form>
	                
	    
	            </div>
	        </div>
	    </div>
	</div>
	
	<script>
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
							$("#tableBody").append("<tr><td>"+j+"</td><td>"+response[i].CollegeName.toUpperCase()+"</td><td>"+response[i].location.charAt(0).toUpperCase()+response[i].location.slice(1)+"</td><td><button class='viewCourse' value='"+response[i].CollegeId+"'>View</button></td></tr>")
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
			
			
		})
	</script>
    
</body>
</html>