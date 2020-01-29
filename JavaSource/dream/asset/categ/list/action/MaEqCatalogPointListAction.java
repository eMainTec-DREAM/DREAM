package dream.asset.categ.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;
import dream.asset.categ.list.form.MaEqCatalogPointListForm;
import dream.asset.categ.list.service.MaEqCatalogPointListService;

/**
 * 설비종류의 점검항목 탭 list action
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListAction.java,v 1.0 2015/12/04 09:09:30 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/maEqCatalogPointList" name="maEqCatalogPointListForm"
 *                input="/dream/asset/categ/list/maEqCatalogPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCatalogPointList" path="/dream/asset/categ/list/maEqCatalogPointList.jsp"
 *                        redirect="false"
 */
public class MaEqCatalogPointListAction extends AuthAction
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
        MaEqCatalogPointListForm maEqCatalogPointListForm = (MaEqCatalogPointListForm) form;
        
        switch (maEqCatalogPointListForm.getStrutsAction())
        {
            case MaEqCatalogPointListAction.EQ_MSTR_PMWORK_LIST_FIND:
            	findList(request,response, maEqCatalogPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCatalogPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqCatalogPointListForm.getListId(), maEqCatalogPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCatalogPointListAction.EQ_MSTR_PMWORK_LIST_DELETE:
            	deleteList(request, maEqCatalogPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCatalogPointListAction.BASE_GRID_EXPORT:
            	findList(request,response, maEqCatalogPointListForm, true);
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
     * @author  euna0207
     * @version $Id: MaEqCatalogPointListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCatalogPointListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaEqCatalogPointListForm maEqCatalogPointListForm, boolean excelExport) throws Exception
    {

        MaEqCatalogPointListService maEqCatalogPointListService = (MaEqCatalogPointListService) getBean("maEqCatalogPointListService");

        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCatalogPointListForm.getMaEqCatalogCommonDTO();
        MaEqCatalogPointListDTO maEqCatalogPointListDTO = maEqCatalogPointListForm.getMaEqCatalogPointListDTO();
        
        //Paging
        maEqCatalogCommonDTO.setIsLoadMaxCount("Y".equals(maEqCatalogPointListForm.getIsLoadMaxCount())?true:false);
        maEqCatalogCommonDTO.setFirstRow(maEqCatalogPointListForm.getFirstRow());
        maEqCatalogCommonDTO.setOrderBy(maEqCatalogPointListForm.getOrderBy());
        maEqCatalogCommonDTO.setDirection(maEqCatalogPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqCatalogPointListService.findList(maEqCatalogCommonDTO, maEqCatalogPointListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqCatalogPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqCatalogPointListService.findTotalCount(maEqCatalogCommonDTO, maEqCatalogPointListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response, maEqCatalogPointListForm.getListId(), maEqCatalogPointListForm.getCurrentPageId(), maEqCatalogPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  euna0207
     * @version $Id: MaEqCatalogPointListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCatalogPointListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaEqCatalogPointListForm maEqCatalogPointListForm) throws Exception
    {
    	MaEqCatalogPointListService maEqCatalogPointListService = (MaEqCatalogPointListService) getBean("maEqCatalogPointListService");
        
    	String[] deleteRows = maEqCatalogPointListForm.getDeleteRows();
    
    	maEqCatalogPointListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}