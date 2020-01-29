package dream.work.list.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.ResponseDTO;
import common.bean.User;
import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.req.work.service.MaWoReqDetailService;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.form.MaWoResultMstrDetailForm;
import dream.work.list.service.MaWoResultMstrDetailService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * �۾���� - �� action
 * 
 * @author kim2107
 * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maWoResultMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/maWoResultMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/month/maWoResultMonthMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRprMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRprMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmCalEtcMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPmCalEtcMstrDetail.jsp" scope="request"
 *                validate="false"                
 * @struts:action path="/maWoResultPmClnMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmClnMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmOilMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmOilMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRprMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRprMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRplMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmOilMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRprMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRprMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRplMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultTrEleMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/maWoResultTrEleMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmGmMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmGmMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmBaseMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmBaseMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmGnlMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPmGnlMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmSclMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPmSclMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmPrsMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPmPrsMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmLocBaseMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmLocBaseMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListEqChangeMstrDetail" name="maWoResultMstrDetailForm"
 *                input="/dream/work/list/eq/change/workListEqChangeMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultMstrDetail" path=/dream/work/list/maWoResultMstrDetail.jsp"
 *                        redirect="false"
 */
public class MaWoResultMstrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_RESULT_DETAIL_INIT 					= 8001;
    /** ���� */
    public static final int WO_RESULT_DETAIL_INPUT 					= 5002;
    /** ���� */
    public static final int WO_RESULT_DETAIL_UPDATE 				= 6003;
    /** �Ϸ� */
    public static final int WO_RESULT_DETAIL_COMPLETE 				= 6004;
    /** ���˵����� ���� */
    public static final int CREATE_POINT_DATA						= 5005;
    /** �����׸� �˻� */
    public static final int WO_RESULT_DETAIL_CHECKPOINT				= 8006;
    /** �����۾�(�Ϲ�) report */
    public static final int WO_RESULT_PM_GNL_DETAIL_REPORT 			= 8007;
    /** �����۾�(�з°�) report */
    public static final int WO_RESULT_PM_PRS_DETAIL_REPORT 			= 8008;
    /** �����۾�(����) report */
    public static final int WO_RESULT_PM_SCL_DETAIL_REPORT 			= 8009;
    
    public static final int WO_RESULT_SERIAL_COUNT 		 			= 8010;
    
    public static final int WO_RESULT_FIND_PAGE        				= 8011;
    /** �۾���ȹ��� ���翩�� �˻� */
    public static final int WO_PLAN_CHECK							= 8012;
    /** �Ϸ���� */
    public static final int WO_RESULT_DETAIL_COMPLETE_CANCEL 		= 1013;
    /** WO ���º�ȭ */
    public static final int WO_RESULT_STATUS_DATA					= 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultMstrDetailForm maWoResultMstrDetailForm = (MaWoResultMstrDetailForm) form;
        
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultMstrDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO().getAuditKey()==""?maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO().getAuditKey():maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoResultMstrDetailForm.getStrutsAction())
        {
            case MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoResultMstrDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_SERIAL_COUNT:
                findSerialCount(request, maWoResultMstrDetailForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INPUT:
            	insertDetail(maWoResultMstrDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaWoResultMstrDetailAction.WO_RESULT_DETAIL_UPDATE:
            	updateDetail(maWoResultMstrDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaWoResultMstrDetailAction.WO_RESULT_DETAIL_COMPLETE:
            	completeDetail(maWoResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultMstrDetailAction.CREATE_POINT_DATA:
            	createPoint(maWoResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultMstrDetailAction.WO_RESULT_DETAIL_CHECKPOINT:
            	checkPoint(maWoResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultMstrDetailAction.BASE_ACTION_REPORT:
            	findReport(request, maWoResultMstrDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_PM_GNL_DETAIL_REPORT:
                findPmGnlReport(request, maWoResultMstrDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_PM_PRS_DETAIL_REPORT:
                findPmPrsReport(request, maWoResultMstrDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_PM_SCL_DETAIL_REPORT:
                findPmSclReport(request, maWoResultMstrDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_FIND_PAGE:
                findPage(request, maWoResultMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultMstrDetailAction.WO_PLAN_CHECK:
                woPlanCheck(maWoResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultMstrDetailAction.WO_RESULT_DETAIL_COMPLETE_CANCEL:
            	completeCancelDetail(maWoResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultMstrDetailAction.WO_RESULT_STATUS_DATA:
            	updateWoStatus(maWoResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;            	
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void findPage(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm)
    {
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService)getBean("maWoResultMstrDetailService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO();
        
        String wkOrPage = maWoResultMstrDetailService.findPage(maWoResultMstrCommonDTO, getUser(request));
        setAjaxDesc(request, wkOrPage);
    }

    private void findSerialCount(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm,HttpServletResponse response) throws IOException 
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService)getBean("maWoResultMstrDetailService");

    	MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
    	maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
    	maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        // ��ȸ�� �� �κ�
        int resultStatus = maWoResultMstrDetailService.findSerialCount(maWoResultMstrDetailDTO);

        response.getWriter().print(resultStatus+"");
	}

	/**
     * �۾���� �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService)getBean("maWoResultMstrDetailService", request);

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoResultMstrCommonDTO.setUserLang(getUser(request).getLangId());
        // ��ȸ�� �� �κ�
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
        if("BM".equals(maWoResultMstrDetailDTO.getWoTypeId())&&maWoResultMstrDetailDTO.getWoTypeId()!=null){
        	// ��ȸ�� �� �κ�
        	MaWoResultFailDetailDTO maWoResultFailDetailDTO = maWoResultMstrDetailService.findFailDetail(maWoResultMstrCommonDTO);
        	maWoResultMstrDetailForm.setMaWoResultFailDetailDTO(maWoResultFailDetailDTO);
        } else if("PMC".equals(maWoResultMstrDetailDTO.getWoTypeId())&&maWoResultMstrDetailDTO.getWoTypeId()!=null){
        	// ��ȸ�� �� �κ�
        	MaWoResultPmCalDTO maWoResultPmCalDTO = maWoResultMstrDetailService.findCalDetail(maWoResultMstrCommonDTO);
        	maWoResultMstrDetailForm.setMaWoResultPmCalDTO(maWoResultPmCalDTO);
        }
        // ��ȸ�� ��  �����Ѵ�.
        maWoResultMstrDetailForm.setMaWoResultMstrDetailDTO(maWoResultMstrDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailForm
     * @param request
     */
    private void insertDetail(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService",request);
        
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        MaWoResultFailDetailDTO maWoResultFailDetailDTO = maWoResultMstrDetailForm.getMaWoResultFailDetailDTO();
        MaWoResultPmCalDTO maWoResultPmCalDTO = maWoResultMstrDetailForm.getMaWoResultPmCalDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        ResponseDTO responseDTO = maWoResultMstrDetailService.insertDetail(maWoResultMstrDetailDTO, maWoResultMstrCommonDTO, getUser(request));
        
        if("BM".equals(maWoResultMstrCommonDTO.getSelectedWoType())&&maWoResultMstrCommonDTO.getSelectedWoType()!=null){
            maWoResultFailDetailDTO.setEqAsmbId(maWoResultMstrDetailDTO.getEqAsmbId());
            maWoResultFailDetailDTO.setEqAsmbDesc(maWoResultMstrDetailDTO.getEqAsmbDesc());
            responseDTO = maWoResultMstrDetailService.updateFailDetail(maWoResultMstrCommonDTO, maWoResultFailDetailDTO, getUser(request));
        } else if("PMC".equals(maWoResultMstrCommonDTO.getSelectedWoType())&&maWoResultMstrCommonDTO.getSelectedWoType()!=null){
            maWoResultPmCalDTO.setWkorDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
            responseDTO = maWoResultMstrDetailService.updateCalDetail(maWoResultMstrCommonDTO, maWoResultPmCalDTO, getUser(request));
        }
        
        CommonUtil.makeJsonResult(responseDTO, response);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService",request);
        
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        MaWoResultFailDetailDTO maWoResultFailDetailDTO = maWoResultMstrDetailForm.getMaWoResultFailDetailDTO();
        MaWoResultPmCalDTO maWoResultPmCalDTO = maWoResultMstrDetailForm.getMaWoResultPmCalDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	User loginUser = getUser(request);
    	
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        ResponseDTO responseDTO = maWoResultMstrDetailService.updateDetail(maWoResultMstrDetailDTO, loginUser);
        
        //������Ʈ �����ÿ���
        if(200<=responseDTO.getStatus() && responseDTO.getStatus()<300) {
            if("BM".equals(maWoResultMstrDetailDTO.getWoTypeId())&&maWoResultMstrDetailDTO.getWoTypeId()!=null){
                maWoResultFailDetailDTO.setEqAsmbId(maWoResultMstrDetailDTO.getEqAsmbId());
                maWoResultFailDetailDTO.setEqAsmbDesc(maWoResultMstrDetailDTO.getEqAsmbDesc());
                responseDTO = maWoResultMstrDetailService.updateFailDetail(maWoResultMstrCommonDTO, maWoResultFailDetailDTO, loginUser);
            } else if("PMC".equals(maWoResultMstrDetailDTO.getWoTypeId())&&maWoResultMstrDetailDTO.getWoTypeId()!=null){
                maWoResultPmCalDTO.setWkorDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
                responseDTO = maWoResultMstrDetailService.updateCalDetail(maWoResultMstrCommonDTO, maWoResultPmCalDTO, loginUser);
            }
        }
        
        CommonUtil.makeJsonResult(responseDTO, response);
    }
    
    /**
     * detail complete
     * @author  kim21017
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailForm
     * @param request
     */
    private void completeDetail(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService",request);
    	
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        MaWoResultPmCalDTO maWoResultPmCalDTO = maWoResultMstrDetailForm.getMaWoResultPmCalDTO();
        
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        maWoResultMstrDetailDTO.setLoginUser(getUser(request));
        maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO, maWoResultPmCalDTO);
        
        setAjaxStatus(request);
    }
    
    
    /**
     * detail complete
     * @author  kim21017
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailForm
     * @param request
     */
    private void completeCancelDetail(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService",request);
    	
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        MaWoResultPmCalDTO maWoResultPmCalDTO = maWoResultMstrDetailForm.getMaWoResultPmCalDTO();
        
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        maWoResultMstrDetailDTO.setLoginUser(getUser(request));
        
        maWoResultMstrDetailService.completeCancelDetail(maWoResultMstrDetailDTO, maWoResultPmCalDTO);
        
        setAjaxStatus(request);
    }
    
    
    
    
    private void checkPoint(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService",request);
    	
    	MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
    	
    	String isValid = maWoResultMstrDetailService.checkPoint(maWoResultMstrDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
    /**
     * create point data
     * @author  kim21017
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailForm
     * @param request
     */
    private void createPoint(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService");
        
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultMstrDetailService.createPoint(maWoResultMstrDetailDTO);
        
        setAjaxStatus(request);
    }

    /**
     * Report �� ��ȸ�Ѵ�.
     * @param request
     * @param maWoResultMstrDetailForm
     * @param response 
     * @throws DRException 
     * @throws JRException 
     * @throws IOException 
     */
    private void findReport(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm) throws JRException, DRException, IOException
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService");
    	MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoResultMstrDetailService.getReportView(maWoResultMstrDetailDTO);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        
        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoResultMstrList",strJson, getUser(request));

        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    
    /**
     * ���� Report �� ��ȸ�Ѵ�.
     * @param request
     * @param maWoResultMstrDetailForm
     * @param response 
     * @throws DRException 
     * @throws JRException 
     * @throws IOException 
     */
    private void findPmGnlReport(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm) throws JRException, DRException, IOException
    {
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService");
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoResultMstrDetailService.getPmGnlReportView(maWoResultMstrDetailDTO);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);

        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoResultPmGnlMstrDetail",strJson, getUser(request));
        

        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    
    /**
     * ���� Report(�з°�) �� ��ȸ�Ѵ�.
     * @param request
     * @param maWoResultMstrDetailForm
     * @param response 
     * @throws DRException 
     * @throws JRException 
     * @throws IOException 
     */
    private void findPmPrsReport(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm) throws JRException, DRException, IOException
    {
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService");
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoResultMstrDetailService.getPmPrsReportView(maWoResultMstrDetailDTO);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        
        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoResultPmGnlMstrDetail",strJson, getUser(request));

        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    
    /**
     * ���� Report(����) �� ��ȸ�Ѵ�.
     * @param request
     * @param maWoResultMstrDetailForm
     * @param response 
     * @throws DRException 
     * @throws JRException 
     * @throws IOException 
     */
    private void findPmSclReport(HttpServletRequest request, MaWoResultMstrDetailForm maWoResultMstrDetailForm) throws JRException, DRException, IOException
    {
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService");
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoResultMstrDetailService.getPmSclReportView(maWoResultMstrDetailDTO);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);

        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maWoResultPmSclMstrDetail",strJson, getUser(request));


        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    
    // �۾���ȹ��� ���翩�� �˻�
    private void woPlanCheck(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO();
        
        String isExist = maWoResultMstrDetailService.woPlanCheck(maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxDesc(request, isExist);
    }
    
    
    // ���¸� ������ ���(ex)���� �۾����->�۾���)
    private void updateWoStatus(MaWoResultMstrDetailForm maWoResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) getBean("maWoResultMstrDetailService",request);
    	MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService");
    	
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailForm.getMaWoResultMstrDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrDetailForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoResultMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());

        User loginUser = getUser(request);
        
        maWoResultMstrDetailService.updateWoStatus(maWoResultMstrDetailDTO, loginUser);
        
        setAjaxStatus(request);
    }
    
   
}