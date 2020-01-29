package dream.work.alarm.action;


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
import dream.work.alarm.dto.WorkAlarmDTO;
import dream.work.alarm.form.WorkAlarmForm;
import dream.work.alarm.service.WorkAlarmService;

/**
 * Alarm List - 목록 action 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workAlarmList" name="workAlarmForm"
 *                input="/dream/work/alarm/workAlarmList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workAlarmDetail" name="workAlarmForm"
 *                input="/dream/work/alarm/workAlarmDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workAlarmList" path="/dream/work/alarm/workAlarmList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="workAlarmDetail" path="/dream/work/alarm/workAlarmDetail.jsp"
 *                        redirect="false"
 */
public class WorkAlarmAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         	= 8001;
    /** 삭제하기 */
    public static final int LIST_DELETE     	= 7002;
    /** 상세조회 */
    public static final int DETAIL_INIT         = 8002;
    /** 상세수정 */
    public static final int DETAIL_UPDATE       = 1005;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkAlarmForm workAlarmForm = (WorkAlarmForm) form;
        
        super.updateAudit(workAlarmForm.getWorkAlarmDTO().getAuditKey()==""?workAlarmForm.getWorkAlarmDTO().getAuditKey():workAlarmForm.getWorkAlarmDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workAlarmForm.getStrutsAction())
        {
            case WorkAlarmAction.LIST_FIND:
            	findList(request, workAlarmForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkAlarmAction.LIST_DELETE:
            	deleteList(request, workAlarmForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkAlarmAction.DETAIL_INIT:
                findDetail(request, response, workAlarmForm);
                returnActionForward = mapping.findForward("workAlarmDetail");
                break;
            case WorkAlarmAction.DETAIL_UPDATE:
                updateDetail(request, response, workAlarmForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkAlarmAction.BASE_SET_HEADER:
                setHeader(request, response, workAlarmForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkAlarmAction.BASE_GRID_EXPORT:
            	findList(request, workAlarmForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkAlarmForm workAlarmForm) throws IOException
    {
        super.setHeader(request, response, workAlarmForm.getListId(), workAlarmForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workAlarmForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, WorkAlarmForm workAlarmForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkAlarmService workAlarmService = (WorkAlarmService) getBean("workAlarmService");        

    	WorkAlarmDTO workAlarmDTO = workAlarmForm.getWorkAlarmDTO();
    	workAlarmDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        workAlarmDTO.setIsLoadMaxCount("Y".equals(workAlarmForm.getIsLoadMaxCount())?true:false);
        workAlarmDTO.setFirstRow(workAlarmForm.getFirstRow());
        workAlarmDTO.setOrderBy(workAlarmForm.getOrderBy());
        workAlarmDTO.setDirection(workAlarmForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = workAlarmService.findList(workAlarmDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workAlarmForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workAlarmService.findTotalCount(workAlarmDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workAlarmForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, WorkAlarmForm workAlarmForm) throws Exception
    {
    	WorkAlarmService workAlarmService = (WorkAlarmService) getBean("workAlarmService");        
    	
        String[] deleteRows = workAlarmForm.getDeleteRows();
        User user = getUser(request);
        
        workAlarmService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 상세 조회
     * @author 	nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param 	request
     * @param 	workAlarmForm
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkAlarmForm workAlarmForm) throws Exception 
    {   
        // Service 객체 생성
    	WorkAlarmService workAlarmService = (WorkAlarmService)getBean("workAlarmService");
    	
    	WorkAlarmDTO workAlarmDTO = workAlarmForm.getWorkAlarmDTO();
    	
    	// 유저
    	User user = getUser(request);
    	
        // 조회된 상세 부분
    	workAlarmDTO = workAlarmService.findDetail(workAlarmDTO, user);
        
        // 조회된 상세  셋팅한다.
        workAlarmForm.setWorkAlarmDTO(workAlarmDTO);
    }
    
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkAlarmForm workAlarmForm) throws Exception
    {
    	// Service 객체 생성
    	WorkAlarmService workAlarmService = (WorkAlarmService)getBean("workAlarmService");
    	
    	WorkAlarmDTO workAlarmDTO = workAlarmForm.getWorkAlarmDTO();
        
        User user = getUser(request);
        
        workAlarmService.updateDetail(workAlarmDTO, user);
        
        setAjaxStatus(request);
    }
}
