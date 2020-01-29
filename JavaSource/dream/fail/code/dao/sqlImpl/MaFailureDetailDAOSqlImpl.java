package dream.fail.code.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
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
public class MaFailureDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaFailureDetailDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                        compNo,     ");
        query.append("       x.failure_id                           failureId,  ");
        query.append("       x.failure_no                           failureNo,  ");
        query.append("       x.description		                    failureDesc,");
        query.append("       x.fail_type                            failType,   ");
        query.append("       dbo.SFACODE_TO_DESC(x.fail_type, 'FAILURE_TYPE', 'SYS', x.comp_no,'"+user.getLangId()+"') failTypeDesc,    ");
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
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
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAFAILURE x                                        ");
        query.append("WHERE  x.comp_no   = '" + maFailureDetailDTO.getCompNo()   + "'");
        query.append("  AND  x.failure_no= '" + maFailureDetailDTO.getFailureNo()+ "'");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }

	@Override
	public void makeLangData(MaFailureDetailDTO maFailureDetailDTO) {
		// TODO Auto-generated method stub
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("  INSERT INTO TALANG(lang_id,lang,key_type,key_no,key_name,IS_COMM_JS_USE, use_web) 		");
		query.append("  SELECT NEXT VALUE FOR sqalang_id, cdsysd_no, 'FAILCODE', ?, ?, 'N', 'Y'  FROM TACDSYSD where list_type ='LANG' AND is_use ='Y'	");

    	
    	Object[] objects = new Object[] {
    			maFailureDetailDTO.getFailureNo(),
    			maFailureDetailDTO.getFailName()
    	};
    	
    	getJdbcTemplate().update(query.toString(), getObject(objects));
		
	}

	@Override
	public void updateLangData(MaFailureDetailDTO maFailureDetailDTO) {
		// TODO Auto-generated method stub
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("begin                                               ");
		query.append("    declare @v_count               numeric(4);      ");
		query.append("    declare @v_lang                varchar(20);     ");
		query.append("    declare @v_key_no              varchar(50);     ");
		query.append("    declare @v_key_name            varchar(500);    ");
		query.append("                                                    ");
		query.append("    set @v_lang = ?                                 ");
		query.append("    set @v_key_no = ?                               ");
		query.append("    set @v_key_name = ?                             ");
		query.append("                                                   ");
		query.append("    select @v_count = count(*)                     ");
		query.append("    from talang                                    ");
		query.append("    where lang = @v_lang                           ");
		query.append("        and key_type = 'FAILCODE'                  ");
		query.append("        and key_no = @v_key_no                     ");
		query.append("    if @v_count > 0                                ");
		query.append("       update talang set key_name = @v_key_name    ");
		query.append("       from talang                                 ");
		query.append("       where lang = @v_lang                        ");
		query.append("            and key_type = 'FAILCODE'              ");
		query.append("            and key_no = @v_key_no                 ");
		query.append("    else                                           ");
		query.append("       insert into talang(lang_id, lang, key_type, key_no, key_name, upd_date, is_comm_js_use, use_web, use_android)                  ");
		query.append("       values(NEXT VALUE FOR sqalang_id, @v_lang, 'FAILCODE', @v_key_no, @v_key_name, convert(varchar(8),getdate(),112), 'N','Y','Y') ");
		query.append("end                                                                                                                                   ");

    	
		Object[] objects = new Object[] {
				maFailureDetailDTO.getUserLang()
				,maFailureDetailDTO.getFailureNo()
    			,maFailureDetailDTO.getFailName()
    	};
    	
    	getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	
		
	}
}