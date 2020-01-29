package dream.budget.account.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.budget.account.dao.BudgetAccountListDAO;
import dream.budget.account.dto.BudgetAccountCommonDTO;

/**
 * 예산계정 - 목록 dao
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="budgetAccountListDAOTarget"
 * @spring.txbn id="budgetAccountListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BudgetAccountListDAOOraImpl extends BaseJdbcDaoSupportOra implements BudgetAccountListDAO
{
    public List findList(BudgetAccountCommonDTO budgetAccountCommonDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                                                                                                ");
        query.append("    ''                                                                            AS isDelCheck       ");
        query.append("    ,''                                                                           AS seqNo            ");
        query.append("    ,x.accnt_id                                                                   AS accntId          ");
        query.append("    ,x.accnt_no                                                                   AS accntNo          ");
        query.append("    ,x.description                                                                AS accntDesc        ");
        query.append("    ,x.is_use                                                                     AS isUse            ");
        query.append("    ,x.remark                                                                     AS remark           ");
        query.append("    ,x.accnt_type                                                                 AS accntType        ");
        query.append("    ,SFACODE_TO_DESC(x.accnt_type,'ACCNT_TYPE','SYS','','"+user.getLangId()+"')   AS accntTypeDesc    ");
        query.append("FROM TAACCOUNT x                                                                                      ");
        query.append("WHERE 1=1                                                                                             ");
    	query.append(this.getWhere(budgetAccountCommonDTO, user));
    	query.getOrderByQuery("x.accnt_no", budgetAccountCommonDTO.getOrderBy(), budgetAccountCommonDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(budgetAccountCommonDTO.getIsLoadMaxCount(), budgetAccountCommonDTO.getFirstRow()));
    } 
    
    private String getWhere(BudgetAccountCommonDTO budgetAccountCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(budgetAccountCommonDTO.getAccntId()))
        {
            query.getAndQuery("x.accnt_id", budgetAccountCommonDTO.getAccntId());
            return query.toString();
        }
        query.getAndQuery("x.accnt_no", budgetAccountCommonDTO.getFilterAccntNo());
        
        query.getLikeQuery("x.description", budgetAccountCommonDTO.getFilterAccntDesc());
        
        query.getSysCdQuery("x.is_use", budgetAccountCommonDTO.getFilterIsUse(), budgetAccountCommonDTO.getFilterIsUse(), "IS_USE", user.getCompNo(), user.getLangId());
        
        query.getSysCdQuery("x.accnt_type", budgetAccountCommonDTO.getFilterAccntType(), budgetAccountCommonDTO.getFilterAccntTypeDesc(), "ACCNT_TYPE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }
    
    public int deleteList(String accntId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAACCOUNT  ");
        query.append("WHERE  comp_no  = ?    ");
        query.append("  AND  accnt_id = ?    ");      
        
        Object[] objects = new Object[] {   
            user.getCompNo()
            ,accntId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(BudgetAccountCommonDTO budgetAccountCommonDTO,User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAACCOUNT x                                        ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(budgetAccountCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}