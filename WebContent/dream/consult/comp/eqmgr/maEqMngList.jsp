<%--===========================================================================
설비담당자변경 - 목록
author  jung7126
version $Id: maEqMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.eqmgr.action.MaEqMngListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비담당자변경 -->
<title><bean:message key='MENU.maEqMngList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */
var eqLocDescAc, deptAc, mainMngAc, subMngAc, equipDescAc, eqCtgAc;
function loadPage() 
{
	// 설비종류
	eqCtgAc = new autoC({"maEqMngCommonDTO.filterEqCtgDesc":"full_desc"});
	eqCtgAc.setTable("TAEQCTG");
	eqCtgAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
	eqCtgAc.setAcResultMap({
        "maEqMngCommonDTO.filterEqCtgId":"eqctg_id"
    });
	eqCtgAc.init();
    
	// 설비명
	equipDescAc = new autoC({"maEqMngCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maEqMngCommonDTO.filterEqLocId",
    	"eqctg_id" : "maEqMngCommonDTO.filterEqCtgId",
    });
    equipDescAc.init();
    
	// 관리부서
	deptAc = new autoC({"maEqMngCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEqMngCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
	
    // 관리자(정)
    mainMngAc = new autoC({"maEqMngCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maEqMngCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMngCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    
    // 관리자(부)
	subMngAc = new autoC({"maEqMngCommonDTO.filterSubMngName":"emp_name"});
	subMngAc.setTable("TAEMP");
	subMngAc.setAcResultMap({
        "maEqMngCommonDTO.filterSubMngId":"emp_id"
    });
	subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMngCommonDTO.filterDeptId"
    });
	subMngAc.init();
    
	//부서
	maEqMngListForm.elements['maEqMngCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	maEqMngListForm.elements['maEqMngCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;

	if(loginUser.eqLocId!='null'){
		maEqMngListForm.elements['maEqMngCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		maEqMngListForm.elements['maEqMngCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
    initGrid();
    
    eqLocDescAc = new autoC({"maEqMngCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maEqMngCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    goTabPage("maEqMngDetail");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500); 
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goOpen();
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEqMngList.do";

    maEqMngListForm.elements['strutsAction'].value = '<%=MaEqMngListAction.EQ_MNG_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMngListForm), "EQUIPID", "Y");
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maEqMngListForm.elements['maEqMngCommonDTO.equipId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_equipId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMngListForm.elements['maEqMngCommonDTO.equipId'].value != _equipId) return;
	maEqMngListForm.elements['maEqMngCommonDTO.equipId'].value = _equipId;
	findGridList('ReloadRow');
	maEqMngListForm.elements['maEqMngCommonDTO.equipId'].value = "";
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
	goCommonTabPage(maEqMngListForm, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMngDetail');
}
 
  /**
   * 생성
   */
 function goCreate()
 {
 	createValidationCheck(myGrid, "maEqMngDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMngListForm.elements['maEqMngCommonDTO.equipId'].value = "";
	goCommonTabPage(maEqMngListForm, '', pageId);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMngCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMngCommonDTO.filterEqLocId"/>
<html:hidden property="maEqMngCommonDTO.filterEqCtgId"/>
<html:hidden property="maEqMngCommonDTO.filterPlfTypeId"/>
<html:hidden property="maEqMngCommonDTO.filterDeptId"/>
<html:hidden property="maEqMngCommonDTO.filterMainMngId"/>
<html:hidden property="maEqMngCommonDTO.filterSubMngId"/>
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
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.mngDept"/></label>
					<div class="input_box">
						<html:text property="maEqMngCommonDTO.filterDeptDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maEqMngCommonDTO.filterEqLocDesc" tabindex="20" />
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
						<html:text property="maEqMngCommonDTO.filterMainMngName" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maEqMngCommonDTO.filterSubMngName" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비명 -->
				<div class="field">
                    <label>설비명</label>
                    <div class="input_box">
                        <html:text property="maEqMngCommonDTO.filterEquipDesc" tabindex="50"/>
                    </div>
                </div>
                <!-- 설비종류 -->
                <div class="field">
                    <label>설비종류</label>
                    <div class="input_box">
                        <html:text property="maEqMngCommonDTO.filterEqCtgDesc" tabindex="60"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 설비번호 -->
                <div class="field">
                    <label>설비번호</label>
                    <div class="input_box">
                        <html:text property="maEqMngCommonDTO.filterItemNo" tabindex="70"/>
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
				<div id="gridbox" style="width:100%; height:150px; background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>