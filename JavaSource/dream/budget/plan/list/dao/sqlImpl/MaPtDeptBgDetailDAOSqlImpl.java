package dream.budget.plan.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.budget.plan.list.dao.MaPtDeptBgDetailDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtDeptBgDetailDTO;

/**
 * 何前芭贰贸 惑技 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtDeptBgDetailDAOTarget"
 * @spring.txbn id="maPtDeptBgDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtDeptBgDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtDeptBgDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtDeptBgDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	 query.append("SELECT											        ");
         query.append("       y.description            AS deptDesc,             ");
         query.append("       x.plan_amt planAmt,								");
         query.append("       x.bgt_dept_plan_id bgtDeptPlanId,					");
         query.append("       x.bgt_plan_id bgtPlanId,   					    ");
         query.append("       y.dept_id                AS deptId,				");
         query.append("       y.p_dept_id              AS pDeptId,				");
         query.append("       y.dept_no                AS deptNo,				");
         query.append("       y.ord_no											");
         query.append("FROM   TABGTDEPTPLAN x RIGHT OUTER JOIN TADEPT y			");
         query.append("	  ON  x.dept_id = y.dept_id  					    	");
         query.append("WHERE  1=1						 					    ");
         query.getAndQuery("y.dept_id", maPtBudgetCommonDTO.getDeptId());
         query.getAndQuery("x.bgt_dept_plan_id", maPtBudgetCommonDTO.getBgtDeptPlanId());
         
        MaPtDeptBgDetailDTO maPtDeptBgDetailDTO = 
        		(MaPtDeptBgDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtDeptBgDetailDTO()));
        
        return maPtDeptBgDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailDTO
     * @return
     */
    public int updateVendor(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TABGTDEPTPLAN SET				");
    	query.append("	     plan_amt	    	= ?,		");
    	query.append("	     upd_by	    	    = ?,		");
    	query.append("	     upd_date	    	= CONVERT(VARCHAR, GETDATE(), 112)	");
    	query.append("WHERE  bgt_dept_plan_id   = ?			");
    	
    	Object[] objects = new Object[] {
    			maPtDeptBgDetailDTO.getPlanAmt(),
    			loginUser.getUserId(),
    			maPtDeptBgDetailDTO.getBgtDeptPlanId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailDTO
     * @return
     */
    public int insertPtVendor(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TABGTDEPTPLAN (				                    ");
    	query.append("	 comp_no,   bgt_dept_plan_id,    bgt_plan_id,    plan_amt,  ");
    	query.append("	 dept_id,                        upd_date,       upd_by     ");
    	query.append(")	VALUES (                                                	");
    	query.append("	 ?,			?,	            ?,          ?,              	");
    	query.append("	 ?,			  	            CONVERT(VARCHAR, GETDATE(), 112), ?  ");
    	query.append(")									                        	");
    	
    	Object[] objects = new Object[] {
    			maPtDeptBgDetailDTO.getCompNo(),
    			maPtDeptBgDetailDTO.getBgtDeptPlanId(),
    			maPtDeptBgDetailDTO.getBgtPlanId(),
    			maPtDeptBgDetailDTO.getPlanAmt(),
    			maPtDeptBgDetailDTO.getDeptId(),
//    			maPtDeptBgDetailDTO.getYyyymm(),
    			loginUser.getUserId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	public MaPtDeptBgDetailDTO findNewDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
   	 	query.append("SELECT											        ");
   	    query.append("       NEXT VALUE FOR SQABGT_DEPT_PLAN_ID AS bgtDeptPlanId,      ");
        query.append("       y.description            AS deptDesc,              ");
        query.append("       y.dept_id                AS deptId 				");
        query.append("FROM   TADEPT y							");
        query.append("WHERE  1 = 1  							");
        query.getAndQuery("y.dept_id", maPtBudgetCommonDTO.getDeptId());
        
       MaPtDeptBgDetailDTO maPtDeptBgDetailDTO = 
       		(MaPtDeptBgDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtDeptBgDetailDTO()));
       
       return maPtDeptBgDetailDTO;
	}
}