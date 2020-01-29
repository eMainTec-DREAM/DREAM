<%--===========================================================================
화변별 필드 기본값 상세 
author  kim21017
version $Id: maPgMngFieldValueDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.consult.program.page.action.MaPgMngFieldValueDetailAction"%>
<html>
<head>
<!--Site -->
<title><bean:message key="TAB.maPgMngFieldValueDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var compNoAc;
var defaultValueAc;

var cdUsrDetailAc;
var assBaseListAc;
var ctCtrAc;
var deptAc;
var docctgAc;
var empAc;
var eqCtgTypeAc;
var eqLocDescAc;
var equipAc;
var failCodeAc;
var partAc;
var plantAc;
var userAc;
var wkCtrAc;

function loadPage() 
{
	setTitle("maPgMngFieldValueDetailDTO.compNoDesc");
	setForUpdate();
	
	//회사코드 AC
	compNoAc = new autoC({"maPgMngFieldValueDetailDTO.compNoDesc":"description"});
	compNoAc.setTable("TACOMP");
	compNoAc.setAcResultMap({
		"maPgMngFieldValueDetailDTO.compNoId":"comp_no"
	});
	compNoAc.setKeyName("maPgMngFieldValueDetailDTO.compNoId");
	compNoAc.init();
	
	//사용자코드 AC
	cdUsrDetailAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
	cdUsrDetailAc.setTable("TACDUSRD");
	cdUsrDetailAc.setAcConditionMap({
    	"dir_type":maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.codeListTypeId'].value,
    	"is_use":"Y"
	   });
	cdUsrDetailAc.setAcDConditionMap({
        "comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
	cdUsrDetailAc.setAcResultMap({
          "maPgMngFieldValueDetailDTO.defaultValueId":"cdusrd_id" // 상위코드ID
    });
	
	//평가항목 AC
	assBaseListAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"assBaseListDesc"});
    assBaseListAc.setTable("TAASSBASE");
    assBaseListAc.setAcDConditionMap({
        "comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
    assBaseListAc.setAcConditionMap({
        "is_use" : "Y"
    });
    assBaseListAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"assBaseListId",
        "maPgMngFieldValueDetailDTO.defaultValueId":"assBaseListDesc"
    });
    assBaseListAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //코스트센터AC
    ctCtrAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    ctCtrAc.setTable("TACTCTR");
    ctCtrAc.setAcDConditionMap({
        "comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
    ctCtrAc.setAcConditionMap({
        "is_use" : "Y"
    });
    ctCtrAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"ctctr_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"description"
    });
    ctCtrAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //부서AC
    deptAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcDConditionMap({
        "comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
    deptAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"dept_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"description"
    });
    deptAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
	
    //문서구분 AC
    docctgAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    docctgAc.setTable("TADOCCTG");
    docctgAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
    docctgAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"docctg_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"description"
    });
    docctgAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //사원 AC
    empAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    empAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"emp_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"emp_name"
    });
    empAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //설비종류 AC
    eqCtgTypeAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    eqCtgTypeAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"eqctg_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"full_desc"
    });
    eqCtgTypeAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //설비위치 AC
    eqLocDescAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    eqLocDescAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"eqloc_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"full_desc"
    });
    eqLocDescAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //설비 AC
    equipAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    equipAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultValueId":"description",
        "maPgMngFieldValueDetailDTO.defaultObjectId":"equip_id"
    });
    equipAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //고장코드AC
    failCodeAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    failCodeAc.setTable("TAFAILURE");
    failCodeAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    failCodeAc.setAcConditionMap({
  	   "is_use":"Y"
  	   });
    failCodeAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"failure_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"description"
    });
    failCodeAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //부품AC
    partAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"full_desc"});
    partAc.setTable("TAPARTS");
	partAc.setAcConditionMap({
		"is_use" : "Y"
	});
	partAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    partAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"part_id"
        ,"maPgMngFieldValueDetailDTO.defaultValueId":"full_desc"
    });
    partAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    plantAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
  	   });
    plantAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultValueId":"plant"
    });
    plantAc.setKeyName("maPgMngFieldValueDetailDTO.defaultValueId");
    
    //사용자AC
    userAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"emp_name"});
    userAc.setTable("TAUSER");
    userAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
    userAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"user_id"
       ,"maPgMngFieldValueDetailDTO.defaultValueId":"emp_name"
    });
    userAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    //작업그룹AC
    wkCtrAc = new autoC({"maPgMngFieldValueDetailDTO.defaultValueDesc":"description"});
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setAcDConditionMap({
    	"comp_no":"maPgMngFieldValueDetailDTO.compNoId"
    });
    wkCtrAc.setAcResultMap({
        "maPgMngFieldValueDetailDTO.defaultObjectId":"wkctr_id"
       ,"maPgMngFieldValueDetailDTO.defaultValueId":"description"
    });
    wkCtrAc.setKeyName("maPgMngFieldValueDetailDTO.defaultObjectId");
    
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
}

function goInput(){
	sequenceNextVal('SQAPGFIELDVALUE_ID');
	changeDefaultValueField();
}

function goUpdate(){
	changeDefaultValueField();
}

function setSequenceVal(sequenceVal)
{
	maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.pgFieldValueId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	//회사코드 중복검사 
	validCompNo();
	if(isValid!='0') return;
	
}

function validCompNo(){
	var actionUrl = contextPath + "/maPgMngFieldValueDetail.do";
	var param =  "&strutsAction=" + '<%= MaPgMngFieldValueDetailAction.CHECK_COMP_NO %>'
	          +  "&" + FormQueryString(maPgMngFieldValueDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidCompNo');
}

var isValid;
function afterValidCompNo(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0009'/>");
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultObjectId'].value = '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.dateInterval'].value = '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultValueId'].value = '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultValueDesc'].value = '';
    }else{
    	if(ckCreate(currentPageId)) maPgMngFieldValueDetailForm.strutsAction.value = '<%=MaPgMngFieldValueDetailAction.INPUT_DETAIL%>';
    	else maPgMngFieldValueDetailForm.strutsAction.value = '<%= MaPgMngFieldValueDetailAction.UPDATE_DETAIL %>';
    	
    	beforeSaveSetValues();
    	
    	var actionUrl = contextPath + "/maPgMngFieldValueDetail.do";
    	XMLHttpPost(actionUrl, FormQueryString(maPgMngFieldValueDetailForm), 'afterSave');
    }
}


function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.pgFieldValueId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
    setTitle("maPgMngFieldValueDetailDTO.compNoDesc");
}

function beforeSaveSetValues(){
	switch(maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.formInputTypeId'].value){
	case 'TEXT_INPUT_TYPE' :
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultObjectId'].value = '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.dateInterval'].value = '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultValueId'].value
		 = maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultValueDesc'].value;
		break;
	case 'YEAR_INPUT_TYPE'||'MONTH_INPUT_TYPE'||'DATE_INPUT_TYPE':
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultObjectId'].value = '';
		break;
	case 'CODE_INPUT_TYPE':
		switch(maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.formInputDetailTypeId'].value){
			case 'TACDSYSD':
			case 'TACDUSRD':
			case 'TAPLANT':
				maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultObjectId'].value = '';
				break;
		}
		break;
	default : 
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.dateInterval'].value = '';
		break;
	}
}

/**
 * 기본값 필드 속성 설정
 */
 function changeDefaultValueField(){

	 if(typeof defaultValueAc != "undefined") defaultValueAc.destroy();
	 
	switch(maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.formInputTypeId'].value){
		case 'TEXT_INPUT_TYPE' :
			//돋보기 창을 없애고 일간격 필드를 숨긴다.
			$("[name='maPgMngFieldValueDetailDTO.defaultValueDesc']").nextAll([".open_spop"]).hide();
			hideField('maPgMngFieldValueDetailDTO.dateInterval');
			break;
		case 'YEAR_INPUT_TYPE':
		case 'MONTH_INPUT_TYPE':
		case 'DATE_INPUT_TYPE':
			//돋보기 버튼,일간격 필드을 보여주고 시스템 코드 ( YEAR_INPUT_TYPE) AC를 할당해준다.
			$("[name='maPgMngFieldValueDetailDTO.defaultValueDesc']").nextAll([".open_spop"]).show();
			showField('maPgMngFieldValueDetailDTO.dateInterval');
			defaultValueAc = acSysDesc("maPgMngFieldValueDetailDTO.defaultValueDesc","maPgMngFieldValueDetailDTO.defaultValueId",maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.formInputTypeId'].value);
			break;
		case 'CODE_INPUT_TYPE':
			$("[name='maPgMngFieldValueDetailDTO.defaultValueDesc']").nextAll([".open_spop"]).show();
			hideField('maPgMngFieldValueDetailDTO.dateInterval');
			switch(maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.formInputDetailTypeId'].value){
				case 'TACDSYSD':
					defaultValueAc = acSysDesc("maPgMngFieldValueDetailDTO.defaultValueDesc","maPgMngFieldValueDetailDTO.defaultValueId",maPgMngFieldValueDetailForm.elements['maPgMngFieldDetailDTO.codeListTypeId'].value);
					break;
				case 'TAPLANT':
					defaultValueAc = plantAc;
					defaultValueAc.init();
					break;
				case 'TACDUSRD':
					defaultValueAc = cdUsrDetailAc;
					defaultValueAc.init();
					break;
				case 'TAASSBASE':
					defaultValueAc = assBaseListAc;
					defaultValueAc.init();
					break;
				case 'TACTCTR':
					defaultValueAc = ctCtrAc;
					ctCtrAc.init();
					break;
				case 'TADEPT':
					defaultValueAc = deptAc;
					defaultValueAc.init();
					break;
				case 'TADOCCTG':
					defaultValueAc = docctgAc;
					defaultValueAc.init();
					break;
				case 'TAEMP':
					defaultValueAc = empAc;
					defaultValueAc.init();
					break;
				case 'TAEQCTG':
					defaultValueAc = eqCtgTypeAc;
					defaultValueAc.init();
					break;
				case 'TAEQLOC':
					defaultValueAc = eqLocDescAc;
					defaultValueAc.init();
					break;
				case 'TAEQUIPMENT':
					defaultValueAc = equipAc;
					defaultValueAc.init();
					break;
				case 'TAFAILURE':
					defaultValueAc = failCodeAc;
					defaultValueAc.init();
					break;
				case 'TAPARTS':
					defaultValueAc = partAc;
					defaultValueAc.init();
					break;
				case 'TAUSER':
					defaultValueAc = userAc;
					defaultValueAc.init();
					break;
				case 'TAWKCTR':
					defaultValueAc = wkCtrAc;
					defaultValueAc.init();
					break;
					
				default:
					break;
			}
			break;
		default : 
			break;
		}
}

function afterAutoCmpt(code)
{
	if(code=="maPgMngFieldValueDetailDTO.compNoDesc"){
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultObjectId'].value 	= '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultValueId'].value 	= '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.defaultValueDesc'].value 	= '';
		maPgMngFieldValueDetailForm.elements['maPgMngFieldValueDetailDTO.dateInterval'].value 		= '';
	}
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPgMngFieldValueDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/>
<html:hidden property="maPgMngFieldDetailDTO.fieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.pgFieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.formInputTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.formInputDetailTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.codeListTypeId"/>
<html:hidden property="maPgMngFieldValueDetailDTO.pgFieldValueId"/>
<html:hidden property="maPgMngFieldValueDetailDTO.compNoId"/>
<html:hidden property="maPgMngFieldValueDetailDTO.fileName"/>
<html:hidden property="maPgMngFieldValueDetailDTO.fieldId"/>
<html:hidden property="maPgMngFieldValueDetailDTO.defaultObjectId"/>
<html:hidden property="maPgMngFieldValueDetailDTO.defaultValueId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 회사코드 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.compNo"/></label>
				<div class="input_box">
						<html:text property="maPgMngFieldValueDetailDTO.compNoDesc" tabindex="10"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
				</div>
			</div>
			<!-- 기본값 -->
			<div class="field">
				<label><bean:message key="LABEL.defaultValue"/></label>
				<div class="input_box">
						<html:text property="maPgMngFieldValueDetailDTO.defaultValueDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
				</div>
			</div>
			<!-- Day Interval -->
			<div class="field">
				<label><bean:message key="LABEL.dateInterval"/></label>
				<div class="input_box">
						<html:text property="maPgMngFieldValueDetailDTO.dateInterval" tabindex="30" styleClass='num'/>
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>