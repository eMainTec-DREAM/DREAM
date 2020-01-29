package dream.mgr.fiassetcd.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovAssetListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovAssetListDAO
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																");
        query.append("       asset_id assetId,												");
        query.append("       asset_no assetNo,												");
        query.append("       description assetDesc,											");
        query.append("       TO_CHAR(TO_DATE (acq_date,'yyyy-mm-dd'),'yyyy-mm-dd') acqDate,	");
        query.append("       TO_CHAR(buyer_amt,'FM999,999,999,999') buyerAmt,				");
        query.append("       TO_CHAR(dep_amt,'FM999,999,999,999') depAmt,					");
        query.append("       TO_CHAR(res_amt,'FM999,999,999,999') resAmt					");
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
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																");
        query.append("       asset_id asset_id,												");
        query.append("       asset_no asset_no,												");
        query.append("       description description,										");
        query.append("       TO_CHAR(TO_DATE (acq_date,'yyyy-mm-dd'),'yyyy-mm-dd') acqDate,	");
        query.append("       TO_CHAR(buyer_amt,'FM999,999,999,999') buyerAmt,				");
        query.append("       TO_CHAR(dep_amt,'FM999,999,999,999') depAmt,					");
        query.append("       TO_CHAR(res_amt,'FM999,999,999,999') resAmt					");
        query.append("      , is_use        ");
        query.append("      , remark        ");
        query.append("FROM TAASSET															");
        query.append("WHERE 1=1																");
        query.getAndQuery("comp_no", conditionMap);
        query.getLikeQuery("asset_no", lovAssetListDTO.getAssetNo());
        query.getAndQuery("is_use", lovAssetListDTO.getIsUse());
        query.getLikeQuery("description", lovAssetListDTO.getAssetDesc());
        
        query.getOrderByQuery("asset_id", lovAssetListDTO.getOrderBy(), lovAssetListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovAssetListDTO.getIsLoadMaxCount(), lovAssetListDTO.getFirstRow()));
	}

	@Override
	public String findTotalCount(LovAssetListDTO lovAssetListDTO, User user, Map<String, String> conditionMap)
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT										");
        query.append("       COUNT(1)								");
        query.append("FROM TAASSET									");
        query.append("WHERE 1=1										");
        query.getAndQuery("comp_no", conditionMap);
        query.getLikeQuery("asset_no", lovAssetListDTO.getAssetNo());
        query.getAndQuery("is_use", lovAssetListDTO.getIsUse());
        query.getLikeQuery("description", lovAssetListDTO.getAssetDesc());
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}