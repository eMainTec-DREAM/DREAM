package dream.mgr.cal.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;
import dream.mgr.cal.form.MgrCalCompWkrcalDowsetListForm;
import dream.mgr.cal.service.MgrCalCompWkrcalDowsetListService;

/**
 * 휴무요일 설정  - 목록 action
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/mgrCalCompWkrcalDowsetList" name="mgrCalCompWkrcalDowsetListForm"
 *                input="/dream/mgr/cal/mgrCalCompWkrcalDowsetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalCompWkrcalDowsetList" path="/dream/mgr/cal/mgrCalCompWkrcalDowsetList.jsp"
 *                        redirect="false"
 */
public class MgrCalCompWkrcalDowsetListAction extends AuthAction
{
    /** 조회 */
    public static final int WRKCAL_DOWSET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WRKCAL_DOWSET_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCalCompWkrcalDowsetListForm mgrCalCompWkrcalDowsetListForm = (MgrCalCompWkrcalDowsetListForm) form;

        switch (mgrCalCompWkrcalDowsetListForm.getStrutsAction())
        {
            case MgrCalCompWkrcalDowsetListAction.WRKCAL_DOWSET_LIST_FIND:
                findDowsetList(request, mgrCalCompWkrcalDowsetListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalCompWkrcalDowsetListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrCalCompWkrcalDowsetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalCompWkrcalDowsetListAction.WRKCAL_DOWSET_LIST_DELETE:
            	deleteWrkcal(request, mgrCalCompWkrcalDowsetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrCalCompWkrcalDowsetListAction.BASE_GRID_EXPORT:
            	findDowsetList(request, mgrCalCompWkrcalDowsetListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDowsetList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrCalCompWkrcalDowsetListForm mgrCalCompWkrcalDowsetListForm) throws IOException
    {
        super.setHeader(request, response, mgrCalCompWkrcalDowsetListForm.getListId(),mgrCalCompWkrcalDowsetListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDowsetListForm
     * @param response
     * @throws Exception
     */
    private void findDowsetList(HttpServletRequest request, MgrCalCompWkrcalDowsetListForm mgrCalCompWkrcalDowsetListForm, HttpServletResponse response) throws IOException
    {
    	MgrCalCompWkrcalDowsetListService mgrCalCompWkrcalDowsetListService = (MgrCalCompWkrcalDowsetListService) getBean("mgrCalCompWkrcalDowsetListService");

    	MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalDowsetListForm.getMgrCalCompWkrcalCommonDTO();
    	MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO = mgrCalCompWkrcalDowsetListForm.getMgrCalCompWkrcalDowsetListDTO();
    	
    	mgrCalCompWkrcalCommonDTO.setUserLang(getUser(request).getLangId());

        //리스트를 조회한다.
        List resultList = mgrCalCompWkrcalDowsetListService.findDowsetList(mgrCalCompWkrcalCommonDTO, mgrCalCompWkrcalDowsetListDTO);

        super.makeJsonResult(resultList, request, response, mgrCalCompWkrcalDowsetListForm.getListId());
	}
    /**
     * delete
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDowsetListForm
     */
    private void deleteWrkcal(HttpServletRequest request, MgrCalCompWkrcalDowsetListForm mgrCalCompWkrcalDowsetListForm) throws Exception
    {
    	MgrCalCompWkrcalDowsetListService mgrCalCompWkrcalDowsetListService = (MgrCalCompWkrcalDowsetListService) getBean("mgrCalCompWkrcalDowsetListService");

    	String[] deleteRows = mgrCalCompWkrcalDowsetListForm.getDeleteRows();

    	mgrCalCompWkrcalDowsetListService.deleteWrkcal(deleteRows);

        setAjaxStatus(request);
    }
}
