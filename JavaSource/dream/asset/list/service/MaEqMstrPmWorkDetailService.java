package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * 설비 정기작업 상세
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkDetailService
{    
	/**
	 * 설비 예방작업 상세 조회
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmWorkDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user)throws Exception;
    /**
     * 설비 예방작업 수정 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmWorkDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) throws Exception;
    /**
     * 설비 예방작업 입력
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmWorkDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) throws Exception;
}
