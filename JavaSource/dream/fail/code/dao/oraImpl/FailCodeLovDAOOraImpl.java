package dream.fail.code.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.fail.code.dao.FailCodeLovDAO;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="failCodeLovDAOTarget"
 * @spring.txbn id="failCodeLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class FailCodeLovDAOOraImpl extends BaseJdbcDaoSupportOra implements FailCodeLovDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureCommonDTO
     * @return List
     */
    public List findList(MaFailureCommonDTO maFailureCommonDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap)
    { 
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
       
        query.append("SELECT ''                                       seqNo,       ");
        query.append("       ''                                       isDelCheck,  ");
        query.append("       x.comp_no                                compNo,      ");
        query.append("       x.failure_id                             failure_id,  ");
        query.append("       SFACODE_TO_DESC(x.fail_type, 'FAILURE_TYPE', 'SYS', x.comp_no,'"+user.getLangId()+"') failTypeDesc,    ");
        query.append("       x.failure_no                             failure_no,  ");
        query.append("       y.key_name                               failureDesc, ");
        query.append("       y.key_name                               failName,    ");
        query.append("       x.ord_no                                 ordNo,       ");
        query.append("       x.is_use                                 isUse        ");
		query.append("FROM   TAFAILURE x INNER JOIN TALANG y 					");
		query.append("  ON   x.key_no =y.key_no AND x.key_type= y.key_type AND y.lang= '"+user.getLangId()+"'                  ");     
		query.append("WHERE  1=1                  								");
		query.getAndQuery("x.is_use", "Y");
    	query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.fail_type", conditionMap);
        query.append(this.getWhere(maFailureCommonDTO,user));
    	query.append("ORDER  BY x.fail_type, x.ord_no                              ");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaFailureCommonDTO maFailureCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maFailureCommonDTO.getFailureId()))
        {
            query.getAndQuery("x.failure_id", maFailureCommonDTO.getFailureId());
            return query.toString();
        }
        
        // 고장구분 
        query.getLikeQuery("y.key_name", maFailureCommonDTO.getFilterFailName());
        
        return query.toString();
    }

}