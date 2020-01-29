<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mobile.dream.mapm.mains.action.MaPmInsListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<head>
<title><bean:message key="MENU.maPmInsList"/></title>
<meta name="decorator" content="mobilePage">

</head>

<body>
<html:form action="/maPmInsList.do">
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
<html:hidden property="maPmInsCommonDTO.pmId"/>
    	<div class="form_wrap">
        	<div class="field ty_srch">
            	<div class="btn b_srch"><a><span>Search</span></a></div>
                <div class="input_tx">
                	<html:text property="maPmInsCommonDTO.searchText"></html:text>
                </div>
                <div class="btn b_barcode"><a ><span>Barcode</span></a></div>
            </div>
            <div class="open_option"><a >More Search Options</a></div>
        </div>
        <div class="list_wrap">
        	<div class="list_header">
            	<div class="b_edit"><a ><span>선택</span></a></div>
                <div class="count_box"><bean:message key="LABEL.all"/> : </div>
            </div>
            <ul>
        		<div id='data_container' style='width:100%;height:100px;'></div>
            </ul>
        </div>    
</html:form>	
<script type="text/javascript">
var myList;
function loadPage()
{
	initList();
	
	findList();
	
	$('.btn,b_srch').on('click',function(e){
		//TAMENU

		lovTable("maPmInsCommonDTO.pmId", "maPmInsCommonDTO.searchText", "TAEQUIPMENT");
		
		//lovSysDir("maPmInsCommonDTO.pmId", "maPmInsCommonDTO.searchText", "EQ_STATUS");
	});
}

function openMobileLayerPopup(_pageId, _param)
{
	var topDoc = getTopPage();
	var divLength = getTopPage().$(".pwin_wrap").length+1;

	if(typeof popupDivId == "undefined") popupDivId = "";
	
    var zIndex = 1300 + (divLength*2);
	var popupDivObj = $('<div></div>').prop({
		"id":"popupDiv"+divLength
		}).css({
		"position":"absolute",
		"margin":"0px",
		"display" : "",
		"z-index" : zIndex ,
		"top"	  : "-500px"
	}).addClass("pwin_wrap");

	var titleBar = $('<div></div>').prop({
		"id":"popupDivTitle"+divLength
		}).css({
		"position":"absolute",
		"vertical-align":"top",
		"margin":"0px",
		"display" : "",
		"width" : "100%",
		"height": "45px",
		"z-index" : zIndex,
		"right" : "50px"
	}).addClass("pop_tit")
	popupDivObj.append(titleBar);

	var modal = $('<div></div>').prop({
		"id":"modal"+divLength
		}).css({
		"position":"fixed",
		"top":"0px",
		"right": "0px",
		"bottom":"0px",
		"left":"0px",
		"margin":"0px",
		"padding":"0px",
		"display":"block",
		"background" : "url('./common/images/bg_pop.png') 0 0 repeat",
		"width" : "100%",
		"height": "100%",
		"z-index" : zIndex-1
	}).addClass("modal-window");	

	//Set the Popup Div ID for Closing
	if( typeof _param == "undefined" || _param == "")
	{
		_param = "isDecoratorName=popupPage&popupDivId="+divLength + "&parentPopupDivId="+popupDivId;
	}
	else
	{
		_param = _param + "&isDecoratorName=popupPage&popupDivId="+divLength + "&parentPopupDivId="+popupDivId;
	}

	if(topDoc.filterParam != "") 
	{
		_param = _param + topDoc.filterParam;
		topDoc.filterParam = "";
	}
	
	var iframeObj 	= $('<iframe></iframe>').prop({
		id		:"popupIframe"+divLength,
		name 	:"popupIframe"+divLength,
		scrolling :"no"
		//src		: contextPath + "/" + _pageId + ".do" + "?" + _param
	}).css({
		"height"	:"290px",
		"width"		:"100%",
		"z-index" 	:"1200",
		"border"	:"0px"
	});

	popupDivObj.append(iframeObj);
	
	if(DECORATOR_NAME == "defaultPage")
	{
		//$("#temCurDiaId").val(divLength);
		//$("#temParentDiaId").val('');
	}
	else
	{
		//parent.$("#temCurDiaId").val(divLength);
		//parent.$("#temParentDiaId").val(document.getElementById('curDiaId').value); //Parent Iframe ID Setting	
	}

	//topDoc.$("body").append(modal);
	topDoc.$("body").append(popupDivObj);

	//Submit as POST
	submitPost(_pageId, _param, "popupIframe"+divLength);
	
	popupDivObj.draggable();
}

function initList()
{
	myList = new dhtmlXList({
		container:"data_container"
		,type:{
			template:"<li class='list_box'><a><div class='t_name'>#EQUIPDESC#</div>"+
   		 			 "<div class='t_title'><span class='#Type#'>조치중</span>#R#</div>"+
   		 			 "<div class='t_etc'>#DESCRIPTION#</div></a></li>",
			height:"120"
		}
	});
	
	myList.attachEvent("onItemClick", function (id, ev, html){
		var data = myList.get(id);
		var t = "PMID";
		//alert(data[t]);
		//alert(pmNo);
		
		maPmInsListForm.elements['maPmInsCommonDTO.pmId'].value = data.PMID;
		goCommonMobilePage(maPmInsListForm, '<%= mobile.dream.mapm.mains.action.MaPmInsDetailAction.PM_INS_DETAIL_INIT%>', "maPmInsDetail");
	    return true;
	});
}

function afterSearch()
{
	reloadPage(myList,"PMID","maPmInsCommonDTO.pmId");
}

function findList(sheetAction)
{
	maPmInsListForm.elements['strutsAction'].value = '<%=MaPmInsListAction.PM_INS_LIST_FIND%>'; 

	doListAction("Search", myList, "maPmInsList", FormQueryString(maPmInsListForm), "Y");
    
}

function goCreate(_page)
{
	alert(_page+" 고크래이트");
}

</script>
</body>
