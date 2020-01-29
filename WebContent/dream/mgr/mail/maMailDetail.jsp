<%--===========================================================================
메일수신자설정 - 상세
author  kim21017
version $Id: maMailDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.mail.action.MaMailDetailAction"%>
<html:html>
<head>
<!-- 제목 -->
<title><bean:message key='LABEL.title' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	$("input[name='maMailDetailDTO.startExeDate']").blur( function(){
// 		setWorkTime();
	});
	$("input[name='maMailDetailDTO.startExeTime']").blur( function(){
// 		setWorkTime();
	});
	
	// 사용여부
    acSysDesc("maMailDetailDTO.isActive","maMailDetailDTO.isActive","IS_USE", true);
	
	// 전송방법
	acSysDesc("maMailDetailDTO.methodTypeDesc","maMailDetailDTO.methodType","METHOD_TYPE", true);
	
	// 전송주기
	acSysDesc("maMailDetailDTO.timeTypeDesc","maMailDetailDTO.timeType","TIME_TYPE", true);
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maMailDetailDTO.mailDesc");
	
	setForUpdate();
}

function goInput()
{
	sequenceNextVal('SQAMAIL_LIST_ID');
	hideBtn('SENDMAIL');
	maMailDetailForm.elements['maMailDetailDTO.startExeDate'].value   = getToday();
	maMailDetailForm.elements['maMailDetailDTO.startExeTime'].value   = getTime(false);
	
	maMailDetailForm.elements['maMailDetailDTO.mailScopeTypeId'].value = 
		maMailDetailForm.elements['maMailCommonDTO.mailScopeTypeId'].value;
	valSysDirCode('maMailDetailDTO.mailScopeTypeId', 'maMailDetailDTO.mailScopeTypeDesc', 'MAIL_SCOPE_TYPE','', true);
	
}

function goUpdate(){
	showBtn('SENDMAIL');
	setTitle("maMailDetailDTO.mailDesc");
}

function setSequenceVal(sequenceVal)
{
	maMailDetailForm.elements['maMailDetailDTO.mailListId'].value = sequenceVal;
	maMailDetailForm.elements['maMailCommonDTO.mailListId'].value = sequenceVal;
	maMailDetailForm.elements['maMailDetailDTO.mailListNo'].value = sequenceVal;
	
	goTabPage("maMailUsrList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maMailDetailForm;

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
	if(ckCreate(currentPageId)) maMailDetailForm.strutsAction.value = "<%=MaMailDetailAction.MAIL_DETAIL_INPUT%>";
	else maMailDetailForm.strutsAction.value = '<%=MaMailDetailAction.MAIL_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maMailDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maMailDetailForm), 'afterSave');
}

/**
 * 메일발송 (즉시발송)
 */ 
function goSendmailcheck()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MGS0237'/>", function(result){
		if(result)
		{
			try{
				maMailDetailForm.strutsAction.value = '<%=MaMailDetailAction.MAIL_DETAIL_SEND_CHECK%>';
				var actionUrl = contextPath + "/maMailDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(maMailDetailForm), 'afterSendmail');
		    }catch(e){}
		}
		
	});
}
/**
 * 메일발송
 */ 
function goSendmail()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MGS0237'/>", function(result){
		if(result)
		{
			try{
				maMailDetailForm.strutsAction.value = '<%=MaMailDetailAction.MAIL_DETAIL_SEND%>';
				var actionUrl = contextPath + "/maMailDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(maMailDetailForm), 'afterSendmail');
		    }catch(e){}
		}
		
	});
}

function afterSendmail(ajaxXmlDoc)
{
	if (!checkHttpXml(ajaxXmlDoc)) return;
    // result = 0:정상, -1:메일서비스 미등록, -2:전송대상이없음, -3:보낼내용이없음(본문 script 실행시 0건)
	var result = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
	switch (result.toString()) {
	  case "-1"    : alertMessage1('<bean:message key="MESSAGE.MSG209"/>');
	               break;
	  case "-2"   : alertMessage1('<bean:message key="MESSAGE.MSG210"/>');
	               break;
	  case "-3"  : alertMessage1('<bean:message key="MESSAGE.MSG211"/>');
	               break;
	  default    : alertMessage1('<bean:message key="MESSAGE.CMSG014"/>');
	               break;
	}
	
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maMailDetailForm.elements['maMailCommonDTO.mailListId'].value = maMailDetailForm.elements['maMailDetailDTO.mailListId'].value;
     if (parent.findGridRow)	parent.findGridRow(maMailDetailForm.elements['maMailCommonDTO.mailListId'].value);
 	
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maMailDetailForm.elements['maMailDetailDTO.mailListId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maMailDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maMailCommonDTO.mailListId" />
	<html:hidden property="maMailCommonDTO.mailDetailPageId"/>
	<html:hidden property="maMailCommonDTO.mailScopeTypeId"/>
	<html:hidden property="maMailDetailDTO.mailListId" />
	<html:hidden property="maMailDetailDTO.timeType" />
	<html:hidden property="maMailDetailDTO.methodType" />
	<html:hidden property="maMailDetailDTO.mailScopeTypeId" />
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<div class="field">
				<!-- 작업No -->
				<label class="check"><bean:message key="LABEL.workNum"/></label>
				<div class="input_box">
					<html:text property="maMailDetailDTO.mailListNo" tabindex="10"/>
				</div>
			</div>
			<div class="field">
				<!-- 사용여부 -->
				<label class="check"><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="maMailDetailDTO.isActive" tabindex="15"/>
	                   <p class="open_spop">
	                       <a>
	                           <span>조회</span>
	                       </a>
	                   </p>
				</div>
			</div>
			<div class="field_long">
				<!-- 제목 -->
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="maMailDetailDTO.mailDesc" tabindex="20"/>
				</div>
			</div>
			<div class="field_long">
				<!-- 내용 -->
				<label><bean:message key="LABEL.contents"/></label>
				<div class="input_box">
					<html:textarea  property="maMailDetailDTO.remark" tabindex="30"  styleClass="ta50" />
				</div>
			</div>
			<div class="field">
                <label class="check"><bean:message key="LABEL.transCycle"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="maMailDetailDTO.cycle" tabindex="40" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="maMailDetailDTO.timeTypeDesc" tabindex="50" />
                        <p class="open_spop">
                            <a>
                            <span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>
			<div class="field">
				<!-- 전송방법 -->
				<label class="check"><bean:message key="LABEL.methodType"/></label>
				<div class="input_box">
					<html:text property="maMailDetailDTO.methodTypeDesc" tabindex="60" />
					<p class="open_spop">
						<a>
						<span>조회</span></a>
					</p>
				</div>
			</div>
			
			<!-- 전송일자-->
			<div class="field">
				<label class="check" class="lastcheck"><bean:message key="LABEL.exeStartTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maMailDetailDTO.startExeDate" tabindex="165" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maMailDetailDTO.startExeTime" tabindex="166" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<div class="field">
				<!-- 범위타입 -->
				<label class="check"><bean:message key="LABEL.mailScopeType"/></label>
				<div class="input_read">
					<html:text property="maMailDetailDTO.mailScopeTypeDesc" tabindex="70" readonly="true"/>
				</div>
			</div>
			
			<div class="field_long">
				<!-- 범위script-->
				<label><bean:message key="LABEL.scopeScript"/></label>
				<div class="input_box">
					<html:textarea property="maMailDetailDTO.scopeScript" styleClass="ta50" tabindex="80" />
				</div>
			</div>
			<div class="field_long">
				<!-- 제목-->
				<label><bean:message key="LABEL.titleScript"/></label>
				<div class="input_box">
					<html:textarea property="maMailDetailDTO.titleScript" styleClass="ta50" tabindex="90" />
				</div>
			</div>
			<div class="field_long">
				<!-- 전송 Script -->
				<label><bean:message key="LABEL.transScript"/></label>
				<div class="input_box">
					<html:textarea property="maMailDetailDTO.script" styleClass="ta150" tabindex="100" />
				</div>
			</div>
			<div class="field_long">
				<!-- 대상자 Script -->
				<label><bean:message key="LABEL.targetScript"/></label>
				<div class="input_box">
					<html:textarea property="maMailDetailDTO.targetScript" styleClass="ta50" tabindex="110" />
				</div>
			</div>
			
			
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>