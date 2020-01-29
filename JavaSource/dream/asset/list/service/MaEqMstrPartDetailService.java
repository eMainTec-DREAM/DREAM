package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 상세
 * @author  kim210117
 * @version $Id: MaEqMstrPartDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPartDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MaEqMstrPartDetailDTO findDetail(MaEqMstrPartListDTO maEqMstrPartListDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
   
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception;
    
}
