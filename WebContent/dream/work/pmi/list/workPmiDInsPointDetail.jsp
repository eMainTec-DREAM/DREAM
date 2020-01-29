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
<%@ page import="dream.work.pm.list.action.WorkPmiDInsPointListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiDInsPointDetailAction" %>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html:html>
<head>
<!-- 점검 디테일 -->
<title><bean:message key='LABEL.pm' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

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
<!-- //

/** 자동완성 변수 */

var partAc, stdCheckPointAc;

function loadPage() 
{	
	setSlideImage();
	
	if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setDisable(document.getElementById("disableDiv"));
    
    setTitle("workPmiDInsPointDetailDTO.checkPoint");
    
    //For Save
    setForUpdate();
    
    // 점검결과 자동완성
    acSysDesc("workPmiDInsPointDetailDTO.pmPointRsltStatus","workPmiDInsPointDetailDTO.pmPointRsltStatusId","PM_POINT_RSLT_STATUS", true);
    
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

    if(ckCreate(currentPageId)) workPmiDInsPointDetailForm.strutsAction.value = "<%=WorkPmiDInsPointDetailAction.DETAIL_INPUT%>";
    else workPmiDInsPointDetailForm.strutsAction.value = "<%=WorkPmiDInsPointDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/workPmiDInsPointDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workPmiDInsPointDetailForm),'afterSave');
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
        parent.findGridRow(workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmInsDPointId'].value);
    }
    workPmiDInsPointDetailForm.elements['workPmiDInsCommonDTO.pmInsDPointId'].value = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmInsDPointId'].value;
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("workPmiDInsPointDetailDTO.checkPoint");

}

function checkRsltValue()
{
	var checkType = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.checkTypeId'].value;

	if(checkType == "VAL")
	{
		var resultVal = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.resultValue'].value;
		var resultVal2 = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.resultValue2'].value;
		var resultVal3 = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.resultValue3'].value;
		
		var checkMin = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.checkMin'].value;
		var checkMax = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.checkMax'].value;

		var pmPointRsltStatus = "GD";
		
		if(Number(resultVal) != 0 && resultVal != null
				&& (Number(resultVal) < Number(checkMin) || Number(resultVal) > Number(checkMax)))
		{
			pmPointRsltStatus = "BD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatusId'].value = "BD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatus'].value = "이상";

			valSysDirCode("workPmiDInsPointDetailDTO.pmPointRsltStatusId","workPmiDInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
			
		}
		if(Number(resultVal2) != 0 && resultVal2 != null
				&& (Number(resultVal2) < Number(checkMin) || Number(resultVal2) > Number(checkMax)))
		{
			pmPointRsltStatus = "BD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatusId'].value = "BD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatus'].value = "이상";

			valSysDirCode("workPmiDInsPointDetailDTO.pmPointRsltStatusId","workPmiDInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
			
		}
		if(Number(resultVal3) != 0 && resultVal3 != null
				&& (Number(resultVal3) < Number(checkMin) || Number(resultVal3) > Number(checkMax)))
		{
			pmPointRsltStatus = "BD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatusId'].value = "BD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatus'].value = "이상";

			valSysDirCode("workPmiDInsPointDetailDTO.pmPointRsltStatusId","workPmiDInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
			
		}
		if(pmPointRsltStatus != "BD")
		{
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatusId'].value = "GD";
			workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmPointRsltStatus'].value = "정상";

			valSysDirCode("workPmiDInsPointDetailDTO.pmPointRsltStatusId","workPmiDInsPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS", "", true);
		    
		}
	}
}


function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.workPmiDInsPointDetailForm;

	if(pageId == "maDocLibList" || pageId == "workPmiDInsPointDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['workPmiDInsPointDetailDTO.pmInsDPointId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "PM_DAY_POINT";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['workPmiDInsPointDetailDTO.checkPoint'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workPmiDInsPointDetailForm.elements['workPmiDInsPointDetailDTO.pmInsDPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

function loadSlideImages()
{
	var url = contextPath + "/workPmiDInsPointDetail.do";

    var oldSAction = workPmiDInsPointDetailForm.elements['strutsAction'].value;
    workPmiDInsPointDetailForm.elements['strutsAction'].value = '<%=WorkPmiDInsPointDetailAction.DETAIL_PHOTO%>'

    $.post(url,FormQueryString(workPmiDInsPointDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    workPmiDInsPointDetailForm.elements['strutsAction'].value = oldSAction;
}



//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workPmiDInsPointDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="workPmiDInsCommonDTO.pmInsDPointId" />          <!-- Key -->
        <html:hidden property="workPmiDInsCommonDTO.pmInsDListId" />          
        <html:hidden property="workPmiDInsPointDetailDTO.pmInsDPointId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.pmInsDListId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.checkPointId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.pmId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.eqAsmbId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.checkMethodId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.checkTypeId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.stwrkPointId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.isActiveId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.pmPointRsltStatusId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.stwrkPointId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.pmPointId" />    
        <html:hidden property="workPmiDInsPointDetailDTO.checkMin" />    
        <html:hidden property="workPmiDInsPointDetailDTO.checkMax" />    
        
        <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
		<html:hidden property="maDocLibCommonDTO.objectType" />
		<html:hidden property="maDocLibCommonDTO.description" />
        
        <div class="article_box">
            <div class="form_box">
            	<!-- 사진 -->
				<div class="field_img">
					<label><bean:message key="LABEL.photo"/></label>
					<div class="img_box" name="workPmiDInsPointDetailDTO.imgslide">
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
                <div class="field" id="disableDiv">
                    <label><bean:message key="LABEL.pmStepNum"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.stepNum" tabindex="10" styleClass="num" readonly="true"/>
                    </div>
                </div>
                <!-- 점검부위 -->
                <div class="field">
                    <label><bean:message key="LABEL.pmAsmb"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.eqAsmbDesc" tabindex="20" />
                    </div>
                </div>
                <!-- 점검항목 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkPoint"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.checkPoint" tabindex="30" readonly="true"/>
                    </div>
                </div>
                <!-- 점검방법 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkMethod"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.checkMethod" tabindex="40" readonly="true"/>
                    </div>
                </div>
                <!-- 적정기준 -->
                <div class="field">
                    <label><bean:message key="LABEL.fitBasis"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.fitBasis" tabindex="50" readonly="true"/>
                    </div>
                </div>
                <!-- 수치/판정 구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkType"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.checkTypeDesc" tabindex="60" readonly="true"/>
                    </div>
                </div>
                <!-- 설정값/단위 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkValUom"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.checkValUom" tabindex="60" readonly="true"/>
                    </div>
                </div>
                <!-- 시행여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isActive"/></label>
                    <div class="input_read">
                        <html:text property="workPmiDInsPointDetailDTO.isActive" tabindex="70" readonly="true"/>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_read">
                        <html:textarea property="workPmiDInsPointDetailDTO.remark" styleClass="ta50" tabindex="80"  readonly="true"/>
                    </div>
                </div>
                <!-- 표준점검항목 -->
	            <div class="field">
	                <label><bean:message key="LABEL.stdPmCheckPoint"/></label>
	                <div class="input_read">
	                    <html:text property="workPmiDInsPointDetailDTO.stwrkPointDesc" readonly="true" />
	                </div>
	            </div>
                <!-- 점검결과 -->
                <div class="field">
                    <label><bean:message key="LABEL.rsltStatusDesc"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsPointDetailDTO.pmPointRsltStatus" tabindex="90"/>
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
	                        <html:text property="workPmiDInsPointDetailDTO.resultValue" tabindex="92" 
	                        	onblur="javascript:checkRsltValue();" styleClass="ty_num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="workPmiDInsPointDetailDTO.resultValue2" tabindex="94" 
	                        	onblur="javascript:checkRsltValue();" styleClass="ty_num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="workPmiDInsPointDetailDTO.resultValue3" tabindex="96" 
	                        	onblur="javascript:checkRsltValue();" styleClass="ty_num"/>
	                    </div>
                    </div>
                </div>
                <!-- 검사세부내용 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.checkDetailRemark"/></label>
                    <div class="input_box">
                        <html:textarea property="workPmiDInsPointDetailDTO.insDetail" styleClass="ta50" tabindex="100" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
