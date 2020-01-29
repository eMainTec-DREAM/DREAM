
<%--===========================================================================
부품실사 item 상세
author  kim21017
version $Id: partAdjStkTakeItemDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%@page import="dream.part.adj.stktake.action.PartAdjStkTakeItemDetailAction"%>
<%
	String partGrade = MwareConfig.getPartGrade();
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.partDesc"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var partNoAc;
function loadPage() 
{
	setTitle("partAdjStkTakeItemDetailDTO.partNo","partAdjStkTakeItemDetailDTO.partDesc");
	setForUpdate();

	partNoAc = new autoC({"partAdjStkTakeItemDetailDTO.partNo":"part_no"});
	partNoAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_stock_control":"Y"
    });
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
    	 "partAdjStkTakeItemDetailDTO.partId":"part_id",
         "partAdjStkTakeItemDetailDTO.partDesc":"description",
         "partAdjStkTakeItemDetailDTO.ptSize":"pt_size",
         "partAdjStkTakeItemDetailDTO.model":"model",
         "partAdjStkTakeItemDetailDTO.maker":"maker",
    });
    partNoAc.setKeyName("partAdjStkTakeItemDetailDTO.partId");
    partNoAc.init();
    
    acSysDesc("partAdjStkTakeItemDetailDTO.partGradeDesc","partAdjStkTakeItemDetailDTO.partGrade","PART_GRADE", true);
    
/*     if(partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeStatus'].value == "C"){
		setDisableAll();
	}else{
		setEnableAll();
	} */
	
	if(ckCreate(currentPageId)) goInput();

}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPTSTKTAKEITEM_ID');
	
	partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.partGrade'].value = '<%=partGrade%>';
	partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('partAdjStkTakeItemDetailDTO.partGrade', 'partAdjStkTakeItemDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeItemId'].value = sequenceVal;
}


/**
 * 중복 체크
 */
function valItem()
{
	var actionUrl = contextPath + "/partAdjStkTakeItemDetail.do";
	var param = "&strutsAction=" + '<%=PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_CHECK%>'
			  + "&ptstktakeitemId=" + partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeItemId'].value
			  + "&partId=" + partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.partId'].value 
			  + "&partGrade=" + partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.partGrade'].value 
			  + "&ptstktakelistId=" + partAdjStkTakeItemDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value ;
	XMLHttpPost(actionUrl, param, 'setValidItem');
}
/**
 * valItem()실행 후 호출
 */
var isValid;
function setValidItem(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
    	partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.partNo'].value = '';
        partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.partNo'].focus();
        
        // 부품과 등급이 중복되었습니다.
        alertMessage1("<bean:message key='LABEL.partNo'/>"+"와 "+"<bean:message key='MESSAGE.MSG0150' />");
    }
    else
    {
    	goSaveAfterValid();
    }
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(parseInt(partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.realQty'].value) < 0 )
    {
    	alertMessage1("<bean:message key='LABEL.realQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }

	valItem();
}

function goSaveAfterValid()
{  	
	if(ckCreate(currentPageId)) partAdjStkTakeItemDetailForm.strutsAction.value = '<%=PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_INPUT%>';
	else partAdjStkTakeItemDetailForm.strutsAction.value = '<%= PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/partAdjStkTakeItemDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partAdjStkTakeItemDetailForm), 'afterSave');
	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeItemId'].value);

    setTitle("partAdjStkTakeItemDetailDTO.partDesc");
    getTopPage().afterSaveAll(currentPageId);
    
    if(parent.goTabPage)	parent.goTabPage(currentPageId);
}


/**
 * 저장후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
   //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
	partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeItemId'].value = partAdjStkTakeDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeItemId'].value;
    if (parent.findGridRow)	parent.findGridRow(PartAdjStkTakeItemDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeItemId'].value);
    
	 //상태 = C - 실사완료
    PartAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeStatus'].value = "C";
    valSysDirCode('partAdjStkTakeItemDetailDTO.ptStkTakeStatus', 'PartAdjStkTakeDetailDTO.ptStkTakeStatusDesc', 'PTSTKTAKE_STATUS', true);
    setDisableAll();
    getTopPage().afterSaveAll(currentPageId);
    
    //goUpdate();
    //setPtStckTitle();
    
    //저장후 자동완료 기능 제거
    //if(partNoAc)partNoAc.destroy();
    //if(whAc)whAc.destroy();
    
    
}

function goTabPage(pageId)
{
	var form = document.partAdjStkTakeItemDetailForm;

	goCommonTabPage(form, '' , pageId);
}



/* audit Trail */
function goAudtrailLink()
{
	var objectId = partAdjStkTakeItemDetailForm.elements['partAdjStkTakeItemDetailDTO.ptStkTakeItemId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/partAdjStkTakeItemDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeListId"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.ptStkTakeItemId"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.partId"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.partGrade"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.ptStkTakeStatus" />
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<!-- 부품번호 -->
				<div class="field">
					<label><bean:message key="LABEL.partNo"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeItemDetailDTO.partNo" tabindex="10"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.ptDesc"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeItemDetailDTO.partDesc" readonly="true" tabindex="20"/>
					</div>
				</div>
				<!-- 자재등급 -->
			 <div class="field">
				<label class="check"><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="partAdjStkTakeItemDetailDTO.partGradeDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>
				<div class="field">
					<label><bean:message key="LABEL.ptSize"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeItemDetailDTO.ptSize" readonly="true" tabindex="40"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.maker"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeItemDetailDTO.maker" readonly="true" tabindex="45"/>
					</div>
				</div>				
				<div class="field">
					<label><bean:message key="LABEL.model"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeItemDetailDTO.model" readonly="true" tabindex="50"/>
					</div>
				</div>
				
				<div class="field">
					<label class="check"><bean:message key="LABEL.realQty"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeItemDetailDTO.realQty" tabindex="60" styleClass="num"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.sstockQty"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeItemDetailDTO.sstockQty" readonly="true" tabindex="70" styleClass="num"/>
					</div>
				</div>
				<div class="field">
				</div>
				<div class="field">
					<label><bean:message key="LABEL.gapQty"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeItemDetailDTO.gapQty" readonly="true" tabindex="80" styleClass="num"/>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="partAdjStkTakeItemDetailDTO.remark" styleClass="ta350" tabindex="90" />
					</div>
				</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>