<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="mobile.dream.work.pm.list.action.WorkPmWorkListAction"%>
<%@ page import="mobile.dream.work.pm.list.action.WorkPmWorkDetailAction"%>
<head>
<title><bean:message key="MENU.PMWORK"/></title>
<meta name="decorator" content="mobilePage">

</head>

<body>
<html:form action="/workPmWorkList.do">
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
<html:hidden property="workPmWorkCommonDTO.wkorId"/>
<html:hidden property="workPmWorkCommonDTO.equipId"/>
<html:hidden property="workPmWorkCommonDTO.deptId"/>
<html:hidden property="workPmWorkCommonDTO.eqLocId"/>
<html:hidden property="workPmWorkCommonDTO.wkCtrId"/>
<html:hidden property="workPmWorkCommonDTO.eqCtgType"/>
    	<div class="form_wrap">
        	<div class="field ty_srch">
            	<div class="btn b_srch"><a><span>Search</span></a></div>
                <div class="input_tx">
                	<html:text property="workPmWorkCommonDTO.searchText"></html:text>
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
        
        
        <div id="moreOption" class="layer_pop large" style="display: none;z-index: 1000;">
			<div class="pop_header">
				<div class="pop_title">More Search Options</div>
				<div class="b_pclose" style="cursor: pointer;"><a><span>Close</span></a></div>
			</div>
			<div class="pop_content_wrap">
				<div class="form_wrap">
					<div class="field ty_time">
						<label><bean:message key="LABEL.woFromDate"/></label>
						<div class="btn b_date"><a><span>날짜</span></a></div>
						<div class="input_tx">
							<html:text styleClass="datatx" property="workPmWorkCommonDTO.woDateFrom" readonly="true"></html:text>
						</div>
					</div>
					<div class="field ty_time">
						<label><bean:message key="LABEL.woToDate"/></label>
						<div class="btn b_date"><a><span>날짜</span></a></div>
						<div class="input_tx">
							<html:text styleClass="datatx" property="workPmWorkCommonDTO.woDateTo" readonly="true"></html:text>
						</div>
					</div>
					<div class="field ty_srch">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="btn b_srch"><a><span>Search</span></a></div>
						<div class="input_tx">
							<html:text property="workPmWorkCommonDTO.deptDesc" readonly="true"></html:text>
						</div>
					</div>
					<div class="field ty_srch">
						<label><bean:message key="LABEL.wkCtr"/></label>
						<div class="btn b_srch"><a><span>Search</span></a></div>
						<div class="input_tx">
							<html:text property="workPmWorkCommonDTO.wkCtrDesc" readonly="true"></html:text>
						</div>
					</div>
					<div class="field ty_srch">
						<label><bean:message key="LABEL.eqLocDesc"/></label>
						<div class="btn b_srch"><a><span>Search</span></a></div>
						<div class="input_tx">
							<html:text property="workPmWorkCommonDTO.eqLocDesc" readonly="true"></html:text>
						</div>
					</div>
					<div class="field ty_srch">
						<label><bean:message key="LABEL.eqCtgType"/></label>
						<div class="btn b_srch"><a><span>Search</span></a></div>
						<div class="input_tx">
							<html:text property="workPmWorkCommonDTO.eqCtgTypeDesc" readonly="true"></html:text>
						</div>
					</div>
				</div>
			</div>
			<div class="footer_wrap option">
				<div class="btn_box option">
					<button type="button" id="apply">적 용</button>
				</div>
			</div>
		</div> 
</html:form>	
<script type="text/javascript">
var myList;

function loadPage()
{
	initList();
	
	findList("Search");
	
	//Auto Complete와 마찬가지로 첫번째 Param이 세팅각
	Dream.lov.equipment(["workPmWorkCommonDTO.searchText"]);
	Dream.lov.dept(["workPmWorkCommonDTO.deptDesc","workPmWorkCommonDTO.deptId"]);
	Dream.lov.wrkgrp(["workPmWorkCommonDTO.wkCtrDesc","workPmWorkCommonDTO.wkCtrId"]);
	Dream.lov.assetCateg(["workPmWorkCommonDTO.eqCtgTypeDesc","workPmWorkCommonDTO.eqCtgType"]);
	Dream.lov.assetLoc(["workPmWorkCommonDTO.eqLocDesc","workPmWorkCommonDTO.eqLocId"]);
	
	$('.open_option').on("click",function(e){
		$('#moreOption').show();
	});
	
	$('.b_pclose').on("click",function(e){
		$('#moreOption').hide();
	});
	
	$('#apply').on("click",function(e){
		goSearch();
		$('#moreOption').hide();
	});
}

function goSearch()
{
	findList("Search");
}

function initList()
{
	myList = new dhtmlXList({
		container:"data_container"
		,type:{
			template:"<li class='list_box'><a><div class='t_name'>#STARTDATE#</div><div style='float:right;'>#PMTYPEDESC#</div>"+
   		 			 "<div class='t_title'>#EQUIPDESC#</div>"+
   		 			 "<div class='t_etc'>#WKORDESC#</div><div style='float:right;'>#WOSTATUSDESC#</div></a></li>",
			height:"120"
		}
	});
	
	myList.attachEvent("onItemClick", function (id, ev, html){
		var data = myList.get(id);
		//alert(data[t]);
		//alert(pmNo);
		//alert(ev.target.className);//nodeName		
		workPmWorkListForm.elements['workPmWorkCommonDTO.wkorId'].value = data.WKORID;
		workPmWorkListForm.elements['strutsAction'].value = '<%= WorkPmWorkDetailAction.PM_WORK_DETAIL_INIT%>';
		
		submitPost("workPmWorkDetail", FormQueryString(workPmWorkListForm));
		
		return true;
	    
	});
}

function afterSetValue(inputName)
{
	//if(inputName == "workPmWorkCommonDTO.searchText") goSearch();
}

function afterSearch()
{
	reloadPage(myList,"WKORID","workPmWorkCommonDTO.wkorId");
}

function findList(sheetAction)
{
	workPmWorkListForm.elements['strutsAction'].value = '<%=WorkPmWorkListAction.PM_WORK_LIST_FIND%>'; 

	doListAction(sheetAction, myList, "workPmWorkList", FormQueryString(workPmWorkListForm), "Y");
    
}

function goCreate(_page)
{
	goCommonMobilePage(workPmWorkListForm, '', "workPmWorkDetail");
    
}

</script>
</body>
