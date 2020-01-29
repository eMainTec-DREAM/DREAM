package dream.asset.loc.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.loc.list.dao.LovEqLocListDAO;
import dream.asset.loc.list.dto.LovEqLocListDTO;

/**
 * 위치분류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqLocListDAOTarget"
 * @spring.txbn id="lovEqLocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqLocListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEqLocListDAO
{
    /**
     * 위치분류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListDTO
     * @return
     */
    public List findEqLocList(LovEqLocListDTO lovEqLocListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																		");
        query.append("		eqloc_id as id,															");//TreeGrid 출력시 id 필수 
        query.append("		eqloc_id               AS eqLocId,										");
        query.append("		eqloc_no               AS eqLocNo,										");
        query.append("		''						 AS seqNo,										");
        query.append("		SFACODE_TO_DESC(eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+loginUser.getLangId()+"')	eqLocLvlType,	");
        query.append("		full_desc              AS fullDesc,										");
        query.append("		x.p_eqloc_id pEqlocId,													");
        query.append("		MIN(LEVEL) OVER( ORDER BY x.ord_no) as minLvl,							");
        query.append("		level,																	");
        query.append("		is_lowest_lvl isLowestLvl												");
        query.append("FROM TAEQLOC x																");
        query.append("WHERE 1=1																		");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getLikeQuery("full_desc", lovEqLocListDTO.getFullDesc());
        query.getSysCdQuery("eqloc_lvl_type", lovEqLocListDTO.getEqLocLvlType(), lovEqLocListDTO.getEqLocLvlTypeDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        query.append(" START WITH p_eqloc_id = '0'                                      ");
        query.append(" CONNECT BY PRIOR eqloc_id = p_eqloc_id                           ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * 위치분류 AC
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListDTO
     * @return
     */
    public List findEqLocAcList(LovEqLocListDTO lovEqLocListDTO, User loginUser, Map<String, String> columnMap,
			Map<String, String> conditionMap) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT b.*,  LEVEL LVL, MIN(LEVEL) OVER() MINLVL FROM (                       ");
        query.append("SELECT																	    ");
        query.append("		x.eqloc_id as id,														");//TreeGrid 출력시 id 필수 
        query.append("		x.eqloc_id               AS eqloc_id,									");
        query.append("		x.eqloc_no               AS eqLocNo,									");
        query.append("		''						 AS seqNo,										");
        query.append("		SFACODE_TO_DESC(eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+loginUser.getLangId()+"')	eqLocLvlType,	");
        query.append("      x.description              AS description,                              ");
        query.append("		x.full_desc              AS full_desc,									");
        query.append("		x.p_eqloc_id pEqlocId,													");
        query.append("      x.eqloc_lvl_type,                                                       ");
//        query.append("		MIN(LEVEL) OVER( ORDER BY x.ord_no) as minLvl,							");
        query.append("		level,																	");
        query.append("      is_use,                                                                 ");
        query.append("      comp_no,                                                                ");
        query.append("		x.is_lowest_lvl isLowestLvl,											");
        query.append("		x.plant                 AS plant	       								");
        query.append("FROM TAEQLOC x																");
        query.append("WHERE 1=1																		");
        query.append(" START WITH p_eqloc_id = '0'                                                  ");
        query.append(" CONNECT BY PRIOR eqloc_id = p_eqloc_id                                       ");
        query.append(" ORDER SIBLINGS BY  ord_no                                                    ");
        query.append(" ) b                                                                          ");
        query.append("WHERE 1 = 1                                                                   ");
        query.getSysCdQuery("eqloc_lvl_type", lovEqLocListDTO.getEqLocLvlType(), lovEqLocListDTO.getEqLocLvlTypeDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        //공장코드
        query.getCodeLikeQuery("plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = b.comp_no AND a.plant = b.plant )", lovEqLocListDTO.getPlant(), lovEqLocListDTO.getPlantDesc());
        query.getLikeQuery("eqLocNo", lovEqLocListDTO.getEqLocNo());
        query.getLikeQuery("full_desc", lovEqLocListDTO.getFullDesc());
        query.getAndQuery("is_use", "Y");
        query.getAndQuery("isLowestLvl", conditionMap);
        query.getAndQuery("eqloc_lvl_type", conditionMap);
        query.getAndQuery("comp_no", conditionMap);
        query.append("START WITH pEqlocId = 0                                                       ");
        query.append("CONNECT BY PRIOR eqloc_id = pEqlocId                                          ");

        
        return getJdbcTemplate().queryForList(query.toString());
    }
}