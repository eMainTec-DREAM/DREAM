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
<%@ page import="dream.work.close.check.action.MgrWorkCloseCheckPointDetailAction"%>
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
    
    setTitle("mgrWorkCloseCheckPointDetailDTO.stepNum", "mgrWorkCloseCheckPointDetailDTO.checkPoint");
    
    setForUpdate();

    

	asmbDescAc = new autoC({"mgrWorkCloseCheckPointDetailDTO.eqAsmbDesc":"description"});
	asmbDescAc.setAcConditionMap({
 	   "equip_id":""
 	   });
	asmbDescAc.setTable("TAEQASMB");
	asmbDescAc.setAcResultMap({
        "mgrWorkCloseCheckPointDetailDTO.eqAsmbId":"eqasmb_id"
    });
	asmbDescAc.setKeyName("mgrWorkCloseCheckPointDetailDTO.eqAsmbId");
	asmbDescAc.init();
    
    //수치/판정 AC
    acSysDesc("mgrWorkCloseCheckPointDetailDTO.checkTypeDesc","mgrWorkCloseCheckPointDetailDTO.checkType","CHECK_TYPE",true);
    //사용여부
    acSysDesc("mgrWorkCloseCheckPointDetailDTO.isActive","mgrWorkCloseCheckPointDetailDTO.isActive","IS_USE",true);
    
    //수치/판정 hidden control
    checkTypeControl($("input[name='mgrWorkCloseCheckPointDetailDTO.checkType']").val());
}

function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "mgrWorkCloseCheckPointDetailDTO.checkTypeDesc") 
	{
		checkTypeControl(rtnJsonArry[0].CDSYSD_NO);
	}
}

/*
 * 수치/판정 hidden control
 */
function checkTypeControl(checkType)
{
	var checkMinObj 	= $("input[name='mgrWorkCloseCheckPointDetailDTO.checkMin']");		 // 하한값
	var checkBasisObj	= $("input[name='mgrWorkCloseCheckPointDetailDTO.checkBasisVal']");  // 기준값
	var checkMaxObj		= $("input[name='mgrWorkCloseCheckPointDetailDTO.checkMax']");		 // 상한값
	var uomObj			= $("input[name='mgrWorkCloseCheckPointDetailDTO.uom']");		     // 단위

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

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQASTWRK_POINT_ID',"WRK");
    
    //수치판정 구분  SEN - 판정
	mgrWorkCloseCheckPointDetailForm.elements['mgrWorkCloseCheckPointDetailDTO.checkTypeDesc'].value = "SEN";
	valSysDir('mgrWorkCloseCheckPointDetailDTO.checkType', 'mgrWorkCloseCheckPointDetailDTO.checkTypeDesc', 'CHECK_TYPE', true);
	
    mgrWorkCloseCheckPointDetailForm.elements['mgrWorkCloseCheckPointDetailDTO.isActive'].value = "Y";
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
		mgrWorkCloseCheckPointDetailForm.elements['mgrWorkCloseCheckPointDetailDTO.stWrkPointId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) mgrWorkCloseCheckPointDetailForm.strutsAction.value = "<%=MgrWorkCloseCheckPointDetailAction.STD_DETAIL_INPUT%>";
    else mgrWorkCloseCheckPointDetailForm.strutsAction.value = '<%=MgrWorkCloseCheckPointDetailAction.STD_DETAIL_UPDATE%>'; 
    
    var actionUrl = contextPath + "/mgrWorkCloseCheckPointDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(mgrWorkCloseCheckPointDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow)	parent.findGridRow(mgrWorkCloseCheckPointDetailForm.elements['mgrWorkCloseCheckPointDetailDTO.stWrkPointId'].value);
     setTitle("mgrWorkCloseCheckPointDetailDTO.stepNum","mgrWorkCloseCheckPointDetailDTO.checkPoint");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 
 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = maEqMstrDetailForm.elements['mgrWorkCloseCheckPointDetailDTO.stWrkPointId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/mgrWorkCloseCheckPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="mgrWorkCloseCheckCommonDTO.stwrkId"/>
	<html:hidden property="mgrWorkCloseCheckPointDetailDTO.stWrkPointId"/>
	<html:hidden property="mgrWorkCloseCheckPointDetailDTO.checkType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 점검순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_box">
					<html:text property="mgrWorkCloseCheckPointDetailDTO.stepNum" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_box">
					<html:text property="mgrWorkCloseCheckPointDetailDTO.checkMethod" tabindex="30"/>
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_box">
					<html:text property="mgrWorkCloseCheckPointDetailDTO.checkPoint" tabindex="50"/>
				</div>
			</div>
			
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_box">
					<html:text property="mgrWorkCloseCheckPointDetailDTO.fitBasis" tabindex="60"/>
				</div>
			</div>
			<!-- 수치판정구분 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkType"/></label>
				<div class="input_box">
					<html:text property="mgrWorkCloseCheckPointDetailDTO.checkTypeDesc" tabindex="70" />
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
						<html:text property="mgrWorkCloseCheckPointDetailDTO.checkMin" tabindex="80" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="mgrWorkCloseCheckPointDetailDTO.checkBasisVal" tabindex="82" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="mgrWorkCloseCheckPointDetailDTO.checkMax" tabindex="84" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="mgrWorkCloseCheckPointDetailDTO.uom" tabindex="86" />
					</div>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="mgrWorkCloseCheckPointDetailDTO.isActive" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			
			<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="mgrWorkCloseCheckPointDetailDTO.remark"
							tabindex="200" />
					</div>
				</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>

