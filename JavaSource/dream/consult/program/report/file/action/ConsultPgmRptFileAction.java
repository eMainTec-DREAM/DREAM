package dream.consult.program.report.file.action;


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
import dream.consult.program.report.file.dto.ConsultPgmRptFileDTO;
import dream.consult.program.report.file.form.ConsultPgmRptFileForm;
import dream.consult.program.report.file.service.ConsultPgmRptFileService;

/**
 * 출력물 설정 파일 - 목록 action 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/consultPgmRptFileList" name="consultPgmRptFileForm"
 *                input="/dream/consult/program/report/file/consultPgmRptFileList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultPgmRptFileDetail" name="consultPgmRptFileForm"
 *                input="/dream/consult/program/report/file/consultPgmRptFileDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmRptFileList" path="/dream/consult/program/report/file/consultPgmRptFileList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="consultPgmRptFileDetail" path="/dream/consult/program/report/file/consultPgmRptFileDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmRptFileAction extends ConsultAuthAction
{
    /** 목록 조회 */
    public static final int LIST_FIND         	 	= 8001;
    /** 목록 삭제 */
    public static final int LIST_DELETE     	 	= 7002;
    /** 상세 조회 */
    public static final int DETAIL_INIT          	= 8002;
    /** 상세 저장 */
    public static final int DETAIL_INPUT			= 5001;
    /** 상세 수정 */
    public static final int DETAIL_UPDATE			= 6001;
    /** 상세 중복체크 */
    public static final int DETAIL_CHECK        = 6002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmRptFileForm consultPgmRptFileForm = (ConsultPgmRptFileForm) form;
        
//        super.updateAudit(consultPgmRptFileForm.getConsultPgmRptFileDTO().getAuditKey()==""?consultPgmRptFileForm.getConsultPgmRptFileDTO().getAuditKey():consultPgmRptFileForm.getConsultPgmRptFileDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (consultPgmRptFileForm.getStrutsAction())
        {
            case ConsultPgmRptFileAction.LIST_FIND:
            	findList(request, consultPgmRptFileForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmRptFileAction.LIST_DELETE:
            	deleteList(request, consultPgmRptFileForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmRptFileAction.DETAIL_INIT:
                findDetail(request, response, consultPgmRptFileForm);
                returnActionForward = mapping.findForward("consultPgmRptFileDetail");
                break;
            case ConsultPgmRptFileAction.DETAIL_INPUT:
                insertDetail(request, response, consultPgmRptFileForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmRptFileAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultPgmRptFileForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmRptFileAction.DETAIL_CHECK:
            	checkDetail(request, consultPgmRptFileForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmRptFileAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmRptFileForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmRptFileAction.BASE_GRID_EXPORT:
            	findList(request, consultPgmRptFileForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptFileForm consultPgmRptFileForm) throws IOException
    {
        super.setHeader(request, response, consultPgmRptFileForm.getListId(), consultPgmRptFileForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param consultPgmRptFileForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ConsultPgmRptFileForm consultPgmRptFileForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ConsultPgmRptFileService consultPgmRptFileService = (ConsultPgmRptFileService) getBean("consultPgmRptFileService");        

    	ConsultPgmRptFileDTO consultPgmRptFileDTO = consultPgmRptFileForm.getConsultPgmRptFileDTO();
    	
    	//Paging
        consultPgmRptFileDTO.setIsLoadMaxCount("Y".equals(consultPgmRptFileForm.getIsLoadMaxCount())?true:false);
        consultPgmRptFileDTO.setFirstRow(consultPgmRptFileForm.getFirstRow());
        consultPgmRptFileDTO.setOrderBy(consultPgmRptFileForm.getOrderBy());
        consultPgmRptFileDTO.setDirection(consultPgmRptFileForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = consultPgmRptFileService.findList(consultPgmRptFileDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultPgmRptFileForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmRptFileService.findTotalCount(consultPgmRptFileDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmRptFileForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, ConsultPgmRptFileForm consultPgmRptFileForm) throws Exception
    {
    	ConsultPgmRptFileService consultPgmRptFileService = (ConsultPgmRptFileService) getBean("consultPgmRptFileService");        
    	
        String[] deleteRows = consultPgmRptFileForm.getDeleteRows();
        User user = getUser(request);
        
        consultPgmRptFileService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 상세 조회
     * @author 	youngjoo38
     * @version $Id:$
     * @since   1.0
     * @param 	request
     * @param 	consultPgmRptFileForm
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptFileForm consultPgmRptFileForm) throws Exception 
    {   
        // Service 객체 생성
    	ConsultPgmRptFileService consultPgmRptFileService = (ConsultPgmRptFileService)getBean("consultPgmRptFileService");
    	ConsultPgmRptFileDTO consultPgmRptFileDTO = consultPgmRptFileForm.getConsultPgmRptFileDTO();
    	
    	// 유저
    	User user = getUser(request);
    	
        // 조회된 상세 부분
    	consultPgmRptFileDTO = consultPgmRptFileService.findDetail(consultPgmRptFileDTO, user);
        
        // 조회된 상세  셋팅한다.
        consultPgmRptFileForm.setConsultPgmRptFileDTO(consultPgmRptFileDTO);
    }
    
    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultPgmRptFileForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptFileForm consultPgmRptFileForm) throws Exception
    {
    	// Service 객체 생성
    	ConsultPgmRptFileService consultPgmRptFileService = (ConsultPgmRptFileService)getBean("consultPgmRptFileService");
    	ConsultPgmRptFileDTO consultPgmRptFileDTO = consultPgmRptFileForm.getConsultPgmRptFileDTO();
        
    	// User 객체 생성
        User user = getUser(request);
        
        consultPgmRptFileService.insertDetail(consultPgmRptFileDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultPgmRptFileForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmRptFileForm consultPgmRptFileForm) throws Exception
    {
    	// Service 객체 생성
    	ConsultPgmRptFileService consultPgmRptFileService = (ConsultPgmRptFileService)getBean("consultPgmRptFileService");
    	ConsultPgmRptFileDTO consultPgmRptFileDTO = consultPgmRptFileForm.getConsultPgmRptFileDTO();
    	
    	// User 객체 생성
    	User user = getUser(request);
    	
    	consultPgmRptFileService.updateDetail(consultPgmRptFileDTO, user);
    	
    	setAjaxStatus(request);
    }
    
    private void checkDetail(HttpServletRequest request, ConsultPgmRptFileForm consultPgmRptFileForm) throws Exception
    {
    	// Service 객체 생성
    	ConsultPgmRptFileService consultPgmRptFileService = (ConsultPgmRptFileService)getBean("consultPgmRptFileService");
    	ConsultPgmRptFileDTO consultPgmRptFileDTO = consultPgmRptFileForm.getConsultPgmRptFileDTO();
    	
    	// User 객체 생성
    	User user = getUser(request);
    	
    	String isValid = consultPgmRptFileService.checkDetail(consultPgmRptFileDTO, user);
    	
    	setAjaxDesc(request, isValid);
    }
}
