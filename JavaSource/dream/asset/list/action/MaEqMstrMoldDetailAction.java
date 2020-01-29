package dream.asset.list.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.microsoft.azure.storage.StorageException;

import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;
import dream.asset.list.form.MaEqMstrMoldDetailForm;
import dream.asset.list.service.MaEqMstrMoldDetailService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * 설비마스터 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaEqMstrMoldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqMstrMoldDetail" name="maEqMstrMoldDetailForm"
 *                input="/dream/asset/list/maEqMstrMoldDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrMoldDetail" path="/dream/asset/list/maEqMstrMoldDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrMoldDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_DETAIL_INIT 	    = 8001;
    /** 저장 */
    public static final int EQ_MSTR_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int EQ_MSTR_DETAIL_UPDATE 		= 6003;
    /** Slide Image 조회 */
    public static final int EQ_MSTR_DETAIL_PHOTO        = 8004;
    /** 완료 */
    public static final int EQ_MSTR_DETAIL_COMPLETE     = 6005;
    /** 복사 */
    public static final int EQ_MSTR_DETAIL_COPY     	= 5006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrMoldDetailForm maEqMstrMoldDetailForm = (MaEqMstrMoldDetailForm) form;
        
        super.updateAudit(maEqMstrMoldDetailForm.getMaEqMstrMoldDetailDTO().getAuditKey()==""?maEqMstrMoldDetailForm.getMaEqMstrCommonDTO().getAuditKey():maEqMstrMoldDetailForm.getMaEqMstrMoldDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrMoldDetailForm.getStrutsAction())
        {
            case MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrMoldDetailForm);
                returnActionForward = mapping.findForward("maEqMstrMoldDetail");
                break;
            case MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_INPUT:
            	insertDetail(maEqMstrMoldDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_UPDATE:
            	updateDetail(maEqMstrMoldDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_PHOTO:
                findSlideImage(maEqMstrMoldDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_COMPLETE:
            	completeDetail(maEqMstrMoldDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_COPY:
            	copyDetail(maEqMstrMoldDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldDetailAction.BASE_ACTION_REPORT:
            	findReport(request, maEqMstrMoldDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrMoldDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void findSlideImage(MaEqMstrMoldDetailForm maEqMstrMoldDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service 객체 생성
        MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService)getBean("maEqMstrMoldDetailService");

        // 조회된 상세 부분
        List list = maEqMstrMoldDetailService.findSlideImage(maEqMstrMoldDetailForm.getMaEqMstrCommonDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }

    /**
     * 설비마스터 상세 조회
     * @author kim2107
     * @version $Id: MaEqMstrMoldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrMoldDetailForm maEqMstrMoldDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService)getBean("maEqMstrMoldDetailService");

        // 조회된 상세 부분
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrMoldDetailService.findDetail(maEqMstrMoldDetailForm.getMaEqMstrCommonDTO(), getUser(request).getCompNo(), getUser(request));
        MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = maEqMstrMoldDetailService.findMoldDetail(maEqMstrMoldDetailForm.getMaEqMstrCommonDTO(), getUser(request));
        
        request.setAttribute("slideFileList", maEqMstrDetailDTO.getSlideFileList());
        // 조회된 상세  셋팅한다.
        maEqMstrMoldDetailForm.setMaEqMstrDetailDTO(maEqMstrDetailDTO);
        maEqMstrMoldDetailForm.setMaEqMstrMoldDetailDTO(maEqMstrMoldDetailDTO);
    }
    
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrMoldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrMoldDetailForm maEqMstrMoldDetailForm, HttpServletRequest request) throws Exception
    {
        MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService) getBean("maEqMstrMoldDetailService");
        
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrDetailDTO();
        MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrMoldDetailDTO();
        
        maEqMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrMoldDetailService.insertDetail(maEqMstrDetailDTO, maEqMstrMoldDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrMoldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrMoldDetailForm maEqMstrMoldDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService) getBean("maEqMstrMoldDetailService");
        
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrDetailDTO();
        MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrMoldDetailDTO();
        
        maEqMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrMoldDetailService.updateDetail(maEqMstrDetailDTO,maEqMstrMoldDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    private void completeDetail(MaEqMstrMoldDetailForm maEqMstrMoldDetailForm, HttpServletRequest request) throws IOException
    {
    	// Service 객체 생성
    	MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService) getBean("maEqMstrMoldDetailService");
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrDetailDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldDetailForm.getMaEqMstrCommonDTO();
    	maEqMstrMoldDetailService.completeDetail(maEqMstrCommonDTO, maEqMstrDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * detail copy
     * @author  syyang
     * @version $Id: MaEqMstrMoldDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldDetailForm
     * @param request
     */
    private void copyDetail(MaEqMstrMoldDetailForm maEqMstrMoldDetailForm, HttpServletRequest request) throws Exception
    {
        MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService) getBean("maEqMstrMoldDetailService");
        
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrDetailDTO();
        MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrMoldDetailDTO();
        
        maEqMstrMoldDetailService.copyDetail(maEqMstrDetailDTO, maEqMstrMoldDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * Report 를 조회한다.
     * @param request
     * @param eqMstrListForm
     * @throws IOException 
     * @throws DRException 
     * @throws JRException 
     */
    private void findReport(HttpServletRequest request, MaEqMstrMoldDetailForm maEqMstrMoldDetailForm) throws JRException, DRException, IOException
    {
    	MaEqMstrMoldDetailService maEqMstrMoldDetailService = (MaEqMstrMoldDetailService) getBean("maEqMstrMoldDetailService");
    	MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrMoldDetailForm.getMaEqMstrDetailDTO();
        maEqMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrDetailDTO.setUserLang(getUser(request).getLangId());
        maEqMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        List reportList = maEqMstrMoldDetailService.getReportView(maEqMstrDetailDTO);
        
        Map map = (Map)reportList.get(0);
	    Gson gson = new Gson();
	    String strJson = gson.toJson(map);
	    
	    ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("maEqMstrDetail",strJson, getUser(request));

        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
    }
}