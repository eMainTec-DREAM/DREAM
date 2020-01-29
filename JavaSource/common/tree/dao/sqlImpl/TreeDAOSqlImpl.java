package common.tree.dao.sqlImpl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.tree.action.TreeAction;
import common.tree.dao.TreeDAO;
import common.util.QuerySqlBuffer;

/**
 * 해당 Tree의 Nodes(folders, items)를 구하는 class
 * 
 * @author javaworker
 * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
 * @since 1.0
 * @spring.bean id="treeDAOTarget"
 * @spring.txbn id="treeDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class TreeDAOSqlImpl extends JdbcDaoSupport implements TreeDAO
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
    public List findT2TreeList(String parentNo, boolean folder)
    {
        if (parentNo == null || parentNo == "" || parentNo == "!")
        {
            parentNo = "0";
        }
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        /*
         * Table : T2EQUIPMENT parent_no 의 이하 값들을 조회한다.
         */
        query.append("SELECT '['+item_no+'] '+description, equip_no, ");
        query.append("       description, item_no                       ");
        query.append("FROM   T2EQUIPMENT a                              ");
        query.append("WHERE  location = '" + parentNo + "'              ");
        
        // 페기 상태인것 Tree에서 안보이게 한다.
        //query.append("  AND  ISNULL(eq_status, 'NULL') <> 'A'              ");
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아인경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
		
        query.append("             (SELECT equip_no                   ");
        query.append("              FROM   T2EQUIPMENT b              ");
        query.append("              WHERE  b.location = a.equip_no)   ");
        query.append("ORDER BY item_no                                ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findImpEqTreeList(String parentNo, boolean folder)
    {
        if (parentNo == null || parentNo == "" || parentNo == "!")
        {
            parentNo = "0";
        }
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        /*
         * Table : T2EQUIPMENT parent_no 의 이하 값들을 조회한다.
         */
        query.append("SELECT '['+item_no+'] '+description, equip_no, ");
        query.append("       description, item_no                       ");
        query.append("FROM   IMP_DATA_EQ a                              ");
        query.append("WHERE  location = '" + parentNo + "'              ");
        
        // 페기 상태인것 Tree에서 안보이게 한다.
        //query.append("  AND  ISNULL(eq_status, 'NULL') <> 'A'              ");
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아인경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT equip_no                   ");
        query.append("              FROM   IMP_DATA_EQ b              ");
        query.append("              WHERE  b.location = a.equip_no)   ");
        query.append("ORDER BY seq_no                                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public String[] getParentNodes(String searchNode, String treeType)
    { 
        List result = null;
        String[] parents = null;
        
        //결과값이 없을경우 전체 Root 조회한다.
        //이미 삭제된 Code를 조회하는경우이다.
        //List getResult = getSearchNode(searchNode);
        
        if (searchNode == null || searchNode.equals(""))
        {   
            // 검색노드가 없는 경우 Root 만을 셋팅한다.
            // Root 를 셋팅하는데, Query로 Root를 구하게 수정이 필요할거 같다.
            // 현재 쿼리로 구하려니 여러개의 Root 가 나온다. 
            // 문의 하여 0, ! 만이 Root 인것을 알고 셋팅...하였다.
            parents = new String[1];
            
            if ((TreeAction.LOCATTREE).equals(treeType))
            {
                parents[0] = "0";
            }
            else
            {
                parents[0] = "!";
            }     
        }
        else if ((TreeAction.LOCATTREE).equals(treeType))
        {
            // 계층 구조의 Tree
            result = findLevelParent(searchNode);
            parents = QuerySqlBuffer.toStringSingleArray(result);
        }
        else if ((TreeAction.DEPT_TREE).equals(treeType))
        {
            // 계층 구조의 Tree
            result = findDeptParent(searchNode);
            parents = QuerySqlBuffer.toStringSingleArray(result);
        }
        else if(TreeAction.EQTYPTREE.equals(treeType) || TreeAction.CATEGTREE.equals(treeType)) 
        {
            // 계통,종류
            result = findEqClassParent(searchNode, treeType);
            parents = QuerySqlBuffer.toStringSingleArray(result);
        }
        else if(TreeAction.HSEDOC.equals(treeType)) 
        {   //System.out.println("실행됨");
            // 계통,종류
            result = findHseDocParent(searchNode, treeType);
            parents = QuerySqlBuffer.toStringSingleArray(result);
        }
        else
        {
            result = findEqtypParent(searchNode, treeType);
            parents = QuerySqlBuffer.toStringSingleArray(result);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("findParentNode() - end");
        }
        return parents;
    }
    
    /**
     * T2EQClASS 의 Code 조회
     * parent nodes 를 구한다.
     * @author  pochul2423
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode : 검색 node key
     * @return 챙ㄷ
     * @throws Exception
     */
    private List getSearchNode(String searchNode)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("       x.code                 ");
        query.append("FROM   T2EQ_CLASS x           ");
        query.append("WHERE x.code='"+searchNode+"' ");
         
        return getJdbcTemplate().queryForList(query.toString());     
    }
    
    /**
     * T4DIR_DTL 테이블의 code Tree
     * parent nodes 를 구한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode : 검색 node key
     * @param treeType   : tree 종류
     * @return parent nodes
     * @throws Exception
     */
    private List findEqtypParent(String searchNode, String treeType)
    {         
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.append("SELECT parent_code                        ");
        query.append("FROM   T4DIR_DTL                          ");
        query.append("START WITH code = '" + searchNode + "' AND dir_type = '" + treeType + "'   ");
        query.append("CONNECT BY PRIOR parent_code = code  AND dir_type = '" + treeType + "'     ");
         
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * T4DIR_DTL 테이블의 code Tree
     * parent nodes 를 구한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode : 검색 node key
     * @param treeType   : tree 종류
     * @return parent nodes
     * @throws Exception
     */
    private List findHseDocParent(String searchNode, String treeType)
    {         
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                ");
        query.append("       parent_code            ");
        query.append("FROM   T2HSE_DOC              ");
        query.append("START WITH doc_no= '" + searchNode + "'           ");
        query.append("CONNECT BY PRIOR parent_code=doc_no       ");
        query.append("UNION     ");
        query.append("SELECT        ");
        query.append("'!'       ");
        query.append("FROM dual     ");
        query.append("order by parent_code DESC     ");

        return getJdbcTemplate().queryForList(query.toString());
    }
 
    /**
     * T4DIR_DTL 테이블의 code Tree
     * parent nodes 를 구한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode : 검색 node key
     * @param treeType   : tree 종류
     * @return parent nodes
     * @throws Exception
     */
    private List findEqClassParent(String searchNode, String treeType)
    {         
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT p_code                             ");
        query.append("FROM   T2EQ_CLASS                         ");
        query.append("START WITH code = '" + searchNode + "' AND eq_class = '" + treeType + "'    ");
        query.append("CONNECT BY PRIOR p_code = code   AND eq_class = '" + treeType + "'          ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * T2EQUIPMENT 테이블의 code Tree
     * parent nodes 를 구한다.
     * @author  javaworker
     * @version $Id: TreeDAO.java,v 1.8 2014/02/20 08:00:04 javaworker Exp $
     * @since   1.0
     * 
     * @param searchNode : 검색 node key
     * @return parent nodes
     * @throws Exception
     */
    private List findLevelParent(String searchNode)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
         query.append("SELECT location                              ");
         query.append("FROM   T2EQUIPMENT                            ");
         query.append("START WITH equip_no = '" + searchNode + "'    ");
         query.append("CONNECT BY PRIOR location = equip_no         ");
         
         List result = getJdbcTemplate().queryForList(query.toString());
         
         return result;
    }
    
    private List findDeptParent(String searchNode)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
         query.append("SELECT p_dept_no                                 ");
         query.append("FROM   T4DEPT                                    ");
         query.append("START WITH dept_no = '" + searchNode + "'        ");
         query.append("CONNECT BY PRIOR p_dept_no = dept_no             ");
         
         List result = getJdbcTemplate().queryForList(query.toString());
         
         return result;
    }
    
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
	public List findT4TreeList(String parentNo, boolean folder, String treeType) 
    {
        logger.debug("start findT4TreeList()");
        
        List resultList = null;
        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        // T2 테이블에서 값을 4개를 조회한다.
        // Tree 구성을 위해서는 최소 4개의 값을 무조건 조회하여야 한다.
        query.append("SELECT description, code,                 "); // 보여지는 값, key
        query.append("       description key1, code key2        "); // description, code
        query.append("FROM   T4DIR_DTL a                        ");
        query.append("WHERE  a.dir_type = '" + treeType + "'    ");
        
        query.append("  AND  a.parent_code = '" + parentNo + "' ");
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT code                     ");
        query.append("              FROM   T4DIR_DTL b              ");
        query.append("              WHERE  b.parent_code = a.code   ");
        query.append("                AND  b.dir_type = '" + treeType + "') ");
        query.append("ORDER BY code                                 ");
        
        resultList = getJdbcTemplate().queryForList(query.toString());
        logger.debug("end findT4TreeList()");
        return resultList;
	}
    
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
    public String findSearchNode(String equipNo, String treeType)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        //===================================================================
        // tree종류에 따라 key값을 location, category, eq_type에 조건으로 한다.
        String columnName = "";
        
        if (TreeAction.LOCATTREE.equals(treeType))
        {
            columnName = "location";
        }
        else if (TreeAction.CATEGTREE.equals(treeType))
        {
            columnName = "eq_category";
        }
        else if (TreeAction.EQTYPTREE.equals(treeType))
        {
            columnName = "eq_type";
        }
        
        query.append("SELECT " + columnName + "                 ");
        query.append("FROM   T2EQUIPMENT                        ");
        query.append("WHERE  equip_no = '" + equipNo + "'       ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }

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
    public List findDeptTreeList(String parent_no, boolean folder)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.append("SELECT '['+dept_no+'] '+description, dept_no, ");
        query.append("       description a, '' b                       ");
        query.append("FROM   T4DEPT x                                  ");
        query.append("WHERE  p_dept_no =  '" + parent_no + "'          ");
        
        if (!"!".equals(parent_no)){
        	query.append("AND    p_dept_no = '" + parent_no + "'       ");
        }
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아인경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("              (SELECT dept_no                 ");
        query.append("               FROM   T4DEPT                  ");
        query.append("               WHERE  p_dept_no = x.dept_no)  ");
        query.append("ORDER BY dept_no                              ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

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
    public List findDeptAllNodes(String searchNode, String rootNodeKey)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        /*
         * connect by로 계층을 만들기 전에
         * 폴더형태의 부서가 앞쪽에 위치하도록 isFolers로 desc 정렬한다.
         */
        query.append("SELECT                                            ");
        query.append("      description+'['+dept_no+']',             ");
        query.append("      dept_no,                                    ");
        query.append("      description a, '' , p_dept_no,              ");
        query.append("      (SELECT CASE WHEN COUNT(p_dept_no) > 0      ");
        query.append("                   THEN 'Y'                       ");
        query.append("                    ELSE 'N'                      ");
        query.append("             END                                  ");
        query.append("      FROM   T4DEPT                               ");
        query.append("      WHERE  p_dept_no = x.dept_no                ");
        query.append("      )  isFolders                                ");
        query.append("FROM  T4DEPT x                                    ");
        query.append("WHERE 1=1                                         ");
        query.append("START WITH  x.p_dept_no = '" + rootNodeKey + "'   ");
        query.append("CONNECT BY PRIOR x.dept_no = x.p_dept_no          ");
        query.append("ORDER BY LEVEL, p_dept_no, 6, dept_no             ");

        return getJdbcTemplate().queryForList(query.toString());
    }

    public List findClassTreeList(String parentNo, boolean folder)
    {
        if (parentNo == null || parentNo.equals(""))
        {
            parentNo = "is null";
        }
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        /*
         * Table : T2WORK_ORDER parent_wo의 이하 값들을 조회한다.
         */
        query.append("SELECT '['+a.class_no+'] '+description,        ");
        query.append("            a.class_no classNo,                   ");
        query.append("            description,                          ");
        query.append("            a.class_no ckeyNo                     ");
        query.append("FROM   T2CLASS a                                  ");
        query.append("WHERE  1=1                                        ");

        if("is null".equals(parentNo))
        {
            query.append("AND  a.p_class_no is null                              ");
        }
        else
        {
            query.append("AND  a.p_class_no = '" + parentNo + "'                 ");
        }
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT b.class_no                       ");
        query.append("              FROM   T2CLASS b                     ");
        query.append("              WHERE  b.p_class_no = a.class_no)       ");
        query.append("ORDER BY a.class_no                                   ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findUomTreeList(String parentNo, boolean folder)
    {
        if (parentNo == null || parentNo.equals(""))
        {
            parentNo = "is null";
        }
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        /*
         * Table : T2WORK_ORDER parent_wo의 이하 값들을 조회한다.
         */
        query.append("SELECT '['+a.uom+']'+description,         ");
        query.append("            a.uom ,                          ");
        query.append("            description,                     ");
        query.append("            a.uom ckeyNo                     ");
        query.append("FROM   T2UOM a                               ");
        query.append("WHERE  1=1                                   ");

        if("is null".equals(parentNo))
        {
            query.append("AND  a.parent_uom is null                              ");
        }
        else
        {
            query.append("AND  a.parent_uom = '" + parentNo + "'                 ");
        }
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT b.uom                       ");
        query.append("              FROM   T2UOM b                     ");
        query.append("              WHERE  b.parent_uom = a.uom)       ");
        query.append("ORDER BY uom                                     ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public String[] findPjtRoot(String searchNode)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT pjt_no, '['+pjt_no+'] '+description     ");
        query.append("FROM  (                                           ");
        query.append("       SELECT pjt_no, description, p_pjt_no       ");
        query.append("       FROM   T2PROJECT x                         ");
        query.append("       START WITH x.pjt_no = '" + searchNode + "' ");
        query.append("       CONNECT BY PRIOR x.p_pjt_no = x.pjt_no     ");
        query.append(")                                                 ");
        query.append("WHERE  p_pjt_no IS NULL                           "); // 최상위 project 검색
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.singleRowToStringArray(resultList);
    }
    
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
    public List findProjectAllNodes(String searchNode, String rootNodeKey)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       '['+pjt_no+'] '+description,            ");
        query.append("       pjt_no,                                    ");
        query.append("       description a, '' , p_pjt_no,              ");
        query.append("      (SELECT CASE WHEN COUNT(p_pjt_no) > 0       ");
        query.append("                   THEN 'Y'                       ");
        query.append("                   ELSE 'N'                       ");
        query.append("              END                                 ");
        query.append("       FROM   T2PROJECT  a                        ");
        query.append("       WHERE  p_pjt_no = x.pjt_no)                ");
        query.append("       isFolders                                  ");
        query.append("FROM   T2PROJECT x                                ");
        query.append("WHERE  x.pjt_status NOT IN ('PX', 'TX')           "); // 최소된 공사는 제외
        query.append("START WITH  x.p_pjt_no = '" + rootNodeKey + "'    ");
        query.append("CONNECT BY PRIOR x.pjt_no = x.p_pjt_no            ");
        query.append("ORDER BY LEVEL, p_pjt_no, 6, pjt_no               ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findEqClassTreeList(String parentNo, boolean isFolder, String treeType)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // T2 테이블에서 값을 4개를 조회한다.
        // Tree 구성을 위해서는 최소 4개의 값을 무조건 조회하여야 한다.
        query.append("SELECT '['+code+'] '+description, code,    "); // 보여지는 값, key
        query.append("       description key1, code key2            "); // description, code
        query.append("FROM   T2EQ_CLASS a                           ");
        query.append("WHERE  a.eq_class = '" + treeType + "'        ");
        
        query.append("  AND  a.p_code = '" + parentNo + "'          ");
        
        if (isFolder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT code                             ");
        query.append("              FROM   T2EQ_CLASS b                     ");
        query.append("              WHERE  b.p_code = a.code                ");
        query.append("                AND  b.eq_class = '" + treeType + "') ");
        query.append("ORDER BY code                                         ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findHseDocTreeList(String parentNo, boolean isFolder)
    {   
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // T2 테이블에서 값을 4개를 조회한다.
        // Tree 구성을 위해서는 최소 4개의 값을 무조건 조회하여야 한다.
        query.append("SELECT '['+doc_no_view+'] '+doc_name, doc_no code,         ");
        query.append("       doc_name key1, doc_no key2                             ");
        query.append("FROM   T2HSE_DOC a                                            ");
        query.append("WHERE  a.parent_code = '" + parentNo + "'                     ");
        
        if (isFolder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT doc_no                           ");
        query.append("              FROM   T2HSE_DOC b                      ");
        query.append("              WHERE  b.parent_code = a.doc_no)        ");
        query.append("ORDER BY doc_no_view                                  ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findPMenuTreeList(String parentNo, boolean isFolder)
    {   
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT '['+description+'] '+description, menu_id code,     ");
        query.append("       description key1, menu_id key2                         ");
        query.append("FROM   TAMENU a                                               ");
        query.append("WHERE  a.p_menu_id = '" + parentNo + "'                       ");
        
        if (isFolder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT menu_id                          ");
        query.append("              FROM   TAMENU b                         ");
        query.append("              WHERE  b.p_menu_id = a.menu_id)         ");
        query.append("ORDER BY menu_id                                      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findPageTreeList(String parentNo, boolean isFolder)
    {   
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // T2 테이블에서 값을 4개를 조회한다.
        // Tree 구성을 위해서는 최소 4개의 값을 무조건 조회하여야 한다.
        query.append("SELECT '['+description+'] '+description, page_id code,     ");
        query.append("       description key1, page_id key2                         ");
        query.append("FROM   TAPAGE a                                               ");
        
        if (isFolder)
        {
            // folder 인 경우이다.
            query.append("  where  1=2            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  where  1=1            ");
        }
        query.append("ORDER BY page_id                                      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
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
    public List findMenuTreeList(String parentNo, boolean isFolder)
    {   
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // T2 테이블에서 값을 4개를 조회한다.
        // Tree 구성을 위해서는 최소 4개의 값을 무조건 조회하여야 한다.
        query.append("SELECT '['+description+'] '+description, menu_id code,     ");
        query.append("       description key1, menu_id key2                         ");
        query.append("FROM   TAMENU a                                               ");
        query.append("WHERE  a.page_id IS NOT NULL                                  ");
        
        if (isFolder)
        {
            // folder 인 경우이다.
            query.append("  AND  1=2            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  AND  1=1            ");
        }
        query.append("ORDER BY menu_id                                      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
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
    public List findButtonTreeList(String parentNo, boolean isFolder)
    {   
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // T2 테이블에서 값을 4개를 조회한다.
        // Tree 구성을 위해서는 최소 4개의 값을 무조건 조회하여야 한다.
        query.append("SELECT '['+description+'] '+description, button_id code,     ");
        query.append("       description key1, button_id key2                         ");
        query.append("FROM   TABUTTON a                                               ");
        
        if (isFolder)
        {
            // folder 인 경우이다.
            query.append("  WHERE  1=2            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  WHERE  1=1            ");
        }
        query.append("ORDER BY button_id                                      ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findRedClassTreeList(String parentNo, boolean folder,
            String treeType)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.append("SELECT '['+code+'] '+description, code,     "); // 보여지는 값, key
        query.append("       description key1, code key2             "); // description, code
        query.append("FROM   T2REDTAG_CLASS a                        ");
        query.append("WHERE  a.class_type = '" + treeType + "'       ");
        
        query.append("  AND  a.parent_code = '" + parentNo + "'      ");
        
        if (folder)
        {
            // folder 인 경우이다.
            query.append("  AND  EXISTS            ");
        }
        else
        {
            // folder 가 아닌 경우이다.
            query.append("  AND  NOT EXISTS        ");
        }
        
        query.append("             (SELECT code                     ");
        query.append("              FROM   T2REDTAG_CLASS b         ");
        query.append("              WHERE  b.parent_code = a.code   ");
        query.append("                AND  b.class_type = '" + treeType + "') ");
        query.append("ORDER BY code                                 ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return resultList;
    }
    
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
    public List findCalTreeList(String parentNo, boolean folder)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        // Table : T2MOR_HDR worked_date 년 월을 조회한다.
        if("!".equals(parentNo))
        {
            if(folder)
            {
                query.append("SELECT                                                                ");
                query.append("       '['+x.worked_date+']',                                       ");
                query.append("       x.worked_date,                                                 ");
                query.append("       x.worked_date description,                                     ");
                query.append("       x.worked_date dkeyNo                                           ");
                query.append("FROM   (SELECT DISTINCT SUBSTRING(a.worked_date, 1, 4) worked_date       ");
                query.append("        FROM   T2MOR_HDR a                                            ");
                query.append("        WHERE  a.worked_date IS NOT NULL) x                           ");
                query.append("WHERE  1=1                                                            ");
                query.append("  AND  x.worked_date IS NOT NULL                                      ");
                query.append("ORDER  BY x.worked_date DESC                                          ");
            }
            else 
            {
                query.append("SELECT *       ");
                query.append("FROM   dual    ");
                query.append("WHERE  1=0     ");
            }
        }
        else
        {
            if(!folder)
            {
                query.append("SELECT                                                                        ");
                query.append("       '['+SUBSTRING(x.worked_date,1,4)+'.'+SUBSTRING(x.worked_date,5,2)+']',   ");
                query.append("       x.worked_date,                                                         ");
                query.append("       x.worked_date description,                                             ");
                query.append("       x.worked_date dkeyNo                                                   ");
                query.append("FROM   (SELECT DISTINCT SUBSTRING(a.worked_date, 1, 6) worked_date               ");
                query.append("        FROM   T2MOR_HDR a                                                    ");
                query.append("        WHERE  a.worked_date IS NOT NULL) x                                   ");
                query.append("WHERE  1=1                                                                    ");
                query.append("  AND  SUBSTRING(x.worked_date, 1, 4) = '" + parentNo + "'                       ");
                query.append("  AND  x.worked_date IS NOT NULL                                              ");
                query.append("ORDER  BY x.worked_date DESC                                                  ");
            }
            else 
            {
                query.append("SELECT *       ");
                query.append("FROM   dual    ");
                query.append("WHERE  1=0     ");
            }
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }

}
