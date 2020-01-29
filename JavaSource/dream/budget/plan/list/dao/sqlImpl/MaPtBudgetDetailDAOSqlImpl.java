package dream.budget.plan.list.dao.sqlImpl;



import java.lang.reflect.Field;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.budget.plan.list.dao.MaPtBudgetDetailDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtBudgetDetailDTO;

/**
 * 예산관리 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maPtBudgetDetailDAOTarget"
 * @spring.txbn id="maPtBudgetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtBudgetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtBudgetDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaPtBudgetDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            		");
        query.append("       x.yyyymm,	    													");
        query.append("       x.accnt_no accountNo,												");
        query.append("       dbo.SFACODE_TO_DESC(x.accnt_no,'ACCNT_NO','SYS','','"+loginUser.getLangId()+"')    accountDesc,		");
        query.append("       x.upd_by updById,													");
        query.append("		(SELECT user_name													");
        query.append("		   FROM TAUSER														");
        query.append("		  WHERE comp_no = x.comp_no											");
        query.append("		    AND user_id = x.upd_by)	updBy,									");
        query.append("       x.upd_date updDate,												");
        query.append("       x.bgt_plan_id bgtPlanId,											");
        query.append("       x.plan_amt planAmt,     											");
        query.append("       (SELECT SUM(a.plan_amt)											");
        query.append("        FROM   TABGTDEPTPLAN a											");
        query.append("        WHERE  a.bgt_plan_id = x.bgt_plan_id) assignAmt,           		");
        query.append("       x.remark,        													");
        query.append("       x.comp_no compNo 													");
        query.append("FROM   TABGTPLAN  x                                                		");
    	query.append("WHERE  1=1																");
    	query.getAndQuery("x.bgt_plan_id", maPtBudgetCommonDTO.getBgtPlanId());
    	query.getAndQuery("x.comp_no", maPtBudgetCommonDTO.getCompNo());
    
        MaPtBudgetDetailDTO maPtBudgetDetailDTO = 
        		(MaPtBudgetDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtBudgetDetailDTO()));
        
        return maPtBudgetDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetDetailDTO
     * @return
     */
    public int insertDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TABGTPLAN(                                    ");
    	query.append("   yyyymm,      accnt_no,    upd_by,     upd_date,	    ");
    	query.append("   bgt_plan_id, plan_amt,    comp_no,    remark  		    ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,		   ?,           ?               ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maPtBudgetDetailDTO.getYyyymm(),
    			maPtBudgetDetailDTO.getAccountNo(),
    			maPtBudgetDetailDTO.getUpdById(),
    			maPtBudgetDetailDTO.getUpdDate(),
    			maPtBudgetDetailDTO.getBgtPlanId(),
    			maPtBudgetDetailDTO.getPlanAmt(),
    			maPtBudgetDetailDTO.getCompNo(),
    			maPtBudgetDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetDetailDTO
     * @return
     */
    public int updateDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TABGTPLAN SET	                ");
    	query.append("	     yyyymm         = ?,	        ");
    	query.append("       accnt_no       = ?,            ");
        query.append("       upd_by         = ?,            ");
        query.append("       upd_date       = ?,            ");
        query.append("       plan_amt       = ?,            ");
        query.append("       remark         = ?,            ");
        query.append("       comp_no        = ?             ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  bgt_plan_id    = ?	            ");
    	
    	Object[] objects = new Object[] {
    			maPtBudgetDetailDTO.getYyyymm(),
    			maPtBudgetDetailDTO.getAccountNo(),
    			maPtBudgetDetailDTO.getUpdById(),
    			maPtBudgetDetailDTO.getUpdDate(),
    			maPtBudgetDetailDTO.getPlanAmt(),
    			maPtBudgetDetailDTO.getRemark(),
    			maPtBudgetDetailDTO.getCompNo(),
    			maPtBudgetDetailDTO.getCompNo(),
    			maPtBudgetDetailDTO.getBgtPlanId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}