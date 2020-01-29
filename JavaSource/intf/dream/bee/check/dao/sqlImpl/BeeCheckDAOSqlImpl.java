package intf.dream.bee.check.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.check.dao.BeeCheckDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeCheckDAOTarget"
 * @spring.txbn id="beeCheckDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeCheckDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeCheckDAO
{
    public List deviceCheck(Map map)
    { 
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	query.append("SELECT            				");
        query.append("      service_type serviceType	");
        query.append("FROM   TAACCESSMNL				");
        query.append("WHERE  is_use= 'Y'				");
        query.append("AND service_type IN ('ANDROID','ANT','BEE','CRICKET')		");
        query.append("AND terminal_no = '"+String.valueOf(map.get("terminalNo"))+"' ");
        query.append("AND comp_no = '"+String.valueOf(map.get("compNo"))+"' ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    public List skinCheck(Map map)
    { 
    	QuerySqlBuffer query = new QuerySqlBuffer(); 
    	
    	query.append("SELECT            				");
    	query.append("      ct_path ctPath				");
    	query.append("FROM   TACOMP						");
    	query.append("WHERE  is_use= 'Y'				");
    	query.append("AND init_ct_path_yn = 'Y' 		");
    	query.append("AND comp_no = '"+String.valueOf(map.get("compNo"))+"' ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    public List beeVersionCheck(Map map)
    { 
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));

    	query.append("SELECT            										");
    	query.append("(SELECT            										");
    	query.append("      value$ 												");
    	query.append("		FROM   TACONFIG										");
    	query.append("		WHERE name ='BEE_VERSION_CODE'						");
    	query.append("		AND comp_no = '"+compNo+"' ) VERSIONCODE			");
    	query.append(",(SELECT            										");
    	query.append("      value$ 												");
    	query.append("		FROM   TACONFIG										");
    	query.append("		WHERE name ='BEE_APK_URL'							");
    	query.append("		AND comp_no = '"+compNo+"' ) 						");
    	query.append("+(SELECT            										");
    	query.append("      value$ 												");
    	query.append("		FROM   TACONFIG										");
    	query.append("		WHERE name ='BEE_VERSION_CODE'						");
    	query.append("		AND comp_no = '"+compNo+"' )+'.apk' AS APKURL 		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	@Override
	public List logoCheck(Map map) {
		
		String compNo = String.valueOf(map.get("compNo"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT top 1  y.imgdata_id AS imgDataId				");
        query.append("		,x.description as description					");
        query.append("		,y.file_name as fileName						");
        query.append("		,y.file_ext as fileExt							");
        query.append("		,y.nf_file_path as filePath						");
        query.append("		,'dream/android/image/'+convert(varchar,y.imgdata_id) as url		");
        query.append("FROM TAIMAGE x INNER JOIN TAIMGDATA y					");
        query.append("ON x.comp_no = y.comp_no								");
        query.append("AND x.image_id = y.image_id							");
        query.append("WHERE 1=1												");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.object_type", "LOGO");
        query.getStringEqualQuery("x.sub_img_type", "LOGINTITLE");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 

}