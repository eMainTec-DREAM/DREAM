package dream.part.rpt.mayearptsched.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;

/**
 * ������ǰ������� service
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public interface MaPmYearPtSchedListService
{     
    /**
     * ���� grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return ��ȸ ��� 
     * @throws Exception
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
     * @return ��ȸ ��� 
     * @throws Exception
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
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findDateList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;

    // ��ǰ�� FIND TOTAL LIST
    public String findPartsTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;
    
    // ���ں� FIND TOTAL LIST
    public String findDateTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception;

        
}
