<%--===========================================================================
청소설비
author  kim21017
version $Id: workPmListRplEquipDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.WorkPmListEquipDetailAction"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html>
<head>
<!-- 자재-->
<title><bean:message key="TAB.workPmListEquipList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
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
var equipDescAc;
var eqAsmbAc;
function loadPage() 
{
	setSlideImage();
	
	setTitle("workPmListEquipDetailDTO.equipDesc","workPmListEquipDetailDTO.eqLocDesc");
	setForUpdate();

	equipDescAc = new autoC({"workPmListEquipDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "eq_status":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setKeyName("workPmListEquipDetailDTO.equipId");
    equipDescAc.setAcResultMap({
        "workPmListEquipDetailDTO.equipId":"equip_id"
        ,"workPmListEquipDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipDescAc.init();
    
    eqAsmbAc = new autoC({"workPmListEquipDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setKeyName("workPmListEquipDetailDTO.eqAsmbId");
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    eqAsmbAc.setAcDConditionMap({
    	"equip_id":"workPmListEquipDetailDTO.equipId"
    });
    eqAsmbAc.setAcResultMap({
        "workPmListEquipDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
    
    acSysDesc("workPmListEquipDetailDTO.isActive","workPmListEquipDetailDTO.isActive","IS_USE",true);
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

function loadSlideImages()
{
	var url = contextPath + "/workPmListRplEquipDetail.do";

    var oldSAction = workPmListEquipDetailForm.elements['strutsAction'].value;
    workPmListEquipDetailForm.elements['strutsAction'].value = '<%=WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(workPmListEquipDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    workPmListEquipDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=WorkPmListEquipDetailAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" +workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.pmEquipId'].value +
                "&" + "maDocImgCommonDTO.objectType=PMWMSTR"+
                "&" + "maDocImgCommonDTO.objectDesc="+ workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.equipDesc'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPMEQUIP_ID');
	workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.isActive'].value = 'Y';
	
	equipDescAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.pmEquipId'].value = sequenceVal;
}

function goUpdate(){
	 setDisable(".disableDiv");
}

function goSave(){
	
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) workPmListEquipDetailForm.strutsAction.value = '<%=WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_INPUT%>';
	else workPmListEquipDetailForm.strutsAction.value = '<%= WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmListRplEquipDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmListEquipDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.pmEquipId'].value);
	if (parent.parent.parent.findGridRow) parent.parent.parent.findGridRow(workPmListEquipDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
	
	setTitle("workPmListEquipDetailDTO.equipDesc","workPmListEquipDetailDTO.eqLocDesc");
    getTopPage().afterSaveAll(currentPageId);
}

/*
 * 설비보기
 */
function goMachequipmentLink(_pageId)
{
	var equipId = workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.equipId'].value;
	var eqctgType = workPmListEquipDetailForm.elements['workPmListEquipDetailDTO.eqctgType'].value;

	if(typeof equipId == "undefined" || equipId == "") {
		goMachEquipList();
		return;
	}else{
		goEquipDetail(equipId, eqctgType);
	}
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmListRplEquipDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="workPmListEquipDetailDTO.pmEquipId"/>
	<html:hidden property="workPmListEquipDetailDTO.equipId"/>
	<html:hidden property="workPmListEquipDetailDTO.eqAsmbId"/>
    <html:hidden property="workPmListEquipDetailDTO.lastSchDate"/>
    <html:hidden property="workPmListEquipDetailDTO.nextSchDate"/>
    <html:hidden property="workPmListEquipDetailDTO.lastCpltDate"/>
    <html:hidden property="workPmListEquipDetailDTO.nextPlanDate"/>
    <html:hidden property="workPmListEquipDetailDTO.oldInitWrkDate"/>
    <html:hidden property="workPmListEquipDetailDTO.eqctgType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 설비 -->
			<div class="field disableDiv">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="workPmListEquipDetailDTO.equipDesc" tabindex="10"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
        	 <!-- 사진 -->
             <div class="field_img" >
				<label>사진</label>
				<div class="img_box"  name="workPmListEquipDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
					
				</div>
             </div>
			<!-- 부위 -->
			<div class="field disableDiv">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="workPmListEquipDetailDTO.eqAsmbDesc" tabindex="13" />
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<div class="field">
                <!-- 최초작업시작일(등록) -->
                <label class="check"><bean:message key="LABEL.initWrkDate"/></label>
                <div class="input_box">
                    <html:text property="workPmListEquipDetailDTO.initWrkDate" tabindex="15" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
			<div class="field">
                <!-- 사용여부 -->
                <label class="check"><bean:message key="LABEL.isActive"/></label>
                <div class="input_box">
                    <html:text property="workPmListEquipDetailDTO.isActive" tabindex="20"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 위치 -->
			<div class="field_long">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read">
					<html:text property="workPmListEquipDetailDTO.eqLocDesc" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="workPmListEquipDetailDTO.remark" styleClass="ta50" tabindex="40" />
                </div>
            </div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>