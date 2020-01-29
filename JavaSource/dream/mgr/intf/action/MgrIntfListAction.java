package dream.mgr.intf.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.form.MgrIntfListForm;
import dream.mgr.intf.service.MgrIntfListService;

/**
 * Interface Page - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrIntfList" name="mgrIntfListForm"
 *                input="/dream/mgr/intf/mgrIntfList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrIntfList" path="/dream/mgr/intf/mgrIntfList.jsp"
 *                        redirect="false"
 */

public class MgrIntfListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrIntfListForm mgrIntfListForm = (MgrIntfListForm) form;
        
        switch (mgrIntfListForm.getStrutsAction())
        {
            case MgrIntfListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrIntfListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrIntfListAction.LIST_FIND:
                findList(request, response, mgrIntfListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrIntfListAction.LIST_DELETE:
            	deleteList(request, mgrIntfListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrIntfListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrIntfListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("mgrIntfList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrIntfListForm mgrIntfListForm) throws IOException
    {
        super.setHeader(request, response, mgrIntfListForm.getListId(), mgrIntfListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrIntfListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrIntfListForm mgrIntfListForm, boolean excelExport) throws Exception
    {
    	MgrIntfListService mgrIntfListService = (MgrIntfListService) getBean("mgrIntfListService");
    	MgrIntfCommonDTO mgrIntfCommonDTO = mgrIntfListForm.getMgrIntfCommonDTO();

    	//Paging
    	mgrIntfCommonDTO.setIsLoadMaxCount("Y".equals(mgrIntfListForm.getIsLoadMaxCount())?true:false);
    	mgrIntfCommonDTO.setFirstRow(mgrIntfListForm.getFirstRow());
    	mgrIntfCommonDTO.setOrderBy(mgrIntfListForm.getOrderBy());
    	mgrIntfCommonDTO.setDirection(mgrIntfListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrIntfListService.findList(mgrIntfCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrIntfListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrIntfListService.findTotalCount(mgrIntfCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrIntfListForm.getListId(),mgrIntfListForm.getCurrentPageId(), mgrIntfListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrIntfListForm
     */
    private void deleteList(HttpServletRequest request, MgrIntfListForm mgrIntfListForm) throws Exception
    {
    	MgrIntfListService mgrIntfListService = (MgrIntfListService) getBean("mgrIntfListService");
        String[] deleteRows = mgrIntfListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrIntfListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
