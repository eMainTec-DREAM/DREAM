package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * 설비 정기점검-점검항목 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsPointListService
{   
	/**
	 * 예방점검 점검항목 검색
	 * @param maEqMstrPmInsDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmInsPointList(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception;
    /**	
     * 설비 예방점검 항목 삭제
     * @param deletePmIdRows
     * @param deletePmPointIdRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqPmInsPointList(String[] deletePmIdRows, String[] deletePmPointIdRows, User user) throws Exception;
	/**
	 * 설비 예방점검 항목 목록 개수 COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception;

}
