package dream.asset.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;
import dream.asset.list.form.MaEqMstrPmInsListForm;
import dream.asset.list.service.MaEqMstrPmInsListService;

/**
 * 설비의 정기점검 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmInsList" name="maEqMstrPmInsListForm"
 *                input="/dream/asset/list/maEqMstrPmInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmInsList" path="/dream/asset/list/maEqMstrPmInsList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmInsListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PMINS_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_PMINS_LIST_DELETE 			= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmInsListForm maEqMstrPmInsListForm = (MaEqMstrPmInsListForm) form;
        
        switch (maEqMstrPmInsListForm.getStrutsAction())
        {
            case MaEqMstrPmInsListAction.EQ_MSTR_PMINS_LIST_FIND:
            	findEqPmInsList(request,response, maEqMstrPmInsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmInsListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrPmInsListForm.getListId(), maEqMstrPmInsListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmInsListAction.EQ_MSTR_PMINS_LIST_DELETE:
            	deleteEqPmInsList(request,maEqMstrPmInsListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmInsListAction.BASE_GRID_EXPORT:
            	findEqPmInsList(request,response, maEqMstrPmInsListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmInsList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrPmInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmInsListForm
     * @throws Exception
     */
    private void findEqPmInsList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPmInsListForm maEqMstrPmInsListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPmInsListService maEqMstrPmInsListService = (MaEqMstrPmInsListService) getBean("maEqMstrPmInsListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmInsListForm.getMaEqMstrCommonDTO();
        MaEqMstrPmInsListDTO maEqMstrPmInsListDTO = maEqMstrPmInsListForm.getMaEqMstrPmInsListDTO();
        
        //Paging
        maEqMstrPmInsListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPmInsListForm.getIsLoadMaxCount())?true:false);
        maEqMstrPmInsListDTO.setFirstRow(maEqMstrPmInsListForm.getFirstRow());
        maEqMstrPmInsListDTO.setOrderBy(maEqMstrPmInsListForm.getOrderBy());
        maEqMstrPmInsListDTO.setDirection(maEqMstrPmInsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrPmInsListService.findEqPmInsList(maEqMstrCommonDTO, maEqMstrPmInsListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrPmInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPmInsListService.findTotalCount(maEqMstrCommonDTO, maEqMstrPmInsListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrPmInsListForm.getListId(),maEqMstrPmInsListForm.getCurrentPageId(), maEqMstrPmInsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrPmInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmInsListForm
     * @throws Exception
     */
    private void deleteEqPmInsList(HttpServletRequest request, MaEqMstrPmInsListForm maEqMstrPmInsListForm) throws Exception
    {
    	MaEqMstrPmInsListService maEqMstrPmInsListService = (MaEqMstrPmInsListService) getBean("maEqMstrPmInsListService");
        
    	String[] deletePmIdRows = maEqMstrPmInsListForm.getDeleteRows();
    	String[] deletePmEquipIdRows = maEqMstrPmInsListForm.getDeleteRowsExt();
    
    	maEqMstrPmInsListService.deleteEqPmInsList(deletePmIdRows,deletePmEquipIdRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}