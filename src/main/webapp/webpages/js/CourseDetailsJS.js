       google.charts.load('current', {packages: ['corechart', 'line','bar']});
        google.charts.setOnLoadCallback(function(){
			defaultLoadAttendanceChart();
			defaultTestChart();
		});

		var url_string = window.location.href;
		var url = new URL(url_string);
		var courseId = url.searchParams.get("courseId");
		const faculty = url.searchParams.get("faculty");
		const college = url.searchParams.get("college");


function defaultTestChart(){
	const data={
		courseId:courseId
	}
	$.ajax({
		url:'myTestChart',
		method:'get',
		data:data,
		success:function(response){
			console.log(response.length);
			drawBackgroundColor(response);
			
		},
		error:function(){
			
		}
	})
}
       

function drawBackgroundColor(chart_data) {
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
	            subtitle: '' },
	          axes: {
	            x: {
	              0: { side: 'top', label: ''} // Top x-axis.
	            }
	          },
	          bar: { groupWidth: "90%" }
	        };
	
	        var chart = new google.charts.Bar(document.getElementById('Testchart_div'));
	        // Convert the Classic options to Material options.
	        chart.draw(data, google.charts.Bar.convertOptions(options));
		}else{
			document.getElementById('Testchart_div').innerHTML='No data available';
		}
		
	
       	
 }
    
    		
    
	   $(document).ready(function(){

			
			const today = new Date();
			$("#AttendanceMonth").val(today.toISOString().slice(0,7));
			const month = today.getMonth()+1;
			const year = today.getFullYear();
			
			const formdate = $("#AttendanceMonth").val();
			const date = new Date(formdate);
			LoadAttendanceBread(date,courseId);
			loadTestBread(courseId);
			$("#getAttendanceBtn").click(function(){
				const formdate = $("#AttendanceMonth").val();
				const date = new Date(formdate);
				
				LoadAttendanceBread(date,courseId);
				LoadAttendanceChart(date);
			})
		})
		
		function loadTestBread(courseId){
			thiscourse = {
				courseId:courseId
			}
			$.ajax({
				url:'myTestAnalysis',
				method:'get',
				data:thiscourse,
				success:function(response){
					console.log(response);
					$('#recentHighMarks').append(response.HighStudentName);
					$('#recentHighObtain').append(response.highMarks);
					$('.recentFullMarks').append(response.fullMarks);
					$('#recentLowMarks').append(response.LowStudentName);
					$('#recentLowObtain').append(response.lowMarks);
				},
				error:function(){
					
				}
			})
		}
		
		
		
		
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
					$(".headCourseA").append("Attendance Chart for "+response.courseName+"("+faculty+")_"+college);
					
					$(".totalStudent").empty();
					$(".totalStudent").append(response.totalStudents);
					
					
					
					$("#AllTimeHighPresent").empty();
					$("#AllTimeHighPresent").append("Presentdays:"+response.allTimeHighCount);
					
					if(typeof response.AllTimeHighName !== 'undefined'){
						$("#AllTimeHighName").empty();
						$("#AllTimeHighName").append("<button class='successButton' value='"+response.AllTimeHighName+"' >View</button>");
						$("#AllTimeLowName").empty();
						$("#AllTimeLowName").append("<button class='successButton' value='"+response.AllTimeLowName+"' >View</button>");
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
						$("#HighMonthName").append("<button class='successButton' value='"+response.highPresentName+"' >View</button>");
						$("#HighMonthPresent").empty();
						$("#HighMonthPresent").append("PresentDays:"+response.highPresentCount);
						
						if(response.lowPresentName ==""){
							console.log("empty")
							$("#LowMonthName").empty();
							$("#LowMonthName").append("<button class='successButton' value='"+response.lowPresentName+"' >View</button>");
							$("#LowMonthPresent").empty();
							$("#LowMonthPresent").append("PresentDays:");
						}else{
							$("#LowMonthName").empty();
							$("#LowMonthName").append("<button class='successButton' value='"+response.lowPresentName+"' >View</button>");
							$("#LowMonthPresent").empty();
							$("#LowMonthPresent").append("PresentDays:"+response.lowPresentCount);
						}
						
						
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
    

    	$(".card").on('click','.successButton',function(){
			var names = $(this).val();
			var name = names.split(',');
			var list="";
			for(i=0;i<name.length;i++){
				console.log(name[i]);
				list=list+name[i]+"<br>";
			}
			Swal.fire(list)
			
			console.log(names);
			
		})
    
    	
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
    	    Attendancedata.addColumn('number', 'Student Present: ');
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
    	              title: 'Date'
    	            },
    	            vAxis: {
    	              title: 'Attendance'
    	            },
    	            backgroundColor: '#fefefe'
    	        
    	          };

    	    var Attendancechart = new google.visualization.LineChart(document.getElementById('chart_div'));
    	    Attendancechart.draw(Attendancedata, options);
    	}