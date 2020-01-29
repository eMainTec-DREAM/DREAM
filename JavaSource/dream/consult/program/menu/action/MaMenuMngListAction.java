package dream.consult.program.menu.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.JsonUtil;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;
import dream.consult.program.menu.form.MaMenuMngListForm;
import dream.consult.program.menu.service.MaMenuMngListService;

/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: MaMenuMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maMenuMngList" name="maMenuMngListForm"
 *                input="/dream/consult/program/menu/maMenuMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMenuMngList" path="/dream/consult/program/menu/maMenuMngList.jsp"
 *                        redirect="false"
 */
public class MaMenuMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int MENU_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int MENU_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaMenuMngListForm maMenuMngListForm = (MaMenuMngListForm) form;
        
        switch (maMenuMngListForm.getStrutsAction())
        {
            case MaMenuMngListAction.MENU_LIST_FIND:
                findMenuList(request, maMenuMngListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMenuMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maMenuMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaMenuMngListAction.MENU_LIST_DELETE:
            	deleteMenu(request, maMenuMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaMenuMngListAction.BASE_GRID_EXPORT:
            	findMenuList(request, maMenuMngListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maMenuMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaMenuMngListForm maMenuMngListForm) throws IOException
    {
        super.setHeader(request, response, maMenuMngListForm.getListId(),maMenuMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaMenuMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMenuMngListForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, MaMenuMngListForm maMenuMngListForm, HttpServletResponse response) throws IOException
    {
    	MaMenuMngListService maMenuMngListService = (MaMenuMngListService) getBean("maMenuMngListService");        

    	MaMenuMngCommonDTO maMenuMngCommonDTO = maMenuMngListForm.getMaMenuMngCommonDTO();

        //리스트를 조회한다.
        List resultList = JsonUtil.convertorTreeMap(maMenuMngListService.findMenuList(maMenuMngCommonDTO,getUser(request)), "0", "MENUID", "PMENUID", "ORDNO");

        super.makeTreeJsonResult(resultList, request, response, maMenuMngListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaMenuMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteMenu(HttpServletRequest request, MaMenuMngListForm maMenuMngListForm) throws Exception
    {
    	MaMenuMngListService maMenuMngListService = (MaMenuMngListService) getBean("maMenuMngListService");        

    	String[] deleteRows = maMenuMngListForm.getDeleteRows();
        
        maMenuMngListService.deleteMenu(deleteRows);
        
        setAjaxStatus(request);
    }
}
