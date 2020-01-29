package dream.part.rpt.mayearptsched.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;

/**
 * 연간부품사용일정 DAO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public interface MaPmYearPtSchedListDAO
{
    /**
     * 연간 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findYearList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user);
    
    /**
     * 부품별 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findPartsList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;
    
    /**
     * 일자별 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findDateList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;

    // 부품별 FIND TOTAL LIST
    public String findPartsTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;

    // 일자별 FIND TOTAL LIST
    public String findDateTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;
    
}