package common.finder.valid.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.finder.valid.form.ValidationForm;
import common.finder.valid.service.ValidationService;
import common.struts.AuthAction;
import common.util.QueryBuffer;

/**
 * Validation Action
 * code ������ description �˻�
 * @author  javaworker
 * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 pochul2423 Exp $
 * @since   1.0
 * 
 * @struts:action path="/validation" name="validationForm"
 *                input="/common/ajax/ajaxXmlVal.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="ajaxXmlVal" path="/common/ajax/ajaxXmlVal.jsp"
 *                        redirect="false"
 */
public class ValidationAction extends AuthAction
{
    /** sequence ���� ���� ��ȸ */
    public static final int VAL_NEXTVAL  			= 1004;
    /** Dir Usr Desc Validation */
    public static final int VAL_USR_DIR_DESC 		= 1005;
    /** Dir Sys Desc Validation */
    public static final int VAL_SYS_DIR_DESC 		= 1006;
    /** Table Desc validation */
    public static final int VAL_TABLE_DESC 			= 1007;
    /** ���� ��ȣ ��������. */
    public static final int VAL_NO_NEXTVAL 			= 1008;
    /** Dir Sys Code Validation */
    public static final int VAL_SYS_DIR_CODE 		= 2001;
    /** Dir Usr Code Validation */
    public static final int VAL_USR_DIR_CODE 		= 2002;
    /** �ý����ڵ� ID TO DESC */
    public static final int VAL_SYS_DIR_ID 			= 2003;
    /** ������ڵ� ID TO DESC */
    public static final int VAL_USR_DIR_ID 			= 2004;
    /** ÷������������ȸ */
    public static final int VAL_FILE_ATTACH			= 3001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ValidationForm validationForm = (ValidationForm)form;
        
        switch (validationForm.getStrutsAction())
        {
            case ValidationAction.VAL_NEXTVAL:
                findNextVal(request, validationForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ValidationAction.VAL_NO_NEXTVAL:
                findNextNoVal(request, validationForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ValidationAction.VAL_USR_DIR_DESC :
            	findUsrDirDescCode(request, validationForm, response);
            	returnActionForward = mapping.findForward("jsonPage");
                break;
            case ValidationAction.VAL_SYS_DIR_DESC :
            	findSysDirDescCode(request, validationForm, response);
            	returnActionForward = mapping.findForward("jsonPage");
                break;
            case ValidationAction.VAL_SYS_DIR_CODE :
                findSysDirCodeDesc(request, validationForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ValidationAction.VAL_USR_DIR_CODE :
                findUsrDirCodeDesc(request, validationForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ValidationAction.VAL_SYS_DIR_ID :
                findSysDirIdDesc(request, validationForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ValidationAction.VAL_USR_DIR_ID :
                findUsrDirIdDesc(request, validationForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ValidationAction.VAL_TABLE_DESC :
                findTableDescCode(request, validationForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ValidationAction.VAL_FILE_ATTACH :
                findFileAttach(request, validationForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default :
                break;
        }
        
        return returnActionForward;
        //return mapping.findForward("ajaxXmlVal");
    }

    
    private void findFileAttach(HttpServletRequest request, ValidationForm validationForm)
    {
        ValidationService valService = (ValidationService)getBean("validationService");
        
        String code = validationForm.getCode();
        String expCode = validationForm.getExpCode();
        
        List resultList = valService.findFileAttach(code, expCode, getUser(request));
        
        setAjaxData(request, QueryBuffer.toStringArray(resultList));
        /*
        
        String status = (String)userInfoList.get(0);
        
        if ("1".equals(status))
        {
            setAjaxId(request, (String)userInfoList.get(1));
            setAjaxDesc(request, (String)userInfoList.get(2));
        }
        else  if ("2".equals(status))
        {R
            setAjaxId(request, (String [])userInfoList.get(1));
            setAjaxDesc(request, (String [])userInfoList.get(2));
            setAjaxEtc(request, (String [])userInfoList.get(3));
        }
        */
    }
    /**
     * description ������ id �� �˻��Ѵ�.
     * @author  javaworker
     * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     * @throws IOException 
     */
    private void findUsrDirDescCode(HttpServletRequest request, ValidationForm validationForm, HttpServletResponse response) throws IOException
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        //�˻���
        String desc = validationForm.getCode();
        String descType = validationForm.getCodeType();
        String descKind = validationForm.getCodeKind();
        
        Map list = validationService.findUsrDirDescCode(desc, descType, descKind,getUser(request).getCompNo());
        
        makeJson(list, request, response);
    }
    
    /**
     * description ������ id �� �˻��Ѵ�.
     * @author  javaworker
     * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     * @throws IOException 
     */
    private void findSysDirDescCode(HttpServletRequest request, ValidationForm validationForm, HttpServletResponse response) throws IOException
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        //�˻���
        String desc = validationForm.getCode();
        String descType = validationForm.getCodeType();
        String descKind = validationForm.getCodeKind();
        
        Map list = validationService.findSysDirDescCode(desc, descType, descKind,getUser(request));

        makeJson(list, request, response);
        //setAjaxDesc(request, code);
    }
    
    /**
     * Code ������  Desc �� �˻��Ѵ�.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     * @throws IOException 
     */
    private void findSysDirCodeDesc(HttpServletRequest request, ValidationForm validationForm, HttpServletResponse response) throws IOException
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        //�˻���
        String code = validationForm.getCode();
        String descType = validationForm.getCodeType();
        String descKind = validationForm.getCodeKind();
        
        Map list = validationService.findSysDirCodeDesc(code, descType, descKind,getUser(request));
        
        makeJson(list, request, response);
        //setAjaxDesc(request, code);
    }
    
    /**
     * Code ������  Desc �� �˻��Ѵ�.(TAUSRD)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     */
    private void findUsrDirCodeDesc(HttpServletRequest request, ValidationForm validationForm)
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        //�˻���
        String code = validationForm.getCode();
        String descType = validationForm.getCodeType();
        String descKind = validationForm.getCodeKind();
        
        String desc = validationService.findUsrDirCodeDesc(code, descType, descKind, getUser(request).getCompNo());
        
        setAjaxDesc(request, desc);
    }
    
    /**
     * id ������  Desc �� �˻��Ѵ�.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     * @throws IOException 
     */
    private void findSysDirIdDesc(HttpServletRequest request, ValidationForm validationForm, HttpServletResponse response) throws IOException
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        //�˻���
        String id = validationForm.getCode();
        
        Map list = validationService.findSysDirIdDesc(id,getUser(request));
        
        makeJson(list, request, response);
    }
    
    /**
     * id ������  Desc �� �˻��Ѵ�.(TAUSRD)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     */
    private void findUsrDirIdDesc(HttpServletRequest request, ValidationForm validationForm)
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        //�˻���
        String id = validationForm.getCode();
        
        String desc = validationService.findUsrDirIdDesc(id, getUser(request).getCompNo());
        
        setAjaxDesc(request, desc);
    }
    
    /**
     * description ������ id�� �˻��Ѵ�.
     * @author  javaworker
     * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     * @throws IOException 
     */
    private void findTableDescCode(HttpServletRequest request, ValidationForm validationForm, HttpServletResponse response) throws IOException
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        
        String desc     = validationForm.getCode();
        String descType = validationForm.getCodeType();
        String expCode  = validationForm.getExpCode();
        String expCode2  = validationForm.getExpCode2();
        String expCode3  = validationForm.getExpCode3();
//        String codeKind = validationForm.getCodeKind();
        String compNo   = getUser(request).getCompNo();
        String lang     =getUser(request).getLangId();
        
        Map list = validationService.findTableDescCode(desc, descType, expCode, compNo, lang,expCode2,expCode3);

        makeJson(list, request, response);
        //setAjaxDesc(request, code);
    }
    /**
     * sequence nextval
     * @author  wondo
     * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     */
    private void findNextVal(HttpServletRequest request, ValidationForm validationForm)
    {
        ValidationService validationService = (ValidationService)getBean("validationService");
        
        String sequenceName = validationForm.getCode();
        
        String description = validationService.findNextVal(sequenceName);
        
        setAjaxId(request, sequenceName);
        setAjaxDesc(request, description);
    }
    /**
     * ���� ��ȣ��������
     * @author  kim21017
     * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     */
    private void findNextNoVal(HttpServletRequest request, ValidationForm validationForm)
    {
        ValidationService validationService = (ValidationService)getBean("validationService");

        String tableName = validationForm.getCode();
        String columnName = validationForm.getCodeType();
        
        String description = validationService.findNextNoVal(tableName,columnName,getUser(request).getCompNo());
        
        setAjaxId(request, tableName);
        setAjaxDesc(request, description);
    }
}
