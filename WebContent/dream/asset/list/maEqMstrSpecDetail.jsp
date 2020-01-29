<%--===========================================================================
설비제원(스펙)
author  kim21017
version $Id: maEqMstrSpecDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrSpecDetailAction"%>
<html>
<head>
<!-- 설비제원-->
<title><bean:message key="TAB.maEqMstrSpecList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var eqAsmbAc;
var isNew = false;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqMstrSpecDetailDTO.categ","maEqMstrSpecDetailDTO.prompt");
	setForUpdate();
	
    // 부위
    eqAsmbAc = new autoC({"maEqMstrSpecDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"equip_id":maEqMstrSpecDetailForm.elements['maEqMstrCommonDTO.equipId'].value
      	, "is_use" : "Y"
  	   });
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setKeyName("maEqMstrSpecDetailDTO.eqAsmbId");
    eqAsmbAc.setAcResultMap({
        "maEqMstrSpecDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();

	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQSPEC_ID');
}

function setSequenceVal(sequenceVal)
{
	if(isNew) maEqMstrSpecDetailForm.elements['maEqMstrSpecDetailDTO.oldEqSpecId'].value = maEqMstrSpecDetailForm.elements['maEqMstrSpecDetailDTO.eqSpecId'].value;

	maEqMstrSpecDetailForm.elements['maEqMstrSpecDetailDTO.eqSpecId'].value = sequenceVal;
}

function goSave()
{
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	if(ckCreate(currentPageId)) maEqMstrSpecDetailForm.strutsAction.value = '<%=MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_INPUT%>';
	else maEqMstrSpecDetailForm.strutsAction.value = '<%= MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrSpecDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrSpecDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maEqMstrSpecDetailForm.elements['maEqMstrSpecDetailDTO.eqSpecId'].value);
    getTopPage().afterSaveAll(currentPageId);
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
	sequenceNextVal('SQAEQSPEC_ID');
	
	var form = maEqMstrSpecDetailForm;
	var url = contextPath + "/maEqMstrSpecDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				var param = "&strutsAction=" + '<%=MaEqMstrSpecDetailAction.DETAIL_COPY%>'
						  + "&" + FormQueryString(form);
			    XMLHttpPostVal(url, param, 'afterCopycreate');
			}
		});
    }
}
function afterCopycreate()
{
	isNew = false;
	var newEqSpecId = maEqMstrSpecDetailForm.elements['maEqMstrSpecDetailDTO.eqSpecId'].value;

	goClose('maEqMstrSpecDetail');
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	
	if(""== newEqSpecId || "undefined"== typeof newEqSpecId)
	{
		// 전체 복사인 경우
		parent.parent.frames["tabFrameTAB.maEqMstrSpecList"].goSearch();
	}
	else
	{	// Unit 복사인 경우
		if(parent.setKeyAftercopy) parent.setKeyAftercopy(newEqSpecId);
	}
}


function goAudtrailLink()
{
	var objectId = maEqMstrSpecDetailForm.elements['maEqMstrSpecDetailDTO.eqSpecId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrSpecDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="maEqMstrSpecDetailDTO.eqSpecId"/>
	<html:hidden property="maEqMstrSpecDetailDTO.eqAsmbId"/>
	<html:hidden property="maEqMstrSpecDetailDTO.oldEqSpecId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 분류 -->
			<div class="field">
				<label><bean:message key="LABEL.category"/></label>
				<div class="input_box">
					<html:text property="maEqMstrSpecDetailDTO.categ" tabindex="10"/>
				</div>
			</div>
			<!-- 부위 -->
            <div class="field">
                <label><bean:message key="LABEL.asmb"/></label>
                <div class="input_box">
                    <html:text property="maEqMstrSpecDetailDTO.eqAsmbDesc" tabindex="15"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 항목 -->
			<div class="field">
				<label><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="maEqMstrSpecDetailDTO.prompt" tabindex="20"/>
				</div>
			</div>
			<!-- 값 -->
			<div class="field">
				<label><bean:message key="LABEL.value"/></label>
				<div class="input_box">
					<html:text property="maEqMstrSpecDetailDTO.response" tabindex="30"/>
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label><bean:message key="LABEL.uom"/></label>
				<div class="input_box">
					<html:text property="maEqMstrSpecDetailDTO.uom" tabindex="40"/>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrSpecDetailDTO.ordNo" tabindex="50"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label>비고</label>
				<div class="input_box">
					<html:textarea property="maEqMstrSpecDetailDTO.remark" tabindex="60" styleClass="ta50"/>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>