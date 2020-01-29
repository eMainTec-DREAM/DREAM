package dream.rcm.pmtask.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.pmtask.dto.RcmPmtaskAcListDTO;
import dream.rcm.pmtask.form.RcmPmtaskAcListForm;
import dream.rcm.pmtask.service.RcmPmtaskAcListService;

/**
 * LOV- List Action
 * 
 * @author kim21017
 * @version $Id: RcmPmtaskAcListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmPmtaskAcList" name="rcmPmtaskAcListForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskAcList" path="/dream/rcm/pmtask/rcmPmtaskAcList.jsp"
 *                        redirect="false"
 */

public class RcmPmtaskAcListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskAcListForm rcmPmtaskAcListForm = (RcmPmtaskAcListForm) form;
        
        switch (rcmPmtaskAcListForm.getStrutsAction())
        {
            case RcmPmtaskAcListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmPmtaskAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskAcListAction.LIST_FIND:
                findList(request, response, rcmPmtaskAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("rcmPmtaskAcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmPmtaskAcListForm rcmPmtaskAcListForm) throws IOException
    {
        super.setHeader(request, response, rcmPmtaskAcListForm.getListId(), rcmPmtaskAcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmPmtaskAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmPmtaskAcListForm rcmPmtaskAcListForm, boolean excelExport) throws Exception
    {
    	RcmPmtaskAcListService rcmPmtaskAcListService = (RcmPmtaskAcListService) getBean("rcmPmtaskAcListService");
    	RcmPmtaskAcListDTO rcmPmtaskAcListDTO = rcmPmtaskAcListForm.getRcmPmtaskAcListDTO();
    	
    	//Paging
    	rcmPmtaskAcListDTO.setIsLoadMaxCount("Y".equals(rcmPmtaskAcListForm.getIsLoadMaxCount())?true:false);
    	rcmPmtaskAcListDTO.setFirstRow(rcmPmtaskAcListForm.getFirstRow());
    	rcmPmtaskAcListDTO.setOrderBy(rcmPmtaskAcListForm.getOrderBy());
    	rcmPmtaskAcListDTO.setDirection(rcmPmtaskAcListForm.getDirection());
    	
        List resultList = rcmPmtaskAcListService.findList(rcmPmtaskAcListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(rcmPmtaskAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmPmtaskAcListService.findTotalCount(rcmPmtaskAcListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,rcmPmtaskAcListForm.getListId(),rcmPmtaskAcListForm.getCurrentPageId(), rcmPmtaskAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
