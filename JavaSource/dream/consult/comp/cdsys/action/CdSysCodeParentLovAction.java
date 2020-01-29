package dream.consult.comp.cdsys.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.cdsys.dto.CdSysCodeParentLovDTO;
import dream.consult.comp.cdsys.form.CdSysCodeParentLovForm;
import dream.consult.comp.cdsys.service.CdSysCodeParentLovService;

/**
 * 시스템코드 부모 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: CdSysCodeParentLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/cdSysCodeParentLov" name="cdSysCodeParentLovForm"
 *                input="/dream/consult/comp/cdsys/cdSysCodeParentLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="cdSysCodeParentLov" path="/dream/consult/comp/cdsys/cdSysCodeParentLov.jsp"
 *                        redirect="false"
 */

public class CdSysCodeParentLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CdSysCodeParentLovForm cdSysCodeParentLovForm = (CdSysCodeParentLovForm) form;
        
        switch (cdSysCodeParentLovForm.getStrutsAction())
        {
            case CdSysCodeParentLovAction.BASE_SET_HEADER:
                setHeader(request, response, cdSysCodeParentLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CdSysCodeParentLovAction.LIST_FIND:
                findList(request, response, cdSysCodeParentLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("cdSysCodeParentLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, CdSysCodeParentLovForm cdSysCodeParentLovForm) throws IOException
    {
        super.setHeader(request, response, cdSysCodeParentLovForm.getListId(), cdSysCodeParentLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param cdSysCodeParentLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, CdSysCodeParentLovForm cdSysCodeParentLovForm, boolean excelExport) throws Exception
    {
    	CdSysCodeParentLovService cdSysCodeParentLovService = (CdSysCodeParentLovService) getBean("cdSysCodeParentLovService");
    	CdSysCodeParentLovDTO cdSysCodeParentLovDTO = cdSysCodeParentLovForm.getCdSysCodeParentLovDTO();
    	
    	//Paging
    	cdSysCodeParentLovDTO.setIsLoadMaxCount("Y".equals(cdSysCodeParentLovForm.getIsLoadMaxCount())?true:false);
    	cdSysCodeParentLovDTO.setFirstRow(cdSysCodeParentLovForm.getFirstRow());
    	cdSysCodeParentLovDTO.setOrderBy(cdSysCodeParentLovForm.getOrderBy());
    	cdSysCodeParentLovDTO.setDirection(cdSysCodeParentLovForm.getDirection());
    	
        List resultList = cdSysCodeParentLovService.findList(cdSysCodeParentLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(cdSysCodeParentLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = cdSysCodeParentLovService.findTotalCount(cdSysCodeParentLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,cdSysCodeParentLovForm.getListId(),cdSysCodeParentLovForm.getCurrentPageId(), cdSysCodeParentLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
