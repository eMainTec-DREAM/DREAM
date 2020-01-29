package dream.pers.priv.pgm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.SuperAuthAction;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.form.MaGrdUsrDetailForm;
import dream.pers.priv.pgm.service.MaGrdUsrDetailService;

/**
 * 칼럼 상세
 * @author  jung7126
 * @version $Id: MaGrdUsrDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdUsrDetail" name="maGrdUsrDetailForm"
 *                input="/dream/pers/priv/pgm/maGrdUsrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdUsrDetail" path="/dream/pers/priv/pgm/maGrdUsrDetail.jsp"
 *                        redirect="false"
 */
public class MaGrdUsrDetailAction extends SuperAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int GRD_USR_DETAIL_INIT 	= 1001;
    /** 수정 */
    public static final int GRD_USR_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int GRD_USR_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdUsrDetailForm maGrdUsrDetailForm = (MaGrdUsrDetailForm) form;
        switch (maGrdUsrDetailForm.getStrutsAction())
        {
            case MaGrdUsrDetailAction.GRD_USR_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maGrdUsrDetailForm);
                returnActionForward = mapping.findForward("maGrdUsrDetail");
                break;
            case MaGrdUsrDetailAction.GRD_USR_DETAIL_UPDATE:
            	updateDetail(maGrdUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdUsrDetailAction.GRD_USR_DETAIL_INPUT:
            	insertDetail(maGrdUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                this.findDetail(request, maGrdUsrDetailForm);
                returnActionForward = mapping.findForward("maGrdUsrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 버튼 상세 조회
     * @author kim2107
     * @version $Id: MaGrdUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdUsrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaGrdUsrDetailForm maGrdUsrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaGrdUsrDetailService maGrdUsrDetailService = (MaGrdUsrDetailService)getBean("maGrdUsrDetailService");

    	// 넘겨진 pageId 구함
    	MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrDetailForm.getMaGrdUsrCommonDTO();
        
        // 조회된 상세 부분
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrDetailService.findDetail(maGrdUsrCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maGrdUsrDetailForm.setMaGrdUsrDetailDTO(maGrdUsrDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaGrdUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailForm
     * @param request
     */
    private void updateDetail(MaGrdUsrDetailForm maGrdUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrDetailService maGrdUsrDetailService = (MaGrdUsrDetailService) getBean("maGrdUsrDetailService");
        
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrDetailForm.getMaGrdUsrDetailDTO();
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrDetailService.updateDetail(maGrdUsrDetailDTO,maGrdUsrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaGrdUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailForm
     * @param request
     */
    private void insertDetail(MaGrdUsrDetailForm maGrdUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrDetailService maGrdUsrDetailService = (MaGrdUsrDetailService) getBean("maGrdUsrDetailService");
        
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrDetailForm.getMaGrdUsrDetailDTO();
        
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrDetailService.insertDetail(maGrdUsrDetailDTO, maGrdUsrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}