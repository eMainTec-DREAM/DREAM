package common.tree.dao;

import java.util.List;

/**
 * �ش� Tree�� Nodes(folders, items)�� ���ϴ� class
 * 
 * @author javaworker
 * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
 * @since 1.0
 */
public interface TreeDAO
{
    /**
     * sParent_no �� null �̰ų� '' �� ��� root �� 0 ���� ��ȸ�Ѵ�. equip_no �� �ٸ� row��
     * parent_no �̸� folder �̰�, equip_no �� �ٸ� row�� parent_no �� �ƴϸ� item �̴�.
     * 
     * @author javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since 1.0
     * @param sParent_no : �˻��� parent_no
     * @param bFolder    : true[folder], false[item]
     * @return �˻��� nodes List
     */
    public List findT2TreeList(String parentNo, boolean folder);
    
    /**
     * sParent_no �� null �̰ų� '' �� ��� root �� 0 ���� ��ȸ�Ѵ�. equip_no �� �ٸ� row��
     * parent_no �̸� folder �̰�, equip_no �� �ٸ� row�� parent_no �� �ƴϸ� item �̴�.
     * 
     * @author javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since 1.0
     * @param sParent_no : �˻��� parent_no
     * @param bFolder    : true[folder], false[item]
     * @return �˻��� nodes List
     */
    public List findImpEqTreeList(String parentNo, boolean folder);
    
    /**
     * �ش� node�� ���� ��� parent node�� �迭�� ������� �迭�� ��Ƽ� �����Ѵ�. 
     * ex.) String [0] : searchNode's parentNode 
     *      String [1] : parent 1 
     *      String [2] : root
     * 
     * @author javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since 1.0
     * @param searchNode : �˻� Node
     * @param treeType   : Tree ����
     * @return �� node�� parent nodes
     * @throws Exception
     */
    public String[] getParentNodes(String searchNode, String treeType);
    
    /**
     * Parent Node �� ���� child nodes �� �˻��Ѵ�.
     * ����, ��ġ, ���, ���� ���� �˻��Ѵ�.
     * ���� table : T2EQUIPMENT
     * ���� table : T4DIR_DTL
     *  
     * @param parentNo : parent node key
     * @param folder   : true [folder �� ���]
     *                   false [item �� ���]
     * @param treeType : LEVEL[����], 
     *                   LOCATION[��ġ], 
     *                   CATEGORY[���], 
     *                   EQ_TYPE[����] 
     * @return         : �ٷ� ���� child nodes
     * @throws Exception
     */
	public List findT4TreeList(String parentNo, boolean folder, String treeType);
	
    
    /**
     * treeType �� ���� search Node�� ��ȸ�Ѵ�.
     * �����ΰ�� equip no
     * ����[��ġ, ���, ����]�� ���� T4DIR_DTL.code ���� ��ȸ�Ѵ�.
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
     * �μ� �˻� Tree
     * parent_no �� �ش��ϴ� ������ ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param parent_no : �˻� parent node
     * @param folder : ture[����], false[������]
     * @return
     * @throws Exception
     */
    public List findDeptTreeList(String parent_no, boolean folder);

    /**
     * ��� node�� �˻��Ͽ� tree�� ���� ��ģ��
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
     * ���� Tree �˻� T2UOM
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
     * Project Tree��
     * Search Node�� Root ���� ��ȸ
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode
     * @return
     */
    public String[] findPjtRoot(String searchNode);
    
    /**
     * ��ü Project Node�� ��ȸ�Ѵ�.
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
     * ����з�(����, ����)
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
     * Red Tag Group/��ġ
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
     * Morning Report Ʈ�� ����(���)
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
