<%--===========================================================================
author  jung7126
version $Id: workPmDInsSelect.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmiDInsListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!--작업일자 & 점검기준 선택 -->
<title><bean:message key="LABEL.woDateInsBaseSelect"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">

var workPointAc;

function loadPage() 
{
	//workPmiDInsCommonDTO.popupWorkPointDesc
	
	// 점검기준 자동완성 (pop up)
    workPointAc = new autoC({"workPmiDInsCommonDTO.popupWorkPointDesc":"description"});
    workPointAc.setTable("TAPMLST");
    workPointAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "is_use" : "Y"
        , "pm_type" : "DINS"
    })
    workPointAc.setKeyName("workPmiDInsCommonDTO.popupWorkPointId");
    workPointAc.setAcResultMap({
        "workPmiDInsCommonDTO.popupWorkPointId":"pmId"
      , "workPmiDInsCommonDTO.popupPmId":"pmId"
      , "workPmiDInsCommonDTO.popupWorkPointNo":"pmNo"
    });
    workPointAc.init();
	
//	setTitle("maGrdUsrDetailDTO.pageDesc","maGrdUsrDetailDTO.description");
	setTitle("","");
//	setReadOnly();
//	setForUpdate();
//	goInput();
	$('.stitle_box').text('<bean:message key="LABEL.selectWoDateInsPoint"/>'); // 작업일자와 점검기준을 선택하세요.
	//$('#detailTitle').html($('#detailTitle').html().replace(":",""));
}

function goSelect(pageName)
{
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;

    var workDate = workPmiDInsListForm.elements['workPmiDInsCommonDTO.popupWorkDate'].value;
    var workPointId = workPmiDInsListForm.elements['workPmiDInsCommonDTO.popupWorkPointId'].value;
    var workPointDesc = workPmiDInsListForm.elements['workPmiDInsCommonDTO.popupWorkPointDesc'].value;

    var workPointNo = workPmiDInsListForm.elements['workPmiDInsCommonDTO.popupWorkPointNo'].value;
    var pmId = workPmiDInsListForm.elements['workPmiDInsCommonDTO.popupPmId'].value;
    var pmNo = workPmiDInsListForm.elements['workPmiDInsCommonDTO.popupPmNo'].value;
    
	getIframeContent().execValidation(workDate, workPointId, workPointNo, pmId, pmNo);
	closeLayerPopup();
}

</script>

</head>
<BODY style="MARGIN: 0px; width: 450px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmiDInsList.do">

<html:hidden property="workPmiDInsCommonDTO.popupWorkPointId"/>
<html:hidden property="workPmiDInsCommonDTO.popupWorkPointNo"/>
<html:hidden property="workPmiDInsCommonDTO.popupPmId"/>
<html:hidden property="workPmiDInsCommonDTO.popupPmNo"/>

	<div class="section_wrap" id="filterSection">
		<div class="sheader_box">
			<div class="stitle_box"></div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
				
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
			    <!-- 작업일자 -->
				<div class="field_long">
					<label class="check"><bean:message key="LABEL.woDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workPmiDInsCommonDTO.popupWorkDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 점검기준 -->
                <div class="field_long">
					<label class="check"><bean:message key="LABEL.insBase"/></label>
					<div class="input_box">
						<html:text property="workPmiDInsCommonDTO.popupWorkPointDesc" tabindex="50"/>
						<p class="open_spop">
						    <a><span>조회</span></a>
					    </p>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div>
</html:form> 
</body>
</html>