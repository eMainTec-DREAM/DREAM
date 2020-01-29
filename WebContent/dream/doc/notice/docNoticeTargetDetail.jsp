<%--===========================================================================
공지대상
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.notice.action.DocNoticeTargetDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 공지대상 -->
<title><bean:message key='LABEL.prompt'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var empAc;

function loadPage() 
{
	docNoticeTargetDetailForm.elements['docNoticeTargetDetailDTO.noticeId'].value = 
		docNoticeTargetDetailForm.elements['docNoticeCommonDTO.noticeId'].value;
	
    setTitle("docNoticeTargetDetailDTO.targetDeptDesc","docNoticeTargetDetailDTO.targetDesc");
    
    empAc = new autoC({"docNoticeTargetDetailDTO.targetDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "docNoticeTargetDetailDTO.targetId":"emp_id"
      , "docNoticeTargetDetailDTO.targetDeptId":"dept_id"
      , "docNoticeTargetDetailDTO.targetDeptDesc":"deptDesc"
      , "docNoticeTargetDetailDTO.targetPlantId":"plant"
    });
    empAc.init();
	
    
    //For Save
    setForUpdate();
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQANOTIUSR_ID');
    
    empAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    docNoticeTargetDetailForm.elements['docNoticeTargetDetailDTO.notiUsrId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) docNoticeTargetDetailForm.strutsAction.value = "<%=DocNoticeTargetDetailAction.DETAIL_INPUT%>";
    else docNoticeTargetDetailForm.strutsAction.value = "<%=DocNoticeTargetDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/docNoticeTargetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(docNoticeTargetDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(docNoticeTargetDetailForm.elements['docNoticeTargetDetailDTO.notiUsrId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("docNoticeTargetDetailDTO.deptNo","docNoticeTargetDetailDTO.deptDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = docNoticeTargetDetailForm.elements['docNoticeTargetDetailDTO.notiUsrId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/docNoticeTargetDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="docNoticeCommonDTO.noticeId"/><!-- Key -->
<html:hidden property="docNoticeTargetDetailDTO.notiUsrId"/><!-- Key -->
<html:hidden property="docNoticeTargetDetailDTO.noticeId"/> 
<html:hidden property="docNoticeTargetDetailDTO.targetId"/> 
<html:hidden property="docNoticeTargetDetailDTO.targetDeptId"/> 
<html:hidden property="docNoticeTargetDetailDTO.readYnId"/> 
<html:hidden property="docNoticeTargetDetailDTO.targetPlantId"/> 
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 사원명 -->
			<div class="field">
				<label><bean:message key="LABEL.empName"/></label>
				<div class="input_box">
					<html:text property="docNoticeTargetDetailDTO.targetDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부서명 -->
			<div class="field">
				<label><bean:message key="LABEL.deptDesc"/></label>
				<div class="input_read">
					<html:text property="docNoticeTargetDetailDTO.targetDeptDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<!-- 확인일자 -->
			<div class="field">
				<label><bean:message key="LABEL.apprDate"/></label>
				<div class="input_read">
					<html:text property="docNoticeTargetDetailDTO.noticeReadDate" tabindex="30" readonly="true" />
				</div>
			</div>
			<!-- 확인여부 -->
			<div class="field">
				<label><bean:message key="LABEL.check"/></label>
				<div class="input_read">
					<html:text property="docNoticeTargetDetailDTO.readYnDesc" tabindex="40" readonly="true" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
