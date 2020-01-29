package intf.dream.ant.doclib.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfAuthAction;
import intf.dream.ant.doclib.dto.AntDocLibCommonDTO;
import intf.dream.ant.doclib.form.AntDocLibListForm;
import intf.dream.ant.doclib.service.AntDocLibListService;

/**
 * 첨부문서 - 목록 action
 * @author  kim21017
 * @version $Id: AntDocLibListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/antDocLibList" name="antDocLibListForm"
 *                input="/ant/doclib/antDocLibList.jsp" scope="request"
 *                validate="false"
 */
public class AntDocLibListAction extends IfAuthAction
{
    /** 저장 */
    public static final String DOCLIB_LIST_UPLOAD       = "FILE_UPLOAD";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntDocLibListForm antDocLibListForm = (AntDocLibListForm) form;
        
        switch (antDocLibListForm.getServiceName())
        {
            case AntDocLibListAction.DOCLIB_LIST_UPLOAD:
                fileUpload(antDocLibListForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void fileUpload(AntDocLibListForm antDocLibListForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        AntDocLibListService antDocLibListService = (AntDocLibListService) getBean("antDocLibListService");

        Map map = getMapData(request);
        
        AntDocLibCommonDTO antDocLibCommonDTO = antDocLibListForm.getAntDocLibCommonDTO();
        FormFile[] fileList = antDocLibListForm.getFileList();
        
        antDocLibListService.uploadFiles(map,fileList);

        setMessage(response, "", "OK");
    }
}
