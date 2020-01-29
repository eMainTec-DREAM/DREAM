package dream.work.service.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.form.WorkServiceListForm;
import dream.work.service.service.WorkServiceListService;

/**
 * 서비스 마스터 - List Action
 * 
 * @author cjscjs9
 * @version $Id: WorkServiceListAction.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @struts:action path="/workServiceList" name="workServiceListForm"
 *                input="/dream/work/service/workServiceList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workServiceList" path="/dream/work/service/workServiceList.jsp"
 *                        redirect="false"
 */

public class WorkServiceListAction extends AuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkServiceListForm workServiceListForm = (WorkServiceListForm) form;
        
        
        switch (workServiceListForm.getStrutsAction())
        {
            case WorkServiceListAction.BASE_SET_HEADER:
                setHeader(request, response, workServiceListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkServiceListAction.LIST_FIND:
                findList(request, response, workServiceListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkServiceListAction.LIST_DELETE:
            	deleteList(request, workServiceListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkServiceListAction.BASE_GRID_EXPORT:
            	findList(request, response, workServiceListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("workServiceList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkServiceListForm workServiceListForm) throws IOException
    {
        super.setHeader(request, response, workServiceListForm.getListId(), workServiceListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workServiceListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkServiceListForm workServiceListForm, boolean excelExport) throws Exception
    {
    	WorkServiceListService workServiceListService = (WorkServiceListService) getBean("workServiceListService");//형변환을 해서 객체를 생성해 준다??
    	WorkServiceCommonDTO workServiceCommonDTO = workServiceListForm.getWorkServiceCommonDTO(); // Form 안에 있는 DTO를 불러옴.

    	//Paging
    	workServiceCommonDTO.setIsLoadMaxCount("Y".equals(workServiceListForm.getIsLoadMaxCount())?true:false);
    	workServiceCommonDTO.setFirstRow(workServiceListForm.getFirstRow());
    	workServiceCommonDTO.setOrderBy(workServiceListForm.getOrderBy());
    	workServiceCommonDTO.setDirection(workServiceListForm.getDirection());
    	
    	User user = getUser(request);

        List resultList = workServiceListService.findWorkServiceList(workServiceCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workServiceListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workServiceListService.findTotalCount(workServiceCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workServiceListForm.getListId(),workServiceListForm.getCurrentPageId(), workServiceListForm.getFileName());
        				//엑셀읆 만들어주기 위함
        else super.makeJsonResult(resultList, request, response, totalCount);
        //토탈을 생성.
    }
    
    /**
     * DELETE LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workServiceListForm
     */
    private void deleteList(HttpServletRequest request, WorkServiceListForm workServiceListForm) throws Exception
    {
    	WorkServiceListService workServiceListService = (WorkServiceListService) getBean("workServiceListService");
        String[] deleteRows = workServiceListForm.getDeleteRows();
        
        //컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
        
        workServiceListService.deleteWorkServiceList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
