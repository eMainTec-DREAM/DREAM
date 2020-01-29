<%--===========================================================================
작업요청서 - 처리사항
author  kim21017
version $Id: reqWorkResDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@ page import="dream.req.work.action.ReqWorkResDetailAction"%>
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

	setTitle("reqWorkResDetailDTO.resDate");
	setForUpdate();

	
	//상태
	acSysDesc("reqWorkResDetailDTO.resStatusDesc","reqWorkResDetailDTO.resStatusId","WORES_STATUS",true);
	
	/**작업자*/
	mainMngAc = new autoC({"reqWorkResDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("reqWorkResDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "reqWorkResDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();

    /** 부서  */
    deptAc = new autoC({"reqWorkResDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("reqWorkResDetailDTO.deptId");
    deptAc.setAcResultMap({
        "reqWorkResDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOREQRES_ID');

	reqWorkResDetailForm.elements['reqWorkResDetailDTO.resDate'].value   = getToday();
	reqWorkResDetailForm.elements['reqWorkResDetailDTO.empId'].value   = loginUser.empId;
	reqWorkResDetailForm.elements['reqWorkResDetailDTO.deptId'].value   = loginUser.deptId;
	reqWorkResDetailForm.elements['reqWorkResDetailDTO.empDesc'].value   = loginUser.empName;
	reqWorkResDetailForm.elements['reqWorkResDetailDTO.deptDesc'].value   = loginUser.deptDesc;
}

function setSequenceVal(sequenceVal)
{
	reqWorkResDetailForm.elements['reqWorkResListDTO.woReqResId'].value = sequenceVal;
	reqWorkResDetailForm.elements['reqWorkResDetailDTO.woReqResId'].value = sequenceVal;
}

function goTabPage(pageId)
{
	var form = document.reqWorkResDetailForm;

	if(pageId == "maDocLibList" || pageId == "maWoDocLibList"|| pageId == "reqWorkResDocLibList")
	{
		reqWorkResDetailForm.elements['maDocLibCommonDTO.objectId'].value = reqWorkResDetailForm.elements['reqWorkResListDTO.woReqResId'].value;
		reqWorkResDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOREQRES";
		//reqWorkResDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//reqWorkResDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
		reqWorkResDetailForm.elements['maDocLibCommonDTO.description'].value =
			reqWorkResDetailForm.elements['reqWorkResDetailDTO.resDate'].value;  //제목
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
	if(ckCreate(currentPageId)) reqWorkResDetailForm.strutsAction.value = '<%=ReqWorkResDetailAction.DETAIL_INPUT%>';
	else reqWorkResDetailForm.strutsAction.value = '<%= ReqWorkResDetailAction.DETAIL_UPDATE %>';

	var actionUrl = contextPath + "/reqWorkResDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(reqWorkResDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(reqWorkResDetailForm.elements['reqWorkResDetailDTO.woReqResId'].value,reqWorkResDetailForm.elements['reqWorkResDetailDTO.resStatusId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" >
<html:form action="/reqWorkResDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="reqWorkCommonDTO.woReqId"/><!-- Key -->
	<html:hidden property="reqWorkResListDTO.woReqResId"/>
	<html:hidden property="reqWorkResDetailDTO.woReqResId"/>
	<html:hidden property="reqWorkResDetailDTO.empId"/>
	<html:hidden property="reqWorkResDetailDTO.deptId"/>
	<html:hidden property="reqWorkResDetailDTO.resStatusId"/>
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="reqWorkResDetailDTO.woreqresMethod" value="EM"/> <!-- 간편조치 -->

	<!-- searchbox 박스 Line -->
	<div class="article_box">
			<!-- 일자 -->
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.date"/></label>
				<div class="input_box">
					<html:text property="reqWorkResDetailDTO.resDate" tabindex="10" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.status"/></label>
				<div class="input_box">
					<html:text property="reqWorkResDetailDTO.resStatusDesc" tabindex="20" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woCraft"/></label>
				<div class="input_box">
					<html:text property="reqWorkResDetailDTO.empDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
					<html:text property="reqWorkResDetailDTO.deptDesc" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 요청내용 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woRemark"/></label>
				<div class="input_box">
					<html:textarea property="reqWorkResDetailDTO.response" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html>