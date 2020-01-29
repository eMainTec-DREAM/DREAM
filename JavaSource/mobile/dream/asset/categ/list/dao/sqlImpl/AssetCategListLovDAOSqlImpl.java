package mobile.dream.asset.categ.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class AssetCategListLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetCategListLovDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													");
        query.append("      x.eqctg_id 						AS ID,				");  //TreeGrid 출력시 id 필수 
        query.append("		x.eqctg_id						AS EQCTGID,			");
        query.append("		x.eqctg_no						AS EQCTGNO,			");
        query.append("		''							 	AS SEQNO,			");
        query.append("		x.lvl							AS LVL,				");
        query.append("		x.full_desc						AS FULLDESC,		");
        query.append("		x.p_eqctg_id 					AS PEQCTGID,		");
        query.append("		MIN(y.lvl) OVER( ORDER BY ISNULL(x.ord_no, '99999999')) AS MINLVL,		");
        query.append("		y.lvl 							AS LEVEL,			");
        query.append("		x.is_lowest_lvl					AS ISLOWESTLVL		");
        query.append("FROM TAEQCtg x											");
        query.append("	  ,(SELECT * FROM dbo.SFAEQCTG_ALL('100','0')) y		");
        query.append("WHERE 1=1													");
        query.append("AND 	x.EQCTG_ID = y.EQCTG_ID								");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.lvl", assetCategListLovDTO.getLvl());
        query.getLikeQuery("x.full_desc", assetCategListLovDTO.getFullDesc());
        query.append("ORDER BY x.eqctg_id										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}