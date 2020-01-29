package dream.mgr.lang.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.mgr.lang.dao.MaResDetailDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;

/**
 * 언어 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maResDetailDAOTarget"
 * @spring.txbn id="maResDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaResDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaResDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param keyTypeNo
     * @param langId
     * @return
     */
    public MaResDetailDTO findDetail(User user, MaResCommonDTO maResCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        String langId = maResCommonDTO.getLangId();
        
        query.append("SELECT															");
        query.append("	x.key_type										keyTypeId		");
        query.append("	,SFACODE_TO_DESC(x.key_type, 'KEY_TYPE','SYS','','"+user.getLangId()+"') keyTypeDesc ");
        query.append("	,x.key_no										keyNo			");
        query.append("  ,x.lang_id                                      langId			");
        query.append("  ,x.lang                                      	lang			");
        query.append("  ,SFACODE_TO_DESC(x.lang,'LANG','SYS','','"+user.getLangId()+"')    		langDesc	 ");
        query.append("	,x.key_name										keyName			");
        query.append("	 ,(SELECT key_name 												");
        query.append("	   FROM TALANG													");
        query.append("	  WHERE key_type = x.key_type									");
        query.append("	    AND key_no	 = x.key_no										");
        query.getStringEqualQuery("lang", "en");
        query.append("	 ) keyNameEn													");
        query.append("	 ,(SELECT key_name 												");
        query.append("	   FROM TALANG													");
        query.append("	  WHERE key_type = x.key_type									");
        query.append("	    AND key_no	 = x.key_no										");
        query.getStringEqualQuery("lang", "ko");
        query.append("	 ) keyNameKo													");
        query.append("    ,x.use_web								isUseWeb			");
        query.append("    ,x.use_android							isUseAndroid		");
        query.append("    ,x.is_comm_js_use 						isUseJs  			");
        query.append("FROM TALANG x														");
        query.append("WHERE 1=1															");
        query.getAndQuery("x.lang_id", langId);
        
        MaResDetailDTO maResDetailDTO = 
        		(MaResDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaResDetailDTO()));
        
        return maResDetailDTO;
    }

    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResDetailDTO
     * @return
     */
    public int updateDetail(MaResDetailDTO maResDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	Object[] objects = null;
    	int rtnValue  = 0;
    	
//    	query.append("MERGE INTO TALANG a													");
//    	query.append("USING ( SELECT ? keyType,												");
//    	query.append("				 ? keyNo,												");
//    	query.append("				 '"+langId+"' lang,										");
//    	query.append("				 ? keyName												");
//    	query.append("			 FROM DUAL) b												");
//    	query.append("ON (  a.key_type = b.keyType											");
//    	query.append("	AND a.key_no = b.keyNo												");
//    	query.append("	AND a.lang = b.lang		)											");
//    	query.append("WHEN MATCHED THEN														");
//    	query.append("	UPDATE SET a.key_name = b.keyName									");
//    	query.append("WHEN NOT MATCHED THEN													");
//    	query.append("INSERT (a.lang_id, a.lang, a.key_type, a.key_no, a.key_name)			");
//    	query.append("VALUES (SQALANG_ID.NEXTVAL, b.lang, b.keyType, b.keyNo, b.keyName)	");
    	
    	query.append("UPDATE TALANG SET           			");
    	query.append(" key_name 			= ?   	 		");
    	query.append(" , upd_date           = ?        		");
    	query.append(" , upd_by             = ?        		");
    	query.append(" , basic_key_name		= ?				");
    	query.append("WHERE lang_id       	= ?     		");
        
    	objects = new Object[] {
    			maResDetailDTO.getKeyName()
    			,CommonUtil.getRowDateToNum(maResDetailDTO.getUpdDate())
    			,user.getEmpId()
    			,maResDetailDTO.getKeyName()
    			,maResDetailDTO.getLangId()
    			
    	};
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

	@Override
	public int insertDetail(MaResDetailDTO maResDetailDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		Object[] objects = null;
		
		query.append("INSERT INTO TALANG 					");
		query.append("(										");
		query.append(" lang_id				, lang			");
		query.append(" , key_type 			, key_no		");
		query.append(" , key_name 			, upd_date		");
		query.append(" , is_comm_js_use		, use_web		");
		query.append(" , use_android		, cre_date		");
		query.append(" , cre_by				, upd_by		");
		query.append(" , basic_key_name						");
		query.append(")										");
		query.append("VALUES								");
		query.append("(										");
	    query.append("   ?        	     , ?      		    ");
        query.append("   , ?         	     , ?            ");
        query.append("   , ?         	     , ?            ");
        query.append("   , ?         	     , ?            ");
        query.append("   , ?              	 , ?            ");
        query.append("   , ?         	     , ?            ");
        query.append("   , ?              	 		        ");
		query.append(")									    ");

        objects = new Object[] {
        		maResDetailDTO.getLangId()
        		,user.getLangId()
        		,"USER"
        		,maResDetailDTO.getLangId() //key_no == lang_id
        		,maResDetailDTO.getKeyName()
        		,CommonUtil.getRowDateToNum(maResDetailDTO.getUpdDate())
        		,maResDetailDTO.getIsUseJs()
        		,maResDetailDTO.getIsUseWeb()
        		,maResDetailDTO.getIsUseAndroid()
        		,CommonUtil.getRowDateToNum(maResDetailDTO.getCreDate())
        		,user.getEmpId()
        		,user.getEmpId()
        		,maResDetailDTO.getKeyName()
      };
		return getJdbcTemplate().update(query.toString(), objects);

	}

}