package intf.dream.android.online.finder.calibration.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.finder.calibration.dao.AnOnFinderCalibrationListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderCalibrationListDAOTarget"
 * @spring.txbn id="anOnFinderCalibrationListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderCalibrationListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnFinderCalibrationListDAO
{
	public List findCalibrationList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String equipDesc = String.valueOf(map.get("equipDesc"));
		String isBaseEq = String.valueOf(map.get("isBaseEq"));
		String itemNo = String.valueOf(map.get("itemNo"));
		String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
     	String eqCtgType = String.valueOf(map.get("eqCtgType"));
    	
        QueryBuffer query = new QueryBuffer();
        

        query.append("SELECT                                               ");
        query.append("         x.equip_id              AS equipId          ");
        query.append("         ,x.item_no              AS itemNo           ");
        query.append("         ,x.description          AS equipDesc        ");
        query.append("         ,x.eqloc_id             AS eqLocId          ");
        query.append("      ,(SELECT a.full_desc                           ");
        query.append("          FROM TAEQLOC a                             ");
        query.append("         WHERE a.comp_no = x.comp_no                 ");
        query.append("           AND a.eqloc_id = x.eqloc_id) eqLocDesc    ");
        query.append("         ,x.serial_no            AS serialNo         ");
        query.append("FROM TAEQUIPMENT x INNER JOIN TAEQTOOL y             ");
        query.append("ON x.comp_no = y.comp_no                             ");
        query.append("AND x.equip_id = y.equip_id                          ");
        query.append("WHERE 1=1												");
        query.append("AND x.is_last_version='Y'								");
        query.append("AND x.is_deleted='N'									");
        query.append("AND x.plant='"+plant+"'								");
        query.getAndQuery("x.comp_no",compNo);
    	query.getDeptLevelQuery("x.usage_dept", usageDeptId, "", compNo);
        query.getLikeQuery("x.item_no||x.description", equipDesc);
        query.getAndQuery("y.is_standard_eq",isBaseEq);
        query.getAndQuery("x.item_no", itemNo);
        query.getAndQuery("x.eqctg_Type", eqCtgType);
        query.append("ORDER BY x.item_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}