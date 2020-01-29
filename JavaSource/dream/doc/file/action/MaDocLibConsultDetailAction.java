package dream.doc.file.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;
import dream.doc.file.form.MaDocLibDetailForm;
import dream.doc.file.service.MaDocLibDetailService;


/**
 * 첨부문서 - 상세 action
 * 
 * @author jung7126
 * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maWrkImpDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/consult/program/wrkimp/maWrkImpDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultProgramOnlinehelpDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/consult/program/onlinehelp/consultProgramOnlinehelpDocLibDetail.jsp" scope="request"
 *                validate="false"
 */
public class MaDocLibConsultDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DOCLIB_DETAIL_INIT 		    = 1001;
    /** 저장 */
    public static final int DOCLIB_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int DOCLIB_DETAIL_UPDATE 		= 1003;
    /** 파일저장 */
    public static final int DOCLIB_FILE_SAVE            = 1004;
    /** 파일삭제 */
    public static final int DOCLIB_FILE_DELETE          = 1005;
    /** 파일저장 */
    public static final int DOCLIB_FILE_SAVE_TEST            = 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocLibDetailForm maDocLibDetailForm = (MaDocLibDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maDocLibDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (maDocLibDetailForm.getStrutsAction())
        {
            case MaDocLibConsultDetailAction.DOCLIB_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maDocLibDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaDocLibConsultDetailAction.DOCLIB_DETAIL_INPUT:
            	insertDetail(maDocLibDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocLibConsultDetailAction.DOCLIB_DETAIL_UPDATE:
            	updateDetail(maDocLibDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocLibConsultDetailAction.DOCLIB_FILE_SAVE:
                fileUpload(maDocLibDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocLibConsultDetailAction.DOCLIB_FILE_DELETE:
                fileDelete(maDocLibDetailForm, request, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaDocLibConsultDetailAction.DOCLIB_FILE_SAVE_TEST:
                fileUploadTest(maDocLibDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void fileDelete(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request, HttpServletResponse response)
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.deleteAutoFiles(maDocLibDetailDTO,maDocLibDetailForm.getDeleteRows());
    }

    private void fileUpload(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.uploadAutoFiles(maDocLibDetailDTO, request, response);

    }
    
    private void fileUploadTest(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        FormFile[] fileList = maDocLibDetailForm.getFileList();
        
//        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
//        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.uploadFiles(maDocLibDetailDTO,fileList);

    }

    /**
     * 버튼 상세 조회
     * @author jung7126
     * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocLibDetailForm
     */
    private void findDetail(HttpServletRequest request, MaDocLibDetailForm maDocLibDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService)getBean("maDocLibDetailService");

        // 넘겨진 메뉴Id 구함
        String docId = maDocLibDetailForm.getMaDocLibCommonDTO().getDocId();
        
        // 조회된 상세 부분
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailService.findDetail(docId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maDocLibDetailForm.setMaDocLibDetailDTO(maDocLibDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailForm
     * @param request
     */
    private void insertDetail(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request) throws Exception
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        MaDocLibCommonDTO maDocLibCommonDTO = maDocLibDetailForm.getMaDocLibCommonDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.insertDetail(maDocLibDetailDTO, maDocLibCommonDTO, maDocLibDetailForm.getFileList());
        
//        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailForm
     * @param request
     */
    private void updateDetail(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request) throws Exception
    {
    	MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.updateDetail(maDocLibDetailDTO, maDocLibDetailForm.getFileList(), maDocLibDetailForm.getDeleteRows());

//        setAjaxStatus(request);
    }
    
}