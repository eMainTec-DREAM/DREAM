package dream.consult.program.page.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.form.MaPgMngBtnListForm;
import dream.consult.program.page.service.MaPgMngBtnListService;

/**
 * 화면별 버튼 목록
 * @author  kim21017
 * @version $Id: MaPgMngBtnListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngBtnList" name="maPgMngBtnListForm"
 *                input="/dream/consult/program/page/maPgMngBtnList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngBtnList" path="/dream/consult/program/page/maPgMngBtnList.jsp"
 *                        redirect="false"
 */
public class MaPgMngBtnListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_BTN_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PG_BTN_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngBtnListForm maPgMngBtnListForm = (MaPgMngBtnListForm) form;
        
        switch (maPgMngBtnListForm.getStrutsAction())
        {
        
            case MaPgMngBtnListAction.PG_BTN_LIST_FIND:
                findBtnList(request,response, maPgMngBtnListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngBtnListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPgMngBtnListForm.getListId(), maPgMngBtnListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngBtnListAction.PG_BTN_LIST_DELETE:
            	deleteBtnList(request,maPgMngBtnListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngBtnListAction.BASE_GRID_EXPORT:
            	findBtnList(request,response, maPgMngBtnListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPgMngBtnList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPgMngBtnListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngBtnListForm
     * @throws Exception
     */
    private void findBtnList(HttpServletRequest request,HttpServletResponse response, MaPgMngBtnListForm maPgMngBtnListForm) throws Exception
    {
        MaPgMngBtnListService maPgMngBtnListService = (MaPgMngBtnListService) getBean("maPgMngBtnListService");
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngBtnListForm.getMaPgMngCommonDTO();
        MaPgMngBtnListDTO maPgMngBtnListDTO = maPgMngBtnListForm.getMaPgMngBtnListDTO();
        maPgMngCommonDTO.setUserLang(getUser(request).getLangId());
        
        List resultList = maPgMngBtnListService.findBtnList(maPgMngCommonDTO, maPgMngBtnListDTO);
        super.makeJsonResult(resultList, request, response, maPgMngBtnListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPgMngBtnListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngBtnListForm
     * @throws Exception
     */
    private void deleteBtnList(HttpServletRequest request, MaPgMngBtnListForm maPgMngBtnListForm) throws Exception
    {
    	MaPgMngBtnListService maPgMngBtnListService = (MaPgMngBtnListService) getBean("maPgMngBtnListService");
        
    	String[] deleteRows = maPgMngBtnListForm.getDeleteRows();
    
    	maPgMngBtnListService.deleteBtnList(deleteRows);
        
    	setAjaxStatus(request);
    }
}