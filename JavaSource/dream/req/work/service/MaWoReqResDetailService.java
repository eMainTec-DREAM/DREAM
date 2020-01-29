package dream.req.work.service;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;

/**
 * 작업요청 - 처리사항
 * @author  kim210117
 * @version $Id: MaWoReqResDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoReqResDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoReqResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoReqResDetailDTO findDetail(String woReqId, String woReqResId,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoReqResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailDTO
     * @param maWoReqCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoReqResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailDTO
     * @param maWoReqCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO) throws Exception;
}
