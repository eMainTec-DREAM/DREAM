package intf.dream.android.online.finder.parts.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.finder.parts.dao.AnOnFinderPartsListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderPartsListDAOTarget"
 * @spring.txbn id="anOnFinderPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderPartsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnFinderPartsListDAO
{
	public List findPartsList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String partDesc = String.valueOf(map.get("partDesc"));
		String partNo = String.valueOf(map.get("partNo"));
		String wcodeId = String.valueOf(map.get("wcodeId"));
		String plant = String.valueOf(map.get("plant"));
		String ptSize = String.valueOf(map.get("ptSize"));
		String model = String.valueOf(map.get("model"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.part_id id                                ");
        query.append("         ,x.part_no no                               ");
        query.append("         ,x.pt_size  ptSize        				   ");
        query.append("         ,x.MODEL model                    		   ");
        query.append("		   ,ISNULL(x.description,'') +','+ ISNULL(x.pt_size,'')+','+ISNULL(x.model,'')+','+ISNULL(x.maker,'')	AS description		");
        query.append("FROM TAPARTS x                                       ");
        query.append("WHERE 1=1                                            ");
        query.getAndQuery("x.comp_no",compNo);
        query.append("AND x.is_stock_control='Y'		");
        query.append("AND x.part_categ='SPPT'			");
        query.append("  AND x.part_id IN ( 	SELECT part_id  			");
        query.append("  					FROM TAPTSTOCK a INNER JOIN TAWAREHOUSE b		");
        query.append("  					ON a.comp_no = b.comp_no						");
        query.append("  					AND a.wcode_id = b.wcode_id						");
        query.append("  					WHERE 1=1										");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.wcode_id='"+wcodeId+"'											");
    	query.append("AND b.plant='"+plant+"'												");
        query.append("AND b.wh_categ='PART'													");
        query.append("  			)														");
        query.getAndQuery("x.part_no", partNo);
        query.getLikeQuery("x.full_desc", partDesc);
        query.append("ORDER BY part_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 

}