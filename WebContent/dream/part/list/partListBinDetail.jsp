<%--===========================================================================
부품창고 보관위치 Detail
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.PartListBinDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html:html>
<head>
<!-- 부품창고 보관위치 -->
<title><bean:message key='LABEL.binNoTxt' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var wnameAc;
var binNoAc;

function loadPage() 
{
    setTitle("partListBinDetailDTO.binNo");
   
    //For Save
    setForUpdate();
    
    wnameAc = new autoC({"partListBinDetailDTO.wname":"wname"});
    wnameAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    wnameAc.setTable("TAWAREHOUSE");
    wnameAc.setKeyName("partListBinDetailDTO.wcodeId");
    wnameAc.setAcResultMap({
        "partListBinDetailDTO.wname":"wname"
        ,"partListBinDetailDTO.wcodeId":"wcode_id"
    });
    wnameAc.init();
    
    binNoAc = new autoC({"partListBinDetailDTO.binNo":"binNo"});
    binNoAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	});
    binNoAc.setAcDConditionMap({
    	"wcode_id" : "partListBinDetailDTO.wcodeId"
    });
    binNoAc.setTable("TAPTBINLIST");
    binNoAc.setKeyName("partListBinDetailDTO.ptBinListId");
    binNoAc.setAcResultMap({
        "partListBinDetailDTO.binNo":"bin_no"
        ,"partListBinDetailDTO.binNoTxt":"bin_no"
        ,"partListBinDetailDTO.ptBinListId":"ptBinListId"
    });
    binNoAc.init();

    // 재고등급
    acSysDesc("partListBinDetailDTO.partGradeDesc","partListBinDetailDTO.partGrade","PART_GRADE",true);
    
	if(ckCreate(currentPageId)) goInput();
}

/**
 * 입력
 */
function goInput()
{
	// 시퀀스를 조회한다.
    sequenceNextVal('SQAPTWHBINNO_ID');    
	
    partListBinDetailForm.elements['partListBinDetailDTO.partGrade'].value = '<%=partGrade%>';
    partListBinDetailForm.elements['partListBinDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('partListBinDetailDTO.partGrade', 'partListBinDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	
    partListBinDetailForm.elements['partListBinDetailDTO.wcodeId'].value = partListBinDetailForm.elements['maPtMstrCommonDTO.wcodeId'].value;
    partListBinDetailForm.elements['partListBinDetailDTO.partId'].value = partListBinDetailForm.elements['maPtMstrCommonDTO.partId'].value;
    
    wnameAc.openLov();
    
}

function setSequenceVal(sequenceVal)
{
    partListBinDetailForm.elements['partListBinDetailDTO.ptwhBinNoId'].value = sequenceVal;
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

    // 생성, 수정 시 중복 담당자 체크
	validPtBin(); 
	if(isPtBin!='0') return; 
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) partListBinDetailForm.strutsAction.value = "<%=PartListBinDetailAction.DETAIL_INPUT%>";
    else partListBinDetailForm.strutsAction.value = "<%=PartListBinDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/partListBinDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partListBinDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) 
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(partListBinDetailForm.elements['partListBinDetailDTO.ptwhBinNoId'].value);

	partListBinDetailForm.elements['partListBinListDTO.partId'].value = partListBinDetailForm.elements['partListBinDetailDTO.partId'].value;
	partListBinDetailForm.elements['partListBinListDTO.ptwhBinNoId'].value = partListBinDetailForm.elements['partListBinDetailDTO.ptwhBinNoId'].value;
	getTopPage().afterSaveAll(currentPageId);
	 setTitle("partListBinDetailDTO.binNo"); 

}




/**
 * 중복 담당자 체크
 */
 function validPtBin(){
	var actionUrl = contextPath + "/partListBinDetail.do";
	var param =  "&strutsAction=" + '<%= PartListBinDetailAction.DETAIL_CHECK %>'
	          +  "&" + FormQueryString(partListBinDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setvalidPtBinNo');
}
/**
 * valEmpNo()실행 후 호출
 */
var isPtBin;
function setvalidPtBinNo(ajaxXmlDoc)
{
	isPtBin = '0';
	isPtBin = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isPtBin != '0')
    {
    	closeModal();
    	partListBinDetailForm.elements['partListBinDetailDTO.binNo'].value = '';
    	partListBinDetailForm.elements['partListBinDetailDTO.binNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
} 
/**
 *  Audit trail
 */
/*  function goAudtrailLink()
 {
    var objectId = partListBinDetailForm.elements['partListBinDetailDTO.ptBinListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 } */
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partListBinDetail.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="maPtMstrCommonDTO.wcodeId" />
		<html:hidden property="maPtMstrCommonDTO.partId" />
		<html:hidden property="partListBinListDTO.wcodeId" />
		<html:hidden property="partListBinListDTO.partId" />
		<html:hidden property="partListBinListDTO.ptwhBinNoId" />
		<html:hidden property="partListBinDetailDTO.wcodeId" />
		<html:hidden property="partListBinDetailDTO.partId" />
		<html:hidden property="partListBinDetailDTO.ptBinListId" /> 
		<html:hidden property="partListBinDetailDTO.ptwhBinNoId" /> 
		<html:hidden property="partListBinDetailDTO.partGrade" /> 

		<div class="article_box">
			<div class="form_box">
				<!-- 창고명 -->
	            <div class="field">
					<label class="check"><bean:message key="LABEL.wname"/></label>
					<div class="input_box">
						<html:text property="partListBinDetailDTO.wname" tabindex="10" />
						 <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
					</div>
				</div>
				<!-- 보관위치  -->
				<div class="field">
					<label><bean:message key="LABEL.binNo"/></label>
					<div class="input_box">
			            <html:text property="partListBinDetailDTO.binNo" tabindex="20"/>
			             <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>			           
					</div>
				</div>
				<!-- 보관위치(Text)  -->
				<div class="field">
					<label><bean:message key="LABEL.binNoTxt"/></label>
					<div class="input_box">
			            <html:text property="partListBinDetailDTO.binNoTxt" tabindex="30"/>			           
					</div>
				</div>  
	            <!-- 재고등급 -->
				 <div class="field">
					<label><bean:message key="LABEL.partGrade"/></label>
					<div class="input_box">
						<html:text property="partListBinDetailDTO.partGradeDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				 </div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="partListBinDetailDTO.remark"
							tabindex="50" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
