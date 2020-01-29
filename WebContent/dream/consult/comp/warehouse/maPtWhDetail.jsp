<%--===========================================================================
부품창고 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.warehouse.action.MaPtWhDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 부품창고 -->
<title><bean:message key='LABEL.wcode' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var isUseAc;
var whCategAc;
var whTypeAc;
var plantAc;

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	setPtWhTitle();
    	goUpdate();
    }
    
    
    setForUpdate();
    
    compAc = new autoC({"maPtWhDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setAcResultMap({
        "maPtWhDetailDTO.compNo":"comp_no"
    });
    compAc.init();

    //공장명
    plantAc = new autoC({"maPtWhDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAUSRPLANTAUTH");
    plantAc.setAcConditionMap({
    });
    plantAc.setAcResultMap({
        "maPtWhDetailDTO.plant":"plant"
    });
    plantAc.setAcDConditionMap({
    	"comp_no" : "maPtWhDetailDTO.compNo"
    });
    plantAc.init();
    
    acSysDesc("maPtWhDetailDTO.isUse","maPtWhDetailDTO.isUse","IS_USE");
    
    whCategAc = new autoC({"maPtWhDetailDTO.whCategDesc":"description"});
    whCategAc.setAcDisplay("DESCRIPTION");
    whCategAc.setAcConditionMap({
        	"list_type":"WH_CATEG",
        	"is_use":"Y"
  	   });
    whCategAc.setTable("TACDSYSD");
    whCategAc.setKeyName("maPtWhDetailDTO.whCateg");
    whCategAc.setAcResultMap({
        "maPtWhDetailDTO.whCateg":"cdsysd_no"
    });
    whCategAc.init();
    
    whTypeAc = new autoC({"maPtWhDetailDTO.whTypeDesc":"description"});
    whTypeAc.setAcDisplay("DESCRIPTION");
    whTypeAc.setAcConditionMap({
        	"list_type":"WH_TYPE",
        	"is_use":"Y"
  	   });
    whTypeAc.setTable("TACDSYSD");
    whTypeAc.setKeyName("maPtWhDetailDTO.whType");
    whTypeAc.setAcResultMap({
        "maPtWhDetailDTO.whType":"cdsysd_no"
    });
    whTypeAc.init();
    
	bindLenLimit($('input[name="maPtWhDetailDTO.outPlant"]'), 4);
	bindLenLimit($('input[name="maPtWhDetailDTO.outWcode"]'), 4);
}

function setPtWhTitle()
{
    setTitle("maPtWhDetailDTO.wcode", "maPtWhDetailDTO.wname");
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAWCODE_ID');
}

function setSequenceVal(sequenceVal)
{
	maPtWhDetailForm.elements['maPtWhDetailDTO.wcodeId'].value = sequenceVal;
	maPtWhDetailForm.elements['maPtWhCommonDTO.wcodeId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
{

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
    if(ckCreate(currentPageId)) maPtWhDetailForm.strutsAction.value = "<%=MaPtWhDetailAction.PTWH_DETAIL_INPUT%>";
    else maPtWhDetailForm.strutsAction.value = '<%=MaPtWhDetailAction.PTWH_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maPtWhDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtWhDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
   //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maPtWhDetailForm.elements['maPtWhCommonDTO.wcodeId'].value = maPtWhDetailForm.elements['maPtWhDetailDTO.wcodeId'].value;
    maPtWhDetailForm.elements['maPtWhCommonDTO.compNo'].value = maPtWhDetailForm.elements['maPtWhDetailDTO.compNo'].value;

    if (parent.findGridRow)	parent.findGridRow(maPtWhDetailForm.elements['maPtWhCommonDTO.compNo'].value, maPtWhDetailForm.elements['maPtWhCommonDTO.wcodeId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtWhTitle();
}
/**
 * Popup에서 데이터 선택후 호출됨. 
 */
function afterSetValue()
{
	//varPtWh();
}

function afterAutoCmpt(_code, _resultArray)
{
	if("maPtWhDetailDTO.compDesc" == _code)
	{
		maPtWhDetailForm.elements['maPtWhDetailDTO.plant'].value = "";
		maPtWhDetailForm.elements['maPtWhDetailDTO.plantDesc'].value  = "";
	}
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maPtWhDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPtWhCommonDTO.wcodeId" />
	<html:hidden property="maPtWhCommonDTO.compNo" />
	<html:hidden property="maPtWhDetailDTO.whType" />
    <html:hidden property="maPtWhDetailDTO.wcodeId" />
    <html:hidden property="maPtWhDetailDTO.whCateg" />
    <html:hidden property="maPtWhDetailDTO.compNo" />
    <html:hidden property="maPtWhDetailDTO.plant" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
            <div class="field">
               <label class="check"><bean:message key="LABEL.compDesc"/></label>
               <div id="compNoDiv" class="input_box">
                   <html:text property="maPtWhDetailDTO.compDesc" tabindex="10"/>
                   <p class="open_spop">
                       <a><span>조회</span></a>
                   </p>
               </div>
            </div>
			<!-- 공장 -->
            <div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maPtWhDetailDTO.plantDesc" tabindex="13" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
        	<!-- 기계능력 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.wcode"/></label>
				<div class="input_box">
					<html:text property="maPtWhDetailDTO.wcode" tabindex="15" />
				</div>
			</div>
			<div class="field">
				<!-- 사용여부 -->
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maPtWhDetailDTO.isUse" tabindex="20"
						onblur="valYn('maPtWhDetailDTO.isUse', true);"/>
					<p class="open_spop">
						<a href="javascript:lovTable('maPtWhDetailDTO.isUse', 'maPtWhDetailDTO.isUse','YN');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field">
				<!-- 창고명 -->
				<label class="check"><bean:message key="LABEL.wname"/></label>
				<div class="input_box">
					<html:text property="maPtWhDetailDTO.wname" tabindex="30" />
				</div>
			</div>
			
			<div class="field">
        	 	<label class="check"><bean:message key="LABEL.whCateg"/></label>
        	 	<div class="input_box">
 					<html:text property="maPtWhDetailDTO.whCategDesc" tabindex="40"
                    onblur="valSysDir('maPtWhDetailDTO.whCateg', 'maPtWhDetailDTO.whCategDesc', 'WH_CATEG', true);"/>
                    <p class="open_spop">
                        <a href="javascript:lovSysDir('maPtWhDetailDTO.whCateg', 'maPtWhDetailDTO.whCategDesc','WH_CATEG');">
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	</div>
        	 
			<div class="field">
        	 	<label class="check"><bean:message key="LABEL.whType"/></label> 
        	 	<div class="input_box">
 					<html:text property="maPtWhDetailDTO.whTypeDesc" tabindex="50"
                    onblur="valSysDir('maPtWhDetailDTO.whType', 'maPtWhDetailDTO.whTypeDesc', 'WH_TYPE', true);"/>
                    <p class="open_spop">
                        <a href="javascript:lovSysDir('maPtWhDetailDTO.whType', 'maPtWhDetailDTO.whTypeDesc','WH_TYPE');">
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
			<div class="field">
				<!-- 외부시스템 공장코드 -->
				<label><bean:message key="LABEL.outPlant"/></label>
				<div class="input_box">
					<html:text property="maPtWhDetailDTO.outPlant" tabindex="60" />
				</div>
			</div>
			<div class="field">
				<!-- 외부시스템 창고코드 -->
				<label><bean:message key="LABEL.outWcode"/></label>
				<div class="input_box">
					<html:text property="maPtWhDetailDTO.outWcode" tabindex="70" />
				</div>
			</div>
			<!-- 설명 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtWhDetailDTO.remark" styleClass="ta50" tabindex="80" />
				</div>
			</div>

        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>