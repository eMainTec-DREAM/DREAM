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
 * Popup Tree구현 시 사용
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
        // forwarding path 기본적으로 success 세팅
        String forwardPath = "success";
        
        // tag library 에서 submit 시에 전송되는 parameter 값이 자동으로 셋팅된다.
        popupTreeForm = (PopupTreeForm) form;
        
        // sampleService를 생성하여 리턴한다.
        treeService = (TreeService) getBean("treeService");
        
        // =========================================================
        // 검색할 parent node의 정보이다.
        String searchNode = popupTreeForm.getSearchNode();
        String treeType   = popupTreeForm.getTreeType();
        
        // =========================================================
        // 해당 Node 까지의 TREE
        List treeNode = null;
        String rootName = "";
        String rootNodeKey = "";
        
        if(TreeAction.DEPT_TREE.equals(treeType))
        {
            // =========================================================
            // 해당 Node를 포함한 모든 부서
            treeNode = treeService.findTreeAllNodes(searchNode, treeType, "!");
            request.setAttribute("EXPAND", "ALL");
        }
        else if(TreeAction.PROJECT.equals(treeType))
        {
            // =========================================================
            // 해당 Node를 포함한 모든 부서
            
            String [] rootInfo = treeService.findRootInfo(treeType, searchNode);
            
            rootNodeKey = rootInfo[0];
            rootName = rootInfo[1];
            
            treeNode = treeService.findTreeAllNodes(searchNode, treeType, rootNodeKey);
            request.setAttribute("EXPAND", "ALL");
        }
        else
        {
            // =========================================================
            // 해당 Node 까지의 TREE
            treeNode = treeService.findTreeNode(searchNode, treeType);
        }
        
        // ==========================================================
        // form에 값 셋팅
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
