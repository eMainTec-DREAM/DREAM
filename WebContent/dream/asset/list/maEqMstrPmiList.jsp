<%--===========================================================================
점검 목록 (INS)
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPmListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 점검 -->
<title><bean:message key='TAB.maEqMstrPmiList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

function setDisable(){}

function afterDisable()
{
	$('.b_pmlist').show();
	$('.b_excel').show();
	$('.b_setting').show();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
	
	//점검-정기점검(INS) 
	maEqMstrPmListForm.elements['maEqMstrPmListDTO.woTypeId'].value = "PMI"; 
	maEqMstrPmListForm.elements['maEqMstrPmListDTO.woTypeDesc'].value = "PMI"; 
	//maEqMstrPmListForm.elements['maEqMstrPmListDTO.exceptedPmTypeId'].value = "INS"; 
	
	valSysDir('maEqMstrPmListDTO.woTypeId', 'maEqMstrPmListDTO.woTypeDesc', 'WO_TYPE', true);
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maEqMstrPmListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	var form = document.maEqMstrPmListForm;	
	form.strutsAction.value = '<%=MaEqMstrPmListAction.EQ_MSTR_RINS_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrPmiList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPmListForm), "PMPOINTID", "Y");
}
/**
 * 예방점검
 */
 function goInslist(){
	var selectedId=myGrid.getSelectedRowId();

	var url   = contextPath + "/maPmMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "isDecoratorName=popupPage"+
	"&maPmMstrCommonDTO.strutsAction="+
	"&maPmMstrCommonDTO.equipId="+maEqMstrPmListForm.elements['maEqMstrCommonDTO.equipId'].value+
	"&maPmMstrCommonDTO.equipDesc="+maEqMstrPmListForm.elements['maEqMstrCommonDTO.equipDesc'].value+
	"&maPmMstrCommonDTO.woType="+maEqMstrPmListForm.elements['maEqMstrPmListDTO.woTypeId'].value+
	"&maPmMstrCommonDTO.woTypeDesc="+maEqMstrPmListForm.elements['maEqMstrPmListDTO.woTypeDesc'].value;
    
	if(selectedId != null){
		var pmId = getValueById(myGrid, selectedId, "PMID");
		var pmNo = getValueById(myGrid, selectedId, "PMNO");
		param += "&maPmMstrCommonDTO.pmNo="+pmNo; 
	}
	//post 전송
	openWindowWithPost(url, "PM_LIST", param, pos);
		
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//    	excelAction(myGrid);
   	excelServerAction("maEqMstrPmiList", maEqMstrPmListForm);

  }
 
  
  /**
   * 예방점검표 출력
   */
   function goPmchecklist()
   {
	  var selectedId = myGrid.getSelectedRowId();
	  if(selectedId == null) 
	  {
		  alertMessage1("<bean:message key='MESSAGE.MSG0074'/>");	  
    	  return;	  
      }
      
	  var pminslistId = getValueById(myGrid, selectedId, "PMINSLISTID");
	  if("" == pminslistId)
	  {
		  alertMessage1("<bean:message key='MESSAGE.CMSG013'/>");	  
		  return;
	  }
	  
  	 reportCall('workPmiDetail','workPmiDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>", pminslistId); 
   }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrPmiList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.equipDesc"/>
<html:hidden property="maEqMstrPmListDTO.pmPointId"/>
<html:hidden property="maEqMstrPmListDTO.woTypeId"/>
<html:hidden property="maEqMstrPmListDTO.woTypeDesc"/>
<html:hidden property="maEqMstrPmListDTO.pmTypeId"/>
<html:hidden property="maEqMstrPmListDTO.pmTypeDesc"/>
<html:hidden property="maEqMstrPmListDTO.exceptedPmTypeId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>