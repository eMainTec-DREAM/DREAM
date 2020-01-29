<%--===========================================================================
검사항목
author  jung7126
version $Id: maPmMstrPointDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.MaPmMstrPointDetailAction"%>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html>
<head>
<!-- 점검항목-->
<title><bean:message key="LABEL.checkPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
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
<!--//
var stdCheckPointAc, eqAsmbAc, uomAc;

function loadPage() 
{
	setSlideImage();
	
	$('#blankDiv').hide();
	
	if("maEqMstrPmInsPointList"!= parent.currentPageId)
	{
		if("CINS"==parent.parent.maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmType'].value)
		{
			$('#stdDiv').hide();
			$('#blankDiv').show();
		}	
	}
 	if(ckCreate(currentPageId)) goInput();
	setTitle("maPmMstrPointDetailDTO.checkPoint");
	setForUpdate();
	
	//사용여부  AC
    acSysDesc("maPmMstrPointDetailDTO.isActive","maPmMstrPointDetailDTO.isActive","IS_USE",true);
	//누적값 여부  AC
    acSysDesc("maPmMstrPointDetailDTO.isRunValue","maPmMstrPointDetailDTO.isRunValue","IS_USE",true);
	//수치/판정구분  AC
    acSysDesc("maPmMstrPointDetailDTO.checkTypeDesc","maPmMstrPointDetailDTO.checkType","CHECK_TYPE",true);
	
    //표준점검항목 AC 
    stdCheckPointAc = new autoC({"maPmMstrPointDetailDTO.stwrkPointDesc":"check_point"});
    stdCheckPointAc.setTable("TASTDCHECKPOINT");
    stdCheckPointAc.setKeyName("maPmMstrPointDetailDTO.stwrkPointId");
    stdCheckPointAc.setAcResultMap({
        "maPmMstrPointDetailDTO.stwrkPointId":"std_check_point_id"
    });
    stdCheckPointAc.init();
    
    eqAsmbAc = new autoC({"maPmMstrPointDetailDTO.eqAsmbToDesc":"description"});
    eqAsmbAc.setTable("TAEQASMBBYPM");
    eqAsmbAc.setKeyName("maPmMstrPointDetailDTO.eqAsmbId");
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    eqAsmbAc.setAcDConditionMap({
    	"pm_id":"maPmMstrCommonDTO.pmId"
    });
    eqAsmbAc.setAcResultMap({
        "maPmMstrPointDetailDTO.eqAsmbId":"eqasmb_id"
        ,"maPmMstrPointDetailDTO.eqAsmbDesc":"description"
        ,"maPmMstrPointDetailDTO.eqAsmbToDesc":"FULL_DESC"
    });
    eqAsmbAc.init();
    
    //단위
    uomAc = new autoC({"maPmMstrPointDetailDTO.uom":"description"});
    uomAc.setTable("TACDUSRD");
    uomAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"UOM"
  	   });
    uomAc.init();
}

/* 
 * 부위보기
 * TAPGLINKEDFUNC에 등록하여 사용한다.
 */
function goAsmbLink(_pageId)
{
	var pmId = maPmMstrPointDetailForm.elements['maPmMstrCommonDTO.pmId'].value;
	var eqAsmbId = maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.eqAsmbId'].value;

 	goAsmbDetail(pmId, eqAsmbId);
}

//상세 설비번호 가져오기
function getParentEqId(){
	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.equipId'].value = parent.getEquipId();
}

function goInput(){
 	// 시퀀스를 조회한다.
 	sequenceNextVal('SQAPM_POINT_ID');
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.isActive'].value='Y';
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.isRunValue'].value='N';
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.fitRate'].value = 1;

	//리스트로 부터 넘겨받은 코드를 대입하고 언어표기
	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.checkType'].value = 
		maPmMstrPointDetailForm.elements['maPmMstrCommonDTO.pointDetailCheckTypeId'].value;
	valSysDirCode('maPmMstrPointDetailDTO.checkType', 'maPmMstrPointDetailDTO.checkTypeDesc', 'CHECK_TYPE','', true);
}

function setSequenceVal(sequenceVal)
{
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.pmPointId'].value = sequenceVal;
}

function goSave()
{
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) maPmMstrPointDetailForm.strutsAction.value = '<%=MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INPUT%>';
	else maPmMstrPointDetailForm.strutsAction.value = '<%= MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPmMstrPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPmMstrPointDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.pmPointId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

function valPointAsmb(){
	getParentEqId();
	valEqAsmb('maPmMstrPointDetailDTO.eqAsmbId','maPmMstrPointDetailDTO.eqAsmbDesc', true, M$('maPmMstrPointDetailDTO.equipId').value);
	
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPmMstrPointDetailForm.elements['maPmMstrCommonDTO.pmPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

function loadSlideImages()
{
	var url = contextPath + "/maPmMstrPointDetail.do";

    var oldSAction = maPmMstrPointDetailForm.elements['strutsAction'].value;
    maPmMstrPointDetailForm.elements['strutsAction'].value = '<%=MaPmMstrPointDetailAction.DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maPmMstrPointDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    maPmMstrPointDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.pmPointId'].value +
                "&" + "maDocImgCommonDTO.objectType=PM_POINT"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.checkPoint'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPmMstrPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="maPmMstrCommonDTO.pmPointId"/>
	<html:hidden property="maPmMstrCommonDTO.equipId"/>
	<html:hidden property="maPmMstrCommonDTO.pointDetailPageId"/>
	<html:hidden property="maPmMstrCommonDTO.pointDetailCheckTypeId"/>
	<html:hidden property="maPmMstrPointDetailDTO.pmPointId"/>
	<html:hidden property="maPmMstrPointDetailDTO.pmId"/>
	<html:hidden property="maPmMstrPointDetailDTO.eqAsmbId"/>
	<html:hidden property="maPmMstrPointDetailDTO.checkType"/>
	<html:hidden property="maPmMstrPointDetailDTO.equipId"/>
	<html:hidden property="maPmMstrPointDetailDTO.stwrkPointId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 사진 -->
			<div class="field_img">
				<label><bean:message key="LABEL.photo"/></label>
				<div class="img_box" name="maPmMstrPointDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
				</div>
			</div>
			<!-- 점검순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.stepNum" tabindex="10" styleClass="num"/>
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.checkPoint" tabindex="15"/>
				</div>
			</div>
			<!-- 점검부위 -->
			<div class="field">
				<label><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.eqAsmbDesc" tabindex="20" />
				</div>
			</div>
			<!-- 점검부위 ID to Desc -->
			<div class="field">
				<label><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.eqAsmbToDesc" tabindex="25" />
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.fitBasis" tabindex="30"/>
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.checkMethod" tabindex="35"/>
				</div>
			</div>
			<!-- 수치판정구분 -->
			<div class="field">
				<label><bean:message key="LABEL.checkType"/></label>
				<div class="input_read">
					<html:text property="maPmMstrPointDetailDTO.checkTypeDesc" tabindex="40" readonly="true"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.isActive" tabindex="45"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 하한값 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMin"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.checkMin" tabindex="50" styleClass="ty_num"/>
				</div>
			</div>		
			<div class="field">	</div>
			<!-- 기준값 -->
			<div class="field">
				<label><bean:message key="LABEL.checkBasisVal"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.checkBasisVal" tabindex="55" styleClass="ty_num"/>
				</div>
			</div>
			<!-- 표준점검항목 -->
            <div class="field" id="stdDiv">
                <label><bean:message key="LABEL.stdPmCheckPoint"/></label>
                <div class="input_box">
                    <html:text property="maPmMstrPointDetailDTO.stwrkPointDesc" tabindex="60"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 상한값 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMax"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.checkMax" tabindex="65" styleClass="ty_num"/>
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label><bean:message key="LABEL.uom"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.uom" tabindex="70"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 누적값 여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isRunValue"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.isRunValue" tabindex="75"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 배율 -->
			<div class="field">
				<label><bean:message key="LABEL.fitRate"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.fitRate" tabindex="80" styleClass="num"/>
				</div>
			</div>	
			<div class="field_long_blank"></div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPmMstrPointDetailDTO.remark" styleClass="ta50" tabindex="85"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>