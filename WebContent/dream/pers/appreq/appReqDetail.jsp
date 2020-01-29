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
<!--결재요청 -->
<title><bean:message key='LABEL.appRequest'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("appReqCommonDTO.title");
	//setReadOnly();
	setForUpdate();
	goInput();
	//$('#detailTitle').html($('#detailTitle').html().replace(":",""));
}

function afterBtnLoad()
{
	//상태가 
	//alert(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "W");
	//if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "W" && appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "D")
	//{
		//$('.b_approval').hide();
		//hideBtn("APPROVAL");
		if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "") $('.b_save').hide();
	//}
}

function afterSelectClose()
{
	if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "W" && appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "D")
		setEnableAll();
}

function goTabPage(pageId)
{
	appReqDetailForm.elements['appReqCommonDTO.apprlistId'].value = appReqDetailForm.elements['appReqDetailDTO.apprlistId'].value;

	goCommonTabPage(appReqDetailForm, '', pageId);
	
}

function goInput()
{
	if(appReqDetailForm.elements['appReqDetailDTO.apprlistId'].value == "") 
	{
		sequenceNextVal('SQAAPPRLIST_ID');
		//$('.b_approval').hide();
		hideBtn("APPROVAL");
	}
	else
	{
		appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.APP_REQ_UPDATE%>';
		//작성중이 아니면 모두 Disable
		//if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "W" && appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value != "D") setDisableAll();
		
		goTabPage('appPrcList');
	}
}

function setSequenceVal(sequenceVal)
{
	appReqDetailForm.elements['appReqDetailDTO.apprlistId'].value = sequenceVal;
	appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.APP_REQ_INPUT%>';
	appReqDetailForm.elements['appReqDetailDTO.objectId'].value = appReqDetailForm.elements['appReqCommonDTO.objectId'].value;
	appReqDetailForm.elements['appReqDetailDTO.apprType'].value = appReqDetailForm.elements['appReqCommonDTO.apprType'].value;
	appReqDetailForm.elements['appReqDetailDTO.title'].value = appReqDetailForm.elements['appReqCommonDTO.title'].value;
	appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value = "W"; //작성중
	
	appReqDetailForm.elements['appReqDetailDTO.reqDate'].value   = getToday();

	appReqDetailForm.elements['appReqDetailDTO.reqByName'].value    = loginUser.empName;
	appReqDetailForm.elements['appReqDetailDTO.reqBy'].value  = loginUser.empId;
	//goTabPage('appReqColList');
	
	if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
		
	goTabPage('appPrcList');
}

function goSaveA()
{
	var actionUrl = contextPath + "/appReqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(appReqDetailForm), 'afterSave');
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	var actionUrl = contextPath + "/appReqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(appReqDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch();
    appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.APP_REQ_UPDATE%>';
    getTopPage().afterSaveAll(currentPageId);
    
    //if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value == "W") $('.b_approval').show();
    if(appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value == "W" || appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value == "D")
    	{
    		//$('.b_approval').show();
    		showBtn("APPROVAL");
    		hideBtn("SAVE");
    	}
    
    setTitle("appReqDetailDTO.title");
}

function goApproval()
{
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }else{
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0070'/>", function(result){
			 if(result){
				 var action = appReqDetailForm.strutsAction.value;
				appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.APP_REQ_CHECK%>';
				var url = contextPath + "/appReqDetail.do"; 
				
				$.post(url,FormQueryString(appReqDetailForm) , function(_data){
					var cnt = parseXmlDoc(_data, 'DESC');
					if(cnt != "0") approval();
					else alertMessage1("<bean:message key='MESSAGE.MSG0072'/>");
					
			    });
				//결재 요청후 액선을 펑션으로 만들어서 초기와 여기서 불러야 함
				
				appReqDetailForm.strutsAction.value = action;
			 }
		});
    }
}

function approval(){
	var action = appReqDetailForm.strutsAction.value;
	appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.APP_REQ_ACTION%>';
	var url = contextPath + "/appReqDetail.do";
	
	appReqDetailForm.elements['appReqDetailDTO.apprStatus'].value ="R"; //결재요청
	
	$.post(url,FormQueryString(appReqDetailForm) , function(_data){
		setDisableAll();
		afterApproval();
    });
	//결재 요청후 액선을 펑션으로 만들어서 초기와 여기서 불러야 함
	
	appReqDetailForm.strutsAction.value = action;
}

function afterApproval()
{
	//승인요청했습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG0071'/>");
	
	
	//부모 상세페이지의 afterApproval 호출 (행 재조회, 상세 닫기?)
	if(typeof getIframeContent().afterApproval) getIframeContent().afterApproval();
	

	closeLayerPopup();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/appReqDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="appReqCommonDTO.objectId"/>
<html:hidden property="appReqCommonDTO.apprType"/>
<html:hidden property="appReqCommonDTO.apprlistId"/>
<html:hidden property="appReqCommonDTO.title"/>

<html:hidden property="appReqDetailDTO.objectId" />
<html:hidden property="appReqDetailDTO.apprType" /> 
<html:hidden property="appReqDetailDTO.apprlistId" /> 
<html:hidden property="appReqDetailDTO.apprStatus" /> 
<html:hidden property="appReqDetailDTO.parentStatus" /> 
<html:hidden property="appReqDetailDTO.reqBy" /> 

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
					 
				</div>
				
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field">
             	 	<label><bean:message key="LABEL.reqBy"/></label>
             	 	<div class="input_read">
             	 		<html:text property="appReqDetailDTO.reqByName" tabindex="5" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.reqDate"/></label>
             	 	<div class="input_read">
             	 		<html:text property="appReqDetailDTO.reqDate" tabindex="15" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label class="check"><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="appReqDetailDTO.title" tabindex="20"/>
             	 	</div>
             	 </div>
             	 <!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="appReqDetailDTO.remark" styleClass="ta50" tabindex="190" />
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div>
</html:form> 
</body>
</html>