package intf.dream.android.online.finder.usrcode.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.finder.usrcode.dao.AnOnFinderUsrcodeListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderUsrcodeListDAOTarget"
 * @spring.txbn id="anOnFinderUsrcodeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderUsrcodeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnFinderUsrcodeListDAO
{
	public List findUsrcodeList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String dirType = String.valueOf(map.get("dirType"));
		String cdusrdNo = String.valueOf(map.get("cdusrdNo"));
		String keyName = String.valueOf(map.get("keyName"));
		String option = String.valueOf(map.get("option"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT														");
        query.append("		x.cdusrd_no AS code										");
        query.append("		,x.description AS description							");
        query.append("FROM TACDUSRD x												");
        query.append("WHERE 1=1														");
        query.getStringEqualQuery("x.is_use", "Y");
        query.append("AND cdusrm_id IN (SELECT a.cdusrm_id							");
        query.append("					FROM TACDUSRM a								");
        query.append("					WHERE 1=1									");
        query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("a.dir_type", dirType);
        query.append("					)											");
        query.getStringEqualQuery("x.cdusrd_no", cdusrdNo);
        if(!"".equals(keyName)&&keyName!=null){
        	query.append("AND (x.description like '%"+keyName+"%'					");
            query.append("     OR x.cdusrd_no like '%"+keyName+"%')            		");
        }
        query.append("ORDER BY ord_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}