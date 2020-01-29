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
import dream.rcm.crity.form.RcmCrityListForm;
import dream.rcm.crity.service.RcmCrityListService;

/**
 * Criticality Matrix Page - List Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityList" name="rcmCrityListForm"
 *                input="/dream/rcm/crity/rcmCrityList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityList" path="/dream/rcm/crity/rcmCrityList.jsp"
 *                        redirect="false"
 */

public class RcmCrityListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityListForm rcmCrityListForm = (RcmCrityListForm) form;
        
        switch (rcmCrityListForm.getStrutsAction())
        {
            case RcmCrityListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmCrityListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmCrityListAction.LIST_FIND:
                findList(request, response, rcmCrityListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case RcmCrityListAction.LIST_DELETE:
            	deleteList(request, rcmCrityListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case RcmCrityListAction.BASE_GRID_EXPORT:
            	findList(request, response, rcmCrityListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmCrityList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmCrityListForm rcmCrityListForm) throws IOException
    {
        super.setHeader(request, response, rcmCrityListForm.getListId(), rcmCrityListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmCrityListForm rcmCrityListForm,  boolean excelExport) throws Exception
    {
    	RcmCrityListService rcmCrityListService = (RcmCrityListService) getBean("rcmCrityListService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityListForm.getRcmCrityCommonDTO();
    	rcmCrityCommonDTO.setIsLoadMaxCount("Y".equals(rcmCrityListForm.getIsLoadMaxCount())?true:false);
    	rcmCrityCommonDTO.setFirstRow(rcmCrityListForm.getFirstRow());
    	rcmCrityCommonDTO.setOrderBy(rcmCrityListForm.getOrderBy());
    	rcmCrityCommonDTO.setDirection(rcmCrityListForm.getDirection());
        
        List resultList = rcmCrityListService.findList(rcmCrityCommonDTO, getUser(request));
    	String totalCount = "";
        if(Integer.parseInt(rcmCrityListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmCrityListService.findTotalCount(rcmCrityCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmCrityListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
      
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param rcmCrityListForm
     */
    private void deleteList(HttpServletRequest request, RcmCrityListForm rcmCrityListForm) throws Exception
    {
    	RcmCrityListService rcmCrityListService = (RcmCrityListService) getBean("rcmCrityListService");
        String[] deleteRows = rcmCrityListForm.getDeleteRows();
        rcmCrityListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
