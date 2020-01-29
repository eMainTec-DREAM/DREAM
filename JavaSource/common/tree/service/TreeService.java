package common.tree.service;

import java.util.List;

public interface TreeService
{
    public String[][] findTreeFolder(String sParent_no, String treeType);

    public String[][] findTreeItem(String sParent_no, String treeType);

    /**
     * node를 검색하여 해당 node까지의 tree 구조를 리턴한다.
     * 계층 구조만 검색한다.
     * 
     * @author javaworker
     * @version $Id: TreeService.java,v 1.1 2013/08/30 09:14:23 javaworker Exp $
     * @since 1.0
     * @param searchNode : 검색할 node
     * @param treeType   : tree 종류
     * @return 해당 node 까지의 tree
     * @throws Exception
     */
    public List findTreeNode(String searchNode, String treeType);
    
    /**
     * node를 검색하여 해당 node까지의 tree 구조를 리턴한다.
     * 모든 node를 검색하여 tree를 전부 펼친다
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
     * treeType 에 따라 search Node를 조회한다.
     * 계층인경우 equip no
     * 이하[위치, 기능, 종류]인 경우는 T4DIR_DTL.code 값을 조회한다.
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
     * SearchNode로 최상위 Root Node 조회
     * @author  javaworker
     * @version $Id: TreeService.java,v 1.1 2013/08/30 09:14:23 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode
     * @return
     */
    public String[] findRootInfo(String treeType, String searchNode);
}
