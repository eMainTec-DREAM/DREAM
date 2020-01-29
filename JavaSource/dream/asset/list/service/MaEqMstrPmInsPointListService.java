package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * ���� ��������-�����׸� ���
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsPointListService
{   
	/**
	 * �������� �����׸� �˻�
	 * @param maEqMstrPmInsDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmInsPointList(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception;
    /**	
     * ���� �������� �׸� ����
     * @param deletePmIdRows
     * @param deletePmPointIdRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqPmInsPointList(String[] deletePmIdRows, String[] deletePmPointIdRows, User user) throws Exception;
	/**
	 * ���� �������� �׸� ��� ���� COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception;

}
