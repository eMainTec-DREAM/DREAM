package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * ���� �������� �׸� ��
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsPointDetailService
{    
	/**
	 * ���� �������� �׸� �� ��ȸ
	 * @param MaEqMstrPmInsDetailDTO
	 * @param maEqMstrPmInsPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmInsPointDetailDTO findDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user)throws Exception;
    /**
     * ���� �������� ���� 
	 * @param MaEqMstrPmInsDetailDTO
	 * @param maEqMstrPmInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO, User user) throws Exception;
    /**
     * ���� �������� �Է�
	 * @param MaEqMstrPmInsDetailDTO
	 * @param maEqMstrPmInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO, User user) throws Exception;
}
