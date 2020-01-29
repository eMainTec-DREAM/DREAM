package dream.invt.prc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.invt.prc.dto.InvtPrcTpLovDTO;
import dream.invt.prc.form.InvtPrcTpLovForm;
import dream.invt.prc.service.InvtPrcTpLovService;

/**
 * 구매절차 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/invtPrcTpLov" name="invtPrcTpLovForm"
 *                input="/dream/invt/prc/invtPrcTpLov.jsp" scope="request"
 *                validate="false"
 * 
 */
public class InvtPrcTpLovAction extends SuperAuthAction
{
    public static final int LOV_AC_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        InvtPrcTpLovForm invtPrcTpLovForm = (InvtPrcTpLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (invtPrcTpLovForm.getStrutsAction())
        {
            case InvtPrcTpLovAction.LOV_AC_FIND :
                findTaskMapList(request, invtPrcTpLovForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcTpLovAction.BASE_SET_HEADER:
                setHeader(request, response, invtPrcTpLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtPrcTpLovForm invtPrcTpLovForm) throws IOException
    {
        super.setHeader(request, response, invtPrcTpLovForm.getListId(),invtPrcTpLovForm.getCurrentPageId()); 
    }

    /**
     * 구매절차를 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovTaskMapListForm
     */
    private void findTaskMapList(HttpServletRequest request,
            InvtPrcTpLovForm invtPrcTpLovForm,HttpServletResponse response, boolean excelExport) throws IOException
    {
        InvtPrcTpLovService invtPrcTpLovService = (InvtPrcTpLovService)getBean("invtPrcTpLovService");
        
        InvtPrcTpLovDTO invtPrcTpLovDTO = invtPrcTpLovForm.getInvtPrcTpLovDTO();
        
        //Paging
        invtPrcTpLovDTO.setIsLoadMaxCount("Y".equals(invtPrcTpLovForm.getIsLoadMaxCount())?true:false);
        invtPrcTpLovDTO.setFirstRow(invtPrcTpLovForm.getFirstRow());
        invtPrcTpLovDTO.setOrderBy(invtPrcTpLovForm.getOrderBy());
        invtPrcTpLovDTO.setDirection(invtPrcTpLovForm.getDirection());
        
        List resultList = invtPrcTpLovService.findTaskMapAcList(invtPrcTpLovDTO,getUser(request),invtPrcTpLovForm );
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(invtPrcTpLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtPrcTpLovService.findTotalCount(invtPrcTpLovDTO,getUser(request),invtPrcTpLovForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, invtPrcTpLovForm.getListId(),invtPrcTpLovForm.getCurrentPageId(), invtPrcTpLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }

}