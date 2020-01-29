package dream.work.pm.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.MaPmEqClnListForm;
import dream.work.pm.list.service.MaPmEqClnListService;

/**
 * 예방설비 목록
 * @author  kim21017
 * @version $Id: MaPmEqClnListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPmEqClnList" name="maPmEqClnListForm"
 *                input="/dream/work/pm/list/work/maPmEqClnList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmEqOilList" name="maPmEqClnListForm"
 *                input="/dream/work/pm/list/work/maPmEqOilList.jsp" scope="request"
 *                validate="false"
 */
public class MaPmEqClnListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_EQ_CLN_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PM_EQ_CLN_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmEqClnListForm maPmEqClnListForm = (MaPmEqClnListForm) form;
        
        switch (maPmEqClnListForm.getStrutsAction())
        {
        
            case MaPmEqClnListAction.PM_EQ_CLN_LIST_FIND:
                findClnList(request,response, maPmEqClnListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmEqClnListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPmEqClnListForm.getListId(), maPmEqClnListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmEqClnListAction.PM_EQ_CLN_LIST_DELETE:
            	deleteClnList(request,maPmEqClnListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmEqClnListAction.BASE_GRID_EXPORT:
            	findClnList(request,response, maPmEqClnListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPmEqClnListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmEqClnListForm
     * @throws Exception
     */
    private void findClnList(HttpServletRequest request,HttpServletResponse response, MaPmEqClnListForm maPmEqClnListForm, boolean excelExport) throws Exception
    {
        MaPmEqClnListService maPmEqClnListService = (MaPmEqClnListService) getBean("maPmEqClnListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = maPmEqClnListForm.getMaPmMstrCommonDTO();
        
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmEqClnListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmEqClnListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmEqClnListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmEqClnListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maPmEqClnListService.findClnList(maPmMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmEqClnListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmEqClnListService.findTotalCount(maPmMstrCommonDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmEqClnListForm.getListId(),maPmEqClnListForm.getCurrentPageId(), maPmEqClnListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPmEqClnListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmEqClnListForm
     * @throws Exception
     */
    private void deleteClnList(HttpServletRequest request, MaPmEqClnListForm maPmEqClnListForm) throws Exception
    {
    	MaPmEqClnListService maPmEqClnListService = (MaPmEqClnListService) getBean("maPmEqClnListService");
        
    	String[] deleteRows = maPmEqClnListForm.getDeleteRows();
    
    	maPmEqClnListService.deleteClnList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}