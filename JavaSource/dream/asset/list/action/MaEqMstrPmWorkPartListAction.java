package dream.asset.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;
import dream.asset.list.form.MaEqMstrPmWorkPartListForm;
import dream.asset.list.service.MaEqMstrPmWorkPartListService;

/**
 * 설비-작업-부품
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmWorkPartList" name="maEqMstrPmWorkPartListForm"
 *                input="/dream/asset/list/maEqMstrPmWorkPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmWorkPartList" path="/dream/asset/list/maEqMstrPmWorkPartList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmWorkPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PMWORK_PART_LIST_FIND 				= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_PMWORK_PART_LIST_DELETE 			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmWorkPartListForm maEqMstrPmWorkPartListForm = (MaEqMstrPmWorkPartListForm) form;
        
        switch (maEqMstrPmWorkPartListForm.getStrutsAction())
        {
            case MaEqMstrPmWorkPartListAction.EQ_MSTR_PMWORK_PART_LIST_FIND:
            	findEqPmWorkPartList(request,response, maEqMstrPmWorkPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmWorkPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrPmWorkPartListForm.getListId(), maEqMstrPmWorkPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmWorkPartListAction.EQ_MSTR_PMWORK_PART_LIST_DELETE:
            	deleteEqPmWorkPartList(request,maEqMstrPmWorkPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmWorkPartListAction.BASE_GRID_EXPORT:
            	findEqPmWorkPartList(request,response, maEqMstrPmWorkPartListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmWorkPartList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrPmWorkPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmWorkPartListForm
     * @throws Exception
     */
    private void findEqPmWorkPartList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPmWorkPartListForm maEqMstrPmWorkPartListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPmWorkPartListService maEqMstrPmWorkPartListService = (MaEqMstrPmWorkPartListService) getBean("maEqMstrPmWorkPartListService");
        MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkPartListForm.getMaEqMstrPmWorkDetailDTO();
        MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO = maEqMstrPmWorkPartListForm.getMaEqMstrPmWorkPartListDTO();
        
        //Paging
        maEqMstrPmWorkPartListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPmWorkPartListForm.getIsLoadMaxCount())?true:false);
        maEqMstrPmWorkPartListDTO.setFirstRow(maEqMstrPmWorkPartListForm.getFirstRow());
        maEqMstrPmWorkPartListDTO.setOrderBy(maEqMstrPmWorkPartListForm.getOrderBy());
        maEqMstrPmWorkPartListDTO.setDirection(maEqMstrPmWorkPartListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrPmWorkPartListService.findEqPmWorkPartList(maEqMstrPmWorkDetailDTO,maEqMstrPmWorkPartListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrPmWorkPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPmWorkPartListService.findTotalCount(maEqMstrPmWorkDetailDTO,maEqMstrPmWorkPartListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrPmWorkPartListForm.getListId(),maEqMstrPmWorkPartListForm.getCurrentPageId(), maEqMstrPmWorkPartListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrPmWorkPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmWorkPartListForm
     * @throws Exception
     */
    private void deleteEqPmWorkPartList(HttpServletRequest request, MaEqMstrPmWorkPartListForm maEqMstrPmWorkPartListForm) throws Exception
    {
    	MaEqMstrPmWorkPartListService maEqMstrPmWorkPartListService = (MaEqMstrPmWorkPartListService) getBean("maEqMstrPmWorkPartListService");
        
    	String[] deletePmIdRows = maEqMstrPmWorkPartListForm.getDeleteRows();
    	String[] deletePmPartIdRows = maEqMstrPmWorkPartListForm.getDeleteRowsExt();
    
    	maEqMstrPmWorkPartListService.deleteEqPmWorkPartList(deletePmIdRows,deletePmPartIdRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}