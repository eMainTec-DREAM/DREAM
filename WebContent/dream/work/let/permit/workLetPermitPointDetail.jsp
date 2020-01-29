<%--===========================================================================
안전작업허가서유형 - 점검항목 상세
author  syyang
version $Id: workLetPermitPointDetail.jsp,v 1.1 2015/12/03 01:45:27 syyang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.permit.action.WorkLetPermitPointDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 점검항목 -->
<title><bean:message key='LABEL.order' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();

    setTitle("workLetPermitPointDetailDTO.stepNum", "workLetPermitPointDetailDTO.checkPoint");
    
    //For Save
    setForUpdate();
   
    //수치판정구분 자동완성
    acSysDesc("workLetPermitPointDetailDTO.checkTypeDesc","workLetPermitPointDetailDTO.checkTypeId","CHECK_TYPE",true);
    
    //점검결과 자동완성
    acSysDesc("workLetPermitPointDetailDTO.woLetRsltStatusDesc","workLetPermitPointDetailDTO.woLetRsltStatus","WOLET_RSLT_STATUS",true);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAWOLETLISTPOINT_ID');
}

function setSequenceVal(sequenceVal)
{
    workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetListPointId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
{
	
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
    if(ckCreate(currentPageId)){
    	workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.creTime'].value = getNowDateTime(true); 
    	workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.updTime'].value = getNowDateTime(true); 
		
    	workLetPermitPointDetailForm.strutsAction.value = "<%=WorkLetPermitPointDetailAction.DETAIL_INPUT%>";
    }
    else { 
    	workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.updTime'].value = getNowDateTime(true);
    	
    	workLetPermitPointDetailForm.strutsAction.value = "<%=WorkLetPermitPointDetailAction.DETAIL_UPDATE%>";
    }
    
	var actionUrl = contextPath + "/workLetPermitPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workLetPermitPointDetailForm),'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{ 
	//=====================================
	if(!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if(parent.findGridRow)
		parent.findGridRow(workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetListPointId'].value);

	workLetPermitPointDetailForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetListPointId'].value;
	
	getTopPage().afterSaveAll(currentPageId);
	
    setTitle("workLetPermitPointDetailDTO.stepNum", "workLetPermitPointDetailDTO.checkPoint");
}

function checkRsltValue()
{
	var checkType = workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.checkTypeId'].value;

	if(checkType == "VAL")
	{
		var resultVal = workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.resultValue'].value;
		
		var checkMin = workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.checkMin'].value;
		var checkMax = workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.checkMax'].value;

		if(Number(resultVal) != 0 && resultVal != null
				&& (Number(resultVal) < Number(checkMin) || Number(resultVal) > Number(checkMax)))
		{
			workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetRsltStatus'].value = "NG";
			workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetRsltStatusDesc'].value = "미확인(이상)";

			valSysDir('workLetPermitPointDetailDTO.woLetRsltStatus', 'workLetPermitPointDetailDTO.woLetRsltStatusDesc', 'WOLET_RSLT_STATUS', true);
		}
		else
		{
			workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetRsltStatus'].value = "GD";
			workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetRsltStatusDesc'].value = "확인(정상)";

			valSysDir('workLetPermitPointDetailDTO.woLetRsltStatus', 'workLetPermitPointDetailDTO.woLetRsltStatusDesc', 'WOLET_RSLT_STATUS', true);
		}
	}
}
	
/**
 *  Audit trail
 */
function goAudtrailLink()
{
   var objectId = workLetPermitPointDetailForm.elements['workLetPermitPointDetailDTO.woLetListPointId'].value;
   var fileName = currentPageId;

   if(typeof objectId=="undefined") return;

   goAudTrailList(objectId, fileName);
}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workLetPermitPointDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="workLetPermitDetailDTO.woLetListId"/>
		<html:hidden property="workLetPermitPointListDTO.woLetListPointId"/>	<!-- Key -->
		<html:hidden property="workLetPermitPointDetailDTO.woLetListPointId" />
		<html:hidden property="workLetPermitPointDetailDTO.checkTypeId" />
		<html:hidden property="workLetPermitPointDetailDTO.woLetRsltStatus" />
		<html:hidden property="workLetPermitPointDetailDTO.creTime" />
		<html:hidden property="workLetPermitPointDetailDTO.updTime" />
		<div class="article_box">
			<div class="form_box">
				<!-- 점검순서 -->
				<div class="field">
					<label><bean:message key="LABEL.pmStepNum" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.stepNum" tabindex="10" styleClass="num"/>
					</div>
				</div>
				<!-- 점검부위 -->
				<div class="field">
					<label><bean:message key="LABEL.pmAsmb" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkPosition" tabindex="20" />
					</div>
				</div>
				<!-- 점검항목 -->
				<div class="field">
					<label><bean:message key="LABEL.checkPoint" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkPoint" tabindex="30" />
					</div>
				</div>
				<!-- 점검방법 -->
				<div class="field">
					<label><bean:message key="LABEL.checkMethod" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkMethod" tabindex="40" />
					</div>
				</div>				
				<!-- 적정기준 -->
				<div class="field">
					<label><bean:message key="LABEL.fitBasis" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.fitBasis" tabindex="50" />
					</div>
				</div>				
				<!-- 수치/판정구분 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkType" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkTypeDesc" tabindex="60" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>				
					</div>
				</div>
				<!-- 하한값 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkMin" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkMin" tabindex="70" styleClass="num"/>
					</div>
				</div>
				<!-- 기준값 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkBasisVal" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkBasisVal" tabindex="80" styleClass="num"/>
					</div>
				</div>				
				<!-- 상한값 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkMax" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.checkMax" tabindex="90" styleClass="num"/>
					</div>
				</div>
				<!-- 단위 -->				
				<div class="field">
					<label><bean:message key="LABEL.uom" /></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.uom" tabindex="100"/>
					</div>
				</div>
                <!-- 점검결과 -->
                <div class="field">
                    <label><bean:message key="LABEL.rsltStatusDesc"/></label>
                    <div class="input_box">
                        <html:text property="workLetPermitPointDetailDTO.woLetRsltStatusDesc" tabindex="110"/>
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
				<!-- 점검값 -->
				<div class="field">
					<label><bean:message key="LABEL.resultVal"/></label>
					<div class="input_box">
						<html:text property="workLetPermitPointDetailDTO.resultValue" tabindex="120" onblur="javascript:checkRsltValue();" styleClass="num" />
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="workLetPermitPointDetailDTO.remark" styleClass="ta50" tabindex="150" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
