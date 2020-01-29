package dream.mgr.cal.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.form.MgrCalCompWkrcalListForm;
import dream.mgr.cal.service.MgrCalCompWkrcalListService;

/**
 * 근무일달력 - 목록 action
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/mgrCalCompWkrcalList" name="mgrCalCompWkrcalListForm"
 *                input="/dream/mgr/cal/mgrCalCompWkrcalList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalCompWkrcalList" path="/dream/mgr/cal/mgrCalCompWkrcalList.jsp"
 *                        redirect="false"
 */
public class MgrCalCompWkrcalListAction extends AuthAction
{
    /** 조회 */
    public static final int WRKCAL_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WRKCAL_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCalCompWkrcalListForm mgrCalCompWkrcalListForm = (MgrCalCompWkrcalListForm) form;

        switch (mgrCalCompWkrcalListForm.getStrutsAction())
        {
            case MgrCalCompWkrcalListAction.WRKCAL_LIST_FIND:
                findWrkcalList(request, mgrCalCompWkrcalListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalCompWkrcalListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrCalCompWkrcalListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalCompWkrcalListAction.WRKCAL_LIST_DELETE:
            	deleteWrkcal(request, mgrCalCompWkrcalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrCalCompWkrcalListAction.BASE_GRID_EXPORT:
            	findWrkcalList(request, mgrCalCompWkrcalListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrCalCompWkrcalList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrCalCompWkrcalListForm mgrCalCompWkrcalListForm) throws IOException
    {
        super.setHeader(request, response, mgrCalCompWkrcalListForm.getListId(),mgrCalCompWkrcalListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalListForm
     * @param response
     * @throws Exception
     */
    private void findWrkcalList(HttpServletRequest request, MgrCalCompWkrcalListForm mgrCalCompWkrcalListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MgrCalCompWkrcalListService mgrCalCompWkrcalListService = (MgrCalCompWkrcalListService) getBean("mgrCalCompWkrcalListService");

    	MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalListForm.getMgrCalCompWkrcalCommonDTO();

    	//Paging
    	mgrCalCompWkrcalCommonDTO.setIsLoadMaxCount("Y".equals(mgrCalCompWkrcalListForm.getIsLoadMaxCount())?true:false);
    	mgrCalCompWkrcalCommonDTO.setFirstRow(mgrCalCompWkrcalListForm.getFirstRow());
    	mgrCalCompWkrcalCommonDTO.setOrderBy(mgrCalCompWkrcalListForm.getOrderBy());
        mgrCalCompWkrcalCommonDTO.setDirection(mgrCalCompWkrcalListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrCalCompWkrcalListService.findWrkcalList(mgrCalCompWkrcalCommonDTO, getUser(request));

      //Paging
        String totalCount = "";
                
        if(Integer.parseInt(mgrCalCompWkrcalListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCalCompWkrcalListService.findTotalCount(mgrCalCompWkrcalCommonDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrCalCompWkrcalListForm); //makeGridExport(resultList, request, response, maUsrGrpListForm.getListId(),maUsrGrpListForm.getCurrentPageId(), maUsrGrpListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);

	}
    /**
     * delete
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalListAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalListForm
     */
    private void deleteWrkcal(HttpServletRequest request, MgrCalCompWkrcalListForm mgrCalCompWkrcalListForm) throws Exception
    {
    	MgrCalCompWkrcalListService mgrCalCompWkrcalListService = (MgrCalCompWkrcalListService) getBean("mgrCalCompWkrcalListService");

    	String[] deleteRows = mgrCalCompWkrcalListForm.getDeleteRows();
    	String[] deleteRowsExt = mgrCalCompWkrcalListForm.getDeleteRowsExt();

    	mgrCalCompWkrcalListService.deleteWrkcal(deleteRows, deleteRowsExt, getUser(request));

        setAjaxStatus(request);
    }
}
