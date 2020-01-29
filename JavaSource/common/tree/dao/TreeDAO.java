package common.tree.dao;

import java.util.List;

/**
 * 해당 Tree의 Nodes(folders, items)를 구하는 class
 * 
 * @author javaworker
 * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
 * @since 1.0
 */
public interface TreeDAO
{
    /**
     * sParent_no 가 null 이거나 '' 인 경우 root 인 0 으로 조회한다. equip_no 가 다른 row에
     * parent_no 이면 folder 이고, equip_no 가 다른 row의 parent_no 가 아니면 item 이다.
     * 
     * @author javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since 1.0
     * @param sParent_no : 검색할 parent_no
     * @param bFolder    : true[folder], false[item]
     * @return 검색한 nodes List
     */
    public List findT2TreeList(String parentNo, boolean folder);
    
    /**
     * sParent_no 가 null 이거나 '' 인 경우 root 인 0 으로 조회한다. equip_no 가 다른 row에
     * parent_no 이면 folder 이고, equip_no 가 다른 row의 parent_no 가 아니면 item 이다.
     * 
     * @author javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since 1.0
     * @param sParent_no : 검색할 parent_no
     * @param bFolder    : true[folder], false[item]
     * @return 검색한 nodes List
     */
    public List findImpEqTreeList(String parentNo, boolean folder);
    
    /**
     * 해당 node의 상의 모든 parent node를 배열로 순서대로 배열에 담아서 리턴한다. 
     * ex.) String [0] : searchNode's parentNode 
     *      String [1] : parent 1 
     *      String [2] : root
     * 
     * @author javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since 1.0
     * @param searchNode : 검색 Node
     * @param treeType   : Tree 종류
     * @return 현 node의 parent nodes
     * @throws Exception
     */
    public String[] getParentNodes(String searchNode, String treeType);
    
    /**
     * Parent Node 의 이하 child nodes 를 검색한다.
     * 계층, 위치, 기능, 종류 별로 검색한다.
     * 계층 table : T2EQUIPMENT
     * 이하 table : T4DIR_DTL
     *  
     * @param parentNo : parent node key
     * @param folder   : true [folder 인 경우]
     *                   false [item 인 경우]
     * @param treeType : LEVEL[계층], 
     *                   LOCATION[위치], 
     *                   CATEGORY[기능], 
     *                   EQ_TYPE[종류] 
     * @return         : 바로 밑의 child nodes
     * @throws Exception
     */
	public List findT4TreeList(String parentNo, boolean folder, String treeType);
	
    
    /**
     * treeType 에 따라 search Node를 조회한다.
     * 계층인경우 equip no
     * 이하[위치, 기능, 종류]인 경우는 T4DIR_DTL.code 값을 조회한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param keyValue
     * @param treeType
     * @return
     * @throws Exception
     */
    public String findSearchNode(String equipNo, String treeType);

    /**
     * 부서 검색 Tree
     * parent_no 에 해당하는 값들을 조회한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param parent_no : 검색 parent node
     * @param folder : ture[폴더], false[아이템]
     * @return
     * @throws Exception
     */
    public List findDeptTreeList(String parent_no, boolean folder);

    /**
     * 모든 node를 검색하여 tree를 전부 펼친다
     * @author  wondo
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param srhDeptNo
     * @param rootNodeKey
     * @return
     * @throws Exception
     */
    public List findDeptAllNodes(String searchNode, String rootNodeKey);

    public List findClassTreeList(String parentNo, boolean folder);
    
    /**
     * 단위 Tree 검색 T2UOM
     * @author  jung7126
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param b
     * @return
     */
    public List findUomTreeList(String parentNo, boolean folder);
    
    /**
     * Project Tree의
     * Search Node로 Root 정보 조회
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode
     * @return
     */
    public String[] findPjtRoot(String searchNode);
    
    /**
     * 전체 Project Node를 조회한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode
     * @param rootNodeKey 
     * @return
     */
    public List findProjectAllNodes(String searchNode, String rootNodeKey);
    
    /**
     * 설비분류(계층, 종류)
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param treeType 
     * @param b
     * @return
     */
    public List findEqClassTreeList(String parentNo, boolean isFolder, String treeType);
    
    /**
     * HSE Doc
     * @author  pochul2423
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param treeType 
     * @param b
     * @return
     */
    public List findHseDocTreeList(String parentNo, boolean isFolder);
    
    /**
     * Parent Menu
     * @author  kim21017
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param treeType 
     * @param b
     * @return
     */
    public List findPMenuTreeList(String parentNo, boolean isFolder);
    
    /**
     * Page
     * @author  kim21017
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param treeType 
     * @param b
     * @return
     */
    public List findPageTreeList(String parentNo, boolean isFolder);

    /**
     * Page
     * @author  kim21017
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param treeType 
     * @param b
     * @return
     */
    public List findMenuTreeList(String parentNo, boolean isFolder);

    /**
     * Button
     * @author  kim21017
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param treeType 
     * @param b
     * @return
     */
    public List findButtonTreeList(String parentNo, boolean isFolder);
    
    /**
     * Red Tag Group/위치
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param sParent_no
     * @param b
     * @param treeType
     * @return
     */
    public List findRedClassTreeList(String parentNo, boolean folder, String treeType);
    
    /**
     * Morning Report 트리 구성(년월)
     * @author  hiimkkm
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param parentNo
     * @param folder
     * @return
     */
    public List findCalTreeList(String parentNo, boolean folder);
}
