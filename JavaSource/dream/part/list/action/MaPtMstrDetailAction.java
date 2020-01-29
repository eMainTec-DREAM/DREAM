package dream.part.list.action;

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
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.list.form.MaPtMstrDetailForm;
import dream.part.list.service.MaPtMstrDetailService;

/**
 * 보전자재분류(마스터) - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maPtMstrDetail" name="maPtMstrDetailForm"
 *                input="/dream/part/list/maPtMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrDetail" path="/dream/part/list/maPtMstrDetail.jsp"
 *                        redirect="false"
 */
public class MaPtMstrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_DETAIL_INIT 			= 8001;
    /** 저장 */
    public static final int PTMSTR_DETAIL_INPUT 		= 5000;
    /** 수정 */
    public static final int PTMSTR_DETAIL_UPDATE 		= 6000;
    /** 중복 체크 */
    public static final int PTMSTR_DETAIL_CHECK 		= 8004;
    
    public static final int PTMSTR_DETAIL_PHOTO         = 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrDetailForm maPtMstrDetailForm = (MaPtMstrDetailForm) form;
        
        super.updateAudit(maPtMstrDetailForm.getMaPtMstrDetailDTO().getAuditKey()==""?maPtMstrDetailForm.getMaPtMstrCommonDTO().getAuditKey():maPtMstrDetailForm.getMaPtMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maPtMstrDetailForm.getStrutsAction())
        {
            case MaPtMstrDetailAction.PTMSTR_DETAIL_INIT:
                this.findDetail(request, maPtMstrDetailForm);
                returnActionForward = mapping.findForward("maPtMstrDetail");
                break;
            case MaPtMstrDetailAction.PTMSTR_DETAIL_INPUT:
            	insertDetail(maPtMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrDetailAction.PTMSTR_DETAIL_UPDATE:
            	updateDetail(maPtMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrDetailAction.PTMSTR_DETAIL_CHECK:
            	validPartNo(maPtMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrDetailAction.PTMSTR_DETAIL_PHOTO:
                findSlideImage(maPtMstrDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maPtMstrDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void findSlideImage(MaPtMstrDetailForm maPtMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService)getBean("maPtMstrDetailService");

        // 조회된 상세 부분
        List  list = maPtMstrDetailService.findSlideImage(maPtMstrDetailForm.getMaPtMstrCommonDTO(), getUser(request));
 
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
        
    }

    /**
     * 보전자재분류(마스터) 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtMstrDetailForm maPtMstrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService)getBean("maPtMstrDetailService");

        // 넘겨진 부서코드 구함
//        String compNo = maPtMstrDetailForm.getMaPtMstrCommonDTO().getCompNo();
//        String partId = maPtMstrDetailForm.getMaPtMstrCommonDTO().getPartId();
        
        // 조회된 상세 부분
        MaPtMstrDetailDTO maPtMstrDetailDTO = maPtMstrDetailService.findDetail(maPtMstrDetailForm.getMaPtMstrCommonDTO(), getUser(request));
        
        List photoList = maPtMstrDetailService.getPhotoList(maPtMstrDetailForm.getMaPtMstrCommonDTO(), getUser(request));
        
        request.setAttribute("slideFileList",photoList);
        // 조회된 상세  셋팅한다.
        maPtMstrDetailForm.setMaPtMstrDetailDTO(maPtMstrDetailDTO);
    }
    
    
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtMstrDetailForm
     * @param request
     */
    private void insertDetail(MaPtMstrDetailForm maPtMstrDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService) getBean("maPtMstrDetailService");
        
        MaPtMstrDetailDTO maPtMstrDetailDTO = maPtMstrDetailForm.getMaPtMstrDetailDTO();
        
        maPtMstrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtMstrDetailService.insertDetail(maPtMstrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailForm
     * @param request
     */
    private void updateDetail(MaPtMstrDetailForm maPtMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService) getBean("maPtMstrDetailService");
        
        MaPtMstrDetailDTO maPtMstrDetailDTO = maPtMstrDetailForm.getMaPtMstrDetailDTO();
        
        maPtMstrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtMstrDetailService.updateDetail(maPtMstrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid part no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailForm
     * @param request
     */
    private void validPartNo(MaPtMstrDetailForm maPtMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService) getBean("maPtMstrDetailService");
        
        MaPtMstrDetailDTO maPtMstrDetailDTO = maPtMstrDetailForm.getMaPtMstrDetailDTO();
        
        maPtMstrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPtMstrDetailService.validPartNo(maPtMstrDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}