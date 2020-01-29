package dream.part.pur.buy.service;

import java.util.List;

import common.bean.User;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 구매신청 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaPtBuyReqHdrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaPtBuyReqHdrDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @return
     * @throws Exception
     */
    public MaPtBuyReqHdrDetailDTO findDetail(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO,User user)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     * @throws Exception
     */
    public String[] confirmDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user) throws Exception;
    /**
     * detail check
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     * @throws Exception
     */
    public String checkDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO) throws Exception;

    public List getReportView(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO);

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;

    public MaPtBuyReqHdrDetailDTO getStatus(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user) throws Exception;
}
