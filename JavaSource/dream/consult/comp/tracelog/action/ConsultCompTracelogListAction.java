package dream.consult.comp.tracelog.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.form.ConsultCompTracelogListForm;
import dream.consult.comp.tracelog.service.ConsultCompTracelogListService;

/**
 * Screen Trace - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultCompTracelogList" name="consultCompTracelogListForm"
 *                input="/dream/consult/comp/tracelog/consultCompTracelogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompTracelogList" path="/dream/consult/comp/tracelog/consultCompTracelogList.jsp"
 *                        redirect="false"
 */

public class ConsultCompTracelogListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompTracelogListForm consultCompTracelogListForm = (ConsultCompTracelogListForm) form;
        
        switch (consultCompTracelogListForm.getStrutsAction())
        {
            case ConsultCompTracelogListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompTracelogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompTracelogListAction.LIST_FIND:
                findList(request, response, consultCompTracelogListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultCompTracelogListAction.LIST_DELETE:
            	deleteList(request, consultCompTracelogListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultCompTracelogListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultCompTracelogListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompTracelogList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompTracelogListForm consultCompTracelogListForm) throws IOException
    {
        super.setHeader(request, response, consultCompTracelogListForm.getListId(), consultCompTracelogListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultCompTracelogListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompTracelogListForm consultCompTracelogListForm, boolean excelExport) throws Exception
    {
    	ConsultCompTracelogListService consultCompTracelogListService = (ConsultCompTracelogListService) getBean("consultCompTracelogListService");
    	ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO = consultCompTracelogListForm.getConsultCompTracelogCommonDTO();

    	//Paging
    	consultCompTracelogCommonDTO.setIsLoadMaxCount("Y".equals(consultCompTracelogListForm.getIsLoadMaxCount())?true:false);
    	consultCompTracelogCommonDTO.setFirstRow(consultCompTracelogListForm.getFirstRow());
    	consultCompTracelogCommonDTO.setOrderBy(consultCompTracelogListForm.getOrderBy());
    	consultCompTracelogCommonDTO.setDirection(consultCompTracelogListForm.getDirection());
    	
        List resultList = consultCompTracelogListService.findCompTracelogList(consultCompTracelogCommonDTO,getUser(request));
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultCompTracelogListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompTracelogListService.findTotalCount(consultCompTracelogCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompTracelogListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompTracelogListForm
     */
    private void deleteList(HttpServletRequest request, ConsultCompTracelogListForm consultCompTracelogListForm) throws Exception
    {
    	ConsultCompTracelogListService consultCompTracelogListService = (ConsultCompTracelogListService) getBean("consultCompTracelogListService");
        String[] deleteRows = consultCompTracelogListForm.getDeleteRows();
        
        consultCompTracelogListService.deleteCompTracelogList(deleteRows,getUser(request));
        setAjaxStatus(request);
    }
    
}
