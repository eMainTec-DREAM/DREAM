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

var deleteRows;
var myVault;
function loadPage() 
{
	deleteRows = new Array();
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maDocImgDetailDTO.objectId","maDocImgDetailDTO.description");
	//For Save
	setForUpdate();
	
	setReadOnly();
	
	setForFileAdd();
	
	setTimeout(loadFileBox, 500);
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
	});
	myVault.setSkin('dhx_terrace'); //스킨 변경

	maDocImgDetailForm.strutsAction.value = oldStrutsAction;
	
	//Download 설정 세팅
    var downUrl = contextPath + "/download.do"; 
	myVault.setDownloadURL(downUrl+"?physicalFileName={serverName}&fileType=IMAGE&isDownloadPage=true");
	
	//파일삭제 Event 설정 세팅
	myVault.attachEvent("onFileRemove", function(file){
		maDocImgDetailForm.strutsAction.value = '<%=MaDocImgDetailAction.DOCIMG_FILE_DELETE%>';
		actionUrl = contextPath + "/maDocImgDetail.do";
		//삭제!!
	    $.post(actionUrl,FormQueryString(maDocImgDetailForm)+"&deleteRows="+file.serverName, function(_data){});
	});
	
	myVault.attachEvent("onBeforeFileAdd", function(file){
		var ext = this.getFileExtension(file.name);
		var imgFileType  = "<%=MwareConfig.getImgFileType()%>";
		if(imgFileType.indexOf(ext) != -1) return true;
		else
		{
			alertMessage1('<bean:message key="COMMON.CMSG111"/>');
			return false;
		}
	});
	
	//자동 업로드 완료 Event 설정 세팅
	myVault.attachEvent("onUploadComplete", function(files){
		myVault.unload(); //닫았다가 새로 생성
		loadFileBox(); 
		getTopPage().updateArray[currentPageId] = "FILE";  //업로드 셋
		
		parent.reloadSlideImg(); //이미지 슬라이드 로딩
	});
	
	//처음 로딩시 파일 로딩
	loadUploadedFile();
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
		myVault.load(fileList);
	    
		if(getTopPage().disableAll || $('.b_save').size() ==0) $('.dhx_vault_button, .dhx_vault_file_delete').hide();
        
		if(typeof resizeTabFrame =="function") resizeTabFrame();

	});
}

function setForFileAdd()
{
	$('.b_fileadd').bind('click',function(){
		addFileList();
	});	
}

function addFileList()
{
	var fileLength = $("input[name^='fileList']").length;
	var fileObj = $("<input type='file' name='fileList["+fileLength+"]' class='file_"+fileLength+"'><p>");

	$('.fileadd_input').append(fileObj);
	
	$("input[name^='fileList']").on({
		"change" : function(){
			getTopPage().updateArray[currentPageId] = "FILE";

			var fName = $(this).val().split("\\");
			var rfName = fName[fName.length-1];
			
			if(rfName == "") return;
			
			var fileName = $("<div class='file_list' ><div class='file_name'><a>"+rfName+"</a></div></div>");
	    	var fileBtnSection = $("<div class='file_btn'></div>");
	    	fileBtnSection.append($("<a class='b_filedel'></a>").on("click",function(){
							    		$('.file_'+fileLength).remove();
							    		$(this).parents('.file_list').remove();
							    	}));
	    	
	    	fileName.append(fileBtnSection);
	    	$('.file_box').append(fileName);
	    	
	    	if(typeof resizeTabFrame =="function") resizeTabFrame();
		}
		/*"input propertychange" : function(e){
			alert("propertychange"+"   "+$(this).prop("name"));
			var fName = $(this).val().split("\\");
			var rfName = fName[fName.length-1];
			
			if(rfName == "") return;
			
			getTopPage().updateArray[currentPageId] = "FILE";
			var fileName = $("<div class='file_list' ><div class='file_name'><a>"+rfName+"</a></div></div>");
	    	var fileBtnSection = $("<div class='file_btn'></div>");
	    	fileBtnSection.append($("<a class='b_filedel'></a>").on("click",function(){
							    		$('.file_'+fileLength).remove();
							    		$(this).parents('.file_list').remove();
							    	}));
	    	
	    	fileName.append(fileBtnSection);
	    	$('.file_box').append(fileName);
		}*/
	});

	if(!isIE)
		fileObj.trigger("click");
	else
	{
		$("input[type='file']").show();
	}

	if(typeof resizeTabFrame =="function") resizeTabFrame();
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAIMAGE_ID');
	
	maDocImgDetailForm.elements['maDocImgDetailDTO.regDate'].value = getToday();		// loginUser User_Id
	maDocImgDetailForm.elements['maDocImgDetailDTO.regId'].value = loginUser.userId;	// loginUser User_Id
	maDocImgDetailForm.elements['maDocImgDetailDTO.regDesc'].value = loginUser.userName;	// loginUser User_Id deptId
	maDocImgDetailForm.elements['maDocImgDetailDTO.deptId'].value = loginUser.deptId;
	maDocImgDetailForm.elements['maDocImgDetailDTO.deptDesc'].value = loginUser.deptDesc;

	maDocImgDetailForm.elements['maDocImgDetailDTO.objectTypeDesc'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectType'].value;
	maDocImgDetailForm.elements['maDocImgDetailDTO.objectTypeDesc'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectType'].value;
	
	valSysDir('maDocImgDetailDTO.objectType', 'maDocImgDetailDTO.objectTypeDesc', 'OBJECT_TYPE', false);
	
	
}

function goUpdate()
{
	//goTabPage("maDocImgFileList");
	
	//showFileAttach(maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value, "IMAGE")
}

function goTabPage(pageId)
{
	goCommonTabPage(maDocImgDetailForm, '' , pageId);
}

function setSequenceVal(sequenceVal)
{
	maDocImgDetailForm.elements['maDocImgDetailDTO.imageId'].value = sequenceVal;
	maDocImgDetailForm.elements['maDocImgCommonDTO.imageId'].value = sequenceVal;
	
	maDocImgDetailForm.elements['maDocImgDetailDTO.description'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.description'].value;
	maDocImgDetailForm.elements['maDocImgDetailDTO.objectType'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectType'].value;
	maDocImgDetailForm.elements['maDocImgDetailDTO.objectId'].value = maDocImgDetailForm.elements['maDocImgCommonDTO.objectId'].value;
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

		//$("form[name='maDocImgDetailForm']").prop("enctype","multipart/form-data");
		$("form[name='maDocImgDetailForm']").ajaxForm({ //jquery Form plugin
			beforeSubmit : function(data, frm, opt) {
				/*for (var i = deleteRows.length - 1; i >= 0; i--) {
					var newData = new Object();
					newData.name = 'deleteRows';
					newData.value = deleteRows[i];

					data.push(newData);
				}


				var flag = false;
				$("input[name^='fileList']").each(function(e) {
					if ($(this).prop("type") == "text")
						return true;
					var fileName = $(this)[0].files[0].name;
					if (!(/\.(gif|jpg|jpeg|tiff|png)$/i).test(fileName)) {
						alertMessage1('You must select an image file only');
						flag = true;
						return false;
					}
				});

				if (flag)
					return false; //사진이 아닌게 있으면 멈춰
					*/
				//if (!(/\.(gif|jpg|jpeg|tiff|png)$/i).test(uploadFile.name)) {
				//    alert('You must select an image file only');
				//    return false;
				//}
				//if (uploadFile.size > 30000000) { // 30mb
				//    alert('Please upload a smaller image, max size is 30 MB');
				//    return false;
				//}

				if(isIE)
				{
					setTime = setInterval("prgressBar();", 200);
				}

			},
			uploadProgress: function(event, position, total, percentComplete) {
				prgressBar(percentComplete); 
		    },
			success : function(responseText, statusText) {
				afterSave(responseText);
				
				if(isIE)
				{
					clearInterval(setTime);
					prgressBar(100);
				}
			},
			error : function() {
				
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
	function afterSave(ajaxXmlDoc) {
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
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0" width="900px">
	<%-- <c:import charEncoding="UTF-8" url="/common/jsp/topButtonInclude.jsp"></c:import> --%>
	<html:form action="/maDocImgDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="maDocImgCommonDTO.imageId" />
		<html:hidden property="maDocImgCommonDTO.objectId" />
		<html:hidden property="maDocImgCommonDTO.objectType" />
		<html:hidden property="maDocImgCommonDTO.description" />
		<html:hidden property="maDocImgDetailDTO.imageId" />
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
						<html:text property="maDocImgDetailDTO.objectTypeDesc"
							tabindex="20"
							onkeydown="validationKeyDown('maDocImgDetailDTO.objectTypeDesc', 'maDocImgDetailDTO.objectType');"
							onblur="valSysDir('maDocImgDetailDTO.objectType', 'maDocImgDetailDTO.objectTypeDesc', 'OBJECT_TYPE', true);"
							styleClass="read" />
						<p class="open_spop">
							<a
								href="javascript:lovSysDir('maDocImgDetailDTO.objectType', 'maDocImgDetailDTO.objectTypeDesc','OBJECT_TYPE');"><span>조회</span></a>
						</p>
						<html:hidden property="maDocImgDetailDTO.objectType" />
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
						<html:hidden property="maDocImgDetailDTO.deptId" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.regId" /></label>
					<div class="input_box">
						<html:text property="maDocImgDetailDTO.regDesc" tabindex="60"
							styleClass="read" />
						<html:hidden property="maDocImgDetailDTO.regId" />
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
				<div class="field_long">
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
