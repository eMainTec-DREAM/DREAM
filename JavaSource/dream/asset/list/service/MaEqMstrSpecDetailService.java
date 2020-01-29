package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * 설비제원(스펙) 상세
 * @author  kim21017
 * @version $Id: MaEqMstrSpecDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrSpecDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqSpecId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrSpecDetailDTO findDetail(MaEqMstrSpecListDTO maEqMstrSpecListDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception;
}
