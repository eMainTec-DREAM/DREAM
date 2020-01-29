package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;

/**
 * 작업요청 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 */
public interface MaWoReqDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @param user
     * @return
     */
    public MaWoReqDetailDTO findDetail(MaWoReqCommonDTO maWoReqCommonDTO, User user);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user);

    public int insertDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user);

    public int updateStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user);

    public int updateIncStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user);
    public int updateReqStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user);
    public String getRecBy(MaWoReqDetailDTO maWoReqDetailDTO, User user);

    public String checkValidRecDept(MaWoReqDetailDTO maWoReqDetailDTO, User user);
    
    public List findWoRecReport(MaWoReqDetailDTO maWoReqDetailDTO, User user);
    
    public String chkWoExistCnt(String woReqId, User user, String method);
    
    public String chkInvtExistCnt(String woReqId, User user, String method);
    
    public String chkWoStCnt(String woReqId, User user, String method);
 
    public String chkInvtStCnt(String woReqId, User user, String method);
    
    public int changeHdrStatus(String woReqId, String changeStatus, User user);
}