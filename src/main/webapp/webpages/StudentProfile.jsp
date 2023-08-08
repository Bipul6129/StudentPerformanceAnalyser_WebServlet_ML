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
	
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div id="blanker">
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
                        <div class="block-item">
                            
                            <label for="AttendanceCount"></label>
                            <label for="student" id="studentAttendance"></label>
                        </div>
                    </div>
            </div>
            <%
            	if(session.getAttribute("user")!=null){
            %>
            	<button id="showpopup">Post FeedBack</button>
            <%
            	}else{
            %>	
            	<button id="showpopup" style="display:none">Post FeedBack</button>
            <% 	
            	}
            %>
            

            <h2>Previous Feedbacks</h2>
            <div id="feedbackSpace" class="carpet" style="padding-top: 0;margin:16px;text-align: start;">

            </div>
        </div>
	</div>
	
	<div class="center">
            <div class="popup">
                <div class="close-btn">&times;</div>
                <form class="form" id="feedbackForm">
                    <h2>PostFeedback</h2>
                    <div class="form-element">
                        <label for="coursename">Feedback on</label>
                        <input type="text" name="feedback_subject" placeholder="Enter subject for feedback" id="FeedbackSubject"/>
                    </div>
                    <div class="form-element">
                        <label for="faculty">Message</label>
                        <!-- <input type="text" style="height: 140px;line-height: 140px;" placeholder="Enter Message"/> -->
                        <textarea name="feedback_msg"  placeholder="Enter message here" id="FeedbackMsg"></textarea>
                    </div>
                    <input type="hidden" name="feedback_email" id="StudentEmail" value="" />
                    <input type="date" name="today_date" id="emailDate" value="" style="display:none"/>
                    <input type="hidden" name="student_id" id="student_id" value=""/>
                    <input type="hidden" name="course_id" id="course_id" value=""/>
                    <button type="submit" id="sendMailBtn">Send</button>
        
                </form>
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
        	var paramCourse_id = params.get('course_id');
        	
        	$('#course_id').val(paramCourse_id);
        	
        	$('#headingCourse').append('Student Profile <br>'+paramCourse);
        	
        	getStudentDetail(paramStudentId);
        	
        	getFeedbacks(paramStudentId);
        	
        })
        
        function getFeedbacks(studentId){
        	$.ajax({
        		url:'feedback',
        		method:'get',
        		data:{
        			student_id:studentId
        		},
        		success:function(response){
        			$('#feedbackSpace').empty();
        			console.log(response);
        			for(i=0;i<response.length;i++){
        				var divStart='<div class="card feedbackcard"><i class="fa-sharp fa-regular fa-comment fa-2x"></i>';
            			var heading = divStart+'<h4>Subject: '+response[i].subject+' ('+response[i].date+')</h4>';
            			var message = heading+'<label>Message: '+response[i].message+'</label></div>';
            			$('#feedbackSpace').append(message);
        			}
        			
        		},
        		error:function(){
        			
        		}
        	})
        }
        
        
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
        			$('#studentAttendance').append(response.totalAttendance+" days present");
        			
        			$('#StudentEmail').val(response.email);
        			var todayDate = new Date().toISOString().split("T")[0];
        			$('#emailDate').val(todayDate);
        			$('#student_id').val(paramStudentId);
        		},
        		error:function(){
        			
        		}
        	})
        }
        
        $('#feedbackForm').on('submit',function(e){
        	e.preventDefault();
        	var formData = $(this).serialize();

        	if($('#FeedbackMsg').val==""||$('#FeedbackSubject').val()==""||$('#StudentEmail')==""){
        		Swal.fire(
        				  'Error occured!',
        				  'Fill all the inputs or check the email!',
        				  'error'
        				)
        	}else{
        		$('#sendMailBtn').hide();
        		$.ajax({
        			url:'feedback',
        			type:'post',
        			data:formData,
        			success:function(response){
        				if(response.message=="success"){
        					Swal.fire(
        	        				  'Feedback Sent!',
        	        				  'Feedback has been sent successfully and alerted',
        	        				  'success'
        	        				)
        	        				$('#sendMailBtn').show();
        					var studentId=$('#student_id').val();
        					getFeedbacks(studentId);
        				}else if(response.message=="failed"){
        					Swal.fire(
        	        				  'Error occured!',
        	        				  'There was error sending the mail!',
        	        				  'error'
        	        				)
        	        				$('#sendMailBtn').show();
        				}
        			},
        			error:function(){
        				
        			}
        		})
        	}
        })
        
        
    </script>
	
</body>
</html>