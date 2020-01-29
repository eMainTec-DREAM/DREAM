<%--===========================================================================
표준항목 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.pm.std.action.MaStdPointDetailAction"%>
<html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//

var checkTypeAc;
var isActiveAc;
var asmbDescAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maStdPointDetailDTO.stepNum", "maStdPointDetailDTO.checkPoint");
    
    setForUpdate();

    

	asmbDescAc = new autoC({"maStdPointDetailDTO.eqAsmbDesc":"description"});
	asmbDescAc.setAcConditionMap({
 	   "equip_id":""
 	   });
	asmbDescAc.setTable("TAEQASMB");
	asmbDescAc.setAcResultMap({
        "maStdPointDetailDTO.eqAsmbId":"eqasmb_id"
    });
	asmbDescAc.setKeyName("maStdPointDetailDTO.eqAsmbId");
	asmbDescAc.init();
    
    //수치/판정 AC
    acSysDesc("maStdPointDetailDTO.checkTypeDesc","maStdPointDetailDTO.checkType","CHECK_TYPE",true);
    //사용여부
    acSysDesc("maStdPointDetailDTO.isActive","maStdPointDetailDTO.isActive","IS_USE",true);
    
    acSysDesc("maStdPointDetailDTO.periodTypeDesc","maStdPointDetailDTO.periodType","PERIOD_TYPE",true);
    
  	//사용량/시간 AC
    acSysDesc("maStdPointDetailDTO.scheduleTypeDesc","maStdPointDetailDTO.scheduleType","SCHEDULE_TYPE",true);
    
    //수치/판정 hidden control
    checkTypeControl($("input[name='maStdPointDetailDTO.checkType']").val());
	//일정생성방법 hidden control
    checkScheduleType($("input[name='maStdPointDetailDTO.scheduleType']").val());
    
}

function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "maStdPointDetailDTO.scheduleTypeDesc") 
	{
		checkScheduleType(rtnJsonArry[0].CDSYSD_NO);
	} 
	else if(code == "maStdPointDetailDTO.checkTypeDesc") 
	{
		checkTypeControl(rtnJsonArry[0].CDSYSD_NO);
	}
}

/*
 * 수치/판정 hidden control
 */
function checkTypeControl(checkType)
{
	var checkMinObj 	= $("input[name='maStdPointDetailDTO.checkMin']");		 // 하한값
	var checkBasisObj	= $("input[name='maStdPointDetailDTO.checkBasisVal']");  // 기준값
	var checkMaxObj		= $("input[name='maStdPointDetailDTO.checkMax']");		 // 상한값
	var uomObj			= $("input[name='maStdPointDetailDTO.uom']");		     // 단위

	if('SEN' == checkType || "" == checkType) //판정 또는 선택 안한경우
	{
// 		usageObj.parent().prev().removeClass("check");
		checkMinObj.parent().parent().parent().hide();
// 		checkBasisObj.parent().parent().hide();
// 		checkMaxObj.parent().parent().hide();
// 		uomObj.parent().parent().hide();
	}
	else if('VAL' == checkType) //수치 
	{
		checkMinObj.parent().parent().parent().show();
// 		checkBasisObj.parent().parent().show();
// 		checkMaxObj.parent().parent().show();
// 		uomObj.parent().parent().show();
	}
}

/*
 * 일정생성방법 hidden control
 */
function checkScheduleType(scheduleType)
{
	var cycleObj 		= $("input[name='maStdPointDetailDTO.cycle']");				// 주기
	var periodTypeObj	= $("input[name='maStdPointDetailDTO.periodTypeDesc']");	// 주기 타입
	var usageObj 		= $("input[name='maStdPointDetailDTO.usage']");				// 사용량
// 	var scheduleType	= $("input[name='maStdPointDetailDTO.scheduleType']");
	
	//사용량(U)일 경우 주기, 주기구분항목 Disable
	if("T" == scheduleType || "" == scheduleType) // 시간(T) 또는 선택 안한경우
	{
		cycleObj.parent().parent().parent().show();
		cycleObj.parent().parent().prev().addClass("check");
		usageObj.parent().prev().removeClass("check");
		usageObj.parent().parent().hide();
		
// 		setEnable(cycleObj);
// 		setEnable(periodTypeObj);
// 		//setDisable(usageObj);
	}
	else if('R' == scheduleType || 'U' == scheduleType)
	{
		usageObj.parent().parent().show();
		usageObj.parent().prev().addClass("check");
		cycleObj.parent().parent().prev().removeClass("check");
		cycleObj.parent().parent().parent().hide();
	}
	
// 	scheduleType.bind("change",function(e){
// 		checkScheduleType($(this).val());	
// 	});
}
/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQASTWRK_POINT_ID',"WRK");
    
    //수치판정 구분  SEN - 판정
	maStdPointDetailForm.elements['maStdPointDetailDTO.checkTypeDesc'].value = "SEN";
	valSysDir('maStdPointDetailDTO.checkType', 'maStdPointDetailDTO.checkTypeDesc', 'CHECK_TYPE', true);
    //일정생성방법  T - 시간
	maStdPointDetailForm.elements['maStdPointDetailDTO.scheduleTypeDesc'].value = "T";
	valSysDir('maStdPointDetailDTO.scheduleType', 'maStdPointDetailDTO.scheduleTypeDesc', 'SCHEDULE_TYPE', true);
	
    maStdPointDetailForm.elements['maStdPointDetailDTO.isActive'].value = "Y";
}

/**
 * 수정
 */
function goUpdate()
{
}

function setSequenceVal(sequenceVal,param)
{
	if(param =="SQASTWRK_POINT_ID")
	{
		maStdPointDetailForm.elements['maStdPointDetailDTO.stWrkPointId'].value = sequenceVal;
	}
}

/**
 * 저장
 */ 
function goSave()
{
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maStdPointDetailForm.strutsAction.value = "<%=MaStdPointDetailAction.STD_DETAIL_INPUT%>";
    else maStdPointDetailForm.strutsAction.value = '<%=MaStdPointDetailAction.STD_DETAIL_UPDATE%>'; 
    
    var actionUrl = contextPath + "/maStdPointDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maStdPointDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow)	parent.findGridRow(maStdPointDetailForm.elements['maStdPointDetailDTO.stWrkPointId'].value);
     setTitle("maStdPointDetailDTO.stepNum","maStdPointDetailDTO.checkPoint");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 
 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = maEqMstrDetailForm.elements['maStdPointDetailDTO.stWrkPointId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maStdPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maStdPointCommonDTO.stWrkId"/>
	<html:hidden property="maStdPointDetailDTO.stWrkPointId"/>
	<html:hidden property="maStdPointDetailDTO.checkType"/>
	<html:hidden property="maStdPointDetailDTO.periodType"/>
	<html:hidden property="maStdPointDetailDTO.scheduleType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 점검순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.stepNum" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.checkMethod" tabindex="30"/>
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.checkPoint" tabindex="50"/>
				</div>
			</div>
			
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.fitBasis" tabindex="60"/>
				</div>
			</div>
			<!-- 수치판정구분 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkType"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.checkTypeDesc" tabindex="70" />
					<p class="open_spop">
						<a>
						<span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 하한/기준/상한/단위 -->
			<div class="field_long ty4">
				<label>하한/기준/상한/단위</label>
				<div class="multi_wrap">
					<div class="input_box">
						<html:text property="maStdPointDetailDTO.checkMin" tabindex="80" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maStdPointDetailDTO.checkBasisVal" tabindex="82" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maStdPointDetailDTO.checkMax" tabindex="84" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maStdPointDetailDTO.uom" tabindex="86" />
					</div>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.isActive" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			
			
			<div class="field">
				<!-- 사용량/시간 구분-->
				<label class="check"><bean:message key="LABEL.scheduleTypeDesc"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.scheduleTypeDesc" tabindex="130" />
					<p class="open_spop">
						<a>
						<span>조회</span></a>
					</p>
				</div>
			</div>
			<div class="field">
                <label><bean:message key="LABEL.cycleDesc"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="maStdPointDetailDTO.cycle" tabindex="90" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="maStdPointDetailDTO.periodTypeDesc" tabindex="140" />
                        <p class="open_spop">
                            <a>
                            <span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>

            
			<div class="field">
				<!-- 사용량 -->
				<label><bean:message key="LABEL.usage"/></label>
				<div class="input_box">
					<html:text property="maStdPointDetailDTO.usage" tabindex="150" styleClass="num"/>
				</div>
			</div>
            <!-- 예상소요시간(분) -->
			<div class="field">
                <label>예상소요시간(분)</label>
                <div class="input_box">
					<html:text property="maStdPointDetailDTO.predTime" tabindex="160" styleClass="num"/>
				</div>
            </div>
            <!-- 정렬값 -->
			<div class="field">
                <label>정렬값</label>
                <div class="input_box">
					<html:text property="maStdPointDetailDTO.ordNo" tabindex="170"/>
				</div>
            </div>
			<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="maStdPointDetailDTO.remark"
							tabindex="200" />
					</div>
				</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>

