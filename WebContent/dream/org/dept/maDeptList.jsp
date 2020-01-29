<%--===========================================================================
보전부서 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.dept.action.MaDeptListAction" %>
<%@ page import="dream.org.dept.action.MaDeptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부서 -->
<title><bean:message key='MENU.DEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var selectedInd;

/** 자동완성 변수 */
var deptAc;
var deptDescAc;
var isUseAc;
var plantAc;
var conOpenAc;
function loadPage() 
{
    initGrid();
    
 	// 사용여부 - 기본 선택.
    maDeptListForm.elements['maDeptCommonDTO.filterIsUse'].value = "Y";
    maDeptListForm.elements['maDeptCommonDTO.filterIsUseDesc'].value = "Y";

	//공장명
    if(loginUser.filterPlant!='null'){
    	maDeptListForm.elements['maDeptCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maDeptListForm.elements['maDeptCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    /** 부서명 */
    deptDescAc = new autoC({"maDeptCommonDTO.filterDescription":"description"});
    deptDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptDescAc.setAcDConditionMap({
    	"plant":"maDeptCommonDTO.filterPlantId"
    });
    deptDescAc.setTable("TADEPT");
    deptDescAc.setAcResultMap({
        "maDeptCommonDTO.filterDescription":"description"
    });  
    deptDescAc.init();
    
    /**상위부서 */
    deptAc = new autoC({"maDeptCommonDTO.filterPdeptDesc":"description"});
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    deptAc.setAcDConditionMap({
    	"plant":"maDeptCommonDTO.filterPlantId"
    });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maDeptCommonDTO.filterPdeptId":"dept_id"
    });
    deptAc.init();

    // 공장명
    plantAc = new autoC({"maDeptCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maDeptCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
    /**사용여부  */
    acSysDesc("maDeptCommonDTO.filterIsUseDesc","maDeptCommonDTO.filterIsUse","IS_USE");
    
    acSysDesc("maDeptCommonDTO.filterIsMaintDesc","maDeptCommonDTO.filterIsMaint","IS_USE");

}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
        selectedId = rowId;
		selectedInd = columnId;
    });
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 

    myGrid.attachEvent("onRowDblClicked", function(id,ind){
    	goEqList(id,ind);
    });
    myGrid.init();
    
    
    setHeader(myGrid, "gridbox"); // grid, grid id
}

function goEqList(id,ind){
	if(myGrid.getColumnId(ind)=="EQCNT"){
		var deptId = getValueById(myGrid, id, "DEPTID");
		var deptDesc = getValueById(myGrid, id, "DESCRIPTION");
		var url   = contextPath + "/maEqMstrList.do";
		var popWidth = 1010;
		var popHeight = 640;
	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
		var param = "isDecoratorName=popupPage"+
					"&maEqMstrCommonDTO.strutsAction="+
					"&maEqMstrCommonDTO.filterDeptId="+deptId+
					"&maEqMstrCommonDTO.filterDeptDesc="+deptDesc;
		//post 전송
		openWindowWithPost(url, "DEPTLIST_EQ_POPUP", param, pos);
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maDeptList.do";
    maDeptListForm.elements['strutsAction'].value = '<%=MaDeptListAction.DEPT_LIST_FIND%>';
    setModal();
    $.post(url,FormQueryString(maDeptListForm), function(_data){
	    myGrid.clearAll(); setLoading("gridbox");
        myGrid.parse(_data,"js");
        myGrid.expandAll(); //펼치기
        setCounter(myGrid,"gridbox"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
        closeModal();
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maDeptListForm.elements['maDeptCommonDTO.deptId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaDeptListAction.DEPT_LIST_FIND%>');   
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
   	maDeptListForm.elements['maDeptCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'COMPNO');
   	maDeptListForm.elements['maDeptCommonDTO.deptId'].value = getValueById(myGrid, selectedId, 'DEPTID');
   	maDeptListForm.elements['maDeptCommonDTO.detailPdeptId'].value = getValueById(myGrid, selectedId, 'DEPTID');
   	maDeptListForm.elements['maDeptCommonDTO.detailPdeptDesc'].value = getValueById(myGrid, selectedId, 'DESCRIPTION');

	goCommonTabPage(maDeptListForm, <%= MaDeptDetailAction.DEPT_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maDeptDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maDeptListForm.elements['maDeptCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'COMPNO');
   	maDeptListForm.elements['maDeptCommonDTO.deptId'].value = getValueById(myGrid, selectedId, 'DEPTID');
   	maDeptListForm.elements['maDeptCommonDTO.detailPdeptId'].value = getValueById(myGrid, selectedId, 'DEPTID');
   	maDeptListForm.elements['maDeptCommonDTO.detailPdeptDesc'].value = getValueById(myGrid, selectedId, 'DESCRIPTION');
    maDeptListForm.elements['strutsAction'].value = '<%=MaDeptDetailAction.DEPT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maDeptListForm), 'maDeptDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maDeptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maDeptListForm.elements['maDeptCommonDTO.deptId'].value = "";
	goCommonTabPage(maDeptListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'deptId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maDeptListForm.strutsAction.value = '<%=MaDeptListAction.DEPT_LIST_DELETE%>';
    var url = contextPath + "/maDeptList.do";
    
    $.post(url,FormQueryString(maDeptListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maDeptDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maDeptListForm.elements['maDeptCommonDTO.deptId'].value = "";
 	excelServerAction("maDeptList", maDeptListForm );  
 }

/*
 * 엑셀 업로드
 */
function goExluploadLink()
{
	var actionUrl = contextPath + "/maDeptList.do";
    var param =  "&strutsAction=" + '<%= MaDeptListAction.GET_DATA %>'
              +  "&" + FormQueryString(maDeptListForm);
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
		dataArr = data.split('//+');
		
		uploadTypeId = dataArr[0];
		uploadType = dataArr[1];
		tableName = dataArr[2];
		
    }
		goExlupload(uploadTypeId, uploadType, tableName);
}

/**
 * Interface 수신
 */
function goInterface()
{
    // TODO - Interface 작업 
}
  
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maDeptListForm.elements['maDeptCommonDTO.deptId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maDeptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maDeptCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maDeptCommonDTO.deptId"/><!-- Key -->
<html:hidden property="maDeptCommonDTO.filterPdeptId"/>
<html:hidden property="maDeptCommonDTO.filterIsUse"/>
<html:hidden property="maDeptCommonDTO.detailPdeptId"/>
<html:hidden property="maDeptCommonDTO.detailPdeptDesc"/>
<html:hidden property="maDeptCommonDTO.filterIsMaint"/>
<html:hidden property="maDeptCommonDTO.filterPlantId"/>
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
	       <div class="article_box">
	           <div class="form_box">
	           <!--부서명  -->
	               <div class="field">
		               <label><bean:message key="LABEL.deptDesc"/></label>
		               <div class="input_box">
		               		<html:text property="maDeptCommonDTO.filterDescription" tabindex="10"/>
		               		<p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
		               </div>
	               </div>
	               <!-- 부서코드 -->
	               <div class="field">
		               <label><bean:message key="LABEL.deptNo"/></label>
                       <div class="input_box">
                            <html:text property="maDeptCommonDTO.filterDeptNo" tabindex="20"/>
                       </div>
               	   </div>
               	   <!--상위부서  -->
               	   <div class="field">
                       <label><bean:message key="LABEL.pdeptDesc"/></label>
                       <div class="input_box">
	                        <html:text property="maDeptCommonDTO.filterPdeptDesc" tabindex="40"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
                   </div>        
                   <!--사용여부  -->   
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="maDeptCommonDTO.filterIsUseDesc" tabindex="30"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                      </div>
                   </div> 
                   <div class="field">
                        <label><bean:message key="LABEL.maintDept"/></label>
                        <div class="input_box">
                           <html:text property="maDeptCommonDTO.filterIsMaintDesc" tabindex="40"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                      	</div>
                   </div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maDeptCommonDTO.filterPlantDesc" tabindex="90" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
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