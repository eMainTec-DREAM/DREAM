package dream.org.emp.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.form.MaEmpListForm;
import dream.org.emp.service.MaEmpListService;

/**
 * 사원 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maEmpList" name="maEmpListForm"
 *                input="/dream/org/emp/maEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEmpList" path="/dream/org/emp/maEmpList.jsp"
 *                        redirect="false"
 */
public class MaEmpListAction extends AuthAction
{
    /** 조회 */
    public static final int EMP_LIST_FIND   = 8001;
    /** 삭제 */
    public static final int EMP_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEmpListForm maEmpListForm = (MaEmpListForm) form;
        
        super.updateAudit(maEmpListForm.getMaEmpCommonDTO().getAuditKey()==""?maEmpListForm.getMaEmpCommonDTO().getAuditKey():maEmpListForm.getMaEmpCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEmpListForm.getStrutsAction())
        {
            case MaEmpListAction.BASE_SET_HEADER:
                setHeader(request, response, maEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEmpListAction.EMP_LIST_FIND:
                findList(request, response, maEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;      
            case MaEmpListAction.EMP_LIST_DELETE:
                deleteList(request, maEmpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                
            case MaEmpListAction.BASE_GRID_EXPORT:
            	findList(request, response, maEmpListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEmpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEmpListForm maEmpListForm) throws IOException
    {
        super.setHeader(request, response, maEmpListForm.getListId(), maEmpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maEmpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaEmpListForm maEmpListForm, boolean excelExport)  throws IOException
    {
    	MaEmpListService maEmpListService = (MaEmpListService) getBean("maEmpListService");        

    	MaEmpCommonDTO maEmpCommonDTO = maEmpListForm.getMaEmpCommonDTO();
    	
    	// Paging
    	maEmpCommonDTO.setIsLoadMaxCount("Y".equals(maEmpListForm.getIsLoadMaxCount())?true:false);
    	maEmpCommonDTO.setFirstRow(maEmpListForm.getFirstRow());
    	maEmpCommonDTO.setOrderBy(maEmpListForm.getOrderBy());
    	maEmpCommonDTO.setDirection(maEmpListForm.getDirection());
        
    	//리스트를 조회한다.
        List resultList = maEmpListService.findEmpList(maEmpCommonDTO, getUser(request));

        // Paging
        String totalCount = "";
        if(Integer.parseInt(maEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEmpListService.findTotalCount(maEmpCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maEmpListForm.getListId(),maEmpListForm.getCurrentPageId(), maEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaEmpListForm maEmpListForm) throws Exception
    {
        MaEmpListService maEmpListService = (MaEmpListService) getBean("maEmpListService");        

        String[] deleteRows = maEmpListForm.getDeleteRows();    // sheet 내역
        
        maEmpListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
