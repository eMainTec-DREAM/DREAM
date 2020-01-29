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
import dream.pers.priv.info.form.MaLinkMenuListForm;
import dream.pers.priv.info.service.MaLinkMenuListService;

/**
 * 목록
 * @author  jung7126
 * @version $Id: MaLinkMenuListAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maLinkMenuList" name="maLinkMenuListForm"
 *                input="/dream/pers/priv/info/maLinkMenuList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLinkMenuList" path="/dream/pers/priv/info/maLinkMenuList.jsp"
 *                        redirect="false"
 */
public class MaLinkMenuListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LINK_MENU_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LINK_MENU_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLinkMenuListForm maLinkMenuListForm = (MaLinkMenuListForm) form;
        
        switch (maLinkMenuListForm.getStrutsAction())
        {
        
            case MaLinkMenuListAction.LINK_MENU_LIST_FIND:
                findList(request,response, maLinkMenuListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLinkMenuListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maLinkMenuListForm.getListId(), maLinkMenuListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLinkMenuListAction.LINK_MENU_LIST_DELETE:
            	deleteList(request,maLinkMenuListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maLinkMenuList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaLinkMenuListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLinkMenuListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaLinkMenuListForm maLinkMenuListForm) throws Exception
    {
        MaLinkMenuListService maLinkMenuListService = (MaLinkMenuListService) getBean("maLinkMenuListService");
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = maLinkMenuListForm.getMaMyInfoCommonDTO();
        
        List resultList = maLinkMenuListService.findList(maMyInfoCommonDTO, getUser(request));
        super.makeJsonResult(resultList, request, response, maLinkMenuListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaLinkMenuListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLinkMenuListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaLinkMenuListForm maLinkMenuListForm) throws Exception
    {
    	MaLinkMenuListService maLinkMenuListService = (MaLinkMenuListService) getBean("maLinkMenuListService");
        
    	String[] deleteRows = maLinkMenuListForm.getDeleteRows();
    
    	maLinkMenuListService.deleteList(deleteRows);
    	
    	setAjaxStatus(request);
    }
}