package dream.work.cal.pmrinsyear.service;

import java.util.List;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.work.cal.pmrinsyear.dto.WorkCalPmRInsYearCommonDTO;

/**
 * �����۾����� - ��� service
 * @author  kim21017
 * @version $Id: WorkCalPmRInsYearListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmRInsYearListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsYearListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workCalPmRInsYearCommonDTO
     * @since   1.0
     *
     * @return ��ȸ ���
     * @throws Exception
     */
    public List findSchedMonthlyList(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);
    public List findYearReport(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsYearListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteSched(String[] deleteRows,User user) throws Exception;

    
    /**
     * ������ ����
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     *
     * @param gridList
     * @param user
     */
    public void updateSchedule(List gridList, User user);
    
    public String findTotalCount(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);
    
	public String findTotalCountByMonthly(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user) throws Exception;

    /**
     * �ϴ��� Ȯ��
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param fixRows
     * @param workCalPmRInsYearCommonDTO
     * @return
     * @throws Exception
     */
    public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user) throws Exception;
    
}
