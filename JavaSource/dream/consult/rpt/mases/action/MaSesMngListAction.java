package dream.consult.rpt.mases.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.struts.BaseAction;
import common.util.DateUtil;
import dream.consult.rpt.mases.form.MaSesMngListForm;
import dream.consult.rpt.mases.service.MaSesMngListService;

/**
 * �ǽð� ������ ���
 * @author  kim21017
 * @version $Id: MaSesMngListAction.java,v 1.1 2013/08/30 09:12:22 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/maSesMngList" name="maSesMngListForm"
 *                input="/dream/consult/rpt/mases/maSesMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maSesMngList" path="/dream/consult/rpt/mases/maSesMngList.jsp"
 *                        redirect="false"
 */
public class MaSesMngListAction
        extends AuthAction
{
    /** LOG IN USER FIND */
    public static final int SES_LOGIN_FIND	= 1001;
    /** USER LOG OUT */
    public static final int SES_LOGOUT		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        MaSesMngListForm maSesMngListForm = (MaSesMngListForm)form;
        ActionForward returnActionForward = null;
        
        switch (maSesMngListForm.getStrutsAction())
        {
        	case MaSesMngListAction.SES_LOGIN_FIND :
	            findLogInUserList(maSesMngListForm, request,response);    
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            case MaSesMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maSesMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaSesMngListAction.SES_LOGOUT :
                doUserLogOut(request, maSesMngListForm);
//                doSessionInvalid(request, maSesMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaSesMngListAction.BASE_GRID_EXPORT:
            	findLogInUserList(maSesMngListForm, request,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default :
                returnActionForward = mapping.findForward("maSesMngList");
                break;
        }
        
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaSesMngListForm maSesMngListForm) throws IOException
    {
        super.setHeader(request, response, maSesMngListForm.getListId(),maSesMngListForm.getCurrentPageId()); 
    }
    
    /**
     * Log In User List �� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: MaSesMngListAction.java,v 1.1 2013/08/30 09:12:22 javaworker Exp $
     * @since   1.0
     * 
     * @param maSesMngListForm
     * @param request
     */
    private void findLogInUserList(MaSesMngListForm maSesMngListForm, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        List resultList = new ArrayList();
        
        // BaseAction.loginUsers �� List�� �����Ѵ�.
        Hashtable loginUsersTable = BaseAction.getLoginUsers();
        
        // Key ����
        Enumeration loginUsersEm = loginUsersTable.keys();
        User user = null;
        int i = 1;
        while (loginUsersEm.hasMoreElements())
        {
            Map rowMap = new LinkedHashMap();
            
            // User session Id 
            String loginUserKey = (String)loginUsersEm.nextElement();
            
            user = (User)loginUsersTable.get(loginUserKey);
            
            if(user.getUserNo().indexOf(maSesMngListForm.getMaSesMngCommonDTO().getFilterUserNo())!=-1){
            	
            	// SeqNo �� �����Ѵ�.
                rowMap.put("SEQNO", i++);
                // delete checkBox
                rowMap.put("ISDELCHECK", "");
                // Session Id �� �����Ѵ�.
                rowMap.put("SESSIONID", loginUserKey);
                
                // User Id ����
                rowMap.put("USERNO", user.getUserNo());
                
                // User Name ����
                rowMap.put("USERNAME", user.getUserName());
                
                // DEPT Name ����
                rowMap.put("DEPTDESC", user.getDeptDesc());
                
                // Log in �ð��� ����Ͻú��ʷ� �����Ѵ�. 
                long time = user.getLoginTimeMillis();
                rowMap.put("LOGINTIME", DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", new Date(time)));
                //======================================================================
                if(getUser(request).getCompNo().equals(user.getCompNo())){
                	resultList.add(rowMap);
                }
            }
        }
        
        super.makeJsonResult(resultList, request, response, maSesMngListForm.getListId());
    	
    }

    /**
     * User �� Log Out ��Ų��-Session �� Invalid ��Ų��.
     * @author  javaworker
     * @version $Id: MaSesMngListAction.java,v 1.1 2013/08/30 09:12:22 javaworker Exp $
     * @since   1.0
     * 
     * @param maSesMngListForm
     * @param request
     */
    private void doUserLogOut(HttpServletRequest request, MaSesMngListForm maSesMngListForm) throws Exception
    {
        MaSesMngListService maSesMngListService = (MaSesMngListService) getBean("maSesMngListService");        

        String[] deleteRows = maSesMngListForm.getDeleteRows();
        
    	maSesMngListService.deleteSes(deleteRows);
        
        setAjaxStatus(request);
    }
}