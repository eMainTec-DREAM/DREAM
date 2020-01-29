package dream.asset.categ.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;
import dream.asset.categ.list.form.MaEqCtgSpecListForm;
import dream.asset.categ.list.service.MaEqCtgSpecListService;

/**
 * 설비종류별 표준제원 목록
 * @author  syyang
 * @version $Id: MaEqCtgSpecListAction.java,v 1.0 2015/12/04 09:09:30 syyang Exp $
 * @since   1.0
 * @struts:action path="/maEqCtgSpecList" name="maEqCtgSpecListForm"
 *                input="/dream/asset/categ/list/maEqCtgSpecList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgSpecList" path="/dream/asset/categ/list/maEqCtgSpecList.jsp"
 *                        redirect="false"
 */
public class MaEqCtgSpecListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_CTG_SPEC_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_CTG_SPEC_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCtgSpecListForm maEqCtgSpecListForm = (MaEqCtgSpecListForm) form;
        
        switch (maEqCtgSpecListForm.getStrutsAction())
        {
        
            case MaEqCtgSpecListAction.EQ_CTG_SPEC_LIST_FIND:
                findSpecList(request,response, maEqCtgSpecListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCtgSpecListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqCtgSpecListForm.getListId(), maEqCtgSpecListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCtgSpecListAction.EQ_CTG_SPEC_LIST_DELETE:
            	deleteSpecList(request,maEqCtgSpecListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgSpecListAction.BASE_GRID_EXPORT:
            	findSpecList(request,response, maEqCtgSpecListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqCtgSpecList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqCtgSpecListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgSpecListForm
     * @throws Exception
     */
    private void findSpecList(HttpServletRequest request,HttpServletResponse response, MaEqCtgSpecListForm maEqCtgSpecListForm, boolean excelExport) throws Exception
    {
        MaEqCtgSpecListService maEqCtgSpecListService = (MaEqCtgSpecListService) getBean("maEqCtgSpecListService");
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgSpecListForm.getMaEqCatalogCommonDTO();
        MaEqCtgSpecListDTO maEqCtgSpecListDTO = maEqCtgSpecListForm.getMaEqCtgSpecListDTO();
        String eqCtgId = maEqCtgSpecListForm.getMaEqCatalogCommonDTO().getEqCtgId();
        
        //Paging
        maEqCatalogCommonDTO.setIsLoadMaxCount("Y".equals(maEqCtgSpecListForm.getIsLoadMaxCount())?true:false);
        maEqCatalogCommonDTO.setFirstRow(maEqCtgSpecListForm.getFirstRow());
        maEqCatalogCommonDTO.setOrderBy(maEqCtgSpecListForm.getOrderBy());
        maEqCatalogCommonDTO.setDirection(maEqCtgSpecListForm.getDirection());
        
        
        List resultList = maEqCtgSpecListService.findSpecList(maEqCatalogCommonDTO, maEqCtgSpecListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maEqCtgSpecListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqCtgSpecListService.findTotalCount(maEqCatalogCommonDTO, maEqCtgSpecListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maEqCtgSpecListForm.getListId(),maEqCtgSpecListForm.getCurrentPageId(), maEqCtgSpecListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqCtgSpecListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgSpecListForm
     * @throws Exception
     */
    private void deleteSpecList(HttpServletRequest request, MaEqCtgSpecListForm maEqCtgSpecListForm) throws Exception
    {
    	MaEqCtgSpecListService maEqCtgSpecListService = (MaEqCtgSpecListService) getBean("maEqCtgSpecListService");
        
    	String[] deleteRows = maEqCtgSpecListForm.getDeleteRows();
    
    	maEqCtgSpecListService.deleteSpecList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}