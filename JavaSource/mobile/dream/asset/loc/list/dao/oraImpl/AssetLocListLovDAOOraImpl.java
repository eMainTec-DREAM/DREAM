package mobile.dream.asset.loc.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import mobile.dream.asset.loc.list.dao.AssetLocListLovDAO;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;

/**
 * 위치분류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetLocListLovDAOTarget"
 * @spring.txbn id="assetLocListLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetLocListLovDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetLocListLovDAO
{
    /**
     * 위치분류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetLocListLovDTO
     * @return
     */
    public List findEqLocList(AssetLocListLovDTO assetLocListLovDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String[] cols = {"full_desc","eqloc_no"};
        
        query.append("SELECT																		");
        query.append("		eqloc_id as id,															");//TreeGrid 출력시 id 필수 
        query.append("		eqloc_id               AS eqLocId,										");
        query.append("		eqloc_no               AS eqLocNo,										");
        query.append("		''						 AS seqNo,										");
        query.append("		SFACODE_TO_DESC(eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+loginUser.getLangId()+"')	eqLocLvlType,	");
        query.append("		full_desc              AS fullDesc,										");
        query.append("		x.p_eqloc_id pEqlocId,													");
//        query.append("		MIN(LEVEL) OVER( ORDER BY x.ord_no) as minLvl,							");
        query.append("		level,																	");
        query.append("		is_lowest_lvl isLowestLvl												");
        query.append("FROM TAEQLOC x																");
        query.append("WHERE 1=1																		");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getLikeQuery("full_desc", assetLocListLovDTO.getFullDesc());
        query.getSysCdQuery("eqloc_lvl_type", assetLocListLovDTO.getEqLocLvlType(), assetLocListLovDTO.getEqLocLvlTypeDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        query.getLikeQuery(cols, assetLocListLovDTO.getSearchText());
        query.append(" START WITH p_eqloc_id = '0'                                      ");
        query.append(" CONNECT BY PRIOR eqloc_id = p_eqloc_id                           ");
        query.append(" ORDER SIBLINGS by eqloc_no 		");
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}