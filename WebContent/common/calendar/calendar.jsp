<%--===========================================================================
Calendar
author  ssong
version $Id: calendar.jsp,v 1.2 2013/11/01 02:08:36 hiimkkm Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<!-- Calendar -->
<TITLE></TITLE> 
</head>
<script>
var gdCurDate = new Date();
var giYear = gdCurDate.getFullYear();
var giMonth = gdCurDate.getMonth()+1;
var giDay = gdCurDate.getDate();

//alert(gdCurDate.getHours());
//alert(gdCurDate.getMinutes());


var curYear,curMonth, clickObj;
function setCalYear(mYear)
{
	if(typeof mYear == "undefined")
	{
		curYear = giYear;
		curMonth = giMonth;
		mYear = 0;
	}
	curYear = curYear + mYear;
	var startYear = curYear - 5;
	var lastYear  = curYear + 5;
	
	$('#monthPicker').find(".ui-datepicker-year").text(curYear);
	$('#monthPicker').find('.ui-state-default').each(function(index){
		if(curYear+$(this).text() == giYear+""+giMonth) $(this).addClass("ui-state-highlight");
		else $(this).removeClass("ui-state-highlight");
	});

	$('#yearPicker').find(".ui-datepicker-year").text(startYear+" - "+lastYear);
	$('#yearPicker').find('.ui-state-default').each(function(index){

		$(this).text(startYear + index);

		if(startYear + index == giYear) $(this).addClass("ui-state-highlight");
		else $(this).removeClass("ui-state-highlight");
		
	});
}

function setAmpm(type, isSeconds)
{
	var sTime;
	
	if(type == "AM")
	{
		sTime = 0;
	}	
	else if(type == "PM")
	{
		sTime = 12;
	}
	if("N"==isSeconds)
	{
		$('.hours').find('a').each(function(e){
			var txtTime = e + sTime;
			
			if(txtTime == gdCurDate.getHours()) $(this).addClass("ui-state-highlight");
			else $(this).removeClass("ui-state-highlight");
				
			if(txtTime < 10) txtTime = "0"+txtTime;
			
			$(this).text(txtTime);
		});
	}
	else
	{
		$('.hours-seconds').find('a').each(function(e){
			var txtTime = e + sTime;
			
			if(txtTime == gdCurDate.getHours()) $(this).addClass("ui-state-highlight");
			else $(this).removeClass("ui-state-highlight");
				
			if(txtTime < 10) txtTime = "0"+txtTime;
			
			$(this).text(txtTime);
		});
	}
}

function setTime()
{
	//var timePickerTitle = giYear+"년"+giMonth+"월"+giDay+"일";
	var timePickerTitle = "시간";
	var hours = gdCurDate.getHours() < 10?"0"+gdCurDate.getHours():gdCurDate.getHours();
	var mimutes = gdCurDate.getMinutes();
	
	$('.timePicker_title').text(timePickerTitle);
	
	if(gdCurDate.getHours() >= 12)
	{
		$('.pm').addClass("ui-state-highlight");
		$('.am').removeClass("ui-state-highlight");
		
		setAmpm("PM", "N");
	}
	else
	{
		$('.am').addClass("ui-state-highlight");
		$('.pm').removeClass("ui-state-highlight");
		
		setAmpm("AM", "N");
	}
	
	$('.hours').find('a').each(function(idx){
		if(hours == $(this).text())
		{
			$(this).addClass("ui-state-highlight");
		}
		else
		{
			$(this).removeClass("ui-state-highlight");
		}
	});
	
	$('.minutes').find('a').each(function(idx){
		if(mimutes == $(this).text())
		{
			$(this).addClass("ui-state-highlight");
		}
		else
		{
			$(this).removeClass("ui-state-highlight");
		}
	});
	
	
	if($('.minutes').find('.ui-state-highlight').length == 0)
	{
		var mimutes = gdCurDate.getMinutes();
		do
		{
			mimutes = mimutes - 1;
		}while(mimutes%5 != 0);

		$('.minutes').find('a').each(function(idx)
	    {
			if(mimutes == $(this).text()) $(this).addClass("ui-state-highlight");
		});
	}
	
	$('.ampm').find('a').on("click", function(e){
		$('.ampm').find('a').removeClass("ui-state-highlight");
		
		$(this).addClass("ui-state-highlight");
		
		var sTime;
		if($(this).is('.am'))
		{
			setAmpm("AM", "N");
		}
		else
		{
			setAmpm("PM","N");
		}
	});
	
}

function setTimeSecond()
{
	//var timePickerTitle = giYear+"년"+giMonth+"월"+giDay+"일";
	var timeSecondPickerTitle = "시간";
	var hours = gdCurDate.getHours() < 10?"0"+gdCurDate.getHours():gdCurDate.getHours();
	var mimutes = gdCurDate.getMinutes();
	var seconds = gdCurDate.getSeconds();
	
	$('.timeSecondPicker_title').text(timeSecondPickerTitle);
	
	if(gdCurDate.getHours() >= 12)
	{
		$('.pm-seconds').addClass("ui-state-highlight");
		$('.am-seconds').removeClass("ui-state-highlight");
		
		setAmpm("PM","Y");
	}
	else
	{
		$('.am-seconds').addClass("ui-state-highlight");
		$('.pm-seconds').removeClass("ui-state-highlight");
		
		setAmpm("AM","Y");
	}
	
	$('.hours-seconds').find('a').each(function(idx){
		if(hours == $(this).text())
		{
			$(this).addClass("ui-state-highlight");
		}
		else
		{
			$(this).removeClass("ui-state-highlight");
		}
	});
	
	$('.minutes-seconds').find('a').each(function(idx){
		if(mimutes == $(this).text())
		{
			$(this).addClass("ui-state-highlight");
		}
		else
		{
			$(this).removeClass("ui-state-highlight");
		}
	});
	
	$('.seconds').find('a').each(function(idx){
		if(seconds == $(this).text())
		{
			$(this).addClass("ui-state-highlight");
		}
		else
		{
			$(this).removeClass("ui-state-highlight");
		}
	});
	
	if($('.minutes-seconds').find('.ui-state-highlight').length == 0)
	{
		var mimutes = gdCurDate.getMinutes();
		do
		{
			mimutes = mimutes - 1;
		}while(mimutes%5 != 0);

		$('.minutes-seconds').find('a').each(function(idx)
	    {
			if(mimutes == $(this).text()) $(this).addClass("ui-state-highlight");
		});
	}
	
	if($('.seconds').find('.ui-state-highlight').length == 0)
	{
		var seconds = gdCurDate.getSeconds();
		do
		{
			seconds = seconds - 1;
		}while(seconds%5 != 0);

		$('.seconds').find('a').each(function(idx)
	    {
			if(seconds == $(this).text()) $(this).addClass("ui-state-highlight");
		});
	}
	
	$('.ampm-seconds').find('a').on("click", function(e){
		$('.ampm-seconds').find('a').removeClass("ui-state-highlight");
		
		$(this).addClass("ui-state-highlight");
		
		var sTime;
		if($(this).is('.am-seconds'))
		{
			setAmpm("AM","Y");
		}
		else
		{
			setAmpm("PM","Y");
		}
	});
} // end of setTimeSecond()

$( document ).ready(function() {

	setCalYear();

	$('.ui-datepicker-prev').on("click",function(e){

		if($(this).parents('#monthPicker').length == 1)
		{
			setCalYear(-1);
		}
		else
		{
			setCalYear(-12);
		}
	});
	
	$('.ui-datepicker-next').on("click",function(e){

		if($(this).parents('#monthPicker').length == 1)
		{
			setCalYear(1);
		}
		else
		{
			setCalYear(12);
		}
	});

	//year open 설정
	$('.open_year_calendar').on("click",function(e){

		setCalYear(); //달력리셋
		$('#monthPicker').hide();
		$('#timePicker').hide();
		$('#timeSecondPicker').hide();
		$('#yearPicker').show();

		clickObj = $(this).parent().find('input');//.prev();
		var offset = clickObj.offset();
		var height = clickObj.outerHeight(true);
		//위치 설정 
		$('#yearPicker').css({
			"left":offset.left,
			"top" :offset.top + height
		});
		
	});
	
	//month open 설정
	$('.open_mon_calendar').on("click",function(e){

		setCalYear(); //달력리셋
		$('#yearPicker').hide();
		$('#timePicker').hide();
		$('#timeSecondPicker').hide();
		$('#monthPicker').show();

		clickObj = $(this).parent().find('input');//.prev();
		var offset = clickObj.offset();
		var height = clickObj.outerHeight(true);

		//위치 설정 
		$('#monthPicker').css({
			"left":offset.left,
			"top" :offset.top + height
		});

	});
	
	$('.open_time').on("click",function(e){
		setTime();

		$('#monthPicker').hide();
		$('#yearPicker').hide();
		$('#timePicker').show();
		$('#timeSecondPicker').hide();

		clickObj = $(this).parent().find('input');//prev();
		var offset = clickObj.offset();
		var height = clickObj.outerHeight(true);
		var left, top;
		//오른쪽 화면 밖으로 밀려나가는거 방지  
		if($( document ).width()- offset.left < 340)
		{
			left = $( document ).width() - 350;
		}
		else left = offset.left;
		top = offset.top + height;
		
		//alert($(document).height() + "   "+$(document).height() +"   "+ top+"     결론 탑:"+($(document).height() - 290)); //286


		var docHeight; //문서 높이 계산
		if(parent)
		{
			if(parent.$('#tabFrameTAB\\.'+currentPageId).length != 0)
			{
				docHeight = parent.$('#tabFrameTAB\\.'+currentPageId).height();
			}
		}
		else docHeight = $(document).height();

		if(docHeight - top < 100 ) //아래로 숨지 않도록...
		{
			top = docHeight - 130;
		} 
		
		//위치 설정 
		$('#timePicker').css({
			"left":left,
			"top" :top
		});
		
	}).parent().find('input').on("keyup",function(e){
		timeFormat(this, e);
	}).on("keydown",function(e){
		onlyNumberInput(e);
	}).on("blur",function(e){
		validTime(this);
	})
	
	
	$('.open_time_second').on("click",function(e){
		setTimeSecond();

		$('#monthPicker').hide();
		$('#yearPicker').hide();
		$('#timePicker').hide();
		$('#timeSecondPicker').show();

		clickObj = $(this).parent().find('input');//prev();
		var offset = clickObj.offset();
		var height = clickObj.outerHeight(true);
		var left, top;
		//오른쪽 화면 밖으로 밀려나가는거 방지  
		if($( document ).width()- offset.left < 340)
		{
			left = $( document ).width() - 350;
		}
		else left = offset.left;
		top = offset.top + height;
		
		//alert($(document).height() + "   "+$(document).height() +"   "+ top+"     결론 탑:"+($(document).height() - 290)); //286


		var docHeight; //문서 높이 계산
		if(parent)
		{
			if(parent.$('#tabFrameTAB\\.'+currentPageId).length != 0)
			{
				docHeight = parent.$('#tabFrameTAB\\.'+currentPageId).height();
			}
		}
		else docHeight = $(document).height();

		if(docHeight - top < 100 ) //아래로 숨지 않도록...
		{
			top = docHeight - 130;
		} 
		
		//위치 설정 
		$('#timeSecondPicker').css({
			"left":left,
			"top" :top
		});
		
	}).parent().find('input').on("keyup",function(e){
		timeSecondFormat(this, e);
	}).on("keydown",function(e){
		onlyNumberInput(e);
	}).on("blur",function(e){
		validTimeSecond(this);
	})
	
	//바탕화면 클릭하면 창 닫기
	$(document).on("click", function(e){
		//alert($(e.target).prop("class") +"   "+$(e.target).is('.open_year_calendar,.ui-icon'));
		if(!$(e.target).is('.open_year_calendar,.ui-icon,.open_mon_calendar,.open_time,.open_time_second'))
		{
			if(!$('#yearPicker').is(':hidden')) $('#yearPicker').hide();
			
			if(!$('#monthPicker').is(':hidden')) $('#monthPicker').hide();
			
			if(!$('#timePicker').is(':hidden') && !$('#timePicker').is(':hover')){
				$('#timePicker').hide();
			}
		}
	});

	//월, 년 클릭하면 입력
	$('.ui-state-default').on({"click":function(e){
		if(clickObj)
		{
			if($(this).parents('#monthPicker').length == 1)
			{
				var mon = $(e.target).text().length == 1?"0"+$(e.target).text():$(e.target).text()
				clickObj.val(curYear+"-"+mon);	
				chkInputVal(clickObj);
				
				if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
				
				if(typeof afterSetMonth == "function") afterSetMonth(clickObj.attr("name"));
			}
			else if($(this).parents('#yearPicker').length == 1)
			{
				clickObj.val($(e.target).text());	
				chkInputVal(clickObj);
				
				if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
				
				if(typeof afterSetYear == "function") afterSetYear(clickObj.attr("name"));
			}
			else if($(this).parents('#timePicker').length == 1)
			{
/* 				var hours = $('.hours').find(".ui-state-highlight").text();
				var minutes = $('.minutes').find(".ui-state-highlight").text();
				
				clickObj.val(hours+":"+minutes);	
	
				if(typeof afterSetTime == "function") afterSetTime(clickObj.attr("name"));
				if($(this).parents('.minutes').length == 1) $('#timePicker').hide(); */
				
			}
		}
	}
/* 	"dblclick":function(e){
		if(clickObj)
		{
			if($(this).parents('#timePicker').length == 1)
			{
				var hours = $('.hours').find(".ui-state-highlight").text();
				var minutes = $('.minutes').find(".ui-state-highlight").text();
				
				clickObj.val(hours+":"+minutes);	
				
				$('#timePicker').hide();
			}
		}
	} */
	});
	
	$('.hours').find('a').on("click", function(e){
		$('.hours').find('a').removeClass("ui-state-highlight");
		
		$(this).addClass("ui-state-highlight");
		
		var hours = $('.hours').find(".ui-state-highlight").text();
		var minutes = $('.minutes').find(".ui-state-highlight").text();

		clickObj.val(hours+":"+minutes);
		chkInputVal(clickObj);
		
		if(curPageUpdate) getTopPage().updateArray[currentPageId] = "TIME";
		if(typeof afterSetTime == "function") afterSetTime(clickObj.attr("name"));
	});
	
	$('.minutes').find('a').on("click", function(e){ //분을 선택하면 
		$('.minutes').find('a').removeClass("ui-state-highlight");  //다른 분들의 선택을 없애고..
		
		$(this).addClass("ui-state-highlight"); //선택한 분을 하이라이트 시킨다.
		
		var hours = $('.hours').find(".ui-state-highlight").text(); //하이라이트된 시간과
		var minutes = $('.minutes').find(".ui-state-highlight").text(); //분을 추출해서...

		clickObj.val(hours+":"+minutes);	//선택된 OBJ(input)에 넣는다.
		chkInputVal(clickObj);
		
		if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE"; //update page이면 update sign을 넣는다.
		if(typeof afterSetTime == "function") afterSetTime(clickObj.attr("name")); // 입력후 afterSetTime을 호출한다. callback
		$('#timePicker').hide(); //분을 선택하면 닫는다.
	});
	
	
	$('.hours-seconds').find('a').on("click", function(e){
		$('.hours-seconds').find('a').removeClass("ui-state-highlight");
		
		$(this).addClass("ui-state-highlight");
		
		var hours = $('.hours-seconds').find(".ui-state-highlight").text();
		var minutes = $('.minutes-seconds').find(".ui-state-highlight").text();
		var seconds = $('.seconds').find(".ui-state-highlight").text();

		clickObj.val(hours+":"+minutes+":"+seconds);
		chkInputVal(clickObj);
		
		if(curPageUpdate) getTopPage().updateArray[currentPageId] = "TIME";
		if(typeof afterSetTime == "function") afterSetTime(clickObj.attr("name"));
	});
	
	$('.minutes-seconds').find('a').on("click", function(e){ //분을 선택하면 
		$('.minutes-seconds').find('a').removeClass("ui-state-highlight");  //다른 분들의 선택을 없애고..
		
		$(this).addClass("ui-state-highlight"); //선택한 분을 하이라이트 시킨다.
		
		var hours = $('.hours-seconds').find(".ui-state-highlight").text(); //하이라이트된 시간과
		var minutes = $('.minutes-seconds').find(".ui-state-highlight").text(); //분을 추출해서...
		var seconds = $('.seconds').find(".ui-state-highlight").text();

		clickObj.val(hours+":"+minutes+":"+seconds);	//선택된 OBJ(input)에 넣는다.
		chkInputVal(clickObj);
		
		if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE"; //update page이면 update sign을 넣는다.
		if(typeof afterSetTime == "function") afterSetTime(clickObj.attr("name")); // 입력후 afterSetTime을 호출한다. callback
// 		$('#timePicker').hide(); //분을 선택하면 닫는다.
	});
	
	$('.seconds').find('a').on("click", function(e){ //초를 선택하면 
		$('.seconds').find('a').removeClass("ui-state-highlight");  //다른 초들의 선택을 없애고..
		
		$(this).addClass("ui-state-highlight"); //선택한 초를 하이라이트 시킨다.
		
		var hours = $('.hours-seconds').find(".ui-state-highlight").text(); //하이라이트된 시간과
		var minutes = $('.minutes-seconds').find(".ui-state-highlight").text(); //분과
		var seconds = $('.seconds').find(".ui-state-highlight").text(); //초를 추출해서...

		clickObj.val(hours+":"+minutes+":"+seconds);	//선택된 OBJ(input)에 넣는다.
		chkInputVal(clickObj);
		
		if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE"; //update page이면 update sign을 넣는다.
		if(typeof afterSetTime == "function") afterSetTime(clickObj.attr("name")); // 입력후 afterSetTime을 호출한다. callback
		$('#timeSecondPicker').hide(); //초를 선택하면 닫는다.
	});
});

</script>
<body>

<div id="monthPicker" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" style="left: 400px; top: 178px; width:240px; display:none; position: absolute; z-index: 5000;">
	<div class="ui-datepicker-group">
		<div class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left">
			<a title="Prev" class="ui-datepicker-prev ui-corner-all" data-event="click" data-handler="prev"><span class="ui-icon ui-icon-circle-triangle-w">Prev</span></a>
			<div class="ui-datepicker-title"><span class="ui-datepicker-year"></span></div>
			<a title="Next" class="ui-datepicker-next ui-corner-all" data-event="click" data-handler="next"><span class="ui-icon ui-icon-circle-triangle-e">Next</span></a>
		</div>
		<table class="ui-monthyearpicker-calendar">
			<tbody>
				<tr>
					<td class=" " data-event="click"><a class="ui-state-default" >1</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >3</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >4</a></td>
				</tr>
				<tr>
					<td class=" " data-event="click"><a class="ui-state-default" >5</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >6</a></td>
					<td class=" " data-event="click"><a class="ui-state-default ui-state-highlight" >7</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >8</a></td>
				</tr>
				<tr>
					<td class=" " data-event="click"><a class="ui-state-default" >9</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >10</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >11</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >12</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div id="yearPicker" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" style="left: 700px; top: 178px; width:240px; display:none; position: absolute; z-index: 5000;">
	<div class="ui-datepicker-group">
		<div class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left">
			<a title="Prev" class="ui-datepicker-prev ui-corner-all" data-event="click" data-handler="prev"><span class="ui-icon ui-icon-circle-triangle-w">Prev</span></a>
			<div class="ui-datepicker-title"><span class="ui-datepicker-year">2011년-2022년</span></div>
			<a title="Next" class="ui-datepicker-next ui-corner-all" data-event="click" data-handler="next"><span class="ui-icon ui-icon-circle-triangle-e">Next</span></a>
		</div>
		<table class="ui-monthyearpicker-calendar">
			<tbody>
				<tr>
					<td class=" " data-event="click"><a class="ui-state-default" >2011</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2012</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2013</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2014</a></td>
				</tr>
				<tr>
					<td class=" " data-event="click"><a class="ui-state-default" >2015</a></td>
					<td class=" " data-event="click"><a class="ui-state-default ui-state-highlight" >2016</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2017</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2018</a></td>
				</tr>
				<tr>
					<td class=" " data-event="click"><a class="ui-state-default" >2019</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2020</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2021</a></td>
					<td class=" " data-event="click"><a class="ui-state-default" >2022</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div id="timePicker" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" id="ui-datepicker-div" style="left: 300px; top: 178px; width:330px; display: none; position: absolute; z-index: 5000;">
	<div class="ui-datepicker-group">
		<div class="tb_ampm">
			<table class="ui-timepicker-calendar ampm" cellspacing="0">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default am" ><bean:message key="LABEL.morning"/></a></td>
						<td class=" " data-event="click"><a class="ui-state-default pm ui-state-highlight" ><bean:message key="LABEL.evening"/></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tb_hours">
			<table class="ui-timepicker-calendar hours">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default" >13</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >14</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >15</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >16</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >17</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >18</a></td>
						<td class=" " data-event="click"><a class="ui-state-default  ui-state-highlight" >19</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >20</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >21</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >22</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >23</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >24</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tb_minutes">
			<table class="ui-timepicker-calendar minutes">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default" >00</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >05</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >10</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >15</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >20</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >25</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >30</a></td>
						<td class=" " data-event="click"><a class="ui-state-default  ui-state-highlight" >35</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >40</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >45</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >50</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >55</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- <div id="timeSecondPicker" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" id="ui-datepicker-div" style="left: 300px; top: 178px; width:330px; display: none; position: absolute; z-index: 5000;"> -->
<div id="timeSecondPicker" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" style="left: 300px; top: 178px; width:330px; display: none; position: absolute; z-index: 5000;">
	<div class="ui-datepicker-group">
		<div class="tb_ampm">
			<table class="ui-timepicker-calendar ampm-seconds" cellspacing="0">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default am-seconds" ><bean:message key="LABEL.morning"/></a></td>
						<td class=" " data-event="click"><a class="ui-state-default pm-seconds ui-state-highlight" ><bean:message key="LABEL.evening"/></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tb_hours">
			<table class="ui-timepicker-calendar hours-seconds">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default" >13</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >14</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >15</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >16</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >17</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >18</a></td>
						<td class=" " data-event="click"><a class="ui-state-default  ui-state-highlight" >19</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >20</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >21</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >22</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >23</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >24</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tb_hours">
			<table class="ui-timepicker-calendar minutes-seconds">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default" >00</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >05</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >10</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >15</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >20</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >25</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >30</a></td>
						<td class=" " data-event="click"><a class="ui-state-default  ui-state-highlight" >35</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >40</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >45</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >50</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >55</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tb_minutes">
			<table class="ui-timepicker-calendar seconds">
				<tbody>
					<tr>
						<td class=" " data-event="click"><a class="ui-state-default" >00</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >05</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >10</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >15</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >20</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >25</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >30</a></td>
						<td class=" " data-event="click"><a class="ui-state-default  ui-state-highlight" >35</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >40</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >45</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >50</a></td>
						<td class=" " data-event="click"><a class="ui-state-default" >55</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body> 
</html>
