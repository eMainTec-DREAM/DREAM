package intf.dream.cricket.finder.equip.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.cricket.finder.equip.dao.CricketFinderEquipListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderEquipListDAOTarget"
 * @spring.txbn id="cricketFinderEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderEquipListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CricketFinderEquipListDAO
{
	public List findEquipList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String equipDesc = String.valueOf(map.get("equipDesc"));
		String eqctgType = String.valueOf(map.get("eqctgType"));
		String itemNo = String.valueOf(map.get("itemNo"));
		String deptId = String.valueOf(map.get("deptId"));
		String eqLocId = String.valueOf(map.get("eqLocId"));   	
		String plant = String.valueOf(map.get("plant"));   	
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
		
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT									");
        query.append("         x.equip_id		AS equipId		");
        query.append("         ,x.item_no		AS itemNo		");
        query.append("         ,x.description	AS equipDesc	");
        query.append("         ,x.eqloc_id		AS eqLocId		");
        query.append("      ,(SELECT a.full_desc                           ");
        query.append("          FROM TAEQLOC a                             ");
        query.append("         WHERE a.comp_no = x.comp_no                 ");
        query.append("           AND a.eqloc_id = x.eqloc_id) eqLocDesc    ");
        query.append("FROM TAEQUIPMENT x						");
        query.append("WHERE 1=1									");
        query.append("AND x.is_last_version='Y'					");
        query.append("AND x.is_deleted='N'						");
        query.getAndQuery("x.comp_no",compNo);
        query.append("AND x.plant='"+plant+"'					");
        query.getLikeQuery("x.item_no+x.description", equipDesc);
        query.getAndQuery("x.eqctg_type",eqctgType);
        query.getAndQuery("x.item_no", itemNo);
        
        query.getEqLocLevelQuery("x.eqloc_id", eqLocId, "", compNo);
        query.getDeptLevelQuery("x.dept_id", deptId, "", compNo);
    	query.getDeptLevelQuery("x.usage_dept", usageDeptId, "", compNo);
        
        query.append("ORDER BY x.item_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 

}