 <%--===========================================================================
Program Error Detail
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.error.action.ConsultPgmErrorDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.PGMGUIDE' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var isUseAc;
var eqStatusAc;
var deptAc;
var eqLocAc;
var eqCtgAc;
var equipAc;
var empAc;
var plantAc;
var partAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) return;
	else goUpdate();
	
    setTitle("consultPgmErrorDetailDTO.errorLogNo");
    
    //For Save
    setForUpdate();
    
    //사용여부
    acSysDesc("consultPgmErrorDetailDTO.checkYn","consultPgmErrorDetailDTO.checkYnId","IS_USE", true);
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
    
    if("Y"==consultPgmErrorDetailForm.elements['consultPgmErrorDetailDTO.checkYnId'].value)
    {
    	consultPgmErrorDetailForm.elements['consultPgmErrorDetailDTO.checkDate'].value = getNowDateTime(true);  
    }
    else
   	{
    	consultPgmErrorDetailForm.elements['consultPgmErrorDetailDTO.checkDate'].value = "";
   	}
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) return;
    else consultPgmErrorDetailForm.strutsAction.value = "<%=ConsultPgmErrorDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/consultPgmErrorDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(consultPgmErrorDetailForm),
				'afterSave');

	}

	/**
	 * 저장후 호출
	 */
	function afterSave(ajaxXmlDoc) {
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc))
			return;
		//=====================================
		if (parent.findGridRow)
			parent
					.findGridRow(consultPgmErrorDetailForm.elements['consultPgmErrorDetailDTO.errorLogId'].value);

		consultPgmErrorDetailForm.elements['consultPgmErrorCommonDTO.errorLogId'].value = consultPgmErrorDetailForm.elements['consultPgmErrorDetailDTO.errorLogId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("consultPgmErrorDetailDTO.errorLogNo");

	}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultPgmErrorDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="consultPgmErrorCommonDTO.errorLogId" /><!-- Key -->
		<html:hidden property="consultPgmErrorDetailDTO.errorLogId" /><!-- Key -->
		<html:hidden property="consultPgmErrorDetailDTO.checkYnId" />
		<div class="article_box">
			<div class="form_box">
				<!-- Error # -->
				<div class="field">
					<label><bean:message key="LABEL.errorNo" /></label>
					<div class="input_read">
						<html:text property="consultPgmErrorDetailDTO.errorLogNo"
							readonly="true" />
					</div>
				</div>
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.date" /></label>
					<div class="input_read">
						<html:text property="consultPgmErrorDetailDTO.errorDate"
							readonly="true" />
					</div>
				</div>
				<!-- 회사 / UserNo -->
				<div class="field">
					<label><bean:message key="LABEL.compUser" /></label>
					<div class="input_read">
						<html:text property="consultPgmErrorDetailDTO.compUser"
							readonly="true" />
					</div>
				</div>
				<!-- url -->
				<div class="field">
					<label><bean:message key="LABEL.url" /></label>
					<div class="input_read">
						<html:text property="consultPgmErrorDetailDTO.url"
							readonly="true" />
					</div>
				</div>
				<!-- 확인여부 -->
				<div class="field">
					<label><bean:message key="LABEL.check" /></label>
					<div class="input_box">
						<html:text property="consultPgmErrorDetailDTO.checkYn"
							tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 확인일자 -->
				<div class="field">
					<label><bean:message key="LABEL.apprDate" /></label>
					<div class="input_read">
						<html:text property="consultPgmErrorDetailDTO.checkDate"
							readonly="true" />
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea property="consultPgmErrorDetailDTO.remark" styleClass="ta50"
							tabindex="110" />
					</div>
				</div>
				<!-- Error log -->
				<div class="field_long">
					<label><bean:message key="LABEL.errorLog" /></label>
					<div class="input_read">
						<html:textarea property="consultPgmErrorDetailDTO.errorlog" styleClass="ta150" 
							readonly="true" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
