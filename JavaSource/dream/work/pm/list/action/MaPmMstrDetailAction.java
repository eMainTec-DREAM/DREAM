package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.form.MaPmMstrDetailForm;
import dream.work.pm.list.service.MaPmMstrDetailService;

/**
 * 설비종류 - 상세 action
 * 
 * @author jung7126
 * @version $Id: MaPmMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maPmMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/maPmMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmInsMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/ins/maPmInsMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmRprMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/work/maPmRprMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmRplMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/work/maPmRplMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmCalEtcMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/cal/maPmCalEtcMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmClnMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/work/maPmClnMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmOilMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/work/maPmOilMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmTrMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/tr/maPmTrMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmGmMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/work/maPmGmMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRInsDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListRInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListDInsDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListDInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListEInsDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListEInsDetail.jsp" scope="request"
 *                validate="false"         
 * @struts:action path="/workPmListPtrlDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/workPmListPtrlDetail.jsp" scope="request"
 *                validate="false"                                     
 * @struts:action path="/workListCinsResultMstrDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/list/workListCinsResultMstrDetail.jsp" scope="request"
 *                validate="false"       
 * @struts:action path="/workPmListUInsDetail" name="maPmMstrDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListUInsDetail.jsp" scope="request"
 *                validate="false"                                 
 */
public class MaPmMstrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_MSTR_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int PM_MSTR_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int PM_MSTR_DETAIL_UPDATE 		= 6003;
    /** 청소설비, 윤활설비 있는지 확인 */
    public static final int PM_MSTR_DETAIL_CHECK 		= 8004;
    /** 주기 번호 중복 체크 */
    public static final int PM_MSTR_DETAIL_PM_NO_CHECK	= 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrDetailForm maPmMstrDetailForm = (MaPmMstrDetailForm) form;

        super.updateAudit(maPmMstrDetailForm.getMaPmMstrDetailDTO().getAuditKey()==""?maPmMstrDetailForm.getMaPmMstrCommonDTO().getAuditKey():maPmMstrDetailForm.getMaPmMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maPmMstrDetailForm.getStrutsAction())
        {
            case MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPmMstrDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPmMstrDetailAction.PM_MSTR_DETAIL_INPUT:
            	insertDetail(maPmMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrDetailAction.PM_MSTR_DETAIL_UPDATE:
            	updateDetail(maPmMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrDetailAction.PM_MSTR_DETAIL_CHECK:
            	checkDetail(maPmMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrDetailAction.PM_MSTR_DETAIL_PM_NO_CHECK:
            	pmNoCheck(maPmMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비종류 상세 조회
     * @author jung7126
     * @version $Id: MaPmMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPmMstrDetailForm maPmMstrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPmMstrDetailService maPmMstrDetailService = (MaPmMstrDetailService)getBean("maPmMstrDetailService");

        MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrDetailForm.getMaPmMstrCommonDTO();
        
        // 조회된 상세 부분
        MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailService.findDetail(maPmMstrCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPmMstrDetailForm.setMaPmMstrDetailDTO(maPmMstrDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPmMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailForm
     * @param request
     */
    private void insertDetail(MaPmMstrDetailForm maPmMstrDetailForm, HttpServletRequest request) throws Exception
    {
        MaPmMstrDetailService maPmMstrDetailService = (MaPmMstrDetailService) getBean("maPmMstrDetailService");
        
        MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailForm.getMaPmMstrDetailDTO();
        
        maPmMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrDetailService.insertDetail(maPmMstrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPmMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailForm
     * @param request
     */
    private void updateDetail(MaPmMstrDetailForm maPmMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrDetailService maPmMstrDetailService = (MaPmMstrDetailService) getBean("maPmMstrDetailService");
        
        MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailForm.getMaPmMstrDetailDTO();
        
        maPmMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrDetailService.updateDetail(maPmMstrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * 청소설비, 윤활설비 있는지 확인
     * @author  kim21017
     * @version $Id: MaPmMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailForm
     * @param request
     */
    private void checkDetail(MaPmMstrDetailForm maPmMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrDetailService maPmMstrDetailService = (MaPmMstrDetailService) getBean("maPmMstrDetailService");
        
        MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailForm.getMaPmMstrDetailDTO();
        
        String isValid = maPmMstrDetailService.checkDetail(maPmMstrDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
    /**
     * 주기#가 변경되었을시에 중복되는지 체크
     * @author js.lee
     * @since   1.0
     *
     * @param maPmMstrDetailForm
     * @param request
     * @throws Exception
     */
    private void pmNoCheck(MaPmMstrDetailForm maPmMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrDetailService maPmMstrDetailService = (MaPmMstrDetailService) getBean("maPmMstrDetailService");
        MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailForm.getMaPmMstrDetailDTO();
        String isValid = maPmMstrDetailService.pmNoCheck(maPmMstrDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
}