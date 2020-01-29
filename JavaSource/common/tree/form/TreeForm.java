package common.tree.form;

import common.struts.BaseForm;

/**
 * Tree 전송을 위한 Sample 이다.
 * 
 * @author javaworker
 * @version $Id: TreeForm.java,v 1.1 2013/08/30 09:12:06 javaworker Exp $
 * @since 1.0
 * @struts.form name="treeForm"
 */
public class TreeForm
        extends BaseForm
{
    // Tree를 위해서 셋팅한다.
    private String[][] folders;
    private String[][] items;
    private String     parentNode = "";
    private String     treeType   = "";

    public String getParentNode()
    {
        return parentNode;
    }

    public void setParentNode(String parentNode)
    {
        this.parentNode = parentNode;
    }

    public String[][] getFolders()
    {
        return folders;
    }

    public void setFolders(String[][] folders)
    {
        this.folders = folders;
    }

    public String[][] getItems()
    {
        return items;
    }

    public void setItems(String[][] items)
    {
        this.items = items;
    }

    public String getTreeType()
    {
        return treeType;
    }

    public void setTreeType(String treeType)
    {
        this.treeType = treeType;
    }
}
