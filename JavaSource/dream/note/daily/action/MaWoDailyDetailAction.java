package dream.note.daily.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.note.daily.form.MaWoDailyDetailForm;
import dream.note.daily.service.MaWoDailyDetailService;

/**
 * �����۾� - �� action
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maWoDailyDetail" name="maWoDailyDetailForm"
 *                input="/dream/note/daily/maWoDailyDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyDetail" path="/dream/note/daily/maWoDailyDetail.jsp"
 *                        redirect="false"
 */
public class MaWoDailyDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_DAILY_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int WO_DAILY_DETAIL_UPDATE       = 6002;
    /** �Է� */
    public static final int WO_DAILY_DETAIL_INPUT        = 5003;
    /** �Ϸ� */
    public static final int WO_DAILY_DETAIL_COMPLETE     = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyDetailForm maWoDailyDetailForm = (MaWoDailyDetailForm) form;
        
        super.updateAudit(maWoDailyDetailForm.getMaWoDailyDetailDTO().getAuditKey()==""?maWoDailyDetailForm.getMaWoDailyCommonDTO().getAuditKey():maWoDailyDetailForm.getMaWoDailyDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoDailyDetailForm.getStrutsAction())
        {
            case MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoDailyDetailForm);
                returnActionForward = mapping.findForward("maWoDailyDetail");
                break;
            case MaWoDailyDetailAction.WO_DAILY_DETAIL_UPDATE:
            	updateDetail(maWoDailyDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoDailyDetailAction.WO_DAILY_DETAIL_INPUT:
                insertDetail(maWoDailyDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoDailyDetailAction.WO_DAILY_DETAIL_COMPLETE:
            	completeDetail(maWoDailyDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoDailyDetailAction.BASE_ACTION_REPORT:
            	findReport(request, maWoDailyDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDailyDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     *  �� ��ȸ
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoDailyDetailForm maWoDailyDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoDailyDetailService maWoDailyDetailService = (MaWoDailyDetailService)getBean("maWoDailyDetailService", request);
    	
    	MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyDetailForm.getMaWoDailyCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyDetailService.findDetail(maWoDailyCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoDailyDetailForm.setMaWoDailyDetailDTO(maWoDailyDetailDTO);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyDetailForm
     * @param request
     */
    private void updateDetail(MaWoDailyDetailForm maWoDailyDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoDailyDetailService maWoDailyDetailService = (MaWoDailyDetailService) getBean("maWoDailyDetailService", request);
        
        MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyDetailForm.getMaWoDailyDetailDTO();
        
        maWoDailyDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDailyDetailService.updateDetail(maWoDailyDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyDetailForm
     * @param request
     */
    private void insertDetail(MaWoDailyDetailForm maWoDailyDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoDailyDetailService maWoDailyDetailService = (MaWoDailyDetailService) getBean("maWoDailyDetailService", request);
        
        MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyDetailForm.getMaWoDailyDetailDTO();
        MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyDetailForm.getMaWoDailyCommonDTO();
        
        maWoDailyDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoDailyCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDailyDetailService.insertDetail(maWoDailyDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * Report �� ��ȸ�Ѵ�.
     * @param request
     * @param maWoDailyDetailForm
     */
    private void findReport(HttpServletRequest request, MaWoDailyDetailForm maWoDailyDetailForm)
    {
    	MaWoDailyDetailService maWoDailyDetailService = (MaWoDailyDetailService) getBean("maWoDailyDetailService", request);
        
        MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyDetailForm.getMaWoDailyDetailDTO();
        
        maWoDailyDetailDTO.setCompNo(getUser(request).getCompNo());
        maWoDailyDetailDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoDailyDetailService.getReportView(maWoDailyDetailDTO,getUser(request));

        // ��ȸ�� List �� form�� �����Ѵ�.
        if ("140".equals(getUser(request).getCompNo())&&"SLP".equals(getUser(request).getPlant())) {
        	request.setAttribute(REPORT_NAME_ATTRIBUTE, "woDailyList_SLP");
        }else{
        	request.setAttribute(REPORT_NAME_ATTRIBUTE, "woDailyList");
        }
        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }


    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyDetailForm
     * @param request
     */
    private void completeDetail(MaWoDailyDetailForm maWoDailyDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoDailyDetailService maWoDailyDetailService = (MaWoDailyDetailService) getBean("maWoDailyDetailService", request);
        
        MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyDetailForm.getMaWoDailyDetailDTO();
        MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyDetailForm.getMaWoDailyCommonDTO();
        
        maWoDailyDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoDailyCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDailyDetailService.updateStatus(maWoDailyDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}