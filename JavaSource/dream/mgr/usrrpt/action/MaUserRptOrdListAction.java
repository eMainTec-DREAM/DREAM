package dream.mgr.usrrpt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.form.MaUserRptOrdListForm;
import dream.mgr.usrrpt.service.MaUserRptOrdListService;

/**
 * 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maUserRptOrdList" name="maUserRptOrdListForm"
 *                input="/dream/mgr/usrrpt/maUserRptOrdList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptOrdList" path="/dream/mgr/usrrpt/maUserRptOrdList.jsp"
 *                        redirect="false"
 */
public class MaUserRptOrdListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_ORD_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_ORD_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptOrdListForm maUserRptOrdListForm = (MaUserRptOrdListForm) form;
        
        switch (maUserRptOrdListForm.getStrutsAction())
        {
        
            case MaUserRptOrdListAction.USER_ORD_LIST_FIND:
                findList(request,response, maUserRptOrdListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptOrdListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maUserRptOrdListForm.getListId(), maUserRptOrdListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptOrdListAction.USER_ORD_LIST_DELETE:
            	deleteList(request,maUserRptOrdListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptOrdListAction.BASE_GRID_EXPORT:
            	findList(request,response, maUserRptOrdListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptOrdList");
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
     * @param maUserRptOrdListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaUserRptOrdListForm maUserRptOrdListForm) throws Exception
    {
        MaUserRptOrdListService maUserRptOrdListService = (MaUserRptOrdListService) getBean("maUserRptOrdListService");
        
        List resultList = maUserRptOrdListService.findList(maUserRptOrdListForm.getMaUserRptCommonDTO(), getUser(request));
        super.makeJsonResult(resultList, request, response, maUserRptOrdListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptOrdListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaUserRptOrdListForm maUserRptOrdListForm) throws Exception
    {
    	MaUserRptOrdListService maUserRptOrdListService = (MaUserRptOrdListService) getBean("maUserRptOrdListService");
        
    	String[] deleteRows = maUserRptOrdListForm.getDeleteRows();
    
    	maUserRptOrdListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}