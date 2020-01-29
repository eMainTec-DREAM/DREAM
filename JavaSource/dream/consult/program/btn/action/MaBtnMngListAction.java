package dream.consult.program.btn.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.program.btn.dto.MaBtnMngCommonDTO;
import dream.consult.program.btn.form.MaBtnMngListForm;
import dream.consult.program.btn.service.MaBtnMngListService;

/**
 * 버튼 - 목록 action
 * @author  kim21017
 * @version $Id: MaBtnMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maBtnMngList" name="maBtnMngListForm"
 *                input="/dream/consult/program/btn/maBtnMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBtnMngList" path="/dream/consult/program/btn/maBtnMngList.jsp"
 *                        redirect="false"
 */
public class MaBtnMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int BTN_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int BTN_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBtnMngListForm maBtnMngListForm = (MaBtnMngListForm) form;
        
        switch (maBtnMngListForm.getStrutsAction())
        {
            case MaBtnMngListAction.BTN_LIST_FIND:
                findBtnList(request, maBtnMngListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBtnMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maBtnMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBtnMngListAction.BTN_LIST_DELETE:
            	deleteBtn(request, maBtnMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBtnMngListAction.BASE_GRID_EXPORT:
            	findBtnList(request, maBtnMngListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maBtnMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBtnMngListForm maBtnMngListForm) throws IOException
    {
        super.setHeader(request, response, maBtnMngListForm.getListId(),maBtnMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaBtnMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBtnMngListForm
     * @param response
     * @throws Exception
     */
    private void findBtnList(HttpServletRequest request, MaBtnMngListForm maBtnMngListForm, HttpServletResponse response) throws IOException
    {
    	MaBtnMngListService maBtnMngListService = (MaBtnMngListService) getBean("maBtnMngListService");        

    	MaBtnMngCommonDTO maBtnMngCommonDTO = maBtnMngListForm.getMaBtnMngCommonDTO();

        //리스트를 조회한다.
        List resultList = maBtnMngListService.findBtnList(maBtnMngCommonDTO);

        super.makeJsonResult(resultList, request, response, maBtnMngListForm.getListId());
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaBtnMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBtnMngListForm
     */
    private void deleteBtn(HttpServletRequest request, MaBtnMngListForm maBtnMngListForm) throws Exception
    {
    	MaBtnMngListService maBtnMngListService = (MaBtnMngListService) getBean("maBtnMngListService");        

    	String[] deleteRows = maBtnMngListForm.getDeleteRows();
    	
    	maBtnMngListService.deleteBtn(deleteRows);
        
        setAjaxStatus(request);
    }
}
