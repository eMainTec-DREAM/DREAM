package dream.note.daily.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 *  - »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 */
public interface MaWoDailyDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureCommonDTO
     * @param loginUser
     * @return
     */
    public MaWoDailyDetailDTO findDetail(MaWoDailyCommonDTO maWoDailyCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @param user 
     * @since   1.0
     * 
     * @param IfFailureDetailDTO
     * @return
     */
    public int updateDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User user);

    public int insertDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User loginUser);
    
    public int insertBmActivities(MaWoDailyDetailDTO maWoDailyDetailDTO, User loginUser);

    public String checkList(MaWoDailyDetailDTO maWoDailyDetailDTO);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
    
    public List findReportWoDetail(MaWoDailyDetailDTO maWoDailyDetailDTO);
    
    public List findReportWorkList(MaWoDailyDetailDTO maWoDailyDetailDTO);
    
    public List findReportSLPWorkList1(MaWoDailyDetailDTO maWoDailyDetailDTO, User user);
    
    public List getListSize(MaWoDailyDetailDTO maWoDailyDetailDTO, User user);

    public List findReportSLPWorkList2(MaWoDailyDetailDTO maWoDailyDetailDTO, User user);

    public int updateStatus(MaWoDailyDetailDTO maWoDailyDetailDTO, User loginUser);
}