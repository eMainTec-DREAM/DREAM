package dream.mgr.msgrec.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.form.MgrMsgRecListForm;
import dream.mgr.msgrec.service.MgrMsgRecListService;

/**
 * 메시지 수신설정 Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrMsgRecList" name="mgrMsgRecListForm"
 *                input="/dream/mgr/msgrec/mgrMsgRecList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrMsgRecList" path="/dream/mgr/msgrec/mgrMsgRecList.jsp"
 *                        redirect="false"
 */
public class MgrMsgRecListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       = 1001;
    /** 삭제하기 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrMsgRecListForm mgrMsgRecListForm = (MgrMsgRecListForm) form;
        
        switch (mgrMsgRecListForm.getStrutsAction())
        {
            case MgrMsgRecListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrMsgRecListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrMsgRecListAction.LIST_FIND:
                findList(request, response, mgrMsgRecListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;         
            case MgrMsgRecListAction.LIST_DELETE:
                deleteList(request, mgrMsgRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;         
            case MgrMsgRecListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrMsgRecListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrMsgRecListForm mgrMsgRecListForm) throws IOException
    {
        super.setHeader(request, response, mgrMsgRecListForm.getListId(), mgrMsgRecListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrMsgRecListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrMsgRecListForm mgrMsgRecListForm, boolean excelExport) throws Exception
    {
        MgrMsgRecListService mgrMsgRecListService = (MgrMsgRecListService) getBean("mgrMsgRecListService",request);
        MgrMsgRecCommonDTO mgrMsgRecCommonDTO = mgrMsgRecListForm.getMgrMsgRecCommonDTO();
      
        //Paging
        mgrMsgRecCommonDTO.setIsLoadMaxCount("Y".equals(mgrMsgRecListForm.getIsLoadMaxCount())?true:false);
        mgrMsgRecCommonDTO.setFirstRow(mgrMsgRecListForm.getFirstRow());
        mgrMsgRecCommonDTO.setOrderBy(mgrMsgRecListForm.getOrderBy());
        mgrMsgRecCommonDTO.setDirection(mgrMsgRecListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrMsgRecListService.findList(mgrMsgRecCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrMsgRecListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrMsgRecListService.findTotalCount(mgrMsgRecCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrMsgRecListForm.getListId(),mgrMsgRecListForm.getCurrentPageId(), mgrMsgRecListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrMsgRecListForm
     */
    
    private void deleteList(HttpServletRequest request, MgrMsgRecListForm mgrMsgRecListForm) throws Exception
    {
    	MgrMsgRecListService mgrMsgRecListService = (MgrMsgRecListService) getBean("mgrMsgRecListService",request);
    	String[] deleteRows = mgrMsgRecListForm.getDeleteRows();
        
        User user = getUser(request);
        mgrMsgRecListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
 
}