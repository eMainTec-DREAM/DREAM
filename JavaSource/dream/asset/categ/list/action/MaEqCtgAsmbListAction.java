package dream.asset.categ.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.JsonUtil;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.asset.categ.list.form.MaEqCtgAsmbListForm;
import dream.asset.categ.list.service.MaEqCtgAsmbListService;

/**
 * 설비종류별 작업부위 목록
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqCtgAsmbList" name="maEqCtgAsmbListForm"
 *                input="/dream/asset/categ/list/maEqCtgAsmbList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgAsmbList" path="/dream/asset/categ/list/maEqCtgAsmbList.jsp"
 *                        redirect="false"
 */
public class MaEqCtgAsmbListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_CTG_ASMB_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_CTG_ASMB_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCtgAsmbListForm maEqCtgAsmbListForm = (MaEqCtgAsmbListForm) form;
        
        switch (maEqCtgAsmbListForm.getStrutsAction())
        {
        
            case MaEqCtgAsmbListAction.EQ_CTG_ASMB_LIST_FIND:
                findAsmbList(request,response, maEqCtgAsmbListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCtgAsmbListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqCtgAsmbListForm.getListId(), maEqCtgAsmbListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCtgAsmbListAction.EQ_CTG_ASMB_LIST_DELETE:
            	deleteAsmbList(request,maEqCtgAsmbListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgAsmbListAction.BASE_GRID_EXPORT:
            	findAsmbList(request,response, maEqCtgAsmbListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqCtgAsmbList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqCtgAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgAsmbListForm
     * @throws Exception
     */
    private void findAsmbList(HttpServletRequest request,HttpServletResponse response, MaEqCtgAsmbListForm maEqCtgAsmbListForm, boolean excelExport) throws Exception
    {
        MaEqCtgAsmbListService maEqCtgAsmbListService = (MaEqCtgAsmbListService) getBean("maEqCtgAsmbListService");
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgAsmbListForm.getMaEqCatalogCommonDTO();
        MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = maEqCtgAsmbListForm.getMaEqCtgAsmbListDTO();
        String eqCtgId = maEqCtgAsmbListForm.getMaEqCatalogCommonDTO().getEqCtgId();
        
        //Paging
        maEqCatalogCommonDTO.setIsLoadMaxCount("Y".equals(maEqCtgAsmbListForm.getIsLoadMaxCount())?true:false);
        maEqCatalogCommonDTO.setFirstRow(maEqCtgAsmbListForm.getFirstRow());
        maEqCatalogCommonDTO.setOrderBy(maEqCtgAsmbListForm.getOrderBy());
        maEqCatalogCommonDTO.setDirection(maEqCtgAsmbListForm.getDirection());
        
        
        List resultList = JsonUtil.convertorTreeMap(maEqCtgAsmbListService.findAsmbList(maEqCatalogCommonDTO, maEqCtgAsmbListDTO, getUser(request)), "0", "EQCTGASMBID", "PEQCTGASMBID", "ORDNO");
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maEqCtgAsmbListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqCtgAsmbListService.findTotalCount(maEqCatalogCommonDTO, maEqCtgAsmbListDTO, getUser(request));

        if(excelExport) super.makeTreeGridExport(resultList, request, response,maEqCtgAsmbListForm.getListId(),maEqCtgAsmbListForm.getCurrentPageId(), maEqCtgAsmbListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqCtgAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgAsmbListForm
     * @throws Exception
     */
    private void deleteAsmbList(HttpServletRequest request, MaEqCtgAsmbListForm maEqCtgAsmbListForm) throws Exception
    {
    	MaEqCtgAsmbListService maEqCtgAsmbListService = (MaEqCtgAsmbListService) getBean("maEqCtgAsmbListService");
        
    	String[] deleteRows = maEqCtgAsmbListForm.getDeleteRows();
    
    	maEqCtgAsmbListService.deleteAsmbList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}