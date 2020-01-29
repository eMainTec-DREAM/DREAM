package dream.mgr.ptwh.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.form.MgrPtWhListForm;
import dream.mgr.ptwh.service.MgrPtWhListService;

/**
 * 부품창고 - List Action
 * 
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/mgrPtWhList" name="mgrPtWhListForm"
 *                input="/dream/mgr/ptwh/mgrPtWhList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhList" path="/dream/mgr/ptwh/mgrPtWhList.jsp"
 *                        redirect="false"
 */

public class MgrPtWhListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhListForm mgrPtWhListForm = (MgrPtWhListForm) form;
        
        switch (mgrPtWhListForm.getStrutsAction())
        {
            case MgrPtWhListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrPtWhListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrPtWhListAction.LIST_FIND:
                findList(request, response, mgrPtWhListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;                
            case MgrPtWhListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrPtWhListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrPtWhListForm mgrPtWhListForm) throws IOException
    {
        super.setHeader(request, response, mgrPtWhListForm.getListId(), mgrPtWhListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrPtWhListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrPtWhListForm mgrPtWhListForm, boolean excelExport) throws Exception
    {
    	MgrPtWhListService mgrPtWhListService = (MgrPtWhListService) getBean("mgrPtWhListService");
    	MgrPtWhCommonDTO mgrPtWhCommonDTO = mgrPtWhListForm.getMgrPtWhCommonDTO();

    	//Paging
    	mgrPtWhCommonDTO.setIsLoadMaxCount("Y".equals(mgrPtWhListForm.getIsLoadMaxCount())?true:false);
    	mgrPtWhCommonDTO.setFirstRow(mgrPtWhListForm.getFirstRow());
    	mgrPtWhCommonDTO.setOrderBy(mgrPtWhListForm.getOrderBy());
    	mgrPtWhCommonDTO.setDirection(mgrPtWhListForm.getDirection());
    	    	
        List resultList = mgrPtWhListService.findPtWhList(mgrPtWhCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrPtWhListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrPtWhListService.findTotalCount(mgrPtWhCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response, mgrPtWhListForm.getListId(),mgrPtWhListForm.getCurrentPageId(), mgrPtWhListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
}
