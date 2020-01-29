package dream.asset.loc.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;
import dream.asset.loc.list.form.MaEqLocListForm;
import dream.asset.loc.list.service.MaEqLocListService;

/**
 * 설비위치 - 목록 
 * @author  kim21017
 * @version $Id: MaEqLocListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqLocList" name="maEqLocListForm"
 *                input="/dream/asset/loc/list/maEqLocList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqLocList" path="/dream/asset/loc/list/maEqLocList.jsp"
 *                        redirect="false"
 */
public class MaEqLocListAction extends AuthAction
{
    /** 조회 */
    public static final int EQ_LOC_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int EQ_LOC_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqLocListForm maEqLocListForm = (MaEqLocListForm) form;
        
        super.updateAudit(maEqLocListForm.getMaEqLocCommonDTO().getAuditKey()==""?maEqLocListForm.getMaEqLocCommonDTO().getAuditKey():maEqLocListForm.getMaEqLocCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maEqLocListForm.getStrutsAction())
        {
            case MaEqLocListAction.EQ_LOC_LIST_FIND:
                findEqLocList(request, maEqLocListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqLocListAction.BASE_SET_HEADER:
                setHeader(request, response, maEqLocListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqLocListAction.EQ_LOC_LIST_DELETE:
            	deleteEqLoc(request, maEqLocListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqLocListAction.BASE_GRID_EXPORT:
            	findEqLocList(request, maEqLocListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqLocList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEqLocListForm maEqLocListForm) throws IOException
    {
        super.setHeader(request, response, maEqLocListForm.getListId(),maEqLocListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqLocListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqLocListForm
     * @param response
     * @throws Exception
     */
    private void findEqLocList(HttpServletRequest request, MaEqLocListForm maEqLocListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaEqLocListService maEqLocListService = (MaEqLocListService) getBean("maEqLocListService");        

    	MaEqLocCommonDTO maEqLocCommonDTO = maEqLocListForm.getMaEqLocCommonDTO();
    	maEqLocCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maEqLocListService.findEqLocList(maEqLocCommonDTO,getUser(request), excelExport);

        //super.makeTreeJsonResult(resultList, request, response, maEqLocListForm.getListId());
        
        if(excelExport) super.makeTreeGridExport(resultList, request, response,maEqLocListForm.getListId(),maEqLocListForm.getCurrentPageId(), maEqLocListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, maEqLocListForm.getListId());
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaEqLocListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqLocListForm
     */
    private void deleteEqLoc(HttpServletRequest request, MaEqLocListForm maEqLocListForm) throws Exception
    {
    	MaEqLocListService maEqLocListService = (MaEqLocListService) getBean("maEqLocListService");        

    	String[] deleteRows = maEqLocListForm.getDeleteRows();
    	MaEqLocCommonDTO maEqLocCommonDTO = maEqLocListForm.getMaEqLocCommonDTO();
    	maEqLocCommonDTO.setCompNo(getUser(request).getCompNo());
    	maEqLocListService.deleteEqLoc(deleteRows,maEqLocCommonDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}
