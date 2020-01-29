package dream.mgr.usrdata.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.jasper.JasperFactory;
import common.struts.AuthAction;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.form.McDataSelectPopupForm;
import dream.mgr.usrdata.service.McDataSelectPopupService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;


/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: McDataSelectListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/mcDataSelectPopup" name="mcDataSelectPopupForm"
 *                input="/dream/mgr/usrdata/mcDataSelectPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcDataSelectPopup" path="/dream/mgr/usrdata/mcDataSelectPopup.jsp"
 *                        redirect="false"
 */
public class McDataSelectPopupAction extends AuthAction
{
    /** 조회 */
    public static final int DATA_LIST_FIND 		= 1001;
    /** 조회 */
    public static final int DATA_SCRIPT_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        McDataSelectPopupForm mcDataSelectPopupForm = (McDataSelectPopupForm) form;
        
        switch (mcDataSelectPopupForm.getStrutsAction())
        {
            case McDataSelectPopupAction.DATA_LIST_FIND:
                findList(request, mcDataSelectPopupForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McDataSelectPopupAction.DATA_SCRIPT_FIND:
                findScript(request, mcDataSelectPopupForm,response);
                returnActionForward = mapping.findForward("mcDataSelectPopup");
                break;
            case McDataSelectPopupAction.BASE_GRID_EXPORT:
            	findList(request, mcDataSelectPopupForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mcDataSelectPopup");
                break;
        }

        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, McDataSelectPopupForm mcDataSelectPopupForm) throws IOException
    {
        super.setHeader(request, response, mcDataSelectPopupForm.getListId(),mcDataSelectPopupForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: McDataSelectListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcDataSelectPopupForm
     * @throws IOException 
     * @throws JRException 
     * @throws DRException 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, McDataSelectPopupForm mcDataSelectPopupForm, HttpServletResponse response, boolean excelExport) throws IOException, JRException, DRException 
    {
    	McDataSelectPopupService mcDataSelectPopupService = (McDataSelectPopupService) getBean("mcDataSelectPopupService");        

    	McDataSelectCommonDTO mcDataSelectCommonDTO = mcDataSelectPopupForm.getMcDataSelectCommonDTO();
    	//Paging
    	mcDataSelectCommonDTO.setIsLoadMaxCount("Y".equals(mcDataSelectPopupForm.getIsLoadMaxCount())?true:false);
    	mcDataSelectCommonDTO.setFirstRow(mcDataSelectPopupForm.getFirstRow());
    	
    	String errMsg = "";
        //리스트를 조회한다.
        List resultList = null;
        //Paging
        String totalCount = "";
		try {
			resultList = mcDataSelectPopupService.findList(mcDataSelectCommonDTO,getUser(request));
			
			mcDataSelectCommonDTO.setIsLoadMaxCount(false);
			List rL2 = mcDataSelectPopupService.findList(mcDataSelectCommonDTO,getUser(request));
			if(Integer.parseInt(mcDataSelectPopupForm.getIsTotalCount()) == 0) totalCount = rL2.size()+"";

		} catch (Exception e) {
			e.printStackTrace();
			
			errMsg = e.getLocalizedMessage();
			errMsg = errMsg.split(":")[errMsg.split(":").length-1];
			resultList = new ArrayList();
		}finally{
		}

//		mcDataSelectPopupService.makeLogForScript(mcDataSelectCommonDTO,errMsg, getUser(request));
		
		
		//-------------------Commmon Dynamic Jasper Report ----------------------// 
//		JasperFactory report = new JasperFactory(); ///회사명(옵션), 회사로고 (옵션)
//		report.setMainTitle(mcDataSelectCommonDTO.getTitle()); // 리포트 타이틀 (옵션)
//		
//		report.setChartType("lineChart"); //차트 필수 //barChart,stackedBarChart,lineChart,areaChart,stackedBar3DChart,pieChart
//		report.setCategory("자재명"); //챠트 필수
//		report.setSerie("재고","재고2"); //챠트 필수
//
//		report.setShowValues(true); //챠트 옵션
//
//		report.makeReport(resultList);
//		report.view();
		//-----------------------------------------------------------------------//
		
        if(excelExport)super.makeGridExport(resultList, request, response,mcDataSelectPopupForm.getListId(),mcDataSelectPopupForm.getCurrentPageId(), mcDataSelectPopupForm.getFileName());
        else super.makeJsonListForReport(resultList, request, response, totalCount, errMsg);

    }
    
    private void findScript(HttpServletRequest request, McDataSelectPopupForm mcDataSelectPopupForm, HttpServletResponse response) throws Exception
    {
        McDataSelectPopupService mcDataSelectPopupService = (McDataSelectPopupService) getBean("mcDataSelectPopupService");        

        McDataSelectCommonDTO mcDataSelectCommonDTO = mcDataSelectPopupForm.getMcDataSelectCommonDTO();
        
        mcDataSelectCommonDTO = mcDataSelectPopupService.findScript(mcDataSelectCommonDTO, getUser(request));
        
        mcDataSelectPopupForm.setMcDataSelectCommonDTO(mcDataSelectCommonDTO);
    }
  
}
