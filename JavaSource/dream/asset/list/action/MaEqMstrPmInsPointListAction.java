package dream.asset.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;
import dream.asset.list.form.MaEqMstrPmInsPointListForm;
import dream.asset.list.service.MaEqMstrPmInsPointListService;

/**
 * 설비-점검-항목
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmInsPointList" name="maEqMstrPmInsPointListForm"
 *                input="/dream/asset/list/maEqMstrPmInsPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmInsPointList" path="/dream/asset/list/maEqMstrPmInsPointList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmInsPointListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PMINS_POINT_LIST_FIND 				= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_PMINS_POINT_LIST_DELETE 			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmInsPointListForm maEqMstrPmInsPointListForm = (MaEqMstrPmInsPointListForm) form;
        
        switch (maEqMstrPmInsPointListForm.getStrutsAction())
        {
            case MaEqMstrPmInsPointListAction.EQ_MSTR_PMINS_POINT_LIST_FIND:
            	findEqPmInsPointList(request,response, maEqMstrPmInsPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmInsPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrPmInsPointListForm.getListId(), maEqMstrPmInsPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmInsPointListAction.EQ_MSTR_PMINS_POINT_LIST_DELETE:
            	deleteEqPmInsPointList(request,maEqMstrPmInsPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmInsPointListAction.BASE_GRID_EXPORT:
            	findEqPmInsPointList(request,response, maEqMstrPmInsPointListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmInsPointList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrPmInsPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmInsPointListForm
     * @throws Exception
     */
    private void findEqPmInsPointList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPmInsPointListForm maEqMstrPmInsPointListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPmInsPointListService maEqMstrPmInsPointListService = (MaEqMstrPmInsPointListService) getBean("maEqMstrPmInsPointListService");
        MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsPointListForm.getMaEqMstrPmInsDetailDTO();
        MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO = maEqMstrPmInsPointListForm.getMaEqMstrPmInsPointListDTO();
        
        //Paging
        maEqMstrPmInsPointListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPmInsPointListForm.getIsLoadMaxCount())?true:false);
        maEqMstrPmInsPointListDTO.setFirstRow(maEqMstrPmInsPointListForm.getFirstRow());
        maEqMstrPmInsPointListDTO.setOrderBy(maEqMstrPmInsPointListForm.getOrderBy());
        maEqMstrPmInsPointListDTO.setDirection(maEqMstrPmInsPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrPmInsPointListService.findEqPmInsPointList(maEqMstrPmInsDetailDTO,maEqMstrPmInsPointListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrPmInsPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPmInsPointListService.findTotalCount(maEqMstrPmInsDetailDTO,maEqMstrPmInsPointListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrPmInsPointListForm.getListId(),maEqMstrPmInsPointListForm.getCurrentPageId(), maEqMstrPmInsPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrPmInsPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmInsPointListForm
     * @throws Exception
     */
    private void deleteEqPmInsPointList(HttpServletRequest request, MaEqMstrPmInsPointListForm maEqMstrPmInsPointListForm) throws Exception
    {
    	MaEqMstrPmInsPointListService maEqMstrPmInsPointListService = (MaEqMstrPmInsPointListService) getBean("maEqMstrPmInsPointListService");
        
    	String[] deletePmIdRows = maEqMstrPmInsPointListForm.getDeleteRows();
    	String[] deletePmPointIdRows = maEqMstrPmInsPointListForm.getDeleteRowsExt();
    
    	maEqMstrPmInsPointListService.deleteEqPmInsPointList(deletePmIdRows,deletePmPointIdRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}