package dream.fail.code.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.fail.code.dao.MaFailureDetailDAO;
import dream.fail.code.dto.MaFailureDetailDTO;

/**
 * 고장코드 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maFailureDetailDAOTarget"
 * @spring.txbn id="maFailureDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaFailureDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaFailureDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public MaFailureDetailDTO findDetail(User user, String failureId)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                        compNo,     ");
        query.append("       x.failure_id                           failureId,  ");
        query.append("       x.failure_no                           failureNo,  ");
        query.append("       x.description		                    failureDesc,");
        query.append("       x.fail_type                            failType,   ");
        query.append("       SFACODE_TO_DESC(x.fail_type, 'FAILURE_TYPE', 'SYS', x.comp_no,'"+user.getLangId()+"') failTypeDesc,    ");
        query.append("       x.is_use                               isUse,      ");
        query.append("       x.ord_no                               ordNo,      ");
        query.append("       x.key_type                             keyType,    ");
        query.append("       x.key_no                               keyNo,      ");
        query.append("       (SELECT key_name									");
        query.append("        FROM TALANG										");
        query.append("        WHERE key_type=x.key_type							");
        query.append("       	AND key_no = x.key_no							");
        query.append("       	AND lang = '"+lang+"') 				failName,	");
        query.append("       x.remark                               remark      ");
        query.append("FROM   TAFAILURE x                                        ");
        query.append("WHERE  x.comp_no    = '"+user.getCompNo()+"'              ");
        query.append("  AND  x.failure_id = '"+failureId+"'	                    ");
    
        MaFailureDetailDTO maFailureDetailDTO = 
        		(MaFailureDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaFailureDetailDTO()));
        
        return maFailureDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     */
    public int insertDetail(MaFailureDetailDTO maFailureDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAFAILURE(                                    ");
    	query.append("   comp_no,      failure_id,  failure_no,   description,	");
    	query.append("   fail_type,    is_use,       ord_no,	                ");
    	query.append("   remark,       key_type,   key_no                       ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,		   ?,                           ");
    	query.append("	 ?,            ?,          ?              		        ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maFailureDetailDTO.getCompNo(),
    			maFailureDetailDTO.getFailureId(),
    			maFailureDetailDTO.getFailType()+maFailureDetailDTO.getFailureNo(),
    			maFailureDetailDTO.getFailName(),
    			maFailureDetailDTO.getFailType(),
    			maFailureDetailDTO.getIsUse(),
    			maFailureDetailDTO.getOrdNo(),
    			maFailureDetailDTO.getRemark(),
    			"FAILCODE",
    			maFailureDetailDTO.getFailureNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     */
    public int updateDetail(MaFailureDetailDTO  maFailureDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAFAILURE SET	                ");
    	query.append("	     description    = ?,	        ");
        query.append("       fail_type      = ?,            ");
        query.append("       is_use         = ?,            ");
        query.append("       ord_no         = ?,            ");
        query.append("       remark         = ?,            ");
        query.append("       key_type       = ?,            ");
        query.append("       key_no         = ?             ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  failure_id     = ?	            ");
    	
    	Object[] objects = new Object[] {
                maFailureDetailDTO.getFailName(),
                maFailureDetailDTO.getFailType(),
                maFailureDetailDTO.getIsUse(),
                maFailureDetailDTO.getOrdNo(),
                maFailureDetailDTO.getRemark(),
                "FAILCODE",
                maFailureDetailDTO.getFailureNo(),
                maFailureDetailDTO.getCompNo(),
                maFailureDetailDTO.getFailureId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     */
    public String validFailureNo(MaFailureDetailDTO maFailureDetailDTO)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAFAILURE x                                        ");
        query.append("WHERE  x.comp_no   = '" + maFailureDetailDTO.getCompNo()   + "'");
        query.append("  AND  x.failure_no= '" + maFailureDetailDTO.getFailureNo()+ "'");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }

	public void makeLangData(MaFailureDetailDTO maFailureDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("  INSERT INTO TALANG(lang_id,lang,key_type,key_no,key_name,IS_COMM_JS_USE, use_web) 		");
		query.append("  SELECT SQALANG_ID.NEXTVAL, cdsysd_no, 'FAILCODE', ?, ?, 'N', 'Y'  FROM TACDSYSD where list_type ='LANG' AND is_use ='Y'	");

    	
    	Object[] objects = new Object[] {
    			maFailureDetailDTO.getFailureNo(),
    			maFailureDetailDTO.getFailName()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public void updateLangData(MaFailureDetailDTO maFailureDetailDTO) {
		
		QueryBuffer query = new QueryBuffer();

		query.append("MERGE INTO TALANG a                                                                   ");
		query.append("USING(    SELECT                                                      				");
		query.append("                 ? keyName,                                                           ");
		query.append("                 ? keyType,                                                           ");
		query.append("                 ? keyNo,                                                            	");
		query.append("                 ? lang                                                         		");
		query.append("            FROM DUAL) b                                                              ");
		query.append("ON(     a.key_type = b.keyType                                                        ");
		query.append("    AND a.key_no = b.keyNo   															");
		query.append("    AND a.lang = b.lang )                                                            	");
		query.append("WHEN MATCHED THEN                                                                     ");
		query.append("    UPDATE SET   a.key_name = b.keyName                                               ");
		query.append("WHEN NOT MATCHED THEN                                                                	");
		query.append("    INSERT (lang_id,	lang,  key_type,   key_no, 	key_name,   IS_COMM_JS_USE, 	use_web )        			");
		query.append("    VALUES (SQALANG_ID.NEXTVAL,      b.lang,     b.keyType,      b.keyNo,     b.keyName,   'N',    'Y'  )   	");

    	
    	Object[] objects = new Object[] {
    			maFailureDetailDTO.getFailName(),
    			"FAILCODE",
    			maFailureDetailDTO.getFailureNo(),
    			maFailureDetailDTO.getUserLang()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
		
	}
}