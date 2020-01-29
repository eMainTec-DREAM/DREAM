package dream.asset.categ.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dao.LovEqCtgSpecAcListDAO;
import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;

/**
 * 설비종류별제원 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqCtgSpecAcListDAOTarget"
 * @spring.txbn id="lovEqCtgSpecAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqCtgSpecAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqCtgSpecAcListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgSpecAcListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgSpecAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 												");
        query.append("     x.eq_ctg_asmb_id				  eqCtgAsmbId		");
        query.append("   , (SELECT a.description                            ");
        query.append("      FROM TAEQCTGASMB a                              ");
        query.append("      WHERE a.comp_no = x.comp_no                     ");
        query.append("      AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)  	eqCtgAsmbDesc		");
        query.append("   , x.categ  					  category 			");
        query.append("   , x.prompt 					  prompt			");
        query.append("   , x.uom 						  uom				");
        query.append("   , x.eqctg_id 					  eqctgid			");
        query.append("   , x.eqctgspec_id 				  eqCtgSpecId		");
        query.append("FROM TAEQCTGSPEC x									");
        query.append("WHERE  1=1    										");
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.is_use", lovEqCtgSpecAcListDTO.getIsUse());
    	query.getAndQuery("x.eqctg_id", lovEqCtgSpecAcListDTO.getEqCtgId());
    	query.getCodeLikeQuery("x.eq_ctg_asmb_id", "(SELECT a.description FROM TAEQCTGASMB a  WHERE a.comp_no = x.comp_no AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)"
				, lovEqCtgSpecAcListDTO.getEqCtgAsmbId(), lovEqCtgSpecAcListDTO.getEqCtgAsmbDesc());
    	query.getLikeQuery("x.prompt", lovEqCtgSpecAcListDTO.getPrompt());
    	query.getLikeQuery("x.uom", lovEqCtgSpecAcListDTO.getUom());
    	query.getLikeQuery("x.categ", lovEqCtgSpecAcListDTO.getCateg());

        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findEqCtgSpecAcAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user, Map<String, String> conditionMap) throws Exception {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
	    query.append("SELECT 												");
        query.append("     x.eq_ctg_asmb_id				  eqCtgAsmbId		");
        query.append("   , (SELECT a.description                            ");
        query.append("      FROM TAEQCTGASMB a                              ");
        query.append("      WHERE a.comp_no = x.comp_no                     ");
        query.append("      AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)  	eqCtgAsmbDesc		");
        query.append("   , x.categ  					  category 			");
        query.append("   , x.prompt 					  prompt			");
        query.append("   , x.uom 						  uom				");
        query.append("   , x.eqctg_id 					  eqctgid			");
        query.append("   , x.eqctgspec_id 				  eqCtgSpecId		");
        query.append("FROM TAEQCTGSPEC x									");
    	query.append("WHERE  1=1    										");
    	query.append(this.getWhere(lovEqCtgSpecAcListDTO, user, conditionMap));
        query.getOrderByQuery("x.eqctgspec_id", "x.categ", lovEqCtgSpecAcListDTO.getOrderBy(), lovEqCtgSpecAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovEqCtgSpecAcListDTO.getIsLoadMaxCount(), lovEqCtgSpecAcListDTO.getFirstRow()));
        
	}
	
	private String getWhere(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		// 회사코드
		query.getAndQuery("x.comp_no", conditionMap);
		// 사용여부
		query.getAndQuery("x.is_use", conditionMap);
		// 설비종류
		query.getAndQuery("x.eqctg_id", conditionMap);
		
		query.getCodeLikeQuery("x.eq_ctg_asmb_id", "(SELECT a.description FROM TAEQCTGASMB a  WHERE a.comp_no = x.comp_no AND a.eq_ctg_asmb_id = x.eq_ctg_asmb_id)"
				, lovEqCtgSpecAcListDTO.getEqCtgAsmbId(), lovEqCtgSpecAcListDTO.getEqCtgAsmbDesc());
		query.getLikeQuery("x.prompt", lovEqCtgSpecAcListDTO.getPrompt());
		query.getLikeQuery("x.uom", lovEqCtgSpecAcListDTO.getUom());
		query.getLikeQuery("x.categ", lovEqCtgSpecAcListDTO.getCateg());
		
		return query.toString();
	}

	@Override
	public String findTotalCount(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user,
			Map<String, String> conditionMap) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("		count(1)					");
        query.append("FROM TAEQCTGSPEC x				");
        query.append("WHERE  1 = 1                      ");
    	query.append(this.getWhere(lovEqCtgSpecAcListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);	
	}
}