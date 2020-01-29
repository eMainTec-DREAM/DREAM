package dream.consult.comp.cdsys.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.comp.cdsys.dao.MaCdSysDetailDAO;
import dream.consult.comp.cdsys.dto.MaCdSysDetailDTO;

/**
 * 시스템코드 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maCdSysDetailDAOTarget"
 * @spring.txbn id="maCdSysDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdSysDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaCdSysDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param cdSysMId
     * @return
     */
    public MaCdSysDetailDTO findDetail(String cdSysMId, String lang)
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
        query.append("       x.is_use			isUse,			");
        query.append("       x.is_system		isSystem,		");
        query.append("       x.list_categ		listCateg,		");
        query.append("       x.key_no			keyNo,		        ");
        query.append("       x.key_type			keyType,	        ");
        query.append("       x.cdsysm_id		cdSysMId		");
        query.append("FROM   TACDSYSM x							");
        query.append("WHERE  x.cdsysm_id = '"+cdSysMId+"'		");
    
        MaCdSysDetailDTO maCdSysDetailDTO = 
        		(MaCdSysDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaCdSysDetailDTO()));
        
        return maCdSysDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     */
    public int insertDetail(MaCdSysDetailDTO maCdSysDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TACDSYSM								");
    	query.append("	(cdsysm_id,		list_type,		description,	");
    	query.append("	 remark,		is_use,			is_system,		");
    	query.append("	 key_no,		key_type,						");
    	query.append("	 list_categ         							");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,								");
    	query.append("	 ?               								");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maCdSysDetailDTO.getCdSysMId(),
    			maCdSysDetailDTO.getListType(),
    			maCdSysDetailDTO.getListTypeDesc(),
    			maCdSysDetailDTO.getRemark(),
    			maCdSysDetailDTO.getIsUse(),
    			maCdSysDetailDTO.getIsSystem(),
    			"".equals(maCdSysDetailDTO.getKeyNo())?maCdSysDetailDTO.getListType():maCdSysDetailDTO.getKeyNo(), //key_no
				"CODESET",
    			maCdSysDetailDTO.getListCateg()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     */
    public int updateDetail(MaCdSysDetailDTO maCdSysDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TACDSYSM SET		");
    	query.append("	list_type		= ?,	");
    	query.append("	description		= ?,	");
    	query.append("	remark			= ?,	");
    	query.append("	key_no			= ?,	");
    	query.append("	key_type		= ?,	");
    	query.append("	list_categ		= ?,	");
    	query.append("	is_use			= ?		");
    	query.append("	,is_system		= ?		");
    	query.append("WHERE cdsysm_id 	= ?		");
    	
    	Object[] objects = new Object[] {
    			maCdSysDetailDTO.getListType(),
    			maCdSysDetailDTO.getListTypeDesc(),
    			maCdSysDetailDTO.getRemark(),
    			"".equals(maCdSysDetailDTO.getKeyNo())?maCdSysDetailDTO.getListType():maCdSysDetailDTO.getKeyNo(),
                "CODESET",
    			maCdSysDetailDTO.getListCateg(),
    			maCdSysDetailDTO.getIsUse(),
    			maCdSysDetailDTO.getIsSystem(),
    			maCdSysDetailDTO.getCdSysMId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public String checkLangData(MaCdSysDetailDTO maCdSysDetailDTO)
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
    			,"".equals(maCdSysDetailDTO.getKeyNo())?maCdSysDetailDTO.getListType():maCdSysDetailDTO.getKeyNo()
    			,maCdSysDetailDTO.getUserLang()
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    
    public void insertLangData(MaCdSysDetailDTO maCdSysDetailDTO) 
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
				maCdSysDetailDTO.getUserLang()	//lang
				,"CODESET"	//key_type
				,"".equals(maCdSysDetailDTO.getKeyNo())?maCdSysDetailDTO.getListType():maCdSysDetailDTO.getKeyNo() //key_no
				,maCdSysDetailDTO.getListTypeDesc()	//key_name
				,"20190331"
				,"N"
				,"Y"
				,"N"
				,"20190331131020"
				,"DREAM"
				,""
				,maCdSysDetailDTO.getListTypeDesc()	//key_name
    	};
        	
    	getJdbcTemplate().update(query.toString(), objects);

	}
    
    public void updateLangData(MaCdSysDetailDTO maCdSysDetailDTO) 
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TALANG SET 		");
    	query.append("    key_name 		= ?		");
    	query.append("WHERE key_type 	= ?		");
    	query.append("AND key_no 		= ?		");
    	query.append("AND lang 			= ?		");
    	
    	Object[] objects = new Object[] {
			maCdSysDetailDTO.getListTypeDesc()
			,"CODESET"
			,"".equals(maCdSysDetailDTO.getKeyNo())?maCdSysDetailDTO.getListType():maCdSysDetailDTO.getKeyNo()
			,maCdSysDetailDTO.getUserLang()
		};
    		
    		getJdbcTemplate().update(query.toString(), objects);
    }
    
}