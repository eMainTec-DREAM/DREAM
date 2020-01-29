package dream.consult.program.setting.upload.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.ConsultAuthAction;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;
import dream.consult.program.setting.upload.form.ConsultPgmSettingUpdFileForm;
import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdFileService;


/**
 * 엑셀업로드 - 상세 action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultPgmSettingUpdFileDetail" name="consultPgmSettingUpdFileForm"
 *                input="/dream/consult/program/setting/upload/consultPgmSettingUpdFileDetail.jsp" scope="request"
 *                validate="false"
 */
public class ConsultPgmSettingUpdFileAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DOCLIB_DETAIL_INIT 		    = 8001;
    /** 업로드 */
    public static final int DOCLIB_DETAIL_UPLOAD        = 5002;
    /** 파일저장 */
    public static final int DOCLIB_FILE_SAVE            = 5004;
    /** 파일삭제 */
    public static final int DOCLIB_FILE_DELETE          = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmSettingUpdFileForm consultPgmSettingUpdFileForm = (ConsultPgmSettingUpdFileForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") consultPgmSettingUpdFileForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (consultPgmSettingUpdFileForm.getStrutsAction())
        {
            case ConsultPgmSettingUpdFileAction.DOCLIB_DETAIL_INIT:
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultPgmSettingUpdFileAction.DOCLIB_DETAIL_UPLOAD:
            	insertDetail(consultPgmSettingUpdFileForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmSettingUpdFileAction.DOCLIB_FILE_SAVE:
                fileUpload(consultPgmSettingUpdFileForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmSettingUpdFileAction.DOCLIB_FILE_DELETE:
                fileDelete(consultPgmSettingUpdFileForm, request, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void fileDelete(ConsultPgmSettingUpdFileForm consultPgmSettingUpdFileForm, HttpServletRequest request, HttpServletResponse response)
    {
        ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService) getBean("consultPgmSettingUpdFileService",request);
        
        ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = consultPgmSettingUpdFileForm.getConsultPgmSettingUpdFileDTO();
        
        consultPgmSettingUpdFileDTO.setEnterBy(getUser(request).getUserId());
        consultPgmSettingUpdFileDTO.setCompNo(getUser(request).getCompNo());
        
        consultPgmSettingUpdFileService.deleteAutoFiles(consultPgmSettingUpdFileDTO,consultPgmSettingUpdFileForm.getDeleteRows());
    }

    private void fileUpload(ConsultPgmSettingUpdFileForm consultPgmSettingUpdFileForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService) getBean("consultPgmSettingUpdFileService",request);
        
        ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = consultPgmSettingUpdFileForm.getConsultPgmSettingUpdFileDTO();
        
        consultPgmSettingUpdFileDTO.setEnterBy(getUser(request).getUserId());
        consultPgmSettingUpdFileDTO.setCompNo(getUser(request).getCompNo());
        
        Map<String,String> rtnMsg = consultPgmSettingUpdFileService.uploadAutoFiles(consultPgmSettingUpdFileDTO, request, response, getUser(request));
        

        if(rtnMsg.containsValue("ERROR"))
        {
        	Map rtnMap = new HashMap();
        	rtnMap.put("state", false);
        	rtnMap.put("extra", rtnMsg);
        	Gson gson = new Gson();
        	String jsonString = gson.toJson(rtnMap);

        	response.getWriter().print(jsonString);
        }
    }
    
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileForm
     * @param request
     */
    private void insertDetail(ConsultPgmSettingUpdFileForm consultPgmSettingUpdFileForm, HttpServletRequest request) throws Exception
    {
        ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService = (ConsultPgmSettingUpdFileService) getBean("consultPgmSettingUpdFileService",request);
        
        ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = consultPgmSettingUpdFileForm.getConsultPgmSettingUpdFileDTO();
        
        consultPgmSettingUpdFileDTO.setEnterBy(getUser(request).getUserId());
        consultPgmSettingUpdFileDTO.setCompNo(getUser(request).getCompNo());
        
        consultPgmSettingUpdFileService.insertDetail(consultPgmSettingUpdFileDTO, consultPgmSettingUpdFileForm.getFileNames(), getUser(request));
        
        setAjaxStatus(request);
    }
    
}