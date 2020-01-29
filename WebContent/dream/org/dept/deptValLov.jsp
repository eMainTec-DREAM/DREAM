<%--===========================================================================
부서 Popup
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.org.dept.action.LovDeptListAction"%>
<html>
<head>
<!-- 부서 -->
<title><bean:message key="LABEL.dept"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

var plantAc;

var conditionFilterMap = {
	"plant":"lovDeptListDTO.plantId"
	,"plantDesc":"lovDeptListDTO.plantDesc"
	,"isMaint":"lovDeptListDTO.isMaint"
	,"isMaintDesc":"lovDeptListDTO.isMaintDesc"
};

function loadPage() 
{

	convertCondition();
	
	initGrid();
	
	// 공장코드
	plantAc = new autoC({"lovDeptListDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovDeptListDTO.plantId":"plant"
    });
    plantAc.init();
	
	/**보전부서여부 */
    acSysDesc("lovDeptListDTO.isMaintDesc","lovDeptListDTO.isMaint","IS_USE");
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.attachEvent("onRowSelected",function(rowId, columnId){
		console.log(myGrid.getSeledtedRowId())
	});
	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}




function openNode(_grid, _id)
{
// 	if(_grid.getParentId(_id) != "0") openNode(_grid, _grid.getParentId(_id));
	_grid.openItem(_id);
}

function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovDeptListForm;	
	form.strutsAction.value = '<%=LovDeptListAction.LOV_DEPT_AC_FIND%>';
	var url = contextPath + "/deptValLov.do";
	
// 	doGridAction('SearchTree', myGrid, "gridbox", url, FormQueryString(form), "dept_id", "", "p_dept_id");

// 	myGrid.expandAll(); //펼치기
//  	setTimeout("myGrid.collapseAll();//접기", 500);
	
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.clearAll(); setLoading("gridbox");
    	myGrid.parse(_data,"js");
    	setCounter(myGrid,"gridbox", "goSearch"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
    	
    	var code = lovDeptListForm.code.value;
    	if(code!=""){ // 입력되어 있는 값이 있으면
    		myGrid.expandAll();
    		var rowId = getRowIdByValue(myGrid,"DESCRIPTION",code);
//     		myGrid.collapseAll();
    		myGrid.selectRowById(rowId);
//         	openNode(myGrid, rowId);
    	}else if(code ==""){
    		myGrid.expandAll();
    		var deptId = loginUser.deptId;
        	myGrid.selectRowById(deptId);
//         	openNode(myGrid, deptId);
    	}
    });
}

function afterSearch(_grid){

	var code = lovDeptListForm.code.value;
	
	if(code!=""){ // 입력되어 있는 값이 있으면
		_grid.expandAll();
		var rowId = getRowIdByValue(_grid,"DESCRIPTION",code);
		_grid.collapseAll();
		_grid.selectRowById(rowId);
    	openNode(_grid, rowId);
	}else if(code ==""){
		var deptId = loginUser.deptId;
		_grid.selectRowById(deptId);
    	openNode(_grid, deptId);
	}
}
function goSelect()
{
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "dept_id");
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/deptValLov" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />
<html:hidden property="code" />


		<html:hidden property="lovDeptListDTO.extCode1" />
		<html:hidden property="lovDeptListDTO.extCode2" />
		<html:hidden property="lovDeptListDTO.codeType" />
		<html:hidden property="lovDeptListDTO.isMaint" />
		<html:hidden property="lovDeptListDTO.plantId"/>
		<html:hidden property="strutsAction" />
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap"><a></a></div>
				<div class="stitle_wrap">
					<div class="stitle_icon"><a></a></div>
					<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
				</div>
				<div class="function_box filter">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display:none;">
						</div>
					</div>
					<div class="fb_group2">
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- 부서코드 -->
						<label><bean:message key="LABEL.deptNo"/></label>
						<div class="input_box">
							<html:text property="lovDeptListDTO.deptNo" />
						</div>
					</div>
					<div class="field">
						<!-- 부서명 -->
						<label><bean:message key="LABEL.deptDesc"/></label>
						<div class="input_box">
							<html:text property="lovDeptListDTO.deptDesc" />
						</div>
					</div>
					<div class="field">
						 <!-- 보전부서여부 -->
	                     <label><bean:message key="LABEL.maintDept"/></label>
	                     <div class="input_box">
	                        <html:text property="lovDeptListDTO.isMaintDesc" />
	                         <p class="open_spop"><a><span>조회</span></a></p>
	                  	 </div>
	                </div> 
	                <!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
							<html:text property="lovDeptListDTO.plantDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
				</div>
			</div><!--article_box end-->
		</div> <!--  end section_wrap -->
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