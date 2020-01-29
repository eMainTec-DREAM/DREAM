<%--===========================================================================
Main
author  wondo
version $Id: main.jsp,v 1.51 2015/01/09 00:16:42 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@page import="dream.main.action.MainAction"%>
<%@page import="dream.login.login.action.LoginAction"%>
<%@ page import="common.struts.BaseAction"%>
<%@ page import="common.util.StringUtil"%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Hashtable"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.util.CommonUtil"%>
<%@ page import="common.bean.MwareConfig"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<jsp:useBean id="mainForm" class="dream.main.form.MainForm" scope="request" />
<%
	//로그인 유저
	User menuLoginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String menuLoginUserName = menuLoginUser.getUserName();

	ArrayList menuList = (ArrayList)session.getAttribute("MENU");

	//===============================================
	// 점검
	String[][] checkList = (String[][])session.getAttribute("CHECKLIST");
	//===============================================
	    
	//===============================================
	// 작업
	String[][] woList = (String[][])session.getAttribute("WOLIST");
	//===============================================
	    
	//===============================================
	// 정비
	String[][] pmList = (String[][])session.getAttribute("PMLIST");
	//===============================================
	        
	
    // login 후 main에서 셋팅된 admin menu를 session에서 가지고 온다.
	String [][] adminMenu1 = (String [][])session.getAttribute("ADMINMENU");
	int adminMenuCount = 0;
	adminMenuCount = adminMenu1.length;
	String adminPage = "";
	if(adminMenuCount!=0)
	{
	    adminPage = adminMenu1[0][1];
	}
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<!-- 설비관리시스템 -->
<title><bean:message key="main.title"/></title>
<c:import charEncoding="UTF-8"  url="/common/jsp/commonInclude.jsp"></c:import>
<link href="<c:url value='/common/css/main/main.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/common/css/main/common.css' />" rel="stylesheet" type="text/css" />
<!-- highcharts  -->
<script type="text/javascript" src="<c:url value="/common/chart/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/common/chart/highcharts.js" />"></script>
<script type="text/javascript" src="<c:url value="/common/chart/exporting.js" />"></script>
<script language="JavaScript" type="text/JavaScript">
//<![CDATA[
function loadPage()
{
	//메뉴바의 위치를 결정한다.
	initTabMenu("menu_cate");
	
	//Inspection 차트 펑션
	getInspChart();
	//자재 입고/출고 차트 펑션
	getPartChart();
}

/**
 * 메인페이지 재조회
 */
function reloadMain()
{
	goMenuPage('main');
}

var list_layers = new Array( "checking", "incomplete", "maintaining");

var list_objs = new Array( list_layers.length );
var inspChart;
var partChart;

function getInspChart()
{	
	//SeriesVal 에 담아줄 변수
	var seriesVal = [];
	//계획 배열
	var planValue = [];
	//실적 배열
	var resultValue = [];
	//일자 배열
	var dateValue = [];
	
	planValue.push(parseInt(mainForm.elements['inspChartDTO.sundayPlan'].value));
	planValue.push(parseInt(mainForm.elements['inspChartDTO.mondayPlan'].value));
	planValue.push(parseInt(mainForm.elements['inspChartDTO.tuesdayPlan'].value));
	planValue.push(parseInt(mainForm.elements['inspChartDTO.wednesdayPlan'].value));
	planValue.push(parseInt(mainForm.elements['inspChartDTO.thursdayPlan'].value));
	planValue.push(parseInt(mainForm.elements['inspChartDTO.fridayPlan'].value));
	planValue.push(parseInt(mainForm.elements['inspChartDTO.saturdayPlan'].value));
	
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.sundayResult'].value));
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.mondayResult'].value));
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.tuesdayResult'].value));
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.wednesdayResult'].value));
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.thursdayResult'].value));
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.fridayResult'].value));
	resultValue.push(parseInt(mainForm.elements['inspChartDTO.saturdayResult'].value));
	
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.sunday'].value));
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.monday'].value));
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.tuesday'].value));
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.wednesday'].value));
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.thursday'].value));
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.friday'].value));
	dateValue.push(parseInt(mainForm.elements['inspChartDTO.saturday'].value));
	
	seriesVal.push({ name:'<bean:message key="main.plan" />'+' ('+mainForm.elements['inspChartDTO.totalPlan'].value+')', data:planValue });
	seriesVal.push({ name:'<bean:message key="main.result" />'+' ('+mainForm.elements['inspChartDTO.totalResult'].value+')',type:'spline', data:resultValue });

	inspChart = new Highcharts.Chart({
	chart: {
        renderTo: 'inspChart',
        type: 'column'
    },
    credits: {
  	  enabled: false
    },
    colors: [
             '#6B66FF', 
             '#DBF0000'
          ],
    title: {
        text: ''
    },
    xAxis: {
        categories: ['<bean:message key="main.sunday" />', '<bean:message key="main.monday" />', '<bean:message key="main.tuesday" />', 
                     '<bean:message key="main.wednesday" />', '<bean:message key="main.thursday" />', '<bean:message key="main.friday" />', '<bean:message key="main.saturday" />']
    },
    yAxis: {
        min: 0,
        title: {
            text: ''
        },
        stackLabels: {
            enabled: true,
            style: {
                fontWeight: 'bold',	
                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
            }
        }
    },
    legend: {
		align: 'center',
		borderWidth: 0,
		margin:0,
		y:12
	},
	plotOptions:{
		series:{
			point:{
				events:{
					click: function(){
						//0이 아닌경우만 실행
						if(this.y!='0')
						{
							if(this.series.type=="column")
							{
								goMenuPlan(dateValue[this.x]);
							}
							else
							{
								goMenuResult(dateValue[this.x]);
							}
						}						
					}
				}
			}
		}
	},
    tooltip: {
        formatter: function() {
        	return '<b>'+ this.series.name.split("(")[0] +'</b><br/>'+
            this.x  +': '+ Highcharts.numberFormat(this.y,0);
        }
    },
    series: seriesVal
	});
}

function goMenuPlan(day)
{			
		<%-- //==========================
		if (checkIsUpdate()) return;
		//==========================
		try{// 현재 Menu의 scrollTop 을 전송하여 페이지 reload 시에 클릭했을때 와 같은 위치로 열려있게 한다.
			document.bottomForm.menuScrollTop.value = document.getElementById("MWARE_MENU").scrollTop;
			}catch(e){}
		document.bottomForm.strutsAction.value = "";
		
		
		// 모래시계
		beginLoading();
		document.bottomForm.target = "";
		document.bottomForm.action = contextPath + "/" + "pmiGrouprsltList" + ".do"+"?pmiGrouprsltCommonDTO.startDate="+day+
									"&pmiGrouprsltCommonDTO.endDate="+day+
									"&strutsAction="+ '<%=PmiGrouprsltListAction.PMI_MAIN_CHART%>';
		document.bottomForm.submit();
		
			
		---- --%>
	
		var url   = contextPath + "/pmiGrouprsltList.do";

	    var popWidth = 1020;
	    var popHeight = 650;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=no";
	    
	    var param = "?" + "strutsAction=" 
	    		+"&isDecoratorName=popupPage"+"&pmiGrouprsltCommonDTO.startDate="+day+
		"&pmiGrouprsltCommonDTO.endDate="+day;
	  
	    window.open(url + param, "BOARD_POPUP", pos);
	
}

function goMenuResult(day)
{			
		<%-- //==========================
		if (checkIsUpdate()) return;
		//==========================
		try{// 현재 Menu의 scrollTop 을 전송하여 페이지 reload 시에 클릭했을때 와 같은 위치로 열려있게 한다.
			document.bottomForm.menuScrollTop.value = document.getElementById("MWARE_MENU").scrollTop;
			}catch(e){}
		document.bottomForm.strutsAction.value = "";
		
		
		// 모래시계
		beginLoading();
		document.bottomForm.target = "";
		document.bottomForm.action = contextPath + "/" + "pmiGroupresultList" + ".do"+"?pmiGroupresultListDTO.startCheckDate="+day+
									"&pmiGroupresultListDTO.endCheckDate="+day+
									"&strutsAction="+ '<%=PmiGroupresultListAction.PMI_MAIN_CHART%>';
		document.bottomForm.submit(); --%>
		
		
		
		var url   = contextPath + "/pmiGroupresultList.do";

	    var popWidth = 1020;
	    var popHeight = 650;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=no";
	    
	    var param = "?" + "strutsAction=" 
	    		+"&isDecoratorName=popupPage"+"&pmiGroupresultListDTO.startCheckDate="+day+
				"&pmiGroupresultListDTO.endCheckDate="+day;
	  
	    window.open(url + param, "BOARD_POPUP", pos);
	
}

function getPartChart()
{
	//SeriesVal 에 담아줄 변수
	var seriesVal = [];
	//입고 배열
	var recValue = [];
	//출고 배열
	var issValue = [];

	var monthArray = (mainForm.elements['partChartDTO.monthArray'].value).split(",");
	
	for(var i=0; i<monthArray.length;i++)
	{
		//Ex)201101 -> 2011-01
		monthArray[i]=monthToMonth(monthArray[i]);		
	}
	
	recValue.push(parseInt(mainForm.elements['partChartDTO.monthRec1'].value));
	recValue.push(parseInt(mainForm.elements['partChartDTO.monthRec2'].value));
	recValue.push(parseInt(mainForm.elements['partChartDTO.monthRec3'].value));
	recValue.push(parseInt(mainForm.elements['partChartDTO.monthRec4'].value));
	recValue.push(parseInt(mainForm.elements['partChartDTO.monthRec5'].value));
	recValue.push(parseInt(mainForm.elements['partChartDTO.monthRec6'].value));

	issValue.push(parseInt(mainForm.elements['partChartDTO.monthIss1'].value));
	issValue.push(parseInt(mainForm.elements['partChartDTO.monthIss2'].value));
	issValue.push(parseInt(mainForm.elements['partChartDTO.monthIss3'].value));
	issValue.push(parseInt(mainForm.elements['partChartDTO.monthIss4'].value));
	issValue.push(parseInt(mainForm.elements['partChartDTO.monthIss5'].value));
	issValue.push(parseInt(mainForm.elements['partChartDTO.monthIss6'].value));
	
	seriesVal.push({ name:'<bean:message key="main.rec"/>'+' ('+mainForm.elements['partChartDTO.recTotal'].value+')', data:recValue });
	seriesVal.push({ name:'<bean:message key="main.iss"/>'+' ('+mainForm.elements['partChartDTO.issTotal'].value+')', type:'spline', data:issValue });

	partChart = new Highcharts.Chart({
	chart: {
        renderTo: 'partChart',
        type: 'column'
    },
    credits: {
  	  enabled: false
    },
    colors: [
             '#6B66FF', 
             '#2F9D27'
          ],
    title: {
        text: ''
    },
    xAxis: {
        categories: [monthArray[0], monthArray[1], monthArray[2], monthArray[3], monthArray[4], monthArray[5]],
        labels: {
            style: {
                fontSize: '10px'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: ''
        },
        stackLabels: {
            enabled: true,
            style: {
                fontWeight: 'bold',			
                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
            }
        }
    },
    tooltip: {
        formatter: function() {
        	return '<b>'+ this.series.name.split("(")[0] +'</b><br/>'+
            this.x  +': '+ Highcharts.numberFormat(this.y,0);
        }
    },
    legend: {
		align: 'center',
		borderWidth: 0,
		margin:0,
		y:12
	},
	plotOptions:{
		series:{
			point:{
				events:{
					click: function(){
						//0이 아닌경우만 실행
						if(this.y!='0')
						{
							if(this.series.type=="column")
							{
								goMenuRec(monthArray[this.x]);
							}
							else
							{
								goMenuIss(monthArray[this.x]);
							}
						}						
					}
				}
			}
		}
	},
    series: seriesVal
	});
}

function goMenuRec(day)
{			
		<%-- //==========================
		if (checkIsUpdate()) return;
		//==========================
		try{// 현재 Menu의 scrollTop 을 전송하여 페이지 reload 시에 클릭했을때 와 같은 위치로 열려있게 한다.
			document.bottomForm.menuScrollTop.value = document.getElementById("MWARE_MENU").scrollTop;
			}catch(e){}
		document.bottomForm.strutsAction.value = "";
		
		
		// 모래시계
		beginLoading();
		document.bottomForm.target = "";
		document.bottomForm.action = contextPath + "/" + "ptHistList" + ".do"+"?ptHistListDTO.ioDateFrom="+day+
									"&ptHistListDTO.ioTypeParam=R"+
									"&strutsAction="+ '<%=PtHistListAction.PT_MSTR_MAIN%>';
		document.bottomForm.submit(); --%>
	
		var url   = contextPath + "/ptHistList.do";

	    var popWidth = 1020;
	    var popHeight = 650;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=no";
	    
	    var param = "?" + "strutsAction=" 
	    		+"&isDecoratorName=popupPage"+"&ptHistListDTO.ioDateFrom="+day+
				"&ptHistListDTO.ioTypeParam=R";
	  
	    window.open(url + param, "BOARD_POPUP", pos);
}

function goMenuIss(day)
{			
		<%-- //==========================
		if (checkIsUpdate()) return;
		//==========================
		try{// 현재 Menu의 scrollTop 을 전송하여 페이지 reload 시에 클릭했을때 와 같은 위치로 열려있게 한다.
			document.bottomForm.menuScrollTop.value = document.getElementById("MWARE_MENU").scrollTop;
			}catch(e){}
		document.bottomForm.strutsAction.value = "";
		
		
		// 모래시계
		beginLoading();
		document.bottomForm.target = "";
		document.bottomForm.action = contextPath + "/" + "ptHistList" + ".do"+"?ptHistListDTO.ioDateFrom="+day+
									"&ptHistListDTO.ioTypeParam=I"+
									"&strutsAction="+ '<%=PtHistListAction.PT_MSTR_MAIN%>';
		document.bottomForm.submit(); --%>
	
		
		var url   = contextPath + "/ptHistList.do";

	    var popWidth = 1020;
	    var popHeight = 650;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=no";
	    
	    var param = "?" + "strutsAction=" 
	    		+"&isDecoratorName=popupPage"+"&ptHistListDTO.ioDateFrom="+day+
				"&ptHistListDTO.ioTypeParam=I";
	  
	    window.open(url + param, "BOARD_POPUP", pos);
		
}

function list_getObjs()
{
	var nObjMax = list_layers.length;
	
	for( nCount = 0; nCount < nObjMax; nCount++ )    
	{
		list_objs[nCount] = document.getElementById( list_layers[nCount] );
	}
}

function list_ChangeBox( layerID )
{
	list_objs[0] != null ? "" : list_getObjs();    
	
	if( layerID == list_objs[0].id )   
	{   list_objs[0].style.display = "block";
		list_objs[1].style.display = "none";
	}
	else if (layerID == list_objs[1].id )
	{
		list_objs[0].style.display = "none";
		list_objs[1].style.display = "block";				
	}
}	

function notice_ChangeBox( layerID )
{
	if( layerID == "notice" )   
	{   document.getElementById("notice").style.display = "block";
		document.getElementById("board").style.display = "none";
	}
	else if (layerID == "board")
	{
		document.getElementById("notice").style.display = "none";
		document.getElementById("board").style.display = "block";			
	}
}

var menuFlag = true;
var thisObj;

function initTabMenu(tabContainerID) 
{
	var tabContainer = document.getElementById(tabContainerID);
	
	var tabAnchor = tabContainer.getElementsByTagName("a");
	
	var i = 0;

	for(i=0; i<tabAnchor.length; i++) 
	{
		if (tabAnchor.item(i).className == "tab")
			thismenu = tabAnchor.item(i);
		else
			continue;

		thismenu.container = tabContainer;
		thismenu.targetEl = document.getElementById(tabAnchor.item(i).href.split("#")[1]);
		thismenu.targetEl.style.display = "none";
		
		thismenu.onclick = function tabMenuClick() 
		{
			currentmenu = this.container.current;
			
			//this를 전역 변수 thisObj로 활용한다.
			thisObj = this;
			
			// 그전 호출된 메뉴를 닫는다.
			if (currentmenu) currentmenu.targetEl.style.display = "none";
		
			// 전에 선택된 메뉴와 현재 선택된 메뉴가 같다면
			if (currentmenu == this)
			{
				if(menuFlag)
				{
					currentmenu.targetEl.style.display = "none";
					closeMenu();
					return;
				}
				else
				{
					openMenu();
				}
			}
			else
			{
				openMenu();
			}
			
			thisObj.container.current = this;
		
			return false;
		};

		if (!thismenu.container.first)
		{
			thismenu.container.first = thismenu;
		}
			
	}
}

var margin;
var height;
var isSlideMenu = true;
if (window.navigator.userAgent.indexOf("MSIE 6.0") > -1)  isSlideMenu = false;
var slideTime = 15;

/**
 *	메뉴바를 펼친다.
 */
function openMenu() 
{
	if (isSlideMenu)
	{
		// main menu를 slide 로 열때
		margin = parseInt(document.all.main_menu.style.marginTop);
		height = parseInt(document.all.main_menu.style.height);
	
		if(margin > 30)
		{
	
			margin = margin - slideTime;
			height = height + slideTime;
	
			document.all.main_menu.style.marginTop = margin;
			document.all.main_menu.style.height = height;
			
			window.setTimeout("openMenu()", 1);
		}
		else
		{
			thisObj.targetEl.style.display = "block";
		}
	}
	else
	{
		// main menu를 slide 로 열지 않을때 test 필요
		document.all.main_menu.style.marginTop = 55;
		document.all.main_menu.style.height = 620;
		thisObj.targetEl.style.display = "block";
	}
	menuFlag = true;
}

/**
**	메뉴바를 닫는다.
**/
function closeMenu() 
{
	if (isSlideMenu)
	{
		// main menu를 slide 로 닫을때
		margin = parseInt(document.all.main_menu.style.marginTop);
		height = parseInt(document.all.main_menu.style.height);
	
		if(margin < 205)
		{
			margin = margin + slideTime;
			height = height - slideTime;
	
			document.all.main_menu.style.marginTop = margin;
			document.all.main_menu.style.height = height;
	
			window.setTimeout("closeMenu()", 1);
		}
	}
	else
	{
		// main menu를 그냥 닫을때 test 필요
		document.all.main_menu.style.marginTop = 205;
		document.all.main_menu.style.height = 440;
	}
	
	menuFlag = false;
}

/**
 * Maintenance Popup
 */
function goMaintPopup()
{
 	var popWidth  = 500;
 	var popHeight = 500;
 	var url;

 	// pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);
     
 	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
 	
 	var strutsAction = '';
 	var param = '';

 	var url   = contextPath + "/maintPopup.do";
 	param = param + "?" + "strutsAction=" + strutsAction;
 	window.open(url+param, "maintPopup", pos+ "scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no");

 	
 }

/**
 * Incomplete List 을 popup 으로 연다.
 */
function goInComPopup()
{
	var popWidth  = 500;
	var popHeight = 500;
	var url;
	
	// pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);
    
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	
	var strutsAction = '';
	var param = '';
	
	
	var url   = contextPath + "/inComPopup.do";
	param = param + "?" + "strutsAction=" + strutsAction;
	window.open(url+param, "inComPopup", pos+ "scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no");
}

/**
 * Check List 을 popup 으로 연다.
 */
function goCheckList()
{
	var popWidth  = 500;
	var popHeight = 500;
	var url;
	
	// pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);
    
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	
	var strutsAction = '';
	var param = '';
	
	
	var url   = contextPath + "/checkPopup.do";
	param = param + "?" + "strutsAction=" + strutsAction;
	window.open(url+param, "ckechPopup", pos+ "scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no");
}

/**
 * 스트링을 적당한 길이로 자른다.
 * maxlength : 자르고자하는 최대 길이(byte단위)
 * str : 자르고자 하는 문자열
 */
function strlength_cut(maxLength, str)
{
	 var sumLength=0;
	 var restr='';
	 for(var i= 0;i < str.length; i++)
	 {
	 	 //한글인 경우
		  if( escape(str.charAt(i)).length > 3 ) 
		  {
		  	tempLength = 2; 
		  }
		  else if(str.charAt(i) == '<' || str.charAt(i) == '>') 
		  { 
		  	tempLength = 4; 
		  }
		  //그외의 경우
		  else 
		  {
		  	tempLength = 1 ; 
		  }
		  
		  //자르고 마지막 문자 이후에 "..." 추가
		  if ( maxLength < (sumLength + tempLength*8) ) 
		  {
		  	restr += "...";
		  	break; 
		  }
		  
		  sumLength += tempLength;
		  restr += str.charAt(i);
	 }
	 return restr;
}

 /**
  * 공지사항
  */
 function goNotice()
 {
	 	var url   = contextPath + "/stdNoticeList.do";

	    var popWidth = 1020;
	    var popHeight = 650;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=no";
	    
	    var param = "?" + "strutsAction=" + lovBaseDefaultAction+"&isDecoratorName=popupPage";
	  
	    window.open(url + param, "BOARD_POPUP", pos);
 }

function setAppDocCount(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
	
    var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC');
    	
	if ("C" == processWfStatus || "D" == processWfStatus )
	{
	    M$("appPend").innerText = aDesc[0];
	}
	else if ("E" == processWfStatus)
	{
	    M$("appReturn").innerText = aDesc[0];
	}	
}

/**
 * 결재할문서
 */
function goAppPrcList()
{
	var url = contextPath + "/appPrcList.do";

	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm);
}

/**
 * 결재요청 문서
 */
function goAppReqList()
{
	var url = contextPath + "/appReqList.do";
	var param = "appReqCommonDTO.isComp=" + "N";
	
	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm, param);
}

/**
 * 수신(통보)된 문서
 */
function goAppNotList()
{
	var url = contextPath + "/appNotList.do";
	
	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm);
}

/**
 * 작업설계
 */
function goWoPlanList()
{
	var url = contextPath + "/woPlanList.do";
	var param =       "woPlanCommonDTO.planDeptNo=" + loginUser.deptNo + 
			    "&" + "woPlanCommonDTO.planDeptDesc=" + loginUser.deptName + 
			    "&" + "woPlanCommonDTO.planEndDate=" + dateToData(getPlusDay(<%=MwareConfig.getMainPeriodDay()%>)) +
			    "&" + "woPlanCommonDTO.woStatus=" + "BA";
	
	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm, param);
}

/**
 * 내작업수행
 */
function goWoResultList()
{
	var url = contextPath + "/woResultList.do";
	var param =       "woResultCommonDTO.workUserId=" + loginUser.userID + 
			    "&" + "woResultCommonDTO.workUserName=" + loginUser.userName + 
			    "&" + "woResultCommonDTO.woStatus=" + "CA";

	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm, param);
}

/**
 * 자재구매입고
 */
function goPurRecList()
{
	var url = contextPath + "/ptRecMng.do";
	var param = "";

	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm, param);
}

/**
 * 자재출고
 */
function goPtIssList()
{
	var url = contextPath + "/ptIssMng.do";
	var param = "";

	bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
	bottomForm.action = url;
	formSubmit2(bottomForm, param);
}

/**
 * 장기미결 작업
 */
function goHoldWoList(_woStatus)
{
    var url = "";
    var param = "";
    
    if ("BA" == _woStatus)
    {
        url = contextPath + "/woPlanList.do";
        param =       "woPlanCommonDTO.isHold=" + "Y" +
                "&" + "woPlanCommonDTO.woStatus=" + _woStatus;
    }
    else
    {
        url = contextPath + "/woResultList.do";
        param =       "woResultCommonDTO.isHold=" + "Y" + 
                "&" + "woResultCommonDTO.woStatus=" + _woStatus;
    }
    
    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * 전체작업설계
 */
function goTotalPlanList()
{
    var url = contextPath + "/woPlanList.do";
    var param =       "woPlanCommonDTO.planEndDate=" + dateToData(getPlusDay(<%=MwareConfig.getMainWoPlanPeriod()%>)) +
                "&" + "woPlanCommonDTO.woStatus=" + "BA" +
                "&" + "woPlanCommonDTO.isHold=" + "N";
    
    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * 전체작업수행건수
 */
function goTotalWorkList()
{
    var url = contextPath + "/woResultList.do";
    var param =       "woResultCommonDTO.woStatus=" + "CA" +
                "&" + "woResultCommonDTO.isHold=" + "N";

    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * 선급검사
 */
function goInspBodyList()
{
    var url = contextPath + "/bmClsList.do";
    
    var param = "bmClsCommonDTO.checkEndDate=" + getPlusDay(180);
    
    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * 위치정보
 */
function goBmLocMng()
{
    var url = contextPath + "/bmLocMng.do";
    var param = "";
    
    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * HSE Revision Request
 */
function goHseRevReqList()
{
    var url = contextPath + "/hseRevList.do";
    var param =       "hseRevCommonDTO.revUserId=" + loginUser.userID +
                "&" + "hseRevCommonDTO.revUserName=" + loginUser.userName +
                "&" + "hseRevCommonDTO.revStatus=" + "AZ";

    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}
/**
 * Pending Card List
 */
function goPendCardList()
{
    var url = contextPath + "/hseScardPding.do";
    var param =       "hseScardCommonDTO.actionBy=" + loginUser.deptNo +
                "&" + "hseScardCommonDTO.hazardStatus=" + "PRO";

    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * HSE Revision Request
 */
function goOpenMocList()
{
    var url = contextPath + "/bmMocMng.do";
    
    var param = "bmMocMngDTO.status=" + "opened";
    
    bottomForm.strutsAction.value = "<%=BaseAction.BASE_QUICK_LIST%>";
    
    bottomForm.action = url;
    formSubmit2(bottomForm, param);
}

/**
 * go Menu Admin
 */
function goMenu(page)
{
	//Admin 페이지를 갈경우 기존에 mgMenuList 가 고정이었는데 mgMenu 에 권한이 없을경우 admin 페이지 이동이 안되었음.
	//권한이 있는 페이지중에서 첫번째 페이지로 이동 하도록 수정

		goMenuPage('<%=adminPage%>');

	
}

//]]>
</script>
</head>
<body onLoad="loadPage();" onUnLoad="javascript:unLoad(event);">
<html:form action="main">
<c:import charEncoding="UTF-8" url="/main/inspChartParam.jsp" />
<c:import charEncoding="UTF-8" url="/main/partChartParam.jsp" />

</html:form>
<div id="wrapper">
  	<div class="main_contents">
  		<div  class="main_left">
  			<p class="logo"></p>
  			<div class="left_name">
  				<p class="name_welcome"><%=menuLoginUserName%></p>
  				<div class="left_btn"><img onclick="javascript:goLogOut('<%=LoginAction.LOGOUT%>');" src="<c:url value='/common/images/main/btn_logout.gif' />" alt="Logout" style="cursor: pointer;"  />
  				<img onclick="javascript:goMember();" src="<c:url value='/common/images/main/btn_member.gif' />" alt="Member" style="cursor: pointer;" />
<%
	// 관리자 메뉴권한 이 있는 사람만 보여준다.
	String [][] adminMenu = (String [][])session.getAttribute("ADMINMENU");
	if (adminMenu != null && adminMenu.length > 0)
	{
%>     
  				<img onclick="javascript:goMenu('admin');" src="<c:url value='/common/images/main/btn_admin.gif' />" alt="Admin" style="cursor: pointer;" />
<%                                    
	}
%>   
				<img onclick="javascript:javascript:callGoQuickHelpDoc();" src="<c:url value='/common/images/main/btn_manual.gif' />" alt="Manual" style="padding-left:0px; cursor: pointer;" />
  				</div>
  				
  			</div>
 				<!-----------------------결재 list div--------------------->
			<div style="width:300px; margin-top:14px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
                    <td align="left" colspan="5" class="tit_line">
                        &nbsp;<img src="<c:url value='/common/images/main/tit_approval.gif' />"/><!-- Approval -->
                    </td>
				  </tr>
				  <tr>	
				    <td width="110px;" height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.appDoc"/></td><!-- 결재할 문서 -->
				    <td width="30px;" align="right" class="myjob_num"><a href="javascript:goAppPrcList();"><strong><c:out value="${mainForm.appCntDTO.appCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				    <td width="20px;">&nbsp;</td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.reqDoc"/></td><!-- 기안진행 문서 -->
				    <td width="30px;"align="right" class="myjob_num"><a href="javascript:goAppReqList();"><strong><c:out value="${mainForm.appCntDTO.reqCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				  <tr>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.appNotDoc"/></td><!-- 수신된 문서 -->
				    <td align="right" class="myjob_num"><a href="javascript:goAppNotList();"class="txt_orange12b"><strong><c:out value="${mainForm.appCntDTO.notCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				</table>
			</div>
			
			<!-----------------------My Work div--------------------->
			<div style="width:300px; margin-top:14px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
                    <td align="left" colspan="5" class="tit_line">
                        &nbsp;<img src="<c:url value='/common/images/main/tit_mywork.gif' />"/><!-- My Work -->
                    </td>
				  </tr>
				  <tr>
				    <td width="110px;" height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.woPlan"/></td><!-- 작업설계 -->
				    <td width="30px;" align="right" class="myjob_num"><a href="javascript:goWoPlanList();" class="txt_orange12b"><strong><c:out value="${mainForm.myWorkCntDTO.woPlanCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				    <td width="20px;">&nbsp;</td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.woResult"/></td><!-- 작업수행 -->
				    <td width="30px;" align="right" class="myjob_num"><a href="javascript:goWoResultList();" class="txt_orange12b"><strong><c:out value="${mainForm.myWorkCntDTO.woResultCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				  <tr>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.pendCard"/></td><!-- Sop Card(Pending) -->
				    <td align="right" class="myjob_num"><a href="javascript:goPendCardList();" class="txt_orange12b"><strong><c:out value="${mainForm.myWorkCntDTO.pendCardCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				    <td width="20px;">&nbsp;</td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.hseRevReq"/></td><!-- HSE Rev. Req. -->
				    <td align="right" class="myjob_num"><a href="javascript:goHseRevReqList();" class="txt_orange12b"><strong><c:out value="${mainForm.myWorkCntDTO.hseRevReqCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				</table>
			</div>
			
			<!----------------------- Work Notice div--------------------->
			<div style="width:300px; margin-top:14px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
                    <td align="left" colspan="5" class="tit_line">
                        &nbsp;<img src="<c:url value='/common/images/main/tit_worknotice.gif' />"/><!-- Work Notice -->
                    </td>
				  </tr>
				  <tr>
				    <td width="110px;" height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.ptRec"/></td><!-- 자재구매입고 -->
				    <td width="30px;" class="myjob_num"><a href="javascript:goPurRecList();"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.purRecCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				    <td width="20px;">&nbsp;</td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.ptIss"/></td><!-- 자재출고예약 -->
				    <td width="30px;" class="myjob_num"><a href="javascript:goPtIssList();"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.ptIssCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				  <tr>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.holdPlan"/></td><!-- 장기미결W/O설계 -->
				    <td align="right" class="myjob_num"><a href="javascript:goHoldWoList('BA');"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.holdPlanCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
                    <td></td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.holdWo"/></td><!-- 장기미결W/O -->
				    <td align="right" class="myjob_num"><a href="javascript:goHoldWoList('CA');"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.holdPerformCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				  <tr>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.totalPlan"/></td><!-- 전체작업설계 -->
				    <td align="right" class="myjob_num"><a href="javascript:goTotalPlanList();"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.totalPlanCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
                    <td></td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.totalWork"/></td><!-- 전체작업수행 -->
				    <td align="right" class="myjob_num"><a href="javascript:goTotalWorkList();"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.totalWorkCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				  </tr>
				  <tr>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.inspBody"/></td><!-- 선체검사 -->
				    <td align="right" class="myjob_num"><a href="javascript:goInspBodyList();"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.inspBodyCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				    <td></td>
				    <td height="22" align="left" class="txt_gray11" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;"><bean:message key="main.openMoc"/></td><!-- MOC대상 -->
				    <td align="right" class="myjob_num"><a href="javascript:goOpenMocList();"class="txt_orange12b"><strong><c:out value="${mainForm.workNoticeCntDTO.openMocCount}" /></strong></a>&nbsp;<bean:message key="main.count"/></td>
				    
				  </tr>
				</table>
			</div>
  		</div><!-- //main_left -->
  			
  		<div id="menu_contents" >
  			<table id='main_menu' border='0'style="width:225px;height:340px;margin-top:205px;vertical-align:bottom;" >
  				<tr>
  					<td valign="bottom">
  					<div id="menu_cate" class="menu_cate">
  						<ul class="menu_1depth">
<%
// 넘겨진 Menu가 0 보다 큰경우
if (menuList != null && menuList.size() > 0)
{
	// 0 번째가 메인 메뉴이다.
	Hashtable mainHash = (Hashtable)menuList.get(0);
	
	// menu[0][0] : 보여지는 이름, menu[0][1] : Menu Id
	String [][] mainMenu = (String [][])mainHash.get("menu");
	
	String [][] tempSubMenu = null;
	int tempSubMenuCount = 0;
	
	// Main Menu의 갯수
	int mainMenuCount = mainMenu.length;
	
	// Main Menu를 구성한다.
	for (int i=0; i < mainMenuCount; i++)
	{
%>
    						
    						<li>
    							<c-rt:set value='<%=mainMenu[i][2]%>' var='mainMenuId'></c-rt:set>
	    						<a href="#menu_cate<%=i %>" class="tab" onfocus="this.blur();"><bean:message key='MENU.${mainMenuId}' /></a>
	    						  <div id="menu_cate<%=i %>" style="display:none;">
	     						  	<ul class="menu_2depth">
<%
					// 해당 메뉴의 sub Menu를 ArrayList에서 찾아서 셋팅한다.
					//tempSubMenu = CommonUtil.getSubMenu(menuList, mainMenu[i][2]);
					
					// 해당 서브 메뉴의 갯수를 계산한다.
					tempSubMenuCount = tempSubMenu.length;

					// Sub Menu를 구성한다.
				    for (int j=0; j<tempSubMenuCount; j++)
                    {
%>    									
										<c-rt:set value='<%=tempSubMenu[j][2]%>' var='subMenuId'></c-rt:set>
	     								<li>
	     									<table>
												<tr>
													<td><span>&nbsp;</span></td>
													<td><a href="javascript:goMenuPage('<%=tempSubMenu[j][1]%>');" onfocus="this.blur();"><bean:message key='MENU.${subMenuId}' /></a></td>
												</tr>
											</table>
	     								</li>
<%
                    }
%>
	     							</ul> 
	     						</div>	
	     					</li>
<%
	}
}
%>
                          </ul>
  						</div>
  					</td>
  				</tr>
  			</table>	
  		</div>
  		<!-- Right Contents -->
  		<div  class="main_right">
  			<div class="right_contents">
  				<ul class="top_txt">
  					<li>Position : <span onClick="goBmLocMng();" style="cursor: pointer;"><c:out value="${mainForm.vsPosition}" /></span></li>
  				</ul>
	            <!-- Notice -->
				<div id="notice">
   				<ul class="tab_type02">
   					<li><a href="#" onclick="notice_ChangeBox('notice')" onfocus="this.blur();"><img src="<c:url value='/common/images/main/tab04_on.gif' />" alt="Checking List" /></a></li>
   					<li><a href="#" onclick="notice_ChangeBox('board')"><img src="<c:url value='/common/images/main/tab05_off.gif' />" alt="Incomplete W/O" /></a></li>
   				</ul>
   				<p class="more"><a href="javascript:goNotice()" onfocus="this.blur();"><img src="<c:url value='/common/images/main/btn_more.gif' />" alt="more" /></a></p>
   				<table style="height: 85px;">
					<tr>
						<td valign="top">
							<table>
								<c:forEach items="${mainForm.noticeList}" var="list" varStatus="i">
								<tr >
				                	<td height="17" align="left" colspan="2" class="txt_gray12" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;">
				                  		<div style="width: 350px; cursor: pointer;" class="txt_list" onclick="goDetailview('<c:out value="${list.noticeNo}" />', 'NOT');">[<c:out value="${list.enterDate}" />] <c:out value="${list.description}" /></div>
				                	</td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
  				</div> 
				<!-- //Board -->     
				<!-- Board -->
				<div id="board" style="display: none;">
   				<ul class="tab_type02">
   					<li><a href="#" onclick="notice_ChangeBox('notice')" onfocus="this.blur();"><img src="<c:url value='/common/images/main/tab04_off.gif' />" alt="Checking List" /></a></li>
   					<li><a href="#" onclick="notice_ChangeBox('board')"><img src="<c:url value='/common/images/main/tab05_on.gif' />" alt="Incomplete W/O" /></a></li>
   				</ul>
   				<p class="more"><a href="javascript:goBoard()" onfocus="this.blur();"><img src="<c:url value='/common/images/main/btn_more.gif' />" alt="more" /></a></p>		
   				<table style="height: 85px;">
					<tr>
						<td valign="top">
							<table>
								<c:forEach items="${mainForm.boardList}" var="list" varStatus="i">
								<tr >
				                	<td height="17" align="left" colspan="2" class="txt_gray12" style="background:url(<c:url value="/common/images/main/img_bus.gif"/>) left no-repeat; padding-left: 7px;">
				                  		<div style="width: 350px; cursor: pointer;" class="txt_list" onclick="goDetailview('<c:out value="${list.boardNo}" />', 'BOD');">[<c:out value="${list.enterDate}" />] <c:if test="${list.parentNo!='0'}"><img src="<c:url value='/common/images/bar/blet_re2.gif' />"/></c:if><c:out value="${list.description}" /></div>
				                	</td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
  				</div> 
				<!-- //Board -->    
  				<div id="checking">
   				<ul class="tab_type01">
   					<li><a href="#" onclick="list_ChangeBox('checking')" onfocus="this.blur();"><img src="<c:url value='/common/images/main/tab01_on.gif' />" alt="Checking List" /></a></li>
   					<li><a href="#" onclick="list_ChangeBox('incomplete')"><img src="<c:url value='/common/images/main/tab02_off.gif' />" alt="Incomplete W/O" /></a></li>   					
   				</ul>   				
   				<table border="0" cellpadding="0" cellspacing="0">
		        	<tr>
		        		<td>
		        		<div id="inspChart" style="width: 444px; height: 150px; margin: 0 auto"></div>
		        		</td>
	        		</tr>
	        	</table>
  				</div>  									
  					<div id="incomplete" style="display:none;">
   					<ul class="tab_type01">
   						<li><a href="#" onclick="list_ChangeBox('checking')" onfocus="this.blur();"><img src="<c:url value='/common/images/main/tab01_off.gif' />" alt="Checking List" /></a></li>
   						<li><a href="#" onclick="list_ChangeBox('incomplete')"><img src="<c:url value='/common/images/main/tab02_on.gif' />" alt="Incomplete W/O" /></a></li>   					
					</ul>					
					<table border="0" cellpadding="0" cellspacing="0">
		        	<tr>
		        		<td>
		        		<div id="partChart" style="width: 444px; height: 150px; margin: 0 auto"></div>
		        		</td>
	        		</tr>
	        	</table>
  					</div>
  				</div>
  		</div><!-- //Right Contents -->   	
     </div><!-- //main_contents -->
</div>
<div id="footer">
	<div class="foot_area">
        <img src="<c:url value='/common/images/main/bg_foot.jpg' />" width="1026" border="0" usemap="#Map">
		<map name="Map">
		  <area shape="rect" coords="761,58,905,76" href="mailto:silver@emaintec.com">
		</map> 
    </div>
</div>
<!-- ########## page 하단 공통 : 모든 페이지 적용 ########## --> 
<c:import charEncoding="UTF-8"  url="/common/jsp/bottomInclude.jsp"></c:import>
</body>
</html>