<%@ page import="mobile.dream.mapm.mains.action.MaPmInsListAction"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<head>
<title>Dream Mobile</title>
<meta name="decorator" content="mobileTabPage">

</head>

<body>
<html:form action="/maPmInsDetail.do">
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
<html:hidden property="maPmInsCommonDTO.pmId"/>
    	    <div class="form_wrap">
		      
		      
		      <div class="field ty_srch">
		        <div class="btn b_srch"><a href="#"><span>Search</span></a></div>
		        <div class="input_tx">
		          <input name="" type="text" value="1st UMA 1st Row Handling Robot">
		        </div>
		        <div class="btn b_barcode"><a href="#"><span>Barcode</span></a></div>
		      </div>
		      <div class="open_option"><a href="#">More Search Options</a></div>
		      <div class="field ty_readonly">
		        <div class="input_tx">
		          <input type="text" value="1공장 &lt; 조립라인 &lt; 100 Station">
		        </div>
		      </div>
		      <div class="field ty_time">
		        <label>이상신고시간</label>
		        <div class="btn b_date"><a href="#"><span>날짜</span></a></div>
		        <div class="input_tx">
		          <input name="" type="text" value=" 2017-04-25" class="datatx">
		        </div>
		        <div class="btn b_time"><a href="#"><span>시각</span></a></div>
		        <div class="input_tx">
		          <input name="" type="text" value="09:30" class="timetx">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>현상</label>
		        <div class="input_tx">
		          <input name="" type="text" value="구동부에서 소음이 심하게 발생하고 있음.">
		        </div>
		      </div>
		      <div class="field">
		        <label>신고자</label>
		        <div class="input_tx">
		          <input name="" type="text" value="홍길동/생산팀">
		        </div>
		      </div>
		      <div class="field ty_srch">
		        <label>작업자</label>
		        <div class="btn b_srch"><a href="#"><span>Search</span></a></div>
		        <div class="input_tx">
		          <input name="" type="text" value="1st UMA 1st Row Handling Robot">
		        </div>
		        <div class="btn b_barcode"><a href="#"><span>Barcode</span></a></div>
		      </div>
		      <div class="field">
		        <label>측정값</label>
		        <div class="input_tx">
		          <input name="" type="text" value="40" class="ty_num">
		        </div>
		      </div>
		      <div class="field">
		        <label>비고</label>
		        <div class="input_tx">

		        </div>
		      </div>
		      
		    </div> <!-- End of Form Wrap -->
		    
		 
<!-- 		  <div class="footer_wrap">
		    <div class="btn_box">
		      <button>Button</button>
		    </div>
		  </div> -->
		  
</html:form>	
<script type="text/javascript">
var myList;
function loadPage()
{
	$('.top_body').css("overflow","auto");
}

function goSave(_page)
{
	alert(_page+"SAVE");
}

</script>
</body>
