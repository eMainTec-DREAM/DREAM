package dream.work.service.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.service.dto.LovWorkServiceListDTO;
import dream.work.service.form.LovWorkServiceListForm;
import dream.work.service.service.LovWorkServiceListService;

/**
 * 서비스 LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/lovWorkServiceList" name="lovWorkServiceListForm"
 *                input="/dream/work/service/lovWorkServiceList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWorkServiceList" path="/dream/work/service/lovWorkServiceList.jsp"
 *                        redirect="false"
 */

public class LovWorkServiceListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovWorkServiceListForm lovWorkServiceListForm = (LovWorkServiceListForm) form;
        
        switch (lovWorkServiceListForm.getStrutsAction())
        {
            case LovWorkServiceListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWorkServiceListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWorkServiceListAction.LIST_FIND:
                findList(request, response, lovWorkServiceListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovWorkServiceList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWorkServiceListForm lovWorkServiceListForm) throws IOException
    {
        super.setHeader(request, response, lovWorkServiceListForm.getListId(), lovWorkServiceListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovWorkServiceListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovWorkServiceListForm lovWorkServiceListForm, boolean excelExport) throws Exception
    {
    	LovWorkServiceListService lovWorkServiceListService = (LovWorkServiceListService) getBean("lovWorkServiceListService");
    	LovWorkServiceListDTO lovWorkServiceListDTO = lovWorkServiceListForm.getLovWorkServiceListDTO();
    	
    	//Paging
    	lovWorkServiceListDTO.setIsLoadMaxCount("Y".equals(lovWorkServiceListForm.getIsLoadMaxCount())?true:false);
    	lovWorkServiceListDTO.setFirstRow(lovWorkServiceListForm.getFirstRow());
    	lovWorkServiceListDTO.setOrderBy(lovWorkServiceListForm.getOrderBy());
    	lovWorkServiceListDTO.setDirection(lovWorkServiceListForm.getDirection());
    	
        List resultList = lovWorkServiceListService.findList(lovWorkServiceListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovWorkServiceListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWorkServiceListService.findTotalCount(lovWorkServiceListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovWorkServiceListForm.getListId(),lovWorkServiceListForm.getCurrentPageId(), lovWorkServiceListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
