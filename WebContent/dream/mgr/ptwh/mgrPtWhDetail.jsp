<%--===========================================================================
부품창고 Detail
author  sy.yang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 부품창고 -->
<title><bean:message key='LABEL.docCountrNo' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var isUseAc;

function loadPage() 
{
//	goTabPage("mgrPtWhEmpList");

    setTitle("mgrPtWhDetailDTO.wcode", "mgrPtWhDetailDTO.wname");
    
    //For Save
    setForUpdate();
	
    //사용여부 자동완성
    acSysDesc("mgrPtWhDetailDTO.isUse","mgrPtWhDetailDTO.isUse","IS_USE", true);
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	mgrPtWhDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value = mgrPtWhDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
	goCommonTabPage(mgrPtWhDetailForm, '', pageId);
    
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

	mgrPtWhDetailForm.elements['mgrPtWhDetailDTO.wcodeId'].value = mgrPtWhDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value;

	//strutsAction 셋팅.
	mgrPtWhDetailForm.strutsAction.value = "<%=MgrPtWhDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrPtWhDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrPtWhDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	mgrPtWhDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value = mgrPtWhDetailForm.elements['mgrPtWhDetailDTO.wcodeId'].value;
	
	if (parent.findGridRow)	parent.findGridRow(mgrPtWhDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value);	
	getTopPage().afterSaveAll(currentPageId);

	setTitle("mgrPtWhDetailDTO.wcode","mgrPtWhDetailDTO.wname");
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrPtWhDetailForm.elements['mgrPtWhDetailDTO.wcodeId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrPtWhDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrPtWhCommonDTO.wcodeId" /><!-- Key -->
		<html:hidden property="mgrPtWhDetailDTO.wcodeId" />

		<div class="article_box">
			<div class="form_box">
				<!-- 창고코드 -->
				<div class="field">
					<label><bean:message key="LABEL.wcode"/></label>
					<div class="input_read">
						<html:text property="mgrPtWhDetailDTO.wcode" tabindex="10" readonly="true"/>
					</div>
				</div>
				
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_read">
						<html:text property="mgrPtWhDetailDTO.plantDesc" tabindex="20" readonly="true" />
					</div>
				</div>
				
				<!-- 창고명 -->
				<div class="field">
					<label><bean:message key="LABEL.wname"/></label>
					<div class="input_read">
						<html:text property="mgrPtWhDetailDTO.wname" tabindex="30" readonly="true"/>
					</div>
				</div>
				<!-- 창고유형 -->
				<div class="field">
	        	 	<label><bean:message key="LABEL.whType"/></label> 
	        	 	<div class="input_read">
	 					<html:text property="mgrPtWhDetailDTO.whTypeDesc" tabindex="40" readonly="true"
	                    onblur="valSysDir('mgrPtWhDetailDTO.whType', 'mgrPtWhDetailDTO.whTypeDesc', 'WH_TYPE', true);"/>
	        	 	</div>
	        	 </div>
	        	 
				<div class="field">
					<!-- 사용여부 -->
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="mgrPtWhDetailDTO.isUse" tabindex="50"
							onblur="valYn('mgrPtWhDetailDTO.isUse', true);"/>
						<p class="open_spop">
							<a href="javascript:lovTable('mgrPtWhDetailDTO.isUse', 'mgrPtWhDetailDTO.isUse','YN');">
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
	        	 
				<!-- 내용 -->
				<div class="field_long">
					<label><bean:message key="LABEL.contents"/></label>
					<div class="input_box">
						<html:textarea property="mgrPtWhDetailDTO.remark" styleClass="ta50" tabindex="80" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
