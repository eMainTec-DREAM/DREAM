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
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;
import dream.asset.categ.list.form.MaEqCtgPartListForm;
import dream.asset.categ.list.service.MaEqCtgPartListService;

/**
 * 설비종류별 부품 목록
 * @author  kim21017
 * @version $Id: MaEqCtgPartListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqCtgPartList" name="maEqCtgPartListForm"
 *                input="/dream/asset/categ/list/maEqCtgPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgPartList" path="/dream/asset/categ/list/maEqCtgPartList.jsp"
 *                        redirect="false"
 */
public class MaEqCtgPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_CTG_PART_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_CTG_PART_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int EQ_CTG_PART_LIST_INPUT          = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCtgPartListForm maEqCtgPartListForm = (MaEqCtgPartListForm) form;
        
        switch (maEqCtgPartListForm.getStrutsAction())
        {
        
            case MaEqCtgPartListAction.EQ_CTG_PART_LIST_FIND:
                findPartList(request,response, maEqCtgPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCtgPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqCtgPartListForm.getListId(), maEqCtgPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCtgPartListAction.EQ_CTG_PART_LIST_DELETE:
            	deletePartList(request,maEqCtgPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgPartListAction.EQ_CTG_PART_LIST_INPUT:
                insertPartList(request,maEqCtgPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqCtgPartListAction.BASE_GRID_EXPORT:
            	findPartList(request,response, maEqCtgPartListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqCtgPartList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqCtgPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgPartListForm
     * @throws Exception
     */
    private void findPartList(HttpServletRequest request,HttpServletResponse response, MaEqCtgPartListForm maEqCtgPartListForm, boolean excelExport) throws Exception
    {
        MaEqCtgPartListService maEqCtgPartListService = (MaEqCtgPartListService) getBean("maEqCtgPartListService");
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgPartListForm.getMaEqCatalogCommonDTO();
        MaEqCtgPartListDTO maEqCtgPartListDTO = maEqCtgPartListForm.getMaEqCtgPartListDTO();
        
        //Paging
        maEqCtgPartListDTO.setIsLoadMaxCount("Y".equals(maEqCtgPartListForm.getIsLoadMaxCount())?true:false);
        maEqCtgPartListDTO.setFirstRow(maEqCtgPartListForm.getFirstRow());
        maEqCtgPartListDTO.setOrderBy(maEqCtgPartListForm.getOrderBy());
        maEqCtgPartListDTO.setDirection(maEqCtgPartListForm.getDirection());

        User user = getUser(request);
        
        List resultList = maEqCtgPartListService.findPartList(maEqCatalogCommonDTO, maEqCtgPartListDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maEqCtgPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqCtgPartListService.findTotalCount(maEqCtgPartListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maEqCtgPartListForm.getListId(),maEqCtgPartListForm.getCurrentPageId(), maEqCtgPartListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
            //super.makeTreeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqCtgPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgPartListForm
     * @throws Exception
     */
    private void deletePartList(HttpServletRequest request, MaEqCtgPartListForm maEqCtgPartListForm) throws Exception
    {
    	MaEqCtgPartListService maEqCtgPartListService = (MaEqCtgPartListService) getBean("maEqCtgPartListService");
        
    	String[] deleteRows = maEqCtgPartListForm.getDeleteRows();
    
    	maEqCtgPartListService.deletePartList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqCtgPartListForm
     * @param request
     */
    private void insertPartList(HttpServletRequest request, MaEqCtgPartListForm maEqCtgPartListForm) throws Exception
    {
        MaEqCtgPartListService maEqCtgPartListService = (MaEqCtgPartListService) getBean("maEqCtgPartListService");
        
        MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = maEqCtgPartListForm.getMaEqCtgPartDetailDTO();
        
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgPartListForm.getMaEqCatalogCommonDTO();
        maEqCtgPartDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqCatalogCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqCtgPartListService.insertPartList(maEqCtgPartDetailDTO, maEqCatalogCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}