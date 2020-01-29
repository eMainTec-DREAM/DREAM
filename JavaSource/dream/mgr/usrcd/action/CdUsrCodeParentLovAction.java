package dream.mgr.usrcd.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrcd.dto.CdUsrCodeParentLovDTO;
import dream.mgr.usrcd.form.CdUsrCodeParentLovForm;
import dream.mgr.usrcd.service.CdUsrCodeParentLovService;

/**
 * 사용자코드 부모 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/cdUsrCodeParentLov" name="cdUsrCodeParentLovForm"
 *                input="/dream/mgr/usrcd/cdUsrCodeParentLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="cdUsrCodeParentLov" path="/dream/mgr/usrcd/cdUsrCodeParentLov.jsp"
 *                        redirect="false"
 */

public class CdUsrCodeParentLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CdUsrCodeParentLovForm cdUsrCodeParentLovForm = (CdUsrCodeParentLovForm) form;
        
        switch (cdUsrCodeParentLovForm.getStrutsAction())
        {
            case CdUsrCodeParentLovAction.BASE_SET_HEADER:
                setHeader(request, response, cdUsrCodeParentLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CdUsrCodeParentLovAction.LIST_FIND:
                findList(request, response, cdUsrCodeParentLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("cdUsrCodeParentLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, CdUsrCodeParentLovForm cdUsrCodeParentLovForm) throws IOException
    {
        super.setHeader(request, response, cdUsrCodeParentLovForm.getListId(), cdUsrCodeParentLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param cdUsrCodeParentLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, CdUsrCodeParentLovForm cdUsrCodeParentLovForm, boolean excelExport) throws Exception
    {
    	CdUsrCodeParentLovService cdUsrCodeParentLovService = (CdUsrCodeParentLovService) getBean("cdUsrCodeParentLovService");
    	CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO = cdUsrCodeParentLovForm.getCdUsrCodeParentLovDTO();
    	
    	//Paging
    	cdUsrCodeParentLovDTO.setIsLoadMaxCount("Y".equals(cdUsrCodeParentLovForm.getIsLoadMaxCount())?true:false);
    	cdUsrCodeParentLovDTO.setFirstRow(cdUsrCodeParentLovForm.getFirstRow());
    	cdUsrCodeParentLovDTO.setOrderBy(cdUsrCodeParentLovForm.getOrderBy());
    	cdUsrCodeParentLovDTO.setDirection(cdUsrCodeParentLovForm.getDirection());
    	
        List resultList = cdUsrCodeParentLovService.findList(cdUsrCodeParentLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(cdUsrCodeParentLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = cdUsrCodeParentLovService.findTotalCount(cdUsrCodeParentLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,cdUsrCodeParentLovForm.getListId(),cdUsrCodeParentLovForm.getCurrentPageId(), cdUsrCodeParentLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
