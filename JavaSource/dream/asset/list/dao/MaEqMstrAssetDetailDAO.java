package dream.asset.list.dao;


import common.bean.User;
import dream.asset.list.dto.MaEqMstrAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrAssetDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetListDTO
     * @param user
     * @return
     */
    public MaEqMstrAssetDetailDTO findDetail(MaEqMstrAssetListDTO maEqMstrAssetListDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * detail copy
     * @author syyang
     * @version $Id: MaEqMstrAssetDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param oldEquipId
     * @param newEquipId
     * @param oldKeyId
     * @param newKeyId
     * @param user
     * @return
     */
    public int copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception;
}