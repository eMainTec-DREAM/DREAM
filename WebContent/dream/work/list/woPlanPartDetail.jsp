<%--===========================================================================
작업계획목록 - 투입부품
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WoPlanPartDetailAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
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

/** 자동완성 변성 */
var partNoAc;
var wareHouseAc;
function loadPage() 
{
	setTitle("woPlanPartDetailDTO.partNo","woPlanPartDetailDTO.partDesc");
	setForUpdate();

    partNoAc = new autoC({"woPlanPartDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
    	"woPlanPartDetailDTO.partDesc":"full_desc",
        "woPlanPartDetailDTO.partId":"part_id"
    });
    partNoAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    partNoAc.setAcDConditionMap({
    	"wcode_id" : "woPlanPartDetailDTO.wcodeId"
    	,"wcode_desc" : "woPlanPartDetailDTO.wcodeDesc"
    });
    partNoAc.setKeyName("woPlanPartDetailDTO.partId");
    partNoAc.init();

	wareHouseAc = new autoC({"woPlanPartDetailDTO.wcodeDesc":"wname"});
    wareHouseAc.setAcDisplay("WNAME");
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "woPlanPartDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.setKeyName("woPlanPartDetailDTO.wcodeId");
    wareHouseAc.init();
    
	//자재등급 AC
    acSysDesc("woPlanPartDetailDTO.partGradeDesc","woPlanPartDetailDTO.partGrade","PART_GRADE",true);
	
	if(ckCreate(currentPageId)) goInput();
}

function afterAutoCmpt (code)
{
	getStockQty();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOPART_ID');
	
	woPlanPartDetailForm.elements['woPlanPartDetailDTO.partGrade'].value = '<%=partGrade%>';
	woPlanPartDetailForm.elements['woPlanPartDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('woPlanPartDetailDTO.partGrade', 'woPlanPartDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	//부서창고로 기본 세팅
    woPlanPartDetailForm.elements['woPlanPartDetailDTO.wcodeId'].value = loginUser.wcodeId;
    woPlanPartDetailForm.elements['woPlanPartDetailDTO.wcodeDesc'].value = loginUser.wname;
    
  	//사용수량 default 1
    woPlanPartDetailForm.elements['woPlanPartDetailDTO.useQty'].value = "1";
  	
    partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	woPlanPartDetailForm.elements['woPlanPartDetailDTO.woPartId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(parseInt(woPlanPartDetailForm.elements['woPlanPartDetailDTO.useQty'].value) <= 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
	//사용수량이 현재고보다 높으면 저장 불가처리.
// 	if(parseInt(woPlanPartDetailForm.elements['woPlanPartDetailDTO.useQty'].value)>
// 	parseInt(woPlanPartDetailForm.elements['woPlanPartDetailDTO.stockQty'].value)){
// 		alertMessage1("<bean:message key='MESSAGE.MSG0067'/>");
// 		return;
// 	}
	if(ckCreate(currentPageId)) woPlanPartDetailForm.strutsAction.value = '<%=WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_INPUT%>';
	else woPlanPartDetailForm.strutsAction.value = '<%= WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/woPlanPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(woPlanPartDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(woPlanPartDetailForm.elements['woPlanPartDetailDTO.woPartId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function afterSetValue(type){
	if(""==woPlanPartDetailForm.elements['woPlanPartDetailDTO.useQty'].value){
		woPlanPartDetailForm.elements['woPlanPartDetailDTO.useQty'].value = "1";
	}
	getStockQty();
}

/**
 * 재고 확인
 */
function getStockQty()
{
	var wcodeId = woPlanPartDetailForm.elements['woPlanPartDetailDTO.wcodeId'].value;
	var partId  = woPlanPartDetailForm.elements['woPlanPartDetailDTO.partId'].value;
	if(wcodeId !=""&&partId!=""){
		var actionUrl = contextPath + "/woPlanPartDetail.do";
		var param =  "&strutsAction=" + '<%= WoPlanPartDetailAction.WO_RESULT_PART_STOCK_CHECK %>'
		          +  "&" + FormQueryString(woPlanPartDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setStockQty');
	}else{
		woPlanPartDetailForm.elements['woPlanPartDetailDTO.stockQty'].value = '';
	}
}

var isValid = 0;
function setStockQty(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	woPlanPartDetailForm.elements['woPlanPartDetailDTO.stockQty'].value = isValid;
    }
    if(woPlanPartDetailForm.elements['woPlanPartDetailDTO.stockQty'].value==''){
    	woPlanPartDetailForm.elements['woPlanPartDetailDTO.stockQty'].value = '0';
    }
}

/* audit Trail */
function goAudtrailLink()
{	
	var objectId = woPlanPartDetailForm.elements['woPlanPartDetailDTO.woPartId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/woPlanPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="woPlanCommonDTO.wkOrId"/>
	<html:hidden property="woPlanPartDetailDTO.woPartId"/>
	<html:hidden property="woPlanPartDetailDTO.partId"/>
	<html:hidden property="woPlanPartDetailDTO.wcodeId"/>
	<html:hidden property="woPlanPartDetailDTO.partGrade"/>
	<html:hidden property="woPlanPartDetailDTO.isSerialPart"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부품번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.partNo"/></label>
				<div class="input_box">
					<html:text property="woPlanPartDetailDTO.partNo" tabindex="10" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 부품명/규격 -->
			<div class="field">
				<label><bean:message key="LABEL.partNameSize"/></label>
				<div class="input_read">
					<html:text property="woPlanPartDetailDTO.partDesc" readonly="true" />
				</div>
			</div>
			<div class="field">
                <label><bean:message key="LABEL.partGrade"/></label>
                <div class="input_box">
                    <html:text property="woPlanPartDetailDTO.partGradeDesc" tabindex="40" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 창고 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.useWareHouse"/></label>
				<div class="input_box">
					<html:text property="woPlanPartDetailDTO.wcodeDesc" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 사용수량 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.useQty"/></label>
                <div class="input_box">
                    <html:text property="woPlanPartDetailDTO.useQty" tabindex="20" styleClass="num"/>
                </div>
            </div>
			<!-- 현재고 -->
			<div class="field">
				<label><bean:message key="LABEL.stockQty"/></label>
				<div class="input_read">
					<html:text property="woPlanPartDetailDTO.stockQty" tabindex="50" readonly="true" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="woPlanPartDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>