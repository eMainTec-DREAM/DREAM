package dream.mgr.usrrpt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.form.MaUserRptTableListForm;
import dream.mgr.usrrpt.service.MaUserRptTableListService;

/**
 * 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maUserRptTableList" name="maUserRptTableListForm"
 *                input="/dream/mgr/usrrpt/maUserRptTableList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptTableList" path="/dream/mgr/usrrpt/maUserRptTableList.jsp"
 *                        redirect="false"
 */
public class MaUserRptTableListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_TABLE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_TABLE_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptTableListForm maUserRptTableListForm = (MaUserRptTableListForm) form;
        
        switch (maUserRptTableListForm.getStrutsAction())
        {
        
            case MaUserRptTableListAction.USER_TABLE_LIST_FIND:
                findList(request,response, maUserRptTableListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptTableListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maUserRptTableListForm.getListId(), maUserRptTableListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptTableListAction.USER_TABLE_LIST_DELETE:
            	deleteList(request,maUserRptTableListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptTableListAction.BASE_GRID_EXPORT:
            	findList(request,response, maUserRptTableListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptTableList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptTableListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaUserRptTableListForm maUserRptTableListForm) throws Exception
    {
        MaUserRptTableListService maUserRptTableListService = (MaUserRptTableListService) getBean("maUserRptTableListService");
        
        List resultList = maUserRptTableListService.findList(maUserRptTableListForm.getMaUserRptCommonDTO(), getUser(request));
        super.makeJsonResult(resultList, request, response, maUserRptTableListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptTableListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaUserRptTableListForm maUserRptTableListForm) throws Exception
    {
    	MaUserRptTableListService maUserRptTableListService = (MaUserRptTableListService) getBean("maUserRptTableListService");
        
    	String[] deleteRows = maUserRptTableListForm.getDeleteRows();
    
    	maUserRptTableListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}