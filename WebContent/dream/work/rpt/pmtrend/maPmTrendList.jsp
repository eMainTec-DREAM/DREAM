<%--===========================================================================
예방점검수치추이
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mapmtrend.action.MaPmTrendListAction" %>
<%@ page import="dream.work.rpt.mapmtrend.action.MaPmTrendDetailAction" %>
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
<!-- 예방점검수치추이 -->
<title><bean:message key='MENU.PMTREND'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var pmGrid;         //예방점검경향분석 그리드

/** 자동완성 변수 */
var eqLocDescAc;
var eqCtgTypeAc;
var deptAc;
var mainMngAc;
var equipDescAc;
var subMngAc;
var empAc;

function loadPage() 
{
	
	//일자 세팅
	maPmTrendListForm.elements['maPmTrendCommonDTO.filterStartDate'].value = getMinusDay(30);
	maPmTrendListForm.elements['maPmTrendCommonDTO.filterEndDate'].value   = getToday();
	//부서정보 세팅
    maPmTrendListForm.elements['maPmTrendCommonDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
    maPmTrendListForm.elements['maPmTrendCommonDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
    if(loginUser.eqLocId!='null'){
        maPmTrendListForm.elements['maPmTrendCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
        maPmTrendListForm.elements['maPmTrendCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
    }
	
	initGrid();
	
	eqLocDescAc = new autoC({"maPmTrendCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcDisplay("DESCRIPTION");
    eqLocDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maPmTrendCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maPmTrendCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maPmTrendCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    deptAc = new autoC({"maPmTrendCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPmTrendCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();

    //법정설비 여부 AC
    acSysDesc("maPmTrendCommonDTO.filterIsLawEq","maPmTrendCommonDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maPmTrendCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPmTrendCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPmTrendCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maPmTrendCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maPmTrendCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maPmTrendCommonDTO.filterDeptId"
    });
    subMngAc.init();
    
    equipDescAc = new autoC({"maPmTrendCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maPmTrendCommonDTO.filterEqLocId",
    	"eqctg_id" : "maPmTrendCommonDTO.filterEqCtgId",
    	"dept_id" : "maPmTrendCommonDTO.filterDeptId"
    });
    equipDescAc.init();
    
    // 담당자
    empAc = new autoC({"maPmTrendCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "maPmTrendCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "maPmTrendCommonDTO.filterDeptId"
    });
    empAc.init();

    // 내/외자
    acSysDesc("maPmTrendCommonDTO.filterPlfTypeDesc","maPmTrendCommonDTO.filterPlfTypeId","PLF_TYPE");
    
    
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maPmTrendListForm.elements['maPmTrendDetailDTO.pmPointId'].value = "";
        return sortColumn("maPmTrendList", this, maPmTrendListForm, "pmPointId", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
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
	if(checkValidation()) return;
	
	var form = document.maPmTrendListForm;	
	form.strutsAction.value = '<%=MaPmTrendListAction.EMP_MTTR_LIST_FIND %>';
	
	var url = contextPath + "/maPmTrendList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmTrendListForm), "PMPOINTID", "Y");
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
	var form = document.maPmTrendListForm;

	maPmTrendListForm.elements['maPmTrendDetailDTO.pmId'].value = getValueById(myGrid, selectedId, 'PMID');
	maPmTrendListForm.elements['maPmTrendDetailDTO.pmPointId'].value = getValueById(myGrid, selectedId, 'PMPOINTID');
	maPmTrendListForm.elements['maPmTrendDetailDTO.equipId'].value = getValueById(myGrid, selectedId, 'EQUIPID');
	maPmTrendListForm.elements['maPmTrendDetailDTO.pmType'].value = getValueById(myGrid, selectedId, 'PMTYPE');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
    goTabPage('maPmTrendDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  if(checkValidation()) return;
  	excelServerAction("maPmTrendList", maPmTrendListForm);
  }
 
  /** 
   * 수정된 그리드 1건을 다시 조회한다.
   */
  function findGridRow(_pmPointId)
  {
	  maPmTrendListForm.elements['maPmTrendCommonDTO.pmPointId'].value = _pmPointId;
      findGridList('ReloadRow');
      maPmTrendListForm.elements['maPmTrendCommonDTO.pmPointId'].value = "";
  }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPmTrendList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="maPmTrendCommonDTO.filterDeptId"/>
<html:hidden property="maPmTrendCommonDTO.pmPointId"/>
<html:hidden property="maPmTrendCommonDTO.filterEqLocId"/>
<html:hidden property="maPmTrendCommonDTO.filterEqCtgId"/>
<html:hidden property="maPmTrendCommonDTO.filterPlfTypeId"/>
<html:hidden property="maPmTrendCommonDTO.filterMainMngId"/>
<html:hidden property="maPmTrendCommonDTO.filterSubMngId"/>
<html:hidden property="maPmTrendCommonDTO.filterEmpId"/>

<html:hidden property="maPmTrendDetailDTO.pmId"/>
<html:hidden property="maPmTrendDetailDTO.pmPointId"/>
<html:hidden property="maPmTrendDetailDTO.equipId"/>
<html:hidden property="maPmTrendDetailDTO.startDate"/>
<html:hidden property="maPmTrendDetailDTO.endDate"/>
<html:hidden property="maPmTrendDetailDTO.pmType"/>

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
				<!-- 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipName"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterEquipDesc" tabindex="10"/>
                    </div>
                </div>
                <!-- 위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.location"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterEqLocDesc" tabindex="20" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 종류 -->
                <div class="field">
                    <label><bean:message key="LABEL.type"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterEqCtgDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 내/외자 -->
				<div class="field">
				    <label><bean:message key="LABEL.plfType"/></label>
				    <div class="input_box">
				         <html:text property="maPmTrendCommonDTO.filterPlfTypeDesc" tabindex="122"/>
				         <p class="open_spop"><a><span>조회</span></a></p>
				    </div>
				</div>
                
                <!-- 관리부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.mngDept"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterDeptDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterEmpDesc" tabindex="55" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                
                <!-- 법정설비여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isLawEq"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterIsLawEq" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 관리자(정) -->
                <div class="field">
                    <label><bean:message key="LABEL.mainManager"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterMainMngName" tabindex="70" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 제작사 -->
                <div class="field">
                    <label><bean:message key="LABEL.maker"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterMaker" tabindex="80"/>
                    </div>
                </div>
                <!-- 관리자(부) -->
                <div class="field">
                    <label><bean:message key="LABEL.subManager"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterSubMngName" tabindex="90" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 모델 -->
                <div class="field">
                    <label><bean:message key="LABEL.model"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendCommonDTO.filterModelNo" tabindex="100"/>
                    </div>
                </div>
                <!-- 점검일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.inspectDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="maPmTrendCommonDTO.filterStartDate" tabindex="110" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="maPmTrendCommonDTO.filterEndDate" tabindex="120" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
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