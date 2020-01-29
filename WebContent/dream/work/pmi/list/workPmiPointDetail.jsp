<%--===========================================================================
점검작업 점검
author  kim21017
version $Id: workPmiPointDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pmi.list.action.WorkPmiPointDetailAction"%>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html>
<head>
<!-- 점검항목-->
<title><bean:message key="LABEL.checkPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
var slideImage = new Array();
<%
if(list != null)
	for(int i= 0; list.size() > i ; i++)
	{
	    Map map = (Map)list.get(i);
%>
	slideImage.push('<%=(String)map.get("FILE_NAME")%>');
<% 
	}
%>	
<!--//

function loadPage() 
{
	setSlideImage();
	
	setTitle("workPmiPointDetailDTO.checkPoint");
	setForUpdate();

	// 점검결과 AC
    acSysDesc("workPmiPointDetailDTO.pmPointRsltStatusDesc","workPmiPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS",true);
}

function goSave(){
	workPmiPointDetailForm.strutsAction.value = '<%= WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmiPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmiPointDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    if (parent.goSearch)	parent.goSearch();
	
    getTopPage().afterSaveAll(currentPageId);
}

function checkRsltValue()
{
	var checkType = workPmiPointDetailForm.elements['workPmiPointDetailDTO.checkTypeId'].value;
	
	if(checkType == "VAL")
	{
		var resultVal = workPmiPointDetailForm.elements['workPmiPointDetailDTO.resultValue'].value;
		var checkVal = workPmiPointDetailForm.elements['workPmiPointDetailDTO.checkValUom'].value.split(" / ");
		var checkMin = checkVal[0];
		var checkMax = checkVal[2].split(" . ");
		checkMax = checkMax[0]

		if (Number(resultVal) != 0 && resultVal != null && Number(checkMin) != 0 && checkMin != null
				&& Number(resultVal) < Number(checkMin) ) {
			workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmPointRsltStatus'].value = "BD";
			workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmPointRsltStatusDesc'].value = '<bean:message key="CODESET.PM_POINT_RSLT_STATUS.BD"/>';

			valSysDir('workPmiPointDetailDTO.pmPointRsltStatus', 'workPmiPointDetailDTO.pmPointRsltStatusDesc', 'PM_POINT_RSLT_STATUS', true);
		}
		else if (Number(resultVal) != 0 && resultVal != null && Number(checkMax) != 0 && checkMax != null
				&& Number(resultVal) > Number(checkMax) ) {
			workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmPointRsltStatus'].value = "BD";
			workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmPointRsltStatusDesc'].value = '<bean:message key="CODESET.PM_POINT_RSLT_STATUS.BD"/>';

			valSysDir('workPmiPointDetailDTO.pmPointRsltStatus', 'workPmiPointDetailDTO.pmPointRsltStatusDesc', 'PM_POINT_RSLT_STATUS', true);
		}
		else
		{
			workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmPointRsltStatus'].value = "GD";
			workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmPointRsltStatusDesc'].value = '<bean:message key="CODESET.PM_POINT_RSLT_STATUS.GD"/>';

			valSysDir('workPmiPointDetailDTO.pmPointRsltStatus', 'workPmiPointDetailDTO.pmPointRsltStatusDesc', 'PM_POINT_RSLT_STATUS', true);
		}
	}
}


function goTabPage(pageId)
{
	var form = document.workPmiPointDetailForm;

	if(pageId == "maDocLibList" || pageId == "workPmiRInsPointDocLibList" || pageId == "workPmiRInsPointValueDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmInsPointId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "PM_ROUTINE_POINT";
		form.elements['maDocLibCommonDTO.description'].value = workPmiPointDetailForm.elements['workPmiPointDetailDTO.checkPoint'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}

/**
 * 저장후생성후 호출
 */
function afterSavenew()
{
	sequenceNextVal('SQAPM_POINT_ID');

    workPmListDInsPointDetailForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
    workPmListDInsPointDetailForm.elements['maPmMstrCommonDTO.pmId'].value = "";
	//점검순서, 점검항목
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.stepNum'].value = "";
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.checkPoint'].value = "";
}



/* audit Trail */
function goAudtrailLink()
{
	var objectId = workPmiPointDetailForm.elements['workPmiPointDetailDTO.pmInsPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

function loadSlideImages()
{
	var url = contextPath + "/workPmiPointDetail.do";

    var oldSAction = workPmiPointDetailForm.elements['strutsAction'].value;
    workPmiPointDetailForm.elements['strutsAction'].value = '<%=WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(workPmiPointDetailForm), function(_data){
    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    workPmiPointDetailForm.elements['strutsAction'].value = oldSAction;
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmiPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workPmiCommonDTO.pminslistId"/>
	<html:hidden property="workPmiPointDetailDTO.pmInsPointId"/>
	<html:hidden property="workPmiPointDetailDTO.pmPointId"/>
	<html:hidden property="workPmiPointDetailDTO.pmPointRsltStatus"/>
	<html:hidden property="workPmiPointDetailDTO.stwrkPointId"/>
	<html:hidden property="workPmiPointDetailDTO.checkTypeId"/>
	
	<html:hidden property="workPmiPointDetailDTO.equipId"/>
	<html:hidden property="workPmiPointDetailDTO.pmEquipId"/>
	
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
	<html:hidden property="maDocLibCommonDTO.objectType" />
	<html:hidden property="maDocLibCommonDTO.description" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 사진 -->
			<div class="field_img">
				<label><bean:message key="LABEL.photo"/></label>
				<div class="img_box" name="workPmiPointDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
				</div>
			</div>
			<!-- 점검순서 -->
			<div class="field">
				<label><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.stepNum" readonly="true" />
				</div>
			</div>
			<!-- 점검부위 -->
			<div class="field">
				<label><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.eqAsmbDesc" readonly="true" />
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field">
				<label><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.checkPoint" readonly="true" />
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.checkMethod" readonly="true" />
				</div>
			</div>
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.fitBasis" readonly="true" />
				</div>
			</div>
			<!-- 수치판정구분 -->
			<div class="field">
				<label><bean:message key="LABEL.checkType"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.checkTypeDesc" readonly="true" />
				</div>
			</div>
			<!-- 설정값/단위 -->
			<div class="field_long">
				<label><bean:message key="LABEL.checkValUom"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.checkValUom" readonly="true" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_read">
					<html:text property="workPmiPointDetailDTO.remark" readonly="true" />
				</div>
			</div>
			<!-- 표준점검항목 -->
            <div class="field">
                <label><bean:message key="LABEL.stdPmCheckPoint"/></label>
                <div class="input_read">
                    <html:text property="workPmiPointDetailDTO.stwrkPointDesc" readonly="true" />
                </div>
            </div>
			<!-- 점검결과  -->
			<div class="field">
				<label><bean:message key="LABEL.rsltStatusDesc"/></label>
				<div class="input_box">
					<html:text property="workPmiPointDetailDTO.pmPointRsltStatusDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검값 -->
			<div class="field">
				<label><bean:message key="LABEL.resultVal"/></label>
				<div class="input_box">
					<html:text property="workPmiPointDetailDTO.resultValue" tabindex="20" 
						onblur="javascript:checkRsltValue();" styleClass="ty_num" />
				</div>
			</div>
			<!-- 검사세부내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.checkDetailRemark"/></label>
				<div class="input_box">
					<html:textarea property="workPmiPointDetailDTO.resultRemark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>