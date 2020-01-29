<%--===========================================================================
내정보
author  kim21017
version $Id: maMyInfo.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.pers.priv.info.action.MaMyInfoAction"%>
<html:html>
<head>
<!-- 내정보 -->
<title><bean:message key='MENU.MYINFO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultTabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction;

var menuIdAc;
var beeMenuIdAc;
var shiftTypeAc;
var eqLocDescAc;
var plantTypeAc;
var secureTypeAc;
var scrnFontAc;
var isUseAc;
var wkCtrDescAc;
var eqCtgAc;
var deptAc;
var empAc;
var menuDisAc;
var filterWkCtrAc;
var usedDeptAc;

function loadPage() 
{
	setTitle("maMyInfoCommonDTO.userNo");
	//setDetailTitle("<bean:message key='MENU.MYINFO' />",maMyInfoForm.elements['maMyInfoCommonDTO.userNo'].value,maMyInfoForm.elements['maMyInfoCommonDTO.userName'].value," : "," / "); //detail 상단에 button  id/desc 세팅
	goUpdate();
	
	//For Save
	setForUpdate();

	//WEB 초기화면
 	menuIdAc = new autoC({"maMyInfoCommonDTO.menuDesc":"description"});
 	menuIdAc.setAcConditionMap({
     	//"is_use":"Y"
 		"service_type":"WEB"
   	   });
 	menuIdAc.setTable("TAPMENU");
 	menuIdAc.setAcResultMap({
         "maMyInfoCommonDTO.menuId":"menu_id"
    });
 	menuIdAc.setKeyName("maMyInfoCommonDTO.menuId");
 	menuIdAc.init(); 
 	
	//BEE 초기화면
 	beeMenuIdAc = new autoC({"maMyInfoCommonDTO.beeInitMenuDesc":"description"});
 	beeMenuIdAc.setAcConditionMap({
     	"service_type":"ANDROID"
   	   });
 	beeMenuIdAc.setTable("TAPMENU");
 	beeMenuIdAc.setAcResultMap({
         "maMyInfoCommonDTO.beeInitMenuId":"menu_id"
    });
 	beeMenuIdAc.setKeyName("maMyInfoCommonDTO.beeInitMenuId");
 	beeMenuIdAc.init(); 

	
 	//---------------------------------------------------------------------//
	eqLocDescAc = new autoC({"maMyInfoCommonDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("maMyInfoCommonDTO.eqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maMyInfoCommonDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maMyInfoCommonDTO.filterPlant"
    });
    eqLocDescAc.init();
    
  //---------------------------------------------------------------------//
    plantTypeAc = new autoC({"maMyInfoCommonDTO.filterPlantDesc":"description"});
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantTypeAc.setKeyName("maMyInfoCommonDTO.filterPlant");
	plantTypeAc.setTable("TAUSRPLANTAUTH");
	plantTypeAc.setAcResultMap({
        "maMyInfoCommonDTO.filterPlant":"plant"
    });
	plantTypeAc.init();

	//--------------------작업그룹 명------------------------------------//
    wkCtrDescAc = new autoC({"maMyInfoCommonDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
		"is_use":"Y"
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maMyInfoCommonDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.setKeyName("maMyInfoCommonDTO.wkCtrId");
    wkCtrDescAc.init();
	//--------------------자주보는 작업그룹 명------------------------------------//
    filterWkCtrAc = new autoC({"maMyInfoCommonDTO.filterWkCtrDesc":"description"});
    filterWkCtrAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
		"is_use":"Y"
  	   });
    filterWkCtrAc.setTable("TAWKCTR");
    filterWkCtrAc.setAcResultMap({
        "maMyInfoCommonDTO.filterWkCtrId":"wkctr_id"
    });
    filterWkCtrAc.setKeyName("maMyInfoCommonDTO.filterWkCtrId");
    filterWkCtrAc.init();

    //---------------------------------------------------------------------//
    deptAc = new autoC({"maMyInfoCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maMyInfoCommonDTO.filterDeptId");
    deptAc.setAcResultMap({
        "maMyInfoCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maMyInfoCommonDTO.filterPlant"
    });
    deptAc.init();
    
    empAc = new autoC({"maMyInfoCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    empAc.setTable("TAEMP");
    empAc.setKeyName("maMyInfoCommonDTO.filterEmpId");
    empAc.setAcResultMap({
        "maMyInfoCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "maMyInfoCommonDTO.filterDeptId",
    	"plant" : "maMyInfoCommonDTO.filterPlant"
    });
    empAc.init();
    
    /* 자주보는 사용부서 */
    usedDeptAc = new autoC({"maMyInfoCommonDTO.filterUsageDeptDesc":"description"});
    usedDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usedDeptAc.setTable("TADEPT");
    usedDeptAc.setKeyName("maMyInfoCommonDTO.filterUsageDeptId");
    usedDeptAc.setAcResultMap({
        "maMyInfoCommonDTO.filterUsageDeptId":"dept_id"
    });
    usedDeptAc.setAcDConditionMap({
    	"plant" : "maMyInfoCommonDTO.filterPlant"
    });
    usedDeptAc.init();

    
	acSysDesc("maMyInfoCommonDTO.shiftTypeDesc","maMyInfoCommonDTO.shiftTypeId","SHIFT_TYPE", true);
	acSysDesc("maMyInfoCommonDTO.scrnFontSizeDesc","maMyInfoCommonDTO.scrnFontSizeId","SCRN_FONT_SIZE", true);
    acSysDesc("maMyInfoCommonDTO.alarmViewYn","maMyInfoCommonDTO.alarmViewYn","IS_USE", true);
    acSysDesc("maMyInfoCommonDTO.isMailRec","maMyInfoCommonDTO.isMailRec","IS_USE", true);
    acSysDesc("maMyInfoCommonDTO.eqCtgTypeDesc","maMyInfoCommonDTO.eqCtgTypeId","EQCTG_TYPE", true);
    acSysDesc("maMyInfoCommonDTO.menuDisplayDesc","maMyInfoCommonDTO.menuDisplay","MENU_DISPLAY", true);
    
//     goTabPage('persPrivInfoMsgEmpList');
}

function goTabPage(pageId)
{
	var form = document.maMyInfoForm;

	goCommonTabPage(form, '' , pageId, pageId);
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
	
	maMyInfoForm.strutsAction.value = '<%=MaMyInfoAction.MYINFO_DETAIL_UPDATE%>';
	var actionUrl = contextPath + "/maMyInfo.do";
	XMLHttpPost(actionUrl, FormQueryString(maMyInfoForm), 'afterSave');
}

/**
 * detail 상단 ID/DESC
 */
 function setDetailTitle(title,desc1,desc2,and1, and2){
	var detailTitle = document.getElementById('detailTitle');
	detailTitle.innerHTML = title+and1+desc1+and2+desc2;
}
/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
 	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
		
    getTopPage().afterSaveAll(currentPageId);
}
/**
 * 수정버튼을 누른경우
 */
function goUpdate()
{
	
	maMyInfoForm.elements['maMyInfoCommonDTO.userNo'].readOnly		= "true";
	maMyInfoForm.elements['maMyInfoCommonDTO.loginNo'].readOnly		= "true";
	maMyInfoForm.elements['maMyInfoCommonDTO.userName'].readOnly	= "true";
	maMyInfoForm.elements['maMyInfoCommonDTO.userGroup'].readOnly	= "true";
	maMyInfoForm.elements['maMyInfoCommonDTO.regDate'].readOnly		= "true";
	maMyInfoForm.elements['maMyInfoCommonDTO.loginDate'].readOnly	= "true";
}
/**
 * 비밀번호변경
 */
 function goChpw(){
	
	var userId 		= maMyInfoForm.elements['maMyInfoCommonDTO.userId'].value;
	var userNo 		= maMyInfoForm.elements['maMyInfoCommonDTO.userNo'].value;
	var userName 	= maMyInfoForm.elements['maMyInfoCommonDTO.userName'].value;
	changePasswordLayer(userId, userNo, userName);
}

 

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maMyInfo" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maMyInfoCommonDTO.userId" />
	<html:hidden property="maMyInfoCommonDTO.menuId" />
	<html:hidden property="maMyInfoCommonDTO.securGradeId" />
	<html:hidden property="maMyInfoCommonDTO.shiftTypeId" />
	<html:hidden property="maMyInfoCommonDTO.scrnFontSizeId" />
	<html:hidden property="maMyInfoCommonDTO.eqLocId" />
	<html:hidden property="maMyInfoCommonDTO.filterPlant" />
	<html:hidden property="maMyInfoCommonDTO.wkCtrId" />
	<html:hidden property="maMyInfoCommonDTO.eqCtgTypeId" />
	<html:hidden property="maMyInfoCommonDTO.filterDeptId" />
	<html:hidden property="maMyInfoCommonDTO.filterEmpId" />
	<html:hidden property="maMyInfoCommonDTO.menuDisplay" />
	<html:hidden property="maMyInfoCommonDTO.filterWkCtrId" />
	<html:hidden property="maMyInfoCommonDTO.filterUsageDeptId" />
	<html:hidden property="maMyInfoCommonDTO.beeInitMenuId" />
	 <!-- <div class="section_wrap" id="myInfoSec">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="detailTitle"></div>
			</div>
			<div class="function_box detail">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div> -->
		<div class="article_box">
			<div class="form_box">
			<!--로그인계정  -->
				<div class="field">
					<label><bean:message key="LABEL.loginNo"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.loginNo" />
					</div>
				</div>
				<!--사용자 번호  -->
				<div class="field">
					<label><bean:message key="LABEL.emp"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.userNo" />
					</div>
				</div>
				<!--사용자명  -->
				<div class="field">
					<label><bean:message key="LABEL.userName"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.userName" />
					</div>
				</div>
				<!--권  한 -->
				<div class="field">
					<label><bean:message key="LABEL.auth"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.userGroup" />
					</div>
				</div>
				<!--등록일  -->
				<div class="field">
					<label><bean:message key="LABEL.regDate"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.regDate" />
					</div>
				</div>
				<!-- 최근접속일 -->
				<div class="field">
					<label><bean:message key="LABEL.lastLoginDate"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.loginDate" />
					</div>
				</div>
				<!-- 전화번호 -->
				<div class="field">
					<label><bean:message key="LABEL.phone"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.phone" tabindex="10" />
					</div>
				</div>
				<!-- EMAIL -->
				<div class="field">
					<label><bean:message key="LABEL.email"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.email" tabindex="20" />
					</div>
				</div>
				<!-- Web초기화면  -->
				<div class="field">
					<label><bean:message key="LABEL.initPage"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.menuDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!--Bee초기화면  -->
				<div class="field">
					<label><bean:message key="LABEL.beeInitPage"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.beeInitMenuDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 교대조. -->
				<div class="field">
					<label><bean:message key="LABEL.shiftType"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.shiftTypeDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.eqLocDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장. -->
				<div class="field">
					<label><bean:message key="LABEL.favoritesPlant"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.filterPlantDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 문서권한  -->
				<div class="field hidden">
					<label><bean:message key="LABEL.secureGrade"/></label>
					<div class="input_read">
						<html:text property="maMyInfoCommonDTO.securGradeDesc" tabindex="70" readonly="true"/>
					</div>
				</div>
				<!-- 화면글자크기  -->
				<div class="field hidden">
					<label><bean:message key="LABEL.scrnFontSize"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.scrnFontSizeDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Alarm On -->
				<div class="field">
                        <label><bean:message key="LABEL.alarmViewYn"/></label>
                        <div class="input_box">
                           <html:text property="maMyInfoCommonDTO.alarmViewYn" tabindex="90"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div> 
				<!--작업그룹 명  -->
                <div class="field">
					<label><bean:message key="LABEL.wkCtr"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.wkCtrDesc" tabindex="100"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!--자주보는 작업그룹 명  -->
				<div class="field">
					<label><bean:message key="LABEL.filterWkCtr"/></label>
					<div class="input_box">
			            <html:text property="maMyInfoCommonDTO.filterWkCtrDesc" tabindex="140"/>
			            <p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 관리 설비유형  -->
				<div class="field">
					<label><bean:message key="LABEL.mngEqCtgType"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.eqCtgTypeDesc" tabindex="110" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.favoritesDept"/></label>
					<div class="input_box">
			            <html:text property="maMyInfoCommonDTO.filterDeptDesc" tabindex="120"/>
			            <p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.favoritesEmp"/></label>
					<div class="input_box">
			            <html:text property="maMyInfoCommonDTO.filterEmpDesc" tabindex="130"/>
			            <p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 메뉴표시  -->
				<div class="field">
					<label><bean:message key="LABEL.menuDisplay"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.menuDisplayDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 메일수신여부  -->
				<div class="field">
					<label><bean:message key="LABEL.isMailRec"/></label>
					<div class="input_box">
						<html:text property="maMyInfoCommonDTO.isMailRec" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 자주보는 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.favoritesUsedDept"/></label>
					<div class="input_box">
			            <html:text property="maMyInfoCommonDTO.filterUsageDeptDesc" tabindex="120"/>
			            <p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
     <!-- </div>  -->
</html:form>
</body>
</html:html>