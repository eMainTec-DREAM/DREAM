package dream.mgr.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;

/**
 * 가동달력설정 - 목록 service
 * @author  euna0207
 * @version $Id: MgrCalLineTimeSetService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalLineTimeSetService
{     
    /**
     *  grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeSetService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @param mgrCalLineTimeSetDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List<MgrCalLineTimeSetDTO> findList(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);

    public String findTotalCount(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);

    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    public MgrCalLineTimeSetDTO findDetail(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user)throws Exception;

    public int updateDetail(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) throws Exception;
    
    public int insertDetail(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) throws Exception;
    
    public int[] insertDetail(List<MgrCalLineTimeSetDTO> list, User user) throws Exception;
}
