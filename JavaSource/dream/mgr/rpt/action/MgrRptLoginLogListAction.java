package dream.mgr.rpt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.form.MgrRptLoginLogListForm;
import dream.mgr.rpt.service.MgrRptLoginLogListService;

/**
 * 로그인 로그 Page - List Action
 * 
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrRptLoginLogList" name="mgrRptLoginLogListForm"
 *                input="/dream/mgr/rpt/mgrRptLoginLogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrRptLoginLogList" path="/dream/mgr/rpt/mgrRptLoginLogList.jsp"
 *                        redirect="false"
 */
public class MgrRptLoginLogListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrRptLoginLogListForm mgrRptLoginLogListForm = (MgrRptLoginLogListForm) form;
        
        switch (mgrRptLoginLogListForm.getStrutsAction())
        {
            case MgrRptLoginLogListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrRptLoginLogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrRptLoginLogListAction.LIST_FIND:
                findList(request, response, mgrRptLoginLogListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;         
            case MgrRptLoginLogListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrRptLoginLogListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrRptLoginLogListForm mgrRptLoginLogListForm) throws IOException
    {
        super.setHeader(request, response, mgrRptLoginLogListForm.getListId(), mgrRptLoginLogListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrRptLoginLogListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrRptLoginLogListForm mgrRptLoginLogListForm, boolean excelExport) throws Exception
    {
        MgrRptLoginLogListService mgrRptLoginLogListService = (MgrRptLoginLogListService) getBean("mgrRptLoginLogListService",request);
        MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO = mgrRptLoginLogListForm.getMgrRptLoginLogCommonDTO();
      
        //Paging
        mgrRptLoginLogCommonDTO.setIsLoadMaxCount("Y".equals(mgrRptLoginLogListForm.getIsLoadMaxCount())?true:false);
        mgrRptLoginLogCommonDTO.setFirstRow(mgrRptLoginLogListForm.getFirstRow());
        mgrRptLoginLogCommonDTO.setOrderBy(mgrRptLoginLogListForm.getOrderBy());
        mgrRptLoginLogCommonDTO.setDirection(mgrRptLoginLogListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrRptLoginLogListService.findList(mgrRptLoginLogCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrRptLoginLogListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrRptLoginLogListService.findTotalCount(mgrRptLoginLogCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrRptLoginLogListForm.getListId(),mgrRptLoginLogListForm.getCurrentPageId(), mgrRptLoginLogListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
 
}