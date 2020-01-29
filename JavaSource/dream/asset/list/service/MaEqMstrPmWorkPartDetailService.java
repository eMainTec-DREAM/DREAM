package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * ���� �����۾� ��ǰ ��
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkPartDetailService
{    
	/**
	 * ���� �����۾� ��ǰ �� ��ȸ
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmWorkPartDetailDTO findDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user)throws Exception;
    /**
     * ���� �����۾� ��ǰ ���� 
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user) throws Exception;
    /**
     * ���� �����۾� ��ǰ �Է�
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO, User user) throws Exception;
}
