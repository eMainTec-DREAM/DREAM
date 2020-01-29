package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;

/**
 * 작업결과 검사항목 상세
 * @author  kim210117
 * @version $Id: MaWoResultPointDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultPointDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param pmPointId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoResultPointDetailDTO findDetail(String wkOrId, String woPointId, String pmPointId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
}
