<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
<style>
	.testCardBtn{
		width:100%;
		background: linear-gradient(90deg,#4074C0,#12BAEB);
	}
	select{
		width:auto;
	}
	.card{
		width:320px;
	}
</style>
</head>
<body>
<div id="blanker">
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<h2>Select course to view Test</h2>
		<select id="selectCourse" style="width:auto">
		</select>
		<button id="getTestBtn">Get Tests</button>
		<h2 class="courseHead">Course Heading</h2>
		<button class="successButton" id="addTestBtn">Add Tests</button>
		<div class="carpet">
			
		</div>
	</div>
</div>

		<div class="center">
	        <div class="popup">
	            <div class="close-btn">&times;</div>
	            <div class="form">
	                <h2>Add New Test</h2>
	                <form id="insertTestForm">
		                <div class="form-element">
		                    <label for="coursename">Test Name</label>
		                    <input type="text" name="testName" id="testName" placeholder="Enter new TestName"/>
		                </div>
		                <div class="form-element">
		                    <label for="faculty">Full Marks</label>
		                    <input type="text" name="fullMarks" id="fullMarks" placeholder="Enter fullMarks"/>
		                </div>
		                <div class="form-element">
		                    <label for="semester">Pass Marks</label>
		                    <input type="text" name="passMarks" id="passMarks" placeholder="Enter passMarks"/>
		                </div>
		                <div class="form-element">
		                    <label for="semester">Select Course</label>
		                    <select name="testCourse" id="insertTestCourse">
		                    	
		                    </select>
		                </div>
		                <button type="submit">Save</button>
	                </form>
	                
	    
	            </div>
	        </div>
	    </div>
	<script src="js/TestViewJS.js">
		
	</script>
</body>
</html>