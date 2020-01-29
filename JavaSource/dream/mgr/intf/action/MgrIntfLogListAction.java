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
import dream.mgr.intf.dto.MgrIntfLogListDTO;
import dream.mgr.intf.form.MgrIntfLogListForm;
import dream.mgr.intf.service.MgrIntfLogListService;

/**
 * Interface Log Page - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrIntfLogList" name="mgrIntfLogListForm"
 *                input="/dream/mgr/intf/mgrIntfLogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrIntfLogList" path="/dream/mgr/intf/mgrIntfLogList.jsp"
 *                        redirect="false"
 */

public class MgrIntfLogListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrIntfLogListForm mgrIntfLogListForm = (MgrIntfLogListForm) form;
        
        switch (mgrIntfLogListForm.getStrutsAction())
        {
            case MgrIntfLogListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrIntfLogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrIntfLogListAction.LIST_FIND:
                findList(request, response, mgrIntfLogListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrIntfLogListAction.LIST_DELETE:
            	deleteList(request, mgrIntfLogListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrIntfLogListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrIntfLogListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("mgrIntfLogList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrIntfLogListForm mgrIntfLogListForm) throws IOException
    {
        super.setHeader(request, response, mgrIntfLogListForm.getListId(), mgrIntfLogListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrIntfLogListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrIntfLogListForm mgrIntfLogListForm, boolean excelExport) throws Exception
    {
    	MgrIntfLogListService mgrIntfLogListService = (MgrIntfLogListService) getBean("mgrIntfLogListService");
    	MgrIntfCommonDTO mgrIntfCommonDTO = mgrIntfLogListForm.getMgrIntfCommonDTO();
    	MgrIntfLogListDTO mgrIntfLogListDTO = mgrIntfLogListForm.getMgrIntfLogListDTO();

    	//Paging
    	mgrIntfLogListDTO.setIsLoadMaxCount("Y".equals(mgrIntfLogListForm.getIsLoadMaxCount())?true:false);
    	mgrIntfLogListDTO.setFirstRow(mgrIntfLogListForm.getFirstRow());
    	mgrIntfLogListDTO.setOrderBy(mgrIntfLogListForm.getOrderBy());
    	mgrIntfLogListDTO.setDirection(mgrIntfLogListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrIntfLogListService.findList(mgrIntfCommonDTO, mgrIntfLogListDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrIntfLogListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrIntfLogListService.findTotalCount(mgrIntfCommonDTO, mgrIntfLogListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrIntfLogListForm.getListId(),mgrIntfLogListForm.getCurrentPageId(), mgrIntfLogListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrIntfLogListForm
     */
    private void deleteList(HttpServletRequest request, MgrIntfLogListForm mgrIntfLogListForm) throws Exception
    {
    	MgrIntfLogListService mgrIntfLogListService = (MgrIntfLogListService) getBean("mgrIntfLogListService");
        String[] deleteRows = mgrIntfLogListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrIntfLogListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
