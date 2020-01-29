package dream.invt.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.invt.list.dto.InvtLovDTO;
import dream.invt.list.form.InvtLovForm;
import dream.invt.list.service.InvtLovService;

/**
 * 설비투자 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/invtLov" name="invtLovForm"
 *                input="/dream/invt/list/invtLov.jsp" scope="request"
 *                validate="false"
 * 
 */
public class InvtLovAction extends SuperAuthAction
{
    public static final int LOV_AC_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        InvtLovForm invtLovForm = (InvtLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (invtLovForm.getStrutsAction())
        {
            case InvtLovAction.LOV_AC_FIND :
                findTaskMapList(request, invtLovForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtLovAction.BASE_SET_HEADER:
                setHeader(request, response, invtLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtLovForm invtLovForm) throws IOException
    {
        super.setHeader(request, response, invtLovForm.getListId(),invtLovForm.getCurrentPageId()); 
    }

    /**
     * 설비투자를 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovTaskMapListForm
     */
    private void findTaskMapList(HttpServletRequest request,
            InvtLovForm invtLovForm,HttpServletResponse response, boolean excelExport) throws IOException
    {
        InvtLovService invtLovService = (InvtLovService)getBean("invtLovService", request);
        
        InvtLovDTO invtLovDTO = invtLovForm.getInvtLovDTO();
        
        //Paging
        invtLovDTO.setIsLoadMaxCount("Y".equals(invtLovForm.getIsLoadMaxCount())?true:false);
        invtLovDTO.setFirstRow(invtLovForm.getFirstRow());
        invtLovDTO.setOrderBy(invtLovForm.getOrderBy());
        invtLovDTO.setDirection(invtLovForm.getDirection());
        
        List resultList = invtLovService.findTaskMapAcList(invtLovDTO,getUser(request),invtLovForm );
                
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(invtLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtLovService.findTotalCount(invtLovDTO,getUser(request),invtLovForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, invtLovForm.getListId(),invtLovForm.getCurrentPageId(), invtLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }

}