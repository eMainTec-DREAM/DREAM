package dream.work.rpt.mabdpoint.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;

/**
 * 이상점검조치 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 */
public interface MaBdPointDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param loginUser
     * @return
     */
    public MaBdPointDetailDTO findInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser);
    public MaBdPointDetailDTO findPInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser);
    public MaBdPointDetailDTO findRInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser);
    public MaBdPointDetailDTO findDInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser);
    public MaBdPointDetailDTO findCInsDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser);
    public String selectPmiType(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointDetailDTO
     * @return
     */
    public int updateDetail(MaBdPointDetailDTO maBdPointDetailDTO);

    public int updateNgDetail(MaBdPointDetailDTO maBdPointDetailDTO);
    
    public int deleteWongPoint(String pmiType, String listKeyId, User user);
    
    public String getWongPointCount(String pmiType, String listKeyId, String pointKeyId, User user);
    
    public int insertWongDetail(String pmiType, String listKeyId, String pmPointId, String wkorDate, String pointKeyId, String rsltValue, String remark, User user);
    
    public int updateWongDetail(String pmiType, String listKeyId, String pointKeyId, String actualDate, String rsltValue, String remark, User user);
    
    public String findId(MaBdPointDetailDTO maBdPointDetailDTO, User loginUser)throws Exception;
}