package dream.req.work.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.form.MaWoReqDetailForm;
import dream.req.work.service.MaWoReqDetailService;

/**
 * 작업요청서 - 상세 action
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maWoReqDetail" name="maWoReqDetailForm"
 *                input="/dream/req/work/maWoReqDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvRecWorkDetail" name="maWoReqDetailForm"
 *                input="/dream/req/work/reqInvRecWorkDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoReqDetail" path="/dream/req/work/maWoReqDetail.jsp"
 *                        redirect="false"
 */
public class MaWoReqDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT         	= 8001;
    /** 수정 */
    public static final int DETAIL_UPDATE     	    = 6002;
    /** 입력 */
    public static final int DETAIL_INSERT        	= 5003;
    /** 접수 */
    public static final int REC_STATUS_UPDATE 		= 6004;
    /** 작업불가 */
    public static final int REC_INC_STATUS_UPDATE 	= 6005;
    /** 작업불가취소 */
    public static final int REC_REQ_STATUS_UPDATE   = 6006;
    /** 처리가능부서여부 */
    public static final int REC_CHECK_VALID_DEPT 	= 8006;
    /** 접수서 출력 */
    public static final int REC_REPORT        		= 2001;
    /** 작업결과 상태 확인 */
    public static final int CHK_WO_STATUS           = 8002;
        
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoReqDetailForm maWoReqDetailForm = (MaWoReqDetailForm) form;
        
        super.updateAudit(maWoReqDetailForm.getMaWoReqDetailDTO().getAuditKey()==""?maWoReqDetailForm.getMaWoReqCommonDTO().getAuditKey():maWoReqDetailForm.getMaWoReqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoReqDetailForm.getStrutsAction())
        {
            case MaWoReqDetailAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maWoReqDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoReqDetailAction.DETAIL_UPDATE:
            	updateDetail(maWoReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoReqDetailAction.DETAIL_INSERT:
            	insertDetail(maWoReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoReqDetailAction.REC_STATUS_UPDATE:
                updateStatus(maWoReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqDetailAction.REC_INC_STATUS_UPDATE:
                updateIncStatus(maWoReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqDetailAction.REC_REQ_STATUS_UPDATE:
                updateReqStatus(maWoReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqDetailAction.REC_CHECK_VALID_DEPT:
                checkValidRecDept(maWoReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoReqDetailAction.REC_REPORT:
                findWoRecReport(request, maWoReqDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaWoReqDetailAction.CHK_WO_STATUS:
                checkWoStatus(request, maWoReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void findWoRecReport(HttpServletRequest request, MaWoReqDetailForm maWoReqDetailForm)throws Exception 
    {   
        // Service 객체 생성
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)getBean("maWoReqDetailService", request);
        
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        List reportList = maWoReqDetailService.findWoRecReport(maWoReqDetailDTO,getUser(request));
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);

        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoReqDetail",strJson, getUser(request));

         // 조회한 List 를 form에 셋팅한다.
         request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
    }
    
    /**
     *  상세 조회
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoReqDetailForm maWoReqDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)getBean("maWoReqDetailService", request);
    	
    	MaWoReqCommonDTO maWoReqCommonDTO = maWoReqDetailForm.getMaWoReqCommonDTO();
    	
        // 조회된 상세 부분
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailService.findDetail(maWoReqCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWoReqDetailForm.setMaWoReqDetailDTO(maWoReqDetailDTO);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailForm
     * @param request
     */
    private void updateDetail(MaWoReqDetailForm maWoReqDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        
        maWoReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoReqDetailService.updateDetail(maWoReqDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailForm
     * @param request
     */
    private void insertDetail(MaWoReqDetailForm maWoReqDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        
        maWoReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoReqDetailService.insertDetail(maWoReqDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * update status
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailForm
     * @param request
     */
    private void updateStatus(MaWoReqDetailForm maWoReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        
        maWoReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoReqDetailService.updateStatus(maWoReqDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * update status
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailForm
     * @param request
     */
    private void updateIncStatus(MaWoReqDetailForm maWoReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        maWoReqDetailService.updateIncStatus(maWoReqDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * update 'REQ' status
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailForm
     * @param request
     */
    private void updateReqStatus(MaWoReqDetailForm maWoReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        maWoReqDetailService.updateReqStatus(maWoReqDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * check valid dept
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailForm
     * @param request
     */
    private void checkValidRecDept(MaWoReqDetailForm maWoReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        MaWoReqDetailDTO maWoReqDetailDTO = maWoReqDetailForm.getMaWoReqDetailDTO();
        String isValid = maWoReqDetailService.checkValidRecDept(maWoReqDetailDTO,getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
    /**
     * check valid status
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqDetailForm
     */
    private void checkWoStatus(HttpServletRequest request, MaWoReqDetailForm maWoReqDetailForm) throws Exception
    {
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService", request);
        MaWoReqCommonDTO maWoReqCommonDTO = maWoReqDetailForm.getMaWoReqCommonDTO();

        String statusId = maWoReqDetailService.checkStatus(maWoReqCommonDTO, getUser(request));
        
        setAjaxDesc(request, statusId);
    }
}