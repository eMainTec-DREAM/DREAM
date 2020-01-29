package dream.work.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.WorkListBmRplPartSerialListForm;
import dream.work.list.service.WorkListBmRplPartSerialListService;

/**
 * 작업결과-부품 - Serial 목록
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListBmRplPartSerialList" name="workListBmRplPartSerialListForm"
 *                input="/dream/work/list/bm/rpl/workListBmRplPartSerialList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListCmRplPartSerialList" name="workListBmRplPartSerialListForm"
 *                input="/dream/work/list/cm/rpl/workListCmRplPartSerialList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListPmRplPartSerialList" name="workListBmRplPartSerialListForm"
 *                input="/dream/work/list/pmw/rpl/workListPmRplPartSerialList.jsp" scope="request"
 *                validate="false"
 */
public class WorkListBmRplPartSerialListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_PTSERIAL_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_PTSERIAL_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListBmRplPartSerialListForm workListBmRplPartSerialListForm = (WorkListBmRplPartSerialListForm) form;
        switch (workListBmRplPartSerialListForm.getStrutsAction())
        {
        
            case WorkListBmRplPartSerialListAction.WO_RESULT_PTSERIAL_LIST_FIND:
                findList(request,response, workListBmRplPartSerialListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListBmRplPartSerialListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListBmRplPartSerialListForm.getListId(), workListBmRplPartSerialListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListBmRplPartSerialListAction.WO_RESULT_PTSERIAL_LIST_DELETE:
            	deleteList(request,workListBmRplPartSerialListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListBmRplPartSerialListAction.BASE_GRID_EXPORT:
            	findList(request,response, workListBmRplPartSerialListForm, true);
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
     * @version $Id: WorkListBmRplPartSerialListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListBmRplPartSerialListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkListBmRplPartSerialListForm workListBmRplPartSerialListForm, boolean excelExport) throws Exception
    {
        WorkListBmRplPartSerialListService workListBmRplPartSerialListService = (WorkListBmRplPartSerialListService) getBean("workListBmRplPartSerialListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListBmRplPartSerialListForm.getMaWoResultMstrCommonDTO();
        WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO = workListBmRplPartSerialListForm.getWorkListBmRplPartSerialListDTO();
        
        //Paging
        workListBmRplPartSerialListDTO.setIsLoadMaxCount("Y".equals(workListBmRplPartSerialListForm.getIsLoadMaxCount())?true:false);
        workListBmRplPartSerialListDTO.setFirstRow(workListBmRplPartSerialListForm.getFirstRow());
        workListBmRplPartSerialListDTO.setOrderBy(workListBmRplPartSerialListForm.getOrderBy());
        workListBmRplPartSerialListDTO.setDirection(workListBmRplPartSerialListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListBmRplPartSerialListService.findList(maWoResultMstrCommonDTO, workListBmRplPartSerialListDTO, workListBmRplPartSerialListForm.getMaWoResultPartDetailDTO(), user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListBmRplPartSerialListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListBmRplPartSerialListService.findTotalCount(maWoResultMstrCommonDTO, workListBmRplPartSerialListDTO,workListBmRplPartSerialListForm.getMaWoResultPartDetailDTO(),user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListBmRplPartSerialListForm.getListId(),workListBmRplPartSerialListForm.getCurrentPageId(), workListBmRplPartSerialListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkListBmRplPartSerialListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListBmRplPartSerialListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, WorkListBmRplPartSerialListForm workListBmRplPartSerialListForm) throws Exception
    {
    	WorkListBmRplPartSerialListService workListBmRplPartSerialListService = (WorkListBmRplPartSerialListService) getBean("workListBmRplPartSerialListService");
        
    	String[] deleteRows = workListBmRplPartSerialListForm.getDeleteRows();
    
    	workListBmRplPartSerialListService.deleteList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}