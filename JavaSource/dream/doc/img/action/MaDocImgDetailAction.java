package dream.doc.img.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.SuperAuthAction;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;
import dream.doc.img.form.MaDocImgDetailForm;
import dream.doc.img.service.MaDocImgDetailService;


/**
 * 첨부문서 - 상세 action
 * 
 * @author jung7126
 * @version $Id: MaDocImgDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maDocImgDetail" name="maDocImgDetailForm"
 *                input="/dream/doc/img/maDocImgDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maDocImgPopDetail" name="maDocImgDetailForm"
 *                input="/dream/doc/img/maDocImgPopDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDocImgDetail" path="/dream/doc/img/maDocImgDetail.jsp"
 *                        redirect="false"
 */
public class MaDocImgDetailAction extends SuperAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DOCIMG_DETAIL_INIT 		    = 8001;
    /** 저장 */
    public static final int DOCIMG_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DOCIMG_DETAIL_UPDATE 		= 6003;
    /** 파일저장 */
    public static final int DOCIMG_FILE_SAVE            = 5004;
    /** 파일삭제 */
    public static final int DOCIMG_FILE_DELETE          = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocImgDetailForm maDocImgDetailForm = (MaDocImgDetailForm) form;
        
        super.updateAudit(maDocImgDetailForm.getMaDocImgDetailDTO().getAuditKey()==""?maDocImgDetailForm.getMaDocImgCommonDTO().getAuditKey():maDocImgDetailForm.getMaDocImgDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDocImgDetailForm.getStrutsAction())
        {
            case MaDocImgDetailAction.DOCIMG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maDocImgDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaDocImgDetailAction.DOCIMG_DETAIL_INPUT:
            	insertDetail(maDocImgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocImgDetailAction.DOCIMG_DETAIL_UPDATE:
            	updateDetail(maDocImgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocImgDetailAction.DOCIMG_FILE_SAVE:
                fileUpload(maDocImgDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocImgDetailAction.DOCIMG_FILE_DELETE:
                fileDelete(maDocImgDetailForm, request, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                //setObjectType(request, maDocImgDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void fileDelete(MaDocImgDetailForm maDocImgDetailForm, HttpServletRequest request, HttpServletResponse response)
    {
        MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService) getBean("maDocImgDetailService");
        
        MaDocImgDetailDTO maDocImgDetailDTO = maDocImgDetailForm.getMaDocImgDetailDTO();
        MaDocImgCommonDTO maDocImgCommonDTO = maDocImgDetailForm.getMaDocImgCommonDTO();
        
        maDocImgDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocImgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocImgDetailService.deleteAutoFiles(maDocImgDetailDTO,maDocImgDetailForm.getDeleteRows());
    }

    private void fileUpload(MaDocImgDetailForm maDocImgDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception
    {
        MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService) getBean("maDocImgDetailService");
        
        MaDocImgDetailDTO maDocImgDetailDTO = maDocImgDetailForm.getMaDocImgDetailDTO();
        MaDocImgCommonDTO maDocImgCommonDTO = maDocImgDetailForm.getMaDocImgCommonDTO();

        maDocImgDetailDTO.setCompNo(getUser(request).getCompNo());
        maDocImgDetailDTO.setEnterBy(getUser(request).getUserId());
        
        if(!"".equals(maDocImgCommonDTO.getCompNo())) maDocImgDetailDTO.setCompNo(maDocImgCommonDTO.getCompNo());

        
        Map<String,String> rtnMsg = maDocImgDetailService.uploadAutoFiles(maDocImgDetailDTO, request, response);
        
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
     * Set Object Type
     * @author  jung7126
     * @version $Id: MaDocImgDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocImgDetailForm
     */
    private void setObjectType(HttpServletRequest request,MaDocImgDetailForm maDocImgDetailForm)
    {
        // Service 객체 생성
        MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService)getBean("maDocImgDetailService");

        // 넘겨진 메뉴Id 구함
        String objectType = maDocImgDetailForm.getMaDocImgCommonDTO().getObjectType();
        
        // 조회된 상세 부분
        String objectTypeDesc = maDocImgDetailService.findObjectTypeDesc(objectType, getUser(request));
        
        // 조회된 상세  셋팅한다.
        MaDocImgDetailDTO maDocImgDetailDTO =  maDocImgDetailForm.getMaDocImgDetailDTO();
        maDocImgDetailDTO.setObjectTypeDesc(objectTypeDesc);
        
        maDocImgDetailForm.setMaDocImgDetailDTO(maDocImgDetailDTO);
    }

    /**
     * 버튼 상세 조회
     * @author jung7126
     * @version $Id: MaDocImgDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocImgDetailForm
     */
    private void findDetail(HttpServletRequest request, MaDocImgDetailForm maDocImgDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService)getBean("maDocImgDetailService");
        
        // 조회된 상세 부분
        MaDocImgDetailDTO maDocImgDetailDTO = maDocImgDetailService.findDetail(maDocImgDetailForm.getMaDocImgCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maDocImgDetailForm.setMaDocImgDetailDTO(maDocImgDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaDocImgDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailForm
     * @param request
     */
    private void insertDetail(MaDocImgDetailForm maDocImgDetailForm, HttpServletRequest request) throws Exception
    {
        MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService) getBean("maDocImgDetailService");
        
        MaDocImgDetailDTO maDocImgDetailDTO = maDocImgDetailForm.getMaDocImgDetailDTO();
        MaDocImgCommonDTO maDocImgCommonDTO = maDocImgDetailForm.getMaDocImgCommonDTO();
        
        maDocImgDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocImgDetailDTO.setCompNo(getUser(request).getCompNo());
        if(!"".equals(maDocImgCommonDTO.getCompNo())) maDocImgDetailDTO.setCompNo(maDocImgCommonDTO.getCompNo());
        
        maDocImgDetailService.insertDetail(maDocImgDetailDTO, maDocImgCommonDTO, maDocImgDetailForm.getFileList());
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaDocImgDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailForm
     * @param request
     */
    private void updateDetail(MaDocImgDetailForm maDocImgDetailForm, HttpServletRequest request) throws Exception
    {
    	MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService) getBean("maDocImgDetailService");
        
        MaDocImgDetailDTO maDocImgDetailDTO = maDocImgDetailForm.getMaDocImgDetailDTO();
        MaDocImgCommonDTO maDocImgCommonDTO = maDocImgDetailForm.getMaDocImgCommonDTO();
        
        maDocImgDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocImgDetailDTO.setCompNo(getUser(request).getCompNo());
        if(!"".equals(maDocImgCommonDTO.getCompNo())) maDocImgDetailDTO.setCompNo(maDocImgCommonDTO.getCompNo());
        
        maDocImgDetailService.updateDetail(maDocImgDetailDTO, maDocImgDetailForm.getFileList(), maDocImgDetailForm.getDeleteRows());

        setAjaxStatus(request);
    }
}