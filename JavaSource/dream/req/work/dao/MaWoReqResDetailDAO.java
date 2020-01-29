package dream.req.work.dao;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;

/**
 * 작업요청-처리사항 상세 dao
 * @author  kim21017
 * @version $Id: MaWoReqResDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoReqResDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoReqResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public MaWoReqResDetailDTO findDetail(String woReqId, String woReqResId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoReqResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailDTO
     * @param maWoReqCommonDTO
     * @return
     */
    public int updateDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoReqResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailDTO
     * @param maWoReqCommonDTO
     * @return
     */
    public int insertDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO);
    
    public int changeHdrStatus(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO);
    
    /**
     * 투자목록 상태 변경시, 연관된 요청 처리사항 상태 변경
     * @param maWoReqResDetailDTO
     * @return
     */
    public int setResStatus(MaWoReqResDetailDTO maWoReqResDetailDTO, User user);
    
    public String chkWoStCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method);

    public String chkInvtStCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method);
    
    public String chkWoExistCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method);

    public String chkInvtExistCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method);
    
    public int setWoResStatus(MaWoReqCommonDTO maWoReqCommonDTO, String status, User user);
}