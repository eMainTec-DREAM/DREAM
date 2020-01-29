package dream.rcm.crity.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;
import dream.rcm.crity.form.RcmCrityValListForm;
import dream.rcm.crity.service.RcmCrityValListService;

/**
 * Criticality Matrix Val Page - List Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityValListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityValList" name="rcmCrityValListForm"
 *                input="/dream/rcm/crity/rcmCrityValList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityValList" path="/dream/rcm/crity/rcmCrityValList.jsp"
 *                        redirect="false"
 */

public class RcmCrityValListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityValListForm rcmCrityValListForm = (RcmCrityValListForm) form;
        
        switch (rcmCrityValListForm.getStrutsAction())
        {
            case RcmCrityValListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmCrityValListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmCrityValListAction.LIST_FIND:
                findList(request, response, rcmCrityValListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case RcmCrityValListAction.BASE_GRID_EXPORT:
            	findList(request, response, rcmCrityValListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmCrityValList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmCrityValListForm rcmCrityValListForm) throws IOException
    {
        super.setHeader(request, response, rcmCrityValListForm.getListId(), rcmCrityValListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityValListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmCrityValListForm rcmCrityValListForm, boolean excelExport) throws Exception
    {
    	RcmCrityValListService rcmCrityValListService = (RcmCrityValListService) getBean("rcmCrityValListService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityValListForm.getRcmCrityCommonDTO();
    	RcmCrityValListDTO rcmCrityValListDTO = rcmCrityValListForm.getRcmCrityValListDTO();
    	rcmCrityCommonDTO.setIsLoadMaxCount("Y".equals(rcmCrityValListForm.getIsLoadMaxCount())?true:false);
    	rcmCrityCommonDTO.setFirstRow(rcmCrityValListForm.getFirstRow());
    	rcmCrityCommonDTO.setOrderBy(rcmCrityValListForm.getOrderBy());
    	rcmCrityCommonDTO.setDirection(rcmCrityValListForm.getDirection());
        
        List resultList = rcmCrityValListService.findList(rcmCrityCommonDTO,rcmCrityValListDTO, getUser(request));
    	String totalCount = "";
        if(Integer.parseInt(rcmCrityValListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmCrityValListService.findTotalCount(rcmCrityCommonDTO,rcmCrityValListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmCrityValListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
  
        
    }
}
