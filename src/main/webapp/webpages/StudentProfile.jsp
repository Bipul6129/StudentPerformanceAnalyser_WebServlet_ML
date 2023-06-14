<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
	<style>
        .block-item{
            display:block;
            
        }
        .feedbackcard{
            align-items: start;
            width: 100%;
            
        }
    </style>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="blanker">
		<div class="center_div">
            <h2 id="headingCourse"></h2>
  
            <div class="carpet" style="background: #dcb4fb;">
                <div id="top_x_div" class="chart" ></div>
                <div class="card" style="
                                            background: none;
                                            justify-content: flex-start;
                                            padding: 0;
                                            margin: 24px;">
                        <div class="block-item">
                            
                            <label for="studentname">Name:</label>
                            <label for="student" id="studentName"></label>
                        </div>
                        <div class="block-item">
                            
                            <label for="studentcount">Age:</label>
                            <label for="student" id="studentAge"></label>
                        </div>
                        <div class="block-item">
                            
                            <label for="studentcount">Ethnicity:</label>
                            <label for="student" id="studentEthni"></label>
                        </div>
                        <div class="block-item">
                            
                            <label for="studentcount">Gender:</label>
                            <label for="student" id="studentGender"></label>
                        </div>
                    </div>
            </div>
            <button id="showpopup">Post FeedBack</button>

            <h2>Previous Feedbacks</h2>
            <div class="carpet" style="padding-top: 0;margin:16px;text-align: start;">
                <div class="card feedbackcard">
                    <i class="fa-sharp fa-regular fa-comment fa-2x"></i>
                    <h4>Subject: For test results</h4>
                    <label for="presentdays">Message: Lorem ipsum dolor sit amet consectetur adipisicing elit. Nisi voluptates consectetur deserunt laudantium!</label>
                </div>
                <div class="card feedbackcard">
                    <i class="fa-sharp fa-regular fa-comment fa-2x"></i>
                    <h4>Subject: For test results</h4>
                    <label for="presentdays">Message: Study new topic</label>
                </div>
            </div>
        </div>
	</div>
	
	<div class="center">
            <div class="popup">
                <div class="close-btn">&times;</div>
                <div class="form">
                    <h2>PostFeedback</h2>
                    <div class="form-element">
                        <label for="coursename">Feedback on</label>
                        <input type="text" placeholder="Enter subject for feedback"/>
                    </div>
                    <div class="form-element">
                        <label for="faculty">Message</label>
                        <!-- <input type="text" style="height: 140px;line-height: 140px;" placeholder="Enter Message"/> -->
                        <textarea placeholder="Enter message here"></textarea>
                    </div>
                    
                    <button>Send</button>
        
                </div>
            </div>
    </div>
    
    <script>
              google.charts.load('current', {
        'packages': ['bar']
      });
              google.charts.setOnLoadCallback(function(){
            	  getChartData();
      		});


      function drawStuff(chart_data) {
  		var dataArray=[];
  		if(chart_data.length!=0){
  			var columns = Object.keys(chart_data[0]);

  			dataArray.push(columns);
  			
  			chart_data.forEach(function(item){
  				var rowData=Object.values(item);
  				dataArray.push(rowData);
  			})
  			var data = new google.visualization.arrayToDataTable(dataArray);
  	   	
  	        var options = {
  	          legend: { position: 'none' },
  	          chart: {
  	            title: 'Test results',
  	            subtitle: 'Out of 10 points' },
  	          axes: {
  	            x: {
  	              0: { side: 'top', label: ''} // Top x-axis.
  	            }
  	          },
  	          bar: { groupWidth: "90%" }
  	        };
  	
  	        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
  	        // Convert the Classic options to Material options.
  	        chart.draw(data, google.charts.Bar.convertOptions(options));
  		}else{
  			document.getElementById('top_x_div').innerHTML='No data available';
  		}
  	}


    //   HERE SCRIPT FOR POPUP
        document.querySelector("#showpopup").addEventListener("click",function(){
            console.log("working")
            document.querySelector(".center").style.top="50%"
            document.querySelector("#blanker").style.opacity="40%"
            window.scrollTo({
                top:0,
                behavior:'smooth'
            })
        });
        document.querySelector(".close-btn").addEventListener("click",function(){
            console.log("working")
            document.querySelector(".center").style.top="-140%"
            document.querySelector("#blanker").style.opacity="100%"
        });

        $(document).ready(function(){
        	var currentUrl = window.location.href;
        	var url = new URL(currentUrl);
        	var params = new URLSearchParams(url.search);
        	
        	var paramCourse = params.get('course');
        	var paramStudentId = params.get('student_id');
        	$('#headingCourse').append('Student Profile '+paramCourse);
        	
        	getStudentDetail(paramStudentId);
        	
        	
        	
        })
        
        
        function getChartData(paramStudentId){
        	var currentUrl = window.location.href;
        	var url = new URL(currentUrl);
        	var params = new URLSearchParams(url.search);
        	
        	var paramCourse = params.get('course');
        	var paramStudentId = params.get('student_id');
        	$.ajax({
        		url:'studentTestChart',
        		method:'get',
        		data:{
        			studentId:paramStudentId
        		},
        		success:function(response){
        			console.log(response);
        			drawStuff(response);
        			
        		},
        		error:function(){
        			
        		}
        	})
        }
        
        
        
        
        function getStudentDetail(paramStudentId){
        	$.ajax({
        		url:'studentProfile',
        		method:'get',
        		data:{
        			studentId:paramStudentId
        		},
        		success:function(response){
        			console.log(response);
        			$('#studentName').append(response.studentName);
        			$('#studentAge').append(response.age);
        			$('#studentEthni').append(response.ethnicity);
        			$('#studentGender').append(response.gender);
        			
        		},
        		error:function(){
        			
        		}
        	})
        }
        
        
    </script>
	
</body>
</html>