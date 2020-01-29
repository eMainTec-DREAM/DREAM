package dream.consult.program.page.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.form.ConsultPgmPgLinkedFuncListForm;
import dream.consult.program.page.service.ConsultPgmPgLinkedFuncListService;

/**
 * 화면별 연결기능 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/consultPgmPgLinkedFuncList" name="consultPgmPgLinkedFuncListForm"
 *                input="/dream/consult/program/page/consultPgmPgLinkedFuncList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmPgLinkedFuncList" path="/dream/consult/program/page/consultPgmPgLinkedFuncList.jsp"
 *                        redirect="false"
 */
public class ConsultPgmPgLinkedFuncListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_FIELD_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PG_FIELD_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmPgLinkedFuncListForm consultPgmPgLinkedFuncListForm = (ConsultPgmPgLinkedFuncListForm) form;
        
        switch (consultPgmPgLinkedFuncListForm.getStrutsAction())
        {
        
            case ConsultPgmPgLinkedFuncListAction.PG_FIELD_LIST_FIND:
                findFieldList(request,response, consultPgmPgLinkedFuncListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmPgLinkedFuncListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, consultPgmPgLinkedFuncListForm.getListId(), consultPgmPgLinkedFuncListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmPgLinkedFuncListAction.PG_FIELD_LIST_DELETE:
            	deleteFieldList(request,consultPgmPgLinkedFuncListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmPgLinkedFuncListAction.BASE_GRID_EXPORT:
            	findFieldList(request,response, consultPgmPgLinkedFuncListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param consultPgmPgLinkedFuncListForm
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     * @throws Exception
     */
    private void findFieldList(HttpServletRequest request,HttpServletResponse response, ConsultPgmPgLinkedFuncListForm consultPgmPgLinkedFuncListForm, boolean excelExport) throws Exception 
    {
        ConsultPgmPgLinkedFuncListService consultPgmPgLinkedFuncListService = (ConsultPgmPgLinkedFuncListService) getBean("consultPgmPgLinkedFuncListService");
        
        MaPgMngCommonDTO maPgMngCommonDTO = consultPgmPgLinkedFuncListForm.getMaPgMngCommonDTO();
        ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO = consultPgmPgLinkedFuncListForm.getConsultPgmPgLinkedFuncListDTO();
        maPgMngCommonDTO.setUserLang(getUser(request).getLangId());
        
        
        //Paging
        maPgMngCommonDTO.setIsLoadMaxCount("Y".equals(consultPgmPgLinkedFuncListForm.getIsLoadMaxCount())?true:false);
        maPgMngCommonDTO.setFirstRow(consultPgmPgLinkedFuncListForm.getFirstRow());
        maPgMngCommonDTO.setOrderBy(consultPgmPgLinkedFuncListForm.getOrderBy());
        maPgMngCommonDTO.setDirection(consultPgmPgLinkedFuncListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = consultPgmPgLinkedFuncListService.findFieldList(maPgMngCommonDTO, consultPgmPgLinkedFuncListDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultPgmPgLinkedFuncListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmPgLinkedFuncListService.findTotalCount(maPgMngCommonDTO,consultPgmPgLinkedFuncListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,consultPgmPgLinkedFuncListForm.getListId(),consultPgmPgLinkedFuncListForm.getCurrentPageId(), consultPgmPgLinkedFuncListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param consultPgmPgLinkedFuncListForm
     * @throws Exception
     */
    private void deleteFieldList(HttpServletRequest request, ConsultPgmPgLinkedFuncListForm consultPgmPgLinkedFuncListForm) throws Exception
    {
    	ConsultPgmPgLinkedFuncListService consultPgmPgLinkedFuncListService = (ConsultPgmPgLinkedFuncListService) getBean("consultPgmPgLinkedFuncListService");
        
    	String[] deleteRows = consultPgmPgLinkedFuncListForm.getDeleteRows();
    
    	consultPgmPgLinkedFuncListService.deleteFieldList(deleteRows);
        
    	setAjaxStatus(request);
    }
}