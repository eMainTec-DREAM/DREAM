package dream.mgr.fiassetcd.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.fiassetcd.LovAssetListDAO;
import dream.mgr.fiassetcd.dto.LovAssetListDTO;

/**
 * 자산검색 팝업
 * @author  kim21017
 * @version $Id: LovAssetListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovAssetListDAOTarget"
 * @spring.txbn id="lovAssetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovAssetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovAssetListDAO
{
    /**
     * 자산 검색
     * @author  kim21017
     * @version $Id: LovAssetListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovAssetListDTO
     * @param loginUser
     * @return
     */
    public List findAssetList(LovAssetListDTO lovAssetListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																");
        query.append("       asset_id assetId,												");
        query.append("       asset_no assetNo,												");
        query.append("       description assetDesc,											");
        query.append("       acq_date AS acqDate,											");
        query.append("       REPLACE(CONVERT(VARCHAR, CONVERT(MONEY, buyer_amt),1),'.00', '') 	buyerAmt,	");
        query.append("       REPLACE(CONVERT(VARCHAR, CONVERT(MONEY, dep_amt),1),'.00', '') 	depAmt,		");
        query.append("       REPLACE(CONVERT(VARCHAR, CONVERT(MONEY, res_amt),1),'.00', '') 	res_amt		");
        query.append("      , is_use        ");
        query.append("      , remark        ");
        query.append("FROM TAASSET															");
        query.append("WHERE 1=1																");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("asset_no", lovAssetListDTO.getAssetNo());
        query.getAndQuery("is_use", lovAssetListDTO.getIsUse());
        query.getLikeQuery("description", lovAssetListDTO.getAssetDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findAssetAcList(LovAssetListDTO lovAssetListDTO, User loginUser, Map<String, String> conditionMap) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                ");
        query.append("       asset_id ASSET_ID                                             ");
        query.append("      , asset_no ASSET_NO                                             ");
        query.append("      , description DESCRIPTION                                       ");
        query.append("      , CONVERT(CHAR(10),CONVERT(DATETIME,acq_date),120) ACQDATE ");
        query.append("      , REPLACE(CONVERT(VARCHAR, CONVERT(MONEY, buyer_amt),1),'.00', '')   BUYERAMT   ");
        query.append("      , REPLACE(CONVERT(VARCHAR, CONVERT(MONEY, dep_amt),1),'.00', '')     DEPAMT     ");
        query.append("      , REPLACE(CONVERT(VARCHAR, CONVERT(MONEY, res_amt),1),'.00', '')     RESAMT     ");
        query.append("      , is_use IS_USE       ");
        query.append("      , remark REMARK       ");
        query.append("FROM TAASSET                                                          ");
        query.append("WHERE 1=1                                                             ");
        query.getAndQuery("comp_no", conditionMap);
        query.getLikeQuery("asset_no", lovAssetListDTO.getAssetNo());
        query.getAndQuery("is_use", lovAssetListDTO.getIsUse());
        query.getLikeQuery("description", lovAssetListDTO.getAssetDesc());
        
        query.getOrderByQuery("asset_id", "asset_id", lovAssetListDTO.getOrderBy(), lovAssetListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovAssetListDTO.getIsLoadMaxCount(), lovAssetListDTO.getFirstRow()));
	}
	
	@Override
	public String findTotalCount(LovAssetListDTO lovAssetListDTO, User user, Map<String, String> conditionMap)
	{
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT										");
        query.append("       COUNT(1)								");
        query.append("FROM TAASSET									");
        query.append("WHERE 1=1										");
        query.getAndQuery("comp_no", conditionMap);
        query.getLikeQuery("asset_no", lovAssetListDTO.getAssetNo());
        query.getAndQuery("is_use", lovAssetListDTO.getIsUse());
        query.getLikeQuery("description", lovAssetListDTO.getAssetDesc());
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}