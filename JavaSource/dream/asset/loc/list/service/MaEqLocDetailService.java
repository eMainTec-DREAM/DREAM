package dream.asset.loc.list.service;

import common.bean.User;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;

/**
 * 설비위치 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaEqLocDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqLocDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqLocDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqLocDetailDTO findDetail(String eqLocId, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqLocDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqLocDetailDTO maEqLocDetailDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqLocDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqLocDetailDTO maEqLocDetailDTO, User user) throws Exception;
    
    public String findEqLocId(String eqLocNo, User user) throws Exception;
}
