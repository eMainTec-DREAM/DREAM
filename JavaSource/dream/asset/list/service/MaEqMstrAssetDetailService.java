package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산 상세
 * @author  kim210117
 * @version $Id: MaEqMstrAssetDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrAssetDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MaEqMstrAssetDetailDTO findDetail(MaEqMstrAssetListDTO maEqMstrAssetListDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    /**
     * detail copy
     * @author syyang
     * @version $Id: MaEqMstrAssetDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param oldEquipId
     * @param newEquipId
     * @param oldKeyId
     * @param newKeyId
     * @param user
     * @return
     * @throws Exception
     */
    public int copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception;
    
}
