package gaia.gapg.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.GaiaAuthAction;
import gaia.gapg.dto.GaPgMngCommonDTO;
import gaia.gapg.form.GaPgMngListForm;
import gaia.gapg.service.GaPgMngListService;

/**
 * 화면 - 목록 action
 * @author  kim21017
 * @version $Id: GaPgMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/gaPgMngList" name="gaPgMngListForm"
 *                input="/gaia/gapg/gaPgMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="gaPgMngList" path="/gaia/gapg/gaPgMngList.jsp"
 *                        redirect="false"
 */
public class GaPgMngListAction extends GaiaAuthAction
{
    /** 조회 */
    public static final int PG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PG_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        GaPgMngListForm gaPgMngListForm = (GaPgMngListForm) form;
        
        switch (gaPgMngListForm.getStrutsAction())
        {
            case GaPgMngListAction.PG_LIST_FIND:
            	findPgList(request, gaPgMngListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case GaPgMngListAction.BASE_SET_HEADER:
                setHeader(request, response, gaPgMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case GaPgMngListAction.PG_LIST_DELETE:
            	deletePage(request, gaPgMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case GaPgMngListAction.BASE_GRID_EXPORT:
            	findPgList(request, gaPgMngListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("gaPgMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, GaPgMngListForm gaPgMngListForm) throws IOException
    {
        super.setHeader(request, response, gaPgMngListForm.getListId(), gaPgMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: GaPgMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param gaPgMngListForm
     * @throws Exception
     */
    private void findPgList(HttpServletRequest request, GaPgMngListForm gaPgMngListForm, HttpServletResponse response) throws IOException
    {
    	GaPgMngListService gaPgMngListService = (GaPgMngListService) getBean("gaPgMngListService");        

    	GaPgMngCommonDTO gaPgMngCommonDTO = gaPgMngListForm.getGaPgMngCommonDTO();

        //리스트를 조회한다.
        List resultList = gaPgMngListService.findPgList(gaPgMngCommonDTO);
        super.makeJsonResult(resultList, request, response, gaPgMngListForm.getListId());
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: GaPgMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngListForm
     * @param request
     */
    private void deletePage(HttpServletRequest request, GaPgMngListForm gaPgMngListForm) throws Exception
    {
    	GaPgMngListService gaPgMngListService = (GaPgMngListService) getBean("gaPgMngListService");
        
    	String[] deleteRows = gaPgMngListForm.getDeleteRows();    // sheet 내역
        
        gaPgMngListService.deletePage(deleteRows);
        
        setAjaxStatus(request);
    }
}
