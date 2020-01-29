package dream.mgr.lang.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.lang.dao.MaResListDAO;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 언어 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maResListDAOTarget"
 * @spring.txbn id="maResListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaResListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaResListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResCommonDTO
     * @return List
     */
    public List findResList(MaResCommonDTO maResCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
        
        String filterLangId = maResCommonDTO.getFilterLangId();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;         ");
        query.append("SELECT ''                                      seqNo      ");
        query.append("       ,a.key_type                             keyTypeId  "); 
        query.append("       ,dbo.SFACODE_TO_DESC(a.key_type,'KEY_TYPE','SYS','','"+user.getLangId()+"') keyTypeDesc "); 
        query.append("       ,a.key_no                               keyNo      "); 
        query.append("       ,a.lang_id                              langId 	"); 
        query.append("       ,dbo.SFACODE_TO_DESC('"+filterLangId+"','LANG','SYS','','"+user.getLangId()+"')  langDesc   "); 
        query.append("		 ,(SELECT key_name 									");
        query.append("		   FROM TALANG										");
        query.append("		  WHERE key_type = a.key_type						");
        query.append("		    AND key_no	 = a.key_no							");
        query.getStringEqualQuery("lang", filterLangId);
        query.append("		 ) keyName											");
        query.append("		 ,(SELECT key_name 									");
        query.append("		   FROM TALANG										");
        query.append("		  WHERE key_type = a.key_type						");
        query.append("		    AND key_no	 = a.key_no							");
        query.getStringEqualQuery("lang", "en");
        query.append("		 ) keyNameEn										");
        query.append("		 ,(SELECT key_name 									");
        query.append("		   FROM TALANG										");
        query.append("		  WHERE key_type = a.key_type						");
        query.append("		    AND key_no	 = a.key_no							");
        query.getStringEqualQuery("lang", "ko");
        query.append("		 ) keyNameKo										");
        query.append("  FROM TALANG	a											");
        query.append("  WHERE 1=1												");
        query.append(this.getWhere(maResCommonDTO, user));
        query.getOrderByQuery("a.lang_id","a.key_type, a.key_no", maResCommonDTO.getOrderBy(), maResCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maResCommonDTO.getIsLoadMaxCount(), maResCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaResCommonDTO maResCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(maResCommonDTO.getLangId()))
        {
        	query.getAndQuery("a.lang_id", maResCommonDTO.getLangId());
            return query.toString();
        }
        query.getAndQuery("a.lang", maResCommonDTO.getFilterLangId());
        query.getLikeQuery("a.key_name", maResCommonDTO.getFilterKeyName());
        query.getSysCdQuery("a.key_type", maResCommonDTO.getFilterKeyTypeId(), maResCommonDTO.getFilterKeyTypeDesc(), "KEY_TYPE", user.getCompNo(), user.getLangId());
        query.getLikeQuery("a.key_no", maResCommonDTO.getFilterKeyNo());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(MaResCommonDTO maResCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;         ");
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("  FROM TALANG	a											");
        query.append("  WHERE 1=1												");
        query.append(this.getWhere(maResCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

}