<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsPointListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsPointDetailAction" %>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>

<html:html>
<head>
<!-- 점검 디테일 -->
<title><bean:message key='LABEL.pm' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var partAc, stdCheckPointAc;

function loadPage() 
{	
	if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setDisable(document.getElementById("disableDiv"));
    
    setTitle("workPmiCInsPointDetailDTO.checkPoint");
    
    //For Save
    setForUpdate();
    
    // 점검결과 자동완성
    acSysDesc("workPmiCInsPointDetailDTO.pmPointRsltStatus","workPmiCInsPointDetailDTO.pmPointRsltStatusId","PM_POINT_RSLT_STATUS", true);
    
}
/**
 * 수정
 */
function goUpdate()
{
    setEnableAll();
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

    if(ckCreate(currentPageId)) workPmiCInsPointDetailForm.strutsAction.value = "<%=WorkPmiCInsPointDetailAction.DETAIL_INPUT%>";
    else workPmiCInsPointDetailForm.strutsAction.value = "<%=WorkPmiCInsPointDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/workPmiCInsPointDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workPmiCInsPointDetailForm),'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow) {
        parent.findGridRow(workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmInsDPointId'].value);
    }
    workPmiCInsPointDetailForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmInsDPointId'].value;
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("workPmiCInsPointDetailDTO.checkPoint");

}

function checkRsltValue()
{
	var checkType = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.checkTypeId'].value;

	if(checkType == "VAL")
	{
		var resultVal = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.resultValue'].value;
		var resultVal2 = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.resultValue2'].value;
		var resultVal3 = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.resultValue3'].value;
		
		var checkMin = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.checkMin'].value;
		var checkMax = workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.checkMax'].value;
		
		var pmPointRsltStatus = "GD";

		if(Number(resultVal) != 0 && resultVal != null
				&& (Number(resultVal) < Number(checkMin) || Number(resultVal) > Number(checkMax)))
		{
			pmPointRsltStatus = "BD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatusId'].value = "BD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatus'].value = "이상";

			valSysDirCode("workPmiCInsPointDetailDTO.pmPointRsltStatusId","workPmiCInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
			
		}
		if(Number(resultVal2) != 0 && resultVal2 != null
				&& (Number(resultVal2) < Number(checkMin) || Number(resultVal2) > Number(checkMax)))
		{
			pmPointRsltStatus = "BD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatusId'].value = "BD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatus'].value = "이상";

			valSysDirCode("workPmiCInsPointDetailDTO.pmPointRsltStatusId","workPmiCInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
			
		}
		if(Number(resultVal3) != 0 && resultVal3 != null
				&& (Number(resultVal3) < Number(checkMin) || Number(resultVal3) > Number(checkMax)))
		{
			pmPointRsltStatus = "BD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatusId'].value = "BD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatus'].value = "이상";

			valSysDirCode("workPmiCInsPointDetailDTO.pmPointRsltStatusId","workPmiCInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
			
		}
		if(pmPointRsltStatus != "BD")
		{
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatusId'].value = "GD";
			workPmiCInsPointDetailForm.elements['workPmiCInsPointDetailDTO.pmPointRsltStatus'].value = "정상";

			valSysDirCode("workPmiCInsPointDetailDTO.pmPointRsltStatusId","workPmiCInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
		    
		}
	}
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.workPmiCInsPointDetailForm;

	if(pageId == "maDocLibList" || pageId == "workPmiCInsPointDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['workPmiCInsPointDetailDTO.pmInsDPointId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "PM_PART_POINT";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['workPmiCInsPointDetailDTO.checkPoint'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workPmiCInsPointDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="workPmiCInsCommonDTO.pmInsDPointId" />          <!-- Key -->
        <html:hidden property="workPmiCInsCommonDTO.pmInsDListId" />          
        <html:hidden property="workPmiCInsPointDetailDTO.pmInsDPointId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.pmInsDListId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.checkPointId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.pmId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.eqAsmbId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.checkMethodId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.checkTypeId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.stwrkPointId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.isActiveId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.pmPointRsltStatusId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.stwrkPointId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.pmPointId" />    
        <html:hidden property="workPmiCInsPointDetailDTO.checkMin" />    
        <html:hidden property="workPmiCInsPointDetailDTO.checkMax" />    
        
        <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
		<html:hidden property="maDocLibCommonDTO.objectType" />
		<html:hidden property="maDocLibCommonDTO.description" />
        
        <div class="article_box">
            <div class="form_box">
                <!-- 점검순서 -->
                <div class="field" id="disableDiv">
                    <label><bean:message key="LABEL.pmStepNum"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.stepNum" tabindex="10" styleClass="num" readonly="true"/>
                    </div>
                </div>
                <!-- 점검부위 -->
                <div class="field">
                    <label><bean:message key="LABEL.pmAsmb"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.eqAsmbDesc" tabindex="20" />
                    </div>
                </div>
                <!-- 점검항목 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkPoint"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.checkPoint" tabindex="30" readonly="true"/>
                    </div>
                </div>
                <!-- 점검방법 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkMethod"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.checkMethod" tabindex="40" readonly="true"/>
                    </div>
                </div>
                <!-- 적정기준 -->
                <div class="field">
                    <label><bean:message key="LABEL.fitBasis"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.fitBasis" tabindex="50" readonly="true"/>
                    </div>
                </div>
                <!-- 수치/판정 구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkType"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.checkTypeDesc" tabindex="60" readonly="true"/>
                    </div>
                </div>
                <!-- 설정값/단위 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkValUom"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.checkValUom" tabindex="60" readonly="true"/>
                    </div>
                </div>
                <!-- 시행여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isActive"/></label>
                    <div class="input_read">
                        <html:text property="workPmiCInsPointDetailDTO.isActive" tabindex="70" readonly="true"/>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_read">
                        <html:textarea property="workPmiCInsPointDetailDTO.remark" styleClass="ta50" tabindex="80"  readonly="true"/>
                    </div>
                </div>
                <!-- 표준점검항목 -->
	            <div class="field">
	                <label><bean:message key="LABEL.stdPmCheckPoint"/></label>
	                <div class="input_read">
	                    <html:text property="workPmiCInsPointDetailDTO.stwrkPointDesc" readonly="true" />
	                </div>
	            </div>
                <!-- 점검결과 -->
                <div class="field">
                    <label><bean:message key="LABEL.rsltStatusDesc"/></label>
                    <div class="input_box">
                        <html:text property="workPmiCInsPointDetailDTO.pmPointRsltStatus" tabindex="90"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 점검값 -->
                <div class="field ty3">
                    <label><bean:message key="LABEL.resultVal"/></label>
                    <div class="multi_wrap">
	                    <div class="input_box">
	                        <html:text property="workPmiCInsPointDetailDTO.resultValue" tabindex="92" 
	                        	onblur="javascript:checkRsltValue();" styleClass="ty_num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="workPmiCInsPointDetailDTO.resultValue2" tabindex="94" 
	                        	onblur="javascript:checkRsltValue();" styleClass="ty_num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="workPmiCInsPointDetailDTO.resultValue3" tabindex="96" 
	                        	onblur="javascript:checkRsltValue();" styleClass="ty_num"/>
	                    </div>
                    </div>
                </div>
                <!-- 검사세부내용 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.checkDetailRemark"/></label>
                    <div class="input_box">
                        <html:textarea property="workPmiCInsPointDetailDTO.insDetail" styleClass="ta50" tabindex="100" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
