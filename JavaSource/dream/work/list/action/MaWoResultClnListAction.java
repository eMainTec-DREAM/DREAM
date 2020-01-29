package dream.work.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.MaWoResultClnListForm;
import dream.work.list.service.MaWoResultClnListService;

/**
 * 작업결과-작업설비 목록
 * @author  kim21017
 * @version $Id: MaWoResultClnListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultClnList" name="maWoResultClnListForm"
 *                input="/dream/work/list/maWoResultClnList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultOilList" name="maWoResultClnListForm"
 *                input="/dream/work/list/maWoResultOilList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultClnList" path="/dream/work/list/maWoResultClnList.jsp"
 *                        redirect="false"
 */
public class MaWoResultClnListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_CLN_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_CLN_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultClnListForm maWoResultClnListForm = (MaWoResultClnListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultClnListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultClnListForm.getStrutsAction())
        {
        
            case MaWoResultClnListAction.WO_RESULT_CLN_LIST_FIND:
                findClnList(request,response, maWoResultClnListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultClnListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maWoResultClnListForm.getListId(), maWoResultClnListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultClnListAction.WO_RESULT_CLN_LIST_DELETE:
            	deleteClnList(request,maWoResultClnListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultClnListAction.BASE_GRID_EXPORT:
            	findClnList(request,response, maWoResultClnListForm, true);
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
     * @version $Id: MaWoResultClnListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultClnListForm
     * @throws Exception
     */
    private void findClnList(HttpServletRequest request,HttpServletResponse response, MaWoResultClnListForm maWoResultClnListForm, boolean excelExport) throws Exception
    {
        MaWoResultClnListService maWoResultClnListService = (MaWoResultClnListService) getBean("maWoResultClnListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultClnListForm.getMaWoResultMstrCommonDTO();
        MaWoResultClnListDTO maWoResultClnListDTO = maWoResultClnListForm.getMaWoResultClnListDTO();
        
        //Paging
        maWoResultClnListDTO.setIsLoadMaxCount("Y".equals(maWoResultClnListForm.getIsLoadMaxCount())?true:false);
        maWoResultClnListDTO.setFirstRow(maWoResultClnListForm.getFirstRow());
        maWoResultClnListDTO.setOrderBy(maWoResultClnListForm.getOrderBy());
        maWoResultClnListDTO.setDirection(maWoResultClnListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maWoResultClnListService.findClnList(maWoResultMstrCommonDTO, maWoResultClnListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoResultClnListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultClnListService.findTotalCount(maWoResultMstrCommonDTO, maWoResultClnListDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultClnListForm.getListId(),maWoResultClnListForm.getCurrentPageId(), maWoResultClnListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaWoResultClnListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultClnListForm
     * @throws Exception
     */
    private void deleteClnList(HttpServletRequest request, MaWoResultClnListForm maWoResultClnListForm) throws Exception
    {
    	MaWoResultClnListService maWoResultClnListService = (MaWoResultClnListService) getBean("maWoResultClnListService");
        
    	String[] deleteRows = maWoResultClnListForm.getDeleteRows();
    
    	maWoResultClnListService.deleteClnList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}