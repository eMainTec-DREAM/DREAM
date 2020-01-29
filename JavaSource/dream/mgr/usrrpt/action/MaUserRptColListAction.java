package dream.mgr.usrrpt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.form.MaUserRptColListForm;
import dream.mgr.usrrpt.service.MaUserRptColListService;

/**
 * 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maUserRptColList" name="maUserRptColListForm"
 *                input="/dream/mgr/usrrpt/maUserRptColList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptColList" path="/dream/mgr/usrrpt/maUserRptColList.jsp"
 *                        redirect="false"
 */
public class MaUserRptColListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_COL_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_COL_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptColListForm maUserRptColListForm = (MaUserRptColListForm) form;
        
        switch (maUserRptColListForm.getStrutsAction())
        {
        
            case MaUserRptColListAction.USER_COL_LIST_FIND:
                findList(request,response, maUserRptColListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptColListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maUserRptColListForm.getListId(), maUserRptColListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptColListAction.USER_COL_LIST_DELETE:
            	deleteList(request,maUserRptColListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptColListAction.BASE_GRID_EXPORT:
            	findList(request,response, maUserRptColListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptColList");
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
     * @param maUserRptColListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaUserRptColListForm maUserRptColListForm) throws Exception
    {
        MaUserRptColListService maUserRptColListService = (MaUserRptColListService) getBean("maUserRptColListService");
        
        List resultList = maUserRptColListService.findList(maUserRptColListForm.getMaUserRptCommonDTO(), getUser(request));
        super.makeJsonResult(resultList, request, response, maUserRptColListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptColListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaUserRptColListForm maUserRptColListForm) throws Exception
    {
    	MaUserRptColListService maUserRptColListService = (MaUserRptColListService) getBean("maUserRptColListService");
        
    	String[] deleteRows = maUserRptColListForm.getDeleteRows();
    
    	maUserRptColListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}