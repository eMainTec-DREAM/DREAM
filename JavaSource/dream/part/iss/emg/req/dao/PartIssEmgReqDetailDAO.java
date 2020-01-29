package dream.part.iss.emg.req.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 부품출고 - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartIssEmgReqDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param partIssEmgReqCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public PartIssEmgReqDetailDTO findIssReqDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param partIssEmgReqDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param partIssEmgReqDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;

    public int updateStatus(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    public int updateIssListStatus(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    public int updateReqInfo(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String[] getPtemgisslistIds(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    
}