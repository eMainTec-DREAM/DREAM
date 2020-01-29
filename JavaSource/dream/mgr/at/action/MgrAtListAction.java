package dream.mgr.at.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.form.MgrAtListForm;
import dream.mgr.at.service.MgrAtListService;

/**
 * Audit Trail  Action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrAtList" name="mgrAtListForm"
 *                input="/dream/mgr/at/mgrAtList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrAtList" path="/dream/mgr/at/mgrAtList.jsp"
 *                        redirect="false"
 */
public class MgrAtListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrAtListForm mgrAtListForm = (MgrAtListForm) form;
        
        switch (mgrAtListForm.getStrutsAction())
        {
            case MgrAtListAction.LIST_FIND:
            	findList(request, mgrAtListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrAtListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrAtListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrAtListAction.BASE_GRID_EXPORT:
            	findList(request, mgrAtListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrAtListForm mgrAtListForm) throws IOException
    {
        super.setHeader(request, response, mgrAtListForm.getListId(),mgrAtListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrAtListForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrAtListForm mgrAtListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrAtListService mgrAtListService = (MgrAtListService) getBean("mgrAtListService");        

    	MgrAtCommonDTO mgrAtCommonDTO = mgrAtListForm.getMgrAtCommonDTO();
    	
    	//Paging
        mgrAtCommonDTO.setIsLoadMaxCount("Y".equals(mgrAtListForm.getIsLoadMaxCount())?true:false);
        mgrAtCommonDTO.setFirstRow(mgrAtListForm.getFirstRow());
        mgrAtCommonDTO.setOrderBy(mgrAtListForm.getOrderBy());
        mgrAtCommonDTO.setDirection(mgrAtListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = mgrAtListService.findList(mgrAtCommonDTO, user);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrAtListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrAtListService.findTotalCount(mgrAtCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrAtListForm.getListId(),mgrAtListForm.getCurrentPageId(), mgrAtListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
}
