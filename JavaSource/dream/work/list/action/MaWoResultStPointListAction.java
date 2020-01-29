package dream.work.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;
import dream.work.list.form.MaWoResultStPointListForm;
import dream.work.list.service.MaWoResultStPointListService;

/**
 * 작업결과-작업필수검사항목 목록
 * @author  kim21017
 * @version $Id: MaWoResultStPointListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultStPointList" name="maWoResultStPointListForm"
 *                input="/dream/work/list/maWoResultStPointList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthStPointList" name="maWoResultStPointListForm"
 *                input="/dream/work/list/month/maWoResultMonthStPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultStPointList" path="/dream/work/list/maWoResultStPointList.jsp"
 *                        redirect="false"
 */
public class MaWoResultStPointListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_STPOINT_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_STPOINT_LIST_DELETE 			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultStPointListForm maWoResultStPointListForm = (MaWoResultStPointListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultStPointListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultStPointListForm.getStrutsAction())
        {
        
            case MaWoResultStPointListAction.WO_RESULT_STPOINT_LIST_FIND:
                findStPointList(request,response, maWoResultStPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultStPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maWoResultStPointListForm.getListId(), maWoResultStPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultStPointListAction.WO_RESULT_STPOINT_LIST_DELETE:
            	deleteStPointList(request,maWoResultStPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultStPointListAction.BASE_GRID_EXPORT:
            	findStPointList(request,response, maWoResultStPointListForm, true);
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
     * @version $Id: MaWoResultStPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultStPointListForm
     * @throws Exception
     */
    private void findStPointList(HttpServletRequest request,HttpServletResponse response, MaWoResultStPointListForm maWoResultStPointListForm, boolean excelExport) throws Exception
    {
        MaWoResultStPointListService maWoResultStPointListService = (MaWoResultStPointListService) getBean("maWoResultStPointListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultStPointListForm.getMaWoResultMstrCommonDTO();
        MaWoResultStPointListDTO maWoResultStPointListDTO = maWoResultStPointListForm.getMaWoResultStPointListDTO();
        
        //Paging
        maWoResultStPointListDTO.setIsLoadMaxCount("Y".equals(maWoResultStPointListForm.getIsLoadMaxCount())?true:false);
        maWoResultStPointListDTO.setFirstRow(maWoResultStPointListForm.getFirstRow());
        maWoResultStPointListDTO.setOrderBy(maWoResultStPointListForm.getOrderBy());
        maWoResultStPointListDTO.setDirection(maWoResultStPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maWoResultStPointListService.findStPointList(maWoResultMstrCommonDTO,maWoResultStPointListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoResultStPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultStPointListService.findTotalCount(maWoResultMstrCommonDTO,maWoResultStPointListDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultStPointListForm.getListId(),maWoResultStPointListForm.getCurrentPageId(), maWoResultStPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaWoResultStPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultStPointListForm
     * @throws Exception
     */
    private void deleteStPointList(HttpServletRequest request, MaWoResultStPointListForm maWoResultStPointListForm) throws Exception
    {
    	MaWoResultStPointListService maWoResultStPointListService = (MaWoResultStPointListService) getBean("maWoResultStPointListService");
        
    	String[] deleteRows = maWoResultStPointListForm.getDeleteRows();
    
    	maWoResultStPointListService.deleteStPointList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}