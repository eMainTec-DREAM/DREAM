package dream.mgr.contract.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.contract.dto.LovMgrContractListDTO;
import dream.mgr.contract.form.LovMgrContractListForm;
import dream.mgr.contract.service.LovMgrContractListService;

/**
 * 단가계약 LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/lovMgrContractList" name="lovMgrContractListForm"
 *                input="/dream/mgr/contract/lovMgrContractList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovMgrContractList" path="/dream/mgr/contract/lovMgrContractList.jsp"
 *                        redirect="false"
 */

public class LovMgrContractListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovMgrContractListForm lovMgrContractListForm = (LovMgrContractListForm) form;
        
        switch (lovMgrContractListForm.getStrutsAction())
        {
            case LovMgrContractListAction.BASE_SET_HEADER:
                setHeader(request, response, lovMgrContractListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMgrContractListAction.LIST_FIND:
                findList(request, response, lovMgrContractListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovMgrContractList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovMgrContractListForm lovMgrContractListForm) throws IOException
    {
        super.setHeader(request, response, lovMgrContractListForm.getListId(), lovMgrContractListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovMgrContractListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovMgrContractListForm lovMgrContractListForm, boolean excelExport) throws Exception
    {
    	LovMgrContractListService lovMgrContractListService = (LovMgrContractListService) getBean("lovMgrContractListService");
    	LovMgrContractListDTO lovMgrContractListDTO = lovMgrContractListForm.getLovMgrContractListDTO();
    	
    	//Paging
    	lovMgrContractListDTO.setIsLoadMaxCount("Y".equals(lovMgrContractListForm.getIsLoadMaxCount())?true:false);
    	lovMgrContractListDTO.setFirstRow(lovMgrContractListForm.getFirstRow());
    	lovMgrContractListDTO.setOrderBy(lovMgrContractListForm.getOrderBy());
    	lovMgrContractListDTO.setDirection(lovMgrContractListForm.getDirection());
    	
        List resultList = lovMgrContractListService.findList(lovMgrContractListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovMgrContractListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovMgrContractListService.findTotalCount(lovMgrContractListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovMgrContractListForm.getListId(),lovMgrContractListForm.getCurrentPageId(), lovMgrContractListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
