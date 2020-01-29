package dream.consult.program.report.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.report.dto.ConsultPgmRptDTO;
import dream.consult.program.report.form.ConsultPgmRptForm;
import dream.consult.program.report.service.ConsultPgmRptService;

/**
 * Report List - 목록 action 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/consultPgmRptList" name="consultPgmRptForm"
 *                input="/dream/consult/program/report/consultPgmRptList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultPgmRptDetail" name="consultPgmRptForm"
 *                input="/dream/consult/program/report/consultPgmRptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmRptList" path="/dream/consult/program/report/consultPgmRptList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="consultPgmRptDetail" path="/dream/consult/program/report/consultPgmRptDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmRptAction extends ConsultAuthAction
{
    /** 목록 조회 */
    public static final int LIST_FIND         	= 8001;
    /** 목록 삭제 */
    public static final int LIST_DELETE     	= 7002;
    /** 상세 조회 */
    public static final int DETAIL_INIT         = 8002;
    /** 상세 저장 */
    public static final int DETAIL_INPUT		= 5001;
    /** 상세 수정 */
    public static final int DETAIL_UPDATE       = 6001;
    /** 상세 중복체크 */
    public static final int DETAIL_CHECK        = 6002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmRptForm consultPgmRptForm = (ConsultPgmRptForm) form;
        
//        super.updateAudit(consultPgmRptForm.getConsultPgmRptDTO().getAuditKey()==""?consultPgmRptForm.getConsultPgmRptDTO().getAuditKey():consultPgmRptForm.getConsultPgmRptDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (consultPgmRptForm.getStrutsAction())
        {
            case ConsultPgmRptAction.LIST_FIND:
            	findList(request, consultPgmRptForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmRptAction.LIST_DELETE:
            	deleteList(request, consultPgmRptForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmRptAction.DETAIL_INIT:
                findDetail(request, response, consultPgmRptForm);
                returnActionForward = mapping.findForward("consultPgmRptDetail");
                break;
            case ConsultPgmRptAction.DETAIL_INPUT:
                insertDetail(request, response, consultPgmRptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmRptAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultPgmRptForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmRptAction.DETAIL_CHECK:
            	checkDetail(request, consultPgmRptForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmRptAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmRptForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmRptAction.BASE_GRID_EXPORT:
            	findList(request, consultPgmRptForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptForm consultPgmRptForm) throws IOException
    {
        super.setHeader(request, response, consultPgmRptForm.getListId(), consultPgmRptForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param consultPgmRptForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ConsultPgmRptForm consultPgmRptForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ConsultPgmRptService consultPgmRptService = (ConsultPgmRptService) getBean("consultPgmRptService");        

    	ConsultPgmRptDTO consultPgmRptDTO = consultPgmRptForm.getConsultPgmRptDTO();
    	consultPgmRptDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        consultPgmRptDTO.setIsLoadMaxCount("Y".equals(consultPgmRptForm.getIsLoadMaxCount())?true:false);
        consultPgmRptDTO.setFirstRow(consultPgmRptForm.getFirstRow());
        consultPgmRptDTO.setOrderBy(consultPgmRptForm.getOrderBy());
        consultPgmRptDTO.setDirection(consultPgmRptForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = consultPgmRptService.findList(consultPgmRptDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultPgmRptForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmRptService.findTotalCount(consultPgmRptDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmRptForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, ConsultPgmRptForm consultPgmRptForm) throws Exception
    {
    	ConsultPgmRptService consultPgmRptService = (ConsultPgmRptService) getBean("consultPgmRptService");        
    	
        String[] deleteRows = consultPgmRptForm.getDeleteRows();
        User user = getUser(request);
        
        consultPgmRptService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 상세 조회
     * @author 	youngjoo38
     * @version $Id:$
     * @since   1.0
     * @param 	request
     * @param 	consultPgmRptForm
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptForm consultPgmRptForm) throws Exception 
    {   
        // Service 객체 생성
    	ConsultPgmRptService consultPgmRptService = (ConsultPgmRptService)getBean("consultPgmRptService");
    	
    	ConsultPgmRptDTO consultPgmRptDTO = consultPgmRptForm.getConsultPgmRptDTO();
    	
    	// 유저
    	User user = getUser(request);
    	
        // 조회된 상세 부분
    	consultPgmRptDTO = consultPgmRptService.findDetail(consultPgmRptDTO, user);
        
        // 조회된 상세  셋팅한다.
        consultPgmRptForm.setConsultPgmRptDTO(consultPgmRptDTO);
    }
    
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptForm consultPgmRptForm) throws Exception
    {
    	// Service 객체 생성
    	ConsultPgmRptService consultPgmRptService = (ConsultPgmRptService)getBean("consultPgmRptService");
    	
    	ConsultPgmRptDTO consultPgmRptDTO = consultPgmRptForm.getConsultPgmRptDTO();
        
        User user = getUser(request);
        
        consultPgmRptService.insertDetail(consultPgmRptDTO, user);
        
        setAjaxStatus(request);
    }
    
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptForm consultPgmRptForm) throws Exception
    {
    	// Service 객체 생성
    	ConsultPgmRptService consultPgmRptService = (ConsultPgmRptService)getBean("consultPgmRptService");
    	
    	ConsultPgmRptDTO consultPgmRptDTO = consultPgmRptForm.getConsultPgmRptDTO();
    	
    	User user = getUser(request);
    	
    	consultPgmRptService.updateDetail(consultPgmRptDTO, user);
    	
    	setAjaxStatus(request);
    }
    
    private void checkDetail(HttpServletRequest request, ConsultPgmRptForm consultPgmRptForm) throws Exception
    {
    	// Service 객체 생성
    	ConsultPgmRptService consultPgmRptService = (ConsultPgmRptService)getBean("consultPgmRptService");
    	
    	ConsultPgmRptDTO consultPgmRptDTO = consultPgmRptForm.getConsultPgmRptDTO();
    	
    	User user = getUser(request);
    	
    	String isValid = consultPgmRptService.checkDetail(consultPgmRptDTO, user);
    	
    	setAjaxDesc(request, isValid);
    }
}
