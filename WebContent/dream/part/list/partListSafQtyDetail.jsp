<%--===========================================================================
부품창고 보관위치 Detail
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.PartListSafQtyDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 부품창고 보관위치 -->
<title><bean:message key='LABEL.binNo' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var wnameAc;

function loadPage() 
{
//     setTitle("partListSafQtyDetailDTO.binNo");
   
    wnameAc = new autoC({"partListSafQtyDetailDTO.wname":"wname"});
    wnameAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    wnameAc.setTable("TAWAREHOUSE");
    wnameAc.setKeyName("partListSafQtyDetailDTO.wcodeId");
    wnameAc.setAcResultMap({
        "partListSafQtyDetailDTO.wname":"wname"
        ,"partListSafQtyDetailDTO.wcodeId":"wcode_id"
    });
    wnameAc.init();
    
    if(ckCreate(currentPageId)) goInput();
    
  	//For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
	// 시퀀스를 조회한다.
//     sequenceNextVal('SQAWHUSER_ID');
    /* partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.wcodeId'].value = partListSafQtyDetailForm.elements['maPtMstrCommonDTO.wcodeId'].value; */
    partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.partId'].value = partListSafQtyDetailForm.elements['maPtMstrCommonDTO.partId'].value;
    
//     wnameAc.openLov();
}

// function setSequenceVal(sequenceVal)
// {
//     partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.partId'].value = sequenceVal;
// }

/**
 * 저장
 */ 
function goSave()
{
    //================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

    // 생성, 수정 시 중복 담당자 체크
	/* validEmp(); */
	/* if(isValid!='0') return; */
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) partListSafQtyDetailForm.strutsAction.value = "<%=PartListSafQtyDetailAction.DETAIL_INPUT%>";
    else partListSafQtyDetailForm.strutsAction.value = "<%=PartListSafQtyDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/partListSafQtyDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partListSafQtyDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) 
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.partId'].value);

	partListSafQtyDetailForm.elements['partListSafQtyListDTO.partId'].value = partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.partId'].value;
	getTopPage().afterSaveAll(currentPageId);
// 	 setTitle("partListSafQtyDetailDTO.binNo"); 

}


/**
 * 중복 담당자 체크
 */
<%-- function validEmp(){
	var actionUrl = contextPath + "/partListSafQtyDetail.do";
	var param =  "&strutsAction=" + '<%= PartListSafQtyDetailAction.DETAIL_CHECK %>'
	          +  "&" + FormQueryString(partListSafQtyDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidEmpNo');
}
/**
 * valEmpNo()실행 후 호출
 */
var isValid;
function setValidEmpNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
    	partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.binNo'].value = '';
    	partListSafQtyDetailForm.elements['partListSafQtyDetailDTO.binNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
} --%>

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partListSafQtyDetail.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="maPtMstrCommonDTO.partId" />
		<%-- <html:hidden property="maPtMstrCommonDTO.wcodId" /> --%>
		<html:hidden property="partListSafQtyListDTO.wcodeId" />
		<html:hidden property="partListSafQtyListDTO.partId" />
		<html:hidden property="partListSafQtyDetailDTO.wcodeId" />
		<html:hidden property="partListSafQtyDetailDTO.partId" />


		<div class="article_box">
			<div class="form_box">
			<!-- 창고명 -->
			<div class="field">
					<label><bean:message key="LABEL.wname"/></label>
                    <div class="input_box">
                        <html:text property="partListSafQtyDetailDTO.wname" tabindex="10"/>
	                   	<p class="open_spop">
	                       <a>
	                           <span>조회</span>
	                       </a>
	                   </p>
                    </div>
                </div>
				<!-- 안전재고수량(min) -->
				<div class="field">
					<label><bean:message key="LABEL.minSaftyQty"/></label>
					<div class="input_box">
			            <html:text property="partListSafQtyDetailDTO.minSaftyQty" tabindex="20" />			           
					</div>
				</div>
				<!-- 안전재고수량(max)  -->
				<div class="field">
					<label><bean:message key="LABEL.maxSaftyQty"/></label>
					<div class="input_box">
			            <html:text property="partListSafQtyDetailDTO.maxSaftyQty" tabindex="30" />			           
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
