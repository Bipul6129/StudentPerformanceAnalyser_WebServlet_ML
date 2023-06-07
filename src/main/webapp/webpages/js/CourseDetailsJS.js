       google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(defaultLoadAttendanceChart);

function drawBackgroundColor() {
      

      

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
					
					Length=Object.keys(response).length;
					
					if(response.courseName=="null"){
						window.location.href="http://localhost:8080/sps_website/webpages/AddCourse.jsp";
					}
					
					console.log(response);
					$(".headCourseA").empty();
					$(".headCourseA").append("Attendance Chart for "+response.courseName);
					
					$(".totalStudent").empty();
					$(".totalStudent").append(response.totalStudents);
					
					
					
					$("#AllTimeHighPresent").empty();
					$("#AllTimeHighPresent").append("Presentdays:"+response.allTimeHighCount);
					
					if(typeof response.AllTimeHighName !== 'undefined'){
						$("#AllTimeHighName").empty();
						$("#AllTimeHighName").append("Name:"+response.AllTimeHighName);
						$("#AllTimeLowName").empty();
						$("#AllTimeLowName").append("Name:"+response.AllTimeLowName);
					}else{
						$("#AllTimeHighName").empty();
						$("#AllTimeLowName").empty();
					}
					
					
					$(".AllTimeTotal").empty();
					$(".AllTimeTotal").append("Totaldays:"+response.TotalClassDays);
					
					$("#AllTimeLowPresent").empty();
					$("#AllTimeLowPresent").append("Presentdays:"+response.allTimeLowCount);
					
					if(typeof response.highPresentName === 'undefined'){
						$("#HighMonthName").empty();
						$("#HighMonthPresent").empty();
						
						
						$("#LowMonthName").empty();
						$("#LowMonthPresent").empty();
						
					}else{
						$("#HighMonthName").empty();
						$("#HighMonthName").append("Name:"+response.highPresentName);
						$("#HighMonthPresent").empty();
						$("#HighMonthPresent").append("PresentDays:"+response.highPresentCount);
						
						
						$("#LowMonthName").empty();
						$("#LowMonthName").append("Name:"+response.lowPresentName);
						$("#LowMonthPresent").empty();
						$("#LowMonthPresent").append("PresentDays:"+response.lowPresentCount);
						
					}
					
					
						
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
    	    var totalDays=0;
    	    $.each(jsonData,function(i,jsonData){
    	    	totalDays++;
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
			
			$("#LowMonthTotal").empty();
			$("#LowMonthTotal").append("TotalDays:"+totalDays);
			
			$("#HighMonthTotal").empty();
			$("#HighMonthTotal").append("TotalDays:"+totalDays);
			
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