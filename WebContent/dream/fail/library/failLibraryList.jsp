<%--===========================================================================
고장library - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.fail.library.action.FailLibraryListAction" %>
<%@ page import="dream.fail.library.action.FailLibraryDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 고장Library-->
<title><bean:message key="MENU.BMCATEG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
var eqCtgTypeAc, failurePt, failureCa, failureMo, failurePm, failureRe;

function loadPage() 
{
	initGrid();

	/** 설비종류  */
	eqCtgTypeAc = new autoC({"failLibraryCommonDTO.eqctgDesc":"description"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    eqCtgTypeAc.setAcResultMap({
        "failLibraryCommonDTO.eqctgId":"eqctg_id"
    });
	eqCtgTypeAc.init();
    
    /** 고장부위  */
    failurePt = new autoC({"failLibraryCommonDTO.failurePtDesc":"failureDesc"});
    failurePt.setTable("TAFAILURE");
    failurePt.setAcResultMap({
        "failLibraryCommonDTO.failurePtId":"failure_id"   
    });
    failurePt.setAcConditionMap({
  	   "fail_type":"PT"
  	   });
    failurePt.init();
    
    
    /** 고장원인  */
    failureCa = new autoC({"failLibraryCommonDTO.failureCaDesc":"failureDesc"});
    failureCa.setTable("TAFAILURE");
    failureCa.setAcResultMap({
        "failLibraryCommonDTO.failureCaId":"failure_id"
    });
    failureCa.setAcConditionMap({
  	   "fail_type":"CA"
  	   });
    failureCa.init();
    
    /** 고장현상  */
    failureMo = new autoC({"failLibraryCommonDTO.failureMoDesc":"failureDesc"});
    failureMo.setTable("TAFAILURE");
    failureMo.setAcResultMap({
        "failLibraryCommonDTO.failureMoId":"failure_id"
    });
    failureMo.setAcConditionMap({
  	   "fail_type":"MO"
  	   });
    failureMo.init();
    
    /**고장조치  */
    failureRe = new autoC({"failLibraryCommonDTO.failureReDesc":"failureDesc"});
    failureRe.setTable("TAFAILURE");
    failureRe.setAcResultMap({
        "failLibraryCommonDTO.failureReId":"failure_id"
    });
    failureRe.setAcConditionMap({
  	   "fail_type":"RE"
  	   });
    failureRe.init();
    
    
    /**예방업무  */
    failurPm = new autoC({"failLibraryCommonDTO.failurePmDesc":"failureDesc"});
    failurPm.setTable("TAFAILURE");
    failurPm.setAcResultMap({
        "failLibraryCommonDTO.failurePmId":"failure_id"
    });
    failurPm.setAcConditionMap({
  	   "fail_type":"PM"
  	   });
    failurPm.init();
    /** 사용여부  */
    acSysDesc("failLibraryCommonDTO.checkYn","failLibraryCommonDTO.checkYn","IS_USE");
    /** 사용여부  */
    acSysDesc("failLibraryCommonDTO.isUse","failLibraryCommonDTO.isUse","IS_USE");

}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    //myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen(rowId);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/failLibraryList.do";

    failLibraryListForm.elements['strutsAction'].value = '<%=FailLibraryListAction.FAILLIB_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(failLibraryListForm), "failsetlistId");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_failsetlistId)
{
	failLibraryListForm.elements['failLibraryCommonDTO.failsetlistId'].value = _failsetlistId;
	findGridList('ReloadRow');
	failLibraryListForm.elements['failLibraryCommonDTO.failsetlistId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	failLibraryListForm.elements['failLibraryCommonDTO.failsetlistId'].value = "";    // 검색시 Tab 이동Key Clear
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
	failLibraryListForm.elements['failLibraryCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    failLibraryListForm.elements['failLibraryCommonDTO.failsetlistId'].value = getValueById(myGrid, selectedId, 'failsetlistId');

    goCommonTabPage(failLibraryListForm, <%=FailLibraryDetailAction.FAILLIB_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
	goTabPage('failLibraryDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    failLibraryListForm.elements['failLibraryCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    failLibraryListForm.elements['failLibraryCommonDTO.failsetlistId'].value = getValueById(myGrid, selectedId, 'failsetlistId');
    failLibraryListForm.elements['strutsAction'].value = '<%=FailLibraryDetailAction.FAILLIB_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(failLibraryListForm), 'failLibraryDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "failLibraryDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	failLibraryListForm.elements['failLibraryCommonDTO.failsetlistId'].value = "";
    goCommonTabPage(failLibraryListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'failsetlistId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    failLibraryListForm.strutsAction.value = '<%=FailLibraryListAction.FAILLIB_LIST_DELETE%>';
    var url = contextPath + "/failLibraryList.do";

    $.post(url,FormQueryString(failLibraryListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('failLibraryDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/failLibraryList">
<html:hidden property="strutsAction"/>
<html:hidden property="failLibraryCommonDTO.compNo"/><!-- Key -->
<html:hidden property="failLibraryCommonDTO.failsetlistId"/><!-- Key -->
<html:hidden property="failLibraryCommonDTO.eqctgId"/>
<html:hidden property="failLibraryCommonDTO.failurePtId"/> <!-- 부위 -->
<html:hidden property="failLibraryCommonDTO.failureCaId"/>
<html:hidden property="failLibraryCommonDTO.failureMoId"/>
<html:hidden property="failLibraryCommonDTO.failurePmId"/>
<html:hidden property="failLibraryCommonDTO.failureReId"/>

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
	                <!-- 설비종류 -->
					<div class="field">
						<label><bean:message key="LABEL.type"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.eqctgDesc" tabindex="10"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 부위 -->
					<div class="field">
						<label><bean:message key="LABEL.asmb"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.failurePtDesc" tabindex="20"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 원인 -->
					<div class="field">
						<label><bean:message key="LABEL.reason"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.failureCaDesc" tabindex="30"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 현상 -->
					<div class="field">
						<label><bean:message key="LABEL.FAILUREMONO"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.failureMoDesc" tabindex="40"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 조치 -->
					<div class="field">
						<label><bean:message key="LABEL.FAILURERENO"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.failureReDesc" tabindex="50"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 예방업무 -->
					<div class="field">
						<label><bean:message key="LABEL.FAILUREPMNO"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.failurePmDesc" tabindex="60"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 작성일자 -->
					<div class="field">
						<label><bean:message key="LABEL.creDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="failLibraryCommonDTO.creDateFrom" tabindex="70" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="failLibraryCommonDTO.creDateTo" tabindex="80" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 사용여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isUse"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.isUse" tabindex="90"/>
							<p class="open_spop">
								<a>
								 <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 확인여부 -->
					<div class="field">
						<label><bean:message key="LABEL.check"/></label>
						<div class="input_box">
							<html:text property="failLibraryCommonDTO.checkYn" tabindex="100"/>
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