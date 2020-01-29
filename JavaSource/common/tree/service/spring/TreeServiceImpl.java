package common.tree.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import common.tree.action.TreeAction;
import common.tree.dao.TreeDAO;
import common.tree.service.TreeService;
import common.util.QueryBuffer;

/**
 * @author javaworker
 * @version $Id: TreeServiceImpl.java,v 1.5 2014/02/20 08:00:04 javaworker Exp $
 * @since 1.0
 * @spring.bean id="treeServiceTarget"
 * @spring.txbn id="treeService"
 * @spring.property name="treeDAO" ref="treeDAO"
 */
public class TreeServiceImpl
        implements TreeService
{
    private TreeDAO       treeDAO;
    private static final Logger logger = Logger.getLogger(TreeServiceImpl.class);

    public TreeServiceImpl()
    {}

    /**
     * sParent_no �� �˻��� �Ѵ�. �ش� parent�� folder�� �˻��Ѵ�.
     * 
     * @author javaworker
     * @version $Id: SampleTreeServiceImpl.java,v 1.2 2005/05/16 05:58:49 dawn
     *          Exp $
     * @since 1.0
     * @param sParent_no
     * @return String [] : �ش� parent�� child folder
     */
    public String[][] findTreeFolder(String sParent_no, String treeType)
    {
        logger.debug("start equipTreeFService()");
        String[][] sResults = null;
        
        // �ش� parent_no�� �÷��� child folders �� ��ȸ�Ѵ�.
        List folderList = null;
            
        if(TreeAction.DEPT_TREE.equals(treeType))
        {
            folderList = treeDAO.findDeptTreeList(sParent_no, true);
        }
        else if (TreeAction.LOCATTREE.equals(treeType))
        {
            folderList = treeDAO.findT2TreeList(sParent_no, true);
        }
        else if (TreeAction.IMP_LOCATTREE.equals(treeType))
        {
            folderList = treeDAO.findImpEqTreeList(sParent_no, true);
        }
        else if(TreeAction.CLASSTREE.equals(treeType)) 
        {
            folderList = treeDAO.findClassTreeList(sParent_no, true);
        }
        else if(TreeAction.UOMTREE.equals(treeType)) 
        {
            folderList = treeDAO.findUomTreeList(sParent_no, true);
        }
        else if(TreeAction.EQTYPTREE.equals(treeType) || TreeAction.CATEGTREE.equals(treeType)) 
        {
            folderList = treeDAO.findEqClassTreeList(sParent_no, true, treeType);
        }
        else if(TreeAction.REDTAGLOC.equals(treeType) || TreeAction.RTTAGTREE.equals(treeType)) 
        {
            folderList = treeDAO.findRedClassTreeList(sParent_no, true, treeType);
        }
        else if(TreeAction.CALTREE.equals(treeType))
        {
            folderList = treeDAO.findCalTreeList(sParent_no, true);
        }
        else if(TreeAction.HSEDOC.equals(treeType)) 
        {
            folderList = treeDAO.findHseDocTreeList(sParent_no, true);
        }
        else if(TreeAction.P_MENU.equals(treeType)) 
        {
            folderList = treeDAO.findPMenuTreeList(sParent_no, true);
        }
        else if(TreeAction.PAGE.equals(treeType)) 
        {
            folderList = treeDAO.findPageTreeList(sParent_no, true);
        }
        else if(TreeAction.MENU.equals(treeType)) 
        {
            folderList = treeDAO.findMenuTreeList(sParent_no, true);
        }
        else if(TreeAction.BUTTON.equals(treeType)) 
        {
            folderList = treeDAO.findButtonTreeList(sParent_no, true);
        }
        else
        {            
            folderList = treeDAO.findT4TreeList(sParent_no, true, treeType);
        }

        sResults = QueryBuffer.toStringArray(folderList);
            
        logger.debug("end equipTreeFService()");
        return sResults;
    }

    /**
     * sParent_no �� �˻��� �Ѵ�. �ش� parent�� item�� �˻��Ѵ�.
     * 
     * @author javaworker
     * @version $Id: SampleTreeServiceImpl.java,v 1.2 2005/05/16 05:58:49 dawn
     *          Exp $
     * @since 1.0
     * @param sParent_no
     * @return String [] : �ش� parent�� child item
     */
    public String[][] findTreeItem(String sParent_no, String treeType)
    {
        logger.debug("start equipTreeIService()");
        
        // �ش� parent_no�� �÷��� child items �� ��ȸ�Ѵ�.
        List itemList = null;
        
        if(TreeAction.DEPT_TREE.equals(treeType))
        {
            itemList = treeDAO.findDeptTreeList(sParent_no, false);
        }
        // ���� �ΰ��� table �� �ٸ���. 
        else if (TreeAction.LOCATTREE.equals(treeType))
        {
            itemList = treeDAO.findT2TreeList(sParent_no, false);
        }
        else if (TreeAction.IMP_LOCATTREE.equals(treeType))
        {
            itemList = treeDAO.findImpEqTreeList(sParent_no, false);
        }
        else if(TreeAction.CLASSTREE.equals(treeType)) 
        {
            itemList = treeDAO.findClassTreeList(sParent_no, false);
        }
        else if(TreeAction.UOMTREE.equals(treeType)) 
        {
            itemList = treeDAO.findUomTreeList(sParent_no, false);
        }
        else if(TreeAction.EQTYPTREE.equals(treeType) || TreeAction.CATEGTREE.equals(treeType)) 
        {
            itemList = treeDAO.findEqClassTreeList(sParent_no, false, treeType);
        }
        else if(TreeAction.REDTAGLOC.equals(treeType) || TreeAction.RTTAGTREE.equals(treeType)) 
        {
            itemList = treeDAO.findRedClassTreeList(sParent_no, false, treeType);
        }
        else if(TreeAction.CALTREE.equals(treeType)) 
        {
            itemList = treeDAO.findCalTreeList(sParent_no, false);
        }
        else if(TreeAction.HSEDOC.equals(treeType)) 
        {            
            itemList = treeDAO.findHseDocTreeList(sParent_no, false);
        }
        else if(TreeAction.P_MENU.equals(treeType)) 
        {            
            itemList = treeDAO.findPMenuTreeList(sParent_no, false);
        }
        else if(TreeAction.PAGE.equals(treeType)) 
        {
        	itemList = treeDAO.findPageTreeList(sParent_no, false);
        }
        else if(TreeAction.MENU.equals(treeType)) 
        {
        	itemList = treeDAO.findMenuTreeList(sParent_no, false);
        }
        else if(TreeAction.BUTTON.equals(treeType)) 
        {
        	itemList = treeDAO.findButtonTreeList(sParent_no, false);
        }
        else
        {
            itemList = treeDAO.findT4TreeList(sParent_no, false, treeType);
        }
        
        logger.debug("end equipTreeIService()");
        return QueryBuffer.toStringArray(itemList);
    }

    public TreeDAO getTreeDAO()
    {
        return treeDAO;
    }

    public void setTreeDAO(TreeDAO sampleTreeDAO)
    {
        this.treeDAO = sampleTreeDAO;
    }
    
    public List findTreeAllNodes(String searchNode, String treeType, String rootNodeKey)
    {
        if (TreeAction.DEPT_TREE.equals(treeType))
        {
            return treeDAO.findDeptAllNodes(searchNode, rootNodeKey);
        }
        else if (TreeAction.PROJECT.equals(treeType))
        {
            if (rootNodeKey == null || "".equals(rootNodeKey)) return null;
            return treeDAO.findProjectAllNodes(searchNode, rootNodeKey);
        }
        return null;
    }

    public List findTreeNode(String searchNode, String treeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getTreeNode() - start");
        }
        
        ArrayList treeNode = new ArrayList();
        HashMap currentNodes = null;

        // ���� Node�� parent node �迭
        String [] parentNodes = treeDAO.getParentNodes(searchNode, treeType);
        int depth = parentNodes.length;
        
        for (int i = 0; i < depth; i++)
        {
            currentNodes = new HashMap();
            
            /*
             * parentNodes[] �� ����� �迭�� ��ġ�� �ݴ�� ArrayList�� �����Ѵ�. ex.)
             * parentNodes[0] : current node's parent parentNodes[1] :
             * parent node paretnNodes[2] : root ArrayList[0] : root
             * ArrayList[1] : parent node ArrayList[2] : current node's
             * parent
             */
            currentNodes.put("parent", parentNodes[depth - 1 - i]); // parent
            
            // Node
            // �̸� ����
            currentNodes.put("folder", findTreeFolder(parentNodes[depth - 1 - i], treeType)); // folder
            // ����
            currentNodes.put("item", findTreeItem(parentNodes[depth - 1 - i], treeType)); // item
            
            // ����
            // ���� depth�� hashtable�� �����Ѵ�.
            treeNode.add(currentNodes);
        }

        if (logger.isDebugEnabled())
        {
            logger.debug("getTreeNode() - end");
        }
        return treeNode;
    }
   
    public String findSearchNode(String equipNo, String treeType)
    {
        return treeDAO.findSearchNode(equipNo, treeType);
    }
    
    @Override
    public String[] findRootInfo(String treeType, String searchNode)
    {
        if (TreeAction.PROJECT.equals(treeType))
        {
            return treeDAO.findPjtRoot(searchNode);
        }
        String [] result = {"!", "ROOT"};    // default Root
        return result;
    }
}
