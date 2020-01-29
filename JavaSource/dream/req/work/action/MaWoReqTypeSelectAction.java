package dream.req.work.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.work.dto.MaWoReqTypeSelectDTO;
import dream.req.work.form.MaWoReqTypeSelectForm;
import dream.req.work.service.MaWoReqTypeSelectService;

/**
 * 작업요청유형 선택팝업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/maWoReqTypeSelect" name="maWoReqTypeSelectForm"
 *                input="/dream/req/work/maWoReqTypeSelect.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoReqTypeSelect" path="/dream/req/work/maWoReqTypeSelect.jsp"
 *                        redirect="false"
 */
public class MaWoReqTypeSelectAction extends AuthAction
{
    public static final int WOREQTYPE_SELECT_DEFAULT		= 1001;
    /** 조회 */
    public static final int WOREQTYPE_SELECT_FIND			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	MaWoReqTypeSelectForm maWoReqTypeSelectForm = (MaWoReqTypeSelectForm)form;
        ActionForward returnActionForward = null;
        
        switch (maWoReqTypeSelectForm.getStrutsAction())
        {
        	case MaWoReqTypeSelectAction.WOREQTYPE_SELECT_DEFAULT :
        		returnActionForward = mapping.findForward("maWoReqTypeSelect");
        		break;
            case MaWoReqTypeSelectAction.WOREQTYPE_SELECT_FIND :
                findList(request, maWoReqTypeSelectForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    /**
     * 작업요청유형 리스트 
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoReqTypeSelectForm
     */
    private void findList(HttpServletRequest request,
    		MaWoReqTypeSelectForm maWoReqTypeSelectForm, HttpServletResponse response) throws IOException
    {
        MaWoReqTypeSelectService maWoReqTypeSelectService = (MaWoReqTypeSelectService)getBean("maWoReqTypeSelectService");
        MaWoReqTypeSelectDTO maWoReqTypeSelectDTO = maWoReqTypeSelectForm.getMaWoReqTypeSelectDTO();
        List resultList = maWoReqTypeSelectService.findList(getUser(request),maWoReqTypeSelectDTO);
        
        super.makeJsonResult(resultList, request, response, maWoReqTypeSelectForm.getListId());
    }
}