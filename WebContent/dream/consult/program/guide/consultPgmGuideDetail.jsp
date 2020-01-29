 <%--===========================================================================
Program Guide Detail
author  kim21017
version $Id: consultPgmGuideDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.consult.program.guide.action.ConsultPgmGuideDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.PGMGUIDE' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var isUseAc;
var eqStatusAc;
var deptAc;
var eqLocAc;
var eqCtgAc;
var equipAc;
var empAc;
var plantAc;
var partAc;

function chkInputVal(_inputObj)
{
	if(_inputObj.val() == "") _inputObj.next().hide();
	else _inputObj.next().show();
}

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("consultPgmGuideDetailDTO.pgGuideNo", "consultPgmGuideDetailDTO.pgGuideDesc");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("consultPgmGuideDetailDTO.isUseDesc","consultPgmGuideDetailDTO.isUseId","IS_USE", true);
    //설비상태 자동완성
    acSysDesc("consultPgmGuideDetailDTO.eqStatusDesc","consultPgmGuideDetailDTO.eqStatusId","EQ_STATUS", true);
    //부서 자동완성
    deptAc = new autoC({"consultPgmGuideDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("consultPgmGuideDetailDTO.deptId");
    deptAc.setAcDConditionMap({
    	"comp_no":"consultPgmGuideDetailDTO.compNo",
    	"plant" : "consultPgmGuideDetailDTO.plantId"
  	   });
    deptAc.setAcResultMap({
        "consultPgmGuideDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    //설비위치 자동완성
    eqLocAc = new autoC({"consultPgmGuideDetailDTO.eqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setKeyName("consultPgmGuideDetailDTO.eqLocId");
  //filterCompNo 에 100으로 하드코딩되어있으니 유의.참고용
    eqLocAc.setAcDConditionMap({
    	"comp_no":"consultPgmGuideDetailDTO.compNo",
    	"plant" : "consultPgmGuideDetailDTO.plantId"
  	   });
    eqLocAc.setAcResultMap({
        "consultPgmGuideDetailDTO.eqLocId":"eqloc_id"
    });
    eqLocAc.init();
    //설비종류 자동완성
    eqCtgAc = new autoC({"consultPgmGuideDetailDTO.eqCtgDesc":"full_desc"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setCustomLov("lovEqCtg('consultPgmGuideDetailDTO.eqCtgId', 'consultPgmGuideDetailDTO.eqCtgDesc')");
    eqCtgAc.setKeyName("consultPgmGuideDetailDTO.eqCtgId");
    eqCtgAc.setAcResultMap({
        "consultPgmGuideDetailDTO.eqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    //설비 자동완성
    equipAc = new autoC({"consultPgmGuideDetailDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setCustomLov("lovEquip('consultPgmGuideDetailDTO.equipId', 'consultPgmGuideDetailDTO.equipDesc')");
    equipAc.setKeyName("consultPgmGuideDetailDTO.equipId");
    equipAc.setAcResultMap({
        "consultPgmGuideDetailDTO.equipId":"equip_id"
    });
    equipAc.setAcDConditionMap({
    	"eqloc_id" : "consultPgmGuideDetailDTO.eqLocId",
    	"eqctg_id" : "consultPgmGuideDetailDTO.eqCtgId",
    	"dept_id" : "consultPgmGuideDetailDTO.deptId",
    	"plant" : "consultPgmGuideDetailDTO.plantId"
    });
    equipAc.init();
    //사원 자동완성
    empAc = new autoC({"consultPgmGuideDetailDTO.empDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setCustomLov("lovEmp('consultPgmGuideDetailDTO.empId','', 'consultPgmGuideDetailDTO.empDesc')");
    empAc.setKeyName("consultPgmGuideDetailDTO.empId");
    empAc.setAcResultMap({
        "consultPgmGuideDetailDTO.empId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "consultPgmGuideDetailDTO.deptId",
    	"plant" : "consultPgmGuideDetailDTO.plantId"
    });
    empAc.init();
    
    //공장 자동완성
    plantAc = new autoC({"consultPgmGuideDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("consultPgmGuideDetailDTO.plantId");
  //filterCompNo 에 100으로 하드코딩되어있으니 유의.참고용
    plantAc.setAcDConditionMap({
    	"comp_no":"consultPgmGuideDetailDTO.compNo"
  	   });
    plantAc.setAcResultMap({
        "consultPgmGuideDetailDTO.plantId":"plant"
    });
    plantAc.init();
    //사원 자동완성
    partAc = new autoC({"consultPgmGuideDetailDTO.partDesc":"full_desc"});
    partAc.setTable("TAPARTS");
    partAc.setCustomLov("lovParts('consultPgmGuideDetailDTO.partId','', 'consultPgmGuideDetailDTO.partDesc')");
    partAc.setKeyName("consultPgmGuideDetailDTO.partId");
    partAc.setAcResultMap({
        "consultPgmGuideDetailDTO.partId":"part_id"
    });
    partAc.init();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAPGGUIDE_ID');
    
    consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.isUseId'].value = 'Y';
    consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('consultPgmGuideDetailDTO.isUseId', 'consultPgmGuideDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.pgGuideId'].value = sequenceVal;
    consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.pgGuideNo'].value = sequenceVal;
    consultPgmGuideDetailForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
}

function goEquipmentLink(_pageId)
{
	var equipId = consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.equipId'].value;
	var _param = "maEqmstrCommonDTO.equipId="+equipId;
	goEquipDetail("maEqMstrDetail", _param);
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
    if(ckCreate(currentPageId)) consultPgmGuideDetailForm.strutsAction.value = "<%=ConsultPgmGuideDetailAction.DETAIL_INPUT%>";
    else consultPgmGuideDetailForm.strutsAction.value = "<%=ConsultPgmGuideDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/consultPgmGuideDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(consultPgmGuideDetailForm),
				'afterSave');

	}

	/**
	 * 저장후 호출
	 */
	function afterSave(ajaxXmlDoc) {
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc))
			return;
		//=====================================
		if (parent.findGridRow)
			parent
					.findGridRow(consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.pgGuideId'].value);

		consultPgmGuideDetailForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = consultPgmGuideDetailForm.elements['consultPgmGuideDetailDTO.pgGuideId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("consultPgmGuideDetailDTO.pgGuideNo",
				"consultPgmGuideDetailDTO.pgGuideDesc");

	}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/consultPgmGuideDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="consultPgmGuideCommonDTO.pgGuideId" />
		<!-- Key -->
		<html:hidden property="consultPgmGuideDetailDTO.pgGuideId" />
		<!-- Key -->
		<html:hidden property="consultPgmGuideDetailDTO.eqLocId" />
		<html:hidden property="consultPgmGuideDetailDTO.eqCtgId" />
		<html:hidden property="consultPgmGuideDetailDTO.equipId" />
		<html:hidden property="consultPgmGuideDetailDTO.deptId" />
		<html:hidden property="consultPgmGuideDetailDTO.empId" />
		<html:hidden property="consultPgmGuideDetailDTO.eqStatusId" />
		<html:hidden property="consultPgmGuideDetailDTO.plantId" />
		<html:hidden property="consultPgmGuideDetailDTO.partId" />
		<html:hidden property="consultPgmGuideDetailDTO.isUseId" />
		<html:hidden property="consultPgmGuideDetailDTO.compNo" value="100" />
		<div class="article_box">
			<div class="form_box">
				<!-- 프로그램 가이드No -->
				<div class="field">
					<label><bean:message key="LABEL.pgmGuideNo" /></label>
					<div class="input_read">
						<html:text property="consultPgmGuideDetailDTO.pgGuideNo"
							readonly="true" />
					</div>
				</div>
				<!-- 프로그램 가이드명 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.pgmGuideName" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.pgGuideDesc"
							tabindex="10" />
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.location" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.eqLocDesc"
							tabindex="20" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.type" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.eqCtgDesc"
							tabindex="30" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.equipDesc" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.equipDesc"
							tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.deptDesc"
							tabindex="50" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 사원 -->
				<div class="field">
					<label><bean:message key="LABEL.emp" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.empDesc"
							tabindex="60" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비상태 -->
				<div class="field">
					<label><bean:message key="LABEL.equipStatus" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.eqStatusDesc"
							tabindex="70" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.plantDesc"
							tabindex="80" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 자재 -->
				<div class="field">
					<label><bean:message key="LABEL.parts" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.partDesc"
							tabindex="90" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.isUseDesc" tabindex="100" />
						<p class="open_spop"><a> <span>조회</span></a>
						</p>
					</div>
				</div>
				
				<!-- 고장발생일자 -->
				<div class="field">
					<label>고장발생일자</label>
					<div class="datetime_wrap">
						<div class="input_box input_carendar">
							<input name="" tabindex=""  type="text" value=""/>
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box">
							<input name="" tabindex=""  type="text" value=""/>
							<p class="open_time"><span>날짜</span></p>
						</div>
					</div>
				</div>
				
				<!-- Field Type1 -->
				<div class="field">
					<label>Field type1</label>
					<div class="datetime_wrap">
						<div class="input_box">
							<input name="" tabindex=""  type="text" value=""/>
							<p class="tx_clear"><a><span>삭제</span></a></p>
							<p class="open_spop"><a><span>조회</span></a>
						</div>
						<div class="input_box">
							<input name="" tabindex=""  type="text" value=""/>
							<p class="tx_clear"><a><span>삭제</span></a></p>
							<p class="open_spop"><a><span>조회</span></a>
						</div>
					</div>
				</div>
				
				<!-- Field Type2 -->
				<div class="field">
					<label>Field type2</label>
					<div class="datetime_wrap">
						<div class="input_box">
							<input name="" tabindex=""  type="text" value=""/>
							<p class="tx_clear_r"><a><span>삭제</span></a></p>
						</div>
						<div class="input_box">
							<input name="" tabindex=""  type="text" value=""/>
							<p class="tx_clear_r"><a><span>삭제</span></a></p>
						</div>
					</div>
				</div>
				
				<!-- Field Type3 -->
				<div class="field ty3-2">
					<label>Field type3-2</label>
					<div class="multi_wrap">
						<div class="input_box">
							<input name="consultPgmGuideDetailDTO.equipDesc1" type="text">
							<p class="open_spop"><a><span>조회</span></a>
						</div>
						<div class="input_box">
							<input name="consultPgmGuideDetailDTO.equipDesc2" type="text">
							<p class="open_spop"><a><span>조회</span></a>
						</div>
						<div class="input_read">
							<input name="consultPgmGuideDetailDTO.equipDesc3" type="text">
							<p class="open_spop"><a><span>조회</span></a>
						</div>
					</div>
				</div>
				
				<!-- Field Type4 -->
				<div class="field ty3-2">
					<label>Field type3-2</label>
					<div class="multi_wrap">
						<div class="input_read">
							<input name="consultPgmGuideDetailDTO.equipDesc1" type="text">
						</div>
						<div class="input_box">
							<input name="consultPgmGuideDetailDTO.equipDesc2" type="text">
						</div>
						<div class="input_read">
							<input name="consultPgmGuideDetailDTO.equipDesc3" type="text">
						</div>
					</div>
				</div>
				
				
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:text property="consultPgmGuideDetailDTO.remark"
							tabindex="110" />
					</div>
				</div>
				
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea property="consultPgmGuideDetailDTO.remark" styleClass="ta50" tabindex="120" />
					</div>
				</div>
				
				

				
				<!-- field group -->
				<div class="field_group_wide">
					<fieldset>
						<legend>1월</legend>
						<!-- 위치 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.location" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqLocDesc"
									tabindex="20" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 종류 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.type" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqCtgDesc"
									tabindex="30" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 설비 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.equipDesc" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.equipDesc"
									tabindex="40" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 설비 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.equipDesc" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.equipDesc"
									tabindex="40" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 비고 -->
						<div class="field_long">
							<label><bean:message key="LABEL.remark" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.remark"
									tabindex="110" />
							</div>
						</div>
					</fieldset>
				</div>
				<!-- field group end -->
				<!-- field group -->
				<div class="field_group">
					<fieldset>
						<legend>2월</legend>
						<!-- 위치 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.location" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqLocDesc"
									tabindex="20" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 종류 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.type" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqCtgDesc"
									tabindex="30" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 설비 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.equipDesc" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.equipDesc"
									tabindex="40" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 설비 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.equipDesc" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.equipDesc"
									tabindex="40" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
					</fieldset>
				</div>
				<!-- field group end -->
				<!-- field group -->
				<div class="field_group">
					<fieldset>
						<legend>3월</legend>
						<!-- 위치 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.location" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqLocDesc2"
									tabindex="20" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 종류 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.type" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqCtgDesc"
									tabindex="30" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 비고 -->
						<div class="field">
							<label><bean:message key="LABEL.remark" /></label>
							<div class="input_box">
								<textarea name="maConfigDetailDTO.configDesc" tabindex="5" class="ta50">배치프로그램 실행시 사용할 User</textarea>
							</div>
						</div>
						
					</fieldset>
				</div>
				<!-- field group end -->
				<!-- field group -->
				<div class="field_group">
					<fieldset>
						<legend>4월</legend>
						<!-- 위치 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.location" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqLocDesc"
									tabindex="20" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 종류 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.type" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.eqCtgDesc"
									tabindex="30" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
						<!-- 설비 -->
						<div class="field">
							<label class="check"><bean:message key="LABEL.equipDesc" /></label>
							<div class="input_box">
								<html:text property="consultPgmGuideDetailDTO.equipDesc"
									tabindex="40" />
								<p class="open_spop">
									<a> <span>조회</span>
									</a>
								</p>
							</div>
						</div>
					</fieldset>
				</div>
				<!-- field group end -->
								<!-- 다섯개필드 -->
				<div class="field_long ty5">
					<label>다섯개</label>
					<div class="multi_wrap">
						<div class="input_box">
							<div class="slabel">100% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">80% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">60% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">40% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">20% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
					</div>
				</div>
				<!-- 네개필드 -->
				<div class="field_long ty4">
					<label>네개</label>
					<div class="multi_wrap">
						<div class="input_box">
							<div class="slabel">100% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">80% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">60% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">40% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc" type="text">
						</div>
					</div>
				</div>
				<!-- 세개필드 -->
				<div class="field_long ty3">
					<label>세개</label>
					<div class="multi_wrap">
						<div class="input_box">
							<div class="slabel">100% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc1" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">80% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc2" type="text">
						</div>
						<div class="input_box">
							<div class="slabel">60% : </div>
							<input name="consultPgmGuideDetailDTO.equipDesc3" type="text">
						</div>
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
