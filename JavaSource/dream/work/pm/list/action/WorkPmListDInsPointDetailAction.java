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

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;
import dream.work.pm.list.form.WorkPmListDInsPointDetailForm;
import dream.work.pm.list.service.WorkPmListDInsPointDetailService;

/**
 * WorkPmListDInsPoint Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmListDInsPointDetail" name="workPmListDInsPointDetailForm"
 *                input="/dream/work/pm/list/ins/workPmListDInsPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListDInsPointDetail" path="/dream/work/pm/list/ins/workPmListDInsPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmListDInsPointDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 6003;
    /** Slide Image 조회 */
    public static final int DETAIL_PHOTO        		= 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListDInsPointDetailForm workPmListDInsPointDetailForm = (WorkPmListDInsPointDetailForm) form;
        

        super.updateAudit(workPmListDInsPointDetailForm.getWorkPmListDInsPointDetailDTO().getAuditKey()==""?workPmListDInsPointDetailForm.getMaPmMstrCommonDTO().getAuditKey():workPmListDInsPointDetailForm.getWorkPmListDInsPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (workPmListDInsPointDetailForm.getStrutsAction())
        {
            case WorkPmListDInsPointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmListDInsPointDetailForm);
                returnActionForward = mapping.findForward("workPmListDInsPointDetail");
                break;
            case WorkPmListDInsPointDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workPmListDInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmListDInsPointDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workPmListDInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmListDInsPointDetailAction.DETAIL_PHOTO:
                findSlideImage(request, response, workPmListDInsPointDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workPmListDInsPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmListDInsPointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmListDInsPointDetailForm workPmListDInsPointDetailForm) throws Exception 
    {   
        WorkPmListDInsPointDetailService workPmListDInsPointDetailService = (WorkPmListDInsPointDetailService)getBean("workPmListDInsPointDetailService");
        
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListDInsPointDetailForm.getMaPmMstrCommonDTO();

        User user = getUser(request);
        WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO = workPmListDInsPointDetailService.findDetail(maPmMstrCommonDTO, user);
        workPmListDInsPointDetailForm.setWorkPmListDInsPointDetailDTO(workPmListDInsPointDetailDTO);
        
        request.setAttribute("slideFileList", workPmListDInsPointDetailDTO.getSlideFileList());
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmListDInsPointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmListDInsPointDetailForm workPmListDInsPointDetailForm) throws Exception
    {
        WorkPmListDInsPointDetailService workPmListDInsPointDetailService = (WorkPmListDInsPointDetailService)getBean("workPmListDInsPointDetailService");
        WorkPmListDInsPointDetailDTO  workPmListDInsPointDetailDTO = workPmListDInsPointDetailForm.getWorkPmListDInsPointDetailDTO(); 
        
        User user = getUser(request);
        workPmListDInsPointDetailService.insertDetail(workPmListDInsPointDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmListDInsPointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmListDInsPointDetailForm workPmListDInsPointDetailForm) throws Exception
    {
        WorkPmListDInsPointDetailService workPmListDInsPointDetailService = (WorkPmListDInsPointDetailService)getBean("workPmListDInsPointDetailService");
        WorkPmListDInsPointDetailDTO  workPmListDInsPointDetailDTO = workPmListDInsPointDetailForm.getWorkPmListDInsPointDetailDTO();
        
        User user = getUser(request);
        workPmListDInsPointDetailService.updateDetail(workPmListDInsPointDetailDTO, user);
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
     * @param workPmListDInsPointDetailForm
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(HttpServletRequest request, HttpServletResponse response, WorkPmListDInsPointDetailForm workPmListDInsPointDetailForm) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service 객체 생성
    	WorkPmListDInsPointDetailService workPmListDInsPointDetailService = (WorkPmListDInsPointDetailService)getBean("workPmListDInsPointDetailService");
        
        // 조회된 상세 부분
        List list = workPmListDInsPointDetailService.findSlideImage(workPmListDInsPointDetailForm.getWorkPmListDInsPointDetailDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
    
}
