package common.tree.service;

import java.util.List;

public interface TreeService
{
    public String[][] findTreeFolder(String sParent_no, String treeType);

    public String[][] findTreeItem(String sParent_no, String treeType);

    /**
     * node�� �˻��Ͽ� �ش� node������ tree ������ �����Ѵ�.
     * ���� ������ �˻��Ѵ�.
     * 
     * @author javaworker
     * @version $Id: TreeService.java,v 1.1 2013/08/30 09:14:23 javaworker Exp $
     * @since 1.0
     * @param searchNode : �˻��� node
     * @param treeType   : tree ����
     * @return �ش� node ������ tree
     * @throws Exception
     */
    public List findTreeNode(String searchNode, String treeType);
    
    /**
     * node�� �˻��Ͽ� �ش� node������ tree ������ �����Ѵ�.
     * ��� node�� �˻��Ͽ� tree�� ���� ��ģ��
     * @author  wondo
     * @version $Id: TreeService.java,v 1.1 2013/08/30 09:14:23 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode
     * @param treeType
     * @return
     */
    public List findTreeAllNodes(String searchNode, String treeType, String rootNodeKey);
    
    /**
     * treeType �� ���� search Node�� ��ȸ�Ѵ�.
     * �����ΰ�� equip no
     * ����[��ġ, ���, ����]�� ���� T4DIR_DTL.code ���� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: TreeService.java,v 1.1 2013/08/30 09:14:23 javaworker Exp $
     * @since   1.0
     * 
     * @param keyValue
     * @param treeType
     * @return
     * @throws Exception
     */
    public String findSearchNode(String equipNo, String treeType);

    /**
     * SearchNode�� �ֻ��� Root Node ��ȸ
     * @author  javaworker
     * @version $Id: TreeService.java,v 1.1 2013/08/30 09:14:23 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode
     * @return
     */
    public String[] findRootInfo(String treeType, String searchNode);
}
