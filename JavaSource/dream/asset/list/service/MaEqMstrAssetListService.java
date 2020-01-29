package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산 목록
 * @author  kim21017
 * @version $Id: MaEqMstrAssetListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrAssetListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrAssetListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @throws Exception
     */
    public List findAssetList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User loginUser );
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaEqMstrAssetListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteAssetList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User user) throws Exception;

}
