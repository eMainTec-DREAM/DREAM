package dream.mgr.usrrpt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.form.MaUserRptParamListForm;
import dream.mgr.usrrpt.service.MaUserRptParamListService;

/**
 * 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maUserRptParamList" name="maUserRptParamListForm"
 *                input="/dream/mgr/usrrpt/maUserRptParamList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptParamList" path="/dream/mgr/usrrpt/maUserRptParamList.jsp"
 *                        redirect="false"
 */
public class MaUserRptParamListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_PARAM_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_PARAM_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptParamListForm maUserRptParamListForm = (MaUserRptParamListForm) form;
        
        switch (maUserRptParamListForm.getStrutsAction())
        {
        
            case MaUserRptParamListAction.USER_PARAM_LIST_FIND:
                findList(request,response, maUserRptParamListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptParamListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maUserRptParamListForm.getListId(), maUserRptParamListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptParamListAction.USER_PARAM_LIST_DELETE:
            	deleteList(request,maUserRptParamListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptParamListAction.BASE_GRID_EXPORT:
            	findList(request,response, maUserRptParamListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptParamList");
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
     * @param maUserRptParamListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaUserRptParamListForm maUserRptParamListForm) throws Exception
    {
        MaUserRptParamListService maUserRptParamListService = (MaUserRptParamListService) getBean("maUserRptParamListService");
        
        List resultList = maUserRptParamListService.findList(maUserRptParamListForm.getMaUserRptCommonDTO(), getUser(request));
        super.makeJsonResult(resultList, request, response, maUserRptParamListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptParamListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaUserRptParamListForm maUserRptParamListForm) throws Exception
    {
    	MaUserRptParamListService maUserRptParamListService = (MaUserRptParamListService) getBean("maUserRptParamListService");
        
    	String[] deleteRows = maUserRptParamListForm.getDeleteRows();
    
    	maUserRptParamListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}