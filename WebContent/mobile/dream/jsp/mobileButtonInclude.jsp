<%--===========================================================================
하단 버튼
author  javaworker
version $Id: buttonInclude.jsp,v 1.2 2013/12/23 06:35:27 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ page import="common.util.CommonUtil"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.bean.MwareConfig"%>

<%
String currentPageId = (String)request.getAttribute("currentPageId");
ArrayList buttonList = (ArrayList)request.getAttribute("pageButton");

%>

<div class="footer_wrap" style="display:none; ">
	<div class="btn_box">
	</div>
</div>
<script>
$(window).ready(function(){
<%  

	if(buttonList != null)
	{
		int btnLength = buttonList.size();
		for(int i = 0; btnLength > i; i++)
		{
		    Map button = (Map)buttonList.get(i);
		    
		    //String padeId = String.valueOf(button.get("FILENAME"));
		    String buttonId 	= String.valueOf(button.get("BUTTONID")).toLowerCase();
		    String buttonType 	= String.valueOf(button.get("BUTTONLOC")).toLowerCase();
		    String buttonDesc 	= String.valueOf(button.get("REMARK"));  //keyNo
		    String buttonImg 	= String.valueOf(button.get("BUTTONIMG"));
		    String isBasic   	= String.valueOf(button.get("ISBASIC"));
		    String isSetGroup 	= String.valueOf(button.get("ISSETGROUP"));

		 %>
        	makeButtonHtml("<%=currentPageId%>","<%=buttonId%>", "<%=buttonType%>", "<bean:message key='<%=buttonDesc%>'/>", "<%=buttonImg%>","<%=isBasic%>","<%=isSetGroup%>");
        <%
	        if(i == btnLength - 1)
	        {
	        	%>
	        	if(typeof afterBtnLoad == "function") afterBtnLoad(); 
	        	<%
	        }
		}
	}
	else
	{
		%>
    	if(typeof afterBtnLoad == "function") afterBtnLoad(); 
    	<%
	}
%>
});

function makeButtonHtml(pageId, buttonId, btnType, buttonDesc, buttonImg, isBasic, isSetGroup)
{
	var boxClass= "btn_box";
	var footerClass= "footer_wrap";
	if(buttonId =="create")
	{
		boxClass= "btn_disc_box";
		footerClass = "footer_disc_wrap";
		buttonDesc = "+";
		
		$('.btn_box:not(.option)').addClass('btn_disc_box').removeClass('btn_box');
		$('.footer_wrap:not(.option)').addClass('footer_disc_wrap').removeClass('footer_wrap');
	}
	
	var btnObj = $("<button type='button'></button>").addClass("b_"+buttonId+" "+pageId+"_btn").bind("click",function(){
		
		var btnId = buttonId.substr(0,1).toUpperCase() + buttonId.substr(1,buttonId.length);

		if(btnId == "Save")
		{
			$.globalEval("goSaveAll();");
		}
		else $.globalEval("go"+btnId+"('"+pageId+"');");
		
	}).text(buttonId=="create"?"+":buttonDesc);
	
	$('.'+boxClass+':not(.option)').append(btnObj);
	$('.'+footerClass+':not(.option)').show();

	if(typeof hideButton == "function") hideButton(buttonId);
}



//==== Mobile LOV ====//
var Dream = Dream || {};

Dream.lov = (function () {
	
  function setClick(_url, _values)
  { 
	  var inputObj 	= $('[name="'+_values[0]+'"]')
	  var btnObj 	= inputObj.parent().prev();
	  
	  if(!inputObj.length)return; 
	  
	  var bndObj = [inputObj, btnObj];
	  if(!inputObj.prop("readOnly")) bndObj = [btnObj];
	  
	  $.each(bndObj, function(i,v) {

		    v.on("click",function(e){
				  var pValId = new Array();
				  for(var seq in _values)
				  {
					  pValId[seq] = _values[seq];
				  }

				  lovValueId = pValId;
				  
				  var param = "strutsAction=1001";
				  openLayerPopup(_url, param);
		    });
	  });

  }
  //Equipment LOV
  var equipment = function(_values) {  
	  	setClick("assetListLovList", _values)   
       };
  //Employee LOV
  var emp = function(_values) {
	  	setClick("orgEmpLovList", _values)
       };
  //Department LOV
  var dept = function(_values) {
   	  	setClick("orgDeptLovList", _values)
       };
  var wrkgrp = function(_values) {
 	  	setClick("orgWkCtrLovList", _values)
       };
  var assetCateg = function(_values) {
    	setClick("assetCategListLov", _values)
       };
  var assetLoc = function(_values) {
    	setClick("assetLocListLov", _values)
       };
 //공개되는 API
 return {
       equipment 	: equipment,
       emp 			: emp,
       dept 		: dept,
       wrkgrp 		: wrkgrp,
       assetCateg	: assetCateg,
       assetLoc		: assetLoc
 };
})(); 
</script>