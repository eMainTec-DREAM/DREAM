package dream.rcm.system.dao.sqlImpl;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.rcm.system.dao.RcmSysListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="rcmSysListDAOTarget"
 * @spring.txbn id="rcmSysListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, User user) throws IOException
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck,	");
        query.append("       x.rcmlist_no                           rcmListNo,  ");
        query.append("       x.description                          description,"); 
        query.append("       dbo.SFACODE_TO_DESC(x.rcmlist_status, 'RCMLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') rcmListStatus,");
        query.append("       x.reg_date                             regDate,    "); 
        query.append("       dbo.SFACODE_TO_DESC(x.rcm_categ, 'RCM_CATEG', 'USR', x.comp_no,'')     rcmCateg,  "); 
        query.append("       x.remark                               remark,     ");
        query.append("       x.rcmlist_id                           rcmListId  	");
        query.append("FROM   TARCMLIST x                                        ");
        query.append("WHERE  1=1                                                "); 
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.append(this.getWhere(rcmSysCommonDTO, user));
        query.getOrderByQuery("x.rcmlist_id","x.rcmlist_id", rcmSysCommonDTO.getOrderBy(), rcmSysCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmSysCommonDTO.getIsLoadMaxCount(), rcmSysCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(RcmSysCommonDTO rcmSysCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(rcmSysCommonDTO.getRcmListId()))
        {
            query.getAndQuery("x.rcmlist_id", rcmSysCommonDTO.getRcmListId());
            return query.toString();
        }
        
        // System 분석명 
        query.getLikeQuery("x.description", rcmSysCommonDTO.getFilterRcmDesc());
        // 등록일자
        query.getAndDateQuery("x.reg_date", rcmSysCommonDTO.getFilterStartDate(), rcmSysCommonDTO.getFilterEndDate());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteList(String compNo, String Id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TARCMLIST                           ");
        query.append("WHERE  comp_no  	= ?                      ");
        query.append("  AND  rcmlist_id	= ?                      ");

        Object[] objects = new Object[] {   
                compNo,
                Id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String  findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT					");
    	query.append("		count(1)			");
    	query.append("FROM   TARCMLIST x		");
    	query.append("WHERE  1 = 1              ");

    	query.append(this.getWhere(rcmSysCommonDTO, user));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}