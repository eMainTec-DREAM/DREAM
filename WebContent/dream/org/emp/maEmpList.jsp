<%--===========================================================================
보전사원 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.emp.action.MaEmpListAction" %>
<%@ page import="dream.org.emp.action.MaEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 보전사원 -->
<title><bean:message key='MENU.EMP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 <style>
		div#layoutObj {
			position: relative;
			margin-top: 10px;
			margin-left: 10px;
			width: 100%;
			height: 400px;
		}
		/* attached */
		h3.some_header {
			padding-left: 10px;
			padding-right: 10px;
			font-family: Roboto, Arial, Helvetica;
			color: #404040;
		}
		div.some_text {
			padding-left: 10px;
			padding-right: 10px;
			font-size: 14px;
			font-family: Roboto, Arial, Helvetica;
			color: #404040;
		}
	</style>
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var wkCtrDescAc;
var empDescAc;
var isUseAc;
var plantAc;
var myLayout;
function loadPage() 
{
	 /* myLayout = new dhtmlXLayoutObject({
		parent: "layoutObj",
		pattern: "1C"
	});

	myLayout.cells("a").hideHeader();
	myLayout.cells("a").attachURL("eduRptEmpList.do", true, {"maEmpCommonDTO.empId":"43","isDecoratorName":"tabPage",sex:"Male"}); 
	  */
     initGrid();
    
    // 근무여부 - 기본 Y 선택.
    maEmpListForm.elements['maEmpCommonDTO.filterIsJoin'].value = 'Y';
    maEmpListForm.elements['maEmpCommonDTO.filterIsJoinDesc'].value = 'Y';

/*  if(loginUser.wkctrId!='null'){
    	maEmpListForm.elements['maEmpCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
    	maEmpListForm.elements['maEmpCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	}	*/
    if(loginUser.filterWkCtrId!='null'){
    	maEmpListForm.elements['maEmpCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
    	maEmpListForm.elements['maEmpCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	maEmpListForm.elements['maEmpCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maEmpListForm.elements['maEmpCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
    /*부서 */
    deptAc = new autoC({"maEmpCommonDTO.filterDeptDesc":"description"});
	deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
	deptAc.setAcDConditionMap({
    	"plant":"maEmpCommonDTO.filterPlantId"
    });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEmpCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    /* 작업그룹 */
    wkCtrDescAc = new autoC({"maEmpCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maEmpCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    empDescAc = new autoC({"maEmpCommonDTO.filterEmpName":"emp_name"});
    empDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empDescAc.setTable("TAEMP");
    empDescAc.setAcResultMap({
        "maEmpCommonDTO.filterEmpName":"emp_name"
    });
    empDescAc.setAcDConditionMap({
    	"dept_id" : "maEmpCommonDTO.filterDeptId",
    	"plant" : "maEmpCommonDTO.filterPlantId"
    });
    empDescAc.init();

    // 공장명
    plantAc = new autoC({"maEmpCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEmpCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("maEmpCommonDTO.filterIsJoinDesc","maEmpCommonDTO.filterIsJoin","IS_USE"); 
    acSysDesc("maEmpCommonDTO.filterIsDirectDesc","maEmpCommonDTO.filterIsDirectDesc","IS_USE");
}

/* function setLovValue(returnArray, dirType)
{
	for(var i=0, len = returnArray[0].length; i<len;i++ )
	{
		//alert(returnArray[0][i]);
	}
} */

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maEmpListForm.elements['maEmpCommonDTO.empId'].value = "";
    	return sortColumn("maEmpList", this, maEmpListForm, "empId", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEmpList.do";
    maEmpListForm.elements['strutsAction'].value = '<%=MaEmpListAction.EMP_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEmpListForm), "empId", "Y");
}

function findGridRow(_empId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	maEmpListForm.elements['maEmpCommonDTO.empId'].value = _empId;
	findGridList('ReloadRow');
	maEmpListForm.elements['maEmpCommonDTO.empId'].value = "";
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maEmpListForm.elements['maEmpCommonDTO.empId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaEmpListAction.EMP_LIST_FIND%>');   
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
   	maEmpListForm.elements['maEmpCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	maEmpListForm.elements['maEmpCommonDTO.empId'].value  = getValueById(myGrid, selectedId, 'empId');
	
	goCommonTabPage(maEmpListForm, <%= MaEmpDetailAction.EMP_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maEmpDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maEmpListForm.elements['maEmpCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	maEmpListForm.elements['maEmpCommonDTO.empId'].value  = getValueById(myGrid, selectedId, 'empId');
    maEmpListForm.elements['strutsAction'].value = '<%=MaEmpDetailAction.EMP_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maEmpListForm), 'maEmpDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maEmpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maEmpListForm.elements['maEmpCommonDTO.empId'].value = "";
    goCommonTabPage(maEmpListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'empId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maEmpListForm.strutsAction.value = '<%=MaEmpListAction.EMP_LIST_DELETE%>';
    var url = contextPath + "/maEmpList.do";
    
    $.post(url,FormQueryString(maEmpListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('maEmpDetail', this);
    // goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
 function goExcel()
 {
	maEmpListForm.elements['maEmpCommonDTO.empId'].value = "";
 	excelServerAction("maEmpList", maEmpListForm );  
 }
 
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maEmpListForm.elements['maEmpCommonDTO.empId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<!-- <div id="layoutObj"></div> -->
<html:form action="/maEmpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEmpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maEmpCommonDTO.empId"/><!-- Key -->
<html:hidden property="maEmpCommonDTO.filterDeptId"/>
<html:hidden property="maEmpCommonDTO.filterWkCtrId"/>
<html:hidden property="maEmpCommonDTO.filterIsJoin"/>
<html:hidden property="maEmpCommonDTO.filterPlantId"/>
    <!-- searchbox 박스 Line -->
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
        <div class="article_box" >
            <div class="form_box">
            <!--사원명  -->
	               <div class="field">
		               <label><bean:message key="LABEL.empName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maEmpCommonDTO.filterEmpName" tabindex="10"/>
	               	   		<p class="open_spop"><a><span>조회</span></a></p>
	               	   </div>
               	   </div>
               	<div class="field">
               	<!--사원번호  -->
		               <label><bean:message key="LABEL.empId"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maEmpCommonDTO.filterEmpNo" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
	               <!--부서  -->
		               <label><bean:message key="LABEL.dept"/></label>
	               	   <div class="input_box"">
	                        <html:text property="maEmpCommonDTO.filterDeptDesc" tabindex="30"/>
	                        <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
               	   </div>
                   <div class="field">
                   <!-- 근무여부 -->
                   <label><bean:message key="LABEL.isJoin"/></label>
                        <div class="input_box">
                           <html:text property="maEmpCommonDTO.filterIsJoinDesc" tabindex="30"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>     
                   <div class="field">
                   <!--작업그룹 -->
                        <label><bean:message key="LABEL.wkCtr"/></label>
                        <div class="input_box">
                           <html:text property="maEmpCommonDTO.filterWkCtrDesc" tabindex="40"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>  
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maEmpCommonDTO.filterPlantDesc" tabindex="50" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>     
                <!-- 직영여부  -->
                <div class="field">
                    <label><bean:message key="LABEL.isDirect"/></label>
                    <div class="input_box">
						<html:text property="maEmpCommonDTO.filterIsDirectDesc" tabindex="60" />
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