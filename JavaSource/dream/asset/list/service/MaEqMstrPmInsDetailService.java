package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * 설비 정기점검 상세
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsDetailService
{    
	/**
	 * 설비 예방점검 상세 조회
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmInsDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user)throws Exception;
    /**
     * 설비 예방점검 수정 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user) throws Exception;
    /**
     * 설비 예방점검 입력
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user) throws Exception;
}
