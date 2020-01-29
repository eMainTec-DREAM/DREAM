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
import dream.mgr.at.dto.MgrAtHistListDTO;
import dream.mgr.at.form.MgrAtHistListForm;
import dream.mgr.at.service.MgrAtHistListService;

/**
 * Audit Trail Hist Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrAtHistList" name="mgrAtHistListForm"
 *                input="/dream/mgr/at/mgrAtHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrAtHistList" path="/dream/mgr/at/mgrAtHistList.jsp"
 *                        redirect="false"
 */
public class MgrAtHistListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrAtHistListForm mgrAtHistListForm = (MgrAtHistListForm) form;
        
        switch (mgrAtHistListForm.getStrutsAction())
        {
            case MgrAtHistListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrAtHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrAtHistListAction.LIST_FIND:
                findList(request, response, mgrAtHistListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrAtHistListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrAtHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrAtHistListForm mgrAtHistListForm) throws IOException
    {
        super.setHeader(request, response, mgrAtHistListForm.getListId(), mgrAtHistListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrAtHistListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrAtHistListForm mgrAtHistListForm, boolean excelExport) throws Exception
    {
        MgrAtHistListService mgrAtHistListService = (MgrAtHistListService) getBean("mgrAtHistListService");
        MgrAtHistListDTO mgrAtHistListDTO = mgrAtHistListForm.getMgrAtHistListDTO();
        MgrAtCommonDTO mgrAtCommonDTO = mgrAtHistListForm.getMgrAtCommonDTO();
      
        //Paging
        mgrAtCommonDTO.setIsLoadMaxCount("Y".equals(mgrAtHistListForm.getIsLoadMaxCount())?true:false);
        mgrAtCommonDTO.setFirstRow(mgrAtHistListForm.getFirstRow());
        mgrAtCommonDTO.setOrderBy(mgrAtHistListForm.getOrderBy());
        mgrAtCommonDTO.setDirection(mgrAtHistListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrAtHistListService.findList(mgrAtCommonDTO, mgrAtHistListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrAtHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrAtHistListService.findTotalCount(mgrAtCommonDTO,mgrAtHistListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrAtHistListForm.getListId(),mgrAtHistListForm.getCurrentPageId(), mgrAtHistListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}