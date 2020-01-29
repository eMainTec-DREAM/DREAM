package common.tree.form;

import java.util.List;
import common.struts.BaseForm;

/**
 * popup ���� Tree�� ���鼭 �ش� Node�� Root - Node �� �ش��ϴ� Tree �� �����ȴ�.
 * 
 * @author javaworker
 * @version $Id: PopupTreeForm.java,v 1.1 2013/08/30 09:12:06 javaworker Exp $
 * @since 1.0
 * @struts.form name="popupTreeForm"
 */
public class PopupTreeForm
        extends BaseForm
{
    /**
     * tree ���·� ��� �� �ִ� ����̴�. ArrayList �ȿ� HashMap���� �� �ִ�.
     */
    private List   TreeNodes;
    /** �˻� Node */
    private String searchNode;
    /** Root Name */
    private String rootName;
    /** Root Node Key */
    private String rootNodeKey;
    /** Tree ���� */
    private String treeType;
    /** Tree ���� ���� */
    private String actionTree = "";
    
    public String getActionTree()
    {
        return actionTree;
    }

    public void setActionTree(String actionTree)
    {
        this.actionTree = actionTree;
    }

    public List getTreeNodes()
    {
        return TreeNodes;
    }

    public void setTreeNodes(List treeNodes)
    {
        TreeNodes = treeNodes;
    }

    public String getSearchNode()
    {
        return searchNode;
    }

    public void setSearchNode(String searchNode)
    {
        this.searchNode = searchNode;
    }

    public String getRootName()
    {
        return rootName;
    }

    public void setRootName(String rootName)
    {
        this.rootName = rootName;
    }

    public String getTreeType()
    {
        return treeType;
    }

    public void setTreeType(String treeType)
    {
        this.treeType = treeType;
    }

    public String getRootNodeKey()
    {
        return rootNodeKey;
    }

    public void setRootNodeKey(String rootNodeKey)
    {
        this.rootNodeKey = rootNodeKey;
    }
}