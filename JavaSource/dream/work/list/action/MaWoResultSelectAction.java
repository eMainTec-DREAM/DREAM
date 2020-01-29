package dream.work.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultSelectDTO;
import dream.work.list.form.MaWoResultSelectForm;
import dream.work.list.service.MaWoResultSelectService;

/**
 * 작업 형태, 종류 선택팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/maWoResultSelect" name="maWoResultSelectForm"
 *                input="/dream/work/list/maWoResultSelect.jsp" scope="request"
 *                validate="false"
* @struts:action path="/maWoResultTypeSelect" name="maWoResultSelectForm"
 *                input="/dream/work/list/maWoResultTypeSelect.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultSelect" path="/dream/work/list/maWoResultSelect.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoResultTypeSelect" path="/dream/work/list/maWoResultTypeSelect.jsp"
 *                        redirect="false"
 */
public class MaWoResultSelectAction extends AuthAction
{
    public static final int WO_SELECT_DEFAULT				= 1001;
    public static final int WO_SELECT_WOTYPE_FIND			= 1002;
    public static final int WO_SELECT_TYPE_FIND				= 1003;
    public static final int WO_TYPESELECT_DEFAULT			= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	MaWoResultSelectForm maWoResultSelectForm = (MaWoResultSelectForm)form;
        ActionForward returnActionForward = null;
        
        switch (maWoResultSelectForm.getStrutsAction())
        {
        	case MaWoResultSelectAction.WO_SELECT_DEFAULT :
        		returnActionForward = mapping.findForward("maWoResultSelect");
        		break;
        	case MaWoResultSelectAction.WO_TYPESELECT_DEFAULT :
        		returnActionForward = mapping.findForward("maWoResultTypeSelect");
        		break;
            case MaWoResultSelectAction.WO_SELECT_WOTYPE_FIND :
                findWoTypeList(request, maWoResultSelectForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultSelectAction.WO_SELECT_TYPE_FIND :
                findTypeList(request, maWoResultSelectForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    /**
     * 작업종류 리스트
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultSelectForm
     */
    private void findWoTypeList(HttpServletRequest request,
    		MaWoResultSelectForm maWoResultSelectForm, HttpServletResponse response) throws IOException
    {
        MaWoResultSelectService maWoResultSelectService = (MaWoResultSelectService)getBean("maWoResultSelectService");
        MaWoResultSelectDTO maWoResultSelectDTO = maWoResultSelectForm.getMaWoResultSelectDTO();
        List resultList = maWoResultSelectService.findWoTypeList(getUser(request),maWoResultSelectDTO);
        
        super.makeJsonResult(resultList, request, response, maWoResultSelectForm.getListId());
    }
    /**
     * 작업형태 리스트
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultSelectForm
     */
    private void findTypeList(HttpServletRequest request,
    		MaWoResultSelectForm maWoResultSelectForm, HttpServletResponse response) throws IOException
    {
        MaWoResultSelectService maWoResultSelectService = (MaWoResultSelectService)getBean("maWoResultSelectService");
        MaWoResultSelectDTO maWoResultSelectDTO = maWoResultSelectForm.getMaWoResultSelectDTO();
        
        List resultList = maWoResultSelectService.findTypeList(getUser(request),maWoResultSelectDTO);
        
        super.makeJsonResult(resultList, request, response, maWoResultSelectForm.getListId());
    }
}