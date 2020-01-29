package dream.mgr.rpt.logintrylog.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.rpt.logintrylog.dto.MgrRptLoginTryLogCommonDTO;
import dream.mgr.rpt.logintrylog.form.MgrRptLoginTryLogListForm;
import dream.mgr.rpt.logintrylog.service.MgrRptLoginTryLogListService;

/**
 * 로그인 시도 로그 Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrRptLoginTryLogList" name="mgrRptLoginTryLogListForm"
 *                input="/dream/mgr/rpt/logintrylog/mgrRptLoginTryLogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrRptLoginTryLogList" path="/dream/mgr/rpt/logintrylog/mgrRptLoginTryLogList.jsp"
 *                        redirect="false"
 */
public class MgrRptLoginTryLogListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrRptLoginTryLogListForm mgrRptLoginTryLogListForm = (MgrRptLoginTryLogListForm) form;
        
        switch (mgrRptLoginTryLogListForm.getStrutsAction())
        {
            case MgrRptLoginTryLogListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrRptLoginTryLogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrRptLoginTryLogListAction.LIST_FIND:
                findList(request, response, mgrRptLoginTryLogListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;         
            case MgrRptLoginTryLogListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrRptLoginTryLogListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrRptLoginTryLogListForm mgrRptLoginTryLogListForm) throws IOException
    {
        super.setHeader(request, response, mgrRptLoginTryLogListForm.getListId(), mgrRptLoginTryLogListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrRptLoginTryLogListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrRptLoginTryLogListForm mgrRptLoginTryLogListForm, boolean excelExport) throws Exception
    {
        MgrRptLoginTryLogListService mgrRptLoginTryLogListService = (MgrRptLoginTryLogListService) getBean("mgrRptLoginTryLogListService",request);
        MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO = mgrRptLoginTryLogListForm.getMgrRptLoginTryLogCommonDTO();
      
        //Paging
        mgrRptLoginTryLogCommonDTO.setIsLoadMaxCount("Y".equals(mgrRptLoginTryLogListForm.getIsLoadMaxCount())?true:false);
        mgrRptLoginTryLogCommonDTO.setFirstRow(mgrRptLoginTryLogListForm.getFirstRow());
        mgrRptLoginTryLogCommonDTO.setOrderBy(mgrRptLoginTryLogListForm.getOrderBy());
        mgrRptLoginTryLogCommonDTO.setDirection(mgrRptLoginTryLogListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrRptLoginTryLogListService.findList(mgrRptLoginTryLogCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrRptLoginTryLogListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrRptLoginTryLogListService.findTotalCount(mgrRptLoginTryLogCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrRptLoginTryLogListForm.getListId(),mgrRptLoginTryLogListForm.getCurrentPageId(), mgrRptLoginTryLogListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
 
}