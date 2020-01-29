package dream.consult.program.uploaddata.action;


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
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.form.ConsultProgramUploadDataForm;
import dream.consult.program.uploaddata.service.ConsultProgramUploadDataService;

/**
 * 업로드데이타 - 목록 action
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/consultProgramUploadDataList" name="consultProgramUploadDataForm"
 *                input="/dream/consult/program/uploaddata/consultProgramUploadDataList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultProgramUploadDataDetail" name="consultProgramUploadDataForm"
 *                input="/dream/consult/program/uploaddata/consultProgramUploadDataDetail.jsp" scope="request"
 *                validate="false"
 */
public class ConsultProgramUploadDataAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 	                = 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	            = 1002;
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 1003;
    /** 저장 */
    public static final int DETAIL_INPUT                = 1004;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 1005;
    /** 테이블 존재여부 확인 */
    public static final int DETAIL_CHECK_TABLE_EXIST    = 1006;
    /** 확정 (테이블생성) */
    public static final int DETAIL_COMPLETE             = 1007;
    /** 취소 (테이블드랍) */
    public static final int DETAIL_DROP_TABLE           = 1008;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultProgramUploadDataForm consultProgramUploadDataForm = (ConsultProgramUploadDataForm) form;
        
        switch (consultProgramUploadDataForm.getStrutsAction())
        {
            case ConsultProgramUploadDataAction.LIST_FIND:
            	findList(request, consultProgramUploadDataForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultProgramUploadDataAction.BASE_SET_HEADER:
                setHeader(request, response, consultProgramUploadDataForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultProgramUploadDataAction.LIST_DELETE:
            	delete(request, consultProgramUploadDataForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataAction.BASE_GRID_EXPORT:
            	findList(request, consultProgramUploadDataForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case ConsultProgramUploadDataAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultProgramUploadDataForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultProgramUploadDataAction.DETAIL_INPUT:
                insertDetail(consultProgramUploadDataForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataAction.DETAIL_UPDATE:
                updateDetail(consultProgramUploadDataForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataAction.DETAIL_CHECK_TABLE_EXIST:
                this.checkTableExist(consultProgramUploadDataForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataAction.DETAIL_COMPLETE:
                completeDetail(consultProgramUploadDataForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataAction.DETAIL_DROP_TABLE:
                dropTable(consultProgramUploadDataForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataForm consultProgramUploadDataForm) throws IOException
    {
        super.setHeader(request, response, consultProgramUploadDataForm.getListId(), consultProgramUploadDataForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param consultProgramUploadDataForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ConsultProgramUploadDataForm consultProgramUploadDataForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService");        

    	ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
    	
        //Paging
        consultProgramUploadDataDTO.setIsLoadMaxCount("Y".equals(consultProgramUploadDataForm.getIsLoadMaxCount())?true:false);
        consultProgramUploadDataDTO.setFirstRow(consultProgramUploadDataForm.getFirstRow());
        consultProgramUploadDataDTO.setOrderBy(consultProgramUploadDataForm.getOrderBy());
        consultProgramUploadDataDTO.setDirection(consultProgramUploadDataForm.getDirection());
        
        List resultList = consultProgramUploadDataService.findList(consultProgramUploadDataDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultProgramUploadDataForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultProgramUploadDataService.findTotalCount(consultProgramUploadDataDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultProgramUploadDataForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataForm
     * @param request
     */
    private void delete(HttpServletRequest request, ConsultProgramUploadDataForm consultProgramUploadDataForm) throws Exception
    {
    	ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService");
        
    	String[] deleteRows = consultProgramUploadDataForm.getDeleteRows();    // sheet 내역
        
        consultProgramUploadDataService.delete(deleteRows);
        
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, ConsultProgramUploadDataForm consultProgramUploadDataForm)throws Exception 
    {   
        // Service 객체 생성
        ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService)getBean("consultProgramUploadDataService");
        
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
        
        // 조회된 상세 부분
        consultProgramUploadDataDTO = consultProgramUploadDataService.findDetail(consultProgramUploadDataDTO,getUser(request));
        
        // 조회된 상세  셋팅한다.
        consultProgramUploadDataForm.setConsultProgramUploadDataDTO(consultProgramUploadDataDTO);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataForm
     * @param request
     */
    private void insertDetail(ConsultProgramUploadDataForm consultProgramUploadDataForm, HttpServletRequest request) throws Exception
    {
        ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService");
        
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
        
        User user = getUser(request);
        
        consultProgramUploadDataService.insertDetail(consultProgramUploadDataDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataForm
     * @param request
     */
    private void updateDetail(ConsultProgramUploadDataForm consultProgramUploadDataForm, HttpServletRequest request) throws Exception
    {
        ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService");
        
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
        
        User user = getUser(request);
        
        consultProgramUploadDataService.updateDetail(consultProgramUploadDataDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * check table exist
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultProgramUploadDataForm
     * @param request
     */
    private void checkTableExist(ConsultProgramUploadDataForm consultProgramUploadDataForm, HttpServletRequest request) throws Exception
    {
        ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService",request);
        
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
        
        consultProgramUploadDataDTO.setEnterBy(getUser(request).getUserId());
        consultProgramUploadDataDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        String isValid  = consultProgramUploadDataService.checkTableExist(consultProgramUploadDataDTO, user);
        
        setAjaxDesc(request, isValid);
    }
    /**
     * detail complete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultProgramUploadDataForm
     * @param request
     */
    private void completeDetail(ConsultProgramUploadDataForm consultProgramUploadDataForm, HttpServletRequest request) throws Exception
    {
        ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService",request);
        
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
        
        consultProgramUploadDataDTO.setEnterBy(getUser(request).getUserId());
        consultProgramUploadDataDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        consultProgramUploadDataService.completeDetail(consultProgramUploadDataDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * Drop Table
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultProgramUploadDataForm
     * @param request
     */
    private void dropTable(ConsultProgramUploadDataForm consultProgramUploadDataForm, HttpServletRequest request) throws Exception
    {
        ConsultProgramUploadDataService consultProgramUploadDataService = (ConsultProgramUploadDataService) getBean("consultProgramUploadDataService",request);
        
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataForm.getConsultProgramUploadDataDTO();
        
        consultProgramUploadDataDTO.setEnterBy(getUser(request).getUserId());
        consultProgramUploadDataDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        consultProgramUploadDataService.dropTable(consultProgramUploadDataDTO, user);
        
        setAjaxStatus(request);
    }
}
