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

<%@ page import="dream.work.list.action.WorkListPointListAction" %>
<%@ page import="dream.work.list.action.WorkListPointDetailAction" %>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>

<html:html>
<head>
<!-- 점검항목 디테일 -->
<title><bean:message key='LABEL.pmComtents' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //
var empAc;
function loadPage() 
{	
	// 점검결과
    acSysDesc("workListPointDetailDTO.pmPointRsltStatusDesc","workListPointDetailDTO.pmPointRsltStatusId","PM_POINT_RSLT_STATUS");
	
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    workListPointDetailForm.elements['workListPointDetailDTO.pmPtrlRsltPointId'].value = workListPointDetailForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value;
    
    workListPointDetailForm.elements['workListPointDetailDTO.ptrlComDateTime'].value = workListPointDetailForm.elements['workListPointDetailDTO.ptrlComDate'].value + ' ' + workListPointDetailForm.elements['workListPointDetailDTO.ptrlComTime'].value;
    
    // 점검자
    empAc = new autoC({"workListPointDetailDTO.ptrlInspectorDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workListPointDetailDTO.ptrlInspectorId":"emp_id"
    });
    empAc.init();
    
    setDisable(document.getElementsByName("disableDiv"));
    
    setTitle("workListPointDetailDTO.ptrlAreaDesc", "workListPointDetailDTO.pmPointDesc");
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
    sequenceNextVal('SQAPMPTRLRSLTPOINT_ID');
}

function setSequenceVal(sequenceVal)
{
    workListPointDetailForm.elements['workListPointDetailDTO.pmPtrlRsltPointId'].value = sequenceVal;
    workListPointDetailForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = sequenceVal;
    
    workListPointDetailForm.elements['workListPointDetailDTO.ptrlComTime'].value = getToday()
}
/**
 * 수정
 */
function goUpdate()
{
}

/**
 * 요청
 */
 
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

    if(ckCreate(currentPageId)) workListPointDetailForm.strutsAction.value = "<%=WorkListPointDetailAction.DETAIL_INPUT%>";
    else workListPointDetailForm.strutsAction.value = "<%=WorkListPointDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/workListPointDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workListPointDetailForm),'afterSave');

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
                .findGridRow(workListPointDetailForm.elements['workListPointDetailDTO.pmPtrlRsltPointId'].value);

    
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workListPointDetailDTO.ptrlAreaDesc", "workListPointDetailDTO.pmPointDesc");

}

function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.workListPointDetailForm;

	if(pageId == "maDocLibList" || pageId == "workPmiPInsPointDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['workListPointDetailDTO.pmPtrlRsltPointId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "PM_PATROL_POINT";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['workListPointDetailDTO.pmPointDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}


/* audit Trail */
function goAudtrailLink()
{ 		
	var objectId = workListPointDetailForm.elements['workListPointDetailDTO.pmPtrlRsltPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workListPointDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="workListPointListDTO.pmPtrlRsltPointId" />          <!-- Key -->
        <html:hidden property="workListPointDetailDTO.pmPtrlRsltPointId" />          <!-- Key -->
        
        <html:hidden property="workListPointDetailDTO.pmPointRsltStatusId" />        <!-- 점검결과ID -->
        <html:hidden property="workListPointDetailDTO.pmPtrlRsltListId" />           <!-- PM 순회점검 결과 ID -->
        <html:hidden property="workListPointDetailDTO.ptrlInspectorId" />            <!-- 점검자ID -->
        <html:hidden property="workListPointDetailDTO.ptrlAreaId" />                 <!-- 순회지역ID(pmequip_id -->
        
        <html:hidden property="workListPointDetailDTO.ptrlComDate" />                 <!-- 순회지역ID(pmequip_id -->
        <html:hidden property="workListPointDetailDTO.ptrlComTime" />                 <!-- 순회지역ID(pmequip_id -->
        
        <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
		<html:hidden property="maDocLibCommonDTO.objectType" />
		<html:hidden property="maDocLibCommonDTO.description" />
        
        <div class="article_box">
            <div class="form_box">
                <!-- 순회지역명 -->
                <div class="field"  name="disableDiv">
                    <label><bean:message key="LABEL.ptrlSite"/></label>
                    <div class="input_box">
                        <html:text property="workListPointDetailDTO.ptrlAreaDesc" tabindex="10"/>
                    </div>
                </div>
                <!-- 점검내용 -->
                <div class="field_long" name="disableDiv">
                    <label><bean:message key="LABEL.pmContents"/></label>
                    <div class="input_box">
                        <html:text property="workListPointDetailDTO.pmPointDesc" tabindex="20"/>
                    </div>
                </div>
                <!-- 점검시간 -->
                <div class="field" name="disableDiv">
                    <label><bean:message key="LABEL.inspectDate"/></label>
                    <div class="input_box">
                        <html:text property="workListPointDetailDTO.ptrlComDateTime" tabindex="30"/>
                    </div>
                </div>
                <!-- 점검자 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.ptrlInspector"/></label>
                    <div class="input_box">
                        <html:text property="workListPointDetailDTO.ptrlInspectorDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 점검결과 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.rsltStatusDesc"/></label>
                    <div class="input_box">
                        <html:text property="workListPointDetailDTO.pmPointRsltStatusDesc" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="workListPointDetailDTO.remark" styleClass="ta50" tabindex="99" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
