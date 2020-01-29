package dream.consult.program.guide.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.form.ConsultPgmGuideListForm;
import dream.consult.program.guide.service.ConsultPgmGuideListService;

/**
 * Guide Page - List Action
 * 
 * @author kim21017
 * @version $Id: ConsultPgmGuideListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultPgmGuideList" name="consultPgmGuideListForm"
 *                input="/dream/consult/program/guide/consultPgmGuideList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultDashBoardGuide" name="consultPgmGuideListForm"
 *                input="/dream/consult/program/guide/consultDashBoardGuide.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmGuideList" path="/dream/consult/program/guide/consultPgmGuideList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="consultDashBoardGuide" path="/dream/consult/program/guide/consultDashBoardGuide.jsp"
 *                        redirect="false"
 */

public class ConsultPgmGuideListAction extends ConsultAuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmGuideListForm consultPgmGuideListForm = (ConsultPgmGuideListForm) form;
        
        switch (consultPgmGuideListForm.getStrutsAction())
        {
            case ConsultPgmGuideListAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmGuideListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmGuideListAction.LIST_FIND:
                findList(request, response, consultPgmGuideListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultPgmGuideListAction.LIST_DELETE:
            	deleteList(request, consultPgmGuideListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultPgmGuideListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultPgmGuideListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("consultPgmGuideList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideListForm consultPgmGuideListForm) throws IOException
    {
        super.setHeader(request, response, consultPgmGuideListForm.getListId(), consultPgmGuideListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultPgmGuideListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideListForm consultPgmGuideListForm, boolean excelExport) throws Exception
    {
    	ConsultPgmGuideListService consultPgmGuideListService = (ConsultPgmGuideListService) getBean("consultPgmGuideListService");
    	ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO = consultPgmGuideListForm.getConsultPgmGuideCommonDTO();

    	//Paging
    	consultPgmGuideCommonDTO.setIsLoadMaxCount("Y".equals(consultPgmGuideListForm.getIsLoadMaxCount())?true:false);
    	consultPgmGuideCommonDTO.setFirstRow(consultPgmGuideListForm.getFirstRow());
    	consultPgmGuideCommonDTO.setOrderBy(consultPgmGuideListForm.getOrderBy());
    	consultPgmGuideCommonDTO.setDirection(consultPgmGuideListForm.getDirection());
    	
    	//컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	user.setCompNo("100");  
    	
        List resultList = consultPgmGuideListService.findPgmGuideList(consultPgmGuideCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultPgmGuideListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmGuideListService.findTotalCount(consultPgmGuideCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,consultPgmGuideListForm.getListId(),consultPgmGuideListForm.getCurrentPageId(), consultPgmGuideListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultPgmGuideListForm
     */
    private void deleteList(HttpServletRequest request, ConsultPgmGuideListForm consultPgmGuideListForm) throws Exception
    {
    	ConsultPgmGuideListService consultPgmGuideListService = (ConsultPgmGuideListService) getBean("consultPgmGuideListService");
        String[] deleteRows = consultPgmGuideListForm.getDeleteRows();
        
        //컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	user.setCompNo("100");  
        
        consultPgmGuideListService.deletePgmGuideList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
