package dream.fail.code.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.fail.code.dao.MaFailureListDAO;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maFailureListDAOTarget"
 * @spring.txbn id="maFailureListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaFailureListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaFailureListDAO
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
    public List findList(MaFailureCommonDTO maFailureCommonDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
       
        query.append("SELECT ''                                       seqNo,       ");
        query.append("       ''                                       isDelCheck,  ");
        query.append("       x.comp_no                                compNo,      ");
        query.append("       x.failure_id                             failureId,   ");
        query.append("       SFACODE_TO_DESC(x.fail_type, 'FAILURE_TYPE', 'SYS', x.comp_no,'"+user.getLangId()+"') failTypeDesc,    ");
        query.append("       x.failure_no                             failureNo,   ");
        query.append("       x.description                            failureDesc, ");
        query.append("       (SELECT a.key_name                                    ");
        query.append("          FROM TALANG a                                      ");
        query.append("         WHERE a.key_type=x.key_type                         ");
        query.append("           AND a.key_no = x.key_no                           ");
        query.append("           AND a.lang = '"+lang+"')             failName,    ");
        query.append("       x.ord_no                                 ordNo,       ");
        query.append("       x.is_use                                 isUse        ");
        query.append("FROM   TAFAILURE x                                           ");
    	query.append("WHERE  x.comp_no = '"+user.getCompNo()+"'");
        query.append(this.getWhere(maFailureCommonDTO,user));
//    	query.append("ORDER  BY x.fail_type, x.ord_no                              ");
        query.getOrderByQuery("x.fail_type, x.ord_no", maFailureCommonDTO.getOrderBy(), maFailureCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maFailureCommonDTO.getIsLoadMaxCount(), maFailureCommonDTO.getFirstRow()));
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
        query.getUsrCdQuery("x.fail_type", maFailureCommonDTO.getFilterFailType(), maFailureCommonDTO.getFilterFailTypeDesc(), "FAILURE_TYPE", maFailureCommonDTO.getFilterCompNo(), user.getLangId());
        
        if(!"".equals(maFailureCommonDTO.getFilterFailName())&&maFailureCommonDTO.getFilterFailName()!=null){
        	query.append("AND x.key_no IN	( SELECT key_no						");
        	query.append("					FROM TALANG							");
        	query.append("					WHERE key_type='FAILCODE'			");
        	query.getStringEqualQuery("lang", lang);
        	query.getLikeQuery("key_name", maFailureCommonDTO.getFilterFailName());
        	query.append("				)									");
        }
        query.getLikeQuery("x.description", maFailureCommonDTO.getFilterFailureDesc());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public int deleteParts(String compNo, String failureId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAFAILURE                                          ");
        query.append("WHERE  comp_no   = ?                                      ");
        query.append("  AND  failure_id = ?                                     ");      
        
        Object[] objects = new Object[] {   
                compNo,
                failureId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String findTotalCount(MaFailureCommonDTO maFailureCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                     				");
        query.append("       COUNT(1)                               			");
        query.append("FROM   TAFAILURE x                                        ");
    	query.append("WHERE  x.comp_no = '"+user.getCompNo()+"'");
        query.append(this.getWhere(maFailureCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}

    @Override
    public int deleteLangData(String compNo, String failureId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TALANG                         ");
        query.append("WHERE  key_type = 'FAILCODE'          ");
        query.append("  AND  key_no   = (SELECT failure_no  ");      
        query.append("                   FROM TAFAILURE     ");      
        query.append("                   WHERE comp_no  = ? ");      
        query.append("                   AND failure_id = ? ");      
        query.append("                   )                  ");      
        
        Object[] objects = new Object[] {   
                compNo,
                failureId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}