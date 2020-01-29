<%--===========================================================================
설비부위
author  kim21017
version $Id: maEqMstrAsmbDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrAsmbDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>

<%@ page import="common.bean.User"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html>
<head>
<!-- 설비부위-->
<title><bean:message key="TAB.maEqMstrAsmbList"/></title>
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
var pEqAc, partNoAc;
var isNew = false;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maEqMstrAsmbDetailDTO.eqAsmbDesc");
	setForUpdate();
	
	setSlideImage();
	
//    setDisable(document.getElementsByName("disableDiv"));
	
	acSysDesc("maEqMstrAsmbDetailDTO.isUse","maEqMstrAsmbDetailDTO.isUse","IS_USE",true);
	
	// 상위부위명 자동완성
    pEqAc = new autoC({"maEqMstrAsmbDetailDTO.peqAsmbDesc":"full_desc"});
    pEqAc.setTable("TAEQASMB");
    pEqAc.setAcConditionMap({
       "comp_no": loginUser.compNo
     , "is_use" : "Y"
    });
    pEqAc.setAcDConditionMap({
       "equip_id": "maEqMstrCommonDTO.equipId"
    });
    pEqAc.setKeyName("maEqMstrAsmbDetailDTO.peqAsmbId");
    pEqAc.setAcResultMap({
        "maEqMstrAsmbDetailDTO.peqAsmbId":"eqasmb_id"
    });
    pEqAc.init();
    
    //부품
    partNoAc = new autoC({"maEqMstrAsmbDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    partNoAc.setAcResultMap({
    	"maEqMstrAsmbDetailDTO.partNameSize":"partNameSize",
//     	"maEqMstrAsmbDetailDTO.partName":"description",
//     	"maEqMstrAsmbDetailDTO.partSize":"pt_size",
        "maEqMstrAsmbDetailDTO.partId":"part_id"
    });
    partNoAc.setKeyName("maEqMstrAsmbDetailDTO.partId");
    partNoAc.init();
    

}

function goInput()
{
	setEnableAll();

    // 시퀀스를 조회한다.
	sequenceNextVal('SQAEQASMB_ID');
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.isEqTypeAsmb'].value = "N";
    valSysDir('maEqMstrAsmbDetailDTO.isEqTypeAsmb', 'maEqMstrAsmbDetailDTO.isEqTypeAsmb', 'IS_USE', true);
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.isUse'].value = "Y";
    valSysDir('maEqMstrAsmbDetailDTO.isUse', 'maEqMstrAsmbDetailDTO.isUse', 'IS_USE', true);
    

    // 생성자
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.creById'].value =	loginUser.empId;
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.creBy'].value =	loginUser.empName;
	// 수정자
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.modifyById'].value =	loginUser.empId;
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.modifyBy'].value =	loginUser.empName;
}

function setSequenceVal(sequenceVal)
{
	if(isNew)
		maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.oldEqAsmbId'].value = maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbId'].value;
		maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.oldEqAsmbNo'].value = maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbNo'].value;

	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbId'].value = sequenceVal;
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbNo'].value = sequenceVal;
}

function goUpdate()
{
	// 수정자
	if(""== maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.modifyById'].value)
	{
		maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.modifyById'].value =	loginUser.empId;
		maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.modifyBy'].value =	loginUser.empName;
	}
}

function goSave(){
	
    // 생성일자
    if(ckCreate(currentPageId)) 
    	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.creDate'].value = getNowDateTime(true);
    
	// 수정일자
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.updDate'].value = getNowDateTime(true);
	
    //================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
	if(ckCreate(currentPageId)) maEqMstrAsmbDetailForm.strutsAction.value = '<%=MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_INPUT%>';
	else maEqMstrAsmbDetailForm.strutsAction.value = '<%= MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrAsmbDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrAsmbDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //if (parent.findGridRow)	parent.findGridRow(maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbId'].value);

    parent.goSearch();

    getTopPage().afterSaveAll(currentPageId);
}

/**
 * 저장후생성후 호출
 */
function afterSavenew() {
	sequenceNextVal('SQAEQASMB_ID');
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbDesc'].value = '';
	maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.remark'].value = '';
}

function loadSlideImages()
{
	var url = contextPath + "/maEqMstrAsmbDetail.do";

    var oldSAction = maEqMstrAsmbDetailForm.elements['strutsAction'].value;
    maEqMstrAsmbDetailForm.elements['strutsAction'].value = '<%=MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maEqMstrAsmbDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });

    maEqMstrAsmbDetailForm.elements['strutsAction'].value = oldSAction;
}


/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbId'].value +
                "&" + "maDocImgCommonDTO.objectType=EQASMB"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbDesc'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
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
	sequenceNextVal('SQAEQASMB_ID');
	
	var form = maEqMstrAsmbDetailForm;
	var url = contextPath + "/maEqMstrAsmbDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				var param = "&strutsAction=" + '<%=MaEqMstrAsmbDetailAction.DETAIL_COPY%>'
				  		  + "&" + FormQueryString(form);
			    XMLHttpPostVal(url, param, 'afterCopycreate');
			}
		});
    }
}

function afterCopycreate()
{
	isNew = false;
	var newEqAsmbId = maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbId'].value; 

	// 상세 닫기
	goClose('maEqMstrAsmbDetail', this);
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	
	if(""== newEqAsmbId || "undefined"== typeof newEqAsmbId)
	{
		// 전체 복사인 경우
		parent.parent.frames["tabFrameTAB.maEqMstrAsmbList"].goSearch();
	}
	else
	{	// Unit 복사인 경우
		if(parent.setKeyAftercopy) parent.setKeyAftercopy(newEqAsmbId,'maEqMstrAsmbDetail');
	}
}

function goAudtrailLink()
{
	var objectId = maEqMstrAsmbDetailForm.elements['maEqMstrAsmbDetailDTO.eqAsmbId'].value;
	var fileName = currentPageId;      

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrAsmbDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.eqAsmbId"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.peqAsmbId"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.oldEqAsmbId"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.oldEqAsmbNo"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.fullDesc"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.creById"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.modifyById"/>
	<html:hidden property="maEqMstrAsmbDetailDTO.partId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box" name = "disableDiv">
					<html:text property="maEqMstrAsmbDetailDTO.eqAsmbDesc" tabindex="10"/>
				</div>
			</div>
			<!-- 사진 -->
			<div class="field_img">
				<label><bean:message key="LABEL.photo"/></label>
				<div class="img_box" name="maEqMstrAsmbDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
				</div>
			</div>
			<!-- 부위코드 -->
			<div class="field">
				<label><bean:message key="LABEL.asmbNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.eqAsmbNo" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 상위부위명 -->
			<div class="field">
				<label><bean:message key="LABEL.pEqCtgAsmb"/></label>
				<div class="input_box" name = "disableDiv">
					<html:text property="maEqMstrAsmbDetailDTO.peqAsmbDesc" tabindex="30"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Tag No -->
			<div class="field">
				<label><bean:message key="LABEL.tagNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.tagNo" tabindex="35"/>
				</div>
			</div>
			<!-- 제작사 -->
			<div class="field">
				<label><bean:message key="LABEL.maker"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.maker" tabindex="40"/>
				</div>
			</div>
			<!-- 모델 -->
			<div class="field">
				<label><bean:message key="LABEL.model"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.modelNo" tabindex="50"/>
				</div>
			</div>
			<!-- AS 업체정보 -->
			<div class="field">
				<label><bean:message key="LABEL.ASINFO"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.asInfo" tabindex="55"/>
				</div>
			</div>
			<!-- 구입금액 -->
			<div class="field">
				<label><bean:message key="LABEL.buyAmt"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.buyAmt" tabindex="60" styleClass="num"/>
				</div>
			</div>
			<!-- 중요스펙 -->
			<div class="field">
				<label><bean:message key="LABEL.spec"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.spec" tabindex="70"/>
				</div>
			</div>
			<!-- 설치일자 -->
			<div class="field">
				<label><bean:message key="LABEL.setupDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.setupDate" tabindex="80"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
            <!-- 조회순서 (정렬값) -->
            <div class="field">
                <label><bean:message key="LABEL.ordNo"/></label>
                <div class="input_box">
                    <html:text property="maEqMstrAsmbDetailDTO.ordNo" tabindex="90"/>
                </div>
            </div>
			<!-- 유사설비 공통부위 -->
			<div class="field">
				<label><bean:message key="LABEL.isEqTypeAsmb"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.isEqTypeAsmb" readonly="true" />
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.isUse" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부위설명 -->
            <div class="field_long">
                <label><bean:message key="LABEL.asmbRemark"/></label>
                <div class="input_box">
                    <html:textarea property="maEqMstrAsmbDetailDTO.remark" styleClass="ta50" tabindex="110" />
                </div>
            </div>
            
			<!-- 보증기간 -->
			<div class="field">
				<label>보증기간</label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.guarantyDate" tabindex="80"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- Serial# -->
			<div class="field">
				<label>Serial#</label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.serialNo" tabindex="100"/>
				</div>
			</div>
			<!-- Size -->
			<div class="field">
				<label>Size</label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.eqSize" tabindex="100"/>
				</div>
			</div>
			<!-- 중량 -->
			<div class="field">
				<label>중량</label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.weight" tabindex="100"/>
				</div>
			</div>
			<!-- 부품번호 -->
			<div class="field">
				<label>부품번호</label>
				<div class="input_box">
					<html:text property="maEqMstrAsmbDetailDTO.partNo" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부품명/규격 -->
			<div class="field">
				<label>부품명/규격</label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.partNameSize" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 생성일자 -->
			<div class="field">
				<label>생성일자</label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.creDate" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 생성자 -->
			<div class="field">
				<label>생성자</label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.creBy" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 수정일자 -->
			<div class="field">
				<label>수정일자</label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.updDate" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 수정자 -->
			<div class="field">
				<label>수정자</label>
				<div class="input_read">
					<html:text property="maEqMstrAsmbDetailDTO.modifyBy" tabindex="100" readonly="true"/>
				</div>
			</div>
			
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>