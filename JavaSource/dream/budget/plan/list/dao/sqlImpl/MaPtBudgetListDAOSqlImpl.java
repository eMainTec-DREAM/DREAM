package dream.budget.plan.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.budget.plan.list.dao.MaPtBudgetListDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 예산관리 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maPtBudgetListDAOTarget"
 * @spring.txbn id="maPtBudgetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtBudgetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtBudgetListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @return List
     */
    public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                            		");
        query.append("       ''                                            seqNo,            	");
        query.append("       ''                                             isDelCheck,        	");
        query.append("       SUBSTRING(x.yyyymm,1, 4)+'-'+SUBSTRING(x.yyyymm, 5, 2) yyyymm,    	");
        query.append("       x.accnt_no accntNo,												");
        query.append("       dbo.SFACODE_TO_DESC(x.accnt_no,'ACCNT_NO','SYS','','"+loginUser.getLangId()+"')    accntDesc,		");
        query.append("		(SELECT user_name													");
        query.append("		   FROM TAUSER														");
        query.append("		  WHERE comp_no = x.comp_no											");
        query.append("		    AND user_id = x.upd_by)	updBy,									");
        query.getDate("x.upd_date", "updDate,");
        query.append("       x.bgt_plan_id bgtPlanId,											");
        query.append("       x.comp_no compNo 													");
        query.append("FROM   TABGTPLAN  x                                                		");
    	query.append("WHERE  1=1																");
        query.append(this.getWhere(maPtBudgetCommonDTO,loginUser));
//    	query.append("ORDER  BY x.bgt_plan_id                                  					");
        query.getOrderByQuery("x.bgt_plan_id", "x.accnt_no", maPtBudgetCommonDTO.getOrderBy(), maPtBudgetCommonDTO.getDirection());

        
        return getJdbcTemplate().queryForList(query.toString(maPtBudgetCommonDTO.getIsLoadMaxCount(), maPtBudgetCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(maPtBudgetCommonDTO.getBgtPlanId()))
        {
            query.getAndQuery("x.bgt_plan_id", maPtBudgetCommonDTO.getBgtPlanId());
            return query.toString();
        }
        
        query.getCodeLikeQuery("x.accnt_no", "dbo.SFACODE_TO_DESC(x.accnt_no,'ACCNT_NO','SYS','','"+loginUser.getLangId()+"')", maPtBudgetCommonDTO.getAccountNo(), maPtBudgetCommonDTO.getAccountDesc());
        query.getAndQuery("x.yyyymm", maPtBudgetCommonDTO.getYyyymm());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int deleteHeader(String key, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TABGTPLAN                                              ");
        query.append("WHERE  bgt_plan_id  = ?                                       ");  
        
        Object[] objects = new Object[] {   
                key
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    
    public int deleteDetail(String key, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TABGTDEPTPLAN                                              ");
        query.append("WHERE  bgt_plan_id  = ?                                           ");  
        
        Object[] objects = new Object[] {   
                  key
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String findTotalCount(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;		");
        query.append("SELECT               	 								");
        query.append("       COUNT(1)       								");
        query.append("FROM   TABGTPLAN  x      								");
    	query.append("WHERE  1=1											");
        query.append(this.getWhere(maPtBudgetCommonDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
        
	}
}