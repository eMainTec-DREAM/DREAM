<%--===========================================================================
설비제원(스펙)
author  kim21017
version $Id: maEqMstrInsList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPmListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검 -->
<title><bean:message key='TAB.maEqMstrInsList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

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
	
	//점검-정기점검 
	maEqMstrPmListForm.elements['maEqMstrPmListDTO.woTypeId'].value = "PMI"; 
	maEqMstrPmListForm.elements['maEqMstrPmListDTO.woTypeDesc'].value = "PMI"; 
	maEqMstrPmListForm.elements['maEqMstrPmListDTO.exceptedPmTypeId'].value = "EINS"; 
	
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
	
	var url = contextPath + "/maEqMstrInsList.do";
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
	//maEqMstrPmListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
   	excelServerAction("maEqMstrInsList", maEqMstrPmListForm);
  }
 
 
  function setDisable(){}
  

  /*
   * 엑셀 업로드
   */
  function goExluploadLink()
  {
    maEqMstrPmListForm.elements['maEqMstrCommonDTO.exceltabNo'].value = "PMINS";
  	var actionUrl = contextPath + "/maEqMstrList.do";
    var param =  "&strutsAction=" + '<%= MaEqMstrListAction.GET_DATA %>'
                +  "&" + FormQueryString(maEqMstrPmListForm);
    
    
    XMLHttpPostVal(actionUrl, param, 'afterGoExlupload');
  }


  var dataArr;
  function afterGoExlupload(ajaxXmlDoc)
  {
  	dataArr = '0';
  	var data = parseXmlDoc(ajaxXmlDoc, 'DESC');
  	var uploadTypeId = "";
  	var uploadType = "";
  	var tableName = "";
  	
  	data = data.toString();

  	if(data != '0')
      {
  		dataArr = data.split(',');
  		
  		uploadTypeId = dataArr[0];
  		uploadType = dataArr[1];
  		tableName = dataArr[2];
  		
      }
  		goExlupload(uploadTypeId, uploadType, tableName);
  }

  /**
   *  Audit trail
   */
   function goAudtrailLink()
   {
      var objectId = maEqMstrPmListForm.elements['maEqMstrPmListDTO.pmPointId'].value;
      var fileName = currentPageId;

      if(typeof objectId=="undefined") return;

      goAudTrailList(objectId, fileName);
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrInsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.equipDesc"/>
<html:hidden property="maEqMstrCommonDTO.exceltabNo"/>
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