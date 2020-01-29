package dream.mgr.usage.cal.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usage.cal.dto.LovUsageWrkAcListDTO;
import dream.mgr.usage.cal.form.LovUsageWrkAcListForm;
import dream.mgr.usage.cal.service.LovUsageWrkAcListService;

/**
 * 사용달력 LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/lovUsageWrkAcList" name="lovUsageWrkAcListForm"
 *                input="/dream/mgr/usage/cal/lovUsageWrkAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUsageWrkAcList" path="/dream/mgr/usage/cal/lovUsageWrkAcList.jsp"
 *                        redirect="false"
 */

public class LovUsageWrkAcListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovUsageWrkAcListForm lovUsageWrkAcListForm = (LovUsageWrkAcListForm) form;
        
        switch (lovUsageWrkAcListForm.getStrutsAction())
        {
            case LovUsageWrkAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovUsageWrkAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUsageWrkAcListAction.LIST_FIND:
                findList(request, response, lovUsageWrkAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovUsageWrkAcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovUsageWrkAcListForm lovUsageWrkAcListForm) throws IOException
    {
        super.setHeader(request, response, lovUsageWrkAcListForm.getListId(), lovUsageWrkAcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovUsageWrkAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovUsageWrkAcListForm lovUsageWrkAcListForm, boolean excelExport) throws Exception
    {
    	LovUsageWrkAcListService lovUsageWrkAcListService = (LovUsageWrkAcListService) getBean("lovUsageWrkAcListService");
    	LovUsageWrkAcListDTO lovUsageWrkAcListDTO = lovUsageWrkAcListForm.getLovUsageWrkAcListDTO();
    	
    	//Paging
    	lovUsageWrkAcListDTO.setIsLoadMaxCount("Y".equals(lovUsageWrkAcListForm.getIsLoadMaxCount())?true:false);
    	lovUsageWrkAcListDTO.setFirstRow(lovUsageWrkAcListForm.getFirstRow());
    	lovUsageWrkAcListDTO.setOrderBy(lovUsageWrkAcListForm.getOrderBy());
    	lovUsageWrkAcListDTO.setDirection(lovUsageWrkAcListForm.getDirection());
    	
        List resultList = lovUsageWrkAcListService.findList(lovUsageWrkAcListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovUsageWrkAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovUsageWrkAcListService.findTotalCount(lovUsageWrkAcListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovUsageWrkAcListForm.getListId(),lovUsageWrkAcListForm.getCurrentPageId(), lovUsageWrkAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
