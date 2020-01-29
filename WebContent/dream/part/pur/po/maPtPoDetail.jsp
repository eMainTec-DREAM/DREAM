<%--===========================================================================
발주이력 - 상세
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.part.pur.po.action.MaPtPoDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='LABEL.poNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maPtPoDetailDTO.poListNo", "maPtPoDetailDTO.partNameSize");
	
	setForUpdate();
	
	/* $("input[name='maPtPoDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maPtPoDetailDTO.partId','maPtPoDetailDTO.partNo','maPtPoDetailDTO.partNameSize','','','','','','maPtPoDetailDTO.unitPrice', true);
		}
	}); */
}

function checkStatus(){
	var status = maPtPoDetailForm.elements['maPtPoDetailDTO.poStatusId'].value;
	if(status=="W"){
		setEnableAll();
		$('.b_reccomplete').hide();
	}else if(status == "P"){
		setEnableAll();
		setTimeout("$('.b_pocomplete').hide();$('.b_delete').hide();",200);
		setTimeout("enableDiv();",200);
		
	}else if(status == "C"){
		setDisableAll();
		setTimeout("$('.b_close').show();",200);
	}
}
function enableDiv(){
	setDisable(document.getElementById("poNoDiv"));
	setDisable(document.getElementById("poStatusDiv"));
	setDisable(document.getElementById("poDateDiv"));
	setDisable(document.getElementById("vendorDiv"));
	setDisable(document.getElementById("ptNoDiv"));
	setDisable(document.getElementById("mngDeptDiv"));
	setDisable(document.getElementById("ptNameSizeDiv"));
	setDisable(document.getElementById("poQtyDiv"));
	setDisable(document.getElementById("reqQtyDiv"));
	setDisable(document.getElementById("reqNoDiv"));
	setDisable(document.getElementById("remarkDiv"));
}

function goUpdate()
{
	checkStatus();
}

function goInput()
{ 
	sequenceNextVal('SQAPOLIST_ID');
	
	maPtPoDetailForm.elements['maPtPoDetailDTO.poDate'].value = getToday(); 
	maPtPoDetailForm.elements['maPtPoDetailDTO.deptId'].value = loginUser.deptId;
    maPtPoDetailForm.elements['maPtPoDetailDTO.deptDesc'].value = loginUser.deptDesc;

	// 상태 : 발주완료 P 입고완료 C 작성중 W
	maPtPoDetailForm.elements['maPtPoDetailDTO.poStatusId'].value = "W"; 
	valSysDirCode('maPtPoDetailDTO.poStatusId', 'maPtPoDetailDTO.poStatusDesc', 'PO_STATUS', true);
	
	$('.b_reccomplete').hide();
	$('.b_pocomplete').hide();
}

function setSequenceVal(sequenceVal)
{
	maPtPoDetailForm.elements['maPtPoDetailDTO.poListId'].value = sequenceVal;
	maPtPoDetailForm.elements['maPtPoDetailDTO.poListNo'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maPtPoDetailForm.strutsAction.value = '<%=MaPtPoDetailAction.PTPO_DETAIL_INPUT%>';
	else maPtPoDetailForm.strutsAction.value = '<%=MaPtPoDetailAction.PTPO_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPtPoDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtPoDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPtPoDetailForm.elements['maPtPoDetailDTO.poListId'].value = maPtPoDetailForm.elements['maPtPoCommonDTO.poListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtPoDetailForm.elements['maPtPoDetailDTO.poListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("maPtPoDetailDTO.poListNo", "maPtPoDetailDTO.partNameSize");
}
 
function sumTotPrice()
{
	 var unitPrice = maPtPoDetailForm.elements['maPtPoDetailDTO.unitPrice'].value;
	 var poQty = maPtPoDetailForm.elements['maPtPoDetailDTO.poQty'].value;
	
	 var result = intToData(poQty) * intToData(unitPrice);
	 maPtPoDetailForm.elements['maPtPoDetailDTO.totPrice'].value = result;
	 setMoneyFormat(maPtPoDetailForm.elements['maPtPoDetailDTO.totPrice'], "3");
}

/**
 * 입고완료 처리 
 */
function goReccomplete()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
		        if(result)
		        {
					if(checkConfirmValidation()) return;
					if(Number(maPtPoDetailForm.elements['maPtPoDetailDTO.recQty'].value) > Number(maPtPoDetailForm.elements['maPtPoDetailDTO.poQty'].value)){
						alertMessage1("<bean:message key='MESSAGE.MSG0094'/>");
						return;
					}
				    maPtPoDetailForm.strutsAction.value = '<%=MaPtPoDetailAction.PTPO_DETAIL_STATUS_UPDATE%>';
				    
				    maPtPoDetailForm.elements['maPtPoDetailDTO.poStatusId'].value = "C"; // 입고완료
				    
				    var actionUrl = contextPath + "/maPtPoDetail.do";
				    XMLHttpPost(actionUrl, FormQueryString(maPtPoDetailForm), 'afterSaveStatus');
		        }
		    });
	 }
}
/**
 * 발주완료 처리 
 */
function goPocomplete()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
		        if(result)
		        {
				    maPtPoDetailForm.strutsAction.value = '<%=MaPtPoDetailAction.PTPO_DETAIL_STATUS_UPDATE%>';
				    
				    maPtPoDetailForm.elements['maPtPoDetailDTO.poStatusId'].value = "P"; // 발주완료 
				    
				    var actionUrl = contextPath + "/maPtPoDetail.do";
				    XMLHttpPost(actionUrl, FormQueryString(maPtPoDetailForm), 'afterSaveStatus');
		        }
		    });
	 }
}


function afterSaveStatus()
{
	alertMessage1("<bean:message key='MESSAGE.MSG0030'/>");
	valSysDirCode('maPtPoDetailDTO.poStatusId', 'maPtPoDetailDTO.poStatusDesc', 'PO_STATUS', true);
	checkStatus();
	if (parent.findGridRow)	parent.findGridRow(maPtPoDetailForm.elements['maPtPoDetailDTO.poListId'].value);
}

function afterSetValue(){
	sumTotPrice();
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtPoDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPtPoCommonDTO.poListId" />
	<html:hidden property="maPtPoDetailDTO.poListId" />
	
	<html:hidden property="maPtPoDetailDTO.deptId" />
	<html:hidden property="maPtPoDetailDTO.partId" />
	<html:hidden property="maPtPoDetailDTO.wcodeId" />
	<html:hidden property="maPtPoDetailDTO.poStatusId" />
	<html:hidden property="maPtPoDetailDTO.vendorId" />
	<html:hidden property="maPtPoDetailDTO.partGrade" />
	<html:hidden property="maPtPoDetailDTO.unitPrice" />
	<html:hidden property="maPtPoDetailDTO.totPrice" />
	<div class="article_box">
		<div class="form_box">
			<!-- 발주No -->
		    <div class="field" id="poNoDiv">
                <label><bean:message key="LABEL.poNo"/></label>
                <div class="input_read">
                    <html:text property="maPtPoDetailDTO.poListNo" tabindex="10" readonly="true"/>
                </div>
            </div>
			<!-- 발주상태 -->
		    <div class="field" id="poStatusDiv">
                <label><bean:message key="LABEL.poStatus"/></label>
                <div class="input_read">
                    <html:text property="maPtPoDetailDTO.poStatusDesc" tabindex="20" readonly="true"/>
                </div>
            </div>
			<!-- 발주일자 -->
		    <div class="field" id="poDateDiv">
                <label class="check"><bean:message key="LABEL.poDate"/></label>
                <div class="input_box">
                    <html:text property="maPtPoDetailDTO.poDate" tabindex="30" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
			<!-- 발주거래처 -->
			<div class="field" id="vendorDiv">
				<label><bean:message key="LABEL.poVendor"/></label>
				<div class="input_box">
					<html:text property="maPtPoDetailDTO.vendorDesc" 
                            onblur="valVendorDesc('maPtPoDetailDTO.vendorId', '', 'maPtPoDetailDTO.vendorDesc', ''
                                                , '', '', '', '', true);" tabindex="40" />
					<p class="open_spop">
						<a href="javascript:lovVendor('maPtPoDetailDTO.vendorId'
                                                    , ''
                                                    , 'maPtPoDetailDTO.vendorDesc'
                                                    ,'','','','','','','');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 품번 -->
			<div class="field" id="ptNoDiv">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div class="input_box">
                    <html:text property="maPtPoDetailDTO.partNo" tabindex="50"
                        onblur="valPartNo('maPtPoDetailDTO.partId','maPtPoDetailDTO.partNo','maPtPoDetailDTO.partNameSize','','','','','','maPtPoDetailDTO.unitPrice', true); afterSetValue();"/>
                    <p class="open_spop">
                        <a href="javascript:lovParts('maPtPoDetailDTO.partId','maPtPoDetailDTO.partNo','maPtPoDetailDTO.partNameSize','','','','','','maPtPoDetailDTO.unitPrice');">
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
			<!-- 담당부서 -->
			<div class="field" id="mngDeptDiv">
				<label class="check"><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="maPtPoDetailDTO.deptDesc" tabindex="60"
						onblur="valDeptDesc('maPtPoDetailDTO.deptId','','maPtPoDetailDTO.deptDesc', true);"/>
					<p class="open_spop"><a href="javascript:lovDept('maPtPoDetailDTO.deptId', '', 'maPtPoDetailDTO.deptDesc');"><span>조회</span></a></p>
				</div>
			</div>
			<!-- 품명/규격 -->
            <div class="field_long" id="ptNameSizeDiv">
               <label><bean:message key="LABEL.ptNameSize"/></label>
               <div class="input_read">
                   <html:text property="maPtPoDetailDTO.partNameSize" tabindex="70" readonly="true"/>
               </div>
            </div>
			<!-- 발주수량 -->
            <div class="field" id="poQtyDiv">
                <label class="check"><bean:message key="LABEL.poQty"/></label>
                <div class="input_box">
                    <html:text property="maPtPoDetailDTO.poQty" tabindex="80"  
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
                </div>
             </div>
			<!-- 청구수량 -->
            <div class="field" id="reqQtyDiv">
                <label><bean:message key="LABEL.reqQty1"/></label>
                <div class="input_box">
                    <html:text property="maPtPoDetailDTO.reqQty" tabindex="90" styleClass="num"/>
                </div>
             </div>
			<!-- 입고일자 -->
		    <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.recDate"/></label>
                <div class="input_box">
                    <html:text property="maPtPoDetailDTO.recDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
			<!-- 입고수량 -->
            <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.recQty"/></label>
                <div class="input_box">
                    <html:text property="maPtPoDetailDTO.recQty" tabindex="110" styleClass="num"/>
                </div>
             </div>
           	 <!-- 입고창고 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.recWarehouse"/></label>
				<div class="input_box">
					<html:text property="maPtPoDetailDTO.wcodeDesc" tabindex="120"
						onblur="valWhouseDesc('maPtPoDetailDTO.wcodeId','maPtPoDetailDTO.wcodeDesc', true);"/>
					<p class="open_spop">
						<a href="javascript:lovWh('maPtPoDetailDTO.wcodeId', 'maPtPoDetailDTO.wcodeDesc');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 청구No -->
		    <div class="field" id="reqNoDiv">
                <label><bean:message key="LABEL.reqNo1"/></label>
                <div class="input_read">
                    <html:text property="maPtPoDetailDTO.requestNo" tabindex="130" readonly="true"/>
                </div>
            </div>
			<!-- 비고 -->
			<div class="field_long" id="remarkDiv">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtPoDetailDTO.remark" styleClass="ta50" tabindex="140" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>