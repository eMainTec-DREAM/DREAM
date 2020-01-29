package common.file.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.file.dto.FileUploadDTO;
import common.file.form.FileUploadForm;
import common.file.service.FileUploadService;
import common.struts.AuthAction;

/**
 * ����÷��
 * @author  javaworker
 * @version $Id: FileUploadAction.java,v 1.3 2014/01/07 08:32:34 hiimkkm Exp $
 * @since   1.0
 * @struts:action path="/fileUpload" name="fileUploadForm"
 *                input="/common/file/fileUpload.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="fileUpload" path="/common/file/fileUpload.jsp"
 *                        redirect="false"
 * @struts:action path="/fileUpload2" name="fileUploadForm"
 *                input="/common/file/fileUpload2.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="fileUpload2" path="/common/file/fileUpload2.jsp"
 *                        redirect="false"
 * @struts:action path="/fileUpload3" name="fileUploadForm"
 *                input="/common/file/fileUpload3.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="fileUpload3" path="/common/file/fileUpload3.jsp"
 *                        redirect="false"
 */
public class FileUploadAction extends AuthAction
{
    /** ����÷�� ��ȸ�Ҷ� */
    public static final int FILE_UPLOAD_FIND      = 1002;
    /** ÷������ ���� */
    public static final int FILE_UPLOAD_SAVE      = 1003;
    /** ÷������ ������ �ٽ� ��ȸ */
    public static final int FILE_UPLOAD_SAVE_FIND = 1004;
    /** �μ�ȣ, ���� �̹������� ���� */
    public static final int USER_FILE_UPLOAD_SAVE = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {    
        FileUploadForm fileUploadForm = (FileUploadForm)form;
        ActionForward returnActionForward = null;
        
        switch(fileUploadForm.getStrutsAction())
        {
            case FileUploadAction.FILE_UPLOAD_FIND :
            case FileUploadAction.FILE_UPLOAD_SAVE_FIND :
                // ���� ���� �� DTO�� �����Ͽ� ��ȸ�ǰ��Ѵ�.
                findDocMngNew(fileUploadForm, request);    
                returnActionForward = mapping.getInputForward();
                break;
            case FileUploadAction.FILE_UPLOAD_SAVE :
                // ���� �󼼸� �����Ѵ�.
                saveDocNew(fileUploadForm, request);
                returnActionForward = mapping.getInputForward();
                break;
            case FileUploadAction.USER_FILE_UPLOAD_SAVE :
                // User File Upload
                saveNewFile(fileUploadForm, request);
                returnActionForward = mapping.getInputForward();
                break;
            case FileUploadAction.DEFAULT_ACTION :
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    /**
     * ÷�������� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: FileUploadAction.java,v 1.3 2014/01/07 08:32:34 hiimkkm Exp $
     * @since   1.0
     * 
     * @param fileUploadForm
     * @param request
     * @throws Exception
     */
    private void findDocMngNew(FileUploadForm fileUploadForm,
            HttpServletRequest request)
    {
        FileUploadService fileUploadService = (FileUploadService) getBean("fileUploadService");
        
        fileUploadService.findDocMngNew(fileUploadForm.getFileUploadDTO());
    }
    
    /**
     * ÷�������� �����Ѵ�.
     * @author  javaworker
     * @version $Id: FileUploadAction.java,v 1.3 2014/01/07 08:32:34 hiimkkm Exp $
     * @since   1.0
     * 
     * @param form
     * @param request
     * @return
     * @throws Exception
     */
    private int saveDocNew(FileUploadForm form, HttpServletRequest request) throws Exception
    {
        FileUploadService fileUploadService = (FileUploadService) getBean("fileUploadService");
        
        FileUploadDTO fileUploadDTO = form.getFileUploadDTO();
        
        fileUploadDTO.setEnterBy(getUser(request).getUserId());
        
        // fileUploadDTO.fileNo �� ������ fileNo�� ����Ǿ� ���� 
        return fileUploadService.saveDocNew(fileUploadDTO, form.getFileList());
    }
    
    /**
     * �μ�ȣ, ���� �̹������� ����
     * @author  pochul2423
     * @version $Id: FileUploadAction.java,v 1.3 2014/01/07 08:32:34 hiimkkm Exp $
     * @since   1.0
     * 
     * @param form
     * @param request
     * @return
     * @throws Exception
     */
    private int saveNewFile(FileUploadForm form, HttpServletRequest request) throws Exception
    {
        FileUploadService fileUploadService = (FileUploadService) getBean("fileUploadService");
        
        FileUploadDTO fileUploadDTO = form.getFileUploadDTO();
        
        fileUploadDTO.setEnterBy(getUser(request).getUserId());
        
        // fileUploadDTO.fileNo �� ������ fileNo�� ����Ǿ� ���� 
        return fileUploadService.fileUploadForUser(form.getFileList(), fileUploadDTO);
    }
}