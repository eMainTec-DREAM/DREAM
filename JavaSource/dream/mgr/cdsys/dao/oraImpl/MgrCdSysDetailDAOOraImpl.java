package dream.mgr.cdsys.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.cdsys.dao.MgrCdSysDetailDAO;
import dream.mgr.cdsys.dto.MgrCdSysDetailDTO;

/**
 * 시스템코드 - 상세 dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrCdSysDetailDAOTarget"
 * @spring.txbn id="mgrCdSysDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCdSysDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrCdSysDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param cdSysMId
     * @return
     */
    public MgrCdSysDetailDTO findDetail(String cdSysMId, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("       x.list_type		listType,		");
        query.append("       (select aa.key_name                ");
        query.append("         from talang aa                   ");
        query.append("         where  lang = '"+lang+"'         ");
        query.append("             and x.key_type = aa.key_type ");
        query.append("             and x.key_no = aa.key_no) as listTypeDesc,   ");
        //query.append("       x.description		listTypeDesc,	");
        query.append("       x.remark			remark,			");
        query.append("       x.key_no			keyNo,		        ");
        query.append("       x.key_type			keyType,	        ");
        query.append("       x.cdsysm_id		cdSysMId		");
        query.append("FROM   TACDSYSM x							");
        query.append("WHERE  x.cdsysm_id = '"+cdSysMId+"'		");
    
        MgrCdSysDetailDTO mgrCdSysDetailDTO = 
        		(MgrCdSysDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrCdSysDetailDTO()));
        
        return mgrCdSysDetailDTO;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysDetailDTO
     * @return
     */
    public int updateDetail(MgrCdSysDetailDTO mgrCdSysDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TACDSYSM SET		");
    	query.append("	description		= ?,	");
    	query.append("	remark			= ?,	");
    	query.append("	key_no			= ?,	");
    	query.append("	key_type		= ?		");
    	query.append("WHERE cdsysm_id 	= ?		");
    	
    	Object[] objects = new Object[] {
    			mgrCdSysDetailDTO.getListTypeDesc(),
    			mgrCdSysDetailDTO.getRemark(),
    			"".equals(mgrCdSysDetailDTO.getKeyNo())?mgrCdSysDetailDTO.getListType():mgrCdSysDetailDTO.getKeyNo(),
                "CODESET",
    			mgrCdSysDetailDTO.getCdSysMId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public String checkLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT 					");
    	query.append("    COUNT(1) 				");
    	query.append("FROM TALANG a 			");
    	query.append("WHERE 1=1					");
    	query.append("AND a.key_type 	= ? 	");
    	query.append("AND a.key_no 		= ?     ");
    	query.append("AND a.lang 		= ?		");

    	
    	Object[] objects = new Object[] {
    			"CODESET"
    			,"".equals(mgrCdSysDetailDTO.getKeyNo())?mgrCdSysDetailDTO.getListType():mgrCdSysDetailDTO.getKeyNo()
    			,mgrCdSysDetailDTO.getUserLang()
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    
    public void insertLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO) 
	{
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TALANG											");
    	query.append("(   lang_id     			, lang  			, key_type		");
    	query.append("  , key_no      			, key_name      	, upd_date		");
    	query.append("  , is_comm_js_use  		, use_web   		, use_android	");
    	query.append("  , cre_date, site_type	, version_info						");
    	query.append("  , basic_key_name											");
    	query.append(") VALUES ( 													");
    	query.append("    sqalang_id.NEXTVAL	, ?					,?				");
    	query.append("    ,?					, ?					,?				");
    	query.append("    ,?					, ?					,?				");
    	query.append("    ,?					, ?					,?				");
    	query.append("    ,?														");
    	query.append(")																");

    	
    	Object[] objects = new Object[] {
				mgrCdSysDetailDTO.getUserLang()	//lang
				,"CODESET"	//key_type
				,"".equals(mgrCdSysDetailDTO.getKeyNo())?mgrCdSysDetailDTO.getListType():mgrCdSysDetailDTO.getKeyNo() //key_no
				,mgrCdSysDetailDTO.getListTypeDesc()	//key_name
				,"20190331"
				,"N"
				,"Y"
				,"N"
				,"20190331131020"
				,"DREAM"
				,""
				,mgrCdSysDetailDTO.getListTypeDesc()	//key_name
    	};
        	
    	getJdbcTemplate().update(query.toString(), objects);

	}
    
    public void updateLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO) 
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TALANG SET 		");
    	query.append("    key_name 		= ?		");
    	query.append("WHERE key_type 	= ?		");
    	query.append("AND key_no 		= ?		");
    	query.append("AND lang 			= ?		");
    	
    	Object[] objects = new Object[] {
			mgrCdSysDetailDTO.getListTypeDesc()
			,"CODESET"
			,"".equals(mgrCdSysDetailDTO.getKeyNo())?mgrCdSysDetailDTO.getListType():mgrCdSysDetailDTO.getKeyNo()
			,mgrCdSysDetailDTO.getUserLang()
		};
    		
    		getJdbcTemplate().update(query.toString(), objects);
    }
    
}