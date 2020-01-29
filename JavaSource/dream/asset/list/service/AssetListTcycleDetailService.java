package dream.asset.list.service;

import common.bean.User;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.AssetListTcycleDetailDTO;

/**
 * 교정주기 상세
 * @author  kim210117
 * @version $Id: AssetListTcycleDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface AssetListTcycleDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: AssetListTcycleDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqSpecId
     * @param compNo
     * @return
     * @throws Exception
     */
    public AssetListTcycleDetailDTO findDetail(String equipId, String eqPmCycleId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: AssetListTcycleDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param assetListTcycleDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: AssetListTcycleDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param assetListTcycleDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public String insertDetail(AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
}
