package dream.tool.adj.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisDetailDTO;
import dream.tool.adj.form.MaPttDisDetailForm;
import dream.tool.adj.service.MaPttDisDetailService;

/**
 * 공기구반납 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPttDisDetail" name="maPttDisDetailForm"
 *                input="/dream/tool/adj/maPttDisDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttDisDetail" path="/dream/tool/adj/maPttDisDetail.jsp"
 *                        redirect="false"
 */
public class MaPttDisDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTDIS_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTDIS_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTDIS_DETAIL_UPDATE       = 6003;
    /** 폐기완료 */
    public static final int PTDIS_COMPLETE			  = 6004;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttDisDetailForm maPttDisDetailForm = (MaPttDisDetailForm) form;
        
        super.updateAudit(maPttDisDetailForm.getMaPttDisDetailDTO().getAuditKey()==""?maPttDisDetailForm.getMaPttDisCommonDTO().getAuditKey():maPttDisDetailForm.getMaPttDisDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttDisDetailForm.getStrutsAction())
        {
            case MaPttDisDetailAction.PTDIS_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPttDisDetailForm);
                returnActionForward = mapping.findForward("maPttDisDetail");
                break;
            case MaPttDisDetailAction.PTDIS_DETAIL_INPUT:
            	insertDetail(maPttDisDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttDisDetailAction.PTDIS_DETAIL_UPDATE:
            	updateDetail(maPttDisDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttDisDetailAction.PTDIS_COMPLETE:
            	 disPart(maPttDisDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPttDisDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 공기구반납 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttDisDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttDisDetailForm maPttDisDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPttDisDetailService maPttDisDetailService = (MaPttDisDetailService)getBean("maPttDisDetailService");
    	
    	MaPttDisCommonDTO maPttDisCommonDTO = maPttDisDetailForm.getMaPttDisCommonDTO();
    	
        // 조회된 상세 부분
        MaPttDisDetailDTO maPttDisDetailDTO = maPttDisDetailService.findDetail(getUser(request), maPttDisCommonDTO.getPtdisuselistId());
        
        // 조회된 상세  셋팅한다.
        maPttDisDetailForm.setMaPttDisDetailDTO(maPttDisDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailForm
     * @param request
     */
    private void insertDetail(MaPttDisDetailForm maPttDisDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttDisDetailService maPttDisDetailService = (MaPttDisDetailService) getBean("maPttDisDetailService");
        
        MaPttDisDetailDTO maPttDisDetailDTO = maPttDisDetailForm.getMaPttDisDetailDTO();
        
        maPttDisDetailDTO.setCompNo(getUser(request).getCompNo());
        maPttDisDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttDisDetailService.insertDetail(maPttDisDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailForm
     * @param request
     */
    private void updateDetail(MaPttDisDetailForm maPttDisDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttDisDetailService maPttDisDetailService = (MaPttDisDetailService) getBean("maPttDisDetailService");
        
        MaPttDisDetailDTO maPttDisDetailDTO = maPttDisDetailForm.getMaPttDisDetailDTO();
        
        maPttDisDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttDisDetailService.updateDetail(maPttDisDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * 출고
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailForm
     * @param request
     * @throws Exception 
     */
    private void disPart(MaPttDisDetailForm maPttDisDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttDisDetailService maPttDisDetailService = (MaPttDisDetailService) getBean("maPttDisDetailService");
        
        MaPttDisDetailDTO maPttDisDetailDTO = maPttDisDetailForm.getMaPttDisDetailDTO();
        
        maPttDisDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttDisDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValueArr[] = maPttDisDetailService.disPart(maPttDisDetailDTO, getUser(request));
        
//        setAjaxStatus(request);
        setAjaxDesc(request, rtnValueArr);
    }
    
}