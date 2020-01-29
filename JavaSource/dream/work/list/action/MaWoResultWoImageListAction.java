package dream.work.list.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.microsoft.azure.storage.StorageException;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultWoImageListDTO;
import dream.work.list.form.MaWoResultWoImageListForm;
import dream.work.list.service.MaWoResultWoImageListService;

/**
 * 작업결과-작업사진 목록 ACTION
 * @author  kim21017
 * @version $Id: MaWoResultWoImageListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultWoImageList" name="maWoResultWoImageListForm"
 *                input="/dream/work/list/maWoResultWoImageList.jsp" scope="request"
 *                validate="false"
 */
public class MaWoResultWoImageListAction extends AuthAction
{
    public static final int IMAGE_FIND 			= 1001;
    public static final int DATA_FIND 			= 1002;
    public static final int IMAGE_COUNT			= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultWoImageListForm maWoResultWoImageListForm = (MaWoResultWoImageListForm) form;
        
        switch (maWoResultWoImageListForm.getStrutsAction())
        {
        
            case MaWoResultWoImageListAction.IMAGE_FIND:
                this.findSlideImage(maWoResultWoImageListForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultWoImageListAction.DATA_FIND:
            	this.findImage(request, maWoResultWoImageListForm);
                returnActionForward = mapping.getInputForward();
            	break;
            case MaWoResultWoImageListAction.IMAGE_COUNT:
            	this.getImageCount(request, maWoResultWoImageListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    /**
     * 
     * @param request
     * @param maWoResultWoImageListForm
     * @throws Exception
     */
    private void findImage(HttpServletRequest request, MaWoResultWoImageListForm maWoResultWoImageListForm)throws Exception 
    {  
    	MaWoResultWoImageListService maWoResultWoImageListService = (MaWoResultWoImageListService)getBean("maWoResultWoImageListService");
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultWoImageListForm.getMaWoResultMstrCommonDTO();
    	MaWoResultWoImageListDTO maWoResultWoImageListDTO = maWoResultWoImageListService.findImage(maWoResultMstrCommonDTO, getUser(request));
    	
    	//전 후 이미지 리스트를 request 객체에 담기
    	request.setAttribute("slideBeforeFileList", maWoResultWoImageListDTO.getSlideBeforeFileList());
    	request.setAttribute("slideAfterFileList", maWoResultWoImageListDTO.getSlideAfterFileList());
    	
    	maWoResultWoImageListForm.setMaWoResultWoImageListDTO(maWoResultWoImageListDTO);
    }
    
    /**
     * 
     * @param maWoResultWoImageListForm
     * @param request
     * @param response
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(MaWoResultWoImageListForm maWoResultWoImageListForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service 객체 생성
        MaWoResultWoImageListService maWoResultWoImageListService = (MaWoResultWoImageListService)getBean("maWoResultWoImageListService");

        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultWoImageListForm.getMaWoResultMstrCommonDTO();
        MaWoResultWoImageListDTO maWoResultWoImageListDTO = maWoResultWoImageListForm.getMaWoResultWoImageListDTO();
        
        // 이미지를 불러와서 json으로 넘겨준다.
        List list = maWoResultWoImageListService.findSlideImage(maWoResultMstrCommonDTO, maWoResultWoImageListDTO, getUser(request));

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);
        response.getWriter().print(jsonString);
    }
    
    public void getImageCount(HttpServletRequest request, MaWoResultWoImageListForm maWoResultWoImageListForm) throws Exception
    {
    	// Service 객체 생성
        MaWoResultWoImageListService maWoResultWoImageListService = (MaWoResultWoImageListService)getBean("maWoResultWoImageListService");

        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultWoImageListForm.getMaWoResultMstrCommonDTO();
        
        String[][] resultList = maWoResultWoImageListService.getImageCount(maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxData(request, resultList);
    }
}