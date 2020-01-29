package dream.work.pm.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.LovWorkPmDInsListDAO;
import dream.work.pm.list.dto.LovWorkPmDInsListDTO;

/**
 * 점검항목 팝업
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWorkPmDInsListDAOTarget"
 * @spring.txbn id="lovWorkPmDInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWorkPmDInsListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWorkPmDInsListDAO
{
    /**
     * 검색
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWorkPmDInsListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgPartAcList(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user, Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT 																													");
		query.append(" ''            																						AS IsDelCheck		");
		query.append(" ,''           	   																			    	AS SeqNo			");
		query.append(" , x.eqctgpmpoint_id 																					AS eqCtgPmPointId	");
		query.append(",(SELECT eqctg_id FROM TAEQCTG WHERE comp_no = x.comp_no AND eqctg_id = x.eqctg_id) 					AS eqCtgId			");
		query.append(" ,x.cycle 																							AS cycle			");
		query.append(",(SELECT MAX(description) FROM TAEQASMB WHERE comp_no = x.comp_no AND eq_ctg_asmb_id = x.eq_ctg_asmb_id) 			AS eqasmbDesc		");
		query.append(" ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"')  AS periodTypeDesc	");
		query.append(" ,x.check_point 																						AS checkPoint		");
		query.append(" ,x.check_method 																						AS checkMethod		");
		query.append(" ,x.fit_basis 															 							AS fitBasis			");
		query.append(" ,SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE','SYS','"+user.getCompNo()+"','"+user.getLangId()+"') 	AS checkTypeDesc	");
		query.append(", x.check_min ||'/'|| x.check_basis_val  ||'/'|| x.check_max 											AS minBasisMax		");
		query.append(" ,x.uom 																								AS uom				");
		query.append(" ,x.ord_no 																							AS ordNo			");
        query.append(" ,SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') 			AS isUse			");
		query.append(" ,x.remark																							AS remark			");
		query.append(" ,x.work_time																							AS PredTime			");
		query.append(" FROM TAEQCTGPMPOINT x																									");
		query.append(" WHERE 1=1																												");
		query.append(this.getWhere(lovWorkPmDInsListDTO, user, conditionMap));
		query.append("ORDER BY x.eqctgpmpoint_id																								");
        
		return getJdbcTemplate().queryForList(query.toString());
    }
    
	private String getWhere(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
		
		query.getAndQuery("x.comp_no", conditionMap);
		query.getAndQuery("x.is_use", conditionMap);
		//점검항목
        query.getLikeQuery("x.check_point", lovWorkPmDInsListDTO.getCheckPoint());
		
        //설비종류
        query.getAndQuery("x.eqctg_id", conditionMap);
		if(!"".equals(conditionMap.get("pm_id")) && conditionMap.get("pm_id") != null )
		{
			query.append("AND x.eqctg_id IN (SELECT aa.eqctg_id 					");
			query.append("                   FROM TAEQUIPMENT aa       				");
			query.append("                   WHERE 1=1              				");
			query.append("                    ANd aa.comp_no=x.comp_no 				");
			query.append("                    AND aa.equip_id IN (SELECT b.equip_id	");
	        query.append("                	 				 	  FROM TAPMEQUIP b  ");
	        query.append("                	 				 	  WHERE 1=1         ");
	        query.getAndQuery("b.comp_no",conditionMap);
	        query.getAndQuery("b.pm_id", conditionMap);
	        query.append("                 						 )                	");
	        query.append("                	)                						");
		}
        
		return query.toString();
	}
	
	@Override
	public String findTotalCount(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user,
			Map<String, String> conditionMap) throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("		count(1)					");
    	query.append("FROM   TAEQCTGPMPOINT x           ");
        query.append("WHERE  1 = 1                      ");
    	query.append(this.getWhere(lovWorkPmDInsListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}