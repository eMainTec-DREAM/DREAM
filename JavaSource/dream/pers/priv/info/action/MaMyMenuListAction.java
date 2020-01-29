package dream.pers.priv.info.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.form.MaMyMenuListForm;
import dream.pers.priv.info.service.MaMyMenuListService;

/**
 * 칼럼 목록
 * @author  jung7126
 * @version $Id: MaMyMenuListAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maMyMenuList" name="maMyMenuListForm"
 *                input="/dream/pers/priv/info/maMyMenuList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMyMenuList" path="/dream/pers/priv/info/maMyMenuList.jsp"
 *                        redirect="false"
 */
public class MaMyMenuListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MY_MENU_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int MY_MENU_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMyMenuListForm maMyMenuListForm = (MaMyMenuListForm) form;
        
        switch (maMyMenuListForm.getStrutsAction())
        {
        
            case MaMyMenuListAction.MY_MENU_LIST_FIND:
                findList(request,response, maMyMenuListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMyMenuListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maMyMenuListForm.getListId(), maMyMenuListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMyMenuListAction.MY_MENU_LIST_DELETE:
            	deleteList(request,maMyMenuListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maMyMenuList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaMyMenuListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMyMenuListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaMyMenuListForm maMyMenuListForm) throws Exception
    {
        MaMyMenuListService maMyMenuListService = (MaMyMenuListService) getBean("maMyMenuListService");
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = maMyMenuListForm.getMaMyInfoCommonDTO();
        
        List resultList = maMyMenuListService.findList(maMyInfoCommonDTO, getUser(request));
        super.makeJsonResult(resultList, request, response, maMyMenuListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaMyMenuListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMyMenuListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaMyMenuListForm maMyMenuListForm) throws Exception
    {
    	MaMyMenuListService maMyMenuListService = (MaMyMenuListService) getBean("maMyMenuListService");
        
    	String[] deleteRows = maMyMenuListForm.getDeleteRows();
    
    	maMyMenuListService.deleteList(deleteRows);
    	
    	setAjaxStatus(request);
    }
}