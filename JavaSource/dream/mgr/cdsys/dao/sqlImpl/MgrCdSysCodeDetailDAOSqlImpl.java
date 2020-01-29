package dream.mgr.cdsys.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.cdsys.dao.MgrCdSysCodeDetailDAO;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드-분류 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrCdSysCodeDetailDAOTarget"
 * @spring.txbn id="mgrCdSysCodeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCdSysCodeDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCdSysCodeDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param cdSysMId
     * @param cdSysDId
     * @return
     */
    public MgrCdSysCodeDetailDTO findDetail(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT												");
        query.append("       x.CDSYSD_NO		CODE						");
        query.append("       ,(select aa.key_name                           ");
        query.append("         from talang aa                               ");
        query.append("         where  lang = ?                              ");
        query.append("             and x.key_type = aa.key_type             ");
        query.append("             and x.key_no = aa.key_no) as CODEDESC    ");
        query.append("       ,x.ord_no			ORDNO						");
        query.append("       ,x.remark			REMARK						");
        query.append("       ,x.is_use			ISUSE						");
        query.append("       ,x.key_no			KEYNO		                ");
        query.append("       ,x.key_type		KEYTYPE	                    ");
        query.append("       ,x.list_type		LISTTYPE					");
        query.append("       ,x.cdsysd_id		CDSYSDID					");
        query.append("FROM   TACDSYSD x										");
        query.append("WHERE  x.cdsysm_id 		= ?			                ");
        query.append("  AND  x.cdsysd_id 		= ?			                ");
        
        Object[] objects = new Object[]{
        		user.getLangId()
        		,mgrCdSysCommonDTO.getCdSysMId()
        		,mgrCdSysCodeListDTO.getCdSysDId()
        };
    
        MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO = 
        		(MgrCdSysCodeDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrCdSysCodeDetailDTO()));
        
        return mgrCdSysCodeDetailDTO;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCodeDetailDTO
     * @param mgrCdSysCommonDTO
     * @return
     */
    public int updateDetail(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TACDSYSD SET	");
    	query.append("	description		= ? ");
    	query.append("	,remark			= ? ");
    	query.append("	,ord_no			= ? ");
    	query.append("	,is_use			= ? ");
    	query.append("	,key_no			= (SELECT LIST_TYPE + '.' + ? FROM tacdsysm WHERE cdsysm_id = ?) ");
    	query.append("	,key_type		= 'CODESET' ");
    	query.append("WHERE cdsysm_id 	= ?	");
    	query.append("  AND cdsysd_id	= ? ");
    	
    	Object[] objects = new Object[] {
    			mgrCdSysCodeDetailDTO.getCodeDesc()
    			,mgrCdSysCodeDetailDTO.getRemark()
    			,mgrCdSysCodeDetailDTO.getOrdNo()
    			,mgrCdSysCodeDetailDTO.getIsUse()
    			,mgrCdSysCodeDetailDTO.getCode()
    			,mgrCdSysCommonDTO.getCdSysMId()
    			,mgrCdSysCommonDTO.getCdSysMId()
    			,mgrCdSysCodeDetailDTO.getCdSysDId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String checkLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user) 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT 					");
    	query.append("    COUNT(1) 				");
    	query.append("FROM TALANG a 			");
    	query.append("WHERE 1=1					");
    	query.append("AND a.key_type 	= ? 	");
    	query.append("AND a.key_no 		=   ( SELECT 						");
    	query.append("                          	a.list_type + '.' + ? 	");
    	query.append("                          FROM TACDSYSM a 			");
    	query.append("                         WHERE a.cdsysm_id = ? )		");
    	query.append("AND a.lang 		= ?		");

    	
    	Object[] objects = new Object[] {
    			"CODESET"
    			,mgrCdSysCodeDetailDTO.getCode() //key_no
    			,mgrCdSysCommonDTO.getCdSysMId()
    			,user.getLangId()
    	};
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));

	}
    
    
    public int insertLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TALANG											");
    	query.append("(   lang_id     			, lang  			, key_type		");
    	query.append("  , key_no      			, key_name      	, upd_date		");
    	query.append("  , is_comm_js_use  		, use_web   		, use_android	");
    	query.append("  , cre_date, site_type	, version_info						");
    	query.append("  , basic_key_name											");
    	query.append(") VALUES ( 													");
    	query.append("    NEXT VALUE FOR sqalang_id	, ?				,?				");
    	query.append(", ( 	SELECT 													");
    	query.append("    		a.list_type + '.' + ? 								");
    	query.append("		  FROM TACDSYSM a 										");
    	query.append("		WHERE a.cdsysm_id = ?	)								");
    	query.append("    ,?					, ?									");
    	query.append("    ,?					, ?					,?				");
    	query.append("    ,?					, ?					,?				");
    	query.append("    ,?														");
    	query.append(")																");
    	
    	Object[] objects = new Object[] {
    			user.getLangId()	//lang
    			,"CODESET"	//key_type
    			,mgrCdSysCodeDetailDTO.getCode() //key_no
    			,mgrCdSysCommonDTO.getCdSysMId()
    			,mgrCdSysCodeDetailDTO.getCodeDesc() //key_name
    			,"20190331"
				,"N"
				,"Y"
				,"N"
				,"20190331131020"
				,"DREAM"
				,""
    			,mgrCdSysCodeDetailDTO.getCodeDesc() //key_name
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    }
    
    
    public int updateLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TALANG SET 		");
    	query.append("    key_name 		= ?		");
    	query.append("WHERE key_type 	= ?		");
    	query.append("AND key_no 		= ( SELECT 							");
    	query.append("                        		a.list_type + '.' + ? 	");
    	query.append("                        FROM TACDSYSM a 				");
    	query.append("                       WHERE a.cdsysm_id = ? )		");
    	query.append("AND lang 			= ?		");
    	
    	Object[] objects = new Object[] {
    			mgrCdSysCodeDetailDTO.getCodeDesc() //key_name
    			,"CODESET"
    			,mgrCdSysCodeDetailDTO.getCode() //key_no
    			,mgrCdSysCommonDTO.getCdSysMId()
    			,user.getLangId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    }
}