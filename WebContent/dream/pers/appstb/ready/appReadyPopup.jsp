<%--=========================================================================== 
author  jung7126
version $Id: appReqDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.appreq.action.AppReqDetailAction"%>
<html>
<head>
<!--결재처리 -->
<title><bean:message key='LABEL.appRequest'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	//setTitle("appReqDetailDTO.title");
	//setReadOnly();
	//setForUpdate();
	//goInput();
	//$('#detailTitle').html($('#detailTitle').html().replace(":",""));
	//작업시작일자, 종료일자 넣기.
	appReadyListForm.elements['appReadyCommonDTO.apprDate'].value   = getToday();
	appReadyListForm.elements['appReadyCommonDTO.apprBy'].value   = loginUser.empName;
	
}

function afterSelectClose()
{
	//if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "W")
	//	setEnableAll();
}

function goApproval()
{
	<%-- var action = appReqDetailForm.strutsAction.value;
	appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.APP_REQ_ACTION%>';
	var url = contextPath + "/appReqDetail.do";
	$.post(url,FormQueryString(appReqDetailForm) , function(_data){
		appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value ="R"; //결재요청
		setDisableAll();
    });
	//결재 요청후 액선을 펑션으로 만들어서 초기와 여기서 불러야 함
	
	appReqDetailForm.strutsAction.value = action; --%>
}


function goAppraction()
{
	getIframeContent().apprAction(appReadyListForm.elements['appReadyCommonDTO.remark'].value);
	closeLayerPopup();
}

function goReject()
{
	getIframeContent().rejectAction(appReadyListForm.elements['appReadyCommonDTO.remark'].value);
	closeLayerPopup();
}

function goReference()
{
	getIframeContent().referenceAction(appReadyListForm.elements['appReadyCommonDTO.remark'].value);
	closeLayerPopup();
}



//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/appReadyList.do">
<html:hidden property="strutsAction"/>

	<div class="section_wrap" id="filterSection">
		<div class="sheader_box">
			<div class="stitle_box"></div>
			<div class="function_box detail">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					<!--   -->
				</div>
				
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field">
             	 	<label><bean:message key="LABEL.apprBy"/></label>
             	 	<div class="input_read">
             	 		<html:text property="appReadyCommonDTO.apprBy" tabindex="5" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.apprDate"/></label>
             	 	<div class="input_read">
             	 		<html:text property="appReadyCommonDTO.apprDate" tabindex="15" readonly="true"/>
             	 	</div>
             	 </div>
             	 <!-- 결재의견 -->
				<div class="field_long">
					<label><bean:message key="LABEL.appRemark"/></label>
					<div class="input_box">
						<html:textarea property="appReadyCommonDTO.remark" styleClass="ta50" tabindex="190" />
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div>
</html:form> 
</body>
</html>