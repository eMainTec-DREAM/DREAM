package dream.mgr.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalLineTimeDowSetDTO;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;

/**
 * 요일별 설정  목록
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalLineTimeDowSetService
{     
    /**
     *  grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @param mgrCalLineTimeDowSetDTO
     * @param loginUser
     * @throws Exception
     */
    public List<MgrCalLineTimeSetDTO> findDowList(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User loginUser);
    /**
     *  delete
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param deleteRowsExt
     * @throws Exception
     */
    public int[] deleteDowList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);

    public MgrCalLineTimeDowSetDTO findDetail(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user)throws Exception;

    public int updateDetail(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception;

    public int insertDetail(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception;

    public int[] insertDetail(List<MgrCalLineTimeDowSetDTO> list, User user) throws Exception;

    public String validDay(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception;
}
