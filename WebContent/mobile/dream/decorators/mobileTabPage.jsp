<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileCommonInclude.jsp"></c:import>
<link rel="stylesheet" href="<c:url value="/mobile/dream/css/style.css" />" type="text/css" />

<title><decorator:title/></title>
<decorator:head />
<script type="text/JavaScript">
//<!--

function loadDefault()
{
	//현재 선택된 subMenu 의 style을 변경하기 위해 사용한다.	
	//document.getElementById("TD_SUB_MENU_"+'${currentPageId}').className = "allmenu_list_selected";
	/* $(".form_box").keydown(function(e){
    	if(e.keyCode == 13 && typeof goSearch == "function"){
    		if(!(myPop && myPop.isVisible())) goSearch();	
    	}
    });
	$('input,select').keypress(function(event) { return event.keyCode != 13; });  */
}

//-->
</script>
</head>
<!-- <body class="top_body" onload="loadDefault();"  style="margin-top: 0px; margin-left: 0px; margin-right: 0px; margin-bottom: 0px;  background-color: #eeeeee;  overflow:auto;">
  -->  
<body class="top_body" onload="loadDefault();"  style="margin-top: 0px; margin-left: 0px; margin-right: 0px; margin-bottom: 0px;  background-color: #eeeeee;  overflow:auto;">
 	<div class="nav_wrap">
		<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileMenuInclude.jsp"></c:import> 
	</div>  
    <div id="wrap">
    
       	<div class="top_wrap">
	    	<div class="title_box">
	        	<div class="btn_back"><a><span>이전페이지</span></a></div>
	            <div class="page_title"><decorator:title/></div>
	        </div>
	        <div class="btn_mopen"><a><span>메뉴</span></a></div>
	    </div>
        
        <div class="main_wrap">
	    	
	    	<decorator:body />   
			<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileTabPageInclude.jsp"></c:import>
			<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileButtonInclude.jsp" ></c:import>
		</div>
   	</div> <!-- end of wrap -->
    <!-- //CONTAINER -->
<!-- page 하단 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/bottomInclude.jsp"></c:import>
<script language="javascript">
// 비활성화 되어 있다면 활성화 시킨다.
window.focus();
// body 의 onload 에 넣었다가 IE 8 에서 body 가 모두 로딩되기 전에 호출이 되어서 제일 뒤에 loadpage를 호출하게 수정하였음

jQuery(function($){
	loadPage();
	
	$('.btn_back').on("click",function(e){
		window.history.back();
	}).css("cursor","pointer");
});		
</script>
</body>
</html>