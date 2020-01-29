package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmPointUInsDetailDTO;
/**
 * ��뷮 �׸� - Detail Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkPmPointUInsDetailService
{    
	/**
	 * ��뷮 �׸� DETAIL
     * @author js.lee
	 * @param workPmPointUInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkPmPointUInsDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception;
	/**
	 * INSERT ��뷮 �׸� DETAIL
     * @author js.lee
	 * @param workPmPointUInsDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception;
	/**
	 * INSERT ��뷮 �׸� DETAIL
     * @author js.lee
	 * @param workPmPointUInsDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int updateDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception;
    
    /**
     * �������� �ڵ� ����
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
