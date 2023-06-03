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
                        <label for="studentcount" >Total Students:</label>
                        <label for="student" class="totalStudent">49</label>
                    </div>
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount">Most present</label>
                        <label for="student" class="mostPresentDay">49</label>
                    </div>
                    <div class="block-item">
                        <i class="fa-solid fa-people-group card-icon fa-2x"></i>
                        <label for="studentcount">Least present</label>
                        <label for="student" class="mostAbsentDay">16</label>
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
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 26</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Least Present (Month)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 12</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Most Present (AllTime)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 12</label>
                    <label for="totaldays">Todaldays: 28</label>

                </div>
                <div class="card">
                    <i class="fa-regular fa-calendar fa-2x"></i>
                    <h4>Least Present (AllTime)</h4>
                    <label for="nameofstudent">Name: Bipul Tamang</label>
                    <label for="presentdays">Presentdays: 12</label>
                    <label for="totaldays">Todaldays: 28</label>

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
    
    <script>
    
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(defaultLoadAttendanceChart);

function drawBackgroundColor() {
      

      Attendancedata.addRows([
        [1,12],[2,22],[3,24],[4,20],[5,15],[6,29],[8,14]
      ]);

      var Testdata = new google.visualization.DataTable();
      Testdata.addColumn('number', 'X');
      Testdata.addColumn('number', 'Students');

      Testdata.addRows([
        [1,12],[2,17],[3,12],[4,23]
        
      ]);

      var Testoptions = {
        hAxis: {
          title: 'Test'
        },
        vAxis: {
          title: 'Passed'
        },
        backgroundColor: '#fefefe'
    
      };


     

      var Testchart = new google.visualization.LineChart(document.getElementById('Testchart_div'));
      Testchart.draw(Testdata, Testoptions);
    }
    
	   $(document).ready(function(){
		   
		   
		   
			var url_string = window.location.href;
			var url = new URL(url_string);
			var courseId = url.searchParams.get("courseId");
			const today = new Date();
			$("#AttendanceMonth").val(today.toISOString().slice(0,7));
			const month = today.getMonth()+1;
			const year = today.getFullYear();
			
			const formdate = $("#AttendanceMonth").val();
			const date = new Date(formdate);
			LoadAttendanceBread(date,courseId);
			
			$("#getAttendanceBtn").click(function(){
				const formdate = $("#AttendanceMonth").val();
				const date = new Date(formdate);
				
				LoadAttendanceBread(date,courseId);
				LoadAttendanceChart(date);
			})
		})
		
		function LoadAttendanceBread(passedDate,courseId){
		   $.ajax({
				url:'courseDetails',
				type:'get',
				headers:{'courseId':courseId,'month':passedDate.getMonth()+1,'year':passedDate.getFullYear()},
				success:function(response){
					
					if(response.courseName=="null"){
						window.location.href="http://localhost:8080/sps_website/webpages/AddCourse.jsp";
					}
					
					console.log(response);
					$(".headCourseA").empty();
					$(".headCourseA").append("Attendance Chart for "+response.courseName);
					
					$(".totalStudent").empty();
					$(".totalStudent").append(response.totalstudent);
					
					
					
						
				},
				error:function(xhcr,status,error){
					
				}
			})
	   }
		
		
		function LoadAttendanceChart(passedDate){
		   const today = passedDate;
		   const month = today.getMonth()+1;
		   const year = today.getFullYear();
		   var url_string = window.location.href;
		   var url = new URL(url_string);
		   var courseId = url.searchParams.get("courseId");
    		$.ajax({
        		url:'courseAttendanceChart',
        		type:'get',
        		headers:{'month':month,'year':year,'courseId':courseId},
        		success:function(response){
        			console.log(response);
        			drawAttendanceChart(response);
        			
        		},
        		error:function(xhcr,status,error){
        			
        		}
        	  })
    	}
    

    
    
    	
    	function defaultLoadAttendanceChart(){
		   const today = new Date();
		   const month = today.getMonth()+1;
		   const year = today.getFullYear();
		   var url_string = window.location.href;
		   var url = new URL(url_string);
		   var courseId = url.searchParams.get("courseId");
    		$.ajax({
        		url:'courseAttendanceChart',
        		type:'get',
        		headers:{'month':month,'year':year,'courseId':courseId},
        		success:function(response){
        			console.log(response);
        			drawAttendanceChart(response);
        			
        		},
        		error:function(xhcr,status,error){
        			
        		}
        	  })
    	}
    	
    	function drawAttendanceChart(chart_data){
    		var jsonData = chart_data;
    		var Attendancedata = new google.visualization.DataTable();
    	    Attendancedata.addColumn('number', 'X');
    	    Attendancedata.addColumn('number', 'Students');
    	    var lowestPresent=500;
    	    var highPresent = 0;
    	    $.each(jsonData,function(i,jsonData){
    	    	Attendancedata.addRows([[jsonData.day,jsonData.totalPresent]]);
    	    	if(jsonData.totalPresent<lowestPresent){
    	    		lowestPresent = jsonData.totalPresent;
    	    	}
    	    	if(jsonData.totalPresent>highPresent){
    	    		highPresent = jsonData.totalPresent;
    	    	}
    	    });
    	    $(".mostPresentDay").empty();
			$(".mostPresentDay").append(highPresent);
			
			if(lowestPresent==500){
				lowestPresent=0;
			}
			$(".mostAbsentDay").empty();
			$(".mostAbsentDay").append(lowestPresent);
    	    
    	    var options = {
    	            hAxis: {
    	              title: 'Date'+' of Jan'
    	            },
    	            vAxis: {
    	              title: 'Attendance'
    	            },
    	            backgroundColor: '#fefefe'
    	        
    	          };

    	    var Attendancechart = new google.visualization.LineChart(document.getElementById('chart_div'));
    	    Attendancechart.draw(Attendancedata, options);
    	}
    	
    	
    	
    	
    	
    	
    	
    </script>  
	

	
</body>
</html>