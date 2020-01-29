package dream.org.dept.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.dept.dao.LovDeptListDAO;
import dream.org.dept.dto.LovDeptListDTO;

/**
 * 부서검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovDeptListDAOTarget"
 * @spring.txbn id="lovDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovDeptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovDeptListDAO
{
    /**
     * 자재 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovDeptListDTO
     * @param loginUser
     * @return
     */
    public List findDeptList(LovDeptListDTO lovDeptListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       x.dept_id              AS ID,					");
        query.append("       x.dept_id              AS DEPTID,				");
        query.append("       x.p_dept_id            AS PDEPTID,				");
        query.append("       x.dept_no              AS DEPTNO,				");
        query.append("		 ''                     AS SEQNO,               ");
        query.append("       x.description          AS DEPTDESC,			");
        query.append("       MIN(y.LVL) OVER() 		AS MINLVL,				");
        query.append("       y.LVL 					AS LEVEL,				");
        query.append("       x.dept_categ 			AS DEPTCATEG,			");
        query.append("       x.is_lowest_lvl 		AS ISLOWESTLVL			");
		query.append("     , x.plant 					AS plant			");
		query.append("     , (SELECT a.description 							");
		query.append("        FROM TAPLANT a 								");
		query.append("        WHERE a.comp_no = x.comp_no 					");
		query.append("          AND a.plant = x.plant)  AS plantDesc		");
        query.append("FROM   TADEPT	x										");
        query.append("		 ,(SELECT * FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.dept_id = y.dept_id							");
        query.append("AND 	x.is_use = 'Y'  								");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.dept_categ", lovDeptListDTO.getCodeType());
        query.getAndQuery("x.dept_no", lovDeptListDTO.getDeptNo());
        query.getLikeQuery("x.description", lovDeptListDTO.getDeptDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findDeptAcList(LovDeptListDTO lovDeptListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = conditionMap.containsKey("comp_no")?conditionMap.get("comp_no"):user.getCompNo();
        
    	query.append("SELECT                                                    				");
    	query.append("       x.dept_id 		AS ID,                        						");
    	query.append("       x.dept_id   	AS DEPT_ID,                         				");
    	query.append("       x.p_dept_id  	AS P_DEPT_ID,                            			");
    	query.append("       dept_no     	AS DEPT_NO,                       					");
    	query.append("       ROW_NUMBER() OVER (ORDER by y.lvl, x.ord_no)  AS SEQNO,    		");
    	query.append("       description    AS DESCRIPTION,                                     ");
    	query.append("       MIN(y.LVL) OVER() AS MINLVL,	");
    	query.append("       y.lvl as LEVEL,                                            		");
    	query.append("       dept_categ     AS DEPT_CATEG,                                      ");
    	query.append("       dbo.SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISMAINT ,  ");
    	query.append("       is_lowest_lvl  AS IS_LOWEST_LVL                           			");
		query.append("		 ,(SELECT top 1 a.emp_id  							");
		query.append("		 	FROM TAEMP a  									");
		query.append("		 	WHERE a.comp_no=x.comp_no						");
		query.append("		 	AND a.is_response='Y'							");
		query.append("		 	AND a.dept_id = x.dept_id) RESPONSEBY			");
		query.append("		 ,(SELECT top 1 a.emp_name  						");
		query.append("		 	FROM TAEMP a  									");
		query.append("		 	WHERE a.comp_no=x.comp_no						");
		query.append("		 	AND a.is_response='Y'							");
		query.append("		 	AND a.dept_id = x.dept_id) RESPONSEDESC			");
		query.append("     , x.plant 					AS PLANT				");
		query.append("     , (SELECT a.description 								");
		query.append("        FROM TAPLANT a 									");
		query.append("        WHERE a.comp_no = x.comp_no 						");
		query.append("          AND a.plant = x.plant)  AS PLANTDESC			");
		query.append("     , x.wcode_id 									    ");
		query.append("     , (SELECT a.wname 								    ");
		query.append("        FROM TAWAREHOUSE a 								");
		query.append("        WHERE a.comp_no = x.comp_no 						");
		query.append("          AND a.wcode_id = x.wcode_id)  AS WNAME			");
    	query.append("FROM   TADEPT x, (SELECT * FROM dbo.SFADEPT_ALL('"+compNo+"','0')) y  ");
    	query.append("WHERE  x.dept_id = y.dept_id                             	");
        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("x.is_use", conditionMap);
        query.getAndQuery("x.is_maint", conditionMap);
        query.getAndQuery("x.is_monitoring", conditionMap);
        query.getAndQuery("x.dept_categ", conditionMap);
        query.getAndQuery("x.plant", conditionMap);
        query.getAndQuery("x.dept_categ", lovDeptListDTO.getCodeType());
        query.getAndQuery("x.dept_no", lovDeptListDTO.getDeptNo());
        query.getLikeQuery("x.description", lovDeptListDTO.getDeptDesc());
        query.getSysCdQuery("x.is_maint", lovDeptListDTO.getIsMaint(), lovDeptListDTO.getIsMaintDesc(), "IS_USE", user.getCompNo(),user.getLangId());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
                lovDeptListDTO.getPlantId(), lovDeptListDTO.getPlantDesc());
        
        query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	
}