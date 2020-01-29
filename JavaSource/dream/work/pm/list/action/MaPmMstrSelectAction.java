package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.work.pm.list.dto.MaPmMstrSelectDTO;
import dream.work.pm.list.form.MaPmMstrSelectForm;
import dream.work.pm.list.service.MaPmMstrSelectService;

/**
 * 예방작업 작업형태 선택 팝업 
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/maPmMstrSelect" name="maPmMstrSelectForm"
 *                input="/dream/work/pm/list/maPmMstrSelect.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListSelectPm" name="maPmMstrSelectForm"
 *                input="/dream/work/pm/list/workPmListSelectPm.jsp" scope="request"
 *                validate="false"
 */
public class MaPmMstrSelectAction extends SuperAuthAction
{
    public static final int PM_SELECT_DEFAULT			= 1001;
    public static final int PM_SELECT_WOTYPE_FIND		= 1002;
    public static final int PM_SELECT_PMTYPE_FIND		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	MaPmMstrSelectForm maPmMstrSelectForm = (MaPmMstrSelectForm)form;
        ActionForward returnActionForward = null;
        
        switch (maPmMstrSelectForm.getStrutsAction())
        {
        	case MaPmMstrSelectAction.PM_SELECT_DEFAULT :
        		returnActionForward = mapping.getInputForward();
        		break;
            case MaPmMstrSelectAction.PM_SELECT_WOTYPE_FIND :
                findWoTypeList(request, maPmMstrSelectForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrSelectAction.PM_SELECT_PMTYPE_FIND :
            	findPmTypeList(request, maPmMstrSelectForm,response);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    /**
     * 작업형태 리스트
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrSelectForm
     */
    private void findWoTypeList(HttpServletRequest request,
    		MaPmMstrSelectForm maPmMstrSelectForm, HttpServletResponse response) throws IOException
    {
        MaPmMstrSelectService maPmMstrSelectService = (MaPmMstrSelectService)getBean("maPmMstrSelectService");
        
        MaPmMstrSelectDTO maPmMstrSelectDTO = maPmMstrSelectForm.getMaPmMstrSelectDTO();
        
        List resultList = maPmMstrSelectService.findWoTypeList(getUser(request), maPmMstrSelectDTO);
        
        super.makeJsonResult(resultList, request, response, maPmMstrSelectForm.getListId());
    }
    
    
    private void findPmTypeList(HttpServletRequest request,
    		MaPmMstrSelectForm maPmMstrSelectForm, HttpServletResponse response) throws IOException
    		{
    	MaPmMstrSelectService maPmMstrSelectService = (MaPmMstrSelectService)getBean("maPmMstrSelectService");
    	
    	MaPmMstrSelectDTO maPmMstrSelectDTO = maPmMstrSelectForm.getMaPmMstrSelectDTO();
    	
    	List resultList = maPmMstrSelectService.findPmTypeList(getUser(request), maPmMstrSelectDTO);
    	
    	super.makeJsonResult(resultList, request, response, maPmMstrSelectForm.getListId());
    		}

}