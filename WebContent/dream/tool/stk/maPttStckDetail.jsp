<%--===========================================================================
자재재고 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.stk.action.MaPttStckDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 자재재고 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//
var partNameAc, wareHouseAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	setPtStckTitle();
    	goUpdate();
    }
    
    
    setForUpdate();
    
    
    acSysDesc("maPttStckDetailDTO.partGradeDesc","maPttStckDetailDTO.partGrade","PART_GRADE", true);
    
  //--------------------------------------------------------------------------------//
	partNameAc = new autoC({"maPttStckDetailDTO.partNo":"part_no"});
    partNameAc.setAcConditionMap({
 	   "part_categ":"TOOL",
 	   "comp_no":loginUser.compNo
 	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcResultMap({
        "maPttStckDetailDTO.partId":"part_id"
        ,"maPttStckDetailDTO.partNameSize":"partNameSize"
    });
    partNameAc.init();

    //--------------------------------------------------------------------------------//
    
    wareHouseAc = new autoC({"maPttStckDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttStckDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.setKeyName("maPttStckDetailDTO.wcodeId");
    wareHouseAc.init();
    //--------------------------------------------------------------------------------//
    
}

function setPtStckTitle()
{
    setTitle("maPttStckDetailDTO.wname", "maPttStckDetailDTO.partNo");
}

/**
 * 입력
 */
function goInput()
{
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maPttStckDetailForm.elements['maPttStckDetailDTO.wname'].readOnly = true;
    maPttStckDetailForm.elements['maPttStckDetailDTO.partNo'].readOnly = true;
    
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
    if(maPttStckDetailForm.strutsAction.value == '0')
    {
		// 창고, 품번 정보가 있을 경우만 체크한다. 
		if(maPttStckDetailForm.elements['maPttStckDetailDTO.wname'].value == ""
				|| maPttStckDetailForm.elements['maPttStckDetailDTO.partNo'].value == "")
		{
	    	closeModal();
			   return;
		}
		
        var actionUrl = contextPath + "/maPttStckDetail.do";
        var param =  "&strutsAction=" + '<%= MaPttStckDetailAction.PTSTCK_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maPttStckDetailForm);
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
        maPttStckDetailForm.elements['maPttStckDetailDTO.partId'].value = '';
        maPttStckDetailForm.elements['maPttStckDetailDTO.partNo'].value = '';
        maPttStckDetailForm.elements['maPttStckDetailDTO.partNameSize'].value = '';
        maPttStckDetailForm.elements['maPttStckDetailDTO.partNo'].focus();
        
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
        closeModal();
    }else{
    	if(ckCreate(currentPageId)) maPttStckDetailForm.strutsAction.value = "<%=MaPttStckDetailAction.PTSTCK_DETAIL_INPUT%>";
        else maPttStckDetailForm.strutsAction.value = '<%=MaPttStckDetailAction.PTSTCK_DETAIL_UPDATE%>';
        
        var actionUrl = contextPath + "/maPttStckDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(maPttStckDetailForm), 'afterSave');
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

    var bstockQty = maPttStckDetailForm.elements['maPttStckDetailDTO.bstockQty'].value;
    //if(stockQty == "" || parseFloat(stockQty) <= 0 )
    /* if(astockQty == "")
    {
    	alertMessage1("<bean:message key='LABEL.aStockQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    } */
    if(bstockQty == "")
    {
    	closeModal();
    	alertMessage1("<bean:message key='LABEL.bStockQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
    
    if(ckCreate(currentPageId)){ 
    	varPtStck();
    }else {maPttStckDetailForm.strutsAction.value = '<%=MaPttStckDetailAction.PTSTCK_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maPttStckDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPttStckDetailForm), 'afterSave');
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
    maPttStckDetailForm.elements['maPttStckCommonDTO.wcodeId'].value = maPttStckDetailForm.elements['maPttStckDetailDTO.wcodeId'].value;
    maPttStckDetailForm.elements['maPttStckCommonDTO.partId'].value = maPttStckDetailForm.elements['maPttStckDetailDTO.partId'].value;

    if (parent.findGridRow)	parent.findGridRow(maPttStckDetailForm.elements['maPttStckCommonDTO.wcodeId'].value, maPttStckDetailForm.elements['maPttStckCommonDTO.partId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtStckTitle();
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPttStckDetailForm.elements['maPttStckDetailDTO.partId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maPttStckDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPttStckCommonDTO.compNo" />
	<html:hidden property="maPttStckCommonDTO.wcodeId" />
	<html:hidden property="maPttStckCommonDTO.partId" />
	<html:hidden property="maPttStckCommonDTO.partGrade" />
    <html:hidden property="maPttStckDetailDTO.wcodeId" />
    <html:hidden property="maPttStckDetailDTO.partId" />
    <html:hidden property="maPttStckDetailDTO.partGrade" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.wname"/></label>
        	 	<div id="wnameDiv" class="input_box">
        	 		<html:text property="maPttStckDetailDTO.wname" tabindex="20"
                        onblur="valWhouseDesc('maPttStckDetailDTO.wcodeId', 'maPttStckDetailDTO.wname', true);"/>
                    <p id="wnameSchBtn" class="open_spop">
                        <a href="javascript:lovWhTool('maPttStckDetailDTO.wcodeId', 'maPttStckDetailDTO.wname');">
                            <span>조회</span>
                        </a>    
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPttStckDetailDTO.partNo" tabindex="30"
                        onblur="valToolNo('maPttStckDetailDTO.partId','maPttStckDetailDTO.partNo','maPttStckDetailDTO.partNameSize', true);"/>
                    <p id="partNoSchBtn" class="open_spop">
                        <a href="javascript:lovTools('maPttStckDetailDTO.partId','maPttStckDetailDTO.partNo','maPttStckDetailDTO.partNameSize');">
                            <span>조회</span>
                        </a>
                    </p>
                </div>
        	 </div>
        	 <div class="field_long">
        	 	<label><bean:message key="LABEL.partNameSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPttStckDetailDTO.partNameSize" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <!-- 자재등급 -->
			 <div class="field hidden">
				<label><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="maPttStckDetailDTO.partGradeDesc" tabindex="50" 
						onblur="valSysDir('maPttStckDetailDTO.partGrade', 'maPttStckDetailDTO.partGradeDesc', 'PART_GRADE', true);"/>
					<p class="open_spop"><a href="javascript:lovSysDir('maPttStckDetailDTO.partGrade', 'maPttStckDetailDTO.partGradeDesc','PART_GRADE');"><span>조회</span></a></p>
				</div>
			 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.stockQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttStckDetailDTO.bstockQty" tabindex="70" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.minSaftyQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttStckDetailDTO.minSaftyQty" tabindex="80" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.maxSaftyQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttStckDetailDTO.maxSaftyQty" tabindex="90" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.binNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttStckDetailDTO.binNo" tabindex="100"/>
        	 	</div>
        	 </div>

        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>