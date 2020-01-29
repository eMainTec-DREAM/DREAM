package dream.asset.categ.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.JsonUtil;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.form.MaEqCatalogListForm;
import dream.asset.categ.list.service.MaEqCatalogListService;

/**
 * 설비종류 - 목록 action
 * @author  kim21017
 * @version $Id: MaEqCatalogListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqCatalogList" name="maEqCatalogListForm"
 *                input="/dream/asset/categ/list/maEqCatalogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCatalogList" path="/dream/asset/categ/list/maEqCatalogList.jsp"
 *                        redirect="false"
 */
public class MaEqCatalogListAction extends AuthAction
{
    /** 조회 */
    public static final int EQ_CATALOG_LIST_FIND 	= 8001;
    /** 삭제 */
    public static final int EQ_CATALOG_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCatalogListForm maEqCatalogListForm = (MaEqCatalogListForm) form;
        
        super.updateAudit(maEqCatalogListForm.getMaEqCatalogCommonDTO().getAuditKey()==""?maEqCatalogListForm.getMaEqCatalogCommonDTO().getAuditKey():maEqCatalogListForm.getMaEqCatalogCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqCatalogListForm.getStrutsAction())
        {
            case MaEqCatalogListAction.EQ_CATALOG_LIST_FIND:
            	findEqCatalogList(request, maEqCatalogListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCatalogListAction.BASE_SET_HEADER:
                setHeader(request, response, maEqCatalogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqCatalogListAction.EQ_CATALOG_LIST_DELETE:
            	deleteEqCatalog(request, maEqCatalogListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqCatalogListAction.BASE_GRID_EXPORT:
            	findEqCatalogList(request, maEqCatalogListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqCatalogList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEqCatalogListForm maEqCatalogListForm) throws IOException
    {
        super.setHeader(request, response, maEqCatalogListForm.getListId(), maEqCatalogListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqCatalogListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCatalogListForm
     * @throws Exception
     */
    private void findEqCatalogList(HttpServletRequest request, MaEqCatalogListForm maEqCatalogListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaEqCatalogListService maEqCatalogListService = (MaEqCatalogListService) getBean("maEqCatalogListService");        

    	MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCatalogListForm.getMaEqCatalogCommonDTO();
    	maEqCatalogCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = JsonUtil.convertorTreeMap(maEqCatalogListService.findEqCatalogList(maEqCatalogCommonDTO,getUser(request)), "0", "eqCtgId", "pEqCtgId", "ORDNO");
        
        if(excelExport) super.makeTreeGridExport(resultList, request, response,maEqCatalogListForm.getListId(),maEqCatalogListForm.getCurrentPageId(), maEqCatalogListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, maEqCatalogListForm.getListId());
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaEqCatalogListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogListForm
     * @param request
     */
    private void deleteEqCatalog(HttpServletRequest request, MaEqCatalogListForm maEqCatalogListForm) throws Exception
    {
    	MaEqCatalogListService maEqCatalogListService = (MaEqCatalogListService) getBean("maEqCatalogListService");
    	String[] deleteRows = maEqCatalogListForm.getDeleteRows();    // sheet 내역
        
        maEqCatalogListService.deleteEqCatalog(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
