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
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;
import dream.mgr.cal.form.MgrCalCompWkrcalDaysetListForm;
import dream.mgr.cal.service.MgrCalCompWkrcalDaysetListService;

/**
 * 휴무일 설정  - 목록 action
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/mgrCalCompWkrcalDaysetList" name="mgrCalCompWkrcalDaysetListForm"
 *                input="/dream/mgr/cal/mgrCalCompWkrcalDaysetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalCompWkrcalDaysetList" path="/dream/mgr/cal/mgrCalCompWkrcalDaysetList.jsp"
 *                        redirect="false"
 */
public class MgrCalCompWkrcalDaysetListAction extends AuthAction
{
    /** 조회 */
    public static final int WRKCAL_DAYSET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WRKCAL_DAYSET_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCalCompWkrcalDaysetListForm mgrCalCompWkrcalDaysetListForm = (MgrCalCompWkrcalDaysetListForm) form;

        switch (mgrCalCompWkrcalDaysetListForm.getStrutsAction())
        {
            case MgrCalCompWkrcalDaysetListAction.WRKCAL_DAYSET_LIST_FIND:
                findDaysetList(request, mgrCalCompWkrcalDaysetListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalCompWkrcalDaysetListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrCalCompWkrcalDaysetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalCompWkrcalDaysetListAction.WRKCAL_DAYSET_LIST_DELETE:
            	deleteWrkcal(request, mgrCalCompWkrcalDaysetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrCalCompWkrcalDaysetListAction.BASE_GRID_EXPORT:
            	findDaysetList(request, mgrCalCompWkrcalDaysetListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDaysetList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrCalCompWkrcalDaysetListForm mgrCalCompWkrcalDaysetListForm) throws IOException
    {
        super.setHeader(request, response, mgrCalCompWkrcalDaysetListForm.getListId(),mgrCalCompWkrcalDaysetListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDaysetListForm
     * @param response
     * @throws Exception
     */
    private void findDaysetList(HttpServletRequest request, MgrCalCompWkrcalDaysetListForm mgrCalCompWkrcalDaysetListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MgrCalCompWkrcalDaysetListService mgrCalCompWkrcalDaysetListService = (MgrCalCompWkrcalDaysetListService) getBean("mgrCalCompWkrcalDaysetListService");

    	MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalDaysetListForm.getMgrCalCompWkrcalCommonDTO();
    	MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO = mgrCalCompWkrcalDaysetListForm.getMgrCalCompWkrcalDaysetListDTO();
    	
    	mgrCalCompWkrcalCommonDTO.setUserLang(getUser(request).getLangId());
    	
    	//Paging
        mgrCalCompWkrcalCommonDTO.setIsLoadMaxCount("Y".equals(mgrCalCompWkrcalDaysetListForm.getIsLoadMaxCount())?true:false);
        mgrCalCompWkrcalCommonDTO.setFirstRow(mgrCalCompWkrcalDaysetListForm.getFirstRow());
        mgrCalCompWkrcalCommonDTO.setOrderBy(mgrCalCompWkrcalDaysetListForm.getOrderBy());
        mgrCalCompWkrcalCommonDTO.setDirection(mgrCalCompWkrcalDaysetListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrCalCompWkrcalDaysetListService.findDaysetList(mgrCalCompWkrcalCommonDTO, mgrCalCompWkrcalDaysetListDTO);

        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(mgrCalCompWkrcalDaysetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCalCompWkrcalDaysetListService.findTotalCount(mgrCalCompWkrcalCommonDTO, mgrCalCompWkrcalDaysetListDTO);
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrCalCompWkrcalDaysetListForm); 
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDaysetListForm
     */
    private void deleteWrkcal(HttpServletRequest request, MgrCalCompWkrcalDaysetListForm mgrCalCompWkrcalDaysetListForm) throws Exception
    {
    	MgrCalCompWkrcalDaysetListService mgrCalCompWkrcalDaysetListService = (MgrCalCompWkrcalDaysetListService) getBean("mgrCalCompWkrcalDaysetListService");

    	String[] deleteRows = mgrCalCompWkrcalDaysetListForm.getDeleteRows();

    	mgrCalCompWkrcalDaysetListService.deleteWrkcal(deleteRows);

        setAjaxStatus(request);
    }
}
