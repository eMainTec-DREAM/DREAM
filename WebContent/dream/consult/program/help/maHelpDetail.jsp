<%--===========================================================================
상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.help.action.MaHelpDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!--  -->
<title><bean:message key='LABEL.empNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

function loadPage() 
{
	acSysDesc("maHelpDetailDTO.helpdeskStatusDesc","maHelpDetailDTO.helpdeskStatus","HELPDESK_STATUS",true);
	
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maHelpDetailDTO.helpdeskNo", "maHelpDetailDTO.description");
    
    setForUpdate();
}

/**
 * 요청
 */
function goRecconfirm()
{
	if(checkIsUpdate(document)){
		//저장후 사용하세요.
		 alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG046'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				maHelpDetailForm.elements['maHelpDetailDTO.helpdeskStatusDesc'].value = "RQ";
			    valSysDir('maHelpDetailDTO.helpdeskStatus', 'maHelpDetailDTO.helpdeskStatusDesc', 'HELPDESK_STATUS', true);
			    
				maHelpDetailForm.strutsAction.value = '<%=MaHelpDetailAction.HELP_DETAIL_REQUEST%>';
				var actionUrl = contextPath + "/maHelpDetail.do";
			    XMLHttpPost(actionUrl, FormQueryString(maHelpDetailForm), 'afterReqeust');
			 }
			});
	 }
}

function afterReqeust()
{
	setState();
	
	//parent.goSearch();
    if (parent.findGridRow)parent.findGridRow(maHelpDetailForm.elements['maHelpCommonDTO.helpdeskId'].value);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('sqahelpdesk_id');
    
    maHelpDetailForm.elements['maHelpDetailDTO.helpdeskStatus'].value = "WT";
    maHelpDetailForm.elements['maHelpDetailDTO.helpdeskStatusDesc'].value = "WT";
    valSysDir('maHelpDetailDTO.helpdeskStatus', 'maHelpDetailDTO.helpdeskStatusDesc', 'HELPDESK_STATUS', true);
	
    maHelpDetailForm.elements['maHelpDetailDTO.reqDate'].value = getToday();
    maHelpDetailForm.elements['maHelpDetailDTO.reqBy'].value = loginUser.empId;
    maHelpDetailForm.elements['maHelpDetailDTO.reqByName'].value = loginUser.empName;
    
    setState();
}

/**
 * 수정
 */
function goUpdate()
{
	setState();
}

function setSequenceVal(sequenceVal)
{
    maHelpDetailForm.elements['maHelpDetailDTO.helpdeskId'].value = sequenceVal;
    maHelpDetailForm.elements['maHelpDetailDTO.helpdeskNo'].value = sequenceVal;
    maHelpDetailForm.elements['maHelpCommonDTO.helpdeskId'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
	
function goTabPage(pageId)
{
	var form = document.maHelpDetailForm;

	if(pageId == "maDocLibList" || pageId == "maHelpDocLibList")
	{	
		maHelpDetailForm.elements['maDocLibCommonDTO.objectId'].value = maHelpDetailForm.elements['maHelpCommonDTO.helpdeskId'].value;
		maHelpDetailForm.elements['maDocLibCommonDTO.objectType'].value = "HELPDESK";  //HELPDESK docDesc
		maHelpDetailForm.elements['maDocLibCommonDTO.description'].value = maHelpDetailForm.elements['maHelpDetailDTO.description'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
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
    if(ckCreate(currentPageId)) maHelpDetailForm.strutsAction.value = "<%=MaHelpDetailAction.HELP_DETAIL_INPUT%>";
    else maHelpDetailForm.strutsAction.value = '<%=MaHelpDetailAction.HELP_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maHelpDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maHelpDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maHelpDetailForm.elements['maHelpCommonDTO.helpdeskId'].value = maHelpDetailForm.elements['maHelpDetailDTO.helpdeskId'].value;

     //parent.goSearch();
     if (parent.findGridRow)parent.findGridRow(maHelpDetailForm.elements['maHelpCommonDTO.helpdeskId'].value);
     
     setTitle("maHelpDetailDTO.helpdeskNo", "maHelpDetailDTO.description");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
function setState()
{
	if(maHelpDetailForm.elements['maHelpDetailDTO.helpdeskStatus'].value == "WT")
	{
		$('.b_recconfirm').show();
	}
	else if(maHelpDetailForm.elements['maHelpDetailDTO.helpdeskStatus'].value == "CP")
	{
		setDisableAll();
	}
	else
	{
		$('.b_recconfirm').hide();
	}
}

function goAudtrailLink()
{
	var objectId = maHelpDetailForm.elements['maHelpDetailDTO.helpdeskId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maHelpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maHelpCommonDTO.compNo" />
	<html:hidden property="maHelpCommonDTO.helpdeskId" />
	<html:hidden property="maHelpDetailDTO.helpdeskId" />
	<html:hidden property="maHelpDetailDTO.helpdeskStatus" />
	<html:hidden property="maHelpDetailDTO.reqBy" />
	<html:hidden property="maHelpDetailDTO.viewBy" />
	<html:hidden property="maHelpDetailDTO.workBy" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
    <div class="article_box">
        <div class="form_box">
            <!-- 요청번호 -->
            <div class="field">
				<label><bean:message key="LABEL.reqNo"/></label>
				<div class="input_read">
					<html:text property="maHelpDetailDTO.helpdeskNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.proStatus"/></label>
				<div class="input_box">
					<html:text property="maHelpDetailDTO.helpdeskStatusDesc" tabindex="30"/>
					<p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
				</div>
			</div>
			<!-- 요청일자 -->
			<div class="field">
				<label><bean:message key="LABEL.appReqDate"/></label>
				<div class="input_read">
					<html:text property="maHelpDetailDTO.reqDate" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 -->
			<div class="field">
				<label><bean:message key="LABEL.reqBy"/></label>
				<div class="input_read">
					<html:text property="maHelpDetailDTO.reqByName" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="maHelpDetailDTO.description" tabindex="50" />
				</div>
			</div>
			<!-- 요청내용 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.request"/></label>
				<div class="input_box">
					<html:textarea property="maHelpDetailDTO.request" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			<!-- 검토일자 -->
            <div class="field">
				<label><bean:message key="LABEL.viewDate"/></label>
				<div class="input_box">
					<html:text property="maHelpDetailDTO.viewDate" tabindex="65"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 검토자 -->
            <div class="field">
				<label><bean:message key="LABEL.viewBy"/></label>
				<div class="input_box">
					<html:text property="maHelpDetailDTO.viewByName" tabindex="70"/>
				</div>
			</div>
			<!-- 작업일자 -->
			<div class="field">
				<label><bean:message key="LABEL.woDate"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="maHelpDetailDTO.workSdate" tabindex="80"/>
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="maHelpDetailDTO.workEdate" tabindex="90"/>
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 작업자 -->
            <div class="field">
				<label><bean:message key="LABEL.woCraft"/></label>
				<div class="input_box">
					<html:text property="maHelpDetailDTO.workByName" tabindex="80"/>
				</div>
			</div>
			<!-- 작업내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woRemark"/></label>
				<div class="input_box">
					<html:textarea property="maHelpDetailDTO.perform" styleClass="ta50" tabindex="90"/>
				</div>
			</div>
			
    </div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>             
</body>
</html:html>
