<%--===========================================================================
작업결과(고장작업 BM) 부품 - Serial
author  kim21017
version $Id: maWoResultBmOilCraftDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WorkListBmRplPartSerialDetailAction"%>
<html>
<head>
<!-- Serial#-->
<title><bean:message key="TAB.PTSERIAL"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변수  */
var serialInAc,serialOutAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("workListBmRplPartSerialDetailDTO.inSerialNo");
	setForUpdate();
	
	
	setFunction();
}

function setFunction()
{
	serialInAc = new autoC({"workListBmRplPartSerialDetailDTO.inSerialNo":"serialNo"});
	serialInAc.setAcConditionMap({
		"comp_no":loginUser.compNo,
		"part_id":parent.$('[name="maWoResultPartDetailDTO.partId"]').val(),
		"eq_status":"S" //대기설비
	});
	serialInAc.setTable("TAPARTEQUIP");
	serialInAc.setKeyName("workListBmRplPartSerialDetailDTO.inEquipId");
	serialInAc.setAcResultMap({
		"workListBmRplPartSerialDetailDTO.inEquipId":"equipId",
		"workListBmRplPartSerialDetailDTO.partId":"partId"
	});
	serialInAc.init();
	
	serialOutAc = new autoC({"workListBmRplPartSerialDetailDTO.outSerialNo":"serialNo"});
	serialOutAc.setAcConditionMap({
		"comp_no":loginUser.compNo,
		"part_id":parent.$('[name="maWoResultPartDetailDTO.partId"]').val(),
		"eq_status":"R" //운용설비
	});
	serialOutAc.setTable("TAPARTEQUIP");
	serialOutAc.setKeyName("workListBmRplPartSerialDetailDTO.outEquipId");
	serialOutAc.setAcResultMap({
		"workListBmRplPartSerialDetailDTO.outEquipId":"equipId",
		"workListBmRplPartSerialDetailDTO.partId":"partId"
	});
	serialOutAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqawoparts_Serial_id');
}

function setSequenceVal(sequenceVal)
{
	workListBmRplPartSerialDetailForm.elements['workListBmRplPartSerialDetailDTO.wopartsSerialId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if($('input[name="workListBmRplPartSerialDetailDTO.inSerialNo"]').val() == $('input[name="workListBmRplPartSerialDetailDTO.outSerialNo"]').val())
	{
		//투입과 교체 Serial No는 같을수 없습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0172' />");
		return;
	}
	
	if(ckCreate(currentPageId)) workListBmRplPartSerialDetailForm.strutsAction.value = '<%=WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_INPUT%>';
	else workListBmRplPartSerialDetailForm.strutsAction.value = '<%= WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_UPDATE %>';

	var actionUrl = contextPath + "/workListPmRplPartSerialDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workListBmRplPartSerialDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workListBmRplPartSerialDetailForm.elements['workListBmRplPartSerialDetailDTO.wopartsSerialId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListBmRplPartSerialDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="workListBmRplPartSerialListDTO.wopartSerialId"/>
	<html:hidden property="workListBmRplPartSerialDetailDTO.wopartsSerialId"/>
	<html:hidden property="workListBmRplPartSerialDetailDTO.wopartId"/>
	<html:hidden property="maWoResultPartDetailDTO.woPartId"/>
	
	<html:hidden property="workListBmRplPartSerialDetailDTO.partId"/>
	<html:hidden property="workListBmRplPartSerialDetailDTO.inEquipId"/>
	<html:hidden property="workListBmRplPartSerialDetailDTO.outEquipId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 투입 Serial No -->
			<div class="field">
                <label><bean:message key="LABEL.inSerialNo"/></label>
                <div class="input_box">
                    <html:text property="workListBmRplPartSerialDetailDTO.inSerialNo" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <!-- 교체 Serial No -->
             <div class="field">
                <label><bean:message key="LABEL.outSerialNo"/></label>
                <div class="input_box">
                    <html:text property="workListBmRplPartSerialDetailDTO.outSerialNo" tabindex="110"/>
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
					<html:textarea property="workListBmRplPartSerialDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>