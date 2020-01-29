package intf.dream.android.offline.madoclib.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfAuthAction;
import intf.dream.android.offline.madoclib.dto.AnIfDocLibCommonDTO;
import intf.dream.android.offline.madoclib.form.AnIfDocLibListForm;
import intf.dream.android.offline.madoclib.service.AnIfDocLibListService;

/**
 * 첨부문서 - 목록 action
 * @author  kim21017
 * @version $Id: AnIfDocLibListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/anIfDocLibList" name="anIfDocLibListForm"
 *                input="/dream/android/offline/madoclib/anIfDocLibList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfDocLibListAction extends IfAuthAction
{
    /** 저장 */
    public static final String DOCLIB_LIST_UPLOAD       = "FILE_UPLOAD";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfDocLibListForm anIfDocLibListForm = (AnIfDocLibListForm) form;
        
        switch (anIfDocLibListForm.getServiceName())
        {
            case AnIfDocLibListAction.DOCLIB_LIST_UPLOAD:
                fileUpload(anIfDocLibListForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void fileUpload(AnIfDocLibListForm anIfDocLibListForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        AnIfDocLibListService anIfDocLibListService = (AnIfDocLibListService) getBean("anIfDocLibListService");

        Map map = getMapData(request);
        
        AnIfDocLibCommonDTO anIfDocLibCommonDTO = anIfDocLibListForm.getAnIfDocLibCommonDTO();
        FormFile[] fileList = anIfDocLibListForm.getFileList();
        
        anIfDocLibListService.uploadFiles(map,fileList);

        setMessage(response, "", "OK");
    }
}
