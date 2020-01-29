package dream.asset.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;
import dream.asset.list.form.MaEqMstrPmWorkListForm;
import dream.asset.list.service.MaEqMstrPmWorkListService;

/**
 * 설비의 정기작업 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmWorkList" name="maEqMstrPmWorkListForm"
 *                input="/dream/asset/list/maEqMstrPmWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmWorkList" path="/dream/asset/list/maEqMstrPmWorkList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmWorkListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PMWORK_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_PMWORK_LIST_DELETE 			= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmWorkListForm maEqMstrPmWorkListForm = (MaEqMstrPmWorkListForm) form;
        
        switch (maEqMstrPmWorkListForm.getStrutsAction())
        {
            case MaEqMstrPmWorkListAction.EQ_MSTR_PMWORK_LIST_FIND:
            	findEqPmWorkList(request,response, maEqMstrPmWorkListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmWorkListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrPmWorkListForm.getListId(), maEqMstrPmWorkListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPmWorkListAction.EQ_MSTR_PMWORK_LIST_DELETE:
            	deleteEqPmWorkList(request,maEqMstrPmWorkListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmWorkListAction.BASE_GRID_EXPORT:
            	findEqPmWorkList(request,response, maEqMstrPmWorkListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmWorkList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrPmWorkListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmWorkListForm
     * @throws Exception
     */
    private void findEqPmWorkList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPmWorkListForm maEqMstrPmWorkListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPmWorkListService maEqMstrPmWorkListService = (MaEqMstrPmWorkListService) getBean("maEqMstrPmWorkListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmWorkListForm.getMaEqMstrCommonDTO();
        MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO = maEqMstrPmWorkListForm.getMaEqMstrPmWorkListDTO();
        
        //Paging
        maEqMstrPmWorkListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPmWorkListForm.getIsLoadMaxCount())?true:false);
        maEqMstrPmWorkListDTO.setFirstRow(maEqMstrPmWorkListForm.getFirstRow());
        maEqMstrPmWorkListDTO.setOrderBy(maEqMstrPmWorkListForm.getOrderBy());
        maEqMstrPmWorkListDTO.setDirection(maEqMstrPmWorkListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrPmWorkListService.findEqPmWorkList(maEqMstrCommonDTO, maEqMstrPmWorkListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrPmWorkListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPmWorkListService.findTotalCount(maEqMstrCommonDTO, maEqMstrPmWorkListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrPmWorkListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrPmWorkListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmWorkListForm
     * @throws Exception
     */
    private void deleteEqPmWorkList(HttpServletRequest request, MaEqMstrPmWorkListForm maEqMstrPmWorkListForm) throws Exception
    {
    	MaEqMstrPmWorkListService maEqMstrPmWorkListService = (MaEqMstrPmWorkListService) getBean("maEqMstrPmWorkListService");
        
    	String[] deletePmIdRows = maEqMstrPmWorkListForm.getDeleteRows();
    	String[] deletePmEquipIdRows = maEqMstrPmWorkListForm.getDeleteRowsExt();
    
    	maEqMstrPmWorkListService.deleteEqPmWorkList(deletePmIdRows,deletePmEquipIdRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}