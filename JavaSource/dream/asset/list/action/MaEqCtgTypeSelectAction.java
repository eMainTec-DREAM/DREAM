package dream.asset.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqCtgTypeSelectDTO;
import dream.asset.list.form.MaEqCtgTypeSelectForm;
import dream.asset.list.service.MaEqCtgTypeSelectService;

/**
 * 설비유형 선택팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/maEqCtgTypeSelect" name="maEqCtgTypeSelectForm"
 *                input="/dream/asset/list/maEqCtgTypeSelect.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgTypeSelect" path="/dream/asset/list/maEqCtgTypeSelect.jsp"
 *                        redirect="false"
 */
public class MaEqCtgTypeSelectAction extends AuthAction
{
    public static final int EQCTGTYPE_SELECT_DEFAULT		= 1001;
    public static final int EQCTGTYPE_SELECT_FIND			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	MaEqCtgTypeSelectForm maEqCtgTypeSelectForm = (MaEqCtgTypeSelectForm)form;
        ActionForward returnActionForward = null;
        
        switch (maEqCtgTypeSelectForm.getStrutsAction())
        {
        	case MaEqCtgTypeSelectAction.EQCTGTYPE_SELECT_DEFAULT :
        		returnActionForward = mapping.findForward("maEqCtgTypeSelect");
        		break;
            case MaEqCtgTypeSelectAction.EQCTGTYPE_SELECT_FIND :
                findList(request, maEqCtgTypeSelectForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    /**
     * 설비유형 리스트
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgTypeSelectForm
     */
    private void findList(HttpServletRequest request,
    		MaEqCtgTypeSelectForm maEqCtgTypeSelectForm, HttpServletResponse response) throws IOException
    {
        MaEqCtgTypeSelectService maEqCtgTypeSelectService = (MaEqCtgTypeSelectService)getBean("maEqCtgTypeSelectService");
        MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO = maEqCtgTypeSelectForm.getMaEqCtgTypeSelectDTO();
        List resultList = maEqCtgTypeSelectService.findList(getUser(request),maEqCtgTypeSelectDTO);
        
        super.makeJsonResult(resultList, request, response, maEqCtgTypeSelectForm.getListId());
    }
}