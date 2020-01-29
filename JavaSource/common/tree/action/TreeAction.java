package common.tree.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.struts.AuthAction;
import common.tree.form.TreeForm;
import common.tree.service.TreeService;

/**
 * Tree ���� ���� �ҽ��̴�.
 * 
 * @struts:action path="/tree" name="treeForm" scope="request" validate="false"
 * @struts.action-forward name="success" path="/common/tree/bottomTree.jsp"
 *                        redirect="false"
 * @author javaworker
 * @version CVS $Id: TreeAction.java,v 1.4 2014/02/11 00:22:37 pochul2423 Exp $
 * @since 2005. 5. 10.
 */
public class TreeAction
        extends AuthAction
{    
    // Tree ����
    public static final String LOCATTREE = "LOCATION";  // �����ġ(����з�Tree)
    public static final String LOC_CODE_TREE = "LOC_CODE";  // ��ġ(����з�Tree)
    public static final String CATEGTREE = "EQ_CATEGORY";  // ����(����з�Tree)
    public static final String EQTYPTREE = "EQ_TYPE";   // ����(����з�Tree)
    public static final String RTTAGTREE = "REDTAG_GROUP";   // ���ܱ�׷�
    public static final String REDTAGLOC = "REDTAG_LOC";   // ���ܱ� ��ġ
    public static final String HSEDOC  = "HSE_DOC";   // HSE Doc
    
    public static final String DEPT_TREE = "DEPART";    // �μ�
    public static final String DIR_SYMPTOM = "SYMPTOM"; // ����
    
    public static final String CLASSTREE = "CLASS"; //���� Class
    public static final String UOMTREE  = "UOM"; // ����
    
    public static final String PROJECT = "PROJECT";   // ����Tree
    
    public static final String CALTREE  = "CALENDAR";   // ��,��
    public static final String IS_USE  = "IS_USE";   // ��뿩��(Y,N)
    public static final String P_MENU  = "P_MENU";   // �����޴�
    public static final String PAGE  = "PAGE";   // ȭ��
    public static final String MENU  = "MENU";   // �޴�
    public static final String KEY_TYPE  = "KEY_TYPE";   // �������(�ٱ���)
    public static final String EQ_STATUS  = "EQ_STATUS";   // �������
    public static final String BUTTON  = "BUTTON";   // ��ư
    
    /** Import Data ��ġ */
    public static final String IMP_LOCATTREE = "IMP_LOCATION";  // �����ġ(����з�Tree)

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {        
        TreeForm    treeForm = null;
        TreeService treeService = null;
        
        // ��ȸ�� parent node�� ���� �ٷ� ���� folder �� item�� ��ȸ�Ѵ�.
        String[][] folders = null;
        String[][] items = null;
        
        // tag library ���� submit �ÿ� ���۵Ǵ� parameter ���� �ڵ����� ���õȴ�.
        treeForm    = (TreeForm) form;
        // sampleService�� �����Ͽ� �����Ѵ�.
        treeService = (TreeService) getBean("treeService");
        
        // =============================================
        // �˻��� parent node�� �����̴�.
        String parentNode = treeForm.getParentNode();
        String treeType   = treeForm.getTreeType();
        // =============================================
    
        // ���񽺸� �����ϰ�[business logic]����� ArrayList ���Ϲ޴´�.
        folders = treeService.findTreeFolder(parentNode, treeType);
        items   = treeService.findTreeItem(parentNode, treeType);

        /*
         * For Test : set Sample DATA DB���� ��ȸ�� ���� 2���� �迭�� folder �� item ���۵ȴ�.
         * String [][0] : TREE �� �������� �̸� String [][1] : TREE �� �Ӽ� �� (code,
         * action�� ��)
         */
        treeForm.setFolders(folders);
        treeForm.setItems(items);

        return mapping.findForward("success");
    }
}
