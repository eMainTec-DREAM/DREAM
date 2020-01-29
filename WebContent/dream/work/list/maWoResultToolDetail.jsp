<%--===========================================================================
투입공기구
author  kim21017
version $Id: maWoResultToolDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultToolDetailAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 공기구-->
<title><bean:message key="LABEL.toolParts"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변성 */
var partNoAc;
var wareHouseAc;
function loadPage() 
{	
	setTitle("maWoResultToolDetailDTO.partNo","maWoResultToolDetailDTO.partDesc");
	setForUpdate();

    partNoAc = new autoC({"maWoResultToolDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTSTOOLS");
    partNoAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    partNoAc.setAcResultMap({
    	"maWoResultToolDetailDTO.partDesc":"description",
    	"maWoResultToolDetailDTO.partSize":"pt_size",
        "maWoResultToolDetailDTO.partId":"part_id"
    });
    partNoAc.setKeyName("maWoResultToolDetailDTO.partId");
    partNoAc.init();

	wareHouseAc = new autoC({"maWoResultToolDetailDTO.wcodeDesc":"wname"});
    wareHouseAc.setAcDisplay("WNAME");
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y",
    	"wh_categ":"TOOL",
    	"plant" : loginUser.plant
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maWoResultToolDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.setKeyName("maWoResultToolDetailDTO.wcodeId");
    wareHouseAc.init();
    
	//자재등급 AC
    acSysDesc("maWoResultToolDetailDTO.partGradeDesc","maWoResultToolDetailDTO.partGradeId","PART_GRADE",true);

    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
}

function afterAutoCmpt (code)
{
	getStockQty();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOTOOL_ID');
	
	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.partGradeId'].value = '<%=partGrade%>';
	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('maWoResultToolDetailDTO.partGradeId', 'maWoResultToolDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	//부서창고로 기본 세팅
    maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.wcodeId'].value = loginUser.twcodeId;
    maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.wcodeDesc'].value = loginUser.twname;
	
    partNoAc.openLov();
}

function goUpdate(){
	setReadOnly('maWoResultToolDetailDTO.partNo');
	setReadOnly('maWoResultToolDetailDTO.partGradeDesc');
	setReadOnly('maWoResultToolDetailDTO.wcodeDesc');
}

function setSequenceVal(sequenceVal)
{
	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.woToolId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(parseInt(maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.useQty'].value) <= 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
	
	if(ckCreate(currentPageId)) {
		maWoResultToolDetailForm.strutsAction.value = '<%=MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_INPUT%>';
		var actionUrl = contextPath + "/maWoResultToolDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoResultToolDetailForm), 'afterSave');
	}else {
		if(parseInt(maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.useQty'].value)>
		parseInt(maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.stockQty'].value)){
			//재고수량을 초과했습니다. 계속하시겠습니까?
			getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0248'/>", function(result){
	            if(result){
	            	maWoResultToolDetailForm.strutsAction.value = '<%= MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_UPDATE %>';
	            	var actionUrl = contextPath + "/maWoResultToolDetail.do";
	        		XMLHttpPost(actionUrl, FormQueryString(maWoResultToolDetailForm), 'afterSave');
	            }
	            else{
	            	closeModal();
	            }
	        });
		}
		else {
			maWoResultToolDetailForm.strutsAction.value = '<%= MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_UPDATE %>';
           	var actionUrl = contextPath + "/maWoResultToolDetail.do";
       		XMLHttpPost(actionUrl, FormQueryString(maWoResultToolDetailForm), 'afterSave');
		}
	}
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.woToolId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("maWoResultToolDetailDTO.partNo","maWoResultToolDetailDTO.partDesc");
}

/**
 * 저장후생성후 호출
 */
function afterSavenew() {
	sequenceNextVal('SQAWOTOOL_ID');
	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.partNo'].value = '';
	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.partId'].value = '';
	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.partDesc'].value = '';
}

function afterSetValue(type){
	if(""==maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.useQty'].value){
		maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.useQty'].value = "1";
	}
	getStockQty();
}

/**
 * 재고 확인
 */
function getStockQty()
{
	var wcodeId = maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.wcodeId'].value;
	var partId  = maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.partId'].value;
	if(wcodeId !=""&&partId!=""){
		var actionUrl = contextPath + "/maWoResultToolDetail.do";
		var param =  "&strutsAction=" + '<%= MaWoResultToolDetailAction.WO_RESULT_TOOL_STOCK_CHECK %>'
		          +  "&" + FormQueryString(maWoResultToolDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setStockQty');
	}else{
		maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.stockQty'].value = '';
	}
}

var isValid = 0;
function setStockQty(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.stockQty'].value = isValid;
    }
    if(maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.stockQty'].value==''){
    	maWoResultToolDetailForm.elements['maWoResultToolDetailDTO.stockQty'].value = '0';
    }
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoResultToolDetail">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultToolDetailDTO.woToolId"/>
	<html:hidden property="maWoResultToolDetailDTO.partId"/>
	<html:hidden property="maWoResultToolDetailDTO.wcodeId"/>
	<html:hidden property="maWoResultToolDetailDTO.partGradeId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 공기구번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.toolPartsNo"/></label>
				<div class="input_box">
					<html:text property="maWoResultToolDetailDTO.partNo" tabindex="10" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 공기구명 -->
			<div class="field">
				<label><bean:message key="LABEL.toolPartsDesc"/></label>
				<div class="input_read">
					<html:text property="maWoResultToolDetailDTO.partDesc" readonly="true" />
				</div>
			</div>
			<!-- 규격 -->
			<div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
				<div class="input_read">
					<html:text property="maWoResultToolDetailDTO.partSize" readonly="true" />
				</div>
			</div>
			<!-- 사용수량 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.useQty"/></label>
				<div class="input_box">
					<html:text property="maWoResultToolDetailDTO.useQty" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 창고 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.wname"/></label>
				<div class="input_box">
					<html:text property="maWoResultToolDetailDTO.wcodeDesc" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 회수수량 -->
			<div class="field">
				<label><bean:message key="LABEL.disUseRtnQty"/></label>
				<div class="input_box">
					<html:text property="maWoResultToolDetailDTO.disUseRtnQty" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<div class="field" style="display:none;">
				<label><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="maWoResultToolDetailDTO.partGradeDesc" tabindex="50" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 현재고 -->
			<div class="field">
				<label><bean:message key="LABEL.stockQty"/></label>
				<div class="input_read">
					<html:text property="maWoResultToolDetailDTO.stockQty" tabindex="60" readonly="true" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultToolDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>