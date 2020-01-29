package dream.mgr.cdsys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.form.MgrCdSysCodeDetailForm;
import dream.mgr.cdsys.service.MgrCdSysCodeDetailService;

/**
 * 시스템 코드 - 분류 팝업
 * @author  youngjoo38
 * @version $Id: MgrCdSysCodeDetailAction.java,v 1.0 2015/12/04 09:09:30 youngjoo38 Exp $
 * @since   1.0
 * @struts:action path="/mgrCdSysCodeDetail" name="mgrCdSysCodeDetailForm"
 *                input="/dream/mgr/cdsys/mgrCdSysCodeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCdSysCodeDetail" path="/dream/mgr/cdsys/mgrCdSysCodeDetail.jsp"
 *                        redirect="false"
 */
public class MgrCdSysCodeDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_CODE_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int LISTTYPE_CODE_DETAIL_UPDATE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCdSysCodeDetailForm mgrCdSysCodeDetailForm = (MgrCdSysCodeDetailForm) form;
        switch (mgrCdSysCodeDetailForm.getStrutsAction())
        {
            case MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrCdSysCodeDetailForm);
                returnActionForward = mapping.findForward("mgrCdSysCodeDetail");
                break;
            case MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_UPDATE:
            	updateDetail(mgrCdSysCodeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrCdSysCodeDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 시스템코드 분류 조회
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrCdSysCodeDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrCdSysCodeDetailForm mgrCdSysCodeDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MgrCdSysCodeDetailService mgrCdSysCodeDetailService = (MgrCdSysCodeDetailService)getBean("mgrCdSysCodeDetailService");

    	MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysCodeDetailForm.getMgrCdSysCommonDTO();
    	MgrCdSysCodeListDTO mgrCdSysCodeListDTO = mgrCdSysCodeDetailForm.getMgrCdSysCodeListDTO();
    	
        // 조회된 상세 부분
        MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO = mgrCdSysCodeDetailService.findDetail(mgrCdSysCommonDTO, mgrCdSysCodeListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        mgrCdSysCodeDetailForm.setMgrCdSysCodeDetailDTO(mgrCdSysCodeDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCodeDetailForm
     * @param request
     */
    private void updateDetail(MgrCdSysCodeDetailForm mgrCdSysCodeDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrCdSysCodeDetailService mgrCdSysCodeDetailService = (MgrCdSysCodeDetailService) getBean("mgrCdSysCodeDetailService");
        
        MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO = mgrCdSysCodeDetailForm.getMgrCdSysCodeDetailDTO();
        MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysCodeDetailForm.getMgrCdSysCommonDTO();
        
        mgrCdSysCodeDetailService.updateDetail(mgrCdSysCodeDetailDTO,mgrCdSysCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}