package dream.mgr.user.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.form.MaUserListForm;
import dream.mgr.user.service.MaUserListService;

/**
 * 사용자 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maUserList" name="maUserListForm"
 *                input="/dream/mgr/user/maUserList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserList" path="/dream/mgr/user/maUserList.jsp"
 *                        redirect="false"
 */
public class MaUserListAction extends AuthAction
{
    /** 조회 */
    public static final int USER_LIST_FIND      = 8001;
    /** 삭제 */
    public static final int USER_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserListForm maUserListForm = (MaUserListForm) form;
        
        super.updateAudit(maUserListForm.getMaUserCommonDTO().getAuditKey()==""?maUserListForm.getMaUserCommonDTO().getAuditKey():maUserListForm.getMaUserCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUserListForm.getStrutsAction())
        {
            case MaUserListAction.BASE_SET_HEADER:
                setHeader(request, response, maUserListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserListAction.USER_LIST_FIND:
                findList(request, response, maUserListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MaUserListAction.USER_LIST_DELETE:
                deleteList(request, maUserListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaUserListAction.BASE_GRID_EXPORT:
            	findList(request, response, maUserListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaUserListForm maUserListForm) throws IOException
    {
        super.setHeader(request, response, maUserListForm.getListId(), maUserListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maUserListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaUserListForm maUserListForm, boolean excelExport)  throws IOException
    {
    	MaUserListService maUserListService = (MaUserListService) getBean("maUserListService");        

    	MaUserCommonDTO maUserCommonDTO = maUserListForm.getMaUserCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maUserCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	maUserCommonDTO.setCompNo((getUser(request).getCompNo()));
    	maUserCommonDTO.setIsLoadMaxCount("Y".equals(maUserListForm.getIsLoadMaxCount())?true:false);
    	maUserCommonDTO.setFirstRow(maUserListForm.getFirstRow());
    	maUserCommonDTO.setOrderBy(maUserListForm.getOrderBy());
    	maUserCommonDTO.setDirection(maUserListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maUserListService.findUserList(maUserCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maUserListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maUserListService.findTotalCount(maUserCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maUserListForm.getListId(),maUserListForm.getCurrentPageId(), maUserListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaUserListForm maUserListForm) throws Exception
    {
        MaUserListService maUserListService = (MaUserListService) getBean("maUserListService");        

        String[] deleteRows = maUserListForm.getDeleteRows();    // sheet 내역
        
        maUserListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
