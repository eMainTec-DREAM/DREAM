package dream.mgr.fiassetcd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.fiassetcd.dto.LovAssetListDTO;
import dream.mgr.fiassetcd.form.LovAssetListForm;

/**
 * 자산팝업 Service
 * @author  kim21017
 * @version $Id: LovAssetListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovAssetListService
{

    /**
     * 자산검색
     * @author  kim21017
     * @version $Id: LovAssetListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovAssetListDTO
     * @param loginUser
     * @return
     */
    List findAssetList(LovAssetListDTO lovAssetListDTO, User loginUser);

	List findAssetAcList(LovAssetListDTO lovAssetListDTO, User user, LovAssetListForm lovAssetListForm);
	
	public String findTotalCount(LovAssetListDTO lovAssetListDTO, User user, LovAssetListForm lovAssetListForm);
}