package dream.mgr.exldata.service;

import java.util.List;

import common.bean.User;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
import dream.mgr.exldata.dto.MgrExldataDetailDTO;
/**
 * Excel Data Upload - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrExldataDetailService
{    
	/**
	 * FIND DETAIL
	 * @param mgrExldataCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findExldataDetail(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
	/**
	 * INSERT
	 * @param mgrExldataDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertExldataDetail(MgrExldataDetailDTO mgrExldataDetailDTO, User user) throws Exception;
    /**
     * UPDATE
     * @param mgrExldataDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateExldataDetail(MgrExldataDetailDTO mgrExldataDetailDTO, User user) throws Exception;
    
    public void uploadExldataDetail(MgrExldataDetailDTO mgrExldataDetailDTO, User user) throws Exception;
    
    public List findField(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
}
