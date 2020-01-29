package dream.budget.plan.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaPtDeptBgListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtDeptBgListDAO
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
        QueryBuffer query = new QueryBuffer();      
  
        query.append("SELECT  a.*, 										        ");
        query.append("       MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,		");
        query.append("       LEVEL												");
        query.append("FROM(SELECT											    ");
        query.append("       ''  seqNo,											");
        query.append("       y.description            AS deptDesc,              ");
        query.append("       NVL(x.plan_amt,'0') sumPlanAmt,					");
        query.append("       x.plan_amt planAmt,								");
        query.append("       x.bgt_dept_plan_id bgtDeptPlanId,					");
        query.append("       x.bgt_plan_id bgtPlanId,   					    ");
        query.append("       y.dept_id                AS id,				    ");
        query.append("       y.dept_id                AS deptId,				");
        query.append("       y.p_dept_id              AS pDeptId,				");
        query.append("       y.dept_no                AS deptNo,				");
        query.append("       y.ord_no											");
        query.append("FROM   TABGTDEPTPLAN x, TADEPT y							");
        query.append("WHERE  x.dept_id(+) = y.dept_id  							");
        query.append(this.getWhere(maPtBudgetCommonDTO,loginUser));
        query.append(" ) a 														");
        query.append("WHERE 1 = 1												");
        query.append(this.getWhereForRow(maPtBudgetCommonDTO,loginUser));
        query.append(" START WITH pDeptId = 0									");
        query.append(" CONNECT BY PRIOR deptId = pDeptId						");
        //query.append(" order by deptNo                     						");
        
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
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TABGTDEPTPLAN                                ");
    	query.append("WHERE  comp_no     = '"+loginUser.getCompNo()+"'         ");
    	query.append("  AND  bgt_dept_plan_id = '"+id+"'			           ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
//    	//1 row 재검색
//        if (!"".equals(maPtBudgetCommonDTO.getBgtDeptPlanId()) || !"".equals(maPtBudgetCommonDTO.getDeptId()))
//        {
//          return query.toString();
//        }
    	query.getAndQuery("x.bgt_plan_id(+)", maPtBudgetCommonDTO.getBgtPlanId());
    	query.getAndQuery("y.comp_no", loginUser.getCompNo());
    	
    	return query.toString();
    }
    
    private String getWhereForRow(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	//1 row 재검색
        if (!"".equals(maPtBudgetCommonDTO.getBgtDeptPlanId()))
        {
          query.getAndQuery("bgtDeptPlanId", maPtBudgetCommonDTO.getBgtDeptPlanId());
          return query.toString();
        }
        else 
        {
          query.getAndQuery("deptId", maPtBudgetCommonDTO.getDeptId());
          return query.toString();
        }

    }
}