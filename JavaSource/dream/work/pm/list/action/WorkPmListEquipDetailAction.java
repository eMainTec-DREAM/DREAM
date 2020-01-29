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
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.form.WorkPmListEquipDetailForm;
import dream.work.pm.list.service.WorkPmListEquipDetailService;

/**
 * 예방설비
 * @author  kim21017
 * @version $Id: WorkPmListEquipDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmListEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/workPmListEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRprEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/work/workPmListRprEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRplEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/work/workPmListRplEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListGmEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/work/workPmListGmEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRInsEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListRInsEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListDInsEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListDInsEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListEInsEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListEInsEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListInsEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListInsEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListCalEquipDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/cal/workPmListCalEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListPtrlRtDetail" name="workPmListEquipDetailForm"
 *                input="/dream/work/pm/list/workPmListPtrlRtDetail.jsp" scope="request"
 *                validate="false"
 */
public class WorkPmListEquipDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PM_EQ_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int WORK_PM_EQ_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WORK_PM_EQ_DETAIL_INPUT 	= 5003;
    /** 사진 */
    public static final int WORK_PM_EQ_DETAIL_PHOTO     = 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListEquipDetailForm workPmListEquipDetailForm = (WorkPmListEquipDetailForm) form;
        
        super.updateAudit(workPmListEquipDetailForm.getWorkPmListEquipDetailDTO().getAuditKey()==""?workPmListEquipDetailForm.getMaPmMstrCommonDTO().getAuditKey():workPmListEquipDetailForm.getWorkPmListEquipDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmListEquipDetailForm.getStrutsAction())
        {
            case WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workPmListEquipDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_UPDATE:
            	updateDetail(workPmListEquipDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_INPUT:
            	insertDetail(workPmListEquipDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_PHOTO:
                findSlideImage(workPmListEquipDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 상세 조회
     * @author kim2107
     * @version $Id: WorkPmListEquipDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListEquipDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmListEquipDetailForm workPmListEquipDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkPmListEquipDetailService workPmListEquipDetailService = (WorkPmListEquipDetailService)getBean("workPmListEquipDetailService");
    	WorkPmListEquipDetailDTO workPmListEquipDetailDTO = workPmListEquipDetailForm.getWorkPmListEquipDetailDTO();

    	// 작업결과Id 구함
        String pmId = workPmListEquipDetailForm.getMaPmMstrCommonDTO().getPmId();
        // 넘겨진 투입자재id 구함
        String pmEquipId = workPmListEquipDetailForm.getMaPmMstrCommonDTO().getPmEquipId();
        
        // 조회된 상세 부분
        workPmListEquipDetailDTO = workPmListEquipDetailService.findDetail(pmId, pmEquipId, getUser(request));

        // 조회된 상세  셋팅한다.
        workPmListEquipDetailForm.setWorkPmListEquipDetailDTO(workPmListEquipDetailDTO);
        
        request.setAttribute("slideFileList", workPmListEquipDetailDTO.getSlideFileList());
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkPmListEquipDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailForm
     * @param request
     */
    private void updateDetail(WorkPmListEquipDetailForm workPmListEquipDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListEquipDetailService workPmListEquipDetailService = (WorkPmListEquipDetailService) getBean("workPmListEquipDetailService");
        
        WorkPmListEquipDetailDTO workPmListEquipDetailDTO = workPmListEquipDetailForm.getWorkPmListEquipDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListEquipDetailForm.getMaPmMstrCommonDTO();
        workPmListEquipDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListEquipDetailService.updateDetail(workPmListEquipDetailDTO,maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkPmListEquipDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailForm
     * @param request
     */
    private void insertDetail(WorkPmListEquipDetailForm workPmListEquipDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListEquipDetailService workPmListEquipDetailService = (WorkPmListEquipDetailService) getBean("workPmListEquipDetailService");
        
        WorkPmListEquipDetailDTO workPmListEquipDetailDTO = workPmListEquipDetailForm.getWorkPmListEquipDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListEquipDetailForm.getMaPmMstrCommonDTO();
        workPmListEquipDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListEquipDetailService.insertDetail(workPmListEquipDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    private void findSlideImage(WorkPmListEquipDetailForm workPmListEquipDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
    	WorkPmListEquipDetailService workPmListEquipDetailService = (WorkPmListEquipDetailService)getBean("workPmListEquipDetailService");

        // 조회된 상세 부분
        List  list = workPmListEquipDetailService.findSlideImage(workPmListEquipDetailForm.getWorkPmListEquipDetailDTO() , getUser(request));
 
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
        
    }
}