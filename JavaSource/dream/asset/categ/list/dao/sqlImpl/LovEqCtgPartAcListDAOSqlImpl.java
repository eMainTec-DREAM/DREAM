package dream.asset.categ.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dao.LovEqCtgPartAcListDAO;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;

/**
 * 설비종류별부품 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqCtgPartAcListDAOTarget"
 * @spring.txbn id="lovEqCtgPartAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqCtgPartAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqCtgPartAcListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgPartAcListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgPartAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                ");
    	query.append("          x.eq_ctg_asmb_id                 		  	eqCtgAsmbId		");
    	query.append("        , (SELECT a.description                                       ");
    	query.append("           FROM TAEQCTGASMB a                                         ");
    	query.append("          WHERE a.comp_no = x.comp_no                                 ");
    	query.append("            AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)  eqCtgAsmbDesc   ");
    	query.append("        , x.part_id                                 	partId          ");
    	query.append("        , (SELECT a.part_no                                           ");
    	query.append("           FROM TAPARTS a                                             ");
    	query.append("          WHERE a.comp_no = x.comp_no                                 ");
    	query.append("            AND a.part_id = x.part_id)                partNo          ");
    	query.append("        , (SELECT a.description+', '+ISNULL(a.pt_size,'')             ");
    	query.append("           FROM TAPARTS a                                             ");
    	query.append("          WHERE a.comp_no = x.comp_no                                 ");
    	query.append("            AND a.part_id = x.part_id)                partNameSize    ");
    	query.append("        , CONVERT(NVARCHAR,x.cycle)+x.period_type     cycle           ");
    	query.append("        , x.use_qty                    			  	useQty			");
    	query.append("FROM   TAEQCTGPART x                                                	");
    	query.append("WHERE  1=1    														");
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.is_use", lovEqCtgPartAcListDTO.getIsUse());
    	query.getAndQuery("x.eqctg_id", lovEqCtgPartAcListDTO.getEqCtgId());

    	// 부품코드
    	query.getCodeLikeQuery("x.part_id", "(SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)"
    						  , lovEqCtgPartAcListDTO.getPartId(), lovEqCtgPartAcListDTO.getPartNo());
    	// 부품명/규격
    	query.getCodeLikeQuery("x.part_id", "(SELECT a.description+', '+ISNULL(a.pt_size,'') FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)"
    			, lovEqCtgPartAcListDTO.getPartId(), lovEqCtgPartAcListDTO.getPartNameSize());
    	// 부위명
    	query.getCodeLikeQuery("x.eq_ctg_asmb_id", "(SELECT a.description FROM TAEQCTGASMB a WHERE a.comp_no = x.comp_no AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)"
    			, lovEqCtgPartAcListDTO.getEqCtgAsmbId(), lovEqCtgPartAcListDTO.getEqCtgAsmbDesc());

    	query.append("ORDER BY  4                                       	                ");        
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findEqCtgPartAcAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user, Map<String, String> conditionMap) throws Exception {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
	    query.append("SELECT                                                                ");
    	query.append("          x.eq_ctg_part_id                 		  	eqCtgPartId		");
    	query.append("        , x.eq_ctg_asmb_id                 		  	eqCtgAsmbId		");
    	query.append("        , (SELECT a.description                                       ");
    	query.append("           FROM TAEQCTGASMB a                                         ");
    	query.append("          WHERE a.comp_no = x.comp_no                                 ");
    	query.append("            AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)  eqCtgAsmbDesc   ");
    	query.append("        , x.part_id                                 	partId          ");
    	query.append("        , (SELECT a.part_no                                           ");
    	query.append("           FROM TAPARTS a                                             ");
    	query.append("          WHERE a.comp_no = x.comp_no                                 ");
    	query.append("            AND a.part_id = x.part_id)                partNo          ");
    	query.append("        , (SELECT a.description+', '+ISNULL(a.pt_size,'')             ");
    	query.append("           FROM TAPARTS a                                             ");
    	query.append("          WHERE a.comp_no = x.comp_no                                 ");
    	query.append("            AND a.part_id = x.part_id)                partNameSize    ");
    	query.append("        , CONVERT(NVARCHAR,x.cycle)+x.period_type     cycle           ");
    	query.append("        , x.use_qty                    			  	useQty			");
    	query.append("FROM   TAEQCTGPART x                                                	");
    	query.append("WHERE  1=1    														");
    	query.append(this.getWhere(lovEqCtgPartAcListDTO, user, conditionMap));

        query.getOrderByQuery("x.eq_ctg_part_id", "(SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)", lovEqCtgPartAcListDTO.getOrderBy(), lovEqCtgPartAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovEqCtgPartAcListDTO.getIsLoadMaxCount(), lovEqCtgPartAcListDTO.getFirstRow()));
        
	}
	
	private String getWhere(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		// 회사코드
		query.getAndQuery("x.comp_no", conditionMap);
		// 사용여부
		query.getAndQuery("x.is_use", conditionMap);
		// 설비종류
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
		
		// 부품코드
		query.getLikeQuery("(SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)", lovEqCtgPartAcListDTO.getPartNo());
		// 부품명/규격
		query.getLikeQuery("(SELECT a.description+', '+ISNULL(a.pt_size,'') FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)", lovEqCtgPartAcListDTO.getPartNameSize());
		// 부위명
		query.getLikeQuery("(SELECT a.description FROM TAEQCTGASMB a WHERE a.comp_no = x.comp_no AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)", lovEqCtgPartAcListDTO.getEqCtgAsmbDesc());
		
		return query.toString();
	}

	@Override
	public String findTotalCount(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user,
			Map<String, String> conditionMap) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("		count(1)					");
    	query.append("FROM   TAEQCTGPART x              ");
        query.append("WHERE  1 = 1                      ");
    	query.append(this.getWhere(lovEqCtgPartAcListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);	
	}
}