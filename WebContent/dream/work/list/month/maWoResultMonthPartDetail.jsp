<%--===========================================================================
작업결과(월간작업일정) 투입자재
author  kim21017
version $Id: maWoResultMonthPartDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultPartDetailAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
String partGrade = MwareConfig.getPartGrade();
%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자재-->
<title><bean:message key="LABEL.parts"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//자동완성
var partNoAc;
var wareHouseAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maWoResultPartDetailDTO.partNo","maWoResultPartDetailDTO.partDesc");
	setForUpdate();
	
	setFunction();
}

function setFunction()
{
	partNoAc = new autoC({"maWoResultPartDetailDTO.partNo":"part_no"});
    partNoAc.setAcDConditionMap({
    	"wcode_id" : "maWoResultPartDetailDTO.wcodeId",
    	"wcode_desc" : "maWoResultPartDetailDTO.wcodeDesc"
  	});
    partNoAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
    	"maWoResultPartDetailDTO.partDesc":"description",
    	"maWoResultPartDetailDTO.partSize":"pt_size",
        "maWoResultPartDetailDTO.partId":"part_id"
    });
    partNoAc.setKeyName("maWoResultPartDetailDTO.partId");
    partNoAc.init();
    
	wareHouseAc = new autoC({"maWoResultPartDetailDTO.wcodeDesc":"wname"});
    wareHouseAc.setAcDisplay("WNAME");
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y",
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maWoResultPartDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.setKeyName("maWoResultPartDetailDTO.wcodeId");
    wareHouseAc.init();

	//부품등급 AC
    acSysDesc("maWoResultPartDetailDTO.partGradeDesc","maWoResultPartDetailDTO.partGrade","PART_GRADE",true);
}

function beforeAutoCmpt(code)
{
	var isValid = true;
	if("maWoResultPartDetailDTO.partNo" == code)
	{
		M$('maWoResultPartDetailDTO.equipId').value = parent.parent.M$('maWoResultMstrDetailDTO.equipId').value;
		M$('maWoResultPartDetailDTO.equipDesc').value = parent.parent.M$('maWoResultMstrDetailDTO.equipDesc').value;
	}
	return isValid;
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOPART_ID');
	
	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.partGrade'].value = '<%=partGrade%>';
	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('maWoResultPartDetailDTO.partGrade', 'maWoResultPartDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	//부서창고로 기본 세팅
    maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.wcodeId'].value = loginUser.wcodeId;
    maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.wcodeDesc'].value = loginUser.wname;
}

function goUpdate(){
	setReadOnly('maWoResultPartDetailDTO.partNo');
	setReadOnly('maWoResultPartDetailDTO.partGradeDesc');
	setReadOnly('maWoResultPartDetailDTO.wcodeDesc');
}

function setSequenceVal(sequenceVal)
{
	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.woPartId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) {
		maWoResultPartDetailForm.strutsAction.value = '<%=MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_INPUT%>';
		var actionUrl = contextPath + "/maWoResultPartDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoResultPartDetailForm), 'afterSave');
	}
	else {
		if(maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.ptisslistId'].value == "") {
			if(parseInt(maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.useQty'].value)>
			parseInt(maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.stockQty'].value)){
				//재고수량을 초과했습니다. 계속하시겠습니까?
				getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0248'/>", function(result){
		            if(result){
		            	maWoResultPartDetailForm.strutsAction.value = '<%= MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_UPDATE %>';
		            	var actionUrl = contextPath + "/maWoResultPartDetail.do";
		        		XMLHttpPost(actionUrl, FormQueryString(maWoResultPartDetailForm), 'afterSave');
		            }
		            else{
		            	closeModal();
		            }
		        });
			}
			else {
				maWoResultPartDetailForm.strutsAction.value = '<%= MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_UPDATE %>';
            	var actionUrl = contextPath + "/maWoResultPartDetail.do";
        		XMLHttpPost(actionUrl, FormQueryString(maWoResultPartDetailForm), 'afterSave');
			}
		}
		else {
			checkLinkedUseQty();
		}
	}
}

function checkLinkedUseQty() {
	maWoResultPartDetailForm.strutsAction.value = '<%= MaWoResultPartDetailAction.WO_RESULT_PART_QTY_CHECK %>';
	var actionUrl = contextPath + "/maWoResultPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoResultPartDetailForm), 'afterCheckLinkedUseQty');
}

function afterCheckLinkedUseQty(ajaxXmlDoc) {
	var valid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if('S' == valid)
	{
		maWoResultPartDetailForm.strutsAction.value = '<%= MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_UPDATE %>';
    	var actionUrl = contextPath + "/maWoResultPartDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoResultPartDetailForm), 'afterSave');
	}
	else
	{
		//출고수량을 초과하였습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0249'/>");
		closeModal();
	}
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.woPartId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("maWoResultPartDetailDTO.partNo","maWoResultPartDetailDTO.partDesc");
}

/**
 * 저장후생성후 호출
 */
function afterSavenew() {
	sequenceNextVal('SQAWOPART_ID');
	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.partNo'].value = '';
	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.partId'].value = '';
	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.partDesc'].value = '';
}

function afterSetValue(type){
	getStockQty();
}

/**
 * 재고 확인
 */
function getStockQty()
{
	var wcodeId = maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.wcodeId'].value;
	var partId  = maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.partId'].value;
	if(wcodeId !=""&&partId!=""){
		var actionUrl = contextPath + "/maWoResultPartDetail.do";
		var param =  "&strutsAction=" + '<%= MaWoResultPartDetailAction.WO_RESULT_PART_STOCK_CHECK %>'
		          +  "&" + FormQueryString(maWoResultPartDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setStockQty');
	}else{
		maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.stockQty'].value = '';
	}
}

var isValid = 0;
function setStockQty(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.stockQty'].value = isValid;
    }
    if(maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.stockQty'].value==''){
    	maWoResultPartDetailForm.elements['maWoResultPartDetailDTO.stockQty'].value = '0';
    }
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoResultPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultPartDetailDTO.woPartId"/>
	<html:hidden property="maWoResultPartDetailDTO.partId"/>
	<html:hidden property="maWoResultPartDetailDTO.wcodeId"/>
	<html:hidden property="maWoResultPartDetailDTO.partGrade"/>
	<html:hidden property="maWoResultPartDetailDTO.equipId"/>
	<html:hidden property="maWoResultPartDetailDTO.equipDesc"/>
	<html:hidden property="maWoResultPartDetailDTO.ptisslistId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부품번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.partNo"/></label>
				<div class="input_box">
					<html:text property="maWoResultPartDetailDTO.partNo" tabindex="10" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 부품명 -->
			<div class="field">
				<label><bean:message key="LABEL.partDesc"/></label>
				<div class="input_read">
					<html:text property="maWoResultPartDetailDTO.partDesc" readonly="true" />
				</div>
			</div>
			<!-- 규격 -->
			<div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
				<div class="input_read">
					<html:text property="maWoResultPartDetailDTO.partSize" readonly="true" />
				</div>
			</div>
			<!-- 사용수량 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.useQty"/></label>
				<div class="input_box">
					<html:text property="maWoResultPartDetailDTO.useQty" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 창고 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.useWareHouse"/></label>
				<div class="input_box">
					<html:text property="maWoResultPartDetailDTO.wcodeDesc" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field" style="display:none;">
				<label><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="maWoResultPartDetailDTO.partGradeDesc" tabindex="40" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 현재고 -->
			<div class="field">
				<label><bean:message key="LABEL.stockQty"/></label>
				<div class="input_read">
					<html:text property="maWoResultPartDetailDTO.stockQty" tabindex="50" readonly="true" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultPartDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/work/list/month/maWoResultMonthPartDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/list/month/maWoResultMonthPartDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>