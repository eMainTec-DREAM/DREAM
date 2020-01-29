package dream.mgr.manst.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;
import dream.mgr.manst.form.MaNstGrpDetailForm;
import dream.mgr.manst.service.MaNstGrpDetailService;

/**
 * 무정지대표라인 - 상세
 * 
 * @author kim2107
 * @version $Id: MaNstGrpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maNstGrpDetail" name="maNstGrpDetailForm"
 *                input="/dream/mgr/manst/maNstGrpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maNstGrpDetail" path="/dream/mgr/manst/maNstGrpDetail.jsp"
 *                        redirect="false"
 */
public class MaNstGrpDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int NST_GRP_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int NST_GRP_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int NST_GRP_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaNstGrpDetailForm maNstGrpDetailForm = (MaNstGrpDetailForm) form;
        
        switch (maNstGrpDetailForm.getStrutsAction())
        {
            case MaNstGrpDetailAction.NST_GRP_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maNstGrpDetailForm);
                returnActionForward = mapping.findForward("maNstGrpDetail");
                break;
            case MaNstGrpDetailAction.NST_GRP_DETAIL_INPUT:
            	insertDetail(maNstGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaNstGrpDetailAction.NST_GRP_DETAIL_UPDATE:
            	updateDetail(maNstGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maNstGrpDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비위치 상세 조회
     * @author kim2107
     * @version $Id: MaNstGrpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maNstGrpDetailForm
     */
    private void findDetail(HttpServletRequest request, MaNstGrpDetailForm maNstGrpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaNstGrpDetailService maNstGrpDetailService = (MaNstGrpDetailService)getBean("maNstGrpDetailService",request);
        
        // 조회된 상세 부분
        MaNstGrpDetailDTO maNstGrpDetailDTO = maNstGrpDetailService.findDetail(maNstGrpDetailForm.getMaNstGrpCommonDTO(),getUser(request));
        
        // 조회된 상세  셋팅한다.
        maNstGrpDetailForm.setMaNstGrpDetailDTO(maNstGrpDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaNstGrpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailForm
     * @param request
     */
    private void insertDetail(MaNstGrpDetailForm maNstGrpDetailForm, HttpServletRequest request) throws Exception
    {
        MaNstGrpDetailService maNstGrpDetailService = (MaNstGrpDetailService) getBean("maNstGrpDetailService");
        
        MaNstGrpDetailDTO maNstGrpDetailDTO = maNstGrpDetailForm.getMaNstGrpDetailDTO();
        
        maNstGrpDetailDTO.setEnterBy(getUser(request).getUserId());
        maNstGrpDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maNstGrpDetailService.insertDetail(maNstGrpDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaNstGrpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailForm
     * @param request
     */
    private void updateDetail(MaNstGrpDetailForm maNstGrpDetailForm, HttpServletRequest request) throws Exception
    {
    	MaNstGrpDetailService maNstGrpDetailService = (MaNstGrpDetailService) getBean("maNstGrpDetailService");
        
        MaNstGrpDetailDTO maNstGrpDetailDTO = maNstGrpDetailForm.getMaNstGrpDetailDTO();
        
        maNstGrpDetailDTO.setEnterBy(getUser(request).getUserId());
        maNstGrpDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maNstGrpDetailService.updateDetail(maNstGrpDetailDTO);
        
        setAjaxStatus(request);
    }
}