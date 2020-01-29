package dream.consult.comp.cdsys.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.cdsys.dao.MaCdSysCodeDetailDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드-분류 dao
 * @author  kim21017
 * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maCdSysCodeDetailDAOTarget"
 * @spring.txbn id="maCdSysCodeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdSysCodeDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaCdSysCodeDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param cdSysMId
     * @param cdSysDId
     * @return
     */
    public MaCdSysCodeDetailDTO findDetail(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO, User user)
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
        query.append("       ,x.param1			PARAM1						");
        query.append("       ,x.param2			PARAM2						");
        query.append("       ,x.param3			PARAM3						");
        query.append("       ,x.key_no			KEYNO		                ");
        query.append("       ,x.key_type		KEYTYPE	                    ");
        query.append("       ,x.list_type		LISTTYPE					");
        query.append("       ,x.cdsysd_id		CDSYSDID					");
        query.append("FROM   TACDSYSD x										");
        query.append("WHERE  x.cdsysm_id 		= ?			                ");
        query.append("  AND  x.cdsysd_id 		= ?			                ");
        
        Object[] objects = new Object[]{
        		user.getLangId()
        		,maCdSysCommonDTO.getCdSysMId()
        		,maCdSysCodeListDTO.getCdSysDId()
        };
    
        MaCdSysCodeDetailDTO maCdSysCodeDetailDTO = 
        		(MaCdSysCodeDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaCdSysCodeDetailDTO()));
        
        return maCdSysCodeDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     */
    public int updateDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TACDSYSD SET	");
    	query.append("	description		= ? ");
    	query.append("	,remark			= ? ");
    	query.append("	,ord_no			= ? ");
    	query.append("	,is_use			= ? ");
    	query.append("	,param1			= ? ");
    	query.append("	,param2			= ? ");
    	query.append("	,param3			= ? ");
    	query.append("	,key_no			= (SELECT LIST_TYPE + '.' + ? FROM tacdsysm WHERE cdsysm_id = ?) ");
    	query.append("	,key_type		= 'CODESET' ");
    	query.append("	,CDSYSD_NO		= ? ");
    	query.append("WHERE cdsysm_id 	= ?	");
    	query.append("  AND cdsysd_id	= ? ");
    	
    	Object[] objects = new Object[] {
    			maCdSysCodeDetailDTO.getCodeDesc()
    			,maCdSysCodeDetailDTO.getRemark()
    			,maCdSysCodeDetailDTO.getOrdNo()
    			,maCdSysCodeDetailDTO.getIsUse()
    			,maCdSysCodeDetailDTO.getParam1()
    			,maCdSysCodeDetailDTO.getParam2()
    			,maCdSysCodeDetailDTO.getParam3()
    			,maCdSysCodeDetailDTO.getCode()
    			,maCdSysCommonDTO.getCdSysMId()
    			,maCdSysCodeDetailDTO.getCode()
    			,maCdSysCommonDTO.getCdSysMId()
    			,maCdSysCodeDetailDTO.getCdSysDId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String checkLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) 
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
    			,maCdSysCodeDetailDTO.getCode() //key_no
    			,maCdSysCommonDTO.getCdSysMId()
    			,user.getLangId()
    	};
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));

	}
    
    
    public int insertLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) 
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
    			,maCdSysCodeDetailDTO.getCode() //key_no
    			,maCdSysCommonDTO.getCdSysMId()
    			,maCdSysCodeDetailDTO.getCodeDesc() //key_name
    			,"20190331"
				,"N"
				,"Y"
				,"N"
				,"20190331131020"
				,"DREAM"
				,""
    			,maCdSysCodeDetailDTO.getCodeDesc() //key_name
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    }
    
    
    public int updateLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) 
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
    			maCdSysCodeDetailDTO.getCodeDesc() //key_name
    			,"CODESET"
    			,maCdSysCodeDetailDTO.getCode() //key_no
    			,maCdSysCommonDTO.getCdSysMId()
    			,user.getLangId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    }
    
    
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     */
    public int insertDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TACDSYSD (					");
    	query.append("	cdsysm_id,		cdsysd_id,		CDSYSD_NO,	");
    	query.append("	description,	ord_no,			is_use,	");
    	query.append("	remark,			param1,			param2,	");
    	query.append("	key_no,			key_type        ,param3 ");
    	query.append("	)	VALUES				(				");
    	query.append("	?,				?,				?,		");
    	query.append("	?,				?,				?,		");
    	query.append("	?,				?,				?,		");
    	query.append("	(SELECT LIST_TYPE + '.' + ? FROM tacdsysm WHERE cdsysm_id = ?),				'CODESET'                ,?	");
    	query.append("	)										");
    	
    	Object[] objects = new Object[] {
    			maCdSysCommonDTO.getCdSysMId()
    			,maCdSysCodeDetailDTO.getCdSysDId()
    			,maCdSysCodeDetailDTO.getCode()
    			,maCdSysCodeDetailDTO.getCodeDesc()
    			,maCdSysCodeDetailDTO.getOrdNo()
    			,maCdSysCodeDetailDTO.getIsUse()
    			,maCdSysCodeDetailDTO.getRemark()
    			,maCdSysCodeDetailDTO.getParam1()
    			,maCdSysCodeDetailDTO.getParam2()
    			,maCdSysCodeDetailDTO.getCode()
    			,maCdSysCommonDTO.getCdSysMId()
    			,maCdSysCodeDetailDTO.getParam3()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	query = new QuerySqlBuffer();

    	
    	query.append(" update  a set a.list_type = b.list_type                                  ");
    	query.append(" 		from tacdsysd a inner join tacdsysm b on a.cdsysm_id = b.cdsysm_id  ");
    	query.append(" 		where 1=1                                                           ");
    	query.append("         and  a.cdsysm_id  = ?                           	                ");
    	
    	objects = new Object[] {
    			maCdSysCommonDTO.getCdSysMId()
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
    	
    	return rtnValue;
    }
}