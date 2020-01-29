package dream.part.rpt.mayearptsched.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;

/**
 * ������ǰ������� DAO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public interface MaPmYearPtSchedListDAO
{
    /**
     * ���� grid find
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
     * ��ǰ�� grid find
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
     * ���ں� grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findDateList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;

    // ��ǰ�� FIND TOTAL LIST
    public String findPartsTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;

    // ���ں� FIND TOTAL LIST
    public String findDateTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;
    
}