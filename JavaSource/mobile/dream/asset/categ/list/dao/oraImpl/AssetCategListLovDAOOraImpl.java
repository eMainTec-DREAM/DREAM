package mobile.dream.asset.categ.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import mobile.dream.asset.categ.list.dao.AssetCategListLovDAO;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;


/**
 * 설비종류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetCategListLovDAOTarget"
 * @spring.txbn id="assetCategListLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetCategListLovDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetCategListLovDAO
{
    /**
     * 설비종류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetCategListLovDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgList(AssetCategListLovDTO assetCategListLovDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("      eqctg_id as id,										");  //TreeGrid 출력시 id 필수 
        query.append("		eqctg_id						AS eqCtgId,			");
        query.append("		eqctg_no						AS eqCtgNo,			");
        query.append("		''							 	AS seqNo,			");
        query.append("		lvl								AS lvl,				");
        query.append("		full_desc						AS fullDesc,		");
        query.append("		p_eqctg_id AS pEqctgId,								");
//        query.append("		MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,		");
        query.append("		LEVEL,												");
        query.append("		is_lowest_lvl					AS isLowestLvl		");
        query.append("FROM TAEQCtg												");
        query.append("WHERE 1=1													");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getLikeQuery("lvl", assetCategListLovDTO.getLvl());
        query.getLikeQuery("full_desc", assetCategListLovDTO.getFullDesc());
        String[] cols = {"full_desc","eqctg_no"};
        query.getLikeQuery(cols, assetCategListLovDTO.getSearchText());
        
        query.append(" START WITH p_eqctg_id = '0'								");
        query.append(" CONNECT BY PRIOR eqctg_id = p_eqctg_id					");
        query.append(" ORDER SIBLINGS by eqctg_no 		");

        return getJdbcTemplate().queryForList(query.toString());
    }
}