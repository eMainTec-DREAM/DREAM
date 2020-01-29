package common.tree.form;

import java.util.List;
import common.struts.BaseForm;

/**
 * popup 으로 Tree를 열면서 해당 Node와 Root - Node 에 해당하는 Tree 가 구성된다.
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
     * tree 형태로 모두 들어가 있는 경우이다. ArrayList 안에 HashMap으로 들어가 있다.
     */
    private List   TreeNodes;
    /** 검색 Node */
    private String searchNode;
    /** Root Name */
    private String rootName;
    /** Root Node Key */
    private String rootNodeKey;
    /** Tree 종류 */
    private String treeType;
    /** Tree 동작 구분 */
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