<%--===========================================================================
자재마스터
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrListAction" %>
<%@ page import="dream.part.list.action.MaPtMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 자재마스터 -->
<title><bean:message key="MENU.PTMSTR"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var partNameAc,mroTypeAc,deptAc,partGroupAc,partClassAc,inPartAc,partGroupAc,equipAc;

function loadPage() 
{
	initGrid();
	
	setFunction();
	
    maPtMstrListForm.elements['maPtMstrCommonDTO.filterIsUse'].value = 'Y';
    maPtMstrListForm.elements['maPtMstrCommonDTO.filterIsUseDesc'].value = 'Y';
  }

/**
 * All Fucntion 
 */
function setFunction()
{
   partNameAc = new autoC({"maPtMstrCommonDTO.filterPartNameSize":"full_desc"});
   partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
   partNameAc.setTable("TAPARTS");
   partNameAc.init(); 

   deptAc = new autoC({"maPtMstrCommonDTO.filterDeptDesc":"description"});
   deptAc.setAcConditionMap({
   	"comp_no":loginUser.compNo
 	   });
   deptAc.setTable("TADEPT");
   deptAc.setAcResultMap({
       "maPtMstrCommonDTO.filterDeptId":"dept_id"
   });
   deptAc.init();
   

	partGroupAc = new autoC({"maPtMstrCommonDTO.filterPartGroupDesc":"description"});
	partGroupAc.setAcDisplay("DESCRIPTION");
	partGroupAc.setAcConditionMap({
	    	"dir_type":"PART_GROUP",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	partGroupAc.setTable("TACDUSRD");
	partGroupAc.setAcResultMap({
	    "maPtMstrCommonDTO.filterPartGroup":"cdusrd_no"
	});
	partGroupAc.init();
	
	equipAc = new autoC({"maPtMstrCommonDTO.filterEquipDesc":"description"});
	equipAc.setAcDisplay("DESCRIPTION");
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
	    "maPtMstrCommonDTO.filterEquipId":"equip_id"
	});
	equipAc.init();
  
   acSysDesc("maPtMstrCommonDTO.filterMroTypeDesc","maPtMstrCommonDTO.filterMroType","MRO_TYPE");
   acSysDesc("maPtMstrCommonDTO.filterPtAbcClassDesc","maPtMstrCommonDTO.filterPtAbcClass","PT_ABC_CLASS");
   acSysDesc("maPtMstrCommonDTO.filterIsUseDesc","maPtMstrCommonDTO.filterIsUse","IS_USE");
   acSysDesc("maPtMstrCommonDTO.filterIsInPartDesc","maPtMstrCommonDTO.filterIsInPart","IS_USE");
   
   acSysDesc("maPtMstrCommonDTO.isSerialPart","maPtMstrCommonDTO.isSerialPart","IS_USE");
   acSysDesc("maPtMstrCommonDTO.isStockControl","maPtMstrCommonDTO.isStockControl","IS_USE");
   acSysDesc("maPtMstrCommonDTO.filterIsAssetStockDesc","maPtMstrCommonDTO.filterIsAssetStockId","IS_USE");
   acSysDesc("maPtMstrCommonDTO.filterPlfTypeDesc","maPtMstrCommonDTO.filterPlfType","PLF_TYPE");
   
   /* maPtMstrListForm.elements['maPtMstrCommonDTO.filterMroType'].value = "C";
   var sysCode = acSysCode("maPtMstrCommonDTO.filterMroType","maPtMstrCommonDTO.filterMroTypeDesc","MRO_TYPE");
   sysCode.exec(); */
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
    	maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = "";
    	return sortColumn("maPtMstrList", this, maPtMstrListForm, "PARTID", ind, direction);
	});
    
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

<%-- function beforeSorting()
{
    var url = contextPath + "/maPtMstrList.do";

    maPtMstrListForm.elements['strutsAction'].value = '<%=MaPtMstrListAction.PTMSTR_LIST_FIND%>';

    doGridAction("Search", myGrid, "gridbox", url, FormQueryString(maPtMstrListForm), "PARTID","N");
    
    return true;
} --%>

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//var ids = myGrid.getChangedRows(true);
	
    var url = contextPath + "/maPtMstrList.do";

   
    maPtMstrListForm.elements['strutsAction'].value = '<%=MaPtMstrListAction.PTMSTR_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtMstrListForm), "PARTID","Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_partId)
{
	maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = _partId;
	findGridList('ReloadRow');
	maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search');
}

function afterSearch()
{
	maPtMstrListForm.elements['maPtMstrCommonDTO.sapParts'].value = ""; 
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
	maPtMstrListForm.elements['maPtMstrCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');

    goCommonTabPage(maPtMstrListForm, <%=MaPtMstrDetailAction.PTMSTR_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPtMstrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtMstrListForm.elements['maPtMstrCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
    maPtMstrListForm.elements['strutsAction'].value = '<%=MaPtMstrDetailAction.PTMSTR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtMstrListForm), 'maPtMstrDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtMstrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = "";
    goCommonTabPage(maPtMstrListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'partId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtMstrListForm.strutsAction.value = '<%=MaPtMstrListAction.PTMSTR_LIST_DELETE%>';
    var url = contextPath + "/maPtMstrList.do";

    $.post(url,FormQueryString(maPtMstrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtMstrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value = "";
 	excelServerAction("maPtMstrList", maPtMstrListForm );
	//excelAction(myGrid);
}

/*
 * 엑셀 업로드
 */
function goExluploadLink()
{
	var actionUrl = contextPath + "/maPtMstrList.do";
    var param =  "&strutsAction=" + '<%= MaPtMstrListAction.GET_DATA %>'
              +  "&" + FormQueryString(maPtMstrListForm);
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
 * 자재 복사
 */
 function goCopycreate(){
	 var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'PARTID'); //Grid, check box column seq, pk column seq
	 	
 	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	 
 	dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
		if(result){
			setModal('<bean:message key="MESSAGE.MSG0083"/>');
			maPtMstrListForm.strutsAction.value = '<%=MaPtMstrListAction.PTMSTR_LIST_COPY%>';
			var url = contextPath + "/maPtMstrList.do";
			$.post(url,FormQueryString(maPtMstrListForm)+selArray , function(_data){
				var jsonObj = JSON.parse(_data);  
		    	for(var i = 0; jsonObj.data.length >i ; i++)
		    	{
		    		findGridRow(jsonObj.data[i].ID);
		    	}
		    	closeModal();
		    	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
		    });
		}
	});
 }
 
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maPtMstrListForm.elements['maPtMstrCommonDTO.partId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtMstrList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtMstrCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPtMstrCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtMstrCommonDTO.filterPlfType"/>
<html:hidden property="maPtMstrCommonDTO.filterMroType"/>
<html:hidden property="maPtMstrCommonDTO.filterIsUse"/>
<html:hidden property="maPtMstrCommonDTO.filterDeptId"/>
<html:hidden property="maPtMstrCommonDTO.filterIsInPart"/>
<html:hidden property="maPtMstrCommonDTO.filterPtAbcClass"/>
<html:hidden property="maPtMstrCommonDTO.filterPartGroup"/>
<html:hidden property="maPtMstrCommonDTO.filterEquipId"/>
<html:hidden property="maPtMstrCommonDTO.filterIsAssetStockId"/>
<html:hidden property="maPtMstrCommonDTO.sapParts"/>

    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
        <div class="sheader_box">
            <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter not_fold">
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
                       <label><bean:message key="LABEL.partNameSize"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterPartNameSize" tabindex="10"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.partNo"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterPartNo" tabindex="15"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterModel" tabindex="20"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterMaker" tabindex="30"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.seller"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterSeller" tabindex="35"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.ptUsage"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterUsage" tabindex="40"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.plfType"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterPlfTypeDesc" tabindex="122"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
                   </div>                   
                   <div class="field">
                        <label><bean:message key="LABEL.mroType"/></label>
                        <div class="input_box">
                           <html:text property="maPtMstrCommonDTO.filterMroTypeDesc" tabindex="55"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="maPtMstrCommonDTO.filterIsUseDesc" tabindex="60"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div> 
                   <!-- 관리부서 -->
				   <div class="field">
					    <label><bean:message key="LABEL.usedDept"/></label>
					    <div class="input_box">
					        <html:text property="maPtMstrCommonDTO.filterDeptDesc" tabindex="70"
					                    onkeydown="validationKeyDown('maPtMstrCommonDTO.filterDeptDesc', 'maPtMstrCommonDTO.filterDeptId');"/>
					        <p class="open_spop"><a href="javascript:lovDept('maPtMstrCommonDTO.filterDeptId', '', 'maPtMstrCommonDTO.filterDeptDesc');"><span>조회</span></a></p>
					    </div>
                   </div> 
                   <div class="field">
                      <label><bean:message key="LABEL.isInPart"/></label>
                      <div class="input_box">
                         <html:text property="maPtMstrCommonDTO.filterIsInPartDesc" tabindex="80"/>
                          <p class="open_spop">
                              <a>
                                  <span>조회</span>
                              </a>
                          </p>
                    </div>
                   </div> 
                   <div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterVendorPtCode" tabindex="90"/>
                       </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.partGroup"/></label>
                        <div class="input_box">
                           <html:text property="maPtMstrCommonDTO.filterPartGroupDesc" tabindex="95"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.isStockControl"/></label>
                        <div class="input_box">
                           <html:text property="maPtMstrCommonDTO.isStockControl" tabindex="95"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.isSerialPart"/></label>
                        <div class="input_box">
                           <html:text property="maPtMstrCommonDTO.isSerialPart" tabindex="95"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.ptAbcClass"/></label>
                        <div class="input_box">
                           <html:text property="maPtMstrCommonDTO.filterPtAbcClassDesc" tabindex="100"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   
                   <!-- 신청일자 -->
					<div class="field">
						<label><bean:message key="LABEL.receivedDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="maPtMstrCommonDTO.filterOutStartUpdDate" tabindex="110" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="maPtMstrCommonDTO.filterOutEndUpdDate" tabindex="120" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<div class="field">
	                       <label><bean:message key="LABEL.vendorPtCode"/></label>
	                       <div class="input_box">
	                            <html:text property="maPtMstrCommonDTO.filterOldPartNo" tabindex="15"/>
	                       </div>
	                </div>
	                <!-- 설비 -->
	                <div class="field">
	                     <label><bean:message key="LABEL.equipName"/></label>
	                     <div class="input_box">
	                        <html:text property="maPtMstrCommonDTO.filterEquipDesc" tabindex="100"/>
	                         <p class="open_spop"><a><span>조회</span></a></p>
	                   </div>
	                </div>
	                <!-- 비고 -->
	                <div class="field">
                       <label><bean:message key="LABEL.remark"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterRemark" tabindex="110"/>
                       </div>
                   </div>
	                <!-- 저장품여부 -->
	                <div class="field">
                       <label><bean:message key="LABEL.isAssetStock"/></label>
                       <div class="input_box">
                            <html:text property="maPtMstrCommonDTO.filterIsAssetStockDesc" tabindex="120"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                       </div>
                   </div>
                   <!-- ERP 품번 -->
                   <div class="field">
	                       <label><bean:message key="LABEL.erpPartNo"/></label>
	                       <div class="input_box">
	                            <html:text property="maPtMstrCommonDTO.filterErpPartNo" tabindex="130"/>
	                       </div>
	                </div>
                       
                </div><!--form_box end--> 
            </div><!--article_box end-->
    </div> <!--  end section_wrap -->
    <div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
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