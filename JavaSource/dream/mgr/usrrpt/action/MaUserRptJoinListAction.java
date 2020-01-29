package dream.mgr.usrrpt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.form.MaUserRptJoinListForm;
import dream.mgr.usrrpt.service.MaUserRptJoinListService;

/**
 * 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maUserRptJoinList" name="maUserRptJoinListForm"
 *                input="/dream/mgr/usrrpt/maUserRptJoinList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptJoinList" path="/dream/mgr/usrrpt/maUserRptJoinList.jsp"
 *                        redirect="false"
 */
public class MaUserRptJoinListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_JOIN_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_JOIN_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptJoinListForm maUserRptJoinListForm = (MaUserRptJoinListForm) form;
        
        switch (maUserRptJoinListForm.getStrutsAction())
        {
        
            case MaUserRptJoinListAction.USER_JOIN_LIST_FIND:
                findList(request,response, maUserRptJoinListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptJoinListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maUserRptJoinListForm.getListId(), maUserRptJoinListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptJoinListAction.USER_JOIN_LIST_DELETE:
            	deleteList(request,maUserRptJoinListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptJoinListAction.BASE_GRID_EXPORT:
            	findList(request,response, maUserRptJoinListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptJoinList");
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
     * @param maUserRptJoinListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaUserRptJoinListForm maUserRptJoinListForm) throws Exception
    {
        MaUserRptJoinListService maUserRptJoinListService = (MaUserRptJoinListService) getBean("maUserRptJoinListService");
        
        List resultList = maUserRptJoinListService.findList(maUserRptJoinListForm.getMaUserRptCommonDTO(), getUser(request));
        super.makeJsonResult(resultList, request, response, maUserRptJoinListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptJoinListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaUserRptJoinListForm maUserRptJoinListForm) throws Exception
    {
    	MaUserRptJoinListService maUserRptJoinListService = (MaUserRptJoinListService) getBean("maUserRptJoinListService");
        
    	String[] deleteRows = maUserRptJoinListForm.getDeleteRows();
    
    	maUserRptJoinListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}