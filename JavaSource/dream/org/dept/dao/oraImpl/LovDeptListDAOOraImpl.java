package dream.org.dept.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovDeptListDAO
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("       dept_id                AS id,						");
        query.append("       dept_id                AS deptId,					");
        query.append("       p_dept_id              AS pDeptId,					");
        query.append("       dept_no                AS deptNo,					");
        query.append("		 ''                     AS seqNo,                   ");
        query.append("       description            AS deptDesc,				");
        query.append("       MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,		");
        query.append("       LEVEL,												");
        query.append("       dept_categ deptCateg,								");
        query.append("       is_lowest_lvl isLowestLvl							");
		query.append("     , x.plant 					AS plant				");
		query.append("     , (SELECT a.description 								");
		query.append("        FROM TAPLANT a 									");
		query.append("        WHERE a.comp_no = x.comp_no 						");
		query.append("          AND a.plant = x.plant)  AS plantDesc			");
        query.append("FROM   TADEPT												");
        query.append("WHERE 1=1													");
        query.append("    and is_use = 'Y'  									");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("dept_categ", lovDeptListDTO.getCodeType());
        query.getAndQuery("dept_no", lovDeptListDTO.getDeptNo());
        query.getLikeQuery("description", lovDeptListDTO.getDeptDesc());
        
        if(!"".equals(lovDeptListDTO.getExtCode1()))
        {
        	query.append("  AND  comp_no  ='"+ lovDeptListDTO.getExtCode1()+"'	");
        }  
        
//        String deptStr = "0";
//        String deptCol = "p_dept_id";
//        if(lovDeptListDTO.getDeptNo()==null||"".equals(lovDeptListDTO.getDeptNo())){
//        	if(lovDeptListDTO.getDeptDesc()!=null&&!"".equals(lovDeptListDTO.getDeptDesc())){
//        		deptStr = lovDeptListDTO.getDeptDesc();
//            	deptCol = "description";
//        	}
//        }else{
//        	deptStr = lovDeptListDTO.getDeptNo();
//        	deptCol = "dept_no";
//        }
        
        query.append(" START WITH p_dept_id = 0									");
        query.append(" CONNECT BY PRIOR dept_id = p_dept_id						");
//        query.append(" order by dept_no                     					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findDeptAcList(LovDeptListDTO lovDeptListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("       x.dept_id                AS id,					");
        query.append("       x.dept_id                ,							");
        query.append("       x.p_dept_id              ,							");
        query.append("       x.dept_no                ,							");
        query.append("		 ''                     AS seqNo,                   ");
        query.append("       x.description            ,							");
        query.append("       MIN(LEVEL) OVER() AS minLvl,		");
        query.append("       LEVEL,												");
        query.append("       x.dept_categ ,										");
        query.append("       SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', comp_no,'"+user.getLangId()+"') isMaint ,  ");
        query.append("       x.is_lowest_lvl 									");
		query.append("		 ,(SELECT a.emp_id  								");
		query.append("		 	FROM TAEMP a  									");
		query.append("		 	WHERE a.comp_no=x.comp_no						");
		query.append("		 	AND a.dept_id = x.dept_id						");
		query.append("		 	AND a.is_response='Y'							");
		query.append("		 	AND rownum = 1) responseBy						");
		query.append("		 ,(SELECT a.emp_name  								");
		query.append("		 	FROM TAEMP a  									");
		query.append("		 	WHERE a.comp_no=x.comp_no						");
		query.append("		 	AND a.dept_id = x.dept_id						");
		query.append("		 	AND a.is_response='Y'							");
		query.append("		 	AND rownum = 1) responseDesc					");
		query.append("     , x.plant 					AS plant				");
		query.append("     , (SELECT a.description 								");
		query.append("        FROM TAPLANT a 									");
		query.append("        WHERE a.comp_no = x.comp_no 						");
		query.append("          AND a.plant = x.plant)  AS plantDesc			");
		query.append("     , x.wcode_id 									    ");
		query.append("     , (SELECT a.wname 								    ");
		query.append("        FROM TAWAREHOUSE a 								");
		query.append("        WHERE a.comp_no = x.comp_no 						");
		query.append("          AND a.wcode_id = x.wcode_id)  AS wname			");
		query.append("     ,is_maint  					AS is_maint				");
        query.append("FROM   TADEPT x											");
        query.append("WHERE 1=1													");
        query.append("    and x.is_use = 'Y'  									");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.getAndQuery("x.is_maint", conditionMap);
        query.getAndQuery("x.dept_categ", conditionMap);
        query.getAndQuery("x.is_monitoring", conditionMap);
        query.getAndQuery("x.plant", conditionMap);
        query.getAndQuery("x.dept_categ", lovDeptListDTO.getCodeType());
        query.getAndQuery("x.dept_no", lovDeptListDTO.getDeptNo());
        query.getLikeQuery("x.description", lovDeptListDTO.getDeptDesc());
        query.getSysCdQuery("x.is_maint", lovDeptListDTO.getIsMaint(), lovDeptListDTO.getIsMaintDesc(), "IS_USE", user.getCompNo(),user.getLangId());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
                lovDeptListDTO.getPlantId(), lovDeptListDTO.getPlantDesc());
        
        query.append(" START WITH x.p_dept_id = 0									");
        query.append(" CONNECT BY PRIOR x.dept_id = x.p_dept_id						");
//        query.append(" order by dept_no                     					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

}