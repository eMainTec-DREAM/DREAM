package dream.consult.program.lang.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.program.lang.dao.MaLangMngDetailDAO;
import dream.consult.program.lang.dto.MaLangMngDetailDTO;

/**
 * 다국어 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maLangMngDetailDAOTarget"
 * @spring.txbn id="maLangMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLangMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaLangMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param langIdKo
     * @param langIdEn
     * @return
     */
    public MaLangMngDetailDTO findDetail(String langId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("select                                                                                      ");
        query.append("     x.lang_id                                                              as langId       ");
        query.append("    ,x.lang                                                                 as lang         ");
        query.append("    ,dbo.SFACODE_TO_DESC(x.lang,'LANG','SYS','','"+user.getLangId()+"')         as langDesc     ");
        query.append("    ,x.key_type                                                             as keyType      ");
        query.append("    ,dbo.SFACODE_TO_DESC(x.key_type,'KEY_TYPE','SYS','','"+user.getLangId()+"') as keyTypeDesc  ");
        query.append("    ,x.key_no                                                               as keyNo        ");
        query.append("    ,x.key_name                                                             as keyName      ");
        query.append("    ,x.is_comm_js_use                                                       as isCommJsUse  ");
        query.append("    ,x.remark                                                               as remark       ");
        query.append("    ,x.use_web                                                              as useWeb       ");
        query.append("    ,x.use_android                                                          as useAndroid   ");
        query.append("from TALANG x                                                                               ");
        query.append("where 1=1                                                                                   ");
        query.append(" AND x.lang_id	=   ?       						                                      ");
        
        Object[] objects = new Object[] {
        		langId
    	};
    
        MaLangMngDetailDTO maLangMngDetailDTO = 
        		(MaLangMngDetailDTO)getJdbcTemplate().query(query.toString(),objects,  new MwareExtractor(new MaLangMngDetailDTO()));
        
        return maLangMngDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     */
    public int updateDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
    	
    	query.append("update TALANG set 													");
    	query.append("    key_name = ?       												");
    	query.append("    ,is_comm_js_use = ?      				  						    ");
    	query.append("    ,remark = ?       											    ");
    	query.append("    ,use_web = ?       												");
    	query.append("    ,use_android = ?       											");
    	query.append("where lang_id = ?                                                     ");
    	
    	Object[] objects = new Object[] {
    			maLangMngDetailDTO.getKeyName()
    			,maLangMngDetailDTO.getIsCommJsUse()
    			,maLangMngDetailDTO.getRemark()
    			,maLangMngDetailDTO.getUseWeb()
    			,maLangMngDetailDTO.getUseAndroid()
    			,maLangMngDetailDTO.getLangId()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     */
    public int insertDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TALANG(								");
    	query.append("	 lang_id       ,lang							");
    	query.append("	 ,key_type	   ,key_no							");
    	query.append("	 ,key_name     ,is_comm_js_use   				");
    	query.append("	 ,remark       ,use_web   				        ");
    	query.append("	 ,use_android     				                ");
    	query.append("	 )	VALUES (								    ");
    	query.append("	 next value for SQALANG_ID	 ,?		            ");
    	query.append("	 ,?				     ,?							");
    	query.append("	 ,?				     ,?							");
    	query.append("	 ,?				     ,?							");
    	query.append("	 ,?												");
    	query.append("	  )												");
    	Object[] objects = new Object[] {
    			 maLangMngDetailDTO.getLang()
    			,maLangMngDetailDTO.getKeyType()
    			,maLangMngDetailDTO.getKeyNo()
    			,maLangMngDetailDTO.getKeyName()
    			,maLangMngDetailDTO.getIsCommJsUse()
    			,maLangMngDetailDTO.getRemark()
    			,maLangMngDetailDTO.getUseWeb()
    			,maLangMngDetailDTO.getUseAndroid()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	return rtnValue;
    }
    
    /**
     * valid keyId
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     */
    public String validKeyId(MaLangMngDetailDTO maLangMngDetailDTO, User user)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	String keyNo = maLangMngDetailDTO.getLangId();
    	String keyType = maLangMngDetailDTO.getKeyType();
    	String lang = maLangMngDetailDTO.getLang();
        
        
        query.append("SELECT                               ");
        query.append("      count(*)                       ");
        query.append("FROM  TALANG x                       ");
        query.append("WHERE x.key_type = '" + keyType + "' ");
        query.append("  AND x.key_no   = '" + keyNo + "'   ");
        query.append("  AND x.lang   = '" + lang + "'   ");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    
}