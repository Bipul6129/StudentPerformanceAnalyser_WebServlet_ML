<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
<style>
	
	
</style>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<h2>Add Student</h2>
        <div class="carpet" style="background: #fefefe;width:50%;">
            <form class="form_div" id="studentAddForm">
                <table style="width: 100%;">
                    <tr>
                        <td><label for="nameOfStudent">Name of student</label></td>
                        <td><input name="studentName" id="studentName" type="text" placeholder="Enter Name of Student"/></td>
                    </tr>
                    <tr>
                        <td><label for="emailOfStudent">Email of student</label></td>
                        <td><input name="studentEmail" id="studentEmail" type="text" placeholder="Enter Email of Student"/></td>
                    </tr>
                    <tr>
                        <td><label for="nameOfCollege">Select Course</label></td>
                        <td><select name="courseSelect" id="courseSelect"></select></td>
                    </tr>
                    <tr>
                        <td><label for="AgeOfStudent">Age</label></td>
                        <td><input name="studentAge" id="studentAge" type="text" placeholder="Enter Student age"/></td>
                    </tr>
                    <tr>
                        <td><label for="Ethnicity">Ethicity</label></td>
                        <td><select class="smallOption" name="studentEthnicity">
                        	<option value="hilly">Hilly</option>
                        	<option value="himalayan">Himalayan</option>
                        	<option value="terai">Terai</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td><label for="Gender">Gender</label></td>
                        <td><select class="smallOption" name="studentGender">
                        	<option value="male">Male</option>
                        	<option value="female">Female</option>
                        	<option value="other">Other</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td><label for="Status">Status</label></td>
                        <td><select class="smallOption" name="studentStatus">
                        	<option value="rich">Rich</option>
                        	<option value="middle">Middle</option>
                        	<option value="poor">Poor</option>
                        </select></td>
                    </tr>
                </table>
                <div class="form_element_div">
                    <button type="submit">Save</button>
                </div>
            </form>
            
        </div>
	</div>
	
	<script src="js/addStudentJS.js">
		
	</script>
</body>
</html>