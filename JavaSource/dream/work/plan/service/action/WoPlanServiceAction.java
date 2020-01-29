package dream.work.plan.service.action;


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
import common.util.CommonUtil;
import dream.work.plan.service.dto.WoPlanServiceDTO;
import dream.work.plan.service.form.WoPlanServiceForm;
import dream.work.plan.service.service.WoPlanServiceService;

/**
 * 서비스작업 Action 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/woPlanServiceList" name="woPlanServiceForm"
 *                input="/dream/work/plan/service/woPlanServiceList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/woPlanServiceDetail" name="woPlanServiceForm"
 *                input="/dream/work/plan/service/woPlanServiceDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="woPlanServiceList" path="/dream/work/plan/service/woPlanServiceList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="woPlanServiceDetail" path="/dream/work/plan/service/woPlanServiceDetail.jsp"
 *                        redirect="false"
 */
public class WoPlanServiceAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         	= 8001;
    /** 삭제하기 */
    public static final int LIST_DELETE     	= 7002;
    /** 상세조회 */
    public static final int DETAIL_INIT         = 8002;
    /** 상세저장 */
    public static final int DETAIL_INSERT       = 5001;
    /** 상세수정 */
    public static final int DETAIL_UPDATE       = 6001;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanServiceForm woPlanServiceForm = (WoPlanServiceForm) form;
        
        super.updateAudit(woPlanServiceForm.getWoPlanServiceDTO().getAuditKey()==""?woPlanServiceForm.getWoPlanServiceDTO().getAuditKey():woPlanServiceForm.getWoPlanServiceDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (woPlanServiceForm.getStrutsAction())
        {
            case WoPlanServiceAction.LIST_FIND:
            	findList(request, woPlanServiceForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanServiceAction.LIST_DELETE:
            	deleteList(request, woPlanServiceForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanServiceAction.DETAIL_INIT:
                findDetail(request, response, woPlanServiceForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WoPlanServiceAction.DETAIL_INSERT:
                insertDetail(request, response, woPlanServiceForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanServiceAction.DETAIL_UPDATE:
                updateDetail(request, response, woPlanServiceForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanServiceAction.BASE_SET_HEADER:
                setHeader(request, response, woPlanServiceForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanServiceAction.BASE_GRID_EXPORT:
            	findList(request, woPlanServiceForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, WoPlanServiceForm woPlanServiceForm) throws IOException
    {
        super.setHeader(request, response, woPlanServiceForm.getListId(), woPlanServiceForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param 	request
     * @param 	response
     * @param 	woPlanServiceForm
     * @throws 	Exception
     */
    private void findList(HttpServletRequest request, WoPlanServiceForm woPlanServiceForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WoPlanServiceService woPlanServiceService = (WoPlanServiceService) getBean("woPlanServiceService");        

    	WoPlanServiceDTO woPlanServiceDTO = woPlanServiceForm.getWoPlanServiceDTO();
    	    	
    	//Paging
        woPlanServiceDTO.setIsLoadMaxCount("Y".equals(woPlanServiceForm.getIsLoadMaxCount())?true:false);
        woPlanServiceDTO.setFirstRow(woPlanServiceForm.getFirstRow());
        woPlanServiceDTO.setOrderBy(woPlanServiceForm.getOrderBy());
        woPlanServiceDTO.setDirection(woPlanServiceForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = woPlanServiceService.findList(woPlanServiceDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(woPlanServiceForm.getIsTotalCount()) == 0 && !excelExport) totalCount = woPlanServiceService.findTotalCount(woPlanServiceDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,woPlanServiceForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, WoPlanServiceForm woPlanServiceForm) throws Exception
    {
    	WoPlanServiceService woPlanServiceService = (WoPlanServiceService) getBean("woPlanServiceService");        
    	
        String[] deleteRows = woPlanServiceForm.getDeleteRows();
        User user = getUser(request);
        
        woPlanServiceService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 상세 조회
     * @author 	nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param 	request
     * @param 	response
     * @param 	woPlanServiceForm
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WoPlanServiceForm woPlanServiceForm) throws Exception 
    {   
        // Service 객체 생성
    	WoPlanServiceService woPlanServiceService = (WoPlanServiceService)getBean("woPlanServiceService");
    	
    	WoPlanServiceDTO woPlanServiceDTO = woPlanServiceForm.getWoPlanServiceDTO();
    	
    	// 유저
    	User user = getUser(request);
    	
        // 조회된 상세 부분
    	woPlanServiceDTO = woPlanServiceService.findDetail(woPlanServiceDTO, user);
        
        // 조회된 상세  셋팅한다.
        woPlanServiceForm.setWoPlanServiceDTO(woPlanServiceDTO);
    }
    
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WoPlanServiceForm woPlanServiceForm) throws Exception
    {
    	// Service 객체 생성
    	WoPlanServiceService woPlanServiceService = (WoPlanServiceService)getBean("woPlanServiceService");
    	
    	WoPlanServiceDTO woPlanServiceDTO = woPlanServiceForm.getWoPlanServiceDTO();
        
        User user = getUser(request);
        
        woPlanServiceService.insertDetail(woPlanServiceDTO, user);
        
        setAjaxStatus(request);
    }
    
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WoPlanServiceForm woPlanServiceForm) throws Exception
    {
    	// Service 객체 생성
    	WoPlanServiceService woPlanServiceService = (WoPlanServiceService)getBean("woPlanServiceService");
    	
    	WoPlanServiceDTO woPlanServiceDTO = woPlanServiceForm.getWoPlanServiceDTO();
        
        User user = getUser(request);
        
        woPlanServiceService.updateDetail(woPlanServiceDTO, user);
        
        setAjaxStatus(request);
    }
}
