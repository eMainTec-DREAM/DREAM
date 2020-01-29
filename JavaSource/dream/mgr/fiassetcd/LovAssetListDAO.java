package dream.mgr.fiassetcd;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.fiassetcd.dto.LovAssetListDTO;

/**
 * 자산검색 팝업
 * @author  kim21017
 * @version $Id: LovAssetListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovAssetListDAO
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
    public List findAssetList(LovAssetListDTO lovAssetListDTO, User loginUser);

	public List findAssetAcList(LovAssetListDTO lovAssetListDTO, User user, Map<String, String> conditionMap);
	
	public String findTotalCount(LovAssetListDTO lovAssetListDTO, User user, Map<String, String> conditionMap);
}