<%--===========================================================================
검교정상세 측정값 일괄등록
author  kim21017
version $Id: workListGnlCavalPopup.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListGnlCavalListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key='LABEL.regBatchCalibVal'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,<bean:message key='LABEL.ISDELCHECK'/>,<bean:message key='LABEL.calibPointTypeCateg'/>,<bean:message key='LABEL.calibPoint'/>,<bean:message key='LABEL.allowValue'/>,<bean:message key='LABEL.asFound'/>,#cspan,#cspan,<bean:message key='LABEL.asLeft'/>,#cspan,#cspan,ID");
	myGrid.attachHeader(["#rspan","#rspan","#rspan","#rspan","#rspan","<bean:message key='LABEL.asfStdValue'/>","<bean:message key='LABEL.asfCalValue'/>","<bean:message key='LABEL.asfDiffValue'/>","<bean:message key='LABEL.aslStdValue'/>","<bean:message key='LABEL.aslCalValue'/>","<bean:message key='LABEL.aslDiffValue'/>","#rspan"]);
	myGrid.setColumnIds("SEQNO,ISDELCHECK,CLIBPOINTTYPEDESC,CALIBPOINT,ALLOWVALUE,ASFSTDVALUE,ASFCALVALUE,ASFDIFFVALUE,ASLSTDVALUE,ASLCALVALUE,ASLDIFFVALUE,WOCALIBGNLVALUEID");
	myGrid.setInitWidths("60,60,120,80,80,80,80,80,80,80,80,80");
	myGrid.setColAlign("center,center,center,center,center,center,center,center,center,center,center,center");
	myGrid.setColTypes("cntr,ch,ro,edn,edn,edn,edn,edn,edn,edn,edn,ro");
// 	myGrid.setNumberFormat("0,000.00",6,".",",");
// 	myGrid.setNumberFormat("0,000.00",9,".",",");
	
	myGrid.attachEvent("onEditCell",function(stage,id,index,new_value,old_value){
		if(index == 1) return true;
		if(index == 3) return true;
		if(index == 4) return true;
		if(index == 7) return true;
		if(index == 10) return true;
		if(index == 5){//AF 표준값 변경
			if(new_value=='') myGrid.cells(id, 7).setValue(''); //값이 없으면 편차 지우기.
			else{//값이 있다면 편차 구하기.
				var asfStdValue   = parseFloat(new_value);
				var asfCalValue   = parseFloat(myGrid.cells(id, 6).getValue());
				
				myGrid.cells(id, 7).setValue(getDiffValue(asfCalValue,asfStdValue));
			}
			
			return true;
		}
		if(index == 6){//AF 측정값 변경
			if(new_value=='') myGrid.cells(id, 7).setValue('');//값이 없으면 편차 지우기.
			else{//값이 있다면 편차 구하기.
				var asfStdValue   = parseFloat(myGrid.cells(id, 5).getValue());
				var asfCalValue   = parseFloat(new_value);
				myGrid.cells(id, 7).setValue(getDiffValue(asfCalValue,asfStdValue));
			}
			
			return true;
		}
		if(index == 8){//AL 표준값 변경
			if(new_value=='') myGrid.cells(id, 10).setValue('');
			else{//값이 있다면 편차 구하기.
				var aslStdValue   = parseFloat(new_value);
				var aslCalValue   = parseFloat(myGrid.cells(id, 9).getValue());
				
				myGrid.cells(id, 10).setValue(getDiffValue(aslCalValue,aslStdValue));
			}
			
			return true;
		}
		if(index == 9){//AL 측정값 변경
			if(new_value=='') myGrid.cells(id, 10).setValue('');//값이 없으면 편차 지우기.
			else{//값이 있다면 편차 구하기.
				var aslStdValue = parseFloat(myGrid.cells(id, 8).getValue());
				var aslCalValue   = parseFloat(new_value);
				myGrid.cells(id, 10).setValue(getDiffValue(aslCalValue,aslStdValue));
			}
			
			return true;
		}
		
	});

	myGrid.enableSmartRendering(true,500);
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(11,true);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(myGrid,"gridbox", true)}); myGrid.init();

	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
	
	goSearch();
}
/**
 * 편차 구하기. 둘 중 소수점 큰 것에 맞춰서 편차구함.
 */
function getDiffValue (val1, val2){
	var dpLength1 = 0;
	var dpLength2 = 0;
	
	if((val1+'').indexOf('.')>0){
		dpLength1 = (val1+'').length - (val1+'').indexOf('.')-1;
	}else{
		dpLength1 = 0;
	}
	
	if((val2+'').indexOf('.')>0){
		dpLength2 = (val2+'').length - (val2+'').indexOf('.')-1;
	}else{
		dpLength2 = 0;
	}
	var fixedLength = Math.max(dpLength1,dpLength2);
	
	var resultVal = (val1 - val2).toFixed(fixedLength);
	if(resultVal=='NaN') resultVal = '';
	
	return resultVal;
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
	if (workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.workListGnlCavalListForm;	
	form.strutsAction.value = '<%=WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_FIND %>';
	
	var url = contextPath + "/workListGnlCavalPopup.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListGnlCavalListForm), "WOCALIBGNLVALUEID");

}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  }

  function afterRegbatchcalibval()
  {
	  alertMessage1('<bean:message key="MESSAGE.CMSG057"/>');
	  getIframeContent().goSearch();
  }
  
  function goRegbatchcalibval(){
	myGrid.setCheckedRows(1,1);
	myGrid.selectRow(0);
	var allArray = getSelectedAllCol(myGrid,'ISDELCHECK');
	if(typeof allArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	workListGnlCavalListForm.strutsAction.value = '<%= WorkListGnlCavalListAction.WORK_LIST_GNL_BATCH_SAVE %>';
	var url = contextPath + "/workListGnlCavalPopup.do";
	$.post(url,FormQueryString(workListGnlCavalListForm)+"&allArray="+allArray , function(_data){
		afterRegbatchcalibval();
		});
	}
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListGnlCavalList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.pmCalibStdTpId"/>
<html:hidden property="maWoResultMstrCommonDTO.wocalibgnlvalueId"/>
    <!-- searchbox 박스 Line -->

    <div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->

</html:form> 
</body>
</html>