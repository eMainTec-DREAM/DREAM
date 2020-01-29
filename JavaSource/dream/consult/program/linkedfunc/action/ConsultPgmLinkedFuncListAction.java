package dream.consult.program.linkedfunc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.form.ConsultPgmLinkedFuncListForm;
import dream.consult.program.linkedfunc.service.ConsultPgmLinkedFuncListService;

/**
 * PgmLinkedFunc Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/consultPgmLinkedFuncList" name="consultPgmLinkedFuncListForm"
 *                input="/dream/consult/program/linkedfunc/consultPgmLinkedFuncList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmLinkedFuncList" path="/dream/consult/program/linkedfunc/consultPgmLinkedFuncList.jsp"
 *                        redirect="false"
 */
public class ConsultPgmLinkedFuncListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmLinkedFuncListForm consultPgmLinkedFuncListForm = (ConsultPgmLinkedFuncListForm) form;
        
        switch (consultPgmLinkedFuncListForm.getStrutsAction())
        {
            case ConsultPgmLinkedFuncListAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmLinkedFuncListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmLinkedFuncListAction.LIST_FIND:
                findList(request, response, consultPgmLinkedFuncListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultPgmLinkedFuncListAction.LIST_DELETE:
                deleteList(request, consultPgmLinkedFuncListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmLinkedFuncListAction.BASE_GRID_EXPORT:
                findList(request, response, consultPgmLinkedFuncListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultPgmLinkedFuncList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmLinkedFuncListForm consultPgmLinkedFuncListForm) throws IOException
    {
        super.setHeader(request, response, consultPgmLinkedFuncListForm.getListId(), consultPgmLinkedFuncListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultPgmLinkedFuncListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultPgmLinkedFuncListForm consultPgmLinkedFuncListForm, boolean excelExport) throws Exception
    {
        ConsultPgmLinkedFuncListService consultPgmLinkedFuncListService = (ConsultPgmLinkedFuncListService) getBean("consultPgmLinkedFuncListService");
        ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO = consultPgmLinkedFuncListForm.getConsultPgmLinkedFuncCommonDTO();
      
        //Paging
        consultPgmLinkedFuncCommonDTO.setIsLoadMaxCount("Y".equals(consultPgmLinkedFuncListForm.getIsLoadMaxCount())?true:false);
        consultPgmLinkedFuncCommonDTO.setFirstRow(consultPgmLinkedFuncListForm.getFirstRow());
        consultPgmLinkedFuncCommonDTO.setOrderBy(consultPgmLinkedFuncListForm.getOrderBy());
        consultPgmLinkedFuncCommonDTO.setDirection(consultPgmLinkedFuncListForm.getDirection());
        
        List resultList = consultPgmLinkedFuncListService.findPgmLinkedFuncList(consultPgmLinkedFuncCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultPgmLinkedFuncListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmLinkedFuncListService.findTotalCount(consultPgmLinkedFuncCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmLinkedFuncListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultPgmLinkedFuncListForm
     */
    private void deleteList(HttpServletRequest request, ConsultPgmLinkedFuncListForm consultPgmLinkedFuncListForm) throws Exception
    {
        ConsultPgmLinkedFuncListService consultPgmLinkedFuncListService = (ConsultPgmLinkedFuncListService) getBean("consultPgmLinkedFuncListService");
        String[] deleteRows = consultPgmLinkedFuncListForm.getDeleteRows();
        
        consultPgmLinkedFuncListService.deletePgmLinkedFuncList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
}