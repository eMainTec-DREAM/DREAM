package dream.mgr.exldata.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.AuthAction;
import dream.mgr.exldata.dto.MgrExldataUploadDetailDTO;
import dream.mgr.exldata.form.MgrExldataUploadDetailForm;
import dream.mgr.exldata.service.MgrExldataUploadDetailService;


/**
 * 엑셀업로드 - 상세 action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrExldataUploadDetail" name="mgrExldataUploadDetailForm"
 *                input="/dream/mgr/exldata/mgrExldataUploadDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrExldataUploadDetail" path="/dream/mgr/exldata/mgrExldataUploadDetail.jsp"
 *                        redirect="false"
 */
public class MgrExldataUploadDetailAction extends AuthAction
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
        MgrExldataUploadDetailForm mgrExldataUploadDetailForm = (MgrExldataUploadDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") mgrExldataUploadDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(mgrExldataUploadDetailForm.getMgrExldataUploadDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrExldataUploadDetailForm.getStrutsAction())
        {
            case MgrExldataUploadDetailAction.DOCLIB_DETAIL_INIT:
                returnActionForward = mapping.getInputForward();
                break;
            case MgrExldataUploadDetailAction.DOCLIB_DETAIL_UPLOAD:
            	insertDetail(mgrExldataUploadDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrExldataUploadDetailAction.DOCLIB_FILE_SAVE:
                fileUpload(mgrExldataUploadDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrExldataUploadDetailAction.DOCLIB_FILE_DELETE:
                fileDelete(mgrExldataUploadDetailForm, request, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void fileDelete(MgrExldataUploadDetailForm mgrExldataUploadDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        MgrExldataUploadDetailService mgrExldataUploadDetailService = (MgrExldataUploadDetailService) getBean("mgrExldataUploadDetailService",request);
        
        MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO = mgrExldataUploadDetailForm.getMgrExldataUploadDetailDTO();
        
        mgrExldataUploadDetailDTO.setEnterBy(getUser(request).getUserId());
        mgrExldataUploadDetailDTO.setCompNo(getUser(request).getCompNo());
        
        mgrExldataUploadDetailService.deleteAutoFiles(mgrExldataUploadDetailDTO,mgrExldataUploadDetailForm.getDeleteRows());
    }

    private void fileUpload(MgrExldataUploadDetailForm mgrExldataUploadDetailForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        MgrExldataUploadDetailService mgrExldataUploadDetailService = (MgrExldataUploadDetailService) getBean("mgrExldataUploadDetailService",request);
        
        MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO = mgrExldataUploadDetailForm.getMgrExldataUploadDetailDTO();
        
        mgrExldataUploadDetailDTO.setEnterBy(getUser(request).getUserId());
        mgrExldataUploadDetailDTO.setCompNo(getUser(request).getCompNo());
        
        Map<String,String> rtnMsg = mgrExldataUploadDetailService.uploadAutoFiles(mgrExldataUploadDetailDTO, request, response, getUser(request));
        

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
     * @param mgrExldataUploadDetailForm
     * @param request
     */
    private void insertDetail(MgrExldataUploadDetailForm mgrExldataUploadDetailForm, HttpServletRequest request) throws Exception
    {
        MgrExldataUploadDetailService mgrExldataUploadDetailService = (MgrExldataUploadDetailService) getBean("mgrExldataUploadDetailService",request);
        
        MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO = mgrExldataUploadDetailForm.getMgrExldataUploadDetailDTO();
        
        mgrExldataUploadDetailDTO.setEnterBy(getUser(request).getUserId());
        mgrExldataUploadDetailDTO.setCompNo(getUser(request).getCompNo());
        
        mgrExldataUploadDetailService.insertDetail(mgrExldataUploadDetailDTO, mgrExldataUploadDetailForm.getFileNames(), getUser(request));
        
        setAjaxStatus(request);
    }
    
}