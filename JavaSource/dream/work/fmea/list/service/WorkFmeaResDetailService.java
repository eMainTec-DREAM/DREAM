package dream.work.fmea.list.service;

import common.bean.User;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaResDetailDTO;
/**
 * ���念�⼺�� - Detail Service
 * @author kim21017
 * @version $Id: WorkFmeaResDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface WorkFmeaResDetailService
{    
	/**
	 * FIND DETAIL
	 * @param workFmeaResCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkFmeaResDetailDTO findDetail(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
	 * @param workFmeaResDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param workFmeaResDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception;
    /**
     * COMPLETED
     * @param workFmeaResDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int completedDetail(WorkFmeaResCommonDTO workFmeaResCommonDTO, WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception;

}
