<%--===========================================================================
자격증분류 - 상세
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.edu.list.action.EduDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 자격증분류 : 품번 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var creatorAc;
var mngDeptAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("eduDetailDTO.courseListNo", "eduDetailDTO.description");
	
	creatorAc = new autoC({"eduDetailDTO.empDesc":"emp_name"});
	creatorAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
	creatorAc.setAcDConditionMap({
    	"dept_id" : "eduDetailDTO.deptId"
    });
	creatorAc.setTable("TAEMP");
	creatorAc.setKeyName("eduDetailDTO.empId");
	creatorAc.setAcResultMap({
        "eduDetailDTO.empId":"emp_id"
    });
	creatorAc.init();
    
    mngDeptAc = new autoC({"eduDetailDTO.deptDesc":"description"});
    mngDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mngDeptAc.setTable("TADEPT");
    mngDeptAc.setKeyName("eduDetailDTO.deptId");
    mngDeptAc.setAcResultMap({
        "eduDetailDTO.deptId":"dept_id"
    });
    mngDeptAc.init();
	
    var courseTypeAc = new autoC({"eduDetailDTO.courseTypeDesc":"description"});
    courseTypeAc.setTable("TACDUSRD");
    courseTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"COURSE_TYPE"
  	   });
    courseTypeAc.setAcResultMap({
        "eduDetailDTO.courseType":"cdusrd_no"
    });
    courseTypeAc.init();

    
	//acSysDesc("eduDetailDTO.courseTypeDesc","eduDetailDTO.courseType","COURSE_TYPE", true);
	
	acSysDesc("eduDetailDTO.isUse","eduDetailDTO.isUse","IS_USE", true);
	   
	setForUpdate();
}
	
function goUpdate()
{
    //수정시 readonly설정 
    //eduDetailForm.elements['eduDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  

}

function goInput()
{ 
	eduDetailForm.elements['eduDetailDTO.deptId'].value    = "<%=loginUser.getDeptId()%>";
	eduDetailForm.elements['eduDetailDTO.deptDesc'].value  = "<%=loginUser.getDeptDesc()%>";
	//사원
	eduDetailForm.elements['eduDetailDTO.empId'].value     = "<%=loginUser.getEmpId()%>";
	eduDetailForm.elements['eduDetailDTO.empDesc'].value   = "<%=loginUser.getEmpName()%>";
	
	eduDetailForm.elements['eduDetailDTO.isUse'].value = 'Y'; 
	
	sequenceNextVal('SQACOURSELIST_ID');
}

function setSequenceVal(sequenceVal)
{
	eduDetailForm.elements['eduDetailDTO.courseListId'].value = sequenceVal;
	eduDetailForm.elements['eduDetailDTO.courseListNo'].value = sequenceVal;
	eduDetailForm.elements['eduCommonDTO.courseListId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) eduDetailForm.strutsAction.value = '<%=EduDetailAction.EDU_DETAIL_INPUT%>';
	else eduDetailForm.strutsAction.value = '<%=EduDetailAction.EDU_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/eduDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(eduDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	eduDetailForm.elements['eduDetailDTO.courseListId'].value = eduDetailForm.elements['eduCommonDTO.courseListId'].value;
	if (parent.findGridRow)	parent.findGridRow(eduDetailForm.elements['eduDetailDTO.courseListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("eduDetailDTO.courseListNo", "eduDetailDTO.description");
}
 
function goTabPage(pageId)
{
    var form = document.eduDetailForm;
        goCommonTabPage(form, '' , pageId);
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = eduDetailForm.elements['eduDetailDTO.courseListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/eduDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="eduCommonDTO.courseListId" />	
	<html:hidden property="eduDetailDTO.courseListId" />
	<html:hidden property="eduDetailDTO.courseType" />
	<html:hidden property="eduDetailDTO.empId" />
	<html:hidden property="eduDetailDTO.deptId" />
	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.number"/></label>
				<div id="ptRecListNoDiv" class="input_read">
					<html:text property="eduDetailDTO.courseListNo" tabindex="10" readonly="true" />
				</div>
			</div>
			<!-- 자격증 구분 -->
			<div class="field">
			    <label ><bean:message key="LABEL.courseType"/></label>
			    <div class="input_box">
			    	<html:text property="eduDetailDTO.courseTypeDesc" tabindex="40" />
			    	<p class="open_spop"><a><span>조회</span></a></p>
			    </div>
			</div>
			<div class="field_long">
               <label class="check"><bean:message key="LABEL.trainDesc"/></label>
               <div class="input_box">
                   <html:text property="eduDetailDTO.description" tabindex="60"/>
               </div>
            </div>
			<!-- 개설일자 -->
			<div class="field">
				<label><bean:message key="LABEL.creCourseDate"/></label>
				<div class="input_box">
					<html:text property="eduDetailDTO.creDate" tabindex="80" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="eduDetailDTO.isUse" tabindex="160"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.creCourseBy"/></label>
				<div class="input_box">
					<html:text property="eduDetailDTO.empDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label ><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="eduDetailDTO.deptDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
 			<!-- 교육내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.trainContents"/></label>
				<div class="input_box">
					<html:textarea property="eduDetailDTO.contents" styleClass="ta50" tabindex="190" />
				</div>
			</div>

		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>