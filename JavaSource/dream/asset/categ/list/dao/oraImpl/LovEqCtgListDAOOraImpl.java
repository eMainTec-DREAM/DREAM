package dream.asset.categ.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

import dream.asset.categ.list.dao.LovEqCtgListDAO;
import dream.asset.categ.list.dto.LovEqCtgListDTO;

/**
 * 설비종류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqCtgListDAOTarget"
 * @spring.txbn id="lovEqCtgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqCtgListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEqCtgListDAO
{
    /**
     * 설비종류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgList(LovEqCtgListDTO lovEqCtgListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("      eqctg_id as id,										");  //TreeGrid 출력시 id 필수 
        query.append("		eqctg_id						AS eqCtgId,			");
        query.append("		eqctg_no						AS eqCtgNo,			");
        query.append("		''							 	AS seqNo,			");
        query.append("		lvl								AS lvl,				");
        query.append("      mng_cd                			AS mngcd,			");
        query.append("		full_desc						AS fullDesc,		");
        query.append("		p_eqctg_id AS pEqctgId,								");
        query.append("		MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,		");
        query.append("		LEVEL,												");
        query.append("		is_lowest_lvl					AS isLowestLvl		");
        query.append("FROM TAEQCtg												");
        query.append("WHERE 1=1													");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getLikeQuery("lvl", lovEqCtgListDTO.getLvl());
        query.getLikeQuery("full_desc", lovEqCtgListDTO.getFullDesc());
        query.append(" START WITH p_eqctg_id = '0'								");
        query.append(" CONNECT BY PRIOR eqctg_id = p_eqctg_id					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findEqCtgAcList(LovEqCtgListDTO lovEqCtgListDTO, User loginUser, Map<String, String> conditionMap) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("      eqctg_id as id,										");  //TreeGrid 출력시 id 필수 
        query.append("		eqctg_id						eqctg_id,			");
        query.append("		eqctg_no						eqctg_no,			");
        query.append("		''							 	AS seqNo,			");
        query.append("		lvl								AS lvl,				");
        query.append("      mng_cd                			AS mngcd,			");
        query.append("		full_desc						full_desc,			");
        query.append("		description						description,		");
        query.append("		p_eqctg_id AS p_eqctg_id,							");
        query.append("		MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,		");
        query.append("		LEVEL,												");
        query.append("		is_lowest_lvl					AS isLowestLvl		");
        query.append("FROM TAEQCtg												");
        query.append("WHERE 1=1													");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getLikeQuery("lvl", lovEqCtgListDTO.getLvl());
        query.getLikeQuery("full_desc", lovEqCtgListDTO.getFullDesc());
        query.append(" START WITH p_eqctg_id = '0'								");
        query.append(" CONNECT BY PRIOR eqctg_id = p_eqctg_id					");
        
        return getJdbcTemplate().queryForList(query.toString());
	}
}