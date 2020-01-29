package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaWoReqDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public MaWoReqDetailDTO findDetail(MaWoReqCommonDTO maWoReqCommonDTO, User loginUser)throws Exception;
   
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
    public int insertDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;


    public int updateStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
    public int updateIncStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
    
    public int updateReqStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
    
    public String checkValidRecDept(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;

    public List findWoRecReport(MaWoReqDetailDTO maWoReqDetailDTO, User user);
    
    public String checkStatus(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;
    
    public int chkExistCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception; 
}
