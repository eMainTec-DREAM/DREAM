package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * 설비 정기작업 부품 상세
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkPartDetailService
{    
	/**
	 * 설비 예방작업 부품 상세 조회
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmWorkPartDetailDTO findDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user)throws Exception;
    /**
     * 설비 예방작업 부품 수정 
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user) throws Exception;
    /**
     * 설비 예방작업 부품 입력
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user) throws Exception;
}
