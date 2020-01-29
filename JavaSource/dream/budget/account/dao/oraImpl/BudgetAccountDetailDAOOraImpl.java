package dream.budget.account.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.budget.account.dao.BudgetAccountDetailDAO;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.dto.BudgetAccountDetailDTO;

/**
 * 예산계정 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="budgetAccountDetailDAOTarget"
 * @spring.txbn id="budgetAccountDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BudgetAccountDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements BudgetAccountDetailDAO
{
    public BudgetAccountDetailDTO findDetail(BudgetAccountCommonDTO budgetAccountCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                ");
        query.append("    x.accnt_id                                                                    AS accntId          ");
        query.append("    ,x.accnt_no                                                                   AS accntNo          ");
        query.append("    ,x.description                                                                AS accntDesc        ");
        query.append("    ,x.is_use                                                                     AS isUse            ");
        query.append("    ,x.remark                                                                     AS remark           ");
        query.append("    ,x.accnt_type                                                                 AS accntType        ");
        query.append("    ,SFACODE_TO_DESC(x.accnt_type,'ACCNT_TYPE','SYS','','"+user.getLangId()+"')   AS accntTypeDesc    ");
        query.append("FROM TAACCOUNT x                                                                                      ");
        query.append("WHERE 1=1                                                                                             ");
        query.append("  AND x.comp_no  = ?                                                                                  ");
        query.append("  AND x.accnt_id = ?                                                                                  ");
        
        Object[] objects = new Object[] {
    		user.getCompNo()
    		,budgetAccountCommonDTO.getAccntId()
    	};
        
        BudgetAccountDetailDTO budgetAccountDetailDTO = 
                (BudgetAccountDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new BudgetAccountDetailDTO()));
        
        return budgetAccountDetailDTO;
    }
    
    public int insertDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAACCOUNT (                              ");
    	query.append("    comp_no,         accnt_id,           accnt_no,   ");
    	query.append("    description,     is_use,             remark,     ");
    	query.append("    accnt_type                                       ");
    	query.append("    )VALUES(                                         ");
    	query.append("    ?,               ?,                  ?,          ");
    	query.append("    ?,               ?,                  ?,          ");
    	query.append("    ?                                                ");
    	query.append("    )                                                ");
    	
    	Object[] objects = new Object[] {
			user.getCompNo()
			,budgetAccountDetailDTO.getAccntId()
			,budgetAccountDetailDTO.getAccntNo()
			,budgetAccountDetailDTO.getAccntDesc()
			,budgetAccountDetailDTO.getIsUse()
			,budgetAccountDetailDTO.getRemark()
			,budgetAccountDetailDTO.getAccntType()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAACCOUNT SET         ");
    	query.append("    accnt_no         = ?     ");
    	query.append("    ,description     = ?     ");
    	query.append("    ,is_use          = ?     ");
    	query.append("    ,remark          = ?     ");
    	query.append("    ,accnt_type      = ?     ");
    	query.append("WHERE 1=1                    ");
    	query.append("AND  comp_no         = ?     ");
    	query.append("AND  accnt_id        = ?	   ");
    	
    	Object[] objects = new Object[] {
	        budgetAccountDetailDTO.getAccntNo()
	        ,budgetAccountDetailDTO.getAccntDesc()
	        ,budgetAccountDetailDTO.getIsUse()
	        ,budgetAccountDetailDTO.getRemark()
            ,budgetAccountDetailDTO.getAccntType()
            ,user.getCompNo()
            ,budgetAccountDetailDTO.getAccntId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String validAccountNo(BudgetAccountDetailDTO budgetAccountDetailDTO, User user)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)       ");
        query.append("FROM   TAACCOUNT x    ");
        query.append("WHERE 1=1             ");
        query.append("AND x.comp_no   = ?   ");
        query.append("AND x.accnt_no  = ?   ");
        query.append("AND x.accnt_id != ?   ");
        
        Object[] objects = new Object[] {
            user.getCompNo()
            ,budgetAccountDetailDTO.getAccntNo()
            ,budgetAccountDetailDTO.getAccntId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    
    }
}