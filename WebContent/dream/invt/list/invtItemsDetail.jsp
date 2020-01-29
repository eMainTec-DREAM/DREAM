
<%--===========================================================================
상세
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.invt.list.action.InvtItemsDetailAction"%>
<html>
<head>
<!--구매항목 -->
<title><bean:message key="TAB.INVTITEMS"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var isNew = false;
var mainMngAc, crityRow, equipDescAc;
function loadPage() 
{	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("invtItemsDetailDTO.itemDesc");
	setForUpdate();
	
	equipDescAc = new autoC({"invtItemsDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
	   "plant":loginUser.plant
 	   });
    equipDescAc.setAcResultMap({
        "invtItemsDetailDTO.equipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.init();
}

function goUpdate()
{
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAINVTITEMS_ID');
	
	if(invtItemsDetailForm.elements['invtItemsDetailDTO.dwptcontitemStatusDesc'])
	{	
		invtItemsDetailForm.elements['invtItemsDetailDTO.dwptcontitemStatusDesc'].value = "W";
		valSysDir('invtItemsDetailDTO.dwptcontitemStatus', 'invtItemsDetailDTO.dwptcontitemStatusDesc', 'PTCONTITEM_STATUS');
		invtItemsDetailForm.elements['invtItemsDetailDTO.dwbuygroupDesc'].value = "110";
		//valSysDir('invtItemsDetailDTO.dwbuygroupDesc', 'invtItemsDetailDTO.dwbuygroup', 'BUYGUBUN', true);
	
		invtItemsDetailForm.elements['invtItemsDetailDTO.dwtaxgubunDesc'].value = "1";
		//valSysDir('invtItemsDetailDTO.dwtaxgubunDesc', 'invtItemsDetailDTO.dwtaxgubun', 'TAXGUBUN', true);
	}
	
}

function afterValSysDir(_name)
{
	if(_name == "PTCONTITEM_STATUS") valSysDir('invtItemsDetailDTO.dwbuygroup', 'invtItemsDetailDTO.dwbuygroupDesc', 'BUYGUBUN');
	else if(_name == "BUYGUBUN")valSysDir('invtItemsDetailDTO.dwtaxgubun', 'invtItemsDetailDTO.dwtaxgubunDesc', 'TAXGUBUN');
}

function setSequenceVal(sequenceVal)
{
	if(isNew) invtItemsDetailForm.elements['invtItemsDetailDTO.oldInvtItemsId'].value = invtItemsDetailForm.elements['invtCommonDTO.invtItemsId'].value;
	
	invtItemsDetailForm.elements['invtItemsDetailDTO.invtItemsId'].value = sequenceVal;
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
	
	if(ckCreate(currentPageId)) invtItemsDetailForm.strutsAction.value = '<%=InvtItemsDetailAction.DETAIL_INPUT%>';
	else invtItemsDetailForm.strutsAction.value = '<%=InvtItemsDetailAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/invtItemsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(invtItemsDetailForm), 'afterSave');
	
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    var invtItemsId = invtItemsDetailForm.elements['invtItemsDetailDTO.invtItemsId'].value;

  	if (parent.findGridRow) parent.findGridRow(invtItemsId);

    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.invtItemsDetailForm;

	goCommonTabPage(form, '' , pageId);
    
}

/*
 * 생성
 */
function goCreate()
{
	parent.goCreate();
}
 
/*
 * 복사
 */
function goCopycreate()
{
	isNew = true;
	sequenceNextVal('SQAINVTITEMS_ID');
	
	var form = invtItemsDetailForm;
	var url = contextPath + "/invtItemsDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				var param = "&strutsAction=" + '<%=InvtItemsDetailAction.DETAIL_COPY%>'
						  + "&" + FormQueryString(form);
			    XMLHttpPostVal(url, param, 'afterCopycreate');
			}
		});
    }
}

function afterCopycreate()
{
	isNew = false;
	var newKeyId = invtItemsDetailForm.elements['invtItemsDetailDTO.invtItemsId'].value;

	goClose('invtItemsDetail');
	
	if(""== newKeyId || "undefined"== typeof newKeyId)
	{
		// 전체 복사인 경우
		parent.parent.frames["tabFrameTAB.invtItemsList"].goSearch();
	}
	else
	{	// Unit 복사인 경우
		if(parent.setKeyAftercopy) parent.setKeyAftercopy(newKeyId);
	}
}

function goAudtrailLink()
{
	var objectId = invtItemsDetailForm.elements['invtItemsDetailDTO.invtItemsId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}



</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/invtItemsDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.invtlistId"/>
<html:hidden property="invtCommonDTO.invtItemsId"/>
<html:hidden property="invtCommonDTO.description"/>

<html:hidden property="invtItemsDetailDTO.invtlistId"/>
<html:hidden property="invtItemsDetailDTO.invtItemsId"/>
<html:hidden property="invtItemsDetailDTO.oldInvtItemsId"/>


    <!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 구매항목 -->
			<div class="field">
				<label class="check"><bean:message key="TAB.INVTITEMS"/></label>
				<div class="input_box">
					<html:text property="invtItemsDetailDTO.itemDesc" tabindex="10"/>
				</div>
			</div>
			<!-- 투자금액-->
			<div class="field">
				<label><bean:message key="LABEL.amt"/></label>
				<div class="input_box">
					<html:text property="invtItemsDetailDTO.amt" tabindex="30" styleClass="num"/>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="invtItemsDetailDTO.ordNo" tabindex="30"/>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label><bean:message key="LABEL.equipDesc"/></label>
				<div class="input_box">
					<html:text property="invtItemsDetailDTO.equipDesc" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="invtItemsDetailDTO.remark" styleClass="ta50" tabindex="40" />
				</div>
			</div>
			
			<c:set var="filePath" value="enhance/${compName}/invt/list/invtItemsDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/invt/list/invtItemsDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>