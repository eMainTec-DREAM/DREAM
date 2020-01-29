package dream.note.daily.action;

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
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyImageListDTO;
import dream.note.daily.form.MaWoDailyImageListForm;
import dream.note.daily.service.MaWoDailyImageListService;

/**
 * ���Ͼ������� ���� ��� ACTION
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maWoDailyImageList" name="maWoDailyImageListForm"
 *                input="/dream/note/daily/maWoDailyImageList.jsp" scope="request"
 *                validate="false"
 */
public class MaWoDailyImageListAction extends AuthAction
{
    public static final int IMAGE_FIND 			= 1001;
    public static final int DATA_FIND 			= 1002;
    public static final int IMAGE_COUNT			= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyImageListForm maWoDailyImageListForm = (MaWoDailyImageListForm) form;
        
        switch (maWoDailyImageListForm.getStrutsAction())
        {
        
            case MaWoDailyImageListAction.IMAGE_FIND:
                this.findSlideImage(maWoDailyImageListForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDailyImageListAction.DATA_FIND:
            	this.findImage(request, maWoDailyImageListForm);
                returnActionForward = mapping.getInputForward();
            	break;
            case MaWoDailyImageListAction.IMAGE_COUNT:
            	this.getImageCount(request, maWoDailyImageListForm);
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
     * @param maWoDailyImageListForm
     * @throws Exception
     */
    private void findImage(HttpServletRequest request, MaWoDailyImageListForm maWoDailyImageListForm)throws Exception 
    {  
    	MaWoDailyImageListService maWoDailyImageListService = (MaWoDailyImageListService)getBean("maWoDailyImageListService");
    	MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyImageListForm.getMaWoDailyCommonDTO();
    	MaWoDailyImageListDTO maWoDailyImageListDTO = maWoDailyImageListService.findImage(maWoDailyCommonDTO, getUser(request));
    	
    	//�� �� �̹��� ����Ʈ�� request ��ü�� ���
    	request.setAttribute("slideAfterFileList", maWoDailyImageListDTO.getSlideAfterFileList());
    	
    	maWoDailyImageListForm.setMaWoDailyImageListDTO(maWoDailyImageListDTO);
    }
    
    /**
     * 
     * @param maWoDailyImageListForm
     * @param request
     * @param response
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(MaWoDailyImageListForm maWoDailyImageListForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service ��ü ����
        MaWoDailyImageListService maWoDailyImageListService = (MaWoDailyImageListService)getBean("maWoDailyImageListService");

        MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyImageListForm.getMaWoDailyCommonDTO();
        MaWoDailyImageListDTO maWoDailyImageListDTO = maWoDailyImageListForm.getMaWoDailyImageListDTO();
        
        // �̹����� �ҷ��ͼ� json���� �Ѱ��ش�.
        List list = maWoDailyImageListService.findSlideImage(maWoDailyCommonDTO, maWoDailyImageListDTO, getUser(request));

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);
        response.getWriter().print(jsonString);
    }
    
    public void getImageCount(HttpServletRequest request, MaWoDailyImageListForm maWoDailyImageListForm) throws Exception
    {
    	// Service ��ü ����
        MaWoDailyImageListService maWoDailyImageListService = (MaWoDailyImageListService)getBean("maWoDailyImageListService");

        MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyImageListForm.getMaWoDailyCommonDTO();
        
        String[][] resultList = maWoDailyImageListService.getImageCount(maWoDailyCommonDTO, getUser(request));
        
        setAjaxData(request, resultList);
    }
}