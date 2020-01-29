package dream.doc.data.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.form.MaDocCntrCdListForm;
import dream.doc.data.service.MaDocCntrCdListService;

/**
 * 일반자료실 - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maDocCntrCdList" name="maDocCntrCdListForm"
 *                input="/dream/doc/data/maDocCntrCdList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDocCntrCdList" path="/dream/doc/data/maDocCntrCdList.jsp"
 *                        redirect="false"
 */
public class MaDocCntrCdListAction extends AuthAction
{
    /** 조회 */
    public static final int DOCCNTR_CD_LIST_FIND    = 1001;
    /** 삭제 */
    public static final int DOCCNTR_CD_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocCntrCdListForm maDocCntrCdListForm = (MaDocCntrCdListForm) form;
        
        super.updateAudit(maDocCntrCdListForm.getMaDocCntrCdCommonDTO().getAuditKey()==""?maDocCntrCdListForm.getMaDocCntrCdCommonDTO().getAuditKey():maDocCntrCdListForm.getMaDocCntrCdCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDocCntrCdListForm.getStrutsAction())
        {
            case MaDocCntrCdListAction.BASE_SET_HEADER:
                setHeader(request, response, maDocCntrCdListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocCntrCdListAction.DOCCNTR_CD_LIST_FIND:
                findList(request, response, maDocCntrCdListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaDocCntrCdListAction.DOCCNTR_CD_LIST_DELETE:
            	deleteList(request, maDocCntrCdListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaDocCntrCdListAction.BASE_GRID_EXPORT:
            	findList(request, response, maDocCntrCdListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maDocCntrCdList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaDocCntrCdListForm maDocCntrCdListForm) throws IOException
    {
        super.setHeader(request, response, maDocCntrCdListForm.getListId(), maDocCntrCdListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maDocCntrCdListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaDocCntrCdListForm maDocCntrCdListForm, boolean excelExport) throws IOException
    {
    	MaDocCntrCdListService maDocCntrCdListService = (MaDocCntrCdListService) getBean("maDocCntrCdListService");        

    	MaDocCntrCdCommonDTO maDocCntrCdCommonDTO = maDocCntrCdListForm.getMaDocCntrCdCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maDocCntrCdCommonDTO.setFilterCompNo((getUser(request).getCompNo()));

    	// Paging
    	maDocCntrCdCommonDTO.setIsLoadMaxCount("Y".equals(maDocCntrCdListForm.getIsLoadMaxCount())?true:false);
    	maDocCntrCdCommonDTO.setFirstRow(maDocCntrCdListForm.getFirstRow());
    	maDocCntrCdCommonDTO.setOrderBy(maDocCntrCdListForm.getOrderBy());
    	maDocCntrCdCommonDTO.setDirection(maDocCntrCdListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maDocCntrCdListService.findList(maDocCntrCdCommonDTO, getUser(request));

        // Paging
        String totalCount = "";
        if(Integer.parseInt(maDocCntrCdListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maDocCntrCdListService.findTotalCount(maDocCntrCdCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maDocCntrCdListForm.getListId(),maDocCntrCdListForm.getCurrentPageId(), maDocCntrCdListForm.getFileName());
        // 조회한 List 를 form에 셋팅한다.
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaDocCntrCdListForm maDocCntrCdListForm) throws Exception
    {
    	MaDocCntrCdListService maDocCntrCdListService = (MaDocCntrCdListService) getBean("maDocCntrCdListService");        

        String[] deleteRows = maDocCntrCdListForm.getDeleteRows();    // sheet 내역
        
        maDocCntrCdListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
