<%--===========================================================================
사용자 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.mgr.user.action.MgrUserPlantauthListAction" %>
<%@ page import="dream.mgr.user.action.MgrUserPlantauthDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<html>
<head>
<!-- 사용자 -->
<title><bean:message key='MENU.USER'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var plantAc;

function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("ADD");
// 	hideBtn("DEL");
	hideBtn("EDITCNCL");
	
	plantAc = new autoC({"mgrUserPlantauthDetailDTO.multiDesc":"description"});
	plantAc.setTable("TAUSRPLANTAUTH");
	plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
	plantAc.setAcResultMap({
        "mgrUserPlantauthDetailDTO.multiKey":"plant"
    });
	plantAc.setMultiSelect(true);
	plantAc.init();
	
	setForUpdate();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = "";	// 검색시 Tab 이동Key Clear
   		return sortColumn("mgrUserPlantauthList", this, mgrUserPlantauthListForm, "usrPlantauthId", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUserPlantauthList.do";
    mgrUserPlantauthListForm.elements['strutsAction'].value = '<%=MgrUserPlantauthListAction.USERPLANT_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUserPlantauthListForm), "usrPlantauthId", "Y");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
    tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
   	mgrUserPlantauthListForm.elements['maUserCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = getValueById(myGrid, selectedId, 'usrPlantauthId');

    goCommonTabPage(mgrUserPlantauthListForm, <%=MgrUserPlantauthDetailAction.USERPLANT_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_userId)
{
	mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = _userId;
	findGridList('ReloadRow');
	mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrUserPlantauthDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrUserPlantauthListForm.elements['maUserCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = getValueById(myGrid, selectedId, 'usrPlantauthId');
    mgrUserPlantauthListForm.elements['strutsAction'].value = '<%=MgrUserPlantauthDetailAction.USERPLANT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrUserPlantauthListForm), 'mgrUserPlantauthDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrUserPlantauthDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = "";
    goCommonTabPage(mgrUserPlantauthListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = "";
	excelServerAction('mgrUserPlantauthList', mgrUserPlantauthListForm);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'usrPlantauthId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUserPlantauthListForm.strutsAction.value = '<%=MgrUserPlantauthListAction.USERPLANT_LIST_DELETE%>';
    var url = contextPath + "/mgrUserPlantauthList.do";
    
    $.post(url,FormQueryString(mgrUserPlantauthListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrUserPlantauthDetail',this);
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


/**
 * Make Editable Mode
 */
function goEdit()
{
// 	hideBtn("COPY");
	hideBtn("EXCEL");
	hideBtn("OPEN");
	hideBtn("REGBATCH");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("mgrUserPlantauthDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/mgrUserPlantauthList.do";
	var stAct = "<%=MgrUserPlantauthListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave","{}");
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"ISAUTH","acedp"); //AC,EDIT,POPUP
}

function goSave()
{
	var actionUrl = contextPath + "/mgrUserPlantauthList.do";
	
	if(typeof proGrid != "undefined") proGrid.sendData();
	else XMLHttpPost(actionUrl, FormQueryString(mgrUserPlantauthListForm), 'afterAllSave');
	
}

function afterAllSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================

	getTopPage().afterSaveAll(currentPageId);
	
	goSearch();
 }
/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}

/**
 * After Edit
 */
function afterSave()
{
	afterEditRow(myGrid);
	
	//Control Button
	hideBtn("SAVE");
	hideBtn("ADD");
// 	hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("OPEN");
	showBtn("REGBATCH");
	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value = "";
	
	//Search
	goSearch();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	//Make Page as Normal
	setForNormal();
}

/**
 * AC Setting, This is called when the column is changed to edit mode.
 */
function setGridAc(_gridObj, _cellObj)
{
    acSysDesc("ISAUTH","ISAUTHID","IS_USE",true,_gridObj,_cellObj);
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	plantAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if("ISAUTH" != code)
	{
		mgrUserPlantauthListForm.strutsAction.value = '<%=MgrUserPlantauthListAction.LIST_INSERT%>';
		mgrUserPlantauthListForm.elements['mgrUserPlantauthDetailDTO.isAuth'].value = "Y";

		goSaveAll();
	}
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrUserPlantauthListForm.elements['maUserCommonDTO.usrPlantauthId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUserPlantauthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maUserCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maUserCommonDTO.userId"/><!-- Key -->
<html:hidden property="maUserCommonDTO.usrPlantauthId"/><!-- Key -->
<html:hidden property="maUserCommonDTO.filterUsrGrpId"/>
<html:hidden property="maUserCommonDTO.filterDeptId"/>
<html:hidden property="maUserCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maUserCommonDTO.filterIsUse"/>
<html:hidden property="maUserCommonDTO.filterPlantId"/>
<html:hidden property="mgrUserPlantauthDetailDTO.multiKey" />
<html:hidden property="mgrUserPlantauthDetailDTO.multiDesc" />
<html:hidden property="mgrUserPlantauthDetailDTO.isAuth" />
		
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