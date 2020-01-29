package dream.asset.rpt.unit.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.unit.dao.AssetRptEqUnitsDAO;
import dream.asset.rpt.unit.dto.AssetRptEqUnitsDTO;

/**
 *  ¸ñ·Ï dao
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 * @spring.bean id="assetRptEqUnitsDAOTarget"
 * @spring.txbn id="assetRptEqUnitsDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptEqUnitsDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptEqUnitsDAO
{

	@Override
	public List findList(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User loginUser) {
		QueryBuffer query = new QueryBuffer();
		
		query.append(getColums(assetRptEqUnitsDTO, loginUser));
        query.append(getTables(assetRptEqUnitsDTO, loginUser));
        query.append(this.getWhere(assetRptEqUnitsDTO, loginUser)); 
        query.append(getOrderBy(assetRptEqUnitsDTO, loginUser));
        
		return getJdbcTemplate().queryForList(query.toString(assetRptEqUnitsDTO.getIsLoadMaxCount(), assetRptEqUnitsDTO.getFirstRow()));
	
	}

	@Override
	public String findTotalCount(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(assetRptEqUnitsDTO, user));
        query.append(this.getWhere(assetRptEqUnitsDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}

	@Override
	public String getColums(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();

		query.append("SELECT                                             		");
		query.append(" ''                                 	AS SEQNO        	");
		query.append(" , x.equip_id                     	AS EQUIPID         	");
		query.append(" , x.item_no                         	AS ITEMNO        	");
		query.append(" , x.description                     	AS EQUIPDESC    	");
		query.append(" , (SELECT xx.full_desc FROM TAEQLOC xx WHERE x.comp_no = xx.comp_no AND x.eqloc_id = xx.eqloc_id) AS EQLOCDESC        		");
		query.append(" , (SELECT xx.full_desc FROM TAEQCTG xx WHERE x.comp_no = xx.comp_no AND x.eqctg_id = xx.eqctg_id) AS EQCTGDESC        		");
		query.append(" , (SELECT xx.description FROM TAPLANT xx WHERE x.comp_no = xx.comp_no AND x.plant = xx.plant)      AS PLANTDESC        		");
		query.append(" , y.eqasmb_no                     	AS EQASMBNO        		");
		query.append(" , y.full_desc                     	AS EQASMBDESC    		");
		query.append(" , y.maker                         	AS MAKER        		");
		query.append(" , y.model_no                     	AS MODELNO        		");
		query.append(" , y.buy_amt                         	AS BUYAMT        		");
		query.append(" , y.spec                         	AS SPEC            		");
		query.append(" , y.upd_date                         AS UPDDATE        		");
		query.append(" , 												    		");
		query.getDate("y.setup_date", "SETUPDATE");
		query.append(" , y.remark                         	AS REMARK      			");
		    
		return query.toString();
	}

	@Override
	public String getTables(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("FROM TAEQUIPMENT x								");
		query.append("INNER JOIN TAEQASMB y								");
		query.append("ON x.comp_no = y.comp_no							");
		query.append("AND x.equip_id = y.equip_id						");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("x.equip_id", assetRptEqUnitsDTO.getOrderBy(), assetRptEqUnitsDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("WHERE 1 = 1	");

		query.getAndQuery("x.comp_no", user.getCompNo());
		
        if(!"".equals(assetRptEqUnitsDTO.getEquipId())){
        	query.getAndQuery("x.equip_id", assetRptEqUnitsDTO.getEquipId());
        	return query.toString();
        }
        
        query.getStringEqualQuery("x.is_deleted", "N");
        query.getStringEqualQuery("x.is_last_version", "Y");
        query.getLikeQuery("x.item_no", assetRptEqUnitsDTO.getFilterItemNo());
        query.getLikeQuery("x.description", assetRptEqUnitsDTO.getFilterEquipDesc());
        query.getLikeQuery("y.full_desc", assetRptEqUnitsDTO.getFilterEqCtgAsmbDesc());
        query.getEqLocLevelQuery("x.eqloc_id", assetRptEqUnitsDTO.getFilterEqLocId(), assetRptEqUnitsDTO.getFilterEqLocDesc(), user.getCompNo());
        query.getEqCtgLevelQuery("x.eqctg_id", assetRptEqUnitsDTO.getFilterEqCtgId(), assetRptEqUnitsDTO.getFilterEqCtgDesc(), user.getCompNo());
        query.getDeptLevelQuery("x.dept_id", assetRptEqUnitsDTO.getFilterDeptId(), assetRptEqUnitsDTO.getFilterDeptDesc(), user.getCompNo());
        query.getAndDateQuery("y.upd_date", assetRptEqUnitsDTO.getFilterUpDate(), assetRptEqUnitsDTO.getFilterEndUpDate());
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		assetRptEqUnitsDTO.getFilterPlantId(), assetRptEqUnitsDTO.getFilterPlantDesc());
		return query.toString();
	}

}