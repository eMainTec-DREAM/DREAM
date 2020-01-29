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
import dream.rcm.crity.dto.RcmCrityRowListDTO;
import dream.rcm.crity.form.RcmCrityRowListForm;
import dream.rcm.crity.service.RcmCrityRowListService;

/**
 * Criticality Matrix Row Page - List Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityRowListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityRowList" name="rcmCrityRowListForm"
 *                input="/dream/rcm/crity/rcmCrityRowList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityRowList" path="/dream/rcm/crity/rcmCrityRowList.jsp"
 *                        redirect="false"
 */

public class RcmCrityRowListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityRowListForm rcmCrityRowListForm = (RcmCrityRowListForm) form;
        
        switch (rcmCrityRowListForm.getStrutsAction())
        {
            case RcmCrityRowListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmCrityRowListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmCrityRowListAction.LIST_FIND:
                findList(request, response, rcmCrityRowListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case RcmCrityRowListAction.LIST_DELETE:
            	deleteList(request, rcmCrityRowListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case RcmCrityRowListAction.BASE_GRID_EXPORT:
            	findList(request, response, rcmCrityRowListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmCrityRowList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmCrityRowListForm rcmCrityRowListForm) throws IOException
    {
        super.setHeader(request, response, rcmCrityRowListForm.getListId(), rcmCrityRowListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityRowListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmCrityRowListForm rcmCrityRowListForm, boolean excelExport) throws Exception
    {
    	RcmCrityRowListService rcmCrityRowListService = (RcmCrityRowListService) getBean("rcmCrityRowListService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityRowListForm.getRcmCrityCommonDTO();
    	RcmCrityRowListDTO rcmCrityRowListDTO = rcmCrityRowListForm.getRcmCrityRowListDTO();
    	rcmCrityCommonDTO.setIsLoadMaxCount("Y".equals(rcmCrityRowListForm.getIsLoadMaxCount())?true:false);
    	rcmCrityCommonDTO.setFirstRow(rcmCrityRowListForm.getFirstRow());
    	rcmCrityCommonDTO.setOrderBy(rcmCrityRowListForm.getOrderBy());
    	rcmCrityCommonDTO.setDirection(rcmCrityRowListForm.getDirection());
        
        List resultList = rcmCrityRowListService.findList(rcmCrityCommonDTO,rcmCrityRowListDTO, getUser(request));
    	
        String totalCount = "";
        if(Integer.parseInt(rcmCrityRowListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmCrityRowListService.findTotalCount(rcmCrityCommonDTO,rcmCrityRowListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmCrityRowListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
  
        
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param rcmCrityRowListForm
     */
    private void deleteList(HttpServletRequest request, RcmCrityRowListForm rcmCrityRowListForm) throws Exception
    {
    	RcmCrityRowListService rcmCrityRowListService = (RcmCrityRowListService) getBean("rcmCrityRowListService");
        String[] deleteRows = rcmCrityRowListForm.getDeleteRows();
        rcmCrityRowListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
