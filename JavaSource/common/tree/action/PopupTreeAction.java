package common.tree.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.struts.AuthAction;
import common.tree.form.PopupTreeForm;
import common.tree.service.TreeService;

/**
 * Popup Tree���� �� ���
 * 
 * @struts:action path="/popupTree" name="popupTreeForm" input="/popupTree.jsp"
 *                scope="request" validate="false"
 * @struts.action-forward name="success" path="/common/tree/popupTree.jsp"
 *                        redirect="false"
 * @author javaworker
 * @version $Id: PopupTreeAction.java,v 1.2 2013/10/24 08:41:45 jung7126 Exp $
 * @since 1.0
 */
public class PopupTreeAction
        extends AuthAction
{
    private PopupTreeForm       popupTreeForm = null;
    private TreeService         treeService   = null;
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(TreeAction.class);

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("run() - start");
        }
        
        // =========================================================
        // forwarding path �⺻������ success ����
        String forwardPath = "success";
        
        // tag library ���� submit �ÿ� ���۵Ǵ� parameter ���� �ڵ����� ���õȴ�.
        popupTreeForm = (PopupTreeForm) form;
        
        // sampleService�� �����Ͽ� �����Ѵ�.
        treeService = (TreeService) getBean("treeService");
        
        // =========================================================
        // �˻��� parent node�� �����̴�.
        String searchNode = popupTreeForm.getSearchNode();
        String treeType   = popupTreeForm.getTreeType();
        
        // =========================================================
        // �ش� Node ������ TREE
        List treeNode = null;
        String rootName = "";
        String rootNodeKey = "";
        
        if(TreeAction.DEPT_TREE.equals(treeType))
        {
            // =========================================================
            // �ش� Node�� ������ ��� �μ�
            treeNode = treeService.findTreeAllNodes(searchNode, treeType, "!");
            request.setAttribute("EXPAND", "ALL");
        }
        else if(TreeAction.PROJECT.equals(treeType))
        {
            // =========================================================
            // �ش� Node�� ������ ��� �μ�
            
            String [] rootInfo = treeService.findRootInfo(treeType, searchNode);
            
            rootNodeKey = rootInfo[0];
            rootName = rootInfo[1];
            
            treeNode = treeService.findTreeAllNodes(searchNode, treeType, rootNodeKey);
            request.setAttribute("EXPAND", "ALL");
        }
        else
        {
            // =========================================================
            // �ش� Node ������ TREE
            treeNode = treeService.findTreeNode(searchNode, treeType);
        }
        
        // ==========================================================
        // form�� �� ����
        popupTreeForm.setTreeNodes(treeNode);
        popupTreeForm.setSearchNode(searchNode);
        
        if (rootNodeKey == null || "".equals(rootNodeKey)) rootNodeKey = "!";   // default Root key
        
        popupTreeForm.setRootNodeKey(rootNodeKey);
        
        if (rootName == null || "".equals(rootName))
        {
            rootName = getMessage(request, "TREE." + treeType);
        }
        popupTreeForm.setRootName(rootName);
        // ===========================================================
        
        if (logger.isDebugEnabled())
        {
            logger.debug("run() - end");
        }
        
        return mapping.findForward(forwardPath);
    }
}
