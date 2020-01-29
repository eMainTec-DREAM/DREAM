package dream.mgr.tobeprocess.action;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.tobeprocess.dto.MgrToBeProcessCommonDTO;
import dream.mgr.tobeprocess.form.MgrToBeProcessListForm;
import dream.mgr.tobeprocess.service.MgrToBeProcessListService;

/**
 * ToBeProcess Page - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrToBeProcessList" name="mgrToBeProcessListForm"
 *                input="/dream/mgr/tobeprocess/mgrToBeProcessList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrToBeProcessList" path="/dream/mgr/tobeprocess/mgrToBeProcessList.jsp"
 *                        redirect="false"
 */

public class MgrToBeProcessListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrToBeProcessListForm mgrToBeProcessListForm = (MgrToBeProcessListForm) form;
        
        switch (mgrToBeProcessListForm.getStrutsAction())
        {
            case MgrToBeProcessListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrToBeProcessListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrToBeProcessListAction.LIST_FIND:
                findList(request, response, mgrToBeProcessListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrToBeProcessListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrToBeProcessListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrToBeProcessList");
                break;
        }
        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrToBeProcessListForm mgrToBeProcessListForm) throws IOException
    {
        super.setHeader(request, response, mgrToBeProcessListForm.getListId(), mgrToBeProcessListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrToBeProcessListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrToBeProcessListForm mgrToBeProcessListForm, boolean excelExport) throws Exception
    {
    	MgrToBeProcessListService mgrToBeProcessListService = (MgrToBeProcessListService) getBean("mgrToBeProcessListService");
    	MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO = mgrToBeProcessListForm.getMgrToBeProcessCommonDTO();

    	//Paging
    	mgrToBeProcessCommonDTO.setIsLoadMaxCount("Y".equals(mgrToBeProcessListForm.getIsLoadMaxCount())?true:false);
    	mgrToBeProcessCommonDTO.setFirstRow(mgrToBeProcessListForm.getFirstRow());
    	mgrToBeProcessCommonDTO.setOrderBy(mgrToBeProcessListForm.getOrderBy());
    	mgrToBeProcessCommonDTO.setDirection(mgrToBeProcessListForm.getDirection());
    	
    	User user = getUser(request);
    	mgrToBeProcessCommonDTO.setCompNo(user.getCompNo());
    	
        List resultList = mgrToBeProcessListService.findToBeProcessList(mgrToBeProcessCommonDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrToBeProcessListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrToBeProcessListService.findTotalCount(mgrToBeProcessCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrToBeProcessListForm.getListId(),mgrToBeProcessListForm.getCurrentPageId(), mgrToBeProcessListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
