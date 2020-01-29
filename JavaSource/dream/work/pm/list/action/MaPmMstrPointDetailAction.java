package dream.work.pm.list.action;

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

import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.form.MaPmMstrPointDetailForm;
import dream.work.pm.list.service.MaPmMstrPointDetailService;

/**
 * 작업결과 - 검사항목
 * @author  jung7126
 * @version $Id: MaPmMstrPointDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maPmMstrPointDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/work/pm/list/maPmMstrPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmMstrPointValueDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/work/pm/list/maPmMstrPointValueDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListPtrlRtPointDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/work/pm/list/workPmListPtrlRtPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMstrPmInsPointDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/asset/list/maEqMstrPmInsPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMstrPmInsPointValueDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/asset/list/maEqMstrPmInsPointValueDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmMstrPointDetail" path="/dream/work/pm/list/maPmMstrPointDetail.jsp"
 *                        redirect="false"
 */
public class MaPmMstrPointDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_MSTR_POINT_DETAIL_INIT 	= 1001;
    /** 수정 */
    public static final int PM_MSTR_POINT_DETAIL_UPDATE = 1002;
    /** 입력 */
    public static final int PM_MSTR_POINT_DETAIL_INPUT 	= 1003;
    /** Slide Image 조회 */
    public static final int DETAIL_PHOTO        		= 8001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrPointDetailForm maPmMstrPointDetailForm = (MaPmMstrPointDetailForm) form;
        
        super.updateAudit(maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO().getAuditKey()==""?maPmMstrPointDetailForm.getMaPmMstrCommonDTO().getAuditKey():maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPmMstrPointDetailForm.getStrutsAction())
        {
            case MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPmMstrPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_UPDATE:
            	updateDetail(maPmMstrPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INPUT:
            	insertDetail(maPmMstrPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointDetailAction.DETAIL_PHOTO:
                findSlideImage(request, response, maPmMstrPointDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업결과-검사항목 상세 조회
     * @author kim2107
     * @version $Id: MaPmMstrPointDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPmMstrPointDetailForm maPmMstrPointDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService)getBean("maPmMstrPointDetailService");
    	MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointDetailForm.getMaPmMstrCommonDTO();
    	// 작업결과Id 구함
        String wkOrId = maPmMstrPointDetailForm.getMaPmMstrCommonDTO().getPmId();
        // 넘겨진 점검항목id 구함
        String pmPointId = maPmMstrPointDetailForm.getMaPmMstrCommonDTO().getPmPointId();

        // 조회된 상세 부분
        maPmMstrPointDetailDTO = maPmMstrPointDetailService.findDetail(wkOrId, pmPointId, getUser(request));
        // 조회된 상세  셋팅한다.
        maPmMstrPointDetailForm.setMaPmMstrPointDetailDTO(maPmMstrPointDetailDTO);
        
        request.setAttribute("slideFileList", maPmMstrPointDetailDTO.getSlideFileList());
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaPmMstrPointDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailForm
     * @param request
     */
    private void updateDetail(MaPmMstrPointDetailForm maPmMstrPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService) getBean("maPmMstrPointDetailService");
        
        MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointDetailForm.getMaPmMstrCommonDTO();
        maPmMstrPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPointDetailService.updateDetail(maPmMstrPointDetailDTO,maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaPmMstrPointDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailForm
     * @param request
     */
    private void insertDetail(MaPmMstrPointDetailForm maPmMstrPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService) getBean("maPmMstrPointDetailService");
        
        MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointDetailForm.getMaPmMstrCommonDTO();
        maPmMstrPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPointDetailService.insertDetail(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 점검 슬라이드 이미지
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param maPmMstrPointDetailForm
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(HttpServletRequest request, HttpServletResponse response, MaPmMstrPointDetailForm maPmMstrPointDetailForm) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service 객체 생성
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService) getBean("maPmMstrPointDetailService");
        
        // 조회된 상세 부분
        List list = maPmMstrPointDetailService.findSlideImage(maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
}