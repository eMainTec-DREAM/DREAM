package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointDetailDTO;

/**
 * 작업결과 작업필수 검사항목 상세
 * @author  kim210117
 * @version $Id: MaWoResultStPointDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultStPointDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultStPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woStPointId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoResultStPointDetailDTO findDetail(String wkOrId, String woStPointId,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultStPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultStPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoResultStPointDetailDTO maWoResultStPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
}
