package dream.consult.program.onlinehelp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;
import dream.consult.program.onlinehelp.form.ConsultProgramOnlinehelpListForm;
import dream.consult.program.onlinehelp.service.ConsultProgramOnlinehelpListService;

/**
 * 도움말 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/consultProgramOnlinehelpList" name="consultProgramOnlinehelpListForm"
 *                input="/dream/consult/program/onlinehelp/consultProgramOnlinehelpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultProgramOnlinehelpList" path="/dream/consult/program/onlinehelp/consultProgramOnlinehelpList.jsp"
 *                        redirect="false"
 */
public class ConsultProgramOnlinehelpListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int HELP_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int HELP_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultProgramOnlinehelpListForm consultProgramOnlinehelpListForm = (ConsultProgramOnlinehelpListForm) form;
        
        switch (consultProgramOnlinehelpListForm.getStrutsAction())
        {
            case ConsultProgramOnlinehelpListAction.HELP_LIST_FIND:
            	findHelpList(request, consultProgramOnlinehelpListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultProgramOnlinehelpListAction.BASE_SET_HEADER:
                setHeader(request, response, consultProgramOnlinehelpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultProgramOnlinehelpListAction.HELP_LIST_DELETE:
            	deleteHelp(request, consultProgramOnlinehelpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramOnlinehelpListAction.BASE_GRID_EXPORT:
            	findHelpList(request, consultProgramOnlinehelpListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultProgramOnlinehelpListForm consultProgramOnlinehelpListForm) throws IOException
    {
        super.setHeader(request, response, consultProgramOnlinehelpListForm.getListId(), consultProgramOnlinehelpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param consultProgramOnlinehelpListForm
     * @throws Exception
     */
    private void findHelpList(HttpServletRequest request, ConsultProgramOnlinehelpListForm consultProgramOnlinehelpListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ConsultProgramOnlinehelpListService consultProgramOnlinehelpListService = (ConsultProgramOnlinehelpListService) getBean("consultProgramOnlinehelpListService");        

    	ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO = consultProgramOnlinehelpListForm.getConsultProgramOnlinehelpCommonDTO();
    	
        //Paging
        consultProgramOnlinehelpCommonDTO.setIsLoadMaxCount("Y".equals(consultProgramOnlinehelpListForm.getIsLoadMaxCount())?true:false);
        consultProgramOnlinehelpCommonDTO.setFirstRow(consultProgramOnlinehelpListForm.getFirstRow());
        consultProgramOnlinehelpCommonDTO.setOrderBy(consultProgramOnlinehelpListForm.getOrderBy());
        consultProgramOnlinehelpCommonDTO.setDirection(consultProgramOnlinehelpListForm.getDirection());
        
        List resultList = consultProgramOnlinehelpListService.findHelpList(consultProgramOnlinehelpCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultProgramOnlinehelpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultProgramOnlinehelpListService.findTotalCount(consultProgramOnlinehelpCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,consultProgramOnlinehelpListForm.getListId(),consultProgramOnlinehelpListForm.getCurrentPageId(), consultProgramOnlinehelpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpListForm
     * @param request
     */
    private void deleteHelp(HttpServletRequest request, ConsultProgramOnlinehelpListForm consultProgramOnlinehelpListForm) throws Exception
    {
    	ConsultProgramOnlinehelpListService consultProgramOnlinehelpListService = (ConsultProgramOnlinehelpListService) getBean("consultProgramOnlinehelpListService");
        
    	String[] deleteRows = consultProgramOnlinehelpListForm.getDeleteRows();    // sheet 내역
        
        consultProgramOnlinehelpListService.deleteHelp(deleteRows);
        
        setAjaxStatus(request);
    }
}
