<%--===========================================================================
자재재고 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.stk.action.MaPtStckDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html> 
<head>
<!-- 자재재고 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var partNoAc, whAc;
function loadPage() 
{
	if("maPtMstrStockList"==parent.currentPageId)
	{
		maPtStckDetailForm.elements['maPtStckDetailDTO.partId'].value
			= maPtStckDetailForm.elements['maPtMstrDetailDTO.partId'].value
		maPtStckDetailForm.elements['maPtStckDetailDTO.partNo'].value
			= maPtStckDetailForm.elements['maPtMstrDetailDTO.partNo'].value
		maPtStckDetailForm.elements['maPtStckDetailDTO.partDesc'].value
			= maPtStckDetailForm.elements['maPtMstrDetailDTO.description'].value
		maPtStckDetailForm.elements['maPtStckDetailDTO.ptSize'].value
			= maPtStckDetailForm.elements['maPtMstrDetailDTO.ptSize'].value
		maPtStckDetailForm.elements['maPtStckDetailDTO.model'].value
			= maPtStckDetailForm.elements['maPtMstrDetailDTO.model'].value
			
		maPtStckDetailForm.elements['maPtStckDetailDTO.partNameSize'].value
			= maPtStckDetailForm.elements['maPtMstrDetailDTO.description'].value + ", " + maPtStckDetailForm.elements['maPtMstrDetailDTO.ptSize'].value;
				
		maPtStckDetailForm.elements['maPtStckDetailDTO.wcodeId'].value
			= loginUser.wcodeId;
		maPtStckDetailForm.elements['maPtStckDetailDTO.wname'].value
			= loginUser.wname;
			
		if(ckCreate(currentPageId)) goInput();
	    else 
	    {    	
	    	setPtStckTitle();
	    	goUpdate();
	    }
	}
	else
	{
	    if(ckCreate(currentPageId)) goInput();
	    else 
	    {    	
	    	setPtStckTitle();
	    	goUpdate();
	    }
	}
  
    setForUpdate();
}

function setPtStckTitle()
{
    setTitle("maPtStckDetailDTO.wname", "maPtStckDetailDTO.partNo");
}

function goOpen(pageId)
{
    
    goTabPage(pageId);
}


/**
 * 입력
 */
function goInput()
{
	/* $('input[name="maPtStckDetailDTO.partNameSize"]')
	.prop("readonly","")
	.parent()
	.removeClass("input_read")
	.addClass("input_box");
	
    autoCmpt({"description || (case when pt_size is null then '' else ',' || pt_size end )  || (case when vendor_code is null then '' else ',' || vendor_code end)":"maPtStckDetailDTO.partNameSize"}
    ,{"part_id":"maPtStckCommonDTO.partId"
     ,"part_id":"maPtStckDetailDTO.partId"
     ,"part_no":"maPtStckDetailDTO.partNo"
     }
    ,"TAPARTS"
    ,{"comp_no":loginUser.compNo
     ,"part_categ":"SPPT"
     }
    ,true
   ); */
    
   partNoAc = new autoC({"maPtStckDetailDTO.partNo":"part_no"}); // 조건식 자재명과 자재번호를 동시에 검색할때. 
   partNoAc.setAcResultMap({
	   "maPtStckDetailDTO.partNameSize":"partNameSize", 
	   "maPtStckDetailDTO.partDesc":"description", 
	   "maPtStckDetailDTO.ptSize":"pt_size", 
	   "maPtStckDetailDTO.model":"model", 
	   "maPtStckCommonDTO.partId":"part_id",
	   "maPtStckDetailDTO.partId":"part_id"
	   });   
   partNoAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
   partNoAc.setTable("TAPARTS");
   partNoAc.setKeyName("maPtStckCommonDTO.partId"); 
   partNoAc.init();
   
    
   whAc = new autoC({"maPtStckDetailDTO.wname":"wname"});
   whAc.setAcResultMap({
	     "maPtStckDetailDTO.wcodeId":"wcode_id"
	    ,"maPtStckDetailDTO.whType":"wh_type"
	   }); 
   whAc.setTable("TAWAREHOUSE");
   whAc.setAcConditionMap({
	   "wh_categ":"PART",
	   "comp_no":loginUser.compNo
	   });
   whAc.setKeyName("maPtStckDetailDTO.wcodeId"); 
   whAc.init();
   
}

/**
 * Callback of AutoComplete
 */
function afterAutoCmpt(code)
{
	if(code == "maPtStckDetailDTO.wname") afterSetValue();
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maPtStckDetailForm.elements['maPtStckDetailDTO.wname'].readOnly = true;
    maPtStckDetailForm.elements['maPtStckDetailDTO.partNo'].readOnly = true;
    
    document.getElementById("wnameDiv").className = "input_read"; 
    document.getElementById("partNoDiv").className = "input_read"; 
    document.getElementById("wnameSchBtn").style.display = "none";
    document.getElementById("partNoSchBtn").style.display = "none";
}

/**
 * wcodeId, partId 중복 체크 - 저장시에 호출한다. 
 */
function varPtStck()
{
	
    isValid = 0;
    // 생성 시에만 체크한다. 
    if(maPtStckDetailForm.strutsAction.value == '0')
    {
		// 창고, 품번 정보가 있을 경우만 체크한다. 
		if(maPtStckDetailForm.elements['maPtStckDetailDTO.wname'].value == ""
				|| maPtStckDetailForm.elements['maPtStckDetailDTO.partNo'].value == "")
		{
	    	closeModal();
			   return;
		}
		
        var actionUrl = contextPath + "/maPtStckDetail.do";
        var param =  "&strutsAction=" + '<%= MaPtStckDetailAction.PTSTCK_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maPtStckDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidPtStck');
    }
}

/**
 * varPtStck()실행 후 호출
 */
var isValid = 0;
function setValidPtStck(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if(isValid != '0')
    {
        maPtStckDetailForm.elements['maPtStckDetailDTO.partId'].value = '';
        maPtStckDetailForm.elements['maPtStckDetailDTO.partNo'].value = '';
        maPtStckDetailForm.elements['maPtStckDetailDTO.partNameSize'].value = '';
        maPtStckDetailForm.elements['maPtStckDetailDTO.partNo'].focus();

    	closeModal();
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }else{
    	if(ckCreate(currentPageId)) maPtStckDetailForm.strutsAction.value = "<%=MaPtStckDetailAction.PTSTCK_DETAIL_INPUT%>";
        else maPtStckDetailForm.strutsAction.value = '<%=MaPtStckDetailAction.PTSTCK_DETAIL_UPDATE%>';
        
        var actionUrl = contextPath + "/maPtStckDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(maPtStckDetailForm), 'afterSave');
    }
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
    
    var astockQty = maPtStckDetailForm.elements['maPtStckDetailDTO.astockQty'].value;
    var bstockQty = maPtStckDetailForm.elements['maPtStckDetailDTO.bstockQty'].value;
    //if(stockQty == "" || parseFloat(stockQty) <= 0 )
    /* if(astockQty == "")
    {
    	alertMessage1("<bean:message key='LABEL.aStockQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    } 
    if(bstockQty == "")
    {
    	closeModal();
    	alertMessage1("<bean:message key='LABEL.bStockQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }*/
    
    if(ckCreate(currentPageId)){ 
    	varPtStck();
    }else {maPtStckDetailForm.strutsAction.value = '<%=MaPtStckDetailAction.PTSTCK_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maPtStckDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtStckDetailForm), 'afterSave');
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
    maPtStckDetailForm.elements['maPtStckCommonDTO.wcodeId'].value = maPtStckDetailForm.elements['maPtStckDetailDTO.wcodeId'].value;
    maPtStckDetailForm.elements['maPtStckCommonDTO.partId'].value = maPtStckDetailForm.elements['maPtStckDetailDTO.partId'].value;

    if (parent.findGridRow)	parent.findGridRow(maPtStckDetailForm.elements['maPtStckCommonDTO.wcodeId'].value, maPtStckDetailForm.elements['maPtStckCommonDTO.partId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtStckTitle();
    
    //저장후 자동완료 기능 제거
    if(partNoAc)partNoAc.destroy();
    if(whAc)whAc.destroy();
    
    
}

function afterSetValue(){
	if(maPtStckDetailForm.elements['maPtStckDetailDTO.whType'].value=="LEGACY"){
		maPtStckDetailForm.elements['maPtStckDetailDTO.astockQty'].value = '';
		setDisable(document.getElementById("astockQtyDiv"));
	}else{
		setEnable(document.getElementById("astockQtyDiv"));
		setEnable(document.getElementById("astockQtyBoxDiv"));
	}
}

function goPtstockpdf()
{
	var url   = contextPath + "/maPtStckDetail.do";
 	var param = "maPtStckDetailDTO.partId="+ maPtStckDetailForm.elements['maPtStckDetailDTO.partId'].value;
 	
 	openPrintView(url, param);
}


function goTabPage(pageId)
{
	var form = document.maPtStckDetailForm;
    goCommonTabPage(form, '' , pageId);
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtStckDetailForm.elements['maPtStckDetailDTO.partId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maPtStckDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtStckCommonDTO.compNo" />
	<html:hidden property="maPtStckCommonDTO.wcodeId" />
	<html:hidden property="maPtStckCommonDTO.partId" />
	
	<html:hidden property="maPtMstrDetailDTO.partId" />
	<html:hidden property="maPtMstrDetailDTO.partNo" />
	<html:hidden property="maPtMstrDetailDTO.description" />
	<html:hidden property="maPtMstrDetailDTO.ptSize" />
	<html:hidden property="maPtMstrDetailDTO.model" />
	
    <html:hidden property="maPtStckDetailDTO.wcodeId" />
    <html:hidden property="maPtStckDetailDTO.partId" />
    <html:hidden property="maPtStckDetailDTO.whType" />
    <html:hidden property="maPtStckDetailDTO.partGrade" />
    <html:hidden property="maPtStckDetailDTO.isSerial" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.wname"/></label>
        	 	<div id="wnameDiv" class="input_box">
        	 		<html:text property="maPtStckDetailDTO.wname" tabindex="20"/>
                    <p id="wnameSchBtn" class="open_spop">
                        <a href="javascript:lovWh('maPtStckDetailDTO.wcodeId', 'maPtStckDetailDTO.wname','maPtStckDetailDTO.whType');">
                            <span>조회</span>
                        </a>    
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPtStckDetailDTO.partNo" tabindex="30"/>
                    <p id="partNoSchBtn" class="open_spop">
                        <a href="javascript:lovParts('maPtStckDetailDTO.partId','maPtStckDetailDTO.partNo','maPtStckDetailDTO.partNameSize');">
                            <span>조회</span>
                        </a>
                    </p>
                </div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.partDesc"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtStckDetailDTO.partDesc" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtStckDetailDTO.ptSize" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.model"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtStckDetailDTO.model" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field_long">
        	 	<label><bean:message key="LABEL.partNameSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtStckDetailDTO.partNameSize" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field" id="astockQtyDiv">
        	 	<label><bean:message key="LABEL.aStockQty"/></label>
        	 	<div class="input_box" id="astockQtyBoxDiv">
        	 		<html:text property="maPtStckDetailDTO.astockQty" tabindex="60" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.bStockQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtStckDetailDTO.bstockQty" tabindex="70" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.aBinNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtStckDetailDTO.abinNo" tabindex="80"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.bBinNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtStckDetailDTO.bbinNo" tabindex="90"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.minSaftyQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtStckDetailDTO.minSaftyQty" tabindex="100" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.maxSaftyQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtStckDetailDTO.maxSaftyQty" tabindex="110" styleClass="num"/>
        	 	</div>
        	 </div>

        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>
