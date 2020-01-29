package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * 설비 정기점검 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsListService
{   
	/**
	 * 설비 예방점검 목록 검색
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user) throws Exception;
    /**
     * 설비 예방점검 삭제
     * @param deletePmIdRows
     * @param deletePmEquipIdRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqPmInsList(String[] deletePmIdRows, String[] deletePmEquipIdRows, User user) throws Exception;
	/**
	 * 설비 예방점검 목록 개수 COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user) throws Exception;

}
