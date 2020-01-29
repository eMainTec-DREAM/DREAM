package dream.consult.comp.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.form.ConsultCompUserListForm;
import dream.consult.comp.user.service.ConsultCompUserListService;

/**
 * CompUser Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: ConsultCompUserListAction.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/consultCompUserList" name="consultCompUserListForm"
 *                input="/dream/consult/comp/user/consultCompUserList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompUserList" path="/dream/consult/comp/user/consultCompUserList.jsp"
 *                        redirect="false"
 */
public class ConsultCompUserListAction extends ConsultAuthAction
{
    
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 1002;
    /** 비밀번호리셋 */
    public static final int RESET_PW        =1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompUserListForm consultCompUserListForm = (ConsultCompUserListForm) form;
        
        switch (consultCompUserListForm.getStrutsAction())
        {
            case ConsultCompUserListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompUserListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompUserListAction.LIST_FIND:
                findList(request, response, consultCompUserListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultCompUserListAction.LIST_DELETE:
                deleteList(request, consultCompUserListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompUserListAction.RESET_PW:
                resetPw(request, consultCompUserListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;  
            case ConsultCompUserListAction.BASE_GRID_EXPORT:
                findList(request, response, consultCompUserListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompUserList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompUserListForm consultCompUserListForm) throws IOException
    {
        super.setHeader(request, response, consultCompUserListForm.getListId(), consultCompUserListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultCompUserListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompUserListForm consultCompUserListForm, boolean excelExport) throws Exception
    {
        ConsultCompUserListService consultCompUserListService = (ConsultCompUserListService) getBean("consultCompUserListService");
        ConsultCompUserCommonDTO consultCompUserCommonDTO = consultCompUserListForm.getConsultCompUserCommonDTO();
      
        //Paging
        consultCompUserCommonDTO.setIsLoadMaxCount("Y".equals(consultCompUserListForm.getIsLoadMaxCount())?true:false);
        consultCompUserCommonDTO.setFirstRow(consultCompUserListForm.getFirstRow());
        consultCompUserCommonDTO.setOrderBy(consultCompUserListForm.getOrderBy());
        consultCompUserCommonDTO.setDirection(consultCompUserListForm.getDirection());
        
        List resultList = consultCompUserListService.findCompUserList(consultCompUserCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompUserListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompUserListService.findTotalCount(consultCompUserCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompUserListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUserListForm
     */
    private void deleteList(HttpServletRequest request, ConsultCompUserListForm consultCompUserListForm) throws Exception
    {
        ConsultCompUserListService consultCompUserListService = (ConsultCompUserListService) getBean("consultCompUserListService");
        ConsultCompUserCommonDTO consultCompUserCommonDTO = consultCompUserListForm.getConsultCompUserCommonDTO();
        
        String[] deleteRows = consultCompUserListForm.getDeleteRows();
        
        consultCompUserListService.deleteCompUserList(deleteRows, getUser(request), consultCompUserCommonDTO);
        setAjaxStatus(request);
    }
    
    /**
     * RESET PASSWORD
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUserListForm
     */
    private void resetPw(HttpServletRequest request, ConsultCompUserListForm consultCompUserListForm) throws Exception
    {
        ConsultCompUserListService consultCompUserListService = (ConsultCompUserListService) getBean("consultCompUserListService");
        String[] resetRows = consultCompUserListForm.getDeleteRows();
        String[] userNo = consultCompUserListForm.getDeleteRowsExt();
        String[] compNo = consultCompUserListForm.getDeleteRowsExt1();
        
        consultCompUserListService.resetCompUserPw(resetRows, userNo, compNo);
        setAjaxStatus(request);
    }
    
    
}