<%--===========================================================================
작업요청서 - 처리사항
author  kim21017
version $Id: maWoReqResDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.req.work.action.MaWoReqResDetailAction"%>
<html>
<head>
<!-- 일자-->
<title><bean:message key="LABEL.date"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
 
 var resStatusAc;
 var mainMngAc;
 var deptAc;
 
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maWoReqResDetailDTO.resDate"); 
	setForUpdate();
	
	
	/**상태  */
	acSysDesc("maWoReqResDetailDTO.resStatusDesc","maWoReqResDetailDTO.resStatusId","WORES_STATUS",true);
	
	
	/**작업자  */
	mainMngAc = new autoC({"maWoReqResDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maWoReqResDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maWoReqResDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    /**부서  */
    deptAc = new autoC({"maWoReqResDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maWoReqResDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maWoReqResDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOREQRES_ID');

	maWoReqResDetailForm.elements['maWoReqResDetailDTO.resDate'].value   = getToday();
	maWoReqResDetailForm.elements['maWoReqResDetailDTO.empId'].value   = loginUser.empId;
	maWoReqResDetailForm.elements['maWoReqResDetailDTO.deptId'].value   = loginUser.deptId;
	maWoReqResDetailForm.elements['maWoReqResDetailDTO.empDesc'].value   = loginUser.empName;
	maWoReqResDetailForm.elements['maWoReqResDetailDTO.deptDesc'].value   = loginUser.deptDesc;
}

function setSequenceVal(sequenceVal)
{
	maWoReqResDetailForm.elements['maWoReqResListDTO.woReqResId'].value = sequenceVal;
	maWoReqResDetailForm.elements['maWoReqResDetailDTO.woReqResId'].value = sequenceVal;
}

function goTabPage(pageId)
{
	var form = document.maWoReqResDetailForm;

	if(pageId == "maDocLibList" || pageId == "maWoDocLibList"|| pageId == "maWoReqResDocLibList")
	{	
		maWoReqResDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWoReqResDetailForm.elements['maWoReqResListDTO.woReqResId'].value;
		maWoReqResDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOREQRES"; 
		//maWoReqResDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//maWoReqResDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
		maWoReqResDetailForm.elements['maDocLibCommonDTO.description'].value = 
			maWoReqResDetailForm.elements['maWoReqResDetailDTO.resDate'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);  
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(ckCreate(currentPageId)) maWoReqResDetailForm.strutsAction.value = '<%=MaWoReqResDetailAction.DETAIL_INPUT%>';
	else maWoReqResDetailForm.strutsAction.value = '<%= MaWoReqResDetailAction.DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maWoReqResDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoReqResDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoReqResDetailForm.elements['maWoReqResDetailDTO.woReqResId'].value,maWoReqResDetailForm.elements['maWoReqResDetailDTO.resStatusId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoReqResDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
	<html:hidden property="maWoReqResListDTO.woReqResId"/>
	<html:hidden property="maWoReqResDetailDTO.woReqResId"/>
	<html:hidden property="maWoReqResDetailDTO.empId"/>
	<html:hidden property="maWoReqResDetailDTO.deptId"/>
	<html:hidden property="maWoReqResDetailDTO.resStatusId"/>
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maWoReqResDetailDTO.woreqresMethod" value="EM"/> <!-- 간편조치 -->
 	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.date"/></label>
				<div class="input_box">
					<html:text property="maWoReqResDetailDTO.resDate" tabindex="10" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.status"/></label>
				<div class="input_box">
					<html:text property="maWoReqResDetailDTO.resStatusDesc" tabindex="20" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woCraft"/></label>
				<div class="input_box">
					<html:text property="maWoReqResDetailDTO.empDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
					<html:text property="maWoReqResDetailDTO.deptDesc" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 요청내용 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woRemark"/></label>
				<div class="input_box">
					<html:textarea property="maWoReqResDetailDTO.response" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>