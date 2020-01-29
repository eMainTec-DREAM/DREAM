<%--===========================================================================
재고이동 - 상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.part.adj.stkmove.action.PartAdjStkMoveDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="LABEL.docCountrNo"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var fromWhAc;
var toWhAc;
var ptStockAc;
function loadPage() 
{
	setTitle("partAdjStkMoveDetailDTO.ptStkMoveNo");
	
	setForUpdate();
		
	fromWhAc = new autoC({"partAdjStkMoveDetailDTO.fromWname":"wname"});
    fromWhAc.setTable("TAWAREHOUSE");
    fromWhAc.setKeyName("partAdjStkMoveDetailDTO.fromWcodeId");
    fromWhAc.setAcResultMap({
    	"partAdjStkMoveDetailDTO.fromWcodeId":"wcode_id"
    });
    fromWhAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	});
    fromWhAc.init();
    
    toWhAc = new autoC({"partAdjStkMoveDetailDTO.toWname":"wname"});
    toWhAc.setTable("TAWAREHOUSE");
    toWhAc.setKeyName("partAdjStkMoveDetailDTO.toWcodeId");
    toWhAc.setAcResultMap({
    	"partAdjStkMoveDetailDTO.toWcodeId":"wcode_id"
    });
    toWhAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	});
    toWhAc.init();
    
    ptStockAc = new autoC({"partAdjStkMoveDetailDTO.partNo":"part_no"});
    ptStockAc.setTable("TAPTSTOCK");
    ptStockAc.setKeyName("partAdjStkMoveDetailDTO.partId");
    ptStockAc.setAcResultMap({
    	"partAdjStkMoveDetailDTO.partId":"part_id"
    	,"partAdjStkMoveDetailDTO.ptDesc":"pt_desc"
    	,"partAdjStkMoveDetailDTO.ptSize":"pt_size"
    	,"partAdjStkMoveDetailDTO.model":"model"
    	,"partAdjStkMoveDetailDTO.fromWcodeId":"wcode_id"
    	,"partAdjStkMoveDetailDTO.fromWname":"wname"
    	,"partAdjStkMoveDetailDTO.partGrade":"part_grade"
    	,"partAdjStkMoveDetailDTO.partGradeDesc":"part_grade_desc"
    	,"partAdjStkMoveDetailDTO.ptNameSize":"pt_name_size"
    });
    ptStockAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
 	    ,"wh_categ":"PART"
 	    ,"part_categ":"SPPT"
    	,"stock_qty":"0.1"
  	});
    ptStockAc.setAcDConditionMap({
    	"wcode_id":"partAdjStkMoveDetailDTO.fromWcodeId"
    	,"wname":"partAdjStkMoveDetailDTO.fromWname"
  	});
    ptStockAc.init();
    
    acSysDesc("partAdjStkMoveDetailDTO.partGradeDesc","partAdjStkMoveDetailDTO.partGrade","PART_GRADE",true);
    
	setPartAdjStkMoveDetailState();
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
}

function setPartAdjStkMoveDetailState()
{
	var ptStkMoveStatus = partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatus'].value;
	
	if(ptStkMoveStatus=="C")
	{
		setDisableAll();
		
		hideBtn("CONFIRMPTSTKMOVE");
		showBtn("CANCELPTSTKMOVE");
	}
	else{
		setEnableAll();
		
		showBtn("CONFIRMPTSTKMOVE");
		hideBtn("CANCELPTSTKMOVE");
	}
}

function goInput()
{
	sequenceNextVal('SQAPTSTKMOVE_ID');
	//작성상태 - C 작성중
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatus'].value = "W";
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatusDesc'].value = "W";
	valSysDir('partAdjStkMoveDetailDTO.ptStkMoveStatus', 'partAdjStkMoveDetailDTO.ptStkMoveStatusDesc', 'PTSTKMOVE_STATUS', true);
	
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.regDate'].value = getToday();
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.regId'].value = loginUser.empId;
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.regDept'].value = loginUser.deptId;
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.regEmpDept'].value = loginUser.empName+"/"+loginUser.deptDesc;
	
	//공장명
    if(loginUser.plant!='null'){
    	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.plant'].value = loginUser.plant;
    	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
	
// 	warehouseAc.openLov();
}

function goUpdate()
{
}

function setSequenceVal(sequenceVal)
{	
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveId'].value = sequenceVal;
	partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = sequenceVal;
	partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveNo'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) partAdjStkMoveDetailForm.strutsAction.value = "<%=PartAdjStkMoveDetailAction.DETAIL_INPUT%>";
	else partAdjStkMoveDetailForm.strutsAction.value = '<%=PartAdjStkMoveDetailAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/partAdjStkMoveDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partAdjStkMoveDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveId'].value;
    if (parent.findGridRow)	parent.findGridRow(partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value);
	
	setTitle("partAdjStkMoveDetailDTO.ptStkMoveNo");
    
    getTopPage().afterSaveAll(currentPageId);
    
    setPartAdjStkMoveDetailState();
}

function goConfirmptstkmove()
{
	//저장을 누르지 않고 출고처리시 저장 프로세스 후에 확정처리를 실행합니다.
	
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    var moveQty = partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.moveQty'].value;
    if(moveQty == "" || parseFloat(moveQty) == 0 )
    {
    	alertMessage1("<bean:message key='LABEL.moveQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
    
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG045'/>", function(result){
		if(result){
			//strutsAction 셋팅.
			if(ckCreate(currentPageId)) partAdjStkMoveDetailForm.strutsAction.value = "<%=PartAdjStkMoveDetailAction.DETAIL_INPUT%>";
			else partAdjStkMoveDetailForm.strutsAction.value = '<%=PartAdjStkMoveDetailAction.DETAIL_UPDATE%>';
			
			var actionUrl = contextPath + "/partAdjStkMoveDetail.do";
			XMLHttpPost(actionUrl, FormQueryString(partAdjStkMoveDetailForm), 'beforeConfirmptstkmove');
		}
	});
}
function beforeConfirmptstkmove(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveId'].value;
    if (parent.findGridRow)	parent.findGridRow(partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value);
    
    setTitle("partAdjStkMoveDetailDTO.ptStkMoveNo");
    
    getTopPage().afterSaveAll(currentPageId);
    
    setPartAdjStkMoveDetailState();
    
    partAdjStkMoveDetailForm.strutsAction.value = '<%=PartAdjStkMoveDetailAction.DETAIL_CONFIRM_MOVE%>';
    
    partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.moveDate'].value = getToday();
    
	var actionUrl = contextPath + "/partAdjStkMoveDetail.do";
	XMLHttpPostVal(actionUrl, FormQueryString(partAdjStkMoveDetailForm), 'afterConfirmptstkmove');
}
function afterConfirmptstkmove(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnValue[0]=="E"){
		alert(rtnValue[1]);
	}else if(rtnValue[0]=="S"){
		//확정되었습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG044'/>");
		
		//완료!
		partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatus'].value = "C";
		partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatusDesc'].value = "C";
		valSysDir('partAdjStkMoveDetailDTO.ptStkMoveStatus', 'partAdjStkMoveDetailDTO.ptStkMoveStatusDesc', 'PTSTKMOVE_STATUS', true);
		
		partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveId'].value;
	    if (parent.findGridRow)	parent.findGridRow(partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value);
	    
	    setPartAdjStkMoveDetailState();
	}
}

function goCancelptstkmove()
{
	if(ckCreate(currentPageId)) return;
	
	if(partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatus'].value != "C") return;
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0238'/>", function(result){
		if(result){
			partAdjStkMoveDetailForm.strutsAction.value = '<%=PartAdjStkMoveDetailAction.DETAIL_CANCEL_MOVE%>';
			
			partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.moveDate'].value = getToday();
			
			var actionUrl = contextPath + "/partAdjStkMoveDetail.do";
			XMLHttpPostVal(actionUrl, FormQueryString(partAdjStkMoveDetailForm), 'afterCancelptstkmove');
		}
	});
}
function afterCancelptstkmove(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnValue[0]=="E"){
		alertMessage1(rtnValue[1]);
	}else if(rtnValue[0]=="S"){
		alertMessage1("<bean:message key='MESSAGE.MSG0239'/>");
		
		//완료!
		partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatus'].value = "W";
		partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveStatusDesc'].value = "W";
		valSysDir('partAdjStkMoveDetailDTO.ptStkMoveStatus', 'partAdjStkMoveDetailDTO.ptStkMoveStatusDesc', 'PTSTKMOVE_STATUS', true);
		
		partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = partAdjStkMoveDetailForm.elements['partAdjStkMoveDetailDTO.ptStkMoveId'].value;
	    if (parent.findGridRow)	parent.findGridRow(partAdjStkMoveDetailForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value);
	    
	    setPartAdjStkMoveDetailState();
	}
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partAdjStkMoveDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="partAdjStkMoveCommonDTO.ptStkMoveId" />
	<html:hidden property="partAdjStkMoveDetailDTO.ptStkMoveId" />
	<html:hidden property="partAdjStkMoveDetailDTO.ptStkMoveStatus" />
	<html:hidden property="partAdjStkMoveDetailDTO.plant" />
	<html:hidden property="partAdjStkMoveDetailDTO.plantDesc" />
	<html:hidden property="partAdjStkMoveDetailDTO.fromWcodeId" />
	<html:hidden property="partAdjStkMoveDetailDTO.toWcodeId" />
	<html:hidden property="partAdjStkMoveDetailDTO.partId" />
	<html:hidden property="partAdjStkMoveDetailDTO.partGrade" />
	<html:hidden property="partAdjStkMoveDetailDTO.regDept" />
	<html:hidden property="partAdjStkMoveDetailDTO.regDate" />
	<html:hidden property="partAdjStkMoveDetailDTO.regId" />
 	
		<div class="article_box">
			<div class="form_box">
				<!-- 이동# -->
				<div class="field">
					<label><bean:message key="LABEL.moveNo"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.ptStkMoveNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.ptStkMoveStatusDesc" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<!-- 처리일자 -->
				<div class="field">
					<label><bean:message key="LABEL.moveDate"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.moveDate" tabindex="30" readonly="true"/>
					</div>
				</div>
				<!-- 등록자 -->
				<div class="field">
					<label><bean:message key="LABEL.regId"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.regEmpDept" tabindex="40"  readonly="true"/>
					</div>
				</div>
				<!-- From 창고 -->
				<div class="field">
	        	 	<label class="check"><bean:message key="LABEL.fromWcodeDesc"/></label>
	        	 	<div class="input_box">
	        	 		<html:text property="partAdjStkMoveDetailDTO.fromWname" tabindex="50"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	        	 	</div>
	       	 	</div>
				<!-- To 창고 -->
				<div class="field">
	        	 	<label class="check"><bean:message key="LABEL.toWcodeDesc"/></label>
	        	 	<div class="input_box">
	        	 		<html:text property="partAdjStkMoveDetailDTO.toWname" tabindex="60"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	        	 	</div>
	       	 	</div>
	       	 	<!-- 부품번호 -->
	       	 	<div class="field">
	        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
	        	 	<div class="input_box">
	        	 		<html:text property="partAdjStkMoveDetailDTO.partNo" tabindex="70"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	        	 	</div>
	       	 	</div>
	       	 	<!-- 품명/규격 -->
	       	 	<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.ptNameSize" tabindex="80"  readonly="true"/>
					</div>
				</div>
	       	 	<!-- 품명 -->
	       	 	<div class="field">
					<label><bean:message key="LABEL.ptDesc"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.ptDesc" tabindex="80"  readonly="true"/>
					</div>
				</div>
	       	 	<!-- 규격 -->
	       	 	<div class="field">
					<label><bean:message key="LABEL.ptSize"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.ptSize" tabindex="90"  readonly="true"/>
					</div>
				</div>
	       	 	<!-- 모델 -->
	       	 	<div class="field">
					<label><bean:message key="LABEL.model"/></label>
					<div class="input_read">
						<html:text property="partAdjStkMoveDetailDTO.model" tabindex="100"  readonly="true"/>
					</div>
				</div>
	       	 	<!-- 재고등급 -->
	       	 	<div class="field">
	        	 	<label class="check"><bean:message key="LABEL.partGrade"/></label>
	        	 	<div class="input_box">
	        	 		<html:text property="partAdjStkMoveDetailDTO.partGradeDesc" tabindex="110"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	        	 	</div>
	       	 	</div>
	       	 	<!-- 이동수량 -->
	       	 	<div class="field">
					<label><bean:message key="LABEL.moveQty"/></label>
					<div class="input_box">
						<html:text property="partAdjStkMoveDetailDTO.moveQty" tabindex="120" styleClass="num"/>
					</div>
				</div>
	       	 	<!-- 비고 -->
	       	 	<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="partAdjStkMoveDetailDTO.remark" styleClass="ta50" tabindex="130" />
					</div>
				</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>