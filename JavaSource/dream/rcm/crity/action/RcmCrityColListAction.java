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
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.form.RcmCrityColListForm;
import dream.rcm.crity.service.RcmCrityColListService;

/**
 * Criticality Matrix Col Page - List Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityColListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityColList" name="rcmCrityColListForm"
 *                input="/dream/rcm/crity/rcmCrityColList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityColList" path="/dream/rcm/crity/rcmCrityColList.jsp"
 *                        redirect="false"
 */

public class RcmCrityColListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityColListForm rcmCrityColListForm = (RcmCrityColListForm) form;
        
        switch (rcmCrityColListForm.getStrutsAction())
        {
            case RcmCrityColListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmCrityColListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmCrityColListAction.LIST_FIND:
                findList(request, response, rcmCrityColListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case RcmCrityColListAction.LIST_DELETE:
            	deleteList(request, rcmCrityColListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case RcmCrityColListAction.BASE_GRID_EXPORT:
            	findList(request, response, rcmCrityColListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmCrityColList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmCrityColListForm rcmCrityColListForm) throws IOException
    {
        super.setHeader(request, response, rcmCrityColListForm.getListId(), rcmCrityColListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityColListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmCrityColListForm rcmCrityColListForm, boolean excelExport) throws Exception
    {
    	RcmCrityColListService rcmCrityColListService = (RcmCrityColListService) getBean("rcmCrityColListService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityColListForm.getRcmCrityCommonDTO();
    	RcmCrityColListDTO rcmCrityColListDTO = rcmCrityColListForm.getRcmCrityColListDTO();
    	rcmCrityCommonDTO.setIsLoadMaxCount("Y".equals(rcmCrityColListForm.getIsLoadMaxCount())?true:false);
    	rcmCrityCommonDTO.setFirstRow(rcmCrityColListForm.getFirstRow());
    	rcmCrityCommonDTO.setOrderBy(rcmCrityColListForm.getOrderBy());
    	rcmCrityCommonDTO.setDirection(rcmCrityColListForm.getDirection());
        
        List resultList = rcmCrityColListService.findList(rcmCrityCommonDTO,rcmCrityColListDTO, getUser(request));
    	String totalCount = "";
        if(Integer.parseInt(rcmCrityColListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmCrityColListService.findTotalCount(rcmCrityCommonDTO,rcmCrityColListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmCrityColListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
     
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param rcmCrityColListForm
     */
    private void deleteList(HttpServletRequest request, RcmCrityColListForm rcmCrityColListForm) throws Exception
    {
    	RcmCrityColListService rcmCrityColListService = (RcmCrityColListService) getBean("rcmCrityColListService");
        String[] deleteRows = rcmCrityColListForm.getDeleteRows();
        rcmCrityColListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
