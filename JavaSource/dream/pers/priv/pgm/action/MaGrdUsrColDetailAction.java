package dream.pers.priv.pgm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.form.MaGrdUsrColDetailForm;
import dream.pers.priv.pgm.service.MaGrdUsrColDetailService;

/**
 * 칼럼 상세
 * @author  jung7126
 * @version $Id: MaGrdUsrColDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdUsrColDetail" name="maGrdUsrColDetailForm"
 *                input="/dream/pers/priv/pgm/maGrdUsrColDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdUsrColDetail" path="/dream/pers/priv/pgm/maGrdUsrColDetail.jsp"
 *                        redirect="false"
 */
public class MaGrdUsrColDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int GRD_USR_COL_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int GRD_USR_COL_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int GRD_USR_COL_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdUsrColDetailForm maGrdUsrColDetailForm = (MaGrdUsrColDetailForm) form;
        switch (maGrdUsrColDetailForm.getStrutsAction())
        {
            case MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maGrdUsrColDetailForm);
                returnActionForward = mapping.findForward("maGrdUsrColDetail");
                break;
            case MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_UPDATE:
            	updateDetail(maGrdUsrColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_INPUT:
            	insertDetail(maGrdUsrColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maGrdUsrColDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 버튼 상세 조회
     * @author kim2107
     * @version $Id: MaGrdUsrColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdUsrColDetailForm
     */
    private void findDetail(HttpServletRequest request, MaGrdUsrColDetailForm maGrdUsrColDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaGrdUsrColDetailService maGrdUsrColDetailService = (MaGrdUsrColDetailService)getBean("maGrdUsrColDetailService");

    	// 넘겨진 pageId 구함
        String pgGridColId = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO().getPgGridColId();
        // 넘겨진 grdColId 구함
        String usrPgGridColId = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO().getUsrPgGridColId();
        
        // 조회된 상세 부분
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = maGrdUsrColDetailService.findDetail(pgGridColId, usrPgGridColId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maGrdUsrColDetailForm.setMaGrdUsrColDetailDTO(maGrdUsrColDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaGrdUsrColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailForm
     * @param request
     */
    private void updateDetail(MaGrdUsrColDetailForm maGrdUsrColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrColDetailService maGrdUsrColDetailService = (MaGrdUsrColDetailService) getBean("maGrdUsrColDetailService");
        
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = maGrdUsrColDetailForm.getMaGrdUsrColDetailDTO();
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrColDetailService.updateDetail(maGrdUsrColDetailDTO,maGrdUsrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaGrdUsrColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailForm
     * @param request
     */
    private void insertDetail(MaGrdUsrColDetailForm maGrdUsrColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrColDetailService maGrdUsrColDetailService = (MaGrdUsrColDetailService) getBean("maGrdUsrColDetailService");
        
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = maGrdUsrColDetailForm.getMaGrdUsrColDetailDTO();
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrColDetailForm.getMaGrdUsrDetailDTO();
        
        maGrdUsrColDetailDTO.setUsrPgGridId(maGrdUsrColDetailForm.getMaGrdUsrDetailDTO().getUsrPgGridId());
        
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrColDetailService.insertDetail(maGrdUsrColDetailDTO, maGrdUsrCommonDTO, getUser(request), maGrdUsrDetailDTO);
        
        setAjaxStatus(request);
    }
}