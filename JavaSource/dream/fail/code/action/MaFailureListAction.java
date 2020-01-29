package dream.fail.code.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.form.MaFailureListForm;
import dream.fail.code.service.MaFailureListService;

/**
 * 고장코드 - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maFailureList" name="maFailureListForm"
 *                input="/dream/fail/code/maFailureList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maFailureList" path="/dream/fail/code/maFailureList.jsp"
 *                        redirect="false"
 */
public class MaFailureListAction extends AuthAction
{
    /** 조회 */
    public static final int FAILURE_LIST_FIND   = 8001;
    /** 삭제 */
    public static final int FAILURE_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaFailureListForm maFailureListForm = (MaFailureListForm) form;
        
        super.updateAudit(maFailureListForm.getMaFailureCommonDTO().getAuditKey()==""?maFailureListForm.getMaFailureCommonDTO().getAuditKey():maFailureListForm.getMaFailureCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maFailureListForm.getStrutsAction())
        {
            case MaFailureListAction.BASE_SET_HEADER:
                setHeader(request, response, maFailureListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaFailureListAction.FAILURE_LIST_FIND:
                findList(request, response, maFailureListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaFailureListAction.FAILURE_LIST_DELETE:
            	deleteList(request, maFailureListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaFailureListAction.BASE_GRID_EXPORT:
            	findList(request, response, maFailureListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maFailureList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaFailureListForm maFailureListForm) throws IOException
    {
        super.setHeader(request, response, maFailureListForm.getListId(), maFailureListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maFailureListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaFailureListForm maFailureListForm, boolean excelExport) throws IOException
    {
    	MaFailureListService maFailureListService = (MaFailureListService) getBean("maFailureListService");        

    	MaFailureCommonDTO maFailureCommonDTO = maFailureListForm.getMaFailureCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maFailureCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	
    	//Paging
    	maFailureCommonDTO.setIsLoadMaxCount("Y".equals(maFailureListForm.getIsLoadMaxCount())?true:false);
    	maFailureCommonDTO.setFirstRow(maFailureListForm.getFirstRow());
    	maFailureCommonDTO.setOrderBy(maFailureListForm.getOrderBy());
    	maFailureCommonDTO.setDirection(maFailureListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maFailureListService.findList(maFailureCommonDTO,getUser(request));
      
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maFailureListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maFailureListService.findTotalCount(maFailureCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maFailureListForm.getListId(),maFailureListForm.getCurrentPageId(), maFailureListForm.getFileName());
        // 조회한 List 를 form에 셋팅한다.
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaFailureListForm maFailureListForm) throws Exception
    {
    	MaFailureListService maFailureListService = (MaFailureListService) getBean("maFailureListService");        

        String[] deleteRows = maFailureListForm.getDeleteRows();    // sheet 내역
        
        maFailureListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
