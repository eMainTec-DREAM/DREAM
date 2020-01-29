package dream.mgr.message.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.form.MgrMessageTransListForm;
import dream.mgr.message.service.MgrMessageTransListService;

/**
 * Message Transfer Page - List Action
 * 
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrMessageTransList" name="mgrMessageTransListForm"
 *                input="/dream/mgr/message/mgrMessageTransList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrMessageTransList" path="/dream/mgr/message/mgrMessageTransList.jsp"
 *                        redirect="false"
 */

public class MgrMessageTransListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrMessageTransListForm mgrMessageTransListForm = (MgrMessageTransListForm) form;
        
        switch (mgrMessageTransListForm.getStrutsAction())
        {
            case MgrMessageTransListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrMessageTransListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrMessageTransListAction.LIST_FIND:
                findList(request, response, mgrMessageTransListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrMessageTransListAction.LIST_DELETE:
            	deleteList(request, mgrMessageTransListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrMessageTransListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrMessageTransListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("mgrMessageTransList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrMessageTransListForm mgrMessageTransListForm) throws IOException
    {
        super.setHeader(request, response, mgrMessageTransListForm.getListId(), mgrMessageTransListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrMessageTransListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrMessageTransListForm mgrMessageTransListForm, boolean excelExport) throws Exception
    {
    	MgrMessageTransListService mgrMessageTransListService = (MgrMessageTransListService) getBean("mgrMessageTransListService");
    	MgrMessageTransCommonDTO mgrMessageTransCommonDTO = mgrMessageTransListForm.getMgrMessageTransCommonDTO();

    	//Paging
    	mgrMessageTransCommonDTO.setIsLoadMaxCount("Y".equals(mgrMessageTransListForm.getIsLoadMaxCount())?true:false);
    	mgrMessageTransCommonDTO.setFirstRow(mgrMessageTransListForm.getFirstRow());
    	mgrMessageTransCommonDTO.setOrderBy(mgrMessageTransListForm.getOrderBy());
    	mgrMessageTransCommonDTO.setDirection(mgrMessageTransListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrMessageTransListService.findList(mgrMessageTransCommonDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrMessageTransListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrMessageTransListService.findTotalCount(mgrMessageTransCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrMessageTransListForm.getListId(),mgrMessageTransListForm.getCurrentPageId(), mgrMessageTransListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrMessageTransListForm
     */
    private void deleteList(HttpServletRequest request, MgrMessageTransListForm mgrMessageTransListForm) throws Exception
    {
    	MgrMessageTransListService mgrMessageTransListService = (MgrMessageTransListService) getBean("mgrMessageTransListService");
        String[] deleteRows = mgrMessageTransListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrMessageTransListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
