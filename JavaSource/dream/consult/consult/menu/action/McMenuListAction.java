package dream.consult.consult.menu.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.consult.menu.dto.McMenuCommonDTO;
import dream.consult.consult.menu.form.McMenuListForm;
import dream.consult.consult.menu.service.McMenuListService;

/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: McMenuListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/mcMenuList" name="mcMenuListForm"
 *                input="/dream/consult/consult/menu/mcMenuList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcMenuList" path="/dream/consult/consult/menu/mcMenuList.jsp"
 *                        redirect="false"
 */
public class McMenuListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int MENU_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int MENU_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        McMenuListForm mcMenuListForm = (McMenuListForm) form;
        
        switch (mcMenuListForm.getStrutsAction())
        {
            case McMenuListAction.MENU_LIST_FIND:
                findMenuList(request, mcMenuListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McMenuListAction.BASE_SET_HEADER:
                setHeader(request, response, mcMenuListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McMenuListAction.MENU_LIST_DELETE:
            	deleteMenu(request, mcMenuListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case McMenuListAction.BASE_GRID_EXPORT:
            	findMenuList(request, mcMenuListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mcMenuList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, McMenuListForm mcMenuListForm) throws IOException
    {
        super.setHeader(request, response, mcMenuListForm.getListId(),mcMenuListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: McMenuListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcMenuListForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, McMenuListForm mcMenuListForm, HttpServletResponse response) throws IOException
    {
    	McMenuListService mcMenuListService = (McMenuListService) getBean("mcMenuListService");        

    	McMenuCommonDTO mcMenuCommonDTO = mcMenuListForm.getMcMenuCommonDTO();

        //리스트를 조회한다.
        List resultList = mcMenuListService.findMenuList(mcMenuCommonDTO,getUser(request));

        super.makeJsonResult(resultList, request, response, mcMenuListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: McMenuListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteMenu(HttpServletRequest request, McMenuListForm mcMenuListForm) throws Exception
    {
    	McMenuListService mcMenuListService = (McMenuListService) getBean("mcMenuListService");        

    	String[] deleteRows = mcMenuListForm.getDeleteRows();
        
        mcMenuListService.deleteMenu(deleteRows);
        
        setAjaxStatus(request);
    }
}
