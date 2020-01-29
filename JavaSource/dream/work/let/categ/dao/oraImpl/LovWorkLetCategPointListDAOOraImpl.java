package dream.work.let.categ.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.categ.dao.LovWorkLetCategPointListDAO;
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;

/**
 * 안전작업허가서 표준점검항목 Lov
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWorkLetCategPointListDAOTarget"
 * @spring.txbn id="lovWorkLetCategPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWorkLetCategPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWorkLetCategPointListDAO
{
	@Override
	public List findWorkLetCategPointAcList(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, Map<String, String> conditionMap)
	{
		QueryBuffer query = new QueryBuffer();
	
        query.append("SELECT																					");
        query.append("		step_num         												AS stepNum			");
        query.append("		, comp_no                    	 								AS compNo			");
        query.append("		, check_position 												AS checkPosition	");
        query.append("		, check_point 													AS checkPoint		");
        query.append("		, check_method 													AS checkMethod		");
        query.append("		, fit_basis     												AS fitBasis			");
        query.append("		, a.check_type												 	AS checkType		");
        query.append("		, SFACODE_TO_DESC(a.check_type, 'CHECK_TYPE', 'SYS', '', 'ko') 	AS checkTypeDesc	");
        query.append("		, (a.check_min||' / '||a.check_basis_val||' / '||a.check_max)	AS minBasisMax		");
        query.append("		, check_min                                                     AS checkMin			");
        query.append("		, check_basis_val                                               AS checkBasisVal	");
        query.append("		, check_max                                                     AS checkMax			");
        query.append("		, uom                                                           AS uom				");
        query.append("		, SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', 'ko')          AS isUse			");
        query.append(" 		, remark                                                 		AS remark			");
        query.append("		, woletctgpoint_id                    	 						AS woLetCtgPointId	");
        query.append("		, woletctg_id                    	 							AS woLetCtgId		");
        query.append("FROM TAWOLETCTGPOINT a																	");
        query.append("WHERE  1=1																				");
        query.getAndQuery("a.comp_no", conditionMap);
        query.append(this.getWhere(lovWorkLetCategPointListDTO, user, conditionMap));	
        query.getOrderByQuery("a.step_num", lovWorkLetCategPointListDTO.getOrderBy(), lovWorkLetCategPointListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(lovWorkLetCategPointListDTO.getIsLoadMaxCount(), lovWorkLetCategPointListDTO.getFirstRow()));
	}
	
	private String getWhere(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, Map<String, String> conditionMap)
    {
		QueryBuffer query = new QueryBuffer();
		
        //점검항목
        query.getLikeQuery("a.description", lovWorkLetCategPointListDTO.getCheckPoint());
        
        //사용여부
        query.getSysCdQuery("a.is_use", lovWorkLetCategPointListDTO.getIsUse(), lovWorkLetCategPointListDTO.getIsUse(), "IS_USE", user.getCompNo(),user.getLangId());
        

        // 안전작업유형별 점검항목
		if(!"".equals(conditionMap.get("woletctg_type")) && conditionMap.get("woletctg_type") != null )
		{
	        query.append("AND a.woletctg_id IN (SELECT aa.woletctg_id             	");
	        query.append("                      FROM TAWOLETCTG aa               	");
	        query.append("                      WHERE aa.comp_no = a.comp_no       	");
	        query.getAndQuery("aa.woletctg_type",conditionMap);
	        query.append("                	   )                					");
		}
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                  	");
        query.append("       COUNT(1)                          	");
        query.append("FROM TAWOLETCTGPOINT a					");
        query.append("WHERE  1=1								");
        query.getAndQuery("a.comp_no", conditionMap);
        query.append(this.getWhere(lovWorkLetCategPointListDTO, user, conditionMap));	
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
	
}