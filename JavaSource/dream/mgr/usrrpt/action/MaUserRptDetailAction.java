package dream.mgr.usrrpt.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.jasper.JasperFactory;
import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;
import dream.mgr.usrrpt.form.MaUserRptDetailForm;
import dream.mgr.usrrpt.service.MaUserRptDetailService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * 메뉴 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaUserRptDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maUserRptDetail" name="maUserRptDetailForm"
 *                input="/dream/mgr/usrrpt/maUserRptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptDetail" path="/dream/mgr/usrrpt/maUserRptDetail.jsp"
 *                        redirect="false"
 */
public class MaUserRptDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_RPT_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int USER_RPT_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int USER_RPT_DETAIL_UPDATE 		= 6003;
    /** Report */
    public static final int USER_RPT_DETAIL_PRINT 		= 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptDetailForm maUserRptDetailForm = (MaUserRptDetailForm) form;
        
        super.updateAudit(maUserRptDetailForm.getMaUserRptDetailDTO().getAuditKey()==""?maUserRptDetailForm.getMaUserRptCommonDTO().getAuditKey():maUserRptDetailForm.getMaUserRptDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
                
        switch (maUserRptDetailForm.getStrutsAction())
        {
            case MaUserRptDetailAction.USER_RPT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maUserRptDetailForm);
                returnActionForward = mapping.findForward("maUserRptDetail");
                break;
            case MaUserRptDetailAction.USER_RPT_DETAIL_INPUT:
            	insertDetail(maUserRptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptDetailAction.USER_RPT_DETAIL_UPDATE:
            	updateDetail(maUserRptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptDetailAction.USER_RPT_DETAIL_PRINT:
            	reportCall(maUserRptDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("maUserRptDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void reportCall(MaUserRptDetailForm maUserRptDetailForm, HttpServletRequest request,HttpServletResponse response) throws JRException, DRException, IOException {
    	// Service 객체 생성
    	MaUserRptDetailService maUserRptDetailService = (MaUserRptDetailService)getBean("maUserRptDetailService");

    	String errMsg = "";
        //리스트를 조회한다.
    	Map<String, List<Map>> resultMap = null;
		try {
			resultMap = maUserRptDetailService.makeReport(maUserRptDetailForm.getMaUserRptCommonDTO(), getUser(request));
		} catch (Exception e) {
			e.printStackTrace();
			
			errMsg = e.getLocalizedMessage();
			errMsg = errMsg.split(":")[errMsg.split(":").length-1];
//			resultList = new ArrayList();
			resultMap = new HashMap<String, List<Map>>();
			resultMap.put("colList", new ArrayList());
			resultMap.put("resultList", new ArrayList());
			
			
		}finally{
		}
		
		response.getWriter().print(errMsg);
		//request.setAttribute("MESSAGE", errMsg);
//    	Map<String, List<Map<String,String>>> resultMap = maUserRptDetailService.makeReport(maUserRptDetailForm.getMaUserRptCommonDTO(), getUser(request));
	
    	JasperFactory jf = new JasperFactory();
    	
    	jf.setMainTitle(maUserRptDetailForm.getMaUserRptDetailDTO().getTitle());
    	jf.setColList(resultMap.get("colList"));
    	jf.setResultList(resultMap.get("resultList"));
//    	jf.setLogoImgName(getImgPath("mware_login.png"));
    	jf.setSubTitle(maUserRptDetailForm.getMaUserRptDetailDTO().getDescription());
    	
    	jf.view();
    }

	/**
     * 버튼 상세 조회
     * @author kim2107
     * @version $Id: MaUserRptDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maUserRptDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserRptDetailForm maUserRptDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaUserRptDetailService maUserRptDetailService = (MaUserRptDetailService)getBean("maUserRptDetailService");

        // 넘겨진 메뉴Id 구함
        String usrrptId = maUserRptDetailForm.getMaUserRptCommonDTO().getUsrrptlistId();
        
        // 조회된 상세 부분
        MaUserRptDetailDTO maUserRptDetailDTO = maUserRptDetailService.findDetail(usrrptId, getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        maUserRptDetailForm.setMaUserRptDetailDTO(maUserRptDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaUserRptDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptDetailForm
     * @param request
     */
    private void insertDetail(MaUserRptDetailForm maUserRptDetailForm, HttpServletRequest request) throws Exception
    {
        MaUserRptDetailService maUserRptDetailService = (MaUserRptDetailService) getBean("maUserRptDetailService");
        
        MaUserRptDetailDTO maUserRptDetailDTO = maUserRptDetailForm.getMaUserRptDetailDTO();
        
        maUserRptDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maUserRptDetailService.insertDetail(maUserRptDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaUserRptDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptDetailForm
     * @param request
     */
    private void updateDetail(MaUserRptDetailForm maUserRptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptDetailService maUserRptDetailService = (MaUserRptDetailService) getBean("maUserRptDetailService");
        
        MaUserRptDetailDTO maUserRptDetailDTO = maUserRptDetailForm.getMaUserRptDetailDTO();
        
        maUserRptDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maUserRptDetailService.updateDetail(maUserRptDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}