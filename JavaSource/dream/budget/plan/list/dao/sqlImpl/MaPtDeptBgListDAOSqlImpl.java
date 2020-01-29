package dream.budget.plan.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.budget.plan.list.dao.MaPtDeptBgListDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 부품거래처 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtDeptBgListDAOTarget"
 * @spring.txbn id="maPtDeptBgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtDeptBgListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtDeptBgListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return List
     */
    public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();      
  
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;         ");
        query.append("SELECT												    ");
        query.append("       ''  seqNo											");
        query.append("     , (SELECT a.description 								");
        query.append("          FROM TADEPT a 									");
        query.append("         WHERE a.comp_no = y.comp_no 						");
        query.append("           AND a.dept_id = y.dept_id)         AS deptDesc ");
        query.append("     , ISNULL(x.plan_amt,'0')   AS sumPlanAmt				");
        query.append("     , x.plan_amt 			  AS planAmt				");
        query.append("     , x.bgt_dept_plan_id 	  AS bgtDeptPlanId			");
        query.append("     , x.bgt_plan_id 			  AS bgtPlanId   			");
        query.append("     , y.dept_id                AS id				    	");
        query.append("     , y.dept_id                AS deptId					");
        query.append("     , y.p_dept_id              AS pDeptId				");
        query.append("     , (SELECT a.dept_no									");
        query.append("          FROM TADEPT a 									");
        query.append("         WHERE a.comp_no = y.comp_no 						");
        query.append("           AND a.dept_id = y.dept_id)         AS deptNo   ");
        query.append("     , (SELECT a.ord_no 									");
        query.append("          FROM TADEPT a 									");
        query.append("         WHERE a.comp_no = y.comp_no 						");
        query.append("           AND a.dept_id = y.dept_id) 		AS ord_no   ");
        query.append("     , MIN(y.lvl) OVER()    					AS MINLVL   ");
        query.append("     , y.lvl 									AS LVL      ");
        query.append("FROM   TABGTDEPTPLAN x RIGHT OUTER JOIN (SELECT * FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"','0')) y			");
        query.append("  ON  x.comp_no = y.comp_no AND x.dept_id = y.dept_id		");
        query.getAndQuery("x.bgt_plan_id", maPtBudgetCommonDTO.getBgtPlanId());
        query.append("WHERE  1=1												");
        query.append(this.getWhere(maPtBudgetCommonDTO,loginUser));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptVendorId
     * @return
     */
    public int deleteList(String id, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TABGTDEPTPLAN                                ");
    	query.append("WHERE  comp_no     = '"+loginUser.getCompNo()+"'         ");
    	query.append("  AND  bgt_dept_plan_id = '"+id+"'			           ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("y.comp_no", loginUser.getCompNo());
    	
    	//1 row 재검색
        if (!"".equals(maPtBudgetCommonDTO.getBgtDeptPlanId()))
        {
        	query.getAndQuery("x.bgt_dept_plan_id", maPtBudgetCommonDTO.getBgtDeptPlanId());
        }
        else
        {
        	query.getAndQuery("y.dept_id", maPtBudgetCommonDTO.getDeptId());
        }
            	
    	return query.toString();
    }
}