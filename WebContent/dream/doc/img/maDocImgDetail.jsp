<%--===========================================================================
파일 - 상세
author  jung7126
version $Id: maDocImgDetail.jsp,v 1.5 2014/07/02 04:13:54 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@page import="common.bean.MwareConfig"%>
<%@ page
	import="dream.doc.img.action.MaDocImgDetailAction"%>
<!DOCTYPE html>
<html:html>
<head>
<title><bean:message key='TAB.maDocImgDetail' /></title>
<meta name="decorator" content="tabPage">
<style>
.progress { position:relative; width:98%; border: 0px solid #ddd; padding: 1px; border-radius: 3px; }
.bar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
.percent { position:absolute; display:inline-block; top:3px; left:48%; }
</style>
<script language="javascript">// 저장 시 수행되는 action
var addId, delId, myVault;
var deleteRows;
function loadPage() 
{
	deleteRows = new Array();
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maDocImgDetailDTO.objectId","maDocImgDetailDTO.description");
	//For Save
	setForUpdate();
	
	setReadOnly();
	
	//setTimeout(loadFileBox, 500);
}

function loadFileBox()
{
	var oldStrutsAction = maDocImgDetailForm.strutsAction.value;
	maDocImgDetailForm.strutsAction.value = '<%=MaDocImgDetailAction.DOCIMG_FILE_SAVE%>';
	var actionUrl = contextPath + "/maDocImgDetail.do?"+FormQueryString(maDocImgDetailForm)+"&isUploadPage=true";

	//파일 업로드 객체 생성!
	myVault = new dhtmlXVaultObject({
		container:  "vaultObj",             // html container for vault
	    uploadUrl:  actionUrl,           // html4/html5 upload url
		  //  swfPath:    "../../common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
		swfPath:    "common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
		swfUrl:     actionUrl        // flash upload url
		,autoStart : false
		,autoRemove : false
		//,buttonUpload  : true
		,buttonClear: false
	});
	myVault.setSkin('dhx_terrace'); //스킨 변경
	myVault.setProgressMode("eta");
	
	maDocImgDetailForm.strutsAction.value = oldStrutsAction;
	
	//Download 설정 세팅
    var downUrl = contextPath + "/download.do"; 
	myVault.setDownloadURL(downUrl+"?physicalFileName={serverName}&fileType=IMAGE&isDownloadPage=true");
	
	attachEvent();

	myVault.attachEvent("onUploadFail", function(file, extra){
		/* alert(extra.info); */
	});
	
	myVault.attachEvent("onBeforeFileAdd", function(file){
		var ext = this.getFileExtension(file.name);
		var imgFileType  = "<%=MwareConfig.getImgFileType()%>";		
		var fileSize  	 = "<%=MwareConfig.getMaxFileSize()%>";	
		var fileType  	 = "<%=MwareConfig.getFileType()%>";		
		
		var rtnValue = true;

		if((fileType.toUpperCase()).indexOf(ext.toUpperCase()) == -1) rtnValue =  true;
		else
		{
			//허용되지 않는 파일입니다.
			alertMessage1('<bean:message key="MESSAGE.MSG049"/>');
			rtnValue = false;
		}
		
		if((imgFileType.toUpperCase()).indexOf(ext.toUpperCase()) != -1) rtnValue =  true;
		else
		{
			//이미지 파일만 업로드 가능합니다.
			alertMessage1('<bean:message key="MESSAGE.MSG047"/>');
			rtnValue = false;
		}

		if(file.size > fileSize*1000000)
		{
			//xxMB 이하의 파일만 업로드 가능합니다.
			alertMessage1(fileSize+'<bean:message key="MESSAGE.MSG048"/>');
			rtnValue = false;
		} 
		
		return rtnValue;
	});
	
	//자동 업로드 완료 Event 설정 세팅
	myVault.attachEvent("onUploadComplete", function(files){
		loadUploadedFile();
		
		parent.reloadSlideImg(); //이미지 슬라이드 로딩
	});

	//처음 로딩시 파일 로딩
	loadUploadedFile();
}

function detachEvent()
{
	myVault.detachEvent(addId);
	
	myVault.detachEvent(delId);
}

function attachEvent()
{
	addId= myVault.attachEvent("onFileAdd", function(files){

		getTopPage().updateArray[currentPageId] = "FILE";  //업로드 셋
	});

	delId = myVault.attachEvent("onFileRemove", function(file){

		deleteRows.push(file.serverName);
		getTopPage().updateArray[currentPageId] = "FILE";  //업로드 셋
		return true;
	});
}

function loadUploadedFile()
{
	showFileAttach2(maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value, "IMAGE")
	.done(function(ajaxXmlDoc){
		var aImageNo  = parseXmlDoc(ajaxXmlDoc, 'ARR0');
	    var aFileName = parseXmlDoc(ajaxXmlDoc, 'ARR1');
	    var aFileType = parseXmlDoc(ajaxXmlDoc, 'ARR2');
	    var imageType = parseXmlDoc(ajaxXmlDoc, 'ARR4');
	    var aFileSize = parseXmlDoc(ajaxXmlDoc, 'ARR6');
	    
	    var dataLength = aImageNo.length;
		//JSON 형식으로 세팅
	    var jsonList = new Array();
	    
	    for (var i=0; i<dataLength; i++)
	    {
	    	var jsonObj = new Object();
	    	//alert(aFileName[i]+"   "+imageType[i]+"   "+aImageNo[i]+"    "+aFileSize[i]);
	    	jsonObj.name = aFileName[i];
	    	jsonObj.serverName = aImageNo[i];
	    	jsonObj.size = aFileSize[i];
	    	
	    	jsonList.push(jsonObj);
	        
	    	if(aImageNo[i] == "")myVault.disable();
	    }

	    var fileList = JSON.stringify(jsonList); //JSON String으로 변환
	    
	    detachEvent();
	    myVault.clear();	    
		myVault.load(fileList);
		attachEvent();
        
		if(getTopPage().disableAll || $('.b_save').size() ==0) $('.dhx_vault_button, .dhx_vault_file_delete').hide();
		
		if(typeof resizeTabFrame =="function") resizeTabFrame();

	});
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAIMAGE_ID');
	
	maDocImgDetailForm.elements['maDocImgDetailDTO.regDate'].value = getToday();		// loginUser User_Id
	maDocImgDetailForm.elements['maDocImgDetailDTO.regId'].value = loginUser.empId;	// loginUser User_Id
	maDocImgDetailForm.elements['maDocImgDetailDTO.regDesc'].value = loginUser.userName;	// loginUser User_Id deptId
	maDocImgDetailForm.elements['maDocImgDetailDTO.deptId'].value = loginUser.deptId;
	maDocImgDetailForm.elements['maDocImgDetailDTO.deptDesc'].value = loginUser.deptDesc;

	maDocImgDetailForm.elements['maDocImgDetailDTO.objectTypeDesc'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectType'].value;
	
	valSysDir('maDocImgDetailDTO.objectType', 'maDocImgDetailDTO.objectTypeDesc', 'OBJECT_TYPE', false);
	
	
}

function goUpdate()
{
	//goTabPage("maDocImgFileList");
	
	//showFileAttach(maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value, "IMAGE")
	setTimeout(loadFileBox, 200);
}

function goTabPage(pageId)
{
	goCommonTabPage(maDocImgDetailForm, '' , pageId);
}

function setSequenceVal(sequenceVal)
{
	maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value = sequenceVal;
	maDocImgDetailForm.elements['maDocImgCommonDTO.imageId'].value = sequenceVal;
	
	maDocImgDetailForm.elements['maDocImgDetailDTO.description'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectDesc'].value;
	maDocImgDetailForm.elements['maDocImgDetailDTO.objectType'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectType'].value;
	maDocImgDetailForm.elements['maDocImgDetailDTO.objectId'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectId'].value;
	
	setTimeout(loadFileBox, 200);
}

/**
 * 저장
 */ 
function goSave()
{
	var setTime;
	//================================
	// 필수 항목 체크한다.
	//================================
	//if (checkRequireValue("maDocImgDetailDTO.menuDesc", '<bean:message key="LABEL.menuName"/>')) return;
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maDocImgDetailForm.strutsAction.value = '<%=MaDocImgDetailAction.DOCIMG_DETAIL_INPUT%>';
	else maDocImgDetailForm.strutsAction.value = '<%=MaDocImgDetailAction.DOCIMG_DETAIL_UPDATE%>';

		var actionUrl = contextPath + "/maDocImgDetail.do";
		//XMLHttpPost(actionUrl, FormQueryString(maDocImgDetailForm), 'afterSave');

		myVault.upload();
		//$("form[name='maDocImgDetailForm']").prop("enctype","multipart/form-data");
		$("form[name='maDocImgDetailForm']").ajaxForm({ //jquery Form plugin
			beforeSubmit:function(data,frm, opt){ 
				
				var newArray = new Array();

				for(i = deleteRows.length-1; i >= 0 ; i--)
				{
					var newData = new Object();
					newData.name ='deleteRows';
					newData.value = deleteRows[i];

					data.push(newData)
				}
				
				if(isIE)
				{
					setTime = setInterval("prgressBar();", 200);
				}

			},
			uploadProgress: function(event, position, total, percentComplete) {
				prgressBar(percentComplete); 
		    },
			success:function(responseText, statusText){
				afterSave(responseText);
				
				if(isIE)
				{
					clearInterval(setTime);
					prgressBar(100);
				}
	
				
			},
			error :function(){
				//var files = e.originalEvent.dataTransfer.files;
				//alert(files);
			}
		}).submit();

	}

var perVal = 0;
function prgressBar(percentComplete)
{	
	var bar = $('.bar');
	var percent = $('.percent');
	
	if(typeof percentComplete =="undefined")
	{
		perVal = perVal + 1;
		
		if(perVal >= 99) perVal = 0;
	}
	else
	{
		perVal = percentComplete;
	}
	var percentVal = perVal + '%';

    bar.width(percentVal)
    percent.html(percentVal);
}

	/*== * 저장후 호출
	 */
	function afterSave(responseTxt) {
		//=====================================
		// if (!checkHttpXml(ajaxXmlDoc)) return;
		//=====================================

		maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.imageId'].value;
		if (parent.findGridRow)	parent.findGridRow(maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value);
		

		afterSaveAll(currentPageId);

		/* showFileAttach(
				maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value,
				"IMAGE"); */

		deleteRows = new Array();
		$("input[name^='fileList']").remove();
 		
		
		parent.reloadSlideImg();
	}
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0" width="900px">
	<%-- <c:import charEncoding="UTF-8" url="/common/jsp/topButtonInclude.jsp"></c:import> --%>
	<html:form action="/maDocImgDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="maDocImgCommonDTO.imageId" />
		<html:hidden property="maDocImgCommonDTO.compNo" />
		<html:hidden property="maDocImgCommonDTO.objectId" />
		<html:hidden property="maDocImgCommonDTO.objectType" />
		<html:hidden property="maDocImgCommonDTO.description" />
		<html:hidden property="maDocImgCommonDTO.objectDesc" />
		<html:hidden property="maDocImgCommonDTO.subImgType" />
		<html:hidden property="maDocImgDetailDTO.imageId" />
		<html:hidden property="maDocImgDetailDTO.objectType" />
		<html:hidden property="maDocImgDetailDTO.deptId" />
		<html:hidden property="maDocImgDetailDTO.regId" />
		<div class="article_box" id="detailBox">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.imageNo" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.objectId" tabindex="10"
							styleClass="read" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.imgObjType" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.objectTypeDesc" tabindex="20"
							styleClass="read" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.docDesc" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.description" tabindex="30" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.docDeptId" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.deptDesc" tabindex="50"
							styleClass="read" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.regId" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.regDesc" tabindex="60"
							styleClass="read" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.regDate" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.regDate" tabindex="70"
							styleClass="read" />
					</div>
				</div>
<%-- 				<div class="field_long">
					<label class="check fileBtn"><bean:message key="LABEL.file" /></label>
					<div class="file_box">
						<div class="fileadd_box">
							<div class="fileadd_btn">
								<a class="b_fileadd"><span>파일첨부</span></a>
							</div>
							<div class="fileadd_input"></div>
						</div>
					</div>
				</div>
				<div class="field_long">
					<label></label>
					<div class="input_box">
						<div class="progress">
					        <div class="bar"></div >
					        <div class="percent">0%</div >
					    </div>
					</div>
				</div> --%>
				<div class="field_long except">
					<label><bean:message key="LABEL.file"/></label>
					<div class="input_box">
							<div id="vaultObj" style="width:100%; height:250px;"></div>
					</div>
				 </div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
