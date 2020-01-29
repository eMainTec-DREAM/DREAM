<%--===========================================================================
자재출고확정
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.wo.action.MaPtIssListAction" %> 
<%@ page import="dream.part.iss.wo.action.MaPtIssDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String partGrade = MwareConfig.getPartGrade();
%>
<html>
<head>
<!-- 자재출고확정 -->
<title><bean:message key="LABEL.serialNo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
var myGrid;
/** 자동완성 변수 */
var deptAc;
var equipDescAc;
var partAc;
var workDescAc;
var mainMngAc;
var multiPartAc;
var plantAc;
var ptissAc;
var issDeptAc;
var recByAc;
var whAc;

function loadPage() 
{
	//출고부서
 	setInitVal('maPtIssCommonDTO.filterIssueDeptId', loginUser.filterDeptId);
	setInitVal('maPtIssCommonDTO.filterIssueDeptDesc', loginUser.filterDeptDesc);
	
	setInitVal('maPtIssCommonDTO.filterPlantId', loginUser.filterPlant);
	setInitVal('maPtIssCommonDTO.filterPlantDesc', loginUser.filterPlantDesc);
	
	initGrid();	
	
	deptAc = new autoC({"maPtIssCommonDTO.deptDesc":"description"});
	deptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	});
	deptAc.setAcDConditionMap({
    	"plant" : "maPtIssCommonDTO.filterPlantId"
    });
	deptAc.setTable("TADEPT");
	deptAc.setAcResultMap({
	    "maPtIssCommonDTO.deptId":"dept_id"
	});
	deptAc.init();
	
	//창고 자동완성
    whAc = new autoC({"maPtIssCommonDTO.filterWname":"wname"});
    whAc.setTable("TAWAREHOUSE");
    whAc.setKeyName("maPtIssCommonDTO.filterWcodeId");
    whAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "wh_categ":"PART"
      , "is_use":"Y"
  	});
    whAc.setAcDConditionMap({
    	"plant"     : "maPtIssCommonDTO.filterPlantId"
   	  , "plantDesc" : "maPtIssCommonDTO.filterPlantDesc"
    });
    whAc.setAcResultMap({
        "maPtIssCommonDTO.filterWcodeId":"wcode_id"
    });
    whAc.init();
	
	equipDescAc = new autoC({"maPtIssCommonDTO.equipDesc":"description"});
	equipDescAc.setAcConditionMap({
	  "comp_no":loginUser.compNo
	});
	equipDescAc.setAcDConditionMap({
    	"dept_id" : "maPtIssCommonDTO.deptId",
    	"plant" : "maPtIssCommonDTO.filterPlantId"
    });
	equipDescAc.setTable("TAEQUIPMENT");//maPtIssCommonDTO.equipId
	equipDescAc.setAcResultMap({
        "maPtIssCommonDTO.equipId":"equip_id"
    });
    	equipDescAc.init();
	
	partAc = new autoC({"maPtIssCommonDTO.partDesc":"full_desc"});
    partAc.setAcConditionMap({
 	   "part_categ":"SPPT",
 	   "comp_no":loginUser.compNo
 	   });
    partAc.setTable("TAPARTS");
    partAc.setAcResultMap({
        "maPtIssCommonDTO.partId":"part_id"
    });
    partAc.init();
    
    workDescAc = new autoC({"maPtIssCommonDTO.":""});
    workDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    workDescAc.setTable("TAWORKORDER");
    workDescAc.init();
    
    mainMngAc = new autoC({"maPtIssCommonDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPtIssCommonDTO.deptId",
    	"plant" : "maPtIssCommonDTO.filterPlantId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPtIssCommonDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    multiPartAc = new autoC({"maPtIssDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
    multiPartAc.setAcResultMap({
	    "maPtIssDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();

    //공장
    plantAc = new autoC({"maPtIssCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	, "is_use":"Y"
    });
    plantAc.setAcResultMap({
        "maPtIssCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("maPtIssCommonDTO.issStatusDesc","maPtIssCommonDTO.issStatus","PTISS_STATUS");
    
    //출고부서
    issDeptAc = new autoC({"maPtIssCommonDTO.filterIssueDeptDesc":"description"});
    issDeptAc.setTable("TADEPT");
    issDeptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    issDeptAc.setAcResultMap({
        "maPtIssCommonDTO.filterIssueDeptId":"dept_id"
    });
    issDeptAc.init();
    
  	//수령자
    recByAc = new autoC({"maPtIssCommonDTO.filterRecByDesc":"emp_name"});
    recByAc.setTable("TAEMP");
    recByAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    recByAc.setAcResultMap({
        "maPtIssCommonDTO.filterRecById":"emp_id"
    });
    recByAc.init();
    
    /**출고구분 */
  	ptissAc = new autoC({"maPtIssCommonDTO.filterPtissTypeDesc":"description"});
  	ptissAc.setAcConditionMap({
    	  "list_type" : "PTISS_TYPE"
    	, "param2" 	  : "GITYPE"
  	   });
  	ptissAc.setTable("TACDSYSD");
  	ptissAc.setAcResultMap({
        "maPtIssCommonDTO.filterPtissTypeId":"ptiss_type"
    });
  	ptissAc.init();
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
    	maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value = "";
       	return sortColumn("maPtIssList", this, maPtIssListForm, "ptisslistId", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"userQty"),".",",");

    myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtIssList.do";

    maPtIssListForm.elements['strutsAction'].value = '<%=MaPtIssListAction.PTISS_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtIssListForm), "ptisslistId","Y");
    
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value = "";     // 검색시 Tab 이동Key Clear
	findGridList('Search');
}

function findGridRow(ptisslistId)
{
	maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value 	= ptisslistId;
	findGridList('ReloadRow');
	maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value 	= "";
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
    maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value = getValueById(myGrid, selectedId, 'ptisslistId');
    
    goCommonTabPage(maPtIssListForm, <%=MaPtIssDetailAction.PTISS_DETAIL_INIT%>, pageId);
    
    maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value = '';
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPtIssDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtIssDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value = "";
    goCommonTabPage(maPtIssListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptisslistId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtIssListForm.strutsAction.value = '<%=MaPtIssListAction.PTISS_LIST_DELETE%>';
    var url = contextPath + "/maPtIssList.do";

    $.post(url,FormQueryString(maPtIssListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('maPtIssDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG0285"/>');//완료된 출고건을 제외하고 삭제되었습니다
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value 	= "";
	excelServerAction("maPtIssList", maPtIssListForm);
}

function goSave(){
	var url = contextPath + "/maPtIssList.do";
	
    $.post(url,FormQueryString(maPtIssListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
    
    setForNormal();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();
	
	setForUpdate();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maPtIssDetailDTO.multiDesc')
	{
		maPtIssListForm.strutsAction.value = '<%=MaPtIssListAction.PTISS_LIST_INPUT%>';
		
		maPtIssListForm.elements['maPtIssDetailDTO.partGrade'].value = '<%=partGrade%>';
		
		//부서창고로 기본 세팅
	    maPtIssListForm.elements['maPtIssDetailDTO.wcodeId'].value = loginUser.wcodeId;
	    
	    maPtIssListForm.elements['maPtIssDetailDTO.issueDate'].value   = getToday();
	    maPtIssListForm.elements['maPtIssDetailDTO.issueQty'].value   = 1;
	    
	  	//공장명
	    if(loginUser.plant!='null'){
	    	maPtIssListForm.elements['maPtIssDetailDTO.plantId'].value = loginUser.plant;
	    }
		
	    maPtIssListForm.elements['maPtIssDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtIssListForm.elements['maPtIssCommonDTO.ptisslistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtIssList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtIssCommonDTO.ptisslistId"/><!-- Key -->
<html:hidden property="maPtIssCommonDTO.deptId"/>
<html:hidden property="maPtIssCommonDTO.equipId"/>
<html:hidden property="maPtIssCommonDTO.partId"/>
<html:hidden property="maPtIssCommonDTO.issStatus"/>
<html:hidden property="maPtIssCommonDTO.filterPlantId"/>
<html:hidden property="maPtIssCommonDTO.filterWcodeId"/>
<html:hidden property="maPtIssCommonDTO.filterIssueDeptId"/>
<html:hidden property="maPtIssCommonDTO.filterPtissTypeId"/>
<html:hidden property="maPtIssCommonDTO.filterRecById"/>
<html:hidden property="maPtIssDetailDTO.partGrade" />
<html:hidden property="maPtIssDetailDTO.wcodeId" />
<html:hidden property="maPtIssDetailDTO.issueDate" />
<html:hidden property="maPtIssDetailDTO.issueQty" />
<html:hidden property="maPtIssDetailDTO.plantId" />
<html:hidden property="maPtIssDetailDTO.multiKey" />
<html:hidden property="maPtIssDetailDTO.multiDesc" />
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
	                <!-- 작업일자 -->
					<div class="field">
						<label><bean:message key="LABEL.woDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="maPtIssCommonDTO.startDateFrom" tabindex="10" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="maPtIssCommonDTO.startDateTo" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.deptDesc" tabindex="40"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 설비 -->
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.equipDesc" tabindex="35"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
				    <!-- 부품 -->
					<div class="field">
					    <label><bean:message key="LABEL.partNameSize"/></label>
					    <div class="input_box">
					        <html:text property="maPtIssCommonDTO.partDesc" tabindex="50"/>
					        <p class="open_spop">
					            <a>
					                <span>조회</span>
					            </a>
					        </p>
					    </div>
					</div> 
					<!-- 작업번호 -->
					<div class="field">
						<label><bean:message key="LABEL.woNo"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.woNo" tabindex="1"/>
						</div>
					</div>
					<!-- 작업명 -->
					<div class="field">
						<label><bean:message key="LABEL.woName"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.woDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.empDesc" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 출고일자 -->
					<div class="field">
						<label><bean:message key="LABEL.issDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="maPtIssCommonDTO.issDateFrom" tabindex="10" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="maPtIssCommonDTO.issDateTo" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 출고상태 -->
					<div class="field">
						<label><bean:message key="LABEL.issStatus"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.issStatusDesc" tabindex="70"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 출고구분  -->
					<div class="field">
					    <label><bean:message key="LABEL.ptIssMode"/></label>
					    <div class="input_box">
					        <html:text property="maPtIssCommonDTO.filterPtissTypeDesc" tabindex="80"/>
					        <p class="open_spop"><a><span>조회</span></a></p>
					    </div>
					</div>	
					<!-- 공장명  -->
					<div class="field">
					    <label><bean:message key="LABEL.plantDesc"/></label>
					    <div class="input_box">
					        <html:text property="maPtIssCommonDTO.filterPlantDesc" tabindex="90"/>
					        <p class="open_spop"><a><span>조회</span></a></p>
					    </div>
					</div>
					<!-- 출고창고 -->
					<div class="field">
						<label><bean:message key="LABEL.issWcode"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.filterWname" tabindex="100"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 출고부서 -->
					<div class="field">
						<label><bean:message key="LABEL.issDept"/></label>
						<div class="input_box">
							<html:text property="maPtIssCommonDTO.filterIssueDeptDesc" tabindex="110"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 수령자  -->
					<div class="field">
					    <label><bean:message key="LABEL.recBy"/></label>
					    <div class="input_box">
					        <html:text property="maPtIssCommonDTO.filterRecByDesc" tabindex="120"/>
					        <p class="open_spop"><a><span>조회</span></a></p>
					    </div>
					</div>
					<!-- 출고번호  -->
					<div class="field">
					    <label><bean:message key="LABEL.ptIssListId"/></label>
					    <div class="input_box">
					        <html:text property="maPtIssCommonDTO.filterPtIssListNo" tabindex="130"/>
					    </div>
					</div>
					<c:set var="filePath" value="enhance/${compName}/part/iss/wo/maPtIssList_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/iss/wo/maPtIssList_${compNo}.jsp"></c:import>
					</c:if>		
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