<%--===========================================================================
화변별 필드 상세 
author  kim21017
version $Id: persPrivUsrFieldDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.pgm.action.PersPrivUsrFieldDetailAction"%>
<html>
<head>
<!--화면 - 필드 -->
<title><bean:message key="TAB.persPrivUsrFieldDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var labelAc, fldOpAc, gLabelAc;

function loadPage() 
{
	//if(ckCreate(currentPageId)) goInput();
	
	setTitle("persPrivUsrFieldDetailDTO.keyName");
	setForUpdate();
	
	//Label 명
	labelAc = new autoC({"persPrivUsrFieldDetailDTO.keyName":"key_name"});
	labelAc.setAcConditionMap({
		"key_type":"LABEL"
	  });
	labelAc.setTable("TALANG");
	labelAc.setKeyName("persPrivUsrFieldDetailDTO.keyNo");
	labelAc.setAcResultMap({
	    "persPrivUsrFieldDetailDTO.keyNo":"key_no",
	    "persPrivUsrFieldDetailDTO.keyType":"key_type"
	});
	labelAc.init();
	
	gLabelAc = new autoC({"persPrivUsrFieldDetailDTO.groupKeyName":"key_name"});
	gLabelAc.setAcConditionMap({
		"key_type":"LABEL"
	  });
	gLabelAc.setTable("TALANG");
	gLabelAc.setKeyName("persPrivUsrFieldDetailDTO.groupKeyNo");
	gLabelAc.setAcResultMap({
	    "persPrivUsrFieldDetailDTO.groupKeyNo":"key_no",
	    "persPrivUsrFieldDetailDTO.groupKeyType":"key_type"
	});
	gLabelAc.init();
	
	//화면Display여부
	acSysDesc("persPrivUsrFieldDetailDTO.displayYn","persPrivUsrFieldDetailDTO.displayYn","IS_USE",true);
	
	//필드옵션
	acSysDesc("persPrivUsrFieldDetailDTO.fieldOptionDesc","persPrivUsrFieldDetailDTO.fieldOption","FIELD_OPTION",true);
	acSysDesc("persPrivUsrFieldDetailDTO.groupOptionDesc","persPrivUsrFieldDetailDTO.groupOption","GROUP_OPTION",true);

	//필수 항목은 화면에서 숨길수 없다!
	var checkYn = persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.checkYn'].value;
	if(checkYn == "Y") setDisableObj("persPrivUsrFieldDetailDTO.displayYn");
}

function setDisableObj(_name)
{
	var _obj = $("[name='"+_name+"']");
	if(_obj.length > 0)
	{
		_obj.attr("readonly",true);
		var fieldObj = _obj.parents('.field');
		fieldObj.find('.input_box').removeClass('input_box').addClass('input_read');
		fieldObj.find('a').hide();
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGFIELD_ID');
	persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.hiddenYn'].value = 'N';
	persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.displayYn'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.pgFieldId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	var usrPgFieldId =  persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.usrPgFieldId'].value;
	
	if(usrPgFieldId == "") persPrivUsrFieldDetailForm.strutsAction.value = '<%=PersPrivUsrFieldDetailAction.PG_FIELD_DETAIL_INPUT%>';
	else persPrivUsrFieldDetailForm.strutsAction.value = '<%=PersPrivUsrFieldDetailAction.PG_FIELD_DETAIL_UPDATE%>';

	var actionUrl = contextPath + "/persPrivUsrFieldDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(persPrivUsrFieldDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    var usrPgFieldId = parseXmlDoc(ajaxXmlDoc, 'DESC');
    persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.usrPgFieldId'].value = usrPgFieldId;

    if (parent.findGridRow)	parent.findGridRow(persPrivUsrFieldDetailForm.elements['persPrivUsrFieldDetailDTO.pgFieldId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
    
    
    setParentsFields();
}

function setParentsFields()
{
	var fieldName = $('[name="persPrivUsrFieldDetailDTO.fieldId"]').val();
    var displayYn = $('[name="persPrivUsrFieldDetailDTO.displayYn"]').val();
    var ordNo = $('[name="persPrivUsrFieldDetailDTO.ordNo"]').val();
    var fieldOption = $('[name="persPrivUsrFieldDetailDTO.fieldOption"]').val();
    
    var ifmCnt = parent.getIframeContent();
    var targetObj = ifmCnt.$('[name="'+fieldName+'"]');
    var divObj = targetObj.parents(".field,.field_long,.field_img");

    //Display
    if(displayYn == "N") divObj.hide();
    else divObj.show();
    
    //Label
    var keyName = $('[name="persPrivUsrFieldDetailDTO.keyName"]').val();
    targetObj.parent().prev().text(keyName);

    //Field Option
    if(displayYn == "Y" && fieldOption != "" && divObj.next().is('.field,.field_long,.field_img'))
    	$( "<DIV></DIV>" ).addClass(fieldOption).insertAfter(divObj);
    else if(displayYn == "Y" && fieldOption != "" && !divObj.next().is('.field,.field_long,.field_img'))
    	divObj.next().prop("class","").addClass(fieldOption);
    else if(fieldOption == "" && !divObj.next().is('.field,.field_long,.field_img'))
    	divObj.next().hide();
    
    //OrdNo (tabIndex)
    var firstObj = true;
    ifmCnt.$(".field,.field_long,.field_img").each(function(e){
    	var inputObj = $(this).find('input,textarea,.img_box').eq(0);
    	if(ordNo > inputObj.prop("tabindex"))
    	{
    		divObj.insertAfter( $(this) );
    		firstObj = false;
    		return;
    	}
    });
    
    if(firstObj) ifmCnt.$('.form_box').prepend(divObj);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/persPrivUsrFieldDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="persPrivUsrFieldCommonDTO.pageId"/>
<html:hidden property="persPrivUsrFieldDetailDTO.pgFieldId"/>
<html:hidden property="persPrivUsrFieldDetailDTO.usrPgFieldId"/>
<html:hidden property="persPrivUsrFieldDetailDTO.keyNo"/>
<html:hidden property="persPrivUsrFieldDetailDTO.keyType"/>
<html:hidden property="persPrivUsrFieldDetailDTO.checkYn"/>
<html:hidden property="persPrivUsrFieldDetailDTO.fieldOption"/>
<html:hidden property="persPrivUsrFieldDetailDTO.fieldId"/>

<html:hidden property="persPrivUsrFieldDetailDTO.groupKeyNo"/>
<html:hidden property="persPrivUsrFieldDetailDTO.groupKeyType"/>
<html:hidden property="persPrivUsrFieldDetailDTO.groupOption"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<%-- <!-- 필드id -->
               <div class="field">
	               <label class="check"><bean:message key="LABEL.fieldId"/></label>
               	   <div class="input_box">
               	   		<html:text property="persPrivUsrFieldDetailDTO.fieldId" tabindex="10" />
             	   </div>
           	   </div>
				<!-- 숨김여부 -->
				<div class="field">
					<label><bean:message key="LABEL.hiddenYn"/></label>
					<div class="input_box">
							<html:text property="persPrivUsrFieldDetailDTO.hiddenYn" tabindex="20"
								onblur="valYn('persPrivUsrFieldDetailDTO.hiddenYn', true);"/>
							<p class="open_spop">
								<a href="javascript:lovTable('persPrivUsrFieldDetailDTO.hiddenYn', 'persPrivUsrFieldDetailDTO.hiddenYn','YN');">
									<span>조회</span>
								</a>
							</p>
					</div>
				</div> --%>
				<!-- 화면순서 -->
				<div class="field">
	               <label><bean:message key="LABEL.ordNo"/></label>
               	   <div class="input_box">
               	   		<html:text property="persPrivUsrFieldDetailDTO.ordNo" tabindex="30" />
               	   </div>
				</div>
				<!-- 화면Display여부 -->
				<div class="field">
					<label><bean:message key="LABEL.displayYn"/></label>
					<div class="input_box">
						<html:text property="persPrivUsrFieldDetailDTO.displayYn" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<%-- <!-- 항목설명 -->
				<div class="field_long">
	               <label><bean:message key="LABEL.fieldDesc"/></label>
               	   <div class="input_box">
               	   		<html:text property="persPrivUsrFieldDetailDTO.fieldDesc" tabindex="50" />
               	   </div>
				</div> --%>
				<!-- Label 명 -->
				<div class="field">
					<label><bean:message key="LABEL.keyName"/></label>
					<div class="input_box">
						<html:text property="persPrivUsrFieldDetailDTO.keyName" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 필드 옵션 -->
				<div class="field">
					<label><bean:message key="LABEL.fieldOption"/></label>
					<div class="input_box">
						<html:text property="persPrivUsrFieldDetailDTO.fieldOptionDesc" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Group Label 명 -->
				<div class="field">
					<label><bean:message key="LABEL.groupkeyName"/></label>
					<div class="input_box">
						<html:text property="persPrivUsrFieldDetailDTO.groupKeyName" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 필드 옵션 -->
				<div class="field">
					<label><bean:message key="LABEL.groupOption"/></label>
					<div class="input_box">
						<html:text property="persPrivUsrFieldDetailDTO.groupOptionDesc" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>