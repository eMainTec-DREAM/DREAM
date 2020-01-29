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
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;
import dream.mgr.rpt.form.MgrRptScrnLogListForm;
import dream.mgr.rpt.service.MgrRptScrnLogListService;

/**
 * 화면접속 로그 Page - List Action
 * 
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrRptScrnLogList" name="mgrRptScrnLogListForm"
 *                input="/dream/mgr/rpt/mgrRptScrnLogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrRptScrnLogList" path="/dream/mgr/rpt/mgrRptScrnLogList.jsp"
 *                        redirect="false"
 */
public class MgrRptScrnLogListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrRptScrnLogListForm mgrRptScrnLogListForm = (MgrRptScrnLogListForm) form;
        
        switch (mgrRptScrnLogListForm.getStrutsAction())
        {
            case MgrRptScrnLogListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrRptScrnLogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrRptScrnLogListAction.LIST_FIND:
                findList(request, response, mgrRptScrnLogListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;         
            case MgrRptScrnLogListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrRptScrnLogListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrRptScrnLogListForm mgrRptScrnLogListForm) throws IOException
    {
        super.setHeader(request, response, mgrRptScrnLogListForm.getListId(), mgrRptScrnLogListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrRptScrnLogListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrRptScrnLogListForm mgrRptScrnLogListForm, boolean excelExport) throws Exception
    {
        MgrRptScrnLogListService mgrRptScrnLogListService = (MgrRptScrnLogListService) getBean("mgrRptScrnLogListService",request);
        MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO = mgrRptScrnLogListForm.getMgrRptScrnLogCommonDTO();
        MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO = mgrRptScrnLogListForm.getMgrRptLoginLogCommonDTO();
        
        //Paging
        mgrRptScrnLogCommonDTO.setIsLoadMaxCount("Y".equals(mgrRptScrnLogListForm.getIsLoadMaxCount())?true:false);
        mgrRptScrnLogCommonDTO.setFirstRow(mgrRptScrnLogListForm.getFirstRow());
        mgrRptScrnLogCommonDTO.setOrderBy(mgrRptScrnLogListForm.getOrderBy());
        mgrRptScrnLogCommonDTO.setDirection(mgrRptScrnLogListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrRptScrnLogListService.findList(mgrRptScrnLogCommonDTO, mgrRptLoginLogCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrRptScrnLogListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrRptScrnLogListService.findTotalCount(mgrRptScrnLogCommonDTO, mgrRptLoginLogCommonDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrRptScrnLogListForm.getListId(),mgrRptScrnLogListForm.getCurrentPageId(), mgrRptScrnLogListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
 
}