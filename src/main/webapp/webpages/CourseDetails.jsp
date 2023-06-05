<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	
	<div class="center_div">
            <h2 class="headCourseA">Attendance Chart for</h2>
            <div class="carpet">
                <div id="chart_div" class="chart"></div>
                <div class="card" style="
                                        background: none;
                                        justify-content: flex-start;
                                        padding: 0;
                                        margin: 24px;">
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount" >TotalStudents:</label>
                        <label for="student" class="totalStudent"></label>
                    </div>
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount">MostPresent:</label>
                        <label for="student" class="mostPresentDay"></label>
                    </div>
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount">LeastPresent:</label>
                        <label for="student" class="mostAbsentDay"></label>
                    </div>
                </div>
            </div>
            <div class="month-form">
                <input type="month" id="AttendanceMonth"/>
                <button id="getAttendanceBtn">Get Diagram</button>
            </div>
            
            
            <div class="carpet" style="padding-top: 0;margin:16px;">
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Most Present (Month)</h4>
                    <label for="nameofstudent" id="HighMonthName"></label>
                    <label for="presentdays" id="HighMonthPresent"></label>
                    <label for="totaldays" id="HighMonthTotal"> </label>

                </div>
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Least Present (Month)</h4>
                    <label for="nameofstudent" id="LowMonthName"></label>
                    <label for="presentdays" id="LowMonthPresent"> </label>
                    <label for="totaldays" id="LowMonthTotal"></label>

                </div>
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Most Present (AllTime)</h4>
                    <label for="nameofstudent" id="AllTimeHighName"></label>
                    <label for="presentdays" id="AllTimeHighPresent"></label>
                    <label for="totaldays" class="AllTimeTotal"></label>

                </div>
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Least Present (AllTime)</h4>
                    <label for="nameofstudent" id="AllTimeLowName"></label>
                    <label for="presentdays" id="AllTimeLowPresent"></label>
                    <label for="totaldays" class="AllTimeTotal"></label>

                </div>
            </div>
            
            
            <!-- ========HERE IS ANOTHER CHART SECTION FOR TEST -->
            <h2 class="headCourse">Tests Chart for</h2>
            <div class="carpet" style="background: #dcb4fb;">
                <div id="Testchart_div" class="chart"></div>
                <div class="card" style="
                                        background: none;
                                        justify-content: flex-start;
                                        padding: 0;
                                        margin: 24px;">
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount" >Total Students:</label>
                        <label for="student" class="totalStudent">49</label>
                    </div>
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount">Most failed</label>
                        <label for="student">49</label>
                    </div>
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount">Most passed</label>
                        <label for="student">16</label>
                    </div>
                </div>
            </div>
            <div class="month-form">
                <input type="month"/>
                <button>Get Diagram</button>
            </div>
            <div class="carpet" style="padding-top: 0;margin:16px;">
                <div class="card">
                    <i class="fa-sharp fa-solid fa-book fa-2x"></i>
                    <h4>Top Student (Recent)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 26</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
                <div class="card">
                    <i class="fa-sharp fa-solid fa-book fa-2x"></i>
                    <h4>Low Perform (Recent)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 12</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
                <div class="card">
                    <i class="fa-sharp fa-solid fa-book fa-2x"></i>
                    <h4>Top Student (AllTime)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 12</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
                <div class="card">
                    <i class="fa-sharp fa-solid fa-book fa-2x"></i>
                    <h4>Low Perform (AllTime)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 12</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
            </div>
        
    </div>
    
    <script src="js/CourseDetailsJS.js">

    </script>  
	

	
</body>
</html>