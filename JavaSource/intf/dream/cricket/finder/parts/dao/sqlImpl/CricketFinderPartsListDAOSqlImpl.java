package intf.dream.cricket.finder.parts.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.cricket.finder.parts.dao.CricketFinderPartsListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderPartsListDAOTarget"
 * @spring.txbn id="cricketFinderPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderPartsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CricketFinderPartsListDAO
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
        query.append("         ,x.full_desc  description                   ");
        query.append("FROM TAPARTS x                                       ");
        query.append("WHERE 1=1                                            ");
        query.getAndQuery("x.comp_no",compNo);
    	query.getStringEqualQuery("x.is_deleted", "N");
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