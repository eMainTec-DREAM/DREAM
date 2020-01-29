package dream.consult.program.error.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.form.ConsultPgmErrorListForm;
import dream.consult.program.error.service.ConsultPgmErrorListService;

/**
 * Error Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/consultPgmErrorList" name="consultPgmErrorListForm"
 *                input="/dream/consult/program/error/consultPgmErrorList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmErrorList" path="/dream/consult/program/error/consultPgmErrorList.jsp"
 *                        redirect="false"
 */

public class ConsultPgmErrorListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmErrorListForm consultPgmErrorListForm = (ConsultPgmErrorListForm) form;
        
        switch (consultPgmErrorListForm.getStrutsAction())
        {
            case ConsultPgmErrorListAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmErrorListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmErrorListAction.LIST_FIND:
                findList(request, response, consultPgmErrorListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultPgmErrorListAction.LIST_DELETE:
            	deleteList(request, consultPgmErrorListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultPgmErrorListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultPgmErrorListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("consultPgmErrorList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmErrorListForm consultPgmErrorListForm) throws IOException
    {
        super.setHeader(request, response, consultPgmErrorListForm.getListId(), consultPgmErrorListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultPgmErrorListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultPgmErrorListForm consultPgmErrorListForm, boolean excelExport) throws Exception
    {
    	ConsultPgmErrorListService consultPgmErrorListService = (ConsultPgmErrorListService) getBean("consultPgmErrorListService");
    	ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO = consultPgmErrorListForm.getConsultPgmErrorCommonDTO();

    	//Paging
    	consultPgmErrorCommonDTO.setIsLoadMaxCount("Y".equals(consultPgmErrorListForm.getIsLoadMaxCount())?true:false);
    	consultPgmErrorCommonDTO.setFirstRow(consultPgmErrorListForm.getFirstRow());
    	consultPgmErrorCommonDTO.setOrderBy(consultPgmErrorListForm.getOrderBy());
    	consultPgmErrorCommonDTO.setDirection(consultPgmErrorListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = consultPgmErrorListService.findPgmErrorList(consultPgmErrorCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultPgmErrorListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmErrorListService.findTotalCount(consultPgmErrorCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmErrorListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultPgmErrorListForm
     */
    private void deleteList(HttpServletRequest request, ConsultPgmErrorListForm consultPgmErrorListForm) throws Exception
    {
    	ConsultPgmErrorListService consultPgmErrorListService = (ConsultPgmErrorListService) getBean("consultPgmErrorListService");
        String[] deleteRows = consultPgmErrorListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        consultPgmErrorListService.deletePgmErrorList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
