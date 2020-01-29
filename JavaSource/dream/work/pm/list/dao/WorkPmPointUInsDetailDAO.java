package dream.work.pm.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmPointUInsDetailDTO;

/**
 * 사용량 항목 - Detail DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkPmPointUInsDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @author js.lee
     * @param workPmPointUInsListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmPointUInsDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @author js.lee
     * @param workPmPointUInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @author js.lee
     * @param workPmPointUInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception;
    
    /**
     * 측정순서 자동 셋팅
     * @author js.lee
     * @since   1.0
     *
     * @param workPmPointUInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String setStepNum(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception;
    
    

}