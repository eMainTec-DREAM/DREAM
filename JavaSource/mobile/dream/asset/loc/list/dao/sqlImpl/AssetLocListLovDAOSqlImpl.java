package mobile.dream.asset.loc.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class AssetLocListLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetLocListLovDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("		x.eqloc_id AS ID,								");//TreeGrid 출력시 id 필수 
        query.append("		x.eqloc_id               AS EQLOCID,			");
        query.append("		x.eqloc_no               AS EQLOCNO,			");
        query.append("		''						 AS SEQNO,				");
        query.append("		dbo.SFACODE_TO_DESC(x.eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+loginUser.getLangId()+"')	EQLOCLVLTYPE,	");
        query.append("		x.full_desc              AS FULLDESC,			");
        query.append("		x.p_eqloc_id PEQLOCID,							");
        query.append("		MIN(y.lvl) OVER( ORDER BY x.ord_no) as MINLVL,	");
        query.append("		y.lvl AS LEVEL,									");
        query.append("		x.is_lowest_lvl ISLOWESTLVL						");
        query.append("FROM TAEQLOC x										");
        query.append("	  ,(SELECT * FROM dbo.SFAEQLOC_ALL('100','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.EQLOC_ID = y.EQLOC_ID							");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.full_desc", assetLocListLovDTO.getFullDesc());
        query.getSysCdQuery("x.eqloc_lvl_type", assetLocListLovDTO.getEqLocLvlType(), assetLocListLovDTO.getEqLocLvlTypeDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        query.append("ORDER BY x.EQLOC_ID									");
        return getJdbcTemplate().queryForList(query.toString());
    }
}