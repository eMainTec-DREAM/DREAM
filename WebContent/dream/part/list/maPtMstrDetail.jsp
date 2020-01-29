<%--===========================================================================
자재마스터 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html:html> 
<head>
<!-- 자재마스터 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//
var slideImage = new Array();
<%
if(list != null)
	for(int i= 0; list.size() > i ; i++)
	{
	    Map map = (Map)list.get(i);
%>
	slideImage.push('<%=(String)map.get("FILE_NAME")%>');
<% 
	}
%>	
var partDescAc;
var modelAc;
var ptSizeAc;
var partGroupAc;
var varClassAc;
var uomAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
        //goTabPage("maPtMstrVendorList");
        //goTabPage("maPtMstrEqPartList");
        //goTabPage("maPtMstrUsedDeptList");
    }
    
    setTitle("maPtMstrDetailDTO.partNo", "maPtMstrDetailDTO.description");
    
    setForUpdate();
    
    setSlideImage();
    
    partDescAc = new autoC({"maPtMstrDetailDTO.description":"description"});
    partDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    partDescAc.setAcDConditionMap({
    	"part_group" : "maPtMstrDetailDTO.partGroup"
  	   });
    partDescAc.setTable("TAPARTSDESC");
    partDescAc.init();
    
    ptSizeAc = new autoC({"maPtMstrDetailDTO.ptSize":"pt_size"});
    ptSizeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    ptSizeAc.setAcDConditionMap({
    	"part_group" : "maPtMstrDetailDTO.partGroup",
    	"description" : "maPtMstrDetailDTO.description"
  	   });
    ptSizeAc.setTable("TAPARTSPTSIZE");
    ptSizeAc.init();
    
    modelAc = new autoC({"maPtMstrDetailDTO.model":"model"});
    modelAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    modelAc.setAcDConditionMap({
    	"description" : "maPtMstrDetailDTO.description",
    	"part_group" : "maPtMstrDetailDTO.partGroup",
    	"pt_size" : "maPtMstrDetailDTO.ptSize"
  	   });
    modelAc.setTable("TAPARTSMODEL");
    modelAc.init();


	partGroupAc = new autoC({"maPtMstrDetailDTO.partGroupDesc":"description"});
	partGroupAc.setAcConditionMap({
	    	"dir_type":"PART_GROUP",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	partGroupAc.setTable("TACDUSRD");
	partGroupAc.setKeyName("maPtMstrDetailDTO.partGroup");
	partGroupAc.setAcResultMap({
	    "maPtMstrDetailDTO.partGroup":"cdusrd_no"
	});
	partGroupAc.init();
	
	varClassAc = new autoC({"maPtMstrDetailDTO.varClassDesc":"description"});
	varClassAc.setAcConditionMap({
	    	"dir_type":"VAR_CLASS",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	varClassAc.setTable("TACDUSRD");
	varClassAc.setKeyName("maPtMstrDetailDTO.varClass");
	varClassAc.setAcResultMap({
	    "maPtMstrDetailDTO.varClass":"cdusrd_no"
	});
	varClassAc.init();
	
	uomAc = new autoC({"maPtMstrDetailDTO.uomDesc":"description"});
	uomAc.setAcConditionMap({
	    	"dir_type":"UOM",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	uomAc.setTable("TACDUSRD");
	uomAc.setKeyName("maPtMstrDetailDTO.uom");
	uomAc.setAcResultMap({
	    "maPtMstrDetailDTO.uom":"cdusrd_no"
	});
	uomAc.init();
	
    acSysDesc("maPtMstrDetailDTO.mroTypeDesc","maPtMstrDetailDTO.mroType","MRO_TYPE", true);
    acSysDesc("maPtMstrDetailDTO.isUse","maPtMstrDetailDTO.isUse","IS_USE", true);
    acSysDesc("maPtMstrDetailDTO.partCategDesc","maPtMstrDetailDTO.partCateg","PART_CATEG", true);
    acSysDesc("maPtMstrDetailDTO.ptAbcClassDesc","maPtMstrDetailDTO.ptAbcClass","PT_ABC_CLASS", true);
    acSysDesc("maPtMstrDetailDTO.periodTypeDesc","maPtMstrDetailDTO.periodType","PERIOD_TYPE", true);
    
    acSysDesc("maPtMstrDetailDTO.isStockControl","maPtMstrDetailDTO.isStockControl","IS_USE", true);
    acSysDesc("maPtMstrDetailDTO.isSerialPartDesc","maPtMstrDetailDTO.isSerialPart","IS_USE", true);
    acSysDesc("maPtMstrDetailDTO.isAssetStockDesc","maPtMstrDetailDTO.isAssetStockId","IS_USE", true);
    acSysDesc("maPtMstrDetailDTO.plfTypeDesc","maPtMstrDetailDTO.plfType","PLF_TYPE", true);
    
    acSysDesc("maPtMstrDetailDTO.currencyDesc","maPtMstrDetailDTO.currencyId","CURRENCY", true);
}

function loadSlideImages()
{
	var url = contextPath + "/maPtMstrDetail.do";

    var oldSAction = maPtMstrDetailForm.elements['strutsAction'].value;
    maPtMstrDetailForm.elements['strutsAction'].value = '<%=MaPtMstrDetailAction.PTMSTR_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maPtMstrDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    maPtMstrDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAPART_ID');
    maPtMstrDetailForm.elements['maPtMstrDetailDTO.isUse'].value = 'Y';
    maPtMstrDetailForm.elements['maPtMstrDetailDTO.partCateg'].value = 'SPPT';
    maPtMstrDetailForm.elements['maPtMstrDetailDTO.partCategDesc'].value = 'SPPT';
    
    maPtMstrDetailForm.elements['maPtMstrDetailDTO.isSerialPart'].value = 'N';
    maPtMstrDetailForm.elements['maPtMstrDetailDTO.isStockControl'].value = 'N';

    maPtMstrDetailForm.elements['maPtMstrDetailDTO.isAssetStockId'].value = 'N';
    maPtMstrDetailForm.elements['maPtMstrDetailDTO.isAssetStockDesc'].value = 'N';
    
	valSysDir('maPtMstrDetailDTO.partCateg', 'maPtMstrDetailDTO.partCategDesc', 'PART_CATEG', true);
}

/**
 * 수정
 */
function goUpdate()
{
	//외부품번일 경우, 부품번호 수정 금지
	if(maPtMstrDetailForm.elements['maPtMstrDetailDTO.isInPart'].value!="Y"){
		setDisable(document.getElementsByName("disableDiv"));
	}
}

function setSequenceVal(sequenceVal)
{
	maPtMstrDetailForm.elements['maPtMstrDetailDTO.partId'].value = sequenceVal;
    maPtMstrDetailForm.elements['maPtMstrCommonDTO.partId'].value = sequenceVal;
	maPtMstrDetailForm.elements['maPtMstrDetailDTO.partNo'].value = sequenceVal;
    
    //goTabPage("maPtMstrVendorList");
    //goTabPage("maPtMstrEqPartList");
    //goTabPage("maPtMstrUsedDeptList");
}

/**
 * partNo 세팅
 */
function setNoVal(nextNo){
	 maPtMstrDetailForm.elements['maPtMstrDetailDTO.partNo'].value = nextNo;
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId)
{
    var form = document.maPtMstrDetailForm;
    
    if(pageId == "maDocLibList" || pageId == "maPtDocLibList")
    {   
    	maPtMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maPtMstrDetailForm.elements['maPtMstrCommonDTO.partId'].value;
    	maPtMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PTMSTR";  //PTMSTR
    	maPtMstrDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  // 3등급 기본세팅 
    	maPtMstrDetailForm.elements['maDocLibCommonDTO.description'].value = maPtMstrDetailForm.elements['maPtMstrDetailDTO.description'].value;  
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
    }
    else
    goCommonTabPage(form, '' , pageId);
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaPtMstrDetailAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" +maPtMstrDetailForm.elements['maPtMstrCommonDTO.partId'].value +
                "&" + "maDocImgCommonDTO.objectType=PTMSTR"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maPtMstrDetailForm.elements['maPtMstrDetailDTO.description'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}

/**
 * partNo 중복 체크
 */
var oldStructAction;
function varPartNo()
{
	oldStructAction = maPtMstrDetailForm.strutsAction.value;
	var actionUrl = contextPath + "/maPtMstrDetail.do";
	maPtMstrDetailForm.strutsAction.value = "<%=MaPtMstrDetailAction.PTMSTR_DETAIL_CHECK%>";

	XMLHttpPostVal(actionUrl, FormQueryString(maPtMstrDetailForm), 'afterValPartNo');
	
}

/**
 * varPartNo()실행 후 호출
 */
var isValid;
function afterValPartNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
   	maPtMstrDetailForm.strutsAction.value = oldStructAction;
   	
    if(isValid != '0')
    {
    	closeModal();
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
        maPtMstrDetailForm.elements['maPtMstrDetailDTO.partNo'].value = '';
        maPtMstrDetailForm.elements['maPtMstrDetailDTO.partNo'].focus();
        
        return;
    }else{
    	if(oldStructAction)
    	{
	    	//strutsAction 셋팅.
	        if(ckCreate(currentPageId)) maPtMstrDetailForm.strutsAction.value = "<%=MaPtMstrDetailAction.PTMSTR_DETAIL_INPUT%>";
	        else  maPtMstrDetailForm.strutsAction.value = '<%=MaPtMstrDetailAction.PTMSTR_DETAIL_UPDATE%>';
        var actionUrl = contextPath + "/maPtMstrDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(maPtMstrDetailForm), 'afterSave');
    	}
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
    varPartNo();
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maPtMstrDetailForm.elements['maPtMstrCommonDTO.partId'].value = maPtMstrDetailForm.elements['maPtMstrDetailDTO.partId'].value;
     if (parent.findGridRow)	parent.findGridRow(maPtMstrDetailForm.elements['maPtMstrCommonDTO.partId'].value);
     getTopPage().afterSaveAll(currentPageId);
     
     goUpdate();
     setTitle("maPtMstrDetailDTO.partNo", "maPtMstrDetailDTO.description");
     
 }


 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = maPtMstrDetailForm.elements['maPtMstrDetailDTO.partId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maPtMstrDetail" >
	<html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
	<html:hidden property="maPtMstrCommonDTO.compNo" />
	<html:hidden property="maPtMstrCommonDTO.partId" />
    <html:hidden property="maPtMstrDetailDTO.partId" />
    <html:hidden property="maPtMstrDetailDTO.plfType" />
    <html:hidden property="maPtMstrDetailDTO.mroType" />
    <html:hidden property="maPtMstrDetailDTO.fullDesc" />
    <html:hidden property="maPtMstrDetailDTO.ptAbcClass" />
    <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocLibCommonDTO.objectType" />
    <html:hidden property="maDocLibCommonDTO.securGrade" />
    <html:hidden property="maDocLibCommonDTO.description" />
    <html:hidden property="maPtMstrDetailDTO.partGroup" />
    <html:hidden property="maPtMstrDetailDTO.partCateg" />
    <html:hidden property="maPtMstrDetailDTO.isInPart" />
    <html:hidden property="maPtMstrDetailDTO.periodType" />
    <html:hidden property="maPtMstrDetailDTO.isSerialPart" />
    <html:hidden property="maPtMstrDetailDTO.isAssetStockId" />
    <html:hidden property="maPtMstrDetailDTO.varClass" />
    <html:hidden property="maPtMstrDetailDTO.uom" />
    <html:hidden property="maPtMstrDetailDTO.currencyId" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <div class="field" name="disableDiv">  
        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.partNo" tabindex="10" />
        	 	</div>
        	 </div>
        	 <!-- 사진 -->
             <div class="field_img" >
				<label><bean:message key="LABEL.photo"/></label>
				<div class="img_box"  name="maPtMstrDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
					
				</div>
             </div>
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partDesc"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.description" tabindex="20"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptSize"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.ptSize" tabindex="30"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.maker"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.maker" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.model"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.model" tabindex="50"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.seller"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.seller" tabindex="60"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.lastPrice"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.lastPrice" tabindex="70"  styleClass="num"/>
        	 	</div>
        	 </div>
             <div class="field">
                 <label><bean:message key="LABEL.plfType"/></label>
                 <div class="input_box">
                      <html:text property="maPtMstrDetailDTO.plfTypeDesc" tabindex="75"/>
                      <p class="open_spop"><a><span>조회</span></a></p>
                 </div>
             </div>        	 
        	 <div class="field">
        	 	<label><bean:message key="LABEL.uom"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.uomDesc" tabindex="80"/>
        	 		<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.leadTime"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.leadTime" tabindex="90"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptUsage"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.usage" tabindex="100"/>
        	 	</div>
        	 </div>
        	 <div class="field">
                <label><bean:message key="LABEL.isUse"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrDetailDTO.isUse" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
        	 <div class="field">
                <label><bean:message key="LABEL.partCateg"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrDetailDTO.partCategDesc" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <div class="field">
        	 	<label><bean:message key="LABEL.mroType"/></label>
        	 	<div class="input_box">
 					<html:text property="maPtMstrDetailDTO.mroTypeDesc" tabindex="120"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.isSerialPart"/></label>
        	 	<div class="input_box">
 					<html:text property="maPtMstrDetailDTO.isSerialPartDesc" tabindex="130"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.isStockControl"/></label>
        	 	<div class="input_box">
 					<html:text property="maPtMstrDetailDTO.isStockControl" tabindex="140"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 
        	 <div class="field">
                <label><bean:message key="LABEL.replaceCycle"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="maPtMstrDetailDTO.cycle" tabindex="150" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="maPtMstrDetailDTO.periodTypeDesc" tabindex="160" />
                        <p class="open_spop">
                            <a>
                            <span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.kind"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.kind" tabindex="170"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.varClass"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.varClassDesc" tabindex="180"/>
        	 		<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.partGroup"/></label>
        	 	<div class="input_box">
 					<html:text property="maPtMstrDetailDTO.partGroupDesc" tabindex="190"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.vendorPtCode"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.vendorPtCode" tabindex="200"/>
        	 	</div>
        	 </div>
        	 
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptAbcClass"/></label>
        	 	<div class="input_box">
 					<html:text property="maPtMstrDetailDTO.ptAbcClassDesc" tabindex="210"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.lastGrDate"/></label>
        	 	<div  class="input_read">
        	 		<html:text property="maPtMstrDetailDTO.lastGrDate" tabindex="220" readonly="true"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.lastIssDate"/></label>
        	 	<div  class="input_read">
        	 		<html:text property="maPtMstrDetailDTO.lastIssDate" tabindex="230" readonly="true"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.isAssetStock"/></label>
        	 	<div  class="input_box">
        	 		<html:text property="maPtMstrDetailDTO.isAssetStockDesc" tabindex="235" />
        	 		<p class="open_spop">
	                    <a>
	                        <span>조회</span>
	                    </a>
                    </p>
        	 	</div>
        	 </div>
        	 <!-- ERP 품번 -->
			<div class="field">
				<label><bean:message key="LABEL.erpPartNo"/></label>
				<div class="input_box">
					<html:text property="maPtMstrDetailDTO.erpPartNo" tabindex="240" />
				</div>
			</div>
        	 <!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtMstrDetailDTO.remark" styleClass="240" tabindex="200" />
				</div>
			</div>
			<!-- 화폐단위 -->
			<div class="field">
				<label><bean:message key="LABEL.currency"/></label>
				<div class="input_box">
					<html:text property="maPtMstrDetailDTO.currencyDesc" tabindex="244"/>
			          <p class="open_spop">
			              <a><span>조회</span></a>
			          </p>
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/part/list/maPtMstrDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/list/maPtMstrDetail_${compNo}.jsp"></c:import>
			</c:if>
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>