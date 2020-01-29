package dream.work.close.check.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.form.MgrWorkCloseCheckListForm;
import dream.work.close.check.service.MgrWorkCloseCheckListService;

/**
 * MgrWorkCloseCheck Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrWorkCloseCheckList" name="mgrWorkCloseCheckListForm"
 *                input="/dream/work/close/check/mgrWorkCloseCheckList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkCloseCheckList" path="/dream/work/close/check/mgrWorkCloseCheckList.jsp"
 *                        redirect="false"
 */
public class MgrWorkCloseCheckListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       	= 1001;
    /** 삭제하기 */
    public static final int LIST_DELETE     	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrWorkCloseCheckListForm mgrWorkCloseCheckListForm = (MgrWorkCloseCheckListForm) form;

        super.updateAudit(mgrWorkCloseCheckListForm.getMgrWorkCloseCheckCommonDTO().getAuditKey()==""?mgrWorkCloseCheckListForm.getMgrWorkCloseCheckCommonDTO().getAuditKey():mgrWorkCloseCheckListForm.getMgrWorkCloseCheckCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrWorkCloseCheckListForm.getStrutsAction())
        {
            case MgrWorkCloseCheckListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrWorkCloseCheckListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrWorkCloseCheckListAction.LIST_FIND:
                findList(request, response, mgrWorkCloseCheckListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrWorkCloseCheckListAction.LIST_DELETE:
                deleteList(request, mgrWorkCloseCheckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrWorkCloseCheckListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrWorkCloseCheckListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckListForm mgrWorkCloseCheckListForm) throws IOException
    {
        super.setHeader(request, response, mgrWorkCloseCheckListForm.getListId(), mgrWorkCloseCheckListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrWorkCloseCheckListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckListForm mgrWorkCloseCheckListForm, boolean excelExport) throws Exception
    {
        MgrWorkCloseCheckListService mgrWorkCloseCheckListService = (MgrWorkCloseCheckListService) getBean("mgrWorkCloseCheckListService",request);
        MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckListForm.getMgrWorkCloseCheckCommonDTO();
      
        //Paging
        mgrWorkCloseCheckCommonDTO.setIsLoadMaxCount("Y".equals(mgrWorkCloseCheckListForm.getIsLoadMaxCount())?true:false);
        mgrWorkCloseCheckCommonDTO.setFirstRow(mgrWorkCloseCheckListForm.getFirstRow());
        mgrWorkCloseCheckCommonDTO.setOrderBy(mgrWorkCloseCheckListForm.getOrderBy());
        mgrWorkCloseCheckCommonDTO.setDirection(mgrWorkCloseCheckListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrWorkCloseCheckListService.findList(mgrWorkCloseCheckCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrWorkCloseCheckListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrWorkCloseCheckListService.findTotalCount(mgrWorkCloseCheckCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrWorkCloseCheckListForm.getListId(),mgrWorkCloseCheckListForm.getCurrentPageId(), mgrWorkCloseCheckListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrWorkCloseCheckListForm
     */
    private void deleteList(HttpServletRequest request, MgrWorkCloseCheckListForm mgrWorkCloseCheckListForm) throws Exception
    {
        MgrWorkCloseCheckListService mgrWorkCloseCheckListService = (MgrWorkCloseCheckListService) getBean("mgrWorkCloseCheckListService",request);
        String[] deleteRows = mgrWorkCloseCheckListForm.getDeleteRows();
        
        User user = getUser(request);
        mgrWorkCloseCheckListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
}