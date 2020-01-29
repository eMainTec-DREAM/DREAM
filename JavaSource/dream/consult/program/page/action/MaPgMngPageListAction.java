package dream.consult.program.page.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;
import dream.consult.program.page.form.MaPgMngPageListForm;
import dream.consult.program.page.service.MaPgMngPageListService;

/**
 * 화면별 탭페이지 목록
 * @author  kim21017
 * @version $Id: MaPgMngPageListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngPageList" name="maPgMngPageListForm"
 *                input="/dream/consult/program/page/maPgMngPageList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngPageList" path="/dream/consult/program/page/maPgMngPageList.jsp"
 *                        redirect="false"
 */
public class MaPgMngPageListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_PAGE_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int PG_PAGE_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngPageListForm maPgMngPageListForm = (MaPgMngPageListForm) form;
        
        switch (maPgMngPageListForm.getStrutsAction())
        {
        
            case MaPgMngPageListAction.PG_PAGE_LIST_FIND:
                findPageList(request,response, maPgMngPageListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngPageListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPgMngPageListForm.getListId(), maPgMngPageListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngPageListAction.PG_PAGE_LIST_DELETE:
            	deletePageList(request,maPgMngPageListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngPageListAction.BASE_GRID_EXPORT:
            	findPageList(request,response, maPgMngPageListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPgMngPageList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPgMngPageListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngPageListForm
     * @throws Exception
     */
    private void findPageList(HttpServletRequest request,HttpServletResponse response, MaPgMngPageListForm maPgMngPageListForm) throws Exception
    {
        MaPgMngPageListService maPgMngPageListService = (MaPgMngPageListService) getBean("maPgMngPageListService");
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngPageListForm.getMaPgMngCommonDTO();
        MaPgMngPageListDTO maPgMngPageListDTO = maPgMngPageListForm.getMaPgMngPageListDTO();
        
        List resultList = maPgMngPageListService.findPageList(maPgMngCommonDTO, maPgMngPageListDTO);
        super.makeJsonResult(resultList, request, response, maPgMngPageListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPgMngPageListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngPageListForm
     * @throws Exception
     */
    private void deletePageList(HttpServletRequest request, MaPgMngPageListForm maPgMngPageListForm) throws Exception
    {
    	MaPgMngPageListService maPgMngPageListService = (MaPgMngPageListService) getBean("maPgMngPageListService");
        
    	String[] deleteRows = maPgMngPageListForm.getDeleteRows();
    
    	maPgMngPageListService.deletePageList(deleteRows);
    	
    	setAjaxStatus(request);
    }
}